/**
 * Created by Steve Schaefer on 12/22/2015.
 */
package com.steve.mastermind;

    import javax.swing.*;
    import java.awt.*;
    import java.awt.event.ActionEvent;
    import java.awt.event.ActionListener;
    import java.awt.event.MouseEvent;
    import java.awt.event.MouseListener;
    import java.awt.geom.Line2D;
    import java.util.Random;

public class MastermindGUI extends JPanel {

    // Declarations
    private JButton blue = new JButton("Blue");
    private JButton red = new JButton("Red");
    private JButton orange = new JButton("Orange");
    private JButton green = new JButton("Green");
    private JButton yellow = new JButton("Yellow");
    private JButton white = new JButton("White");
    private JButton guess = new JButton("Make Guess");
    private JButton newGame = new JButton("New Game");
    private JLabel currentGuess = new JLabel("Current Guess:");
    private int guessRow = 1;
    private JLabel guessNum = new JLabel("0");
    private JLabel currentColor = new JLabel("Current Color:");
    private JLabel selectedColor = new JLabel("----------");
    private int[] guessX = {20,60,100,140};
    private int[] guessY = {390,350,310,270,230,190,150,110,70,30};
    private int[] evalX = {230,270,310,350};
    private int[] evalY = {390,350,310,270,230,190,150,110,70,30};
    private int color = 6;
    private Peg[] guessPeg = new Peg[40];
    private Peg[] evalPeg = new Peg[40];
    private Peg[] answerPeg = new Peg[4];
    private int pegCount = 0;
    private Boolean validGuess = true;
    private int redCount = 0;
    private int whiteCount = 0;
    private Random generator = new Random();
    private int rnd;
    private Boolean start = false;
    private int evalCount = 0;
    private String peg1, peg2, peg3, peg4;
    private int diffLevel;
    private int diffSetting;

    public MastermindGUI() {
        JPanel colorSelect = new JPanel();

        ButtonHandler buttonHandle = new ButtonHandler();
        com.steve.mastermind.Mastermind.frame.addMouseListener(new MouseHandler());

        //Add button handler
        blue.addActionListener(buttonHandle);
        red.addActionListener(buttonHandle);
        orange.addActionListener(buttonHandle);
        green.addActionListener(buttonHandle);
        yellow.addActionListener(buttonHandle);
        white.addActionListener(buttonHandle);
        newGame.addActionListener(buttonHandle);
        guess.addActionListener(buttonHandle);

        //Disenable Buttons Initially
        blue.setEnabled(false);
        red.setEnabled(false);
        orange.setEnabled(false);
        green.setEnabled(false);
        yellow.setEnabled(false);
        white.setEnabled(false);
        guess.setEnabled(false);

        //Add buttons
        colorSelect.setLayout(new FlowLayout());
        colorSelect.add(blue);
        colorSelect.add(red);
        colorSelect.add(orange);
        colorSelect.add(green);
        colorSelect.add(yellow);
        colorSelect.add(white);

        JPanel sideBar = new JPanel();
        sideBar.setLayout(new BoxLayout(sideBar, BoxLayout.Y_AXIS));

        //Set element attributes
        newGame.setAlignmentX(Component.CENTER_ALIGNMENT);
        guess.setAlignmentX(Component.CENTER_ALIGNMENT);
        currentGuess.setAlignmentX(Component.CENTER_ALIGNMENT);
        guessNum.setAlignmentX(Component.CENTER_ALIGNMENT);
        currentColor.setAlignmentX(Component.CENTER_ALIGNMENT);
        selectedColor.setAlignmentX(Component.CENTER_ALIGNMENT);

        //Add elements
        sideBar.add(Box.createRigidArea(new Dimension(0,18)));
        sideBar.add(newGame);
        sideBar.add(Box.createRigidArea(new Dimension(0,247)));
        sideBar.add(currentGuess);
        sideBar.add(guessNum);
        sideBar.add(Box.createRigidArea(new Dimension(0,20)));
        sideBar.add(currentColor);
        sideBar.add(selectedColor);
        sideBar.add(Box.createRigidArea(new Dimension(0,20)));
        sideBar.add(guess);

        //Add all panels together
        com.steve.mastermind.Mastermind.frame.add(colorSelect, BorderLayout.SOUTH);
        com.steve.mastermind.Mastermind.frame.add(sideBar, BorderLayout.EAST);
        com.steve.mastermind.Mastermind.frame.add(this, BorderLayout.CENTER);

        //Initialize Guess com.steve.mastermind.Peg Array
        pegCount = 0;
        for (int i=0; i<guessY.length; i++) {
            for (int j=0; j<guessX.length; j++) {
                guessPeg[pegCount++] = new Peg(guessX[j], guessY[i], 6);
            } //End for
        } //End for

        //Initialize Eval com.steve.mastermind.Peg Array
        pegCount = 0;
        for (int i=0; i<evalY.length; i++) {
            for (int j=0; j<evalX.length; j++) {
                evalPeg[pegCount++] = new Peg(evalX[j], evalY[i], 6);
            } //End for
        } //End for

    } //End constructor

