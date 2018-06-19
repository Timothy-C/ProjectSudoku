package main.java.sudoku;

import main.java.sudoku.states.GameEngine;
import main.java.sudoku.states.StateMain;
import main.java.sudoku.util.Input;
import processing.core.PApplet;
import processing.event.KeyEvent;
import processing.event.MouseEvent;

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
    
        textFont(createFont("Consolas", 30, true));
    }

    @Override
    public void draw() {
        Input.updateInput();
//        System.out.println(Input.getMousePosition());
        if (gameEngine.running) {
            gameEngine.update();
            gameEngine.draw();
        } else {
            gameEngine.end();
            exit();
        }

        //This draws a small grid on the run window
        //drawGrid(10);
    }

    private void drawGrid(int grid) {
        fill(5f);
        strokeWeight(1);

        for (int i = 0; i < width; i += grid) {
            line(i, 0, i, height);
        }
        for (int i = 0; i < height; i += grid) {
            line(0, i, width, i);
        }
    }

    @Override
    public void mouseWheel(MouseEvent event) {
        Input.setScroll(event.getCount());
    }
    
    @Override
    public void keyPressed(KeyEvent event) {
        System.out.println(event.getKeyCode());
        Input.setKey(event.getKey());
    }
}
