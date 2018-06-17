package main.java.sudoku.transform;

import main.java.sudoku.util.SudokuCell;

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
        for (int i = 0; i < a.length; i++) {
            int temp = a[i].value;
            a[i].value = b[i].value;
            b[i].value = temp;
        }
    }

    private void reverse(SudokuCell[] a) {
        for (int i = 0; i < a.length / 2; i++) {
            int temp = a[i].value;
            a[i].value = a[a.length - i - 1].value;
            a[a.length - i - 1].value = temp;
        }
    }

    public enum Reflection {
        HORIZONTAL, VERTICAL
    }
}
