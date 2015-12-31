package com.steve.mastermind;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Project: Mastermind
 * Created by Steve Schaefer on 12/30/2015.
 */
public class PegTest {

    @Test
    public void testConstructor() {
        new Peg(10,10,0);
    }

    @Test
    public void testPeg0() {
        int x = 10;
        int y = 10;
        int color = 0;
        Peg peg = new Peg(x, y, color);
        assertTrue(peg.x == 10);
        assertTrue(peg.y == 10);
        assertTrue(peg.color == 0);
        assertTrue(peg.colorStr.compareTo("Blue")==0);
        assertFalse(peg.tagged);
    }

    @Test
    public void testPeg1() {
        int x = 20;
        int y = 20;
        int color = 1;
        Peg peg = new Peg(x, y, color);
        assertTrue(peg.x == 20);
        assertTrue(peg.y == 20);
        assertTrue(peg.color == 1);
        assertTrue(peg.colorStr.compareTo("Red")==0);
        assertFalse(peg.tagged);
    }

    @Test
    public void testPeg2() {
        int x = 30;
        int y = 30;
        int color = 2;
        Peg peg = new Peg(x, y, color);
        assertTrue(peg.x == 30);
        assertTrue(peg.y == 30);
        assertTrue(peg.color == 2);
        assertTrue(peg.colorStr.compareTo("Orange")==0);
        assertFalse(peg.tagged);
    }

    @Test
    public void testPeg3() {
        int x = 40;
        int y = 40;
        int color = 3;
        Peg peg = new Peg(x, y, color);
        assertTrue(peg.x == 40);
        assertTrue(peg.y == 40);
        assertTrue(peg.color == 3);
        assertTrue(peg.colorStr.compareTo("Green")==0);
        assertFalse(peg.tagged);
    }

    @Test
    public void testPeg4() {
        int x = 50;
        int y = 50;
        int color = 4;
        Peg peg = new Peg(x, y, color);
        assertTrue(peg.x == 50);
        assertTrue(peg.y == 50);
        assertTrue(peg.color == 4);
        assertTrue(peg.colorStr.compareTo("Yellow")==0);
        assertFalse(peg.tagged);
    }

    @Test
    public void testPeg5() {
        int x = 60;
        int y = 60;
        int color = 5;
        Peg peg = new Peg(x, y, color);
        assertTrue(peg.x == 60);
        assertTrue(peg.y == 60);
        assertTrue(peg.color == 5);
        assertTrue(peg.colorStr.compareTo("White")==0);
        assertFalse(peg.tagged);
    }
}