package main.java.sudoku.states;

import main.java.sudoku.util.Input;
import main.java.sudoku.util.SolarizedColours;
import processing.core.PApplet;
import processing.core.PConstants;

import java.io.*;
import java.time.Duration;

public class StateWin extends GameState {

    private static GameState instance;
    public static Duration time;

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
        scoresort(time.toMinutesPart(), time.toSecondsPart(), time.toMillisPart());
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
    private int timing(int a,int b, int c)
    {
        return 60000 * a+ 1000 * b+ c;
    }

    private void scoresort(int min, int sec, int mil) {
        int[] inmin = new int[10];
        int[] insec = new int[10];
        int[] inmil = new int[10];
        String temp;

        int counter=0;
        // open a file stream to the file of interest
        try {
            File file = new File(System.getProperty("user.dir") + "\\src\\main\\java\\sudoku\\states\\scores.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String acc="";
            // list 10 scores
            for (int i = 0; i < 10; i++) {
                temp=br.readLine();
                counter=0;
                while(temp.charAt(counter)!=(' '))
                {
                    acc=acc.concat(Character.toString(temp.charAt(counter)));
                    counter++;
                }
                inmin[i] = Integer.parseInt(acc);
                acc="";
                counter++;
                while(temp.charAt(counter)!=(' '))
                {
                    acc=acc.concat(Character.toString(temp.charAt(counter)));
                    counter++;
                }
                insec[i] = Integer.parseInt(acc);
                acc="";
                counter++;
                while(counter < temp.length() &&temp.charAt(counter)!=(' '))
                {
                    acc=acc.concat(Character.toString(temp.charAt(counter)));
                    counter++;
                }
                inmil[i] = Integer.parseInt(acc);
                acc="";
                System.out.print(i +" min:"+ inmin[i]+" ");
                System.out.print(i +" sec:"+ insec[i]+" ");
                System.out.println(i +" mil:"+ inmil[i]);
            }
            br.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Unable to open file");
        } catch (IOException ex) {
            System.out.println("Error reading file");
        }
        try {
          //  System.out.println(min+" "+sec+" "+mil);
            FileWriter fileWriter = new FileWriter(System.getProperty("user.dir") + "\\src\\main\\java\\sudoku\\states\\scores.txt");
            PrintWriter printWriter = new PrintWriter(fileWriter);
            int result = timing(min,sec,mil);/*
            System.out.println("writing");
            System.out.println(min+":"+sec+ "."+mil);
            System.out.println("converted");
            System.out.println(result);*/
            if (result < timing(inmin[9],insec[9],inmil[9])) {
                for (int i = 0; i <10; i++) {
                    if (result > timing(inmin[i],insec[i],inmil[i]))//Keep original
                    {
                        printWriter.println(inmin[i]+" "+insec[i]+" "+inmil[i]);
                    } else {
                        printWriter.println(min+" "+sec+" "+mil);
                        for (int j = i+1; j <10 ; j++) {
                            printWriter.println(inmin[j]+" "+insec[j]+" "+inmil[j]);
                        }
                        break;
                    }

//                    printWriter.print("Better");
                }
            } else {
                for (int j = 0; j < 10; j++) {
                    printWriter.println(inmin[j]+" "+insec[j]+" "+inmil[j]);
                }
            }
            printWriter.close();
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
