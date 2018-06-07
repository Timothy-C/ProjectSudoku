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

    /**
     * Populates the {@link #neighbors neighbors} HashSet with the {@link Coordinate Coordinates} that can interfere with this cell.
     */
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
    
    //The "GIVEN" status makes it so that the player cannot mess with the given cells
    enum Status {
        UNSELECTED(SolarizedColours.getColour(2)), SELECTED(SolarizedColours.getColour(0)), HIGHLIGHTED(0xFFF0F000), CONFLICTED(0xFFFF0000), GIVEN(SolarizedColours.getColour(3));
        
        public final int colour;
        
        Status(int colour) {
            this.colour = colour;
        }
    }
}
