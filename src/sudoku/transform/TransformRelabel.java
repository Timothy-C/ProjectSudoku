package sudoku.transform;

import sudoku.SudokuCell;

public class TransformRelabel implements ITransformation {
	
	int n1, n2;
	
	public TransformRelabel(int toN, int fromN) {
		this.n1 = toN;
		this.n2 = fromN;
	}
	
	@Override
	public void apply(SudokuCell[][] board) {
		for (SudokuCell[] row : board) {
			for (int i = 0; i < row.length; i++)
				if (row[i].value == n1)
					row[i].value = -1;
				else if (row[i].value == n2)
					row[i].value = -2;
			for (int i = 0; i < row.length; i++)
				if (row[i].value == -1)
					row[i].value = n2;
				else if (row[i].value == -2)
					row[i].value = n1;
		}
	}
}
