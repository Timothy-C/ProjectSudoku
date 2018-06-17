package main.java.sudoku.states;

import main.java.sudoku.util.Input;
import main.java.sudoku.util.SolarizedColours;
import processing.core.PApplet;
import processing.core.PConstants;

public class StateChow extends GameState {
    
    private static GameState instance;
    public static float time;
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
    public static GameState getInstance(int temp) {
        if (instance == null) {
            instance = new StateChow(GameEngine.getInstance().parent);
        }
        time=temp;
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
        parent.fill(SolarizedColours.getText());
        parent.textAlign(PConstants.CENTER,PConstants.CENTER);
        parent.textSize(50);
        parent.text("YOU WON",450,150);
        parent.text("CLICK TO CONTINUE",450,250);
        parent.text(time, 450, 350);

    }
}
