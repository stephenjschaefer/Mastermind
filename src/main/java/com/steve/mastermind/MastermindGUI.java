/**
 * Project: Mastermind
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

class MastermindGUI extends JPanel {

    // Declarations
    private final JButton blue = new JButton("Blue");
    private final JButton red = new JButton("Red");
    private final JButton orange = new JButton("Orange");
    private final JButton green = new JButton("Green");
    private final JButton yellow = new JButton("Yellow");
    private final JButton white = new JButton("White");
    private final JButton guess = new JButton("Make Guess");
    private final JButton newGame = new JButton("New Game");
    private final JMenuItem exitItem = new JMenuItem("Exit");
    private final JMenuItem newGameItem = new JMenuItem("New Game");
    private final JMenuItem endGameItem = new JMenuItem("Give Up");
    private final JMenuItem rulesItem = new JMenuItem("Rules");
    private final JMenuItem aboutItem = new JMenuItem("About");
    private int guessRow = 1;
    private final JLabel guessNum = new JLabel("0");
    private final JLabel selectedColor = new JLabel("----------");
    private int color = 6;
    private final Peg[] guessPeg = new Peg[40];
    private final Peg[] evalPeg = new Peg[40];
    private final Peg[] answerPeg = new Peg[4];
    private final Random generator = new Random();
    private Boolean start = false;
    private int rnd = 0;
    private int evalCount = 0;
    private JLabel guessLabel1 = new JLabel("Guess #1");
    private JLabel guessLabel2 = new JLabel("Guess #2");
    private JLabel guessLabel3 = new JLabel("Guess #3");
    private JLabel guessLabel4 = new JLabel("Guess #4");
    private JLabel guessLabel5 = new JLabel("Guess #5");
    private JLabel guessLabel6 = new JLabel("Guess #6");
    private JLabel guessLabel7 = new JLabel("Guess #7");
    private JLabel guessLabel8 = new JLabel("Guess #8");
    private JLabel guessLabel9 = new JLabel("Guess #9");
    private JLabel guessLabel10 = new JLabel("Guess #10");
    private JLabel[] guessLabelArray = {guessLabel1, guessLabel2, guessLabel3, guessLabel4, guessLabel5, guessLabel6, guessLabel7, guessLabel8, guessLabel9, guessLabel10};

    public MastermindGUI() {

        //Handlers
        ButtonHandler buttonHandle = new ButtonHandler();
        Mastermind.frame.addMouseListener(new MouseHandler());

        //Add button handler
        blue.addActionListener(buttonHandle);
        red.addActionListener(buttonHandle);
        orange.addActionListener(buttonHandle);
        green.addActionListener(buttonHandle);
        yellow.addActionListener(buttonHandle);
        white.addActionListener(buttonHandle);
        newGame.addActionListener(buttonHandle);
        guess.addActionListener(buttonHandle);

        //Disable Buttons Initially
        blue.setEnabled(false);
        red.setEnabled(false);
        orange.setEnabled(false);
        green.setEnabled(false);
        yellow.setEnabled(false);
        white.setEnabled(false);
        guess.setEnabled(false);

        //Menu Bar
        JMenuBar menuBar = new JMenuBar();

        //Menus
        JMenu fileMenu = new JMenu("File");
        JMenu gameMenu = new JMenu("Game");
        JMenu helpMenu = new JMenu("Help");

        //Add Menu Items To Menus
        fileMenu.add(exitItem);
        gameMenu.add(newGameItem);
        gameMenu.add(endGameItem);
        gameMenu.addSeparator();
        gameMenu.add(rulesItem);
        helpMenu.add(aboutItem);

        //Add Menus to Menu Bar
        menuBar.add(fileMenu);
        menuBar.add(gameMenu);
        menuBar.add(helpMenu);

        //Set Menu Item Hotkeys
        exitItem.setAccelerator(KeyStroke.getKeyStroke('Q', Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        newGameItem.setAccelerator(KeyStroke.getKeyStroke('N', Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        endGameItem.setAccelerator(KeyStroke.getKeyStroke('G', Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        rulesItem.setAccelerator(KeyStroke.getKeyStroke('R', Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        aboutItem.setAccelerator(KeyStroke.getKeyStroke('A', Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));

        //Add Menu Item Action Listener
        exitItem.addActionListener(buttonHandle);
        newGameItem.addActionListener(buttonHandle);
        endGameItem.addActionListener(buttonHandle);
        rulesItem.addActionListener(buttonHandle);
        aboutItem.addActionListener(buttonHandle);

        //Set Game Menu Item Initial State
        newGameItem.setEnabled(true);
        endGameItem.setEnabled(false);

        //ColorSelect Panel
        JPanel colorSelect = new JPanel();

        //Add buttons
        colorSelect.setLayout(new FlowLayout());
        colorSelect.add(blue);
        colorSelect.add(red);
        colorSelect.add(orange);
        colorSelect.add(green);
        colorSelect.add(yellow);
        colorSelect.add(white);

        //leftSideBar Panel
        JPanel leftSideBar = new JPanel();
        leftSideBar.setLayout(new BoxLayout(leftSideBar, BoxLayout.Y_AXIS));

        //Add elements
        leftSideBar.add(Box.createRigidArea(new Dimension(30,30)));
        leftSideBar.add(guessLabel10);
        guessLabel10.setHorizontalAlignment(JLabel.CENTER);
        leftSideBar.add(Box.createRigidArea(new Dimension(30,25)));
        leftSideBar.add(guessLabel9);
        guessLabel9.setHorizontalAlignment(JLabel.CENTER);
        leftSideBar.add(Box.createRigidArea(new Dimension(30,24)));
        leftSideBar.add(guessLabel8);
        guessLabel8.setHorizontalAlignment(JLabel.CENTER);
        leftSideBar.add(Box.createRigidArea(new Dimension(30,25)));
        leftSideBar.add(guessLabel7);
        guessLabel7.setHorizontalAlignment(JLabel.CENTER);
        leftSideBar.add(Box.createRigidArea(new Dimension(30,24)));
        leftSideBar.add(guessLabel6);
        guessLabel6.setHorizontalAlignment(JLabel.CENTER);
        leftSideBar.add(Box.createRigidArea(new Dimension(30,24)));
        leftSideBar.add(guessLabel5);
        guessLabel5.setHorizontalAlignment(JLabel.CENTER);
        leftSideBar.add(Box.createRigidArea(new Dimension(30,24)));
        leftSideBar.add(guessLabel4);
        guessLabel4.setHorizontalAlignment(JLabel.CENTER);
        leftSideBar.add(Box.createRigidArea(new Dimension(30,25)));
        leftSideBar.add(guessLabel3);
        guessLabel3.setHorizontalAlignment(JLabel.CENTER);
        leftSideBar.add(Box.createRigidArea(new Dimension(30,24)));
        leftSideBar.add(guessLabel2);
        guessLabel2.setHorizontalAlignment(JLabel.CENTER);
        leftSideBar.add(Box.createRigidArea(new Dimension(30,25)));
        leftSideBar.add(guessLabel1);
        guessLabel1.setHorizontalAlignment(JLabel.CENTER);

        //rightSideBar Panel
        JPanel rightSideBar = new JPanel();
        rightSideBar.setLayout(new BoxLayout(rightSideBar, BoxLayout.Y_AXIS));

        //Add elements
        rightSideBar.add(Box.createRigidArea(new Dimension(132,18)));
        rightSideBar.add(newGame);
        rightSideBar.add(Box.createRigidArea(new Dimension(132,125)));
        JLabel currentGuess = new JLabel("Current Guess:");
        rightSideBar.add(currentGuess);
        rightSideBar.add(guessNum);
        rightSideBar.add(Box.createRigidArea(new Dimension(132,20)));
        JLabel currentColor = new JLabel("Current Color:");
        rightSideBar.add(currentColor);
        rightSideBar.add(selectedColor);
        rightSideBar.add(Box.createRigidArea(new Dimension(132,140)));
        rightSideBar.add(guess);

        //Set element attributes
        newGame.setAlignmentX(Component.CENTER_ALIGNMENT);
        guess.setAlignmentX(Component.CENTER_ALIGNMENT);
        currentGuess.setAlignmentX(Component.CENTER_ALIGNMENT);
        guessNum.setAlignmentX(Component.CENTER_ALIGNMENT);
        currentColor.setAlignmentX(Component.CENTER_ALIGNMENT);
        selectedColor.setAlignmentX(Component.CENTER_ALIGNMENT);

        //Set Menu Bar and Add All Panels
        Mastermind.frame.setJMenuBar(menuBar);
        Mastermind.frame.add(colorSelect, BorderLayout.SOUTH);
        Mastermind.frame.add(leftSideBar, BorderLayout.WEST);
        Mastermind.frame.add(rightSideBar, BorderLayout.EAST);
        Mastermind.frame.add(this, BorderLayout.CENTER);

        //Initialize Guess Peg Array
        int pegCount = 0;
        int[] guessY = {390, 350, 310, 270, 230, 190, 150, 110, 70, 30};
        for (int aGuessY : guessY) {
            int[] guessX = {20, 60, 100, 140};
            for (int aGuessX : guessX) {
                guessPeg[pegCount++] = new Peg(aGuessX, aGuessY, 6);
            } //End for
        } //End for

        //Initialize Eval Peg Array
        pegCount = 0;
        int[] evalY = {390, 350, 310, 270, 230, 190, 150, 110, 70, 30};
        for (int anEvalY : evalY) {
            int[] evalX = {230, 270, 310, 350};
            for (int anEvalX : evalX) {
                evalPeg[pegCount++] = new Peg(anEvalX, anEvalY, 6);
            } //End for
        } //End for

    } //End constructor

    // Button handler to switch modes
    private class ButtonHandler implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            if(event.getSource() == blue && start) {
                selectedColor.setText("Blue");
                selectedColor.setForeground(Color.BLUE);
                color = 0;
            } //End if
            if(event.getSource() == red && start) {
                selectedColor.setText("Red");
                selectedColor.setForeground(Color.RED);
                color = 1;
            } //End if
            if(event.getSource() == orange && start) {
                selectedColor.setText("Orange");
                selectedColor.setForeground(Color.ORANGE);
                color = 2;
            } //End if
            if(event.getSource() == green && start) {
                selectedColor.setText("Green");
                selectedColor.setForeground(Color.GREEN);
                color = 3;
            } //End if
            if(event.getSource() == yellow && start) {
                selectedColor.setText("Yellow");
                selectedColor.setForeground(Color.YELLOW);
                color = 4;
            } //End if
            if(event.getSource() == white && start) {
                selectedColor.setText("White");
                selectedColor.setForeground(Color.WHITE);
                color = 5;
            } //End if
            if(event.getSource() == newGame || event.getSource() == newGameItem) {
                if (newGame.getText().equals("New Game")) {
                    for (int i = 0; i < 40; i++) {
                        guessPeg[i].color = 6;
                        evalPeg[i].color = 6;
                    } //End for
                    guessRow = 1;
                    guessNum.setText("1");
                    color = 6;
                    selectedColor.setText("----------");
                    selectedColor.setForeground(Color.BLACK);

                    // Difficulty Setting Prompt
                    Object[] options = {"Easy", "Medium", "Hard"};
                    int diffSetting = JOptionPane.showOptionDialog(Mastermind.frame, "Select Your Difficulty Level", "Difficulty Setting", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);

                    //Reset Buttons
                    blue.setEnabled(true);
                    red.setEnabled(true);
                    orange.setEnabled(true);
                    green.setEnabled(true);
                    yellow.setEnabled(true);
                    white.setEnabled(true);
                    guess.setEnabled(true);

                    //Set Game Menu Item State
                    newGameItem.setEnabled(false);
                    endGameItem.setEnabled(true);

                    //Update GUI For New Game
                    updateGuessLabel(guessRow);
                    newGame.setText("Give Up?");

                    //Adjust Based On Difficulty Setting
                    int diffLevel;
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

                    //Initialize Answer Pegs And Answer String
                    for (int i = 0; i < 4; i++) {
                        answerPeg[i] = new Peg(0, 0, generator.nextInt(2 * diffLevel));
                    } //End for

                    //Start Game
                    start = true;
                } //End if
                else {
                    if (newGame.getText().equals("Give Up?")) {
                        endGame();
                    } //End if
                } //End else
            }
            if (event.getSource() == endGameItem) {
                endGame();
            } //End if
            if (event.getSource() == exitItem) {
                System.exit(0);
            } //End if
            if (event.getSource() == rulesItem) {
                JOptionPane.showOptionDialog(Mastermind.frame,  "Flow of Gameplay:\n" +
                                                                "  Click \"New Game\" to start a new game.\n" +
                                                                "  Select your difficulty level.\n" +
                                                                "  Select a guess color from the available colors.\n" +
                                                                "  Place selected color in desired empty location.\n" +
                                                                "  When ready click \"Make Guess\"\n" +
                                                                "  Use the Master's response to make your next guess.\n\n" +
                                                                "Evaluation of Your Guess:\n" +
                                                                "  Red: correct color and location.\n" +
                                                                "  White: correct color but incorrect location.\n\n" +
                                                                "The Master's response peg placement is random!",
                                                                "Rules", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);

            } //End if
            if (event.getSource() == aboutItem) {
                JOptionPane.showOptionDialog(Mastermind.frame, "             Mastermind\n" +
                                                               "          Version 1.0.0.0",
                                                               "About", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);
            } //End if

            if(event.getSource() == guess && start) {
                Boolean validGuess = true;

                //Refactored Valid Guess Logic
                for (int i=((guessRow-1)*4); i<(guessRow*4); i++) {
                    if (guessPeg[i].color == 6) {
                        validGuess = false;
                    } //End if
                } //End fo

                //Perform Evaluation
                if (validGuess) {
                    for (int i=0; i<4; i++) {
                        answerPeg[i].tagged = false;
                    } //End for
                    int redCount = 0;
                    int whiteCount = 0;

                    //Refactored Guess Evaluation Logic
                    for (int i=(guessRow*4)-4; i<(guessRow*4); i++) {
                        for (int j=0; j<4; j++) {
                            if (guessPeg[i].color == answerPeg[i%4].color) {
                                redCount++;
                                if (answerPeg[i%4].tagged) {
                                    whiteCount--;
                                } //End if
                                answerPeg[i%4].tagged = true;
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

                    //Refactored Set Evaluation Pegs Logic
                    evalCount = 0;
                    for (int i = 0; i< redCount; i++) {
                        rnd = generator.nextInt(4);
                        if (evalPeg[rnd+((guessRow-1)*4)].color == 6) {
                            evalPeg[rnd+((guessRow-1)*4)].color = 0;
                            evalCount++;
                        } //End if
                        else {
                            i--;
                        } //End else
                    } //End for

                    for (int i = 0; i< whiteCount; i++) {
                        if (evalCount == 4) {
                            break;
                        } //End if
                        else {
                            rnd = generator.nextInt(4);
                            if (evalPeg[rnd+((guessRow-1)*4)].color == 6) {
                                evalPeg[rnd+((guessRow-1)*4)].color = 1;
                                evalCount++;
                            } //End if
                            else {
                                i--;
                            } //End else
                        } //End else
                    } //End for

                    //Increment Guess Row Count
                    guessRow++;

                    if (redCount == 4) {
                        repaint();
                        winGame();
                    } //End if
                    else {
                        if (guessRow == 11) {
                            repaint();
                            endGame();
                        } //End if
                    } //End else

                    if (guessRow < 11 && redCount < 4) {
                        //Update Guess Number Labels
                        updateGuessLabel(guessRow);

                        //Refactored Set Guess Number Logic
                        guessNum.setText(guessRow + "");
                    }

                } //End if
                else {
                    JOptionPane.showOptionDialog(Mastermind.frame, "Please Select A Color For All Guess Pegs!", "Invalid Guess", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, null, null);
                } //End else

            } //End if
            repaint();
        } //End method
    } //End class

    //End Game
    private void endGame() {
        JOptionPane.showMessageDialog(Mastermind.frame, "<html><body><center>The Answer Was:<br>"+answerPeg[0].colorStr+", "+answerPeg[1].colorStr+", "+answerPeg[2].colorStr+", "+answerPeg[3].colorStr+"<br><br>Better Luck Next Time!</center></body></html>", "You Lose!", JOptionPane.INFORMATION_MESSAGE);
        start = false;
        blue.setEnabled(false);
        red.setEnabled(false);
        orange.setEnabled(false);
        green.setEnabled(false);
        yellow.setEnabled(false);
        white.setEnabled(false);
        guess.setEnabled(false);
        newGame.setText("New Game");
        newGameItem.setEnabled(true);
        endGameItem.setEnabled(false);
    }

    private void winGame() {
        JOptionPane.showMessageDialog(Mastermind.frame, "<html><body><center>The Answer Was:<br>"+answerPeg[0].colorStr+", "+answerPeg[1].colorStr+", "+answerPeg[2].colorStr+", "+answerPeg[3].colorStr+"<br><br>Congratulations, You Won!</center></body></html>", "You Win!", JOptionPane.INFORMATION_MESSAGE);
        start = false;
        blue.setEnabled(false);
        red.setEnabled(false);
        orange.setEnabled(false);
        green.setEnabled(false);
        yellow.setEnabled(false);
        white.setEnabled(false);
        guess.setEnabled(false);
        newGame.setText("New Game");
        newGameItem.setEnabled(true);
        endGameItem.setEnabled(false);
    }

    private void updateGuessLabel(int guessRow) {
        if (guessRow == 1) {
            for (int i=0; i<guessLabelArray.length; i++) {
                guessLabelArray[i].setForeground(Color.BLACK);
            }
            guessLabelArray[guessRow-1].setForeground(Color.BLUE);
        } else {
            guessLabelArray[guessRow-1].setForeground(Color.BLUE);
            guessLabelArray[guessRow-2].setForeground(Color.BLACK);
        }
    }

    //Mouse Handler
    private class MouseHandler implements MouseListener {

        public void mouseExited( MouseEvent event ) {}   //
        public void mouseEntered( MouseEvent event ) {}  // Abstract methods needed
        public void mouseReleased( MouseEvent event ) {} //
        public void mousePressed( MouseEvent event ) {}  //

        public void mouseClicked(MouseEvent event) {
            if (start) {
                //Refactored Mouse Click Event Handler Logic
                if (event.getY() > (Mastermind.frame.getHeight()-65-(40*(guessRow))) && event.getY() < (Mastermind.frame.getHeight()-65-(40*(guessRow-1)))) {
                    if (event.getX() > 100 && event.getX() < 122) {
                        guessPeg[(guessRow-1)+(3*(guessRow-1))].color = color;
                    } //End if
                    if (event.getX() > 140 && event.getX() < 162) {
                        guessPeg[guessRow-1+1+(3*(guessRow-1))].color = color;
                    } //End if
                    if (event.getX() > 180 && event.getX() < 202) {
                        guessPeg[guessRow-1+2+(3*(guessRow-1))].color = color;
                    } //End if
                    if (event.getX() > 220 && event.getX() < 242) {
                        guessPeg[guessRow-1+3+(3*(guessRow-1))].color = color;
                    } //End if
                } //End if
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

        //Draw Guess Pegs
        for (Peg aGuessPeg : guessPeg) {
            switch (aGuessPeg.color) {
                case 0:
                    g2d.setPaint(Color.BLUE);
                    break;
                case 1:
                    g2d.setPaint(Color.RED);
                    break;
                case 2:
                    g2d.setPaint(Color.ORANGE);
                    break;
                case 3:
                    g2d.setPaint(Color.GREEN);
                    break;
                case 4:
                    g2d.setPaint(Color.YELLOW);
                    break;
                case 5:
                    g2d.setPaint(Color.WHITE);
                    break;
                default:
                    g2d.setPaint(Color.BLACK);
                    break;
            } //End switch
            g2d.fillOval(aGuessPeg.x, aGuessPeg.y, 20, 20);
        } //End for

        //Draw Eval Pegs
        for (Peg anEvalPeg : evalPeg) {
            switch (anEvalPeg.color) {
                case 0:
                    g2d.setPaint(Color.RED);
                    break;
                case 1:
                    g2d.setPaint(Color.WHITE);
                    break;
                default:
                    g2d.setPaint(Color.BLACK);
                    break;
            } //End switch
            g2d.fillOval(anEvalPeg.x, anEvalPeg.y, 20, 20);
        } //End for

    } //End method

} //End class
