package com.steve.mastermind;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.awt.image.BufferedImage;

import static org.junit.Assert.*;

/**
 * Project: Mastermind
 * Created by Steve Schaefer on 12/30/2015.
 */
public class MastermindGUITest {

    MastermindGUI result;
    Graphics2D g2;

    @Before
    public void setUp() throws Exception {
        result = new MastermindGUI();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testConstructor() {
        new MastermindGUI();
    }

    @Test
    public void testPaintComponent() throws Exception {
        int width = 600;
        int height = 530;
        BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        g2 = bi.createGraphics();
        result.setSize(width,height);
        result.paint(g2);
    }

}