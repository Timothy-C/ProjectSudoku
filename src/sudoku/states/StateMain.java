package sudoku.states;

import processing.core.PApplet;
import sudoku.Input;

public class StateMain extends GameState {
    
    public StateMain(PApplet parent) {
        super(parent);
    }

    private static StateMain instance;

    public static StateMain getInstance() {
        if (instance == null) instance = new StateMain(GameEngine.getInstance().parent);
        return instance;
    }

    @Override
    public void start() {
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
