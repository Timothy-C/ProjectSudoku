package main.java.sudoku.states;

import processing.core.PApplet;

public class StateAbout extends GameState {
    
    private static GameState instance;
    
    private StateAbout(PApplet parent) {
        super(parent);
    }
    
    /**
     * Gets the singleton instance of this GameState
     *
     * @return the instance of this GameState
     */
    public static GameState getInstance() {
        if (instance == null) {
            instance = new StateAbout(GameEngine.getInstance().parent);
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
    
    }
    
    @Override
    public void draw() {
    
    }
}
