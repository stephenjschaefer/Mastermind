/**
 * Project: Mastermind
 * Created by Steve Schaefer on 12/22/2015.
 */
package com.steve.mastermind;

/**
 * Class representing a guess or response peg.
 */
@SuppressWarnings("CanBeFinal")
class Peg {
    int x, y, color;
    Boolean tagged;
    String colorStr;

    /**
     * Constructor that creates a new instance of a peg.
     * @param x x coordinate of the peg instance.
     * @param y y coordinate of the peg instance.
     * @param color Int representing the color of the peg instance.
     */
    public Peg(int x, int y, int color) {
        this.x = x;
        this.y = y;
        this.color = color;
        this.colorStr = this.getColorString(color);
        this.tagged = false;
    } //End constructor

    /**
     * This method returns the string value of a given pegs color.
     * @param color Int representing pegs color.
     * @return String value of a given pegs color.
     */
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
