package sudoku;

import java.util.HashSet;

public class SudokuCell {
    public int value = 0;
    Coordinate position;

    boolean isClue = false;
    boolean[] notes = new boolean[9];
    Status status = Status.UNSELECTED;

    /**
     * Contains all coordinates that interact with this cell.
     */
    HashSet<Coordinate> neighbors = new HashSet<>();

    SudokuCell(Coordinate position) {
        this.position = position;
        this.generateNeighbors();
    }

    private void generateNeighbors() {
        // column
        for (int i = 0; i < 9; i++)
            neighbors.add(new Coordinate(i, position.y));

        // row
        for (int i = 0; i < 9; i++)
            neighbors.add(new Coordinate(position.x, i));

        // box
        for (int i = (position.x / 3) * 3; i < (position.x / 3) * 3 + 3; i++)
            for (int j = (position.y / 3) * 3; j < (position.y / 3) * 3 + 3; j++)
                neighbors.add(new Coordinate(i, j));

        neighbors.remove(this.position);
    }

    @Override
    public String toString() {
        return value + "";
    }

    enum Status {
        UNSELECTED, SELECTED, HIGHLIGHTED, CONFLICTED
    }
}
