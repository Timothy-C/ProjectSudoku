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

    /**
     * Gets the singleton instance of this GameState
     *
     * @return the instance of this GameState
     */
    public static GameState getInstance() {
        if (instance == null) instance = new StateGame(GameEngine.getInstance().parent);
        return instance;
    }

    @Override
    public void start() {
        digits = new DigitBoard(parent);
        board = new SudokuBoard(parent);
        board.digitBoard = digits;

        board.setPosition(60, 60);
        digits.setPosition(500 - 10, 60 - 4);
    }

    @Override
    public void end() {

    }

    @Override
    public void update() {
        digits.update();
        board.update();//Should pass selected number in here
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