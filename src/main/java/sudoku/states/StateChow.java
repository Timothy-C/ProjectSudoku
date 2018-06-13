package main.java.sudoku.states;

import processing.core.PApplet;

public class StateChow extends GameState {

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
        if (instance == null) instance = new StateChow(GameEngine.getInstance().parent);
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

    }

    @Override
    public void draw() {
        parent.fill(50f);
        parent.text("YOU WON",450,300);
    }
}
