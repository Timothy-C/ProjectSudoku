package main.java.sudoku;

import main.java.sudoku.states.GameEngine;
import main.java.sudoku.states.StateMain;
import processing.core.PApplet;

public class MainClass extends PApplet {
    
    private GameEngine gameEngine;
    
    public static void main(String[] args) {
        PApplet.main("main.java.sudoku.MainClass");
    }
    
    @Override
    public void settings() {
        size(900, 600);//Dimensions of the run window
    }
    
    @Override
    public void setup() {
        Input.parent = this;
        
        gameEngine = GameEngine.getInstance();
        gameEngine.parent = this;
        gameEngine.start();
        gameEngine.changeState(StateMain.getInstance());
    }
    
    @Override
    public void draw() {
        Input.updateInput();
        if (gameEngine.running) {
            gameEngine.update();
            gameEngine.draw();
        }
        else {
            gameEngine.end();
            exit();
        }
        //drawGrid();//This draws a small grid on the run window
    }

    private void drawGrid() {
        fill(10f);
        strokeWeight(1);
        int grid = 8;
        for (int i = 0; i < width; i += grid) {
            line(i, 0, i, height);
        }
        for (int i = 0; i < height; i += grid) {
            line(0, i, width, i);
        }
    }
}
