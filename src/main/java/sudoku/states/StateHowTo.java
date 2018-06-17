package main.java.sudoku.states;
import java.io.*;

import main.java.sudoku.util.Button;
import main.java.sudoku.util.Coordinate;
import main.java.sudoku.util.Input;
import main.java.sudoku.util.SolarizedColours;
import processing.core.PApplet;

public class StateHowTo extends GameState {

    private static GameState instance;
    private StateHowTo(PApplet parent) {
        super(parent);
    }
    private Button quitButton;

    /**
     * Gets the singleton instance of this GameState
     *
     * @return the instance of this GameState
     */
    public static GameState getInstance() {
        if (instance == null) {
            instance = new StateHowTo(GameEngine.getInstance().parent);
        }
        return instance;
    }

    @Override
    public void start() {
        quitButton = new Button(parent,
                new Coordinate(815, 520), new Coordinate(80, 50),
                0xFFFF0000 , "Quit",//UJML colour codes
                () -> changeState(StateMain.getInstance())
        );
    }

    @Override
    public void end() {
        //
    }

    @Override
    public void update() {
        //if (Input.getMouseButton(Input.Button.LEFT, Input.Event.PRESS)) {//add quit button or just click to continue?
         //   changeState(StateMain.getInstance());
        //}
        quitButton.update();
    }

    @Override
    public void draw() {
        //Fileinputstream

      //  String fileName = "instruct.txt";
       // String text;

        //put the instructions here by using textfile stremaing.
        parent.fill(SolarizedColours.getColour(2));
        parent.rect(0,0,900,600);
        try{
            File file = new File(System.getProperty("user.dir")+"\\src\\main\\java\\sudoku\\states\\instruct.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String st;
            int y=50;
            parent.textSize(12);
            parent.fill(SolarizedColours.getText());
            while ((st = br.readLine()) != null) {
                //System.out.println(st);
                parent.text(st,50,y);
                y+=20;
            }
        }
        catch(FileNotFoundException ex) {
            System.out.println("Unable to open file");
        }
        catch(IOException ex) {
            System.out.println("error with file");
        }
        quitButton.draw();
        /*
        //Just some test code
        try {
            File myFile = new File(System.getProperty("user.dir")+"\\src\\main\\java\\sudoku\\states\\instruct.txt");

            System.out.println("Attempting to read from file in: " + myFile.getCanonicalPath());
        }
        catch(FileNotFoundException ex) {
            System.out.println("Unable to open file");
        }
        catch(IOException ex) {
            System.out.println("error with file");
        }*/
        /*
        //This test code deals with the proper absolute path for the instructions
        File file = new File(System.getProperty("user.dir")+"\\src\\main\\java\\sudoku\\states\\instruct.txt");

        System.out.println(file.getPath());

        System.out.println(file.exists());
        */
    }
}
