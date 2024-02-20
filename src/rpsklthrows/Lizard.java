package rpsklthrows;

import java.awt.*;
import java.awt.geom.*;

/**
 * Lizard is a MovingObject that represents a lizard in RPSKL. It is a green
 * square that moves from right to left.
 * 
 * @author Steven Long (sl5259)
 */
public class Lizard implements MovingObject {
    /**
	 * Constructor for a Lizard using a unit size and relative x and y
	 * coordinates.
	 * 
	 * @param x   x coordinate
	 * @param y  y coordinate
	 * @param unit unit size
	 */
	public Lizard (int x, int y, int unit) {
		this.x = x;
		this.y = y;
        this.unit = unit; // unit is in pixels
        this.liz = new Rectangle2D.Double(x, y, unit, unit);
	}

    /**
     * @inheritDoc
     * 
     */
    @Override
    public void draw(Graphics2D g2D) {
        this.liz = new Rectangle2D.Double(this.x, this.y, this.unit, this.unit);

        g2D.setColor(Color.GREEN);
        g2D.fill(liz);
        g2D.draw(liz);
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
     * The standard move for a lizard is to move from right to left.
     */
    @Override
    public void standardMove(int speed) {
        this.translate(-speed, 0);
    }
    
    /**
     * @inheritDoc
     */
    @Override
    public Shape getShape() {
        return liz;
    }

    /**
     * @inheritDoc
     */
    @Override
    public int getIndex() {
        return ind;
    }
 
    private int ind = 4;
    private Rectangle2D.Double liz;
    private int x;
	private int y;
    private int unit;
}
