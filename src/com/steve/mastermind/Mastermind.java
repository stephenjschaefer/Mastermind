/**
 * Created by Steve Schaefer on 12/22/2015.
 */
package com.steve.mastermind;

    import javax.swing.JPanel;
    import javax.swing.JFrame;

public class Mastermind {

    protected static JFrame frame = new JFrame("com.steve.mastermind.Mastermind");

    public static void main (String args[]) {
        JPanel MastermindGUI = new MastermindGUI();
        frame.add(MastermindGUI);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize( 500, 500 );
        frame.setTitle("Mastermind");
        frame.setVisible(true);
    } //End main

} //End class
