import processing.core.PApplet;
import sudoku.SudokuBoard;

public class MainClass extends PApplet {

    SudokuBoard board;

    public static void main(String[] args) {
        PApplet.main("MainClass");
    }

    @Override
    public void settings() {
        size(600, 400);//Space on the right for the numbers
    }

    @Override
    public void setup() {
        board = new SudokuBoard(this);
        board.setPosition(60, 60);
    }

    @Override
    public void draw() {
        board.draw();
    }
}
