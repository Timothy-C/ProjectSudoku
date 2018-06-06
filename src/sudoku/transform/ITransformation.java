package sudoku.transform;

import sudoku.SudokuCell;

public interface ITransformation {
    /**
     * Apply a transformation to the given board
     *
     * @param board board to be transformed
     */
    void apply(SudokuCell[][] board);
}
