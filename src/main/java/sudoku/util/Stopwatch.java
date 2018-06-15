package main.java.sudoku.util;

public class Stopwatch {
    
    /**
     * The time the Stopwatch started in nanoseconds
     */
    private long startTime = 0;
    
    /**
     * The time the Stopwatch stopped in nanoseconds
     */
    private long stopTime = 0;
    
    private boolean running = false;
    
    public void start() {
        this.startTime = System.nanoTime();
        running = true;
    }
    
    public void stop() {
        this.stopTime = System.nanoTime();
        running = false;
    }
    
    public long getElapsedTime() {
        return (running ? System.nanoTime() : stopTime) - startTime;
    }
    
    public int getElapsedTimeSeconds() {
        long temp= getElapsedTime() / 1000000000;
        int counter=0;
        while (temp>0)
        {
            counter+=1;
            temp-=1;
        }
        return counter;
    }
    
}
