package main.java.sudoku.states;
import java.io.*;

import main.java.sudoku.util.Button;
import main.java.sudoku.util.Coordinate;
import main.java.sudoku.util.Input;
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
        if (Input.getMouseButton(Input.Button.LEFT, Input.Event.PRESS)) {//add quit button or just click to continue?
            changeState(StateMain.getInstance());
        }
        quitButton.update();
    }

    @Override
    public void draw() {
        //Fileinputstream
        String fileName = "instruct.txt";
//
//        // This will reference one line at a time
//        String line=null;
//        quitButton.draw();
//        try {
//            // FileReader reads text files in the default encoding.
//            FileReader fileReader = new FileReader("");
//
//            // wraps filereader in bufferedreader
//            BufferedReader bufferedReader = new BufferedReader(fileReader);
//
//            while((line = bufferedReader.readLine()) != null) {
//                System.out.println(line);
//            }
//
//            bufferedReader.close();//closes file
//        }
//        catch(FileNotFoundException ex) {
//            //System.out.println("Unable to open file '" + fileName + "'");
//            System.out.println(System.getProperty("user.dir"));
//        }
//        catch(IOException ex) {
//            System.out.println("Error reading file '"  + "'");
//            // Or we could just do this:
//            // ex.printStackTrace();
//        }


        /*BufferedReader br = new BufferedReader(getClass().getResourceAsStream("/instruct.txt"));
        int x=0;int y=0;
        String st;
        parent.fill(0xFF00FF00);
        while ((st = br.()) != false){
            parent.text(st,x,y);
            y+=50;
        }*/

        //put the instructions here by using textfile stremaing.

        File file = new File(fileName);
        System.out.println(file.getPath());
        System.out.println(file.exists());
    }
}
