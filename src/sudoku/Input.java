package sudoku;

import processing.core.PApplet;

enum Event {
    PRESS, HELD, RELEASE
}

enum Button {
    LEFT, MIDDLE, RIGHT
}

public class Input {

    public static PApplet parent;
    private static boolean[] wasHeld = new boolean[3];
    private static boolean[] isHeld = new boolean[3];

    public static void updateInput() {
        for (int i = 0; i < 3; i++) wasHeld[i] = isHeld[i];
        isHeld[0] = parent.mousePressed && parent.mouseButton == parent.LEFT;
        isHeld[1] = parent.mousePressed && parent.mouseButton == parent.CENTER;
        isHeld[2] = parent.mousePressed && parent.mouseButton == parent.RIGHT;
    }

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
}
