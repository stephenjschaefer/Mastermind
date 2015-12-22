/**
 * Created by Steve Schaefer on 12/22/2015.
 */
package com.steve.mastermind;

public class Peg {
    protected int x, y, color;
    protected Boolean tagged;

    public Peg(int x, int y, int color) {
        this.x = x;
        this.y = y;
        this.color = color;
        this.tagged = false;
    } //End constructor

} //End class
