package main.java.sudoku.states;

import main.java.sudoku.util.Button;
import main.java.sudoku.util.Coordinate;
import main.java.sudoku.util.SolarizedColours;
import processing.core.PApplet;

import java.util.ArrayList;

public class StateMain extends GameState {
    
    private static GameState instance;
    private ArrayList<Button> buttons;
    private Button themeButton;
    private Button startButton;
    private Button quitButton;
    private Button instructButton;
    
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
        buttons = new ArrayList<>();
        
        int y = 60;
        
        buttons.add(new Button(
                parent,
                new Coordinate(550, y += 60), new Coordinate(160, 50), "Theme",
                () -> {
                    SolarizedColours.lightTheme = !SolarizedColours.lightTheme;
                    changeState(getInstance());
                }
        ));
        
        buttons.add(new Button(
                parent,
                new Coordinate(550, y += 60), new Coordinate(160, 50), "Start",
                () -> changeState(StateGame.getInstance())
        ));
        
        buttons.add(new Button(
                parent,
                new Coordinate(550, y += 60), new Coordinate(160, 50), "About",
                () -> changeState(StateAbout.getInstance())
        ));
        
        buttons.add(new Button(parent,
                new Coordinate(550, y += 60), new Coordinate(160, 50), "Instructions",
                () -> changeState(StateInstruction.getInstance())
        ));
        
        buttons.add(new Button(
                parent,
                new Coordinate(550, y += 60), new Coordinate(160, 50), "Scores",
                () -> changeState(StateScore.getInstance())
        ));
        
        buttons.add(new Button(parent,
                new Coordinate(550, y += 60), new Coordinate(160, 50), "Quit",//UJML colours
                () -> GameEngine.getInstance().exit()
        ));
    }
    
    @Override
    public void end() {
    
    }
    
    @Override
    public void update() {
        for (Button button : buttons) {
            button.update();
        }
    }
    
    @Override
    public void draw() {
        for (Button button : buttons) {
            button.draw();
        }
    }
    
}
