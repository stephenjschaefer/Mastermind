/**
 * Project: Mastermind
 * Created by Steve Schaefer on 12/22/2015.
 */
package com.steve.mastermind;

    import javax.swing.*;

class Mastermind {

    static final JFrame frame = new JFrame("com.steve.mastermind.Mastermind");

    public static void main (String args[]) {
        JPanel MastermindGUI = new MastermindGUI();
        frame.add(MastermindGUI);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(530, 530);
        frame.setTitle("Mastermind");
        frame.setVisible(true);
    } //End main

} //End class
