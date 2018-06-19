package main.java.sudoku.states;

import main.java.sudoku.util.Button;
import main.java.sudoku.util.Coordinate;
import main.java.sudoku.util.SolarizedColours;
import processing.core.PApplet;
import processing.core.PConstants;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.Duration;

public class StateScore extends GameState {
    
    private static GameState instance;
    private Button quitButton;
    private Button clearButton;
    private long[] scores = new long[10];
    private String[] names = new String[10];
    
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
     * Saves the score data from scores.txt to the scores and names arrays
     */
    private void getScore() {
        try (BufferedReader br = new BufferedReader(parent.createReader("scores.txt"))) {
            String[] temp;
            for (int i = 0; i < 10; i++) {
                temp = br.readLine().split(" ");
                scores[i] = Long.parseLong(temp[0]);
                names[i] = temp[1];
            }
        } catch (FileNotFoundException ex) {
            System.out.println("Unable to open file");
        } catch (IOException ex) {
            System.out.println("Error reading file");
        }
    }
    
    /**
     * Replaces all scores with Long.MAX_VALUE and all names with "-----"
     */
    private void eraseScore() {
        try (PrintWriter pw = new PrintWriter(parent.createWriter("scores.txt"))) {
            for (int i = 0; i < 10; i++)
                pw.println(Long.MAX_VALUE + " -----");
        }
        StateWin.scorePosition = -1;
    }
    
    @Override
    public void start() {
        quitButton = new Button(parent,
                new Coordinate(815, 520), new Coordinate(80, 50), "Menu",//UJML colours
                () -> changeState(StateMain.getInstance())
        );
        clearButton = new Button(parent,
                new Coordinate(815, 30), new Coordinate(80, 50), "Clear",//UJML colours
                () -> {
                    eraseScore();
                    changeState(getInstance());
                }
        );
        
        getScore();
    }
    
    @Override
    public void end() {
    
    }
    
    @Override
    public void update() {
        quitButton.update();
        clearButton.update();
    }
    
    @Override
    public void draw() {
        parent.fill(SolarizedColours.getColour(2));
        parent.rect(0, 0, 900, 600);
        
        quitButton.draw();
        clearButton.draw();
    
    
        parent.textAlign(PConstants.CENTER);
    
        // header
        parent.textSize(40);
        parent.text("HIGHSCORES", parent.width / 2, 60);
    
        // subheaders
        parent.textSize(30);
        parent.textAlign(PConstants.RIGHT);
        parent.text("RANK", 250, 120);
        parent.text("TIME", 525, 120);
        parent.text("NAME", 700, 120);
    
        for (int i = 0, y = 160; i < 10; i++, y += 40) {
            // rank
            parent.fill(i == StateWin.scorePosition ? SolarizedColours.getSelect() : SolarizedColours.getText());
            parent.text((i + 1) + (i == 0 ? "st" : i == 1 ? "nd" : i == 2 ? "rd" : "th"), 250, y);
    
            // time
            Duration time = Duration.ofNanos(scores[i]);
            String text = scores[i] != Long.MAX_VALUE ?
                    String.format("%02d:%02d.%03d",
                            time.toMinutes(),
                            time.getSeconds() % 60,
                            time.toMillis() % 1000) :
                    "--:--.---";
            parent.text(text, 525, y);
    
            // name
            parent.text(names[i], 700, y);
        }
    
        parent.textAlign(PConstants.LEFT);
    }
}
