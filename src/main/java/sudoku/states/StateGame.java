package main.java.sudoku.states;

import main.java.sudoku.DigitBoard;
import main.java.sudoku.SudokuBoard;
import processing.core.PApplet;

public class StateGame extends GameState {//The actual state of the current game
    
    private static GameState instance;
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
    public void start() {//Declares stuff

        digits = new DigitBoard(parent);
        board = new SudokuBoard(parent);
        board.digitBoard = digits;

        board.setPosition(60, 60);
        digits.setPosition(500 + 20, 60);
    }

    @Override
    public void end() {

    }

    @Override
    public void update() {
        digits.update();
        board.update();//Should pass selected number in here
        if(board.solved)//Exits if the board is solved by changing the state
        {
            changeState(StateChow.getInstance());//FIX THIS!!!
        }
    /*    if (digits.selectedDigit == 7) {//Goes to main menu
            changeState(StateMain.getInstance());
        } else if (digits.selectedDigit == 8) {
            GameEngine.getInstance().exit();
        }*/
    }

    @Override
    public void draw() {
        board.draw();
        digits.draw();
    }
}
