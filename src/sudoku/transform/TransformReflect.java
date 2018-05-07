package sudoku.transform;

import sudoku.SudokuCell;

public class TransformReflect implements ITransformation {
    private Reflection reflection;

    public TransformReflect(Reflection reflection) {
        this.reflection = reflection;
    }

    @Override
    public void apply(SudokuCell[][] board) {
        switch (reflection) {
            case HORIZONTAL:
                for (int i = 0; i < board.length / 2; i++)
                    swap(board[i], board[board.length - i - 1]);
                break;
            case VERTICAL:
                for (SudokuCell[] row : board)
                    reverse(row);
                break;
        }
    }

    private void swap(SudokuCell[] a, SudokuCell[] b) {
        SudokuCell[] temp = new SudokuCell[a.length];
        System.arraycopy(a, 0, temp, 0, a.length);
        System.arraycopy(b, 0, a, 0, a.length);
        System.arraycopy(temp, 0, b, 0, a.length);
    }

    private void reverse(SudokuCell[] a) {
        for (int i = 0; i < a.length / 2; i++) {
            SudokuCell temp;
            temp = a[i];
            a[i] = a[a.length - i - 1];
            a[a.length - i - 1] = temp;
        }
    }

    public enum Reflection {
        HORIZONTAL, VERTICAL
    }
}
