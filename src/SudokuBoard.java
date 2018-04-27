import processing.core.PApplet;

import java.util.Arrays;
import java.util.Random;

public class SudokuBoard extends DrawableElement {
	SudokuCell[][] board;
	
	SudokuBoard(PApplet parent) {
		super(parent);
		
		this.board = new SudokuCell[9][9];
		for (int x = 0; x < board.length; x++) {
			for (int y = 0; y < board[x].length; y++) {
				board[x][y] = new SudokuCell(new Coordinate(x, y));
			}
		}
		while (!isValid()) {
			generate();
			printBoard();
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
		parent.textFont(parent.createFont("Consolas", 15, true));
		parent.fill(0);
		for (int i = 0; i < 9; i++) {
			parent.text(Arrays.toString(board[i]), this.position.x, this.position.y + i * 20);
		}
	}
}
