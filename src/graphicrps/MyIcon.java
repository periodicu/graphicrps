package graphicrps;

import java.awt.*;
import javax.swing.*;

import rpsklthrows.MovingObject;

/**
 * A class that implements the Icon interface to draw a MovingObject.
 * 
 * @author jrk based on cay horstmann, documented by Steven Long (sl5259)
 *
 */
public class MyIcon implements Icon {
	/**
	 * Standard constructor for a MyIcon.
	 * 
	 * @param obj the MovingObject to draw
	 * @param w  the width of the icon
	 * @param h the height of the icon
	 */
	public MyIcon(MovingObject obj, int w, int h) {
		this.obj = obj;
		this.w = w;
		this.h = h;
	}

	/**
	 * Getter for the width of the icon.
	 * 
	 * @return the width of the icon
	 */
	@Override
	public int getIconWidth() {
		return w;
	}

	/**
	 * Getter for the height of the icon.
	 * 
	 * @return the height of the icon
	 */
	@Override
	public int getIconHeight() {
		return h;
	}

	/**
	 * Draws the icon in the given graphics context with the given coordinates.
	 * 
	 * @param c the component to draw on
	 * @param g the graphics context to draw on
	 * @param x the x coordinate of the icon
	 * @param y the y coordinate of the icon
	 */
	@Override
	public void paintIcon(Component c, Graphics g, int x, int y) {
		Graphics2D g2D = (Graphics2D) g;
		obj.draw(g2D);
	}

	private int w;
	private int h;
	private MovingObject obj;
}
