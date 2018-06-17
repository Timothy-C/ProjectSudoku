package main.java.sudoku.states;

import processing.core.PApplet;

public class StateScore extends GameState {
    
    private static GameState instance;
    
    private StateScore(PApplet parent) {
        super(parent);
    }
    
    /**
     * Gets the singleton instance of this GameState
     *
     * @return the instance of this GameState
     */
    public static GameState getInstance() {
        if (instance == null) {
            instance = new StateScore(GameEngine.getInstance().parent);
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
