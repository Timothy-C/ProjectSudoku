package main.java.sudoku.states;

import main.java.sudoku.util.Input;
import main.java.sudoku.util.SolarizedColours;
import processing.core.PApplet;
import processing.core.PConstants;

import java.io.*;
import java.time.Duration;
import java.util.Arrays;

public class StateWin extends GameState {
    
    static int scorePosition = -1;
    private static GameState instance;
    private static Duration time;
    private long[] scores = new long[11];
    private String[] names = new String[10];
    private String name = "";
    private boolean nameSet = false;
    
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
        scorePosition = -1;
        name = "";
        nameSet = false;
    
        // read scores to scores[]
        // open a BufferedReader to the scores file using try-with-resources
        String path = System.getProperty("user.dir") + "\\src\\main\\resources\\text\\scores.txt";
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            /* read elements */
            String[] temp;
            for (int i = 0; i < 10; i++) {
                temp = br.readLine().split(" ");
                scores[i] = Long.parseLong(temp[0]);
                names[i] = temp[1];
            }
            scores[10] = time.toNanos();
            Arrays.sort(scores);
        } catch (FileNotFoundException ex) {
            System.out.println("Unable to open file");
        } catch (IOException ex) {
            System.out.println("Error reading file");
        }
    
        // if your score is in top 10 then allow initials
        if (scores[10] != time.toNanos()) {
            scorePosition = Arrays.binarySearch(scores, time.toNanos());
        }
    }
    
    @Override
    public void end() {
        addScore(time);
    }
    
    @Override
    public void update() {
        if (scorePosition == -1) {
            if (Input.getMouseButton(Input.Button.LEFT, Input.Event.PRESS)) {
                changeState(StateMain.getInstance());
            }
        } else {
            if (Input.getKeyEvent(Input.Event.PRESS)) {
                if (Input.keyIsHeld == 8 && name.length() > 0) {
                    name = name.substring(0, name.length() - 1);
                } else if (Input.keyIsHeld == 10) {
                    nameSet = true;
                } else if (name.length() < 5) {
                    char key = (char) Input.keyIsHeld;
                    if (Character.isLetterOrDigit(key)) {
                        name += Character.toUpperCase((char) Input.keyIsHeld);
                    }
                }
            }
            if (nameSet) {
                names[scorePosition] = name;
                changeState(StateScore.getInstance());
            }
        }
    }
    
    /**
     * Adds the new score to the list of top scores, sorts, and rewrites the top 10 scores.
     *
     * @param score next score to add
     */
    private void addScore(Duration score) {
        String path = System.getProperty("user.dir") + "\\src\\main\\resources\\text\\scores.txt";
        // open PrintWriter to scores file using try-with-resources
        try (PrintWriter pw = new PrintWriter(new FileWriter(path))) {
            /* write elements */
            for (int i = 0; i < 10; i++)
                pw.println(scores[i] + " " + names[i]);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void draw() {
        parent.fill(SolarizedColours.getText());
        parent.textAlign(PConstants.CENTER, PConstants.CENTER);
        parent.noStroke();
        parent.textSize(50);
    
        parent.text("You won!", 450, 150);
        parent.text(String.format("Time: %2d:%02d.%03d",
                time.toMinutesPart(),
                time.toSecondsPart(),
                time.toMillisPart()), 450, 250);
    
        if (scorePosition == -1) {
            parent.text("Click to continue", 450, 350);
        } else {
            parent.text("Enter your initials:", 450, 350);
            parent.textSize(30);
            parent.fill(SolarizedColours.getColour(2));
        
            parent.rect(0, 380, parent.width, 50);
            parent.fill(SolarizedColours.getText());
            parent.text(name, 450, 400);
        }
        
    }
}
