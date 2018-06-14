package main.java.sudoku.states;

import main.java.sudoku.Input;
import processing.core.PApplet;

public class StateChow extends GameState {
    
    private static GameState instance;
    
    private StateChow(PApplet parent) {
        super(parent);
    }

    /*
         ---  |   |   --  \          /
        /     |___|  /  \  \        /
        \     |   |  \  /   \  /\  /
         ---  |   |   --     \/  \/
    */

    /**
     * Gets the singleton instance of this GameState
     *
     * @return the instance of this GameState
     */
    public static GameState getInstance() {
        if (instance == null) {
            instance = new StateChow(GameEngine.getInstance().parent);
        }
        return instance;
    }

    @Override
    public void start() {
    
    }

    @Override
    public void end() {

    }

    @Override
    public void update() {
        if (Input.getMouseButton(Input.Button.LEFT, Input.Event.PRESS)) {
            changeState(StateMain.getInstance());
        }
    }

    @Override
    public void draw() {
        parent.fill(0xFF00FF00);
        parent.text("YOU WON",450,300);
    }
}
