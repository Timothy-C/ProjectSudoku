public class SudokuBoard {
	SudokuCell[][] board;
	
	SudokuBoard() {
		board = new SudokuCell[9][9];
		for (int x = 0; x < board.length; x++) {
			for (int y = 0; y < board[x].length; y++) {
				board[x][y] = new SudokuCell(new Coordinate(x, y));
			}
		}
		printBoard();
	}
	
	void printBoard() {
		for (SudokuCell[] row : board) {
			for (SudokuCell cell : row) {
				System.out.print(cell.value + " ");
			}
			System.out.println();
		}
	}
}
