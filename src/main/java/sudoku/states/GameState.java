package main.java.sudoku.states;

import processing.core.PApplet;

public abstract class GameState {//Parent state for all game states
    PApplet parent;
    public float time=0;
    GameState(PApplet parent) {
        this.parent = parent;
    }

    /**
     * Runs once at the beginning to set up default values and such when entering a GameState.
     */
    public abstract void start();

    /**
     * Runs once at the end to clean up objects and such when leaving of a GameState.
     */
    public abstract void end();

    /**
     * Runs every frame, run logic related code in here.
     */
    public abstract void update();

    /**
     * Runs every frame, run graphics and user related code in here.
     */
    public abstract void draw();

    /**
     * Tells the game engine to transition from this GameState to the given GameState.
     *
     * @param state the GameState the {@link GameEngine GameEngine} to transition to
     */
    void changeState(GameState state) {
        GameEngine.getInstance().changeState(state);
    }
}
