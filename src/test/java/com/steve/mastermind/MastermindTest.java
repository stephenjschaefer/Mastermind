package com.steve.mastermind;

import org.junit.Before;
import org.junit.Test;
import javax.swing.*;
import static com.steve.mastermind.Mastermind.*;
import static org.junit.Assert.*;

/**
 * Project: Mastermind
 * Created by Steve Schaefer on 12/30/2015.
 */
public class MastermindTest {

    JFrame expected;
    JFrame result;

    @Before
    public void setUp() throws Exception {
        //Build Expected Frame
        expected = new JFrame("Mastermind");
        expected.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        expected.setSize(600, 550);
        expected.setVisible(true);
        expected.setResizable(false);

        //Build Result Frame
        Mastermind.main(null);
        result = frame;
    }

    @Test
    public void testConstructor() {
        new Mastermind();
    }

    @Test
    public void testMain() throws Exception {
        assertTrue(result.getTitle().compareTo("Mastermind")==0);
        assertTrue(result.getDefaultCloseOperation()==WindowConstants.EXIT_ON_CLOSE);
        assertTrue(result.getWidth()==600);
        assertTrue(result.getHeight()==550);
        assertTrue(result.isVisible());
        assertFalse(result.isResizable());
    }
}