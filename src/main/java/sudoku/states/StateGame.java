package main.java.sudoku.states;

import main.java.sudoku.DigitBoard;
import main.java.sudoku.SudokuBoard;
import main.java.sudoku.util.Button;
import main.java.sudoku.util.Coordinate;
import main.java.sudoku.util.SolarizedColours;
import main.java.sudoku.util.Stopwatch;
import processing.core.PApplet;
import processing.core.PConstants;

import java.time.Duration;

public class StateGame extends GameState {//The actual state of the current game
    
    private static GameState instance;
    private SudokuBoard board;
    private DigitBoard digits;
    private Button quitButton;
    
    private Stopwatch stopwatch;

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

        quitButton = new Button(parent,
                new Coordinate(815, 520), new Coordinate(80, 50),
                0xFFFF0000 , "Quit",//UJML colour codes
                () -> changeState(StateMain.getInstance())
        );

        stopwatch = new Stopwatch();
        stopwatch.start();
    }

    @Override
    public void end() {
        System.out.println(stopwatch.getElapsedTimeSeconds());
        stopwatch.stop();
    }

    @Override
    public void update() {
        digits.update();
        board.update();//Should pass selected number in here
        quitButton.update();

        if (board.solved) {
            changeState(StateWin.getInstance(stopwatch.getDuration()));
        }
    }

    @Override
    public void draw() {
        parent.background(SolarizedColours.getColour(2));

        board.draw();
        digits.draw();
        quitButton.draw();
        parent.textAlign(PConstants.RIGHT);
        Duration duration = stopwatch.getDuration();
        parent.fill(SolarizedColours.getText());
        parent.text(String.format("%2d:%02d.%03d",
                duration.toMinutesPart(),
                duration.toSecondsPart(),
                duration.toMillisPart()), 720, 50);
        parent.textAlign(PConstants.LEFT);
    }
}
