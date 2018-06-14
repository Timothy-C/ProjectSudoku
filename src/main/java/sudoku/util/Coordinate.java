package main.java.sudoku.util;

public class Coordinate implements Comparable<Coordinate> {

    public int x;
    public int y;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Coordinate)) return false;
        Coordinate other = ((Coordinate) obj);
        return other.x == this.x && other.y == this.y;
    }

    @Override
    public int hashCode() {
        return x * 10 + y;
    }

    @Override
    public int compareTo(Coordinate o) {
        if (this.equals(o)) return 0;
        if (this.x != o.x) {
            if (this.x < o.x) return -1;
            else return 1;
        }
        else {
            if (this.y < o.y) return -1;
            else return 1;
        }
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}
