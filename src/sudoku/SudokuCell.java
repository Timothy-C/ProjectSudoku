package sudoku;

import java.util.HashSet;

public class SudokuCell {
    public int value = 0;
    Coordinate position;

    boolean unknown = false;//This is true if the cell is empty, not empty if the cell is filled
    boolean[] notes = new boolean[9];//Notes for the cell
    Status status = Status.GIVEN;

    /**
     * Contains all coordinates that interact with this cell.
     */
    HashSet<Coordinate> neighbors = new HashSet<>();

    SudokuCell(Coordinate position) {
        this.position = position;
        this.generateNeighbors();
    }

    SudokuCell(int x, int y, int value) {
        this.position = new Coordinate(x, y);
        this.value = value;
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
        return value != 0 ? value + "" : "";
    }

    enum Status {
        UNSELECTED, SELECTED, HIGHLIGHTED, CONFLICTED, GIVEN//The "GIVEN" status makes it so that the player cannot mess with the given cells
    }
}
