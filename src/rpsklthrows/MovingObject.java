package rpsklthrows;

import java.awt.*;

/**
 * This interface represents a moving object. Based on the StickFigure design
 * from class.
 * 
 * @author jrk based on cay horstmann, documented by Steven Long (sl5259)
 *
 */
public interface MovingObject {

	/**
	 * Draws the object in relation to its features, such as relative coords and
	 * unit size.
	 * 
	 * @param g2D the graphics object to draw on
	 */
	void draw(Graphics2D g2D);

	/**
	 * Moves the object by the given amount in the x and y directions.
	 * 
	 * @param xChange change in x
	 * @param yChange change in y
	 */
	void translate(int xChange, int yChange);

	/**
	 * Moves the object in the standard way for the game, as defined in the assignment.
	 * 
	 * @param speed the speed to move the object at
	 */
	void standardMove(int speed);

	/**
	 * Checks if this object intersects with another object.
	 * Default implementation to save space, downside is it requires getshape
	 * 
	 * @param other the other object to check for intersection
	 * @return true if the objects intersect, false otherwise
	 */
	default boolean intersects(MovingObject other) {
		Shape otherShape = other.getShape();
		Shape myShape = this.getShape();
		if (otherShape == null || myShape == null) {
			return false;
		}

		return myShape.intersects(otherShape.getBounds2D());
	}

	/**
	 * Returns the shape of the object, used for intersection checking.
	 * 
	 * @return the shape of the object
	 */
	Shape getShape();

    /**
     * Returns the set index of the object, as defined by the rules.
	 * 
	 * Could've used reflection, but this is easier and faster. Downside is this
     * already large class gets even larger. At least it's under 7 methods still.
	 * 
	 * @return the index of the object
     */
	int getIndex();

}
