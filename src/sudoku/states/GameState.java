package sudoku.states;

import processing.core.PApplet;

public abstract class GameState {
    PApplet parent;

    static GameState instance;

    GameState(PApplet parent) {
        this.parent = parent;
    }

    // Run once
    public abstract void start();

    public abstract void end();

    // Run every frame
    public abstract void update();

    public abstract void draw();

    void changeState(GameState state) {
        GameEngine.getInstance().changeState(state);
    }
}
