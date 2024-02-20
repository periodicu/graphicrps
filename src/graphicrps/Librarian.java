package graphicrps;

import java.util.Random;

import rpsklthrows.*;
/**
 * Librarian is an information holder class that contains the rules for RPKSL
 * and the full names of each move, and some other constants.
 * Rules constants are public in case users want to peek at rules or other 
 * real-world information. It also includes a factory method for creating throws,
 * since it is the only class that knows everything needed to do the job.
 * 
 * @author Steven Long(sl5259)
 * 
 */
public class Librarian {

    /**
     * A factory method for creating throws, used by any class that needs to
     * create throws, usually the factory buttons. This method is included in an
     * info holder class since it would be too small a class on its own.
     * 
     * @param name the name of the throw to create as a String
     * @return a new MovingObject/throw of the given name
     */
    public static MovingObject createThrow(String name) {
        int x = Math.max(0, rand.nextInt(ICON_W) - STD_SIZE - 30);
        int y = Math.max(0, rand.nextInt(ICON_H) - STD_SIZE - 70);
        switch (name) {
            case "rock":
                return new Rock(x, y, STD_SIZE);
            case "paper":
                return new Paper(x, y, STD_SIZE);
            case "scissors":
                return new Scissors(x, y, STD_SIZE);
            case "spock":
                return new Spock(x, y, STD_SIZE);
            case "lizard":
                return new Lizard(x, y, STD_SIZE);
            default:
                throw new IllegalArgumentException("Invalid name: " + name);
        }
    }

    /**
     * The ruleset for RPSKL: rock, paper, scissors, spock, lizard. Displayed here in a 2D array
     * as given in the problem specification with labels in comments.
     */
    public static final int[][] RULES = {
        // ROCK, PAPER, SCISSORS, SPOCK, LIZARD
        {0, -1, +1, -1, +1}, // ROCK
        {+1, 0, -1, +1, -1}, // PAPER
        {-1, +1, 0, -1, +1}, // SCISSORS
        {+1, -1, +1, 0, -1}, // SPOCK
        {-1, +1, -1, +1, 0}  // LIZARD
        
    };

    /**
     * A collection of all the valid names for RPKSL ordered in a standard
     * manner that matches the rulesets.
     */
    public static final String[] NAMES = {"rock", "paper", "scissors", "spock", "lizard"};

    /**
     * The standard size for all throws.
     */
    static final int STD_SIZE = 40;
    
    /**
     * The standard width and height for the icon.
     */
	static final int ICON_W = 600;
	static final int ICON_H = 250;

    private static Random rand = new Random();
}
