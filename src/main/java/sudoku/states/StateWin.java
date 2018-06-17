package main.java.sudoku.states;

import main.java.sudoku.util.Input;
import main.java.sudoku.util.SolarizedColours;
import processing.core.PApplet;
import processing.core.PConstants;

import java.time.Duration;

public class StateWin extends GameState {
    
    private static GameState instance;
    public static Duration time;
    
    private StateWin(PApplet parent) {
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
    public static GameState getInstance(Duration timeIN) {
        if (instance == null) {
            instance = new StateWin(GameEngine.getInstance().parent);
        }
        time = timeIN;
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
        parent.fill(SolarizedColours.getColour(0));
        parent.textAlign(PConstants.CENTER,PConstants.CENTER);
        parent.text("YOU WON",450,250);
        parent.text("CLICK TO CONTINUE",450,350);
        parent.text(String.format("%2d:%02d.%03d",
                time.toMinutesPart(),
                time.toSecondsPart(),
                time.toMillisPart()), 450, 450);

    }
}
