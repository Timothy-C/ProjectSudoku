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
        return (int) (getElapsedTime() / 1000000000);
    }

    public int getElapsedTimeMilli() {
        return (int) (getElapsedTime() / 1000000);
    }

    public Duration getDuration() {
        return Duration.ofNanos(getElapsedTime());
    }

}
