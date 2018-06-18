package main.java.sudoku.states;

import main.java.sudoku.util.Button;
import main.java.sudoku.util.Coordinate;
import processing.core.PApplet;

import java.io.*;
import java.util.ArrayList;

public class StateScore extends GameState {

    private static GameState instance;
    private ArrayList<String> in;
    private Button quitButton;

    private StateScore(PApplet parent) {
        super(parent);
        in = new ArrayList<>();

        // read the score file to update
        try {
            // open a file stream to the file of interest
            File file = new File(System.getProperty("user.dir") + "\\src\\main\\java\\sudoku\\states\\scores.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            // while there are lines to read, add lines to the list
            while ((line = br.readLine()) != null)
                in.add(line);

        } catch (FileNotFoundException ex) {
            System.out.println("Unable to open file");
        } catch (IOException ex) {
            System.out.println("Error reading file");
        }
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

    @Override
    public void start() {
        quitButton = new Button(parent,
            new Coordinate(815, 520), new Coordinate(80, 50), "Menu",//UJML colours
            () -> changeState(StateMain.getInstance())
        );
    }

    @Override
    public void end() {

    }

    @Override
    public void update() {

    }

    @Override
    public void draw() {

    }
}
