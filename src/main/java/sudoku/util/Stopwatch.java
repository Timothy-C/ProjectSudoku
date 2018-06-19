package main.java.sudoku.util;

import java.time.Duration;

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
    
    /**
     * Start the stopwatch.
     */
    public void start() {
        this.startTime = System.nanoTime();
        running = true;
    }
    
    /**
     * Stop the stopwatch.
     */
    public void stop() {
        this.stopTime = System.nanoTime();
        running = false;
    }
    
    /**
     * Returns either the amount of elapsed time since start or amount of time the stopwatch has covered.
     *
     * @return elapsed time in nanoseconds
     */
    public long getElapsedTime() {
        return (running ? System.nanoTime() : stopTime) - startTime;
    }

    public int getElapsedTimeSeconds() {
        return (int) (getElapsedTime() / 1000000000);
    }

    public int getElapsedTimeMilli() {
        return (int) (getElapsedTime() / 1000000);
    }
    
    /**
     * Returns either the amount of elapsed time since start or amount of time the stopwatch has covered.
     * @return elapsed time as Duration
     */
    public Duration getDuration() {
        return Duration.ofNanos(getElapsedTime());
    }

}
