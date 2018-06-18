package main.java.sudoku.states;

import main.java.sudoku.util.Button;
import main.java.sudoku.util.Coordinate;
import main.java.sudoku.util.SolarizedColours;
import processing.core.PApplet;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;

public class StateScore extends GameState {
    
    private static GameState instance;
    private Button quitButton;
    private long[] scores = new long[10];
    
    private StateScore(PApplet parent) {
        super(parent);
    
    }
    
    /**
     * Gets the singleton instance of this GameState
     *
     * @return the instance of this GameState
     */
    public static GameState getInstance() {
        if (instance == null) {
            instance = new StateScore(GameEngine.getInstance().parent);
        }
        return instance;
    
    }
    
    /**
     * Saves the score data from scores.txt to the scores array
     */
    private void getScore() {
        String path = System.getProperty("user.dir") + "\\src\\main\\resources\\text\\scores.txt";
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            for (int i = 0; i < 10; i++)
                scores[i] = Long.parseLong(br.readLine());
            
        } catch (FileNotFoundException ex) {
            System.out.println("Unable to open file");
        } catch (IOException ex) {
            System.out.println("Error reading file");
        }
    }
    
    @Override
    public void start() {
        quitButton = new Button(parent,
                new Coordinate(815, 520), new Coordinate(80, 50), "Menu",//UJML colours
                () -> changeState(StateMain.getInstance())
        );
        getScore();
    }
    
    @Override
    public void end() {
    
    }
    
    @Override
    public void update() {
        quitButton.update();
    }
    
    @Override
    public void draw() {
        parent.fill(SolarizedColours.getColour(2));
        parent.rect(0, 0, 900, 600);
        
        quitButton.draw();
        
        //put the instructions here by using text file streaming
        parent.fill(SolarizedColours.getText());
        parent.textSize(20);
        
        for (int i = 0, y = 50; i < 10; i++, y += 20) {
            Duration time = Duration.ofNanos(scores[i]);
            parent.text(String.format("%02d:%02d.%03d", time.toMinutesPart(), time.toSecondsPart(), time.toMillisPart()), 75, y);
        }
    }
}
