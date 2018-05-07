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
            for (SudokuCell cell : row)
                if (cell.value == n1)
                    cell.value = -1;
                else if (cell.value == n2)
                    cell.value = -2;

            for (SudokuCell cell : row)
                if (cell.value == -1)
                    cell.value = n2;
                else if (cell.value == -2)
                    cell.value = n1;
        }
    }
}
