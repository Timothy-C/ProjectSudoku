import processing.core.PApplet;
import sudoku.Input;
import sudoku.states.GameState;
import sudoku.states.StateGame;

public class MainClass extends PApplet {

    public GameState gameState;

    public static void main(String[] args) {
        PApplet.main("MainClass");
    }

    @Override
    public void settings() {
        size(900, 600);//Space on the right for the numbers
    }

    @Override
    public void setup() {
        gameState = new StateGame(this);
        Input.parent = this;
    
        gameState.start();
    }

    @Override
    public void draw() {
        Input.updateInput();
        gameState.update();
        gameState.draw();
    }
}
