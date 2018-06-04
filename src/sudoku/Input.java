package sudoku;

import processing.core.PApplet;

public class Input {

    PApplet parent;
    boolean[] wasHeld = new boolean[3];
    boolean[] isHeld = new boolean[3];
    boolean wasHeldL, isHeldL;
    boolean wasHeldR, isHeldR;
    boolean wasHeldM, isHeldM;

    void updateInput() {
        for (int i = 0; i < 3; i++) {
            wasHeld[i] = isHeld[i];

        }

        isHeld[0] = parent.mousePressed && parent.mouseButton == parent.LEFT;
        isHeld[1] = parent.mousePressed && parent.mouseButton == parent.CENTER;
        isHeld[2] = parent.mousePressed && parent.mouseButton == parent.RIGHT;
    }

    boolean getMouseButton(Button button, Event event) {


        return false;
    }

    enum Event {
        PRESS, HELD, RELEASE
    }

    enum Button {
        LEFT, MIDDLE, RIGHT
    }
}
