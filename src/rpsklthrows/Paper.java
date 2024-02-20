package rpsklthrows;

import java.awt.*;
import java.awt.geom.*;

/**
 * Lizard is a MovingObject that represents paper in RPSKL. It is a light gray
 * square that moves downward.
 * 
 * @author Steven Long (sl5259)
 */
public class Paper implements MovingObject {
    /**
	 * Constructor for a Paper using a unit size and relative x and y
	 * coordinates.
	 * 
	 * @param x   x coordinate
	 * @param y  y coordinate
	 * @param unit unit size
	 */
	public Paper (int x, int y, int unit) {
		this.x = x;
		this.y = y;
        this.unit = unit; // unit is in pixels
        this.paper = new Rectangle2D.Double(x, y, unit, unit);
	}

    /**
     * @inheritDoc
     */
    @Override
    public void draw(Graphics2D g2D) {
        this.paper = new Rectangle2D.Double(this.x, this.y, this.unit, this.unit);

        g2D.setColor(Color.GRAY);
        g2D.fill(paper);
        g2D.draw(paper);
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
     * The standard move for a paper is to float downward.
     */
    @Override
    public void standardMove(int speed) {
        this.translate(0, speed);
    }
    
    /**
     * @inheritDoc
     */
    @Override
    public Shape getShape() {
        return paper;
    }

    /**
     * @inheritDoc
     */
    @Override
    public int getIndex() {
        return ind;
    }
 
    private int ind = 1;
    private Rectangle2D.Double paper;
    private int x;
	private int y;
    private int unit;
}
