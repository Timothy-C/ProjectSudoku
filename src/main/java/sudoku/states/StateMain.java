package main.java.sudoku.states;

import main.java.sudoku.util.Button;
import main.java.sudoku.util.Coordinate;
import main.java.sudoku.util.SolarizedColours;
import processing.core.PApplet;

public class StateMain extends GameState {

    private Button themeButton;
    private Button startButton;
    private Button quitButton;
    private Button instructButton;
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

        themeButton = new Button(
                parent,
                new Coordinate(100, 100), new Coordinate(85, 50),
                SolarizedColours.getColour(2), "Theme",
                () -> {
                    SolarizedColours.lightTheme = !SolarizedColours.lightTheme;
                    changeState(getInstance());
                }
        );
        startButton = new Button(
                parent,
                new Coordinate(50, 50), new Coordinate(80, 50),
                0xFFFF0000, "Start",
                () -> changeState(StateGame.getInstance())
        );
        quitButton = new Button(parent,
                new Coordinate(815, 520), new Coordinate(80, 50),
                0xFFFF0000 , "Quit",//UJML colours
                () -> GameEngine.getInstance().exit()
        );
        instructButton = new Button(parent,
                new Coordinate(200,200), new Coordinate (150,50),
                0xFF0000FF,"Instructions",
                () -> changeState(StateInstruction.getInstance())
                );
    }

    @Override
    public void end() {

    }

    @Override
    public void update() {
        themeButton.update();
        startButton.update();
        quitButton.update();
        instructButton.update();
    }

    @Override
    public void draw() {
        SolarizedColours.lightTheme = !SolarizedColours.lightTheme;
        themeButton.buttoncolour(SolarizedColours.getText());
        themeButton.draw();
        SolarizedColours.lightTheme = !SolarizedColours.lightTheme;
        startButton.draw();

        quitButton.draw();
        instructButton.draw();
    }

}
