package main.java.sudoku.states;

import main.java.sudoku.util.Input;
import processing.core.PApplet;
import processing.core.PConstants;

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
        //add the text-file streaming to high scores here
    }

    @Override
    public void end() {
        //
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
        parent.textAlign(PConstants.CENTER,PConstants.CENTER);
        parent.text("YOU WON",450,250);
        parent.text("CLICK TO CONTINUE",450,350);

    }
}
