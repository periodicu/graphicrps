package rpsklthrows;

import java.awt.*;
import java.awt.geom.*;

/**
 * Scissors is a MovingObject that represents scissors in RPSKL. It is a red
 * square that moves from left to right.
 * 
 * @author Steven Long (sl5259)
 */
public class Scissors implements MovingObject {
    /**
	 * Constructor for Scissors using a unit size and relative x and y
	 * coordinates.
	 * 
	 * @param x   x coordinate
	 * @param y  y coordinate
	 * @param unit unit size
	 */
	public Scissors (int x, int y, int unit) {
		this.x = x;
		this.y = y;
		this.unit = unit; // unit is in pixels
        this.scissors = new Rectangle2D.Double(x, y, unit, unit);
	}

    /**
     * @inheritDoc
     */
    @Override
    public void draw(Graphics2D g2D) {
        this.scissors = new Rectangle2D.Double(this.x, this.y, this.unit, this.unit);

        g2D.setColor(Color.RED);
        g2D.fill(scissors);
        g2D.draw(scissors);
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
     * The standard move for a scissors is to go left to right.
     */
    @Override
    public void standardMove(int speed) {
        this.translate(speed, 0);
    }
    
    /**
     * @inheritDoc
     */
    @Override
    public Shape getShape() {
        return scissors;
    }
 
    /**
     * @inheritDoc
     */
    @Override
    public int getIndex() {
        return ind;
    }
 
    private int ind = 2;
    private Rectangle2D.Double scissors;
    private int x;
	private int y;
	private int unit;
}
