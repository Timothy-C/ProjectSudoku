package main.java.sudoku.transform;

import main.java.sudoku.util.SudokuCell;

public class TransformRotate implements ITransformation {
    private int rotations;
	
	public TransformRotate(int rotations) {
		this.rotations = rotations;
	}
	
	@Override
	public void apply(SudokuCell[][] board) {
		int[][] newBoard = new int[9][9];
		if (rotations % 4 == 0) return;
		for (int i = 0; i < 9; i++)
			for (int j = 0; j < 9; j++)
				switch (rotations % 4) {
					case 1:
						newBoard[i][j] = board[9 - j - 1][i].value;
						break;
					case 2:
						newBoard[j][9 - i - 1] = board[9 - j - 1][i].value;
						break;
					case 3:
						newBoard[i][j] = board[j][9 - i - 1].value;
						break;
				}
		
		for (int i = 0; i < 9; i++)
			for (int j = 0; j < 9; j++) {
				board[i][j].value = newBoard[i][j];
			}
	}
}
