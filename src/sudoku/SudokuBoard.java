package sudoku;

import processing.core.PApplet;
import processing.core.PConstants;
import sudoku.transform.ITransformation;

import java.util.Arrays;
import java.util.Random;

public class SudokuBoard extends DrawableElement {
    public static final int sideLength = 30;
    public static final int spacing = 5;

    SudokuCell[][] board;

    // board is organized like this
    // board[x][y]
    // 0 x -
    // y
    // |

    public SudokuBoard(PApplet parent) {
        super(parent);

        this.board = new SudokuCell[9][9];
        for (int x = 0; x < board.length; x++) {
            for (int y = 0; y < board[x].length; y++) {
                board[x][y] = new SudokuCell(new Coordinate(x, y));
            }
        }
        while (!isValid()) {
            generate();
        }
    }

    public void generate() {
        Random rand = new Random();
        boolean repeated;

        // generate first row
        for (int i = 0; i < 9; i++) {
            // generate unique number
            do {
                repeated = false;
                // randomly generate some number from 1-9
                board[0][i].value = rand.nextInt(9) + 1;

                // check row for repeats
                for (int j = 0; j < i; j++) {
                    if (board[0][i].value == board[0][j].value) {
                        repeated = true;
                        break;
                    }
                }
            } while (repeated);
        }

        // perform row shifts
        // https://gamedev.stackexchange.com/a/138228
        for (int i = 1; i < 9; i++) {
            int shift = i % 3 == 0 ? 1 : 3;
            for (int j = 0; j < 9; j++) {
                board[i][j] = board[i - 1][(j + shift) % 9];
            }
        }
    }

    void printBoard() {
        for (SudokuCell[] row : board) {
            for (SudokuCell cell : row) {
                System.out.print(cell.value + " ");
            }
            System.out.println();
        }
    }

    private boolean isValid() {
        for (int i = 0; i < 9; i++) {

            int[] row = new int[9];
            int[] square = new int[9];
            int[] column = new int[9];

            for (int j = 0; j < 9; j++) {
                column[j] = board[i][j].value;
                row[j] = board[j][i].value;
                square[j] = board[(i / 3) * 3 + j / 3][i * 3 % 9 + j % 3].value;

            }
            if (!(validArray(column) && validArray(row) && validArray(square)))
                return false;
        }
        return true;
    }

    private boolean validArray(int[] check) {
        int i = 0;
        Arrays.sort(check);
        for (int number : check) {
            if (number != ++i)
                return false;
        }
        return true;
    }

    @Override
    public void draw() {
        parent.textFont(parent.createFont("Consolas", 30, true));

        parent.fill(100, 100, 100);
        parent.rectMode(PConstants.CORNER);
        parent.rect(this.position.x - spacing, this.position.y - spacing, (sideLength + spacing) * 9 + spacing, (sideLength + spacing) * 9 + spacing);


        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                int cellX = this.position.x + x * (sideLength + spacing);
                int cellY = this.position.y + y * (sideLength + spacing);
                // cell rectangle
                parent.fill(255);
                parent.rect(cellX, cellY, sideLength, sideLength);
                // cell number
                parent.fill(127, 127, 127);
                parent.text(board[x][y].value, cellX + sideLength / 4, cellY + sideLength - (sideLength / 4));
            }
        }

    }

    public SudokuBoard transformBoard(ITransformation transformation) {
        //printBoard();
        //System.out.println(isValid() ? "valid" : "invalid");

        transformation.apply(board);

        //System.out.println();
        //printBoard();
        //System.out.println(isValid() ? "valid" : "invalid");
        return this;
    }
}
