package graphicrps;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import rpsklthrows.MovingObject;
import rpsklthrows.Rock;

/**
 * A service provider for the graphical interface. Creates the frame, buttons,
 * and handles the moving objects. Think of it as basically a setup and
 * teardown manager for a stage of some sort. Most of the methods are protected
 * so the user can't mess with them.
 * 
 * @author Steven Long (sl5259)
 */
public class GraphicalProvider {

    /**
     * Does the initial setup for the graphical interface. Includes a frame,
     * a top panel for the buttons, and a main panel for the moving objects.
     */
    GraphicalProvider() {
        this.mover = new Mover();
        this.myFrame = new JFrame("Welcome to Rock, Paper, Scissors, Lizard, Spock!");
        this.myFrame.setLayout(new BorderLayout());
        this.topPanel = new JPanel();
        this.topPanel.setLayout(new BoxLayout(this.topPanel, BoxLayout.X_AXIS));
        this.mainPanel = new JPanel();
        this.mainPanel.setLayout(new BorderLayout());
    }

    /**
     * Glues the panels together and adds them to the frame.
     */
    void addPanels() {
		this.myFrame.add(topPanel, BorderLayout.NORTH);
		this.myFrame.add(mainPanel, BorderLayout.CENTER);
	}

    /**
     * Creates all the buttons in the frame, first the factory buttons, then the
     * fight button.
     */
	void drawButtons() {
		for (String name : Librarian.NAMES) {
            JButton button = createFactoryButton(name);
            this.topPanel.add(button);
        }

        JButton fightButton = createFightButton();
        this.mainPanel.add(fightButton, BorderLayout.SOUTH);
	} 

    private JButton createFactoryButton(String name) {
        JButton button = new JButton("Click for " + name);
        button.addActionListener(
                e -> {
                    MovingObject myObj = Librarian.createThrow(name);
                    JLabel myLabel = addObject(myObj);
                    mover.rememberThrow(myObj, myLabel);
                });

        return button;
    }

    /**
     * Adds the object to the main panel and returns the label for the object.
     * Since this class only does setup, it only adds objects created using
     * the factory buttons. Left as protected since it may be useful for other
     * classes during refactoring.
     * 
     * @param myObj the object to add
     * @return the completed label for the object
     */
    JLabel addObject(MovingObject myObj) {
        MyIcon myIcon = new MyIcon(myObj, Librarian.ICON_W, Librarian.ICON_H);
        JLabel myLabel = new JLabel(myIcon);
        this.mainPanel.add(myLabel, BorderLayout.CENTER);
        this.mainPanel.revalidate();
        this.mainPanel.repaint();

        return myLabel;
    }

    private JButton createFightButton() {
        JButton fightButton = new JButton("Fight! Press again to speed up.");
        fightButton.addActionListener(
                e -> {
                    // this.myFrame.remove(this.topPanel);
                    mover.startTimer(this.myFrame, this.mainPanel);
                });

        return fightButton;
    }

    /**
     * Starts the display. This is the last method called by the Runner.
     */
	void startDisplay() {
		// myFrame.setLayout(new BorderLayout());
		this.myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.myFrame.pack();

		myFrame.setSize(Librarian.ICON_W, Librarian.ICON_H);
        this.myFrame.setResizable(false);
		this.myFrame.setVisible(true);
	}

	/**
     * Creates a rock close to the middle of the frame when the frame is clicked.
     * No longer in use once the factory buttons were added. Kept in for q2.1
     * 
     * @deprecated replaced by {@link #createRockAfterPress()}
     */
	void createRockAfterClick() {
		this.myFrame.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				// create a rock in the middle of the frame
				MovingObject myRock = new Rock((Librarian.ICON_W - Librarian.STD_SIZE) / 2, (Librarian.ICON_H - Librarian.STD_SIZE) / 2, Librarian.STD_SIZE);
				addObject(myRock);
			}
		});
	}

    private JPanel topPanel;
    private JPanel mainPanel;
    private JFrame myFrame;
    private Mover mover;
}
