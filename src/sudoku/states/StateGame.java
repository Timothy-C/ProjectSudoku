package sudoku.states;

import processing.core.PApplet;
import sudoku.DigitBoard;
import sudoku.Input;
import sudoku.SudokuBoard;

public class StateGame extends GameState {

    SudokuBoard board;
    DigitBoard digits;
    
    public StateGame(PApplet parent) {
        super(parent);
    }

    @Override
    public void start() {
        board = new SudokuBoard(parent);
        digits = new DigitBoard(parent);

        board.setPosition(60, 60);
        digits.setPosition(500 - 10, 60 - 4);
    }

    @Override
    public void end() {

    }

    @Override
    public void update() {
        Input.updateInput();

        board.update();
        digits.update();
    }

    @Override
    public void draw() {
        board.draw();
        digits.draw();
    }
}
