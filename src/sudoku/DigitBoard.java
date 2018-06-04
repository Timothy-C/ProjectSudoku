package sudoku;

import processing.core.PApplet;

public class DigitBoard extends DrawableElement {
    
    private int[] digits;
    
    public DigitBoard(PApplet parent) {
        super(parent);
        
        this.parent = parent;
        
        digits = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
    }
    
    @Override
    public void draw() {
        parent.textFont(parent.createFont("Consolas", 50, true));
        parent.fill(100, 100, 100);
        parent.strokeWeight(0.0001f);
        
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                parent.text(digits[x + y * 3], position.x + x * 75, position.y + y * 75);
            }
        }

//        int placeX = 450;
//        int placeY = 50;//Puts the sideboxes for selecting numbers
//        for (int i = 0; i < 5; i++) {
//            parent.text(digits[i], placeX, 400 - posi);
//            placeY += 75;
//        }
//        placeY = 50;
//        placeX = 550;
//        for (int i = 5; i < 9; i++) {
//            parent.text(digits[i], placeX, 400 - placeY);
//            placeY += 75;
//        }//end of sideboxes thing
    }
}

