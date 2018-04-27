package sudoku;

import processing.core.PApplet;

public abstract class DrawableElement {
	protected PApplet parent;
	protected Coordinate position;
	
	public DrawableElement(PApplet parent) {
		this.parent = parent;
	}
	
	public abstract void draw();
	
	public void setPosition(int x, int y) {
		this.position = new Coordinate(x, y);
	}
	
	public void setPosition(Coordinate position) {
		this.position = position;
	}
}
