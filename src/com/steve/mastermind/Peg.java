/**
 * Project: Mastermind
 * Created by Steve Schaefer on 12/22/2015.
 */
package com.steve.mastermind;

@SuppressWarnings("CanBeFinal")
class Peg {
    int x, y, color;
    Boolean tagged;
    String colorStr;

    public Peg(int x, int y, int color) {
        this.x = x;
        this.y = y;
        this.color = color;
        this.colorStr = this.getColorString(color);
        this.tagged = false;
    } //End constructor

    private String getColorString(int color) {
        String colorStr = "";
        switch (color) {
            case 0: colorStr = "Blue"; break;
            case 1: colorStr = "Red"; break;
            case 2: colorStr = "Orange"; break;
            case 3: colorStr = "Green"; break;
            case 4: colorStr = "Yellow"; break;
            case 5: colorStr = "White"; break;
        } //End switch
        return colorStr;
    }

} //End class
