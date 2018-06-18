package main.java.sudoku.states;

import main.java.sudoku.util.Button;
import main.java.sudoku.util.Coordinate;
import main.java.sudoku.util.Input;
import main.java.sudoku.util.SolarizedColours;
import processing.core.PApplet;
import processing.core.PImage;

import java.util.ArrayList;

public class StateMain extends GameState {
    
    private static GameState instance;
    private ArrayList<Button> buttons;
    PImage logo;
    
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

        int x = 650;
        int y = 100;
        
        buttons.add(new Button(
                parent,
                new Coordinate(x, y += 60), new Coordinate(160, 50), "Theme",
                () -> {
                    SolarizedColours.lightTheme = !SolarizedColours.lightTheme;
                    changeState(getInstance());
                }
        ));
        
        buttons.add(new Button(
                parent,
                new Coordinate(x, y += 60), new Coordinate(160, 50), "Start",
                () -> changeState(StateGame.getInstance())
        ));
        
        buttons.add(new Button(
                parent,
                new Coordinate(x, y += 60), new Coordinate(160, 50), "About",
                () -> changeState(StateInstruction.getInstance())
        ));
        
        buttons.add(new Button(
                parent,
                new Coordinate(x, y += 60), new Coordinate(160, 50), "Scores",
                () -> changeState(StateScore.getInstance())
        ));
        
        buttons.add(new Button(parent,
                new Coordinate(x, y += 60), new Coordinate(160, 50), "Quit",//UJML colours
                () -> GameEngine.getInstance().exit()
        ));


        logo = parent.loadImage(System.getProperty("user.dir") + "\\src\\main\\resources\\img\\" + (SolarizedColours.lightTheme ? "LogoLight.png" : "LogoDark.png"));
        logo.resize(logo.width / 2, logo.height / 2);
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
        parent.image(logo, 70, parent.height / 2 - logo.height / 2);
        //System.out.println(Input.getMousePosition());
        for (Button button : buttons) {
            button.draw();
        }
    }
    
}
