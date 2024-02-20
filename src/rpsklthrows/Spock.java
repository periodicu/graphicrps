package rpsklthrows;

import java.awt.*;
import java.awt.geom.*;

/**
 * Spock is a MovingObject that represents spock in RPSKL. It is a blue
 * square that moves upward.
 */
public class Spock implements MovingObject {
    /**
	 * Constructor for a Spock using a unit size and relative x and y
	 * coordinates.
	 * 
	 * @param x   x coordinate
	 * @param y  y coordinate
	 * @param unit unit size
	 */
	public Spock (int x, int y, int unit) {
		this.x = x;
		this.y = y;
        this.unit = unit; // unit is in pixels
        this.spock = new Rectangle2D.Double(x, y, unit, unit);
	}

    /**
     * @inheritDoc
     */
    @Override
    public void draw(Graphics2D g2D) {
        this.spock = new Rectangle2D.Double(this.x, this.y, this.unit, this.unit);

        g2D.setColor(Color.BLUE);
        g2D.fill(spock);
        g2D.draw(spock);
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
     * The standard move for a spock is to rise up.
     */
    @Override
    public void standardMove(int speed) {
        this.translate(0, -speed);
    }
    
    /**
     * @inheritDoc
     */
    @Override
    public Shape getShape() {
        return spock;
    }

    /**
     * @inheritDoc
     */
    @Override
    public int getIndex() {
        return ind;
    }
 
    private int ind = 3;
    private Rectangle2D.Double spock;
    private int x;
	private int y;
    private int unit;
}
