package graphicrps;

import java.util.LinkedList;
import java.util.ListIterator;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import rpsklthrows.MovingObject;

/**
 * Mover is a service provider for the moving objects instatiated by the factory
 * buttons. It handles the movement of the objects, the collisions, and the
 * spawning of new objects. It has a number of methods, but only two are
 * exposed: startTimer and rememberThrow. The rest are private and used for
 * internal bookkeeping.
 * 
 * @author Steven Long (sl5259)
 */
public class Mover {

    /**
     * Saves the throw and its label for later use, useful for removal.
     * 
     * @param myObj the throw to save
     * @param myLabel the label associated with the throw to save
     */
    void rememberThrow(MovingObject myObj, JLabel myLabel) {
        livingThrows.add(myObj);
        labels.add(myLabel);
    }

    /**
     * Starts the timer for the moving objects. Checks for intersections and
     * then moves the objects, repainting the screen on each tick. Intersections
     * result in spawning or execution of the losers. Simultaneous intersections
     * are handled in the order they were added to the game, as expected. Spawning
     * from ties is functional, but will not spawn more than 10 throws at a time.
     * This is to avoid population explosion, but can be changed or removed.
     * 
     * @param myFrame the frame to add the timer to
     */
    void startTimer(JFrame myFrame, JPanel mainPanel) {
        playGame(mainPanel);
        Timer coreTimer = new Timer(DELAY, new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                mainPanel.revalidate();
                mainPanel.repaint();
                playGame(mainPanel);
                moveAllThrows();
            }
        });
        coreTimer.start();
    }

    // too long, bit of tech debt here: should be refactored to be shorter, could
    // be blamed on graphics code being a mess (ed#131), but that's not a good
    // excuse. Comments to show intention are included.
    private void playGame(JPanel mainPanel) {
        // instantiate collections for thread safety
        ArrayList<Integer> losers = new ArrayList<>();
        ArrayList<MovingObject> newThrows = new ArrayList<>();

        ListIterator<MovingObject> iterator1 = livingThrows.listIterator();
        while (iterator1.hasNext()) {
            MovingObject obj1 = iterator1.next();
            ListIterator<MovingObject> iterator2 = livingThrows.listIterator(iterator1.nextIndex());
            while (iterator2.hasNext()) {
                MovingObject obj2 = iterator2.next();
                // reduce nesting level
                if (!obj1.intersects(obj2)) continue;

                int result = determineVictory(obj1, obj2);
                if (result == 1) {
                    findLosers(iterator2, losers);
                    break; // list modified, so exit inner
                } else if (result == -1) {
                    findLosers(iterator1, losers);
                    break; // list modified, so exit inner
                } else {
                    MovingObject newThrow = Librarian.createThrow(Librarian.NAMES[obj1.getIndex()]);
                    newThrows.add(newThrow);
                }
            }
        }

        executeLosers(losers, mainPanel);
        // limit the births on screen to avoid population explosion, feel free to
        // change or remove this, but beware of crashes.
        if (newThrows.size() < 10) {
            spawn(newThrows, mainPanel);
        }
    }

    private int determineVictory(MovingObject obj1, MovingObject obj2) {
        int throw1 = obj1.getIndex();
        int throw2 = obj2.getIndex();
        int result = Librarian.RULES[throw1][throw2];
        return result;
    }

    private void findLosers(ListIterator<MovingObject> iterator, ArrayList<Integer> losers) {
        int index = iterator.previousIndex();
        losers.add(index);
    }

    private void spawn(ArrayList<MovingObject> newThrows, JPanel mainPanel) {
        for (MovingObject obj : newThrows) {
            JLabel myLabel = drawSpawn(obj, mainPanel);
            rememberThrow(obj, myLabel);
        }
    }

    private JLabel drawSpawn(MovingObject obj, JPanel mainPanel) {
        MyIcon myIcon = new MyIcon(obj, Librarian.ICON_W, Librarian.ICON_H);
        JLabel myLabel = new JLabel(myIcon);
        mainPanel.add(myLabel, BorderLayout.CENTER);

        return myLabel;
    }

    private void executeLosers(ArrayList<Integer> losers, JPanel mainPanel) {
        for (int i : losers) {
            try {
                mainPanel.remove(this.labels.get(i));
                this.labels.remove(i);
                livingThrows.remove(i);
            } catch (IndexOutOfBoundsException e) {
                continue;
            }
        }
    }

    private void moveAllThrows() {
        int speed = random.nextInt(5) + 1;
        for (MovingObject obj : livingThrows) {
            obj.standardMove(speed);
        }
    }

    private Random random = new Random();
    private static final int DELAY = 200;
    private LinkedList<MovingObject> livingThrows = new LinkedList<>();
    private ArrayList<JLabel> labels = new ArrayList<>();
}
