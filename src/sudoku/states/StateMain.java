package sudoku.states;

import processing.core.PApplet;

public class StateMain extends GameState {
    
    public StateMain(PApplet parent) {
        super(parent);
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

    }

    @Override
    public void draw() {
        parent.rect(30, 30, 30, 30);
    }
}
