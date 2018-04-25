import java.util.HashSet;

public class SudokuCell {
    Coordinate position;
    int value = 0;

    /**
     * Contains all coordinates that interact with this cell.
     */
    HashSet<Coordinate> neighbors;


    public SudokuCell(Coordinate position) {
        this.position = position;
    }

    private void generateNeighbors() {
        neighbors = new HashSet<>();
        // column
        for (int i = 0; i < 9; i++) {
            neighbors.add(new Coordinate(i, position.y));
        }

        // row
        for (int i = 0; i < 9; i++) {
            neighbors.add(new Coordinate(position.x, i));
        }

        // box
        //for (int i = (position.x/3)*3; i < )
    }
}
