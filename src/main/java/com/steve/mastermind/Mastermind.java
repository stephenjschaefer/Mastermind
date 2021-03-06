/**
 * Project: Mastermind
 * Created by Steve Schaefer on 12/22/2015.
 */
package com.steve.mastermind;

    import javax.swing.*;

/**
 * Class to instantiate a new frame that contains an instance of the Mastermind GUI.
 */
class Mastermind {

    static final JFrame frame = new JFrame("Mastermind");

    public static void main (String args[]) {
        JPanel MastermindGUI = new MastermindGUI();
        frame.add(MastermindGUI);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(600, 550);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
    } //End main

} //End class
