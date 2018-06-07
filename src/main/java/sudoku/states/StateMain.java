package main.java.sudoku.states;

import main.java.sudoku.Input;
import processing.core.PApplet;

public class StateMain extends GameState {

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
        parent.rect(30, 30, 30, 30);
    }

    @Override
    public void end() {

    }

    @Override
    public void update() {
        if (Input.getMouseButton(Input.Button.LEFT, Input.Event.PRESS)) {
            changeState(StateGame.getInstance());
        }
    }

    @Override
    public void draw() {
        parent.rect(30, 30, 30, 30);
    }

}