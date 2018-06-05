package sudoku;

import processing.core.PApplet;

public class DigitBoard extends DrawableElement {
    private static final int sideLength = 75;
    private static final int spacing = 5;

    private int[] digits;

    public int selectedDigit = 1;
    
    public DigitBoard(PApplet parent) {
        super(parent);
        
        this.parent = parent;
        
        digits = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
    }


    @Override
    public void update() {
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                int cellX = this.position.x + x * (sideLength + spacing);
                int cellY = this.position.y + y * (sideLength + spacing);

                if ((parent.mouseX > cellX && parent.mouseX < cellX + sideLength) && (parent.mouseY > cellY && parent.mouseY < cellY + sideLength)) {
                    if (selectedDigit != digits[x + y * 3] && Input.getMouseButton(Button.LEFT, Event.PRESS)) {
                        selectedDigit = digits[x + y * 3];
                    }
                }
            }
        }
    }

    @Override
    public void draw() {
        parent.textFont(parent.createFont("Consolas", 50, true));

        parent.strokeWeight(0.0001f);

        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                int cellX = this.position.x + x * (sideLength + spacing);
                int cellY = this.position.y + y * (sideLength + spacing);

                if(digits[x + y * 3]==selectedDigit)
                {
                    parent.fill(0x80808080);
                    parent.rect(cellX, cellY, sideLength, sideLength);
                }
                else
                {
                    parent.fill(0x00000000);

                    parent.rect(cellX, cellY, sideLength, sideLength);
                }


                parent.fill(100, 100, 100);
                parent.text(digits[x + y * 3], cellX + sideLength / 4, cellY + sideLength - (sideLength / 4));

            }
        }
    }
}

