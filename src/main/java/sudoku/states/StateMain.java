package main.java.sudoku.states;

import main.java.sudoku.Coordinate;
import main.java.sudoku.DrawableElement;
import main.java.sudoku.Input;
import main.java.sudoku.SolarizedColours;
import processing.core.PApplet;
import processing.core.PConstants;

public class StateMain extends GameState {

    public Button themeButton;
    public Button startButton;

    private StateMain(PApplet parent) {
        super(parent);
    }

    /**
     * Gets the singleton instance of this GameState
     *
     * @return the instance of this GameState
     */
    public static GameState getInstance() {
        if (instance == null) instance = new StateMain(GameEngine.getInstance().parent);
        return instance;
    }

    @Override
    public void start() {

        parent.fill(50f);
        //   parent.rect(30, 30, 30, 30);
        //parent.fill(50f);

        themeButton = new Button(
                parent,
                new Coordinate(100, 100), new Coordinate(50, 50),
                0xFF0000FF, "theme",
                () -> {
                    SolarizedColours.lightTheme = !SolarizedColours.lightTheme;
                    changeState(getInstance());
                }
        );
        startButton = new Button(
                parent,
                new Coordinate(60, 60), new Coordinate(50, 50),
                0xFFFF0000, "start",
                () -> changeState(StateGame.getInstance())
        );
    }

    @Override
    public void end() {

    }

    @Override
    public void update() {
        themeButton.update();
        startButton.update();
    }

    @Override
    public void draw() {
        themeButton.draw();
        startButton.draw();
    }

    class Button extends DrawableElement {
        Coordinate size;
        String label;
        Runnable action;
        int colour;

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
}
