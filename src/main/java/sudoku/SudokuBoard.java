package main.java.sudoku;

import main.java.sudoku.transform.ITransformation;
import main.java.sudoku.transform.TransformReflect;
import main.java.sudoku.transform.TransformRotate;
import main.java.sudoku.util.*;
import processing.core.PApplet;
import processing.core.PConstants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class SudokuBoard extends DrawableElement {
    private static final int sideLength = 40;
    private static final int spacing = 8;
    private static final int combinedSpace = (sideLength + spacing);

    public DigitBoard digitBoard;
    public boolean solved = false;
    private SudokuCell[][] board;
    private SudokuCell selected;
    // board is organized like this
    // board[x][y]
    // 0 x
    // y
    // |

    public SudokuBoard(PApplet parent) {
        super(parent);
        this.board = new SudokuCell[9][9];
        selected = null;
        for (int x = 0; x < board.length; x++) {
            for (int y = 0; y < board[x].length; y++) {
                board[x][y] = new SudokuCell(new Coordinate(x, y));
            }
        }

        while (!isValid()) {
            generate();
        }
        removeNumbers();//Removes numbers
    }

    /**
     * Generates a valid Sudoku board arrangement using a row shift algorithm.
     *
     * @see <a href="URL#https://gamedev.stackexchange.com/a/138228">generation algorithm</a>
     */
    private void generate() {
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

        // perform row shifts(transformations)
        // https://gamedev.stackexchange.com/a/138228
        for (int i = 1; i < 9; i++) {
            int shift = i % 3 == 0 ? 1 : 3;
            for (int j = 0; j < 9; j++) {
                board[i][j] = new SudokuCell(i, j, board[i - 1][(j + shift) % 9].value);
                board[i][j].cellType = SudokuCell.CellType.GIVEN;//Sets default to be given
                board[i][j].unknown = false;//Sets default to be known
            }
        }

        // apply some random series of reflections and rotations to the board
        Random random = new Random();
        for (int i = 0, j = random.nextInt(3); i < j; i++) {
            transformBoard(random.nextInt(2) % 2 == 0 ?
                    new TransformReflect(TransformReflect.Reflection.values()[random.nextInt(2)]) :
                    new TransformRotate(random.nextInt(4))
            );
        }
    }

    /**
     * Prints the current board arrangement to the console.
     */
    private void printBoard() {
        for (SudokuCell[] row : board) {
            for (SudokuCell cell : row) {
                System.out.print(cell.value + " ");
            }
            System.out.println();
        }
    }

    /**
     * Checks if the board arrangement is valid.
     * For example, all columns, rows, and border contains the numbers from 1 to 9.
     *
     * @return whether the board arrangement is valid
     */
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

    /**
     * Checks and returns whether or not the array contains all integers from 1 to the length of the array
     *
     * @param check the array to check
     * @return whether the array has all integers from 1 to length of array.
     */
    private boolean validArray(int[] check) {//Checks if array contains all numbers from 1 to 9
        int i = 0;
        Arrays.sort(check);
        for (int number : check) {
            if (number != ++i)
                return false;
        }
        return true;
    }

    private void remove()//Removes numbers from board(reuse if the board result is invalid
    {
        Random rand = new Random();
        int tempX, tempY,counter=0;
        boolean least = false;

        for (int i = 0; i < 45; i++) {//At most 45 cells unknown, otherwise, the sudoku will have multiple solutions
            tempX = rand.nextInt(9);
            tempY = rand.nextInt(9);
            board[tempX][tempY].cellType = SudokuCell.CellType.EMPTY;//Unselected to be an unknown cell
            board[tempX][tempY].value = 0;
            board[tempX][tempY].unknown = true;
        }
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                if (board[x][y].unknown) {
                    counter++;//Counts how many of each number is unknown
                }
            }
        }

        if (counter < 40) {//
            while(!least)
            {
                tempX = rand.nextInt(9);
                tempY = rand.nextInt(9);
                board[tempX][tempY].cellType = SudokuCell.CellType.EMPTY;//Unselected to be an unknown cell
                board[tempX][tempY].value = 0;
                board[tempX][tempY].unknown = true;
                counter = 0;
                for (int x = 0; x < 9; x++) {
                    for (int y = 0; y < 9; y++) {
                        if (board[x][y].unknown) {
                            counter++;
                        }
                    }
                }
                if(counter>=45)
                {
                    least=true;
                }
            }
        }
    }

    /**
     * Randomly selects up to 45 cells to be removed. Any more than 48 clues will probably result in multiple solutions.
     */
    private void removeNumbers() {
        SudokuCell tempboard[][]=board;
        boolean toomuch;
        remove();
        int[] numbers = new int[9];
        for (int y = 0; y < 9; y++) {
            numbers[y] = 0;
        }
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                if (board[x][y].value != 0) {
                    numbers[board[x][y].value - 1]++;
                }
            }
        }
        for(int i=0;i<9;i++){
            if(numbers[i]>7)
            {
                toomuch=true;
                while(toomuch)
                {
                    board=tempboard;//Resets board for removing
                    remove();
                    for (int x = 0; x < 9; x++) {//Resets numbers
                        numbers[x]=0;
                    }
                    for (int x = 0; x < 9; x++) {
                        for (int y = 0; y < 9; y++) {
                            if (board[x][y].value != 0) {
                                numbers[board[x][y].value - 1]++;//Finds numbers
                            }
                        }
                    }
                    toomuch=false;
                    for (int x = 0; x < 9; x++) {
                        if (numbers[x] > 7) {
                            toomuch = true;
                        }
                    }
                }

            }
        }

    }

    /**
     * Selects a cell and deselects the previous cell
     *
     * @param cell the new cell to select
     */
    private void selectCell(SudokuCell cell) {
        if (selected != cell) {
            // provided a selected cell exists, deselect it first
            if (selected != null) {
                if (selected.cellStatus != SudokuCell.CellStatus.CONFLICTED)
                    selected.cellStatus = SudokuCell.CellStatus.UNSELECTED;
            }

            // then set selected to the currently hovered cell
            selected = cell;

            selected.cellStatus = SudokuCell.CellStatus.SELECTED;
        }
    }

    /**
     * Returns whether the cell has a conflict on the board.
     * It iterates over every element in a SudokuCell's neighbor element.
     * If the cell has no value, the function will return false.
     *
     * @param cell the cell the to check
     * @return whether the cell has a conflict
     */
    private boolean cellHasConflict(SudokuCell cell) {
        if (cell.unknown) return false;
        for (Coordinate coord : cell.neighbors)
            if (cell.value == board[coord.x][coord.y].value)
                return true;
        return false;
    }

    /**
     * Highlights all neighbors to the cell
     *
     * @param cell the SudokuCell to center on
     */
    private void highlightNeighbors(SudokuCell cell) {
        // iterate over all the neighbors
        for (Coordinate coord : cell.neighbors) {
            SudokuCell neighbor = board[coord.x][coord.y];
            if (!cellHasConflict(neighbor))
                neighbor.cellStatus = SudokuCell.CellStatus.HIGHLIGHTED;
        }
    }

    @Override
    public void update() {
        // iterate over every cell in the board
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                // position of the cell pixel-wise
                int cellX = this.position.x + x * combinedSpace;
                int cellY = this.position.y + y * combinedSpace;

                // the currently iterated cell
                SudokuCell currentCell = board[x][y];

                if (selected != currentCell)
                    currentCell.cellStatus = SudokuCell.CellStatus.UNSELECTED;
                else
                    currentCell.cellStatus = SudokuCell.CellStatus.SELECTED;

                if (cellHasConflict(currentCell))
                    currentCell.cellStatus = SudokuCell.CellStatus.CONFLICTED;

                // if the cursor is in this box
                if ((parent.mouseX > cellX && parent.mouseX < cellX + sideLength) &&
                        (parent.mouseY > cellY && parent.mouseY < cellY + sideLength)) {

                    // select the cell that is being hovered over
                    selectCell(currentCell);

                    // if the selected cell exists and is modifiable
                    if (selected != null && selected.cellType != SudokuCell.CellType.GIVEN) {
                        // on left click, toggle number
                        if (Input.getMouseButton(Input.Button.LEFT, Input.Event.PRESS)) {
                            selected.value = digitBoard.selectedDigit;
                            selected.notes = new boolean[9];

                            // toggle number in cell
                            selected.unknown = !selected.unknown;
                            if (selected.unknown) selected.value = 0;

                            // test for board completion
                            if (isValid()) solved = true;
                        }
                        // on right click, toggle notes
                        else if (Input.getMouseButton(Input.Button.RIGHT, Input.Event.PRESS) && selected.unknown) {
                            selected.notes[digitBoard.selectedDigit - 1] = !selected.notes[digitBoard.selectedDigit - 1];
                        }
                    }
                }
            }
        }

        if (selected != null) highlightNeighbors(selected);
    }

    @Override
    public void draw() {
        parent.pushStyle();
        final int sideLength1Quarter = sideLength / 4;
        final int sideLength3Quarter = sideLength - sideLength1Quarter;

        parent.strokeWeight(1);
        parent.rectMode(PConstants.CORNER);

        // draw backing
        parent.noStroke();
        parent.fill(SolarizedColours.getColour(4));
        parent.rect(this.position.x - spacing, this.position.y - spacing, 9 * combinedSpace + spacing, 9 * combinedSpace + spacing);

        // draw border around each box
        parent.strokeWeight(spacing / 2 - 1);
        parent.noFill();
        parent.stroke(SolarizedColours.getColour(1));
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                int boxX = this.position.x - (spacing / 2) + x * 3 * combinedSpace - 1;
                int boxY = this.position.y - (spacing / 2) + y * 3 * combinedSpace - 1;
                parent.rect(boxX, boxY, 3 * combinedSpace + 1, 3 * combinedSpace + 1);
            }
        }

        parent.noStroke();
        // draw each cell
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                int cellX = this.position.x + x * combinedSpace;
                int cellY = this.position.y + y * combinedSpace;

                SudokuCell currentCell = board[x][y];

                // cell backing
                parent.fill(currentCell.cellType.getColour());
                parent.rect(cellX, cellY, sideLength, sideLength);

                // cell tinting
                parent.fill(currentCell.cellStatus.getColour(), currentCell.cellType == SudokuCell.CellType.GIVEN ? 0x80 : 0x50);
                parent.rect(cellX, cellY, sideLength, sideLength);

                // cell numbers
                parent.fill(SolarizedColours.getText());

                // draw regular numbers
                if (currentCell.cellType == SudokuCell.CellType.GIVEN || !currentCell.unknown) {
                    parent.textFont(parent.createFont("Consolas", 30, true));
                    parent.text(currentCell.toString(), cellX + sideLength1Quarter, cellY + sideLength3Quarter);
                }
                // draw mini numbers for cell notes
                else {
                    parent.textFont(parent.createFont("Consolas", 13, true));
                    for (int i = 0; i < 9; i++) {
                        if (currentCell.notes[i])
                            parent.text(i + 1, cellX + sideLength1Quarter * (i % 3 + 1) - 3, cellY + sideLength1Quarter * (i / 3 + 1) + 3);
                    }

                }
            }
        }
        parent.popStyle();
    }

    /**
     * {@link ITransformation transformation} to the board.
     *
     * @param transformation transformation to apply
     * @return this SudokuBoard for method chaining
     */
    private SudokuBoard transformBoard(ITransformation transformation) {
        transformation.apply(board);
        return this;
    }
}