    // Button handler to switch moods
    private class ButtonHandler implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            if(event.getSource() == blue && start) {
                selectedColor.setText("Blue");
                color = 0;
            } //End if
            if(event.getSource() == red && start) {
                selectedColor.setText("Red");
                color = 1;
            } //End if
            if(event.getSource() == orange && start) {
                selectedColor.setText("Orange");
                color = 2;
            } //End if
            if(event.getSource() == green && start) {
                selectedColor.setText("Green");
                color = 3;
            } //End if
            if(event.getSource() == yellow && start) {
                selectedColor.setText("Yellow");
                color = 4;
            } //End if
            if(event.getSource() == white && start) {
                selectedColor.setText("White");
                color = 5;
            } //End if
            if(event.getSource() == newGame) {
                if (newGame.getText() == "New Game") {
                    for (int i=0; i<40; i++) {
                        guessPeg[i].color = 6;
                        evalPeg[i].color = 6;
                    } //End for
                    guessRow = 1;
                    guessNum.setText("1");
                    color = 6;
                    selectedColor.setText("----------");

                    // Difficulty Setting Prompt
                    Object[] options = { "Easy", "Medium", "Hard" };
                    diffSetting = JOptionPane.showOptionDialog(com.steve.mastermind.Mastermind.frame, "Select Your Difficulty Level", "Difficulty Setting", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);

                    //Reset Buttons
                    blue.setEnabled(true);
                    red.setEnabled(true);
                    orange.setEnabled(true);
                    green.setEnabled(true);
                    yellow.setEnabled(true);
                    white.setEnabled(true);
                    guess.setEnabled(true);

                    //Adjust Based On Difficulty Setting
                    switch (diffSetting) {
                        case JOptionPane.YES_OPTION:
                            diffLevel = 1;
                            orange.setEnabled(false);
                            green.setEnabled(false);
                            yellow.setEnabled(false);
                            white.setEnabled(false);
                            break;
                        case JOptionPane.NO_OPTION:
                            diffLevel = 2;
                            yellow.setEnabled(false);
                            white.setEnabled(false);
                            break;
                        case JOptionPane.CANCEL_OPTION:
                            diffLevel = 3;
                            break;
                        default:
                            diffLevel = 3;
                            break;
                    } //End switch

                    //Initialize Answer Pegs
                    for (int i=0; i<4; i++) {
                        answerPeg[i] = new Peg(0,0,generator.nextInt(2*diffLevel));
                    } //End for

                    //Initialize Answer String
                    switch (answerPeg[0].color) {
                        case 0: peg1 = "Blue"; break;
                        case 1: peg1 = "Red"; break;
                        case 2: peg1 = "Orange"; break;
                        case 3: peg1 = "Green"; break;
                        case 4: peg1 = "Yellow"; break;
                        case 5: peg1 = "White"; break;
                    } //End switch

                    switch (answerPeg[1].color) {
                        case 0: peg2 = "Blue"; break;
                        case 1: peg2 = "Red"; break;
                        case 2: peg2 = "Orange"; break;
                        case 3: peg2 = "Green"; break;
                        case 4: peg2 = "Yellow"; break;
                        case 5: peg2 = "White"; break;
                    } //End switch

                    switch (answerPeg[2].color) {
                        case 0: peg3 = "Blue"; break;
                        case 1: peg3 = "Red"; break;
                        case 2: peg3 = "Orange"; break;
                        case 3: peg3 = "Green"; break;
                        case 4: peg3 = "Yellow"; break;
                        case 5: peg3 = "White"; break;
                    } //End switch

                    switch (answerPeg[3].color) {
                        case 0: peg4 = "Blue"; break;
                        case 1: peg4 = "Red"; break;
                        case 2: peg4 = "Orange"; break;
                        case 3: peg4 = "Green"; break;
                        case 4: peg4 = "Yellow"; break;
                        case 5: peg4 = "White"; break;
                    } //End switch

                    start = true;
                    newGame.setText("Give Up?");
                } //End if
                else {
                    if (newGame.getText() == "Give Up?") {
                        JOptionPane.showMessageDialog(com.steve.mastermind.Mastermind.frame, "<html><body><center>The Answer Was:<br>"+peg1+", "+peg2+", "+peg3+", "+peg4+"<br><br>Better Luck Next Time!</center></body></html>");
                        start = false;
                        blue.setEnabled(false);
                        red.setEnabled(false);
                        orange.setEnabled(false);
                        green.setEnabled(false);
                        yellow.setEnabled(false);
                        white.setEnabled(false);
                        guess.setEnabled(false);
                        newGame.setText("New Game");
                    } //End if
                } //End else
            } //End if
            if(event.getSource() == guess && start) {
                validGuess = true;
                switch (guessRow) {
                    case 1:
                        for (int i=0; i<4; i++) {
                            if (guessPeg[i].color == 6) {
                                validGuess = false;
                            } //End if
                        } //End for
                        break;
                    case 2:
                        for (int i=4; i<8; i++) {
                            if (guessPeg[i].color == 6) {
                                validGuess = false;
                            } //End if
                        } //End for
                        break;
                    case 3:
                        for (int i=8; i<12; i++) {
                            if (guessPeg[i].color == 6) {
                                validGuess = false;
                            } //End if
                        } //End for
                        break;
                    case 4:
                        for (int i=12; i<16; i++) {
                            if (guessPeg[i].color == 6) {
                                validGuess = false;
                            } //End if
                        } //End for
                        break;
                    case 5:
                        for (int i=16; i<20; i++) {
                            if (guessPeg[i].color == 6) {
                                validGuess = false;
                            } //End if
                        } //End for
                        break;
                    case 6:
                        for (int i=20; i<24; i++) {
                            if (guessPeg[i].color == 6) {
                                validGuess = false;
                            } //End if
                        } //End for
                        break;
                    case 7:
                        for (int i=24; i<28; i++) {
                            if (guessPeg[i].color == 6) {
                                validGuess = false;
                            } //End if
                        } //End for
                        break;
                    case 8:
                        for (int i=28; i<32; i++) {
                            if (guessPeg[i].color == 6) {
                                validGuess = false;
                            } //End if
                        } //End for
                        break;
                    case 9:
                        for (int i=32; i<36; i++) {
                            if (guessPeg[i].color == 6) {
                                validGuess = false;
                            } //End if
                        } //End for
                        break;
                    case 10:
                        for (int i=36; i<40; i++) {
                            if (guessPeg[i].color == 6) {
                                validGuess = false;
                            } //End if
                        } //End for
                        break;
                    default:
                        break;
                } //End switch

                //Perform Evaluation
                if (validGuess) {
                    for (int i=0; i<4; i++) {
                        answerPeg[i].tagged = false;
                    } //End for
                    redCount = 0;
                    whiteCount = 0;
                    switch (guessRow) {
                        case 1:
                            for (int i=0; i<4; i++) {
                                for (int j=0; j<4; j++) {
                                    if (guessPeg[i].color == answerPeg[i].color) {
                                        redCount++;
                                        if (answerPeg[i].tagged) {
                                            whiteCount--;
                                        } //End if
                                        answerPeg[i].tagged = true;
                                        break;
                                    } //End if
                                    else {
                                        if (guessPeg[i].color == answerPeg[j].color && i!=j && !answerPeg[j].tagged) {
                                            whiteCount++;
                                            answerPeg[j].tagged = true;
                                            break;
                                        } //End if
                                    } //End else
                                } //End for
                            } //End for
                            break;

                        case 2:
                            for (int i=4; i<8; i++) {
                                for (int j=0; j<4; j++) {
                                    if (guessPeg[i].color == answerPeg[i-4].color) {
                                        redCount++;
                                        if (answerPeg[i-4].tagged) {
                                            whiteCount--;
                                        } //End if
                                        answerPeg[i-4].tagged = true;
                                        break;
                                    } //End if
                                    else {
                                        if (guessPeg[i].color == answerPeg[j].color && i-4!=j && !answerPeg[j].tagged) {
                                            whiteCount++;
                                            answerPeg[j].tagged = true;
                                            break;
                                        } //End if
                                    } //End else
                                } //End for
                            } //End for
                            break;

                        case 3:
                            for (int i=8; i<12; i++) {
                                for (int j=0; j<4; j++) {
                                    if (guessPeg[i].color == answerPeg[i-8].color) {
                                        redCount++;
                                        if (answerPeg[i-8].tagged) {
                                            whiteCount--;
                                        } //End if
                                        answerPeg[i-8].tagged = true;
                                        break;
                                    } //End if
                                    else {
                                        if (guessPeg[i].color == answerPeg[j].color && i-8!=j && !answerPeg[j].tagged) {
                                            whiteCount++;
                                            answerPeg[j].tagged = true;
                                            break;
                                        } //End if
                                    } //End else
                                } //End for
                            } //End for
                            break;

                        case 4:
                            for (int i=12; i<16; i++) {
                                for (int j=0; j<4; j++) {
                                    if (guessPeg[i].color == answerPeg[i-12].color) {
                                        redCount++;
                                        if (answerPeg[i-12].tagged) {
                                            whiteCount--;
                                        } //End if
                                        answerPeg[i-12].tagged = true;
                                        break;
                                    } //End if
                                    else {
                                        if (guessPeg[i].color == answerPeg[j].color && i-12!=j && !answerPeg[j].tagged) {
                                            whiteCount++;
                                            answerPeg[j].tagged = true;
                                            break;
                                        } //End if
                                    } //End else
                                } //End for
                            } //End for
                            break;

                        case 5:
                            for (int i=16; i<20; i++) {
                                for (int j=0; j<4; j++) {
                                    if (guessPeg[i].color == answerPeg[i-16].color) {
                                        redCount++;
                                        if (answerPeg[i-16].tagged) {
                                            whiteCount--;
                                        } //End if
                                        answerPeg[i-16].tagged = true;
                                        break;
                                    } //End if
                                    else {
                                        if (guessPeg[i].color == answerPeg[j].color && i-16!=j && !answerPeg[j].tagged) {
                                            whiteCount++;
                                            answerPeg[j].tagged = true;
                                            break;
                                        } //End if
                                    } //End else
                                } //End for
                            } //End for
                            break;

                        case 6:
                            for (int i=20; i<24; i++) {
                                for (int j=0; j<4; j++) {
                                    if (guessPeg[i].color == answerPeg[i-20].color) {
                                        redCount++;
                                        if (answerPeg[i-20].tagged) {
                                            whiteCount--;
                                        } //End if
                                        answerPeg[i-20].tagged = true;
                                        break;
                                    } //End if
                                    else {
                                        if (guessPeg[i].color == answerPeg[j].color && i-20!=j && !answerPeg[j].tagged) {
                                            whiteCount++;
                                            answerPeg[j].tagged = true;
                                            break;
                                        } //End if
                                    } //End else
                                } //End for
                            } //End for
                            break;

                        case 7:
                            for (int i=24; i<28; i++) {
                                for (int j=0; j<4; j++) {
                                    if (guessPeg[i].color == answerPeg[i-24].color) {
                                        redCount++;
                                        if (answerPeg[i-24].tagged) {
                                            whiteCount--;
                                        } //End if
                                        answerPeg[i-24].tagged = true;
                                        break;
                                    } //End if
                                    else {
                                        if (guessPeg[i].color == answerPeg[j].color && i-24!=j && !answerPeg[j].tagged) {
                                            whiteCount++;
                                            answerPeg[j].tagged = true;
                                            break;
                                        } //End if
                                    } //End else
                                } //End for
                            } //End for
                            break;

                        case 8:
                            for (int i=28; i<32; i++) {
                                for (int j=0; j<4; j++) {
                                    if (guessPeg[i].color == answerPeg[i-28].color) {
                                        redCount++;
                                        if (answerPeg[i-28].tagged) {
                                            whiteCount--;
                                        } //End if
                                        answerPeg[i-28].tagged = true;
                                        break;
                                    } //End if
                                    else {
                                        if (guessPeg[i].color == answerPeg[j].color && i-28!=j && !answerPeg[j].tagged) {
                                            whiteCount++;
                                            answerPeg[j].tagged = true;
                                            break;
                                        } //End if
                                    } //End else
                                } //End for
                            } //End for
                            break;

                        case 9:
                            for (int i=32; i<36; i++) {
                                for (int j=0; j<4; j++) {
                                    if (guessPeg[i].color == answerPeg[i-32].color) {
                                        redCount++;
                                        if (answerPeg[i-32].tagged) {
                                            whiteCount--;
                                        } //End if
                                        answerPeg[i-32].tagged = true;
                                        break;
                                    } //End if
                                    else {
                                        if (guessPeg[i].color == answerPeg[j].color && i-32!=j && !answerPeg[j].tagged) {
                                            whiteCount++;
                                            answerPeg[j].tagged = true;
                                            break;
                                        } //End if
                                    } //End else
                                } //End for
                            } //End for
                            break;

                        case 10:
                            for (int i=36; i<40; i++) {
                                for (int j=0; j<4; j++) {
                                    if (guessPeg[i].color == answerPeg[i-36].color) {
                                        redCount++;
                                        if (answerPeg[i-36].tagged) {
                                            whiteCount--;
                                        } //End if
                                        answerPeg[i-36].tagged = true;
                                        break;
                                    } //End if
                                    else {
                                        if (guessPeg[i].color == answerPeg[j].color && i-36!=j && !answerPeg[j].tagged) {
                                            whiteCount++;
                                            answerPeg[j].tagged = true;
                                            break;
                                        } //End if
                                    } //End else
                                } //End for
                            } //End for
                            break;

                        default:
                            break;
                    } //End switch

                    //Set eval pegs based on eval
                    evalCount = 0;
                    switch (guessRow) {
                        case 1:
                            for (int i=0; i<redCount; i++) {
                                rnd = generator.nextInt(4);
                                if (evalPeg[rnd].color == 6) {
                                    evalPeg[rnd].color = 0;
                                    evalCount++;
                                } //End if
                                else {
                                    i--;
                                } //End else
                            } //End for

                            for (int i=0; i<whiteCount; i++) {
                                if (evalCount == 4) {
                                    break;
                                } //End if
                                else {
                                    rnd = generator.nextInt(4);
                                    if (evalPeg[rnd].color == 6) {
                                        evalPeg[rnd].color = 1;
                                        evalCount++;
                                    } //End if
                                    else {
                                        i--;
                                    } //End else
                                } //End else
                            } //End for
                            break;

                        case 2:
                            for (int i=0; i<redCount; i++) {
                                rnd = generator.nextInt(4)+4;
                                if (evalPeg[rnd].color == 6) {
                                    evalPeg[rnd].color = 0;
                                    evalCount++;
                                } //End if
                                else {
                                    i--;
                                } //End else
                            } //End for

                            for (int i=0; i<whiteCount; i++) {
                                if (evalCount == 4) {
                                    break;
                                } //End if
                                else {
                                    rnd = generator.nextInt(4)+4;
                                    if (evalPeg[rnd].color == 6) {
                                        evalPeg[rnd].color = 1;
                                        evalCount++;
                                    } //End if
                                    else {
                                        i--;
                                    } //End else
                                } //End else
                            } //End for
                            break;

                        case 3:
                            for (int i=0; i<redCount; i++) {
                                rnd = generator.nextInt(4)+8;
                                if (evalPeg[rnd].color == 6) {
                                    evalPeg[rnd].color = 0;
                                    evalCount++;
                                } //End if
                                else {
                                    i--;
                                } //End else
                            } //End for

                            for (int i=0; i<whiteCount; i++) {
                                if (evalCount == 4) {
                                    break;
                                } //End if
                                else {
                                    rnd = generator.nextInt(4)+8;
                                    if (evalPeg[rnd].color == 6) {
                                        evalPeg[rnd].color = 1;
                                        evalCount++;
                                    } //End if
                                    else {
                                        i--;
                                    } //End else
                                } //End else
                            } //End for
                            break;

                        case 4:
                            for (int i=0; i<redCount; i++) {
                                rnd = generator.nextInt(4)+12;
                                if (evalPeg[rnd].color == 6) {
                                    evalPeg[rnd].color = 0;
                                    evalCount++;
                                } //End if
                                else {
                                    i--;
                                } //End else
                            } //End for

                            for (int i=0; i<whiteCount; i++) {
                                if (evalCount == 4) {
                                    break;
                                } //End if
                                else {
                                    rnd = generator.nextInt(4)+12;
                                    if (evalPeg[rnd].color == 6) {
                                        evalPeg[rnd].color = 1;
                                        evalCount++;
                                    } //End if
                                    else {
                                        i--;
                                    } //End else
                                } //End else
                            } //End for
                            break;

                        case 5:
                            for (int i=0; i<redCount; i++) {
                                rnd = generator.nextInt(4)+16;
                                if (evalPeg[rnd].color == 6) {
                                    evalPeg[rnd].color = 0;
                                    evalCount++;
                                } //End if
                                else {
                                    i--;
                                } //End else
                            } //End for

                            for (int i=0; i<whiteCount; i++) {
                                if (evalCount == 4) {
                                    break;
                                } //End if
                                else {
                                    rnd = generator.nextInt(4)+16;
                                    if (evalPeg[rnd].color == 6) {
                                        evalPeg[rnd].color = 1;
                                        evalCount++;
                                    } //End if
                                    else {
                                        i--;
                                    } //End else
                                } //End else
                            } //End for
                            break;

                        case 6:
                            for (int i=0; i<redCount; i++) {
                                rnd = generator.nextInt(4)+20;
                                if (evalPeg[rnd].color == 6) {
                                    evalPeg[rnd].color = 0;
                                    evalCount++;
                                } //End if
                                else {
                                    i--;
                                } //End else
                            } //End for

                            for (int i=0; i<whiteCount; i++) {
                                if (evalCount == 4) {
                                    break;
                                } //End if
                                else {
                                    rnd = generator.nextInt(4)+20;
                                    if (evalPeg[rnd].color == 6) {
                                        evalPeg[rnd].color = 1;
                                        evalCount++;
                                    } //End if
                                    else {
                                        i--;
                                    } //End else
                                } //End else
                            } //End for
                            break;

                        case 7:
                            for (int i=0; i<redCount; i++) {
                                rnd = generator.nextInt(4)+24;
                                if (evalPeg[rnd].color == 6) {
                                    evalPeg[rnd].color = 0;
                                    evalCount++;
                                } //End if
                                else {
                                    i--;
                                } //End else
                            } //End for

                            for (int i=0; i<whiteCount; i++) {
                                if (evalCount == 4) {
                                    break;
                                } //End if
                                else {
                                    rnd = generator.nextInt(4)+24;
                                    if (evalPeg[rnd].color == 6) {
                                        evalPeg[rnd].color = 1;
                                        evalCount++;
                                    } //End if
                                    else {
                                        i--;
                                    } //End else
                                } //End else
                            } //End for
                            break;

                        case 8:
                            for (int i=0; i<redCount; i++) {
                                rnd = generator.nextInt(4)+28;
                                if (evalPeg[rnd].color == 6) {
                                    evalPeg[rnd].color = 0;
                                    evalCount++;
                                } //End if
                                else {
                                    i--;
                                } //End else
                            } //End for

                            for (int i=0; i<whiteCount; i++) {
                                if (evalCount == 4) {
                                    break;
                                } //End if
                                else {
                                    rnd = generator.nextInt(4)+28;
                                    if (evalPeg[rnd].color == 6) {
                                        evalPeg[rnd].color = 1;
                                        evalCount++;
                                    } //End if
                                    else {
                                        i--;
                                    } //End else
                                } //End else
                            } //End for
                            break;

                        case 9:
                            for (int i=0; i<redCount; i++) {
                                rnd = generator.nextInt(4)+32;
                                if (evalPeg[rnd].color == 6) {
                                    evalPeg[rnd].color = 0;
                                    evalCount++;
                                } //End if
                                else {
                                    i--;
                                } //End else
                            } //End for

                            for (int i=0; i<whiteCount; i++) {
                                if (evalCount == 4) {
                                    break;
                                } //End if
                                else {
                                    rnd = generator.nextInt(4)+32;
                                    if (evalPeg[rnd].color == 6) {
                                        evalPeg[rnd].color = 1;
                                        evalCount++;
                                    } //End if
                                    else {
                                        i--;
                                    } //End else
                                } //End else
                            } //End for
                            break;

                        case 10:
                            for (int i=0; i<redCount; i++) {
                                rnd = generator.nextInt(4)+36;
                                if (evalPeg[rnd].color == 6) {
                                    evalPeg[rnd].color = 0;
                                    evalCount++;
                                } //End if
                                else {
                                    i--;
                                } //End else
                            } //End for

                            for (int i=0; i<whiteCount; i++) {
                                if (evalCount == 4) {
                                    break;
                                } //End if
                                else {
                                    rnd = generator.nextInt(4)+36;
                                    if (evalPeg[rnd].color == 6) {
                                        evalPeg[rnd].color = 1;
                                        evalCount++;
                                    } //End if
                                    else {
                                        i--;
                                    } //End else
                                } //End else
                            } //End for
                            break;

                        default:
                            break;
                    } //End switch

                    guessRow++;

                    switch (guessRow) {
                        case 1: guessNum.setText("1"); break;
                        case 2: guessNum.setText("2"); break;
                        case 3: guessNum.setText("3"); break;
                        case 4: guessNum.setText("4"); break;
                        case 5: guessNum.setText("5"); break;
                        case 6: guessNum.setText("6"); break;
                        case 7: guessNum.setText("7"); break;
                        case 8: guessNum.setText("8"); break;
                        case 9: guessNum.setText("9"); break;
                        case 10: guessNum.setText("10"); break;
                        default: break;
                    } //End switch

                    if (redCount == 4) {
                        JOptionPane.showMessageDialog(com.steve.mastermind.Mastermind.frame, "Congratulations, You Won!");
                        start = false;
                        blue.setEnabled(false);
                        red.setEnabled(false);
                        orange.setEnabled(false);
                        green.setEnabled(false);
                        yellow.setEnabled(false);
                        white.setEnabled(false);
                        guess.setEnabled(false);
                        newGame.setText("New Game");
                    } //End if
                    else {
                        if (guessRow == 11) {
                            JOptionPane.showMessageDialog(com.steve.mastermind.Mastermind.frame, "<html><body><center>The Answer Was:<br>"+peg1+", "+peg2+", "+peg3+", "+peg4+"<br><br>Better Luck Next Time!</center></body></html>");
                            start = false;
                            blue.setEnabled(false);
                            red.setEnabled(false);
                            orange.setEnabled(false);
                            green.setEnabled(false);
                            yellow.setEnabled(false);
                            white.setEnabled(false);
                            guess.setEnabled(false);
                            newGame.setText("New Game");
                        } //End if
                    } //End else

                } //End if
                else {
                    JOptionPane.showMessageDialog(com.steve.mastermind.Mastermind.frame, "Please Select A Color For All Guess Pegs!");
                } //End else

            } //End if
            repaint();
        } //End method
    } //End class

    //Mouse Handler
    private class MouseHandler implements MouseListener {

        public void mouseExited( MouseEvent event ) {}   //
        public void mouseEntered( MouseEvent event ) {}  // Abstract methods needed
        public void mouseReleased( MouseEvent event ) {} //
        public void mousePressed( MouseEvent event ) {}  //

        public void mouseClicked(MouseEvent event) {
            if (start) {
                switch (guessRow) {
                    case 1:
                        if (event.getY() < (com.steve.mastermind.Mastermind.frame.getHeight()/2)+200 && event.getY() > (com.steve.mastermind.Mastermind.frame.getHeight()/2)+160) {
                            if (event.getX() < com.steve.mastermind.Mastermind.frame.getWidth()/8 && event.getX() > (com.steve.mastermind.Mastermind.frame.getWidth()/16)-15) {
                                guessPeg[0].color = color;
                            } //End if
                            if (event.getX() < (com.steve.mastermind.Mastermind.frame.getWidth()/4)-30 && event.getX() > com.steve.mastermind.Mastermind.frame.getWidth()/8) {
                                guessPeg[1].color = color;
                            } //End if
                            if (event.getX() < com.steve.mastermind.Mastermind.frame.getWidth()/4 && event.getX() > (com.steve.mastermind.Mastermind.frame.getWidth()/4)-30) {
                                guessPeg[2].color = color;
                            } //End if
                            if (event.getX() < com.steve.mastermind.Mastermind.frame.getWidth()/3 && event.getX() > com.steve.mastermind.Mastermind.frame.getWidth()/4) {
                                guessPeg[3].color = color;
                            } //End if
                        } //End if
                        break;
                    case 2:
                        if (event.getY() < (com.steve.mastermind.Mastermind.frame.getHeight()/2)+160 && event.getY() > (com.steve.mastermind.Mastermind.frame.getHeight()/2)+120) {
                            if (event.getX() < com.steve.mastermind.Mastermind.frame.getWidth()/8 && event.getX() > (com.steve.mastermind.Mastermind.frame.getWidth()/16)-15) {
                                guessPeg[4].color = color;
                            } //End if
                            if (event.getX() < (com.steve.mastermind.Mastermind.frame.getWidth()/4)-30 && event.getX() > com.steve.mastermind.Mastermind.frame.getWidth()/8) {
                                guessPeg[5].color = color;
                            } //End if
                            if (event.getX() < com.steve.mastermind.Mastermind.frame.getWidth()/4 && event.getX() > (com.steve.mastermind.Mastermind.frame.getWidth()/4)-30) {
                                guessPeg[6].color = color;
                            } //End if
                            if (event.getX() < com.steve.mastermind.Mastermind.frame.getWidth()/3 && event.getX() > com.steve.mastermind.Mastermind.frame.getWidth()/4) {
                                guessPeg[7].color = color;
                            } //End if
                        } //End if
                        break;
                    case 3:
                        if (event.getY() < (com.steve.mastermind.Mastermind.frame.getHeight()/2)+120 && event.getY() > (com.steve.mastermind.Mastermind.frame.getHeight()/2)+80) {
                            if (event.getX() < com.steve.mastermind.Mastermind.frame.getWidth()/8 && event.getX() > (com.steve.mastermind.Mastermind.frame.getWidth()/16)-15) {
                                guessPeg[8].color = color;
                            } //End if
                            if (event.getX() < (com.steve.mastermind.Mastermind.frame.getWidth()/4)-30 && event.getX() > com.steve.mastermind.Mastermind.frame.getWidth()/8) {
                                guessPeg[9].color = color;
                            } //End if
                            if (event.getX() < com.steve.mastermind.Mastermind.frame.getWidth()/4 && event.getX() > (com.steve.mastermind.Mastermind.frame.getWidth()/4)-30) {
                                guessPeg[10].color = color;
                            } //End if
                            if (event.getX() < com.steve.mastermind.Mastermind.frame.getWidth()/3 && event.getX() > com.steve.mastermind.Mastermind.frame.getWidth()/4) {
                                guessPeg[11].color = color;
                            } //End if
                        } //End if
                        break;
                    case 4:
                        if (event.getY() < (com.steve.mastermind.Mastermind.frame.getHeight()/2)+80 && event.getY() > (com.steve.mastermind.Mastermind.frame.getHeight()/2)+40) {
                            if (event.getX() < com.steve.mastermind.Mastermind.frame.getWidth()/8 && event.getX() > (com.steve.mastermind.Mastermind.frame.getWidth()/16)-15) {
                                guessPeg[12].color = color;
                            } //End if
                            if (event.getX() < (com.steve.mastermind.Mastermind.frame.getWidth()/4)-30 && event.getX() > com.steve.mastermind.Mastermind.frame.getWidth()/8) {
                                guessPeg[13].color = color;
                            } //End if
                            if (event.getX() < com.steve.mastermind.Mastermind.frame.getWidth()/4 && event.getX() > (com.steve.mastermind.Mastermind.frame.getWidth()/4)-30) {
                                guessPeg[14].color = color;
                            } //End if
                            if (event.getX() < com.steve.mastermind.Mastermind.frame.getWidth()/3 && event.getX() > com.steve.mastermind.Mastermind.frame.getWidth()/4) {
                                guessPeg[15].color = color;
                            } //End if
                        } //End if
                        break;
                    case 5:
                        if (event.getY() < (com.steve.mastermind.Mastermind.frame.getHeight()/2)+40 && event.getY() > (com.steve.mastermind.Mastermind.frame.getHeight()/2)) {
                            if (event.getX() < com.steve.mastermind.Mastermind.frame.getWidth()/8 && event.getX() > (com.steve.mastermind.Mastermind.frame.getWidth()/16)-15) {
                                guessPeg[16].color = color;
                            } //End if
                            if (event.getX() < (com.steve.mastermind.Mastermind.frame.getWidth()/4)-30 && event.getX() > com.steve.mastermind.Mastermind.frame.getWidth()/8) {
                                guessPeg[17].color = color;
                            } //End if
                            if (event.getX() < com.steve.mastermind.Mastermind.frame.getWidth()/4 && event.getX() > (com.steve.mastermind.Mastermind.frame.getWidth()/4)-30) {
                                guessPeg[18].color = color;
                            } //End if
                            if (event.getX() < com.steve.mastermind.Mastermind.frame.getWidth()/3 && event.getX() > com.steve.mastermind.Mastermind.frame.getWidth()/4) {
                                guessPeg[19].color = color;
                            } //End if
                        } //End if
                        break;
                    case 6:
                        if (event.getY() < (com.steve.mastermind.Mastermind.frame.getHeight()/2) && event.getY() > (com.steve.mastermind.Mastermind.frame.getHeight()/2)-40) {
                            if (event.getX() < com.steve.mastermind.Mastermind.frame.getWidth()/8 && event.getX() > (com.steve.mastermind.Mastermind.frame.getWidth()/16)-15) {
                                guessPeg[20].color = color;
                            } //End if
                            if (event.getX() < (com.steve.mastermind.Mastermind.frame.getWidth()/4)-30 && event.getX() > com.steve.mastermind.Mastermind.frame.getWidth()/8) {
                                guessPeg[21].color = color;
                            } //End if
                            if (event.getX() < com.steve.mastermind.Mastermind.frame.getWidth()/4 && event.getX() > (com.steve.mastermind.Mastermind.frame.getWidth()/4)-30) {
                                guessPeg[22].color = color;
                            } //End if
                            if (event.getX() < com.steve.mastermind.Mastermind.frame.getWidth()/3 && event.getX() > com.steve.mastermind.Mastermind.frame.getWidth()/4) {
                                guessPeg[23].color = color;
                            } //End if
                        } //End if
                        break;
                    case 7:
                        if (event.getY() < (com.steve.mastermind.Mastermind.frame.getHeight()/2)-40 && event.getY() > (com.steve.mastermind.Mastermind.frame.getHeight()/2)-80) {
                            if (event.getX() < com.steve.mastermind.Mastermind.frame.getWidth()/8 && event.getX() > (com.steve.mastermind.Mastermind.frame.getWidth()/16)-15) {
                                guessPeg[24].color = color;
                            } //End if
                            if (event.getX() < (com.steve.mastermind.Mastermind.frame.getWidth()/4)-30 && event.getX() > com.steve.mastermind.Mastermind.frame.getWidth()/8) {
                                guessPeg[25].color = color;
                            } //End if
                            if (event.getX() < com.steve.mastermind.Mastermind.frame.getWidth()/4 && event.getX() > (com.steve.mastermind.Mastermind.frame.getWidth()/4)-30) {
                                guessPeg[26].color = color;
                            } //End if
                            if (event.getX() < com.steve.mastermind.Mastermind.frame.getWidth()/3 && event.getX() > com.steve.mastermind.Mastermind.frame.getWidth()/4) {
                                guessPeg[27].color = color;
                            } //End if
                        } //End if
                        break;
                    case 8:
                        if (event.getY() < (com.steve.mastermind.Mastermind.frame.getHeight()/2)-80 && event.getY() > (com.steve.mastermind.Mastermind.frame.getHeight()/2)-120) {
                            if (event.getX() < com.steve.mastermind.Mastermind.frame.getWidth()/8 && event.getX() > (com.steve.mastermind.Mastermind.frame.getWidth()/16)-15) {
                                guessPeg[28].color = color;
                            } //End if
                            if (event.getX() < (com.steve.mastermind.Mastermind.frame.getWidth()/4)-30 && event.getX() > com.steve.mastermind.Mastermind.frame.getWidth()/8) {
                                guessPeg[29].color = color;
                            } //End if
                            if (event.getX() < com.steve.mastermind.Mastermind.frame.getWidth()/4 && event.getX() > (com.steve.mastermind.Mastermind.frame.getWidth()/4)-30) {
                                guessPeg[30].color = color;
                            } //End if
                            if (event.getX() < com.steve.mastermind.Mastermind.frame.getWidth()/3 && event.getX() > com.steve.mastermind.Mastermind.frame.getWidth()/4) {
                                guessPeg[31].color = color;
                            } //End if
                        } //End if
                        break;
                    case 9:
                        if (event.getY() < (com.steve.mastermind.Mastermind.frame.getHeight()/2)-120 && event.getY() > (com.steve.mastermind.Mastermind.frame.getHeight()/2)-160) {
                            if (event.getX() < com.steve.mastermind.Mastermind.frame.getWidth()/8 && event.getX() > (com.steve.mastermind.Mastermind.frame.getWidth()/16)-15) {
                                guessPeg[32].color = color;
                            } //End if
                            if (event.getX() < (com.steve.mastermind.Mastermind.frame.getWidth()/4)-30 && event.getX() > com.steve.mastermind.Mastermind.frame.getWidth()/8) {
                                guessPeg[33].color = color;
                            } //End if
                            if (event.getX() < com.steve.mastermind.Mastermind.frame.getWidth()/4 && event.getX() > (com.steve.mastermind.Mastermind.frame.getWidth()/4)-30) {
                                guessPeg[34].color = color;
                            } //End if
                            if (event.getX() < com.steve.mastermind.Mastermind.frame.getWidth()/3 && event.getX() > com.steve.mastermind.Mastermind.frame.getWidth()/4) {
                                guessPeg[35].color = color;
                            } //End if
                        } //End if
                        break;
                    case 10:
                        if (event.getY() < (com.steve.mastermind.Mastermind.frame.getHeight()/2)-160 && event.getY() > (com.steve.mastermind.Mastermind.frame.getHeight()/2)-200) {
                            if (event.getX() < com.steve.mastermind.Mastermind.frame.getWidth()/8 && event.getX() > (com.steve.mastermind.Mastermind.frame.getWidth()/16)-15) {
                                guessPeg[36].color = color;
                            } //End if
                            if (event.getX() < (com.steve.mastermind.Mastermind.frame.getWidth()/4)-30 && event.getX() > com.steve.mastermind.Mastermind.frame.getWidth()/8) {
                                guessPeg[37].color = color;
                            } //End if
                            if (event.getX() < com.steve.mastermind.Mastermind.frame.getWidth()/4 && event.getX() > (com.steve.mastermind.Mastermind.frame.getWidth()/4)-30) {
                                guessPeg[38].color = color;
                            } //End if
                            if (event.getX() < com.steve.mastermind.Mastermind.frame.getWidth()/3 && event.getX() > com.steve.mastermind.Mastermind.frame.getWidth()/4) {
                                guessPeg[39].color = color;
                            } //End if
                        } //End if
                        break;
                    default: break;
                } //End switch
            } //End if

            repaint();

        } //End method
    } // End class

    //Drawing Method
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        //Draw Board
        g2d.setPaint(Color.BLACK);
        g2d.setStroke(new BasicStroke(4));
        g2d.draw(new Line2D.Double(10.0, 20.0, 10.0, 420.0));
        g2d.draw(new Line2D.Double(10.0, 420.0, 380.0, 420.0));
        g2d.draw(new Line2D.Double(380.0, 420.0, 380.0, 20.0));
        g2d.draw(new Line2D.Double(380.0, 20.0, 10.0, 20.0));

        //Draw Rows
        g2d.draw(new Line2D.Double(10.0, 60.0, 380.0, 60.0));
        g2d.draw(new Line2D.Double(10.0, 100.0, 380.0, 100.0));
        g2d.draw(new Line2D.Double(10.0, 140.0, 380.0, 140.0));
        g2d.draw(new Line2D.Double(10.0, 180.0, 380.0, 180.0));
        g2d.draw(new Line2D.Double(10.0, 220.0, 380.0, 220.0));
        g2d.draw(new Line2D.Double(10.0, 260.0, 380.0, 260.0));
        g2d.draw(new Line2D.Double(10.0, 300.0, 380.0, 300.0));
        g2d.draw(new Line2D.Double(10.0, 340.0, 380.0, 340.0));
        g2d.draw(new Line2D.Double(10.0, 380.0, 380.0, 380.0));

        //Draw Divider
        g2d.draw(new Line2D.Double(195.0, 20.0, 195.0, 420.0));

        //Mouse range test
        g2d.setPaint(Color.RED);
        //g2d.draw(new Line2D.Double(com.steve.mastermind.Mastermind.frame.getWidth()/2, 20, com.steve.mastermind.Mastermind.frame.getWidth()/2, 420));
        //g2d.draw(new Line2D.Double(com.steve.mastermind.Mastermind.frame.getWidth()/3, 20, com.steve.mastermind.Mastermind.frame.getWidth()/3, 420));
        //g2d.draw(new Line2D.Double(com.steve.mastermind.Mastermind.frame.getWidth()/4, 20, com.steve.mastermind.Mastermind.frame.getWidth()/4, 420));
        //g2d.draw(new Line2D.Double((com.steve.mastermind.Mastermind.frame.getWidth()/4)-30, 20, (com.steve.mastermind.Mastermind.frame.getWidth()/4)-30, 420));

        //Draw Guess Pegs
        for (int i=0; i<guessPeg.length; i++) {
            switch (guessPeg[i].color) {
                case 0: g2d.setPaint(Color.BLUE); break;
                case 1: g2d.setPaint(Color.RED); break;
                case 2: g2d.setPaint(Color.ORANGE); break;
                case 3: g2d.setPaint(Color.GREEN); break;
                case 4: g2d.setPaint(Color.YELLOW); break;
                case 5: g2d.setPaint(Color.WHITE); break;
                default: g2d.setPaint(Color.BLACK); break;
            } //End switch
            g2d.fillOval(guessPeg[i].x,guessPeg[i].y,20,20);
        } //End for

        //Draw Eval Pegs
        for (int i=0; i<evalPeg.length; i++) {
            switch (evalPeg[i].color) {
                case 0: g2d.setPaint(Color.RED); break;
                case 1: g2d.setPaint(Color.WHITE); break;
                default: g2d.setPaint(Color.BLACK); break;
            } //End switch
            g2d.fillOval(evalPeg[i].x,evalPeg[i].y,20,20);
        } //End for

    } //End method

} //End class
