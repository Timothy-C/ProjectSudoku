package main.java.sudoku.states;

import main.java.sudoku.util.Input;
import processing.core.PApplet;

public class StateHowTo extends GameState {

    private static GameState instance;

    private StateHowTo(PApplet parent) {
        super(parent);
    }



    /**
     * Gets the singleton instance of this GameState
     *
     * @return the instance of this GameState
     */
    public static GameState getInstance() {
        if (instance == null) {
            instance = new StateHowTo(GameEngine.getInstance().parent);
        }
        return instance;
    }

    @Override
    public void start() {

    }

    @Override
    public void end() {
        //
    }

    @Override
    public void update() {
        if (Input.getMouseButton(Input.Button.LEFT, Input.Event.PRESS)) {//add quit button or just click to continue?
            changeState(StateMain.getInstance());
        }
    }

    @Override
    public void draw() {
        //put the instructions here by using textfile stremaing.
    }
}
