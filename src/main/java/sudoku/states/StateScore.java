package main.java.sudoku.states;

import main.java.sudoku.util.Button;
import main.java.sudoku.util.Coordinate;
import main.java.sudoku.util.SolarizedColours;
import processing.core.PApplet;
import processing.core.PConstants;

import java.io.*;
import java.util.ArrayList;

public class StateScore extends GameState {

    private static GameState instance;
    private Button quitButton;
    private int[] inmin = new int[10];
    private int[] insec = new int[10];
    private int[] inmil = new int[10];

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

    private void getscore() {//Gets the scores from text file
        try {
            int counter;
            File file = new File(System.getProperty("user.dir") + "\\src\\main\\resources\\text\\scores.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String temp;
            String acc = "";
            // list 10 scores
            for (int i = 0; i < 10; i++) {
                temp = br.readLine();
                counter = 0;
                while (temp.charAt(counter) != (' ')) {
                    acc = acc.concat(Character.toString(temp.charAt(counter)));
                    counter++;
                }
                inmin[i] = Integer.parseInt(acc);
                acc = "";
                counter++;
                while (temp.charAt(counter) != (' ')) {
                    acc = acc.concat(Character.toString(temp.charAt(counter)));
                    counter++;
                }
                insec[i] = Integer.parseInt(acc);
                acc = "";
                counter++;
                while (counter < temp.length() && temp.charAt(counter) != (' ')) {
                    acc = acc.concat(Character.toString(temp.charAt(counter)));
                    counter++;
                }
                inmil[i] = Integer.parseInt(acc);
                acc = "";
            }
            br.close();
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
        getscore();
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

        int y = 50;
        for (int i=0;i<10;i++) {
            parent.textAlign(PConstants.RIGHT);
            parent.text(inmin[i]+" : ", 75, y);
            if(insec[i]<10) {
                parent.text("0"+insec[i] + " : ", 125, y);
            }
            else
            {
                parent.text(insec[i] + " : ", 125, y);
            }
            if(inmil[i]>99) {
                parent.text(inmil[i], 155, y);
            }
            else if(inmil[i]>9)
            {
                parent.text("0"+inmil[i], 155, y);
            }
            else
            {
                parent.text("00"+inmil[i], 155, y);
            }
            parent.textAlign(PConstants.LEFT);
            y += 20;
        }
    }
}
