import processing.core.PApplet;
import sudoku.SudokuBoard;
import sudoku.transform.TransformReflect;

public class MainClass extends PApplet {
	
	SudokuBoard board;
	
	public static void main(String[] args) {
		PApplet.main("MainClass");
	}
	
	@Override
	public void settings() {
		size(300, 300);
	}
	
	@Override
	public void setup() {
		board = new SudokuBoard(this);
		board.setPosition(60, 60);
		board.transformBoard(new TransformReflect(TransformReflect.Reflection.HORIZONTAL)).transformBoard(new TransformReflect(TransformReflect.Reflection.VERTICAL));
	}
	
	@Override
	public void draw() {
		board.draw();
	}
}
