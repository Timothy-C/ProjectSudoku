package main.java.sudoku.states;

import main.java.sudoku.Input;
import main.java.sudoku.SolarizedColours;
import processing.core.PApplet;

public class StateMain extends GameState {
    
    private static GameState instance;
    
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
        parent.rect(60, 60, 50, 50);
    }

    @Override
    public void end() {

    }

    @Override
    public void update() {
        if (Input.getMouseButton(Input.Button.LEFT, Input.Event.PRESS )) {
            if(parent.mouseX>60 && parent.mouseX<110 && parent.mouseY>60 && parent.mouseY<110) {
                SolarizedColours.lightTheme=false;
                changeState(StateGame.getInstance());//If clicked in whatever, change to light
            }
            else
            {
                SolarizedColours.lightTheme=true;
                changeState(StateGame.getInstance());
            }
        }
    }

    @Override
    public void draw() {
    }

}
