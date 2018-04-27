import processing.core.PApplet;

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
	}
	
	@Override
	public void draw() {
		board.draw();
	}
}
