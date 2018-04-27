package sudoku;

import java.util.HashSet;

public class SudokuCell {
	public int value = 0;
	Coordinate position;
	/**
	 * Contains all coordinates that interact with this cell.
	 */
	HashSet<Coordinate> neighbors;
	
	
	public SudokuCell(Coordinate position) {
		this.position = position;
		this.generateNeighbors();
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
		for (int i = (position.x / 3) * 3; i < (position.x / 3) * 3 + 3; i++) {
			for (int j = (position.y / 3) * 3; j < (position.y / 3) * 3 + 3; j++) {
				neighbors.add(new Coordinate(i, j));
			}
		}
		
		neighbors.remove(this.position);
	}
	
	@Override
	public String toString() {
		return value + "";
	}
}
