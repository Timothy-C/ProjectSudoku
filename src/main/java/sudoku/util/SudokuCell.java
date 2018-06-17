package main.java.sudoku.util;

import java.util.HashSet;
import java.util.function.IntSupplier;

public class SudokuCell {
    public int value = 0;
    public Coordinate position;
    
    public boolean unknown = false;//This is true if the cell is empty, not empty if the cell is filled
    public boolean[] notes = new boolean[9];//Notes for the cell
    public CellType cellType = CellType.GIVEN;
    public CellStatus cellStatus = CellStatus.UNSELECTED;

    /**
     * Contains all coordinates that interact with this cell.
     */
    public HashSet<Coordinate> neighbors = new HashSet<>();
    
    public SudokuCell(Coordinate position) {
        this.position = position;
        this.generateNeighbors();
    }
    
    public SudokuCell(int x, int y, int value) {
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
    
    //The "GIVEN" cellType makes it so that the player cannot mess with the given cells
    public enum CellType {
        EMPTY(() -> SolarizedColours.getColour(2)),
        GIVEN(() -> SolarizedColours.getColour(3));
        
        public final IntSupplier supplier;
        
        CellType(IntSupplier supplier) {
            this.supplier = supplier;
        }
        
        CellType(int colour) {
            this.supplier = () -> colour;
        }
        
        public int getColour() {
            return supplier.getAsInt();
        }
    }
    
    public enum CellStatus {
        UNSELECTED(0x00FFFFFF),
        SELECTED(SolarizedColours::getSelect),
        HIGHLIGHTED(SolarizedColours::getText),
        CONFLICTED(SolarizedColours.magenta);
        
        public final IntSupplier supplier;
        
        CellStatus(IntSupplier supplier) {
            this.supplier = supplier;
        }
        
        CellStatus(int colour) {
            this.supplier = () -> colour;
        }
        
        public int getColour() {
            return supplier.getAsInt();
        }
    }
    
}
