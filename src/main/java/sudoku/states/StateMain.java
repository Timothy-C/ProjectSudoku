package main.java.sudoku.states;

import main.java.sudoku.util.Button;
import main.java.sudoku.util.Coordinate;
import main.java.sudoku.util.SolarizedColours;
import processing.core.PApplet;

public class StateMain extends GameState {

    public Button themeButton;
    public Button startButton;

    
    private static GameState instance;
    
    private StateMain(PApplet parent) {
        super(parent);
    }

    /**
     * Gets the singleton instance of this GameState
     *
     * @return the instance of this GameState
     */
    public static GameState getInstance() {
        if (instance == null) instance = new StateMain(GameEngine.getInstance().parent);
        return instance;
    }

    @Override
    public void start() {

        parent.fill(50f);
        //   parent.rect(30, 30, 30, 30);
        //parent.fill(50f);

        themeButton = new Button(
                parent,
                new Coordinate(100, 100), new Coordinate(50, 50),
                0xFF0000FF, "theme",
                () -> {
                    SolarizedColours.lightTheme = !SolarizedColours.lightTheme;
                    changeState(getInstance());
                }
        );
        startButton = new Button(
                parent,
                new Coordinate(60, 60), new Coordinate(50, 50),
                0xFFFF0000, "start",
                () -> changeState(StateGame.getInstance())
        );
    }

    @Override
    public void end() {

    }

    @Override
    public void update() {
        themeButton.update();
        startButton.update();
    }

    @Override
    public void draw() {
        themeButton.draw();
        startButton.draw();
    }

}
