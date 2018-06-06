package sudoku.states;

import processing.core.PApplet;
import sudoku.DigitBoard;
import sudoku.SudokuBoard;

public class StateGame extends GameState {

    private static StateGame instance;
    private SudokuBoard board;
    private DigitBoard digits;

    private StateGame(PApplet parent) {
        super(parent);
    }

    public static StateGame getInstance() {
        if (instance == null) instance = new StateGame(GameEngine.getInstance().parent);
        return instance;
    }

    @Override
    public void start() {
        digits = new DigitBoard(parent);
        board = new SudokuBoard(parent, digits);

        board.setPosition(60, 60);
        digits.setPosition(500 - 10, 60 - 4);
    }

    @Override
    public void end() {

    }

    @Override
    public void update() {
        board.update();
        digits.update();

        if (digits.selectedDigit == 7) {
            changeState(StateMain.getInstance());
        } else if (digits.selectedDigit == 5) {
            GameEngine.getInstance().exit();
        }
    }

    @Override
    public void draw() {
        board.draw();
        digits.draw();
    }
}
