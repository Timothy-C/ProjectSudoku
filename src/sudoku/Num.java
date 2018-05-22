package sudoku;

import processing.core.PApplet;

public class Num extends DrawableElement {
    public void SelectNum(PApplet parent) {
        int[] nums;
        nums = new int[9];
        for (int blyat = 0; blyat < 9; blyat++) {
            nums[blyat] = blyat + 1;
        }
        parent.textFont(parent.createFont("Consolas", 30, true));
        int fillColor= 0;
        int boxX = 450;
        int boxY= 0;
        for(int i=0;i<5;i++)
        {
            parent.text(nums[i], boxX, boxY);
            boxY+=75;
        }

        //This stuff is for the numbers to be selected on the side panel. The boolean stuff has not been made yet.
    }
}
