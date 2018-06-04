import processing.core.PApplet;
import sudoku.DigitBoard;
import sudoku.SudokuBoard;

public class MainClass extends PApplet {

    SudokuBoard board;
    DigitBoard digits;

    public static void main(String[] args) {
        PApplet.main("MainClass");
    }

    @Override
    public void settings() {
        size(900, 600);//Space on the right for the numbers
    }

    @Override
    public void setup() {
        board = new SudokuBoard(this);
        digits = new DigitBoard(this);
        
        board.setPosition(60, 60);
        digits.setPosition(500 - 10, 60 - 4);
    }

    @Override
    public void draw() {
        board.draw();
        digits.draw();

        System.out.println(mouseButton);
    }
}
