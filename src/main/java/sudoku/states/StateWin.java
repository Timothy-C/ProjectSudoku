package main.java.sudoku.states;

import main.java.sudoku.util.Input;
import main.java.sudoku.util.SolarizedColours;
import processing.core.PApplet;
import processing.core.PConstants;

import java.io.*;
import java.time.Duration;
import java.util.Arrays;

public class StateWin extends GameState {
    
    private static GameState instance;
    private static Duration time;
    
    private StateWin(PApplet parent) {
        super(parent);
    }

    /*
         ---  |   |   --  \          /
        /     |___|  /  \  \        /
        \     |   |  \  /   \  /\  /
         ---  |   |   --     \/  \/
    */
    
    /**
     * Gets the singleton instance of this GameState
     *
     * @return the instance of this GameState
     */
    public static GameState getInstance(Duration timeIN) {
        if (instance == null) {
            instance = new StateWin(GameEngine.getInstance().parent);
        }
        time = timeIN;
        return instance;
    }
    
    @Override
    public void start() {
        //add the text-file streaming to high scores here
        addScore(time);
    }
    
    @Override
    public void end() {
        //
    }
    
    @Override
    public void update() {
        if (Input.getMouseButton(Input.Button.LEFT, Input.Event.PRESS)) {
            changeState(StateMain.getInstance());
        }
    }
    
    /**
     * Adds the new score to the list of top scores, sorts, and rewrites the top 10 scores.
     *
     * @param score next score to add
     */
    private void addScore(Duration score) {
        // open a BufferedReader to the scores file using try-with-resources
        String path = System.getProperty("user.dir") + "\\src\\main\\resources\\text\\scores.txt";
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            /* read elements */
            long[] scores = new long[11];
            String temp;
            for (int i = 0; i < 10; i++) {
                temp = br.readLine();
                System.out.println(temp);
                scores[i] = Long.parseLong(temp);
            }
            
            scores[10] = score.toNanos();
            
            Arrays.sort(scores);
            
            // open PrintWriter using try-with-resources because opening it automatically deletes all data
            try (PrintWriter pw = new PrintWriter(new FileWriter(path))) {
                /* write elements */
                for (int i = 0; i < 10; i++)
                    pw.println(scores[i]);
            }
        } catch (FileNotFoundException ex) {
            System.out.println("Unable to open file");
        } catch (IOException ex) {
            System.out.println("Error reading file");
        }
    }
    
    @Override
    public void draw() {
        parent.fill(SolarizedColours.getText());
        parent.textAlign(PConstants.CENTER, PConstants.CENTER);
        parent.textSize(50);
        parent.text("YOU WON", 450, 150);
        parent.text("CLICK TO CONTINUE", 450, 250);
        parent.text(String.format("%2d:%02d.%03d",
                time.toMinutesPart(),
                time.toSecondsPart(),
                time.toMillisPart()), 450, 350);
        
        
    }
}
