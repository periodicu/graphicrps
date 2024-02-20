package rpsklthrows;

import java.awt.*;
import java.awt.geom.*;

/**
 * Rock is a MovingObject that represents a rock in RPSKL. It is a dark gray
 * square that does not move.
 * 
 * @author Steven Long (sl5259)
 */
public class Rock implements MovingObject {
    /**
	 * Constructor for a Rock using a unit size and relative x and y
	 * coordinates.
	 * 
	 * @param x   x coordinate
	 * @param y  y coordinate
	 * @param unit unit size
	 */
	public Rock (int x, int y, int unit) {
		this.x = x;
		this.y = y;
        this.unit = unit; // unit is in pixels
        this.rock = new Rectangle2D.Double(x, y, unit, unit);
	}

    /**
     * @inheritDoc
     */
    @Override
    public void draw(Graphics2D g2D) {
        this.rock = new Rectangle2D.Double(this.x, this.y, this.unit, this.unit);

        g2D.fill(rock);
        g2D.draw(rock);
    }

    /**
     * @inheritDoc
     */
    @Override
    public void translate(int xChange, int yChange) {
        x += xChange;
        y += yChange;
    }

    /**
     * The standard move for a rock is to stay still.
     */
    @Override
    public void standardMove(int speed) {
    }

    /**
     * @inheritDoc
     */
    @Override
    public Shape getShape() {
        return rock;
    }

    /**
     * @inheritDoc
     */
    @Override
    public int getIndex() {
        return ind;
    }
 
    private int ind = 0;
    private Rectangle2D.Double rock;
    private int x;
	private int y;
    private int unit;
}
