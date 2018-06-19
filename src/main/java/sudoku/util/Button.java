package main.java.sudoku.util;

import processing.core.PApplet;
import processing.core.PConstants;

public class Button extends DrawableElement {
    private Coordinate size;
    private String label;
    private Runnable action;
    private int colour;
    private int hoverColour;
    
    public Button(PApplet parent, Coordinate position, Coordinate size, String label, Runnable action) {
        super(parent);
        this.position = position;
        this.size = size;
        this.colour = SolarizedColours.getColour(4);
        this.hoverColour = SolarizedColours.getColour(1);
        this.label = label;
        this.action = action;
    }
    
    /**
     * Checks if the mouse is within its bounds. (hovering)
     *
     * @return whether the mouse is hovering over the button
     */
    private boolean hovering() {
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
        if (hovering()) {
            parent.fill(hoverColour);
        } else {
            parent.fill(colour);
        }
        parent.rect(position.x, position.y, size.x, size.y);
        parent.fill(SolarizedColours.getText());
        parent.textFont(parent.createFont("Consolas", 30, true));
        parent.textSize(size.y / 2);
        parent.textAlign(PConstants.CENTER, PConstants.CENTER);
        parent.text(label, position.x + size.x / 2, position.y + size.y / 2);
        parent.textAlign(PConstants.LEFT);
    }
}
