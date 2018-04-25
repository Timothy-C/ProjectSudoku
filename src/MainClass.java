import processing.core.PApplet;

public class MainClass extends PApplet {
	public static void main(String[] args) {
		PApplet.main("MainClass");
		new SudokuBoard();
	}
	
	@Override
	public void settings() {
		size(300, 300);
	}
	
	@Override
	public void setup() {
		fill(120, 50, 240);
	}
	
	@Override
	public void draw() {
		ellipse(mouseX, mouseY, 30, 30);
		
	}
}
