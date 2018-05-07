package sudoku.transform;

import sudoku.SudokuCell;

public interface ITransformation {
    void apply(SudokuCell[][] board);
}
