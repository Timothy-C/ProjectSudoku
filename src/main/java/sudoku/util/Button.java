package main.java.sudoku.util;

import processing.core.PApplet;
import processing.core.PConstants;

public class Button extends DrawableElement {
    private Coordinate size;
    private String label;
    private Runnable action;
    private int colour;
    
    public Button(PApplet parent, Coordinate position, Coordinate size, int colour, String label, Runnable action) {
        super(parent);
        this.position = position;
        this.size = size;
        this.colour = colour;
        this.label = label;
        this.action = action;
    }
    
    public Button(PApplet parent, Coordinate position, Coordinate size, int colour) {
        this(parent, position, size, colour, null, () -> {
        });
    }
    
    public Button(PApplet parent, Coordinate position, Coordinate size) {
        this(parent, position, size, 0xFFFFFFFF, null, () -> {
        });
    }
    
    public boolean hovering() {
        Coordinate mouse = Input.getMousePosition();
        return mouse.x > position.x && mouse.x < position.x + size.x &&
                mouse.y > position.y && mouse.y < position.y + size.y;
    }
    
    @Override
    public void update() {
        if (hovering() && Input.getMouseButton(Input.Button.LEFT, Input.Event.PRESS)) {
            action.run();
        }
    }
    
    @Override
    public void draw() {
        parent.rectMode(PConstants.CORNER);
        parent.fill(colour);
        parent.rect(position.x, position.y, size.x, size.y);
    }
}
