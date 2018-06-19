package main.java.sudoku.util;

import processing.core.PApplet;

public class Input {
    
    public static PApplet parent;
    public static int keyIsHeld;
    private static boolean[] wasHeld = new boolean[3];
    private static boolean[] isHeld = new boolean[3];
    private static boolean hasKey;
    private static Coordinate mouse = new Coordinate(0, 0);
    private static int mouseScroll;
    private static boolean hasScrolled;
    
    /**
     * Runs every frame to update the data of this frame and the previous
     */
    public static void updateInput() {
        for (int i = 0; i < 3; i++) {
            wasHeld[i] = isHeld[i];
            isHeld[i] = parent.mousePressed && parent.mouseButton == Button.values()[i].keycode;
        }
        mouse.x = parent.mouseX;
        mouse.y = parent.mouseY;
        if (!hasScrolled)
            mouseScroll = 0;
        hasScrolled = false;
    
        if (!hasKey) keyIsHeld = 0;
        hasKey = false;
    }
    
    public static void setScroll(int scroll) {
        mouseScroll = scroll;
        hasScrolled = true;
    }
    
    public static void setKey(int key) {
        keyIsHeld = key;
        hasKey = true;
    }
    
    public static int getMouseScroll() {
        return mouseScroll;
    }
    
    /**
     * Wraps the default way of getting mouse input with Processing into a neater and more accessible method
     *
     * @param button the mouse button to check
     * @param event  the type of event to check for
     * @return the type of the event and mouse queried
     */
    public static boolean getMouseButton(Button button, Event event) {
        switch (event) {
            case PRESS:
                return !wasHeld[button.ordinal()] && isHeld[button.ordinal()];
            case HELD:
                return isHeld[button.ordinal()];
            case RELEASE:
                return wasHeld[button.ordinal()] && !isHeld[button.ordinal()];
        }
    
        return false;
    }
    
    /**
     * Wraps the default way of getting keyboard input with Processing into a neater and more accessible method
     *
     * @param event the type of event to check for
     * @return the type of the event and mouse queried
     */
    public static boolean getKeyEvent(Event event) {
        return keyIsHeld != 0;
    }
    
    /**
     * Wraps the mouse coordinates into a Coordinate object.
     *
     * @return the position of the mouse in Coordinate form
     */
    public static Coordinate getMousePosition() {
        return mouse;
    }
    
    public enum Button {
        LEFT(37), MIDDLE(3), RIGHT(39);
        
        private final int keycode;
        
        Button(int keycode) {
            this.keycode = keycode;
        }
    }
    
    public enum Event {
        PRESS, HELD, RELEASE
    }
}
