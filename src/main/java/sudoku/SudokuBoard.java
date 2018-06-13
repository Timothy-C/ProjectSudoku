package main.java.sudoku;

import main.java.sudoku.transform.ITransformation;
import processing.core.PApplet;
import processing.core.PConstants;

import java.util.Arrays;
import java.util.Random;

public class SudokuBoard extends DrawableElement {
    private static final int sideLength = 40;
    private static final int spacing = 8;

    public DigitBoard digitBoard;
    SudokuCell[][] board;
    SudokuCell selected;

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

        // perform row shifts(transformations)
        // https://gamedev.stackexchange.com/a/138228
        for (int i = 1; i < 9; i++) {
            int shift = i % 3 == 0 ? 1 : 3;
            for (int j = 0; j < 9; j++) {
                board[i][j] = new SudokuCell(i, j, board[i - 1][(j + shift) % 9].value);
                board[i][j].status = SudokuCell.Status.GIVEN;//Sets default to be given
                board[i][j].unknown = false;//Sets default to be known
            }
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

    private boolean validArray(int[] check) {//Checks if array contains all numbers from 1 to 9
        int i = 0;
        Arrays.sort(check);
        for (int number : check) {
            if (number != ++i)
                return false;
        }
        return true;
    }

    /**
     * Randomly selects up to 45 cells to be removed. Any more than 48 clues will probably result in multiple solutions.
     */
    private void removeNumbers() {
        Random rand = new Random();
        int tempX, tempY, counter;
        boolean least = false;
        while (!least) {
            //Removes the numbers from 30 to 35 to random cells, so that there are between 46 to 51 clues.
            for (int i = 0; i < 2; i++) {//<35
                tempX = rand.nextInt(9);
                tempY = rand.nextInt(9);
                board[tempX][tempY].status = SudokuCell.Status.UNSELECTED;//Unselected to be an unknown cell
                board[tempX][tempY].value = 0;
                board[tempX][tempY].unknown = true;
            }
            counter = 0;
            for (int x = 0; x < 9; x++) {
                for (int y = 0; y < 9; y++) {
                    if (board[x][y].unknown) {
                        counter++;
                    }
                }
            }
            if (counter > 00) {//>30
                least = true;
            }
        }
    }

    private void validnum(int num, int a, int b) {
        for (Coordinate neighbor : board[a][b].neighbors) {//This is used to get all the neighboring cells to be highlighted.
            if (board[neighbor.x][neighbor.y].value == num ) {
                board[a][b].status = SudokuCell.Status.CONFLICTED;
            }
        }
    }
    private boolean isSolved()
    {
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                if (board[x][y].value == 0 || board[x][y].status ==SudokuCell.Status.CONFLICTED) {
                    return false;
                }
            }
        }
        return true;
    }


    @Override
    public void update() {

        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                int cellX = this.position.x + x * (sideLength + spacing);
                int cellY = this.position.y + y * (sideLength + spacing);

                // if the cursor is in this box
                if ((parent.mouseX > cellX && parent.mouseX < cellX + sideLength) &&
                        (parent.mouseY > cellY && parent.mouseY < cellY + sideLength)) {
                    // if the selected box is not the old selected, selected is changed
                    if (selected != board[x][y]) {
                        if (selected != null) {
                            if (selected.status != SudokuCell.Status.GIVEN) {
                                selected.status = SudokuCell.Status.UNSELECTED;//Changes the old selected into unselected

//                                for (Coordinate neighbor : selected.neighbors) {//This is used to get all the neighboring cells to be highlighted, but now unusable
//                                    if (board[neighbor.x][neighbor.y].status != SudokuCell.Status.GIVEN) {
//                                        board[neighbor.x][neighbor.y].status = SudokuCell.Status.UNSELECTED;
//                                    }
//                                }
                            }
                        }
                        selected = board[x][y];
                        if (selected.status != SudokuCell.Status.GIVEN) {
                            selected.status = SudokuCell.Status.SELECTED;
                        }

                    }

                    // mouse left click
                    if (selected.status == SudokuCell.Status.SELECTED && Input.getMouseButton(Input.Button.LEFT, Input.Event.PRESS)) {
                        selected.value = digitBoard.selectedDigit;
                        for (int i = 0; i < 9; i++) {
                            selected.notes[i] = false;
                        }

                        selected.unknown = !selected.unknown;//Switches from unknown to known
                        if (selected.unknown) {
                            selected.value = 0;
                        }
                    }

                    // mouse right click
                    if (selected.status == SudokuCell.Status.SELECTED && selected.unknown && Input.getMouseButton(Input.Button.RIGHT, Input.Event.PRESS)) {
                        if (!selected.notes[digitBoard.selectedDigit - 1]) {
                            selected.value = 0;
                        }
                        selected.notes[digitBoard.selectedDigit - 1] = !selected.notes[digitBoard.selectedDigit - 1];
                    }
                }
            }
        }

        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                if (board[x][y].value != 0 && board[x][y].status !=SudokuCell.Status.GIVEN && board[x][y] != selected) {
                    validnum(board[x][y].value, x, y);
                }
            }
        }
        if(isSolved())
        {
            //win stuff
        }
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
        parent.rect(this.position.x - spacing, this.position.y - spacing, 9 * (sideLength + spacing) + spacing, 9 * (sideLength + spacing) + spacing);

        // draw 3*3 border
        parent.strokeWeight(spacing / 2 - 1);
        parent.noFill();
        parent.stroke(SolarizedColours.getColour(1));
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                int boxX = this.position.x - (spacing / 2) + x * 3 * (sideLength + spacing) - 1;
                int boxY = this.position.y - (spacing / 2) + y * 3 * (sideLength + spacing) - 1;
                parent.rect(boxX, boxY, 3 * (sideLength + spacing) + 1, 3 * (sideLength + spacing) + 1);
            }
        }

        parent.noStroke();
        // draw cells
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                int cellX = this.position.x + x * (sideLength + spacing);
                int cellY = this.position.y + y * (sideLength + spacing);

                parent.fill(board[x][y].status.getColour());
                parent.rect(cellX, cellY, sideLength, sideLength);

                // cell number
                parent.fill(SolarizedColours.getText());
                if (board[x][y].status == SudokuCell.Status.GIVEN || !board[x][y].unknown) { // not notes
                    parent.textFont(parent.createFont("Consolas", 30, true));
                    parent.text(board[x][y].toString(), cellX + sideLength1Quarter, cellY + sideLength3Quarter);
                } else if (board[x][y].unknown) { // cell notes
                    parent.textFont(parent.createFont("Consolas", 13, true));
                    for (int i = 0; i < 9; i++) {
                        if (board[x][y].notes[i])
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
    public SudokuBoard transformBoard(ITransformation transformation) {
        transformation.apply(board);
        return this;
    }
}
