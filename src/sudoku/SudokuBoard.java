package sudoku;

import processing.core.PApplet;
import processing.core.PConstants;
import sudoku.transform.ITransformation;

import java.util.Arrays;
import java.util.Random;

public class SudokuBoard extends DrawableElement {
	private static final int sideLength = 30;
	private static final int spacing = 2;
	
	SudokuCell[][] board;
	
	SudokuCell selected;
	private int[] nums = new int[9];

	// board is organized like this
	// board[x][y]
	// 0 x -
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
		for (int blyat = 0; blyat < 9; blyat++) {
			nums[blyat] = blyat + 1;
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
				board[i][j] = new SudokuCell(i, j, board[i - 1][(j + shift) % 9].value);
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
	
	public void draw() {
		parent.textFont(parent.createFont("Consolas", 50, true));//change font size for sideboxes
		parent.fill(100, 100, 100);
		parent.strokeWeight(0.0001f);
		int placeX = 450;
		int placeY= 50;//Puts the sideboxes for selecting numbers
		for (int i=0;i<5;i++)
		{
			parent.text(nums[i], placeX, 400-placeY);
			placeY += 75;
		}
		placeY=50;placeX=550;
		for (int i=5;i<9;i++)
		{
			parent.text(nums[i], placeX, 400-placeY);
			placeY+=75;
		}//end of sideboxes thing
		parent.rectMode(PConstants.CORNER);
        parent.textFont(parent.createFont("Consolas", 40, true));//change back font size
		// draw cells
		for (int x = 0; x < 9; x++) {
			for (int y = 0; y < 9; y++) {
				int cellX = this.position.x + x * (sideLength + spacing);
				int cellY = this.position.y + y * (sideLength + spacing);
				
				int fillColor = 0x000000;
				
				if ((parent.mouseX > cellX && parent.mouseX < cellX + sideLength) && (parent.mouseY > cellY && parent.mouseY < cellY + sideLength)) {
					if (selected != board[x][y]) {
						if (selected != null) {
							System.out.println(selected.position.toString());
							selected.status = SudokuCell.Status.UNSELECTED;
							for (Coordinate neighbor : selected.neighbors) {
								board[neighbor.x][neighbor.y].status = SudokuCell.Status.UNSELECTED;
							}
						}
						
						selected = board[x][y];
						selected.status = SudokuCell.Status.SELECTED;
						System.out.println(selected.position.toString());
						for (Coordinate neighbor : selected.neighbors) {
							board[neighbor.x][neighbor.y].status = SudokuCell.Status.HIGHLIGHTED;
						}
					}
				}
				
				// cell rectangle
				switch (board[x][y].status) {
					case UNSELECTED:
						fillColor = 0xFFFFFFFF;
						break;
					case SELECTED:
						fillColor = 0xFFA0A0A0;
						break;
					case HIGHLIGHTED:
						fillColor = 0xFFF0F000;
						break;
					case CONFLICTED:
						fillColor = 0xFFFF0000;
						break;
					
				}
				parent.fill(fillColor);
				parent.rect(cellX, cellY, sideLength, sideLength);
				
				// cell number
				parent.fill(0, 0, 0);
				parent.text(board[x][y].toString(), cellX + sideLength / 4, cellY + sideLength - (sideLength / 4));
			}
		}
		
		parent.strokeWeight(5);
		// draw 3x3 boxes
		parent.noFill();
		for (int x = 0; x < 3; x++) {//Draws thicc lines between the major boxes
			for (int y = 0; y < 3; y++) {
				int boxX = this.position.x - (spacing / 2) + 3 * x * (sideLength + spacing);
				int boxY = this.position.y - (spacing / 2) + 3 * y * (sideLength + spacing);
				parent.rect(boxX, boxY, 3 * (sideLength + spacing), 3 * (sideLength + spacing));
			}
		}
		parent.fill(100, 100, 100);

		parent.textFont(parent.createFont("Consolas", 40, true));
		int fillColor= 0;
		
	}
	
	public SudokuBoard transformBoard(ITransformation transformation) {
		transformation.apply(board);
		return this;
	}
}
