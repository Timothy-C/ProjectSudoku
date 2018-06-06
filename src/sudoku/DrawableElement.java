package sudoku;

import processing.core.PApplet;

public abstract class DrawableElement {
    protected PApplet parent;
    protected Coordinate position;

    public DrawableElement(PApplet parent) {
        this.parent = parent;
    }

    /**
     * Runs every frame, run logic related code in here.
     */
    public abstract void update();

    /**
     * Runs every frame, run graphics and user related code in here.
     */
    public abstract void draw();

    /**
     * Changes the position of this element using two integers
     *
     * @param x x-coordinate in pixels
     * @param y y-coordinate in pixels
     */
    public void setPosition(int x, int y) {
        this.position = new Coordinate(x, y);
    }

    /**
     * Changes the position of this element using a {@link Coordinate Coordinate}.
     * @param position {@link Coordinate Coordinate} to be set
     */
    public void setPosition(Coordinate position) {
        this.position = position;
    }
}
