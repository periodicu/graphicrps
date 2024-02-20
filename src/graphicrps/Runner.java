package graphicrps;

/**
 * Runs a graphical version of RPSKL, simulating a number of throws instantiated
 * through factory buttons on some battlefield moving in a direction towards
 * each other, with the losers being removed from the game on collision with
 * another throw and ties spawning new throws.
 * NOTE: this program works a bit differently from the hints given, since I
 * discovered them a bit too late.
 * 
 * @author Steven Long (sl525) based on a design by jrk based on cay horstmann
 */
public class Runner {

	/**
	 * Main method runs the graphical RPSKL program. Most of the specifics of
	 * creating objects and adding them to the frame are handled by GraphicalProvider.
	 * GraphicalProvider then uses Mover to manage the moving objects and their
	 * collisions. This allows for a clean separation of concerns between the
	 * graphics and the game logic.
	 * Compared to the original Balloon program, the benefits of static methods
	 * are given up for the sake of cleaner structure within the Runner class.
	 * 
	 * There is some technical debt, only within the collision-handling logic
	 * in Mover, that results in some instability in some situations, such as
	 * when multiple throws collide at the same time, coinciding with a tie that
	 * spawns new throws. This causes the lists for the throws and labels to
	 * become non-aligned. These situations do not break the program.
	 * 
	 * Future iterations of this program could include a more robust collision
	 * handling system, perhaps by using a more rubust data structure, perhaps
	 * a tuple to keep the throws and labels together. Implementing a custom
	 * panel class could also be useful in this as well.
	 * 
	 * @param args not used
	 */
	public static void main(String[] args) {
		assembleDisplay();
	}

	private static void assembleDisplay() {
		GraphicalProvider maker = new GraphicalProvider();
		maker.addPanels();
		maker.drawButtons();
		maker.startDisplay();
	}
}