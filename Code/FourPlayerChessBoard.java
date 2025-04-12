package Coursework;

import javax.swing.JFrame;
import javax.swing.*;
import java.awt.*;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FourPlayerChessBoard extends JFrame{
    private JPanel boardPanel;
    private JLabel currentPlayerLabel; // Used to display the player currently playing dice
    private JLabel diceResultLabel; // A label used to display the result of a die


    private int currentPlayer=0, diceValue, totalBoard=1;
    private boolean newDice = false;  //Determine whether to retry
   private int [] boardXPosition = new int[30];
   private int [] boardYPosition = new int[30];
    public int[] starX={1, 9, 15, 7};//star position
    public int[] starY={7, 1, 9, 15};
    public int[] squaresX = {2, 4, 6, 3, 5, 11, 13, 10, 12, 14};//card position
    public int[] squaresY = {6, 4, 2, 11, 13, 3, 5, 14, 12, 10};
    //Whether the grid has pieces: 0 is no one, 1 is player 1, and so on
    public int [] Grid = new int [30];
    private Image backgroundImage;
    //Draw the starting position of the chess
    ChessPiece piece1 = new ChessPiece("yellow",1);
    ChessPiece piece2 = new ChessPiece("blue",1);
    ChessPiece piece3 = new ChessPiece("red",1);
    ChessPiece piece4 = new ChessPiece("purple",1);


    public FourPlayerChessBoard() {

        this.setTitle("Fly Chess Game");
        this.setSize(2000, 1600);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null); // main
        loadImage();
        // Create a checkerboard panel
        boardPanel = new JPanel() {
           @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                drawBoard(g);
           }
        };
        boardPanel.setPreferredSize(new Dimension(600, 600));
        boardPanel.setBackground(Color.WHITE);

        // lay out
        setLayout(new BorderLayout());
        add(boardPanel, BorderLayout.CENTER);

        // Create the current gamertag
        currentPlayerLabel = new JLabel("Current player: -");
        currentPlayerLabel.setFont(new Font("Arial", Font.PLAIN, 40));
        add(currentPlayerLabel, BorderLayout.NORTH); // It is placed at the top of the interface
        currentPlayerLabel.setText("Current Player:" + (1 ));

        // Create a dice result display TAB
        diceResultLabel = new JLabel("Dice result: -");
        diceResultLabel.setFont(new Font("Arial", Font.PLAIN, 40));
        add(diceResultLabel, BorderLayout.SOUTH); // It is placed at the bottom of the screen

        // Set dice button
        JButton dice = new JButton("throw dice");
        dice.setFont(new Font("Arial", Font.PLAIN, 36));
        //dice.setPreferredSize(new Dimension(250, 100));
        dice.addActionListener(e -> rollDice());
        // Use the GridBagLayout layout manager
        GridBagLayout gridBagLayout = new GridBagLayout();
        boardPanel.setLayout(gridBagLayout);

        // Create a constraint object
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 0, 0);
        // Add buttons to the panel and apply constraints
        boardPanel.add(dice, gbc);
        setVisible(true);

    }
    private void loadImage() {
        ImageIcon icon = new ImageIcon("src\\Coursework\\Background.jpg");
        Image originalImage = icon.getImage();


        int windowWidth = getWidth();
        int windowHeight = getHeight();

        Image scaledImage = originalImage.getScaledInstance(windowWidth, windowHeight, Image.SCALE_SMOOTH);


        backgroundImage = new ImageIcon(scaledImage).getImage();
        if (backgroundImage == null) {
            System.out.println("Failed to load background image!");
        }
    }

    //Store the checkerboard grid position
    public void setPosition()
    {
       // add(boardPanel);
        int sideLength = 80; // The length of the lattice side, adjusted to 80
        int startX = 300; // Initial abscissa
        int startY = 50; // Initial ordinate
        int diagonal = 17; // The number of cells on the diagonal
       //left down
        int col=7,row=15;
       while (col >=1)
       {
           if (Math.abs(row - diagonal / 2) + Math.abs(col - diagonal / 2) == diagonal / 2)
           {
               boardXPosition[totalBoard]= startX + col* sideLength;
               boardYPosition[totalBoard]= startY + row * sideLength;
               totalBoard+=1;
           }
           col--;
           row--;
       }
       //left up
       col=1;
       row=7;
       while (col <= 7)
       {
           if (Math.abs(row - diagonal / 2) + Math.abs(col - diagonal / 2) == diagonal / 2)
           {
               boardXPosition[totalBoard]= startX + col* sideLength;
               boardYPosition[ totalBoard]= startY + row * sideLength;
               totalBoard+=1;
           }
           col++;
           row--;
       }
       //右上
       col =9;
       row = 1;
       while ( col <= 15)
       {
           if (Math.abs(row - diagonal / 2) + Math.abs(col - diagonal / 2) == diagonal / 2)
           {
               boardXPosition[totalBoard]= startX + col* sideLength;
               boardYPosition[ totalBoard]= startY + row * sideLength;
               totalBoard+=1;
           }
           col++;
           row++;
       }
       //右下
        col = 15;
       row = 9;
       while (col >=9)
       {
           if (Math.abs(row - diagonal / 2) + Math.abs(col - diagonal / 2) == diagonal / 2)
           {
               boardXPosition[totalBoard]= startX + col* sideLength;
               boardYPosition[ totalBoard]= startY + row * sideLength;
               totalBoard+=1;
           }
           col--;
           row++;
       }
    }
    //draw chessboard
    private void drawBoard(Graphics g) {
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        } else {
            g.setColor(Color.RED);
            g.drawString("Failed to load background image!", 10, 30);
        }

        int sideLength = 80; // The length of the lattice side, adjusted to 80
        int startX = 300; // Initial abscissa
        int startY =50; // Initial ordinate
        int diagonal = 17; // The number of cells on the diagonal

        // checkerboard
        for (int row = 1; row < diagonal-1; row++) {
            for (int col = 1; col < diagonal-1; col++) {
                if (Math.abs(row - diagonal / 2) + Math.abs(col - diagonal / 2) == diagonal / 2) {
                    g.setColor(new Color(76, 218, 42));
                    g.fillRect(startX + col * sideLength, startY + row * sideLength, sideLength, sideLength);
                    g.setColor(Color.BLACK);
                    g.drawRect(startX + col * sideLength, startY + row * sideLength, sideLength, sideLength);
                }
            }
        }

        for(int row = 2;row < 6; row++){
            g.setColor(new Color(238, 49, 49));
            g.fillRect(startX+ 8 * sideLength, startY + row * sideLength, sideLength, sideLength);
            g.setColor(Color.BLACK);
            g.drawRect(startX+ 8 * sideLength, startY + row * sideLength, sideLength, sideLength);
        }

        for(int row = 11;row < 15; row++){
            g.setColor(new Color(248, 222, 64));
            g.fillRect(startX+ 8 * sideLength, startY + row * sideLength, sideLength, sideLength);
            g.setColor(Color.BLACK);
            g.drawRect(startX+ 8 * sideLength, startY + row * sideLength, sideLength, sideLength);
        }

        for(int col = 2;col < 6; col++){
            g.setColor(new Color(75, 115, 246));
            g.fillRect(startX + col * sideLength, startY + 8 * sideLength, sideLength, sideLength);
            g.setColor(Color.BLACK);
            g.drawRect(startX + col * sideLength, startY + 8 * sideLength, sideLength, sideLength);
        }

        for(int col = 11;col < 15; col++){
            g.setColor(new Color(215, 62, 239));
            g.fillRect(startX + col * sideLength, startY + 8 * sideLength, sideLength, sideLength);
            g.setColor(Color.BLACK);
            g.drawRect(startX + col * sideLength, startY + 8 * sideLength, sideLength, sideLength);
        }


       for (int i = 0; i < squaresX.length; i++) {
            g.setColor(Color.CYAN);
            g.fillRect(startX + squaresX[i] * sideLength, startY + squaresY[i] * sideLength, sideLength, sideLength);

            g.setColor(Color.BLACK);
            g.drawRect(startX + squaresX[i] * sideLength, startY + squaresY[i] * sideLength, sideLength, sideLength);
            paintDrawGrid(g,startX + squaresX[i] * sideLength + sideLength/2 -15, startY + squaresY[i] * sideLength + sideLength/2);
        }
        //Draw the five-pointed star
        for (int i = 0; i < starX.length; i++) {
            drawFivePointedStar(g, startX + starX[i] * sideLength + sideLength / 2, startY + starY[i] * sideLength + sideLength / 2, sideLength / 4);
        }

        //HOME
        Font homeFont = new Font("Arial", Font.BOLD, 50);
        g.setFont(homeFont);
        g.setColor(Color.BLACK);
        g.drawString("H", startX + sideLength * 8 +sideLength / 2 - 20, startY + sideLength * 2 + sideLength * 3 / 4 );
        g.drawString("O", startX + sideLength * 8 +sideLength / 2 - 20, startY + sideLength * 3 + sideLength * 3 / 4 );
        g.drawString("M", startX + sideLength * 8 +sideLength / 2 - 20, startY + sideLength * 4 + sideLength * 3 / 4 );
        g.drawString("E", startX + sideLength * 8 +sideLength / 2 - 20, startY + sideLength * 5 + sideLength * 3 / 4 );

        g.drawString("H", startX + sideLength * 8 +sideLength / 2 - 20, startY + sideLength * 14 + sideLength * 3 / 4 );
        g.drawString("O", startX + sideLength * 8 +sideLength / 2 - 20, startY + sideLength * 13 + sideLength * 3 / 4 );
        g.drawString("M", startX + sideLength * 8 +sideLength / 2 - 20, startY + sideLength * 12 + sideLength * 3 / 4 );
        g.drawString("E", startX + sideLength * 8 +sideLength / 2 - 20, startY + sideLength * 11 + sideLength * 3 / 4 );

        g.drawString("H",startY + sideLength * 5 + sideLength * 2 / 3 -20, startX + sideLength * 5 +sideLength / 2  );
        g.drawString("O",startY + sideLength * 6 + sideLength * 2 / 3 -20, startX + sideLength * 5 +sideLength / 2  );
        g.drawString("M",startY + sideLength * 7 + sideLength * 2 / 3 -20, startX + sideLength * 5 +sideLength / 2  );
        g.drawString("E",startY + sideLength * 8 + sideLength * 2 / 3 -20, startX + sideLength * 5 +sideLength / 2  );

        g.drawString("E",startY + sideLength * 14 + sideLength * 2 / 3 -20, startX + sideLength * 5 +sideLength / 2  );
        g.drawString("M",startY + sideLength * 15 + sideLength * 2 / 3 -20, startX + sideLength * 5 +sideLength / 2  );
        g.drawString("O",startY + sideLength * 16 + sideLength * 2 / 3 -20, startX + sideLength * 5 +sideLength / 2  );
        g.drawString("H",startY + sideLength * 17 + sideLength * 2 / 3 -20, startX + sideLength * 5 +sideLength / 2  );

        //画准备位棋子
        for(int i=0;i<4;i++)
        {
            g.setColor(new Color(248,222,64));
            g.fillOval(820+100*i, 1370, 40, 40);
            g.setColor(Color.black);
            g.drawOval(820+100*i, 1370, 40, 40);
        }
        for(int i=0;i<4;i++)
        {
            g.setColor(new Color(75,115,246));
            g.fillOval(300, 560+100*i, 40, 40);
            g.setColor(Color.black);
            g.drawOval(300, 560+100*i, 40, 40);
        }
        for(int i=0;i<4;i++)
        {
            g.setColor(new Color(215,62,239));
            g.fillOval(1620, 560+100*i, 40, 40);
            g.setColor(Color.black);
            g.drawOval(1620, 560+100*i, 40, 40);
        }
        for(int i=0;i<4;i++)
        {
            g.setColor(new Color(238, 49, 49));
            g.fillOval(820+100*i, 50, 40, 40);
            g.setColor(Color.black);
            g.drawOval(820+100*i, 50, 40, 40);
        }
    }
    //画五角星
    private void drawFivePointedStar(Graphics g, int x, int y, int size) {
        int[] xPoints = new int[10];
        int[] yPoints = new int[10];

        // Calculate the vertex coordinates of the pentagram
        for (int i = 0; i < 10; i++) {
            double angle = Math.toRadians(-90 + i * 36); // The Angle of each vertex
            if (i % 2 == 0) {
                xPoints[i] = x + (int) (size * Math.cos(angle));
                yPoints[i] = y + (int) (size * Math.sin(angle));
            } else {
                xPoints[i] = x + (int) (size * 0.5 * Math.cos(angle));
                yPoints[i] = y + (int) (size * 0.5 * Math.sin(angle));
            }
        }
        g.setColor(Color.red);
        g.fillPolygon(xPoints, yPoints, 10);
    }
    //Draw Card
    private void paintDrawGrid(Graphics g, int x, int y)
    {
           Font drawFont = new Font("Arial", Font.BOLD, 20);
           g.setFont(drawFont);
           g.setColor(Color.black);
           g.drawString("DRAW", x-15, y+5);
       }
  //draw the chess
    public void drawPiece(Graphics g,int x,int y, int player)
    {
        if(player == 1) {
            g.setColor(new Color(248, 222, 64));
            g.fillOval(x + 16, y + 16, 40, 40);
            g.setColor(Color.black);
            g.drawOval(x + 16, y + 16, 40, 40);
        }
       if(player == 2){
           g.setColor(new Color(75, 115, 246));
           g.fillOval(x + 16, y + 16, 40, 40);
           g.setColor(Color.black);
           g.drawOval(x + 16, y + 16, 40, 40);
       }
       if(player ==3){
           g.setColor(new Color(238, 49, 49));
           g.fillOval(x + 16, y + 16, 40, 40);
           g.setColor(Color.black);
           g.drawOval(x + 16, y + 16, 40, 40);
       }
       if(player == 4){
           g.setColor(new Color(215, 62, 239));
           g.fillOval(x + 16, y + 16, 40, 40);
           g.setColor(Color.black);
           g.drawOval(x + 16, y + 16, 40, 40);
       }
    }
    //清除棋子
    public void clearPiece( int x, int y,int z)
    {
        Graphics g = boardPanel.getGraphics();
        if(z ==1 ) {
            g.setColor(new Color(76, 218, 42));
            g.fillOval(x + 16, y + 16, 40, 40);
            g.drawOval(x + 16, y + 16, 40, 40);
        }
        if(z == 2)
        {
            g.setColor(Color.cyan);
            g.fillOval(x+16,y+16,40,40);
            g.drawOval(x+16,y+16,40,40);
        }
    }

    // 清除准备中的棋子
    public void drawPrepareDice(int player,int index)
    {
        Graphics g;
        g = boardPanel.getGraphics();
        switch (player)
        {
            case 1:
                g.setColor(new Color(248, 222, 64));
                g.fillOval(820+100*index,1370,40,40);
                g.setColor(Color.black);
                g.drawOval(820+100*index,1370,40,40);
                break;
            case 2:
                g.setColor(new Color(75,115,246));
                g.fillOval(300,560+100*index,40,40);
                g.setColor(Color.black);
                g.drawOval(300,560+100*index,40,40);
                break;
            case 3:
                g.setColor(new Color(238, 49, 49));
                g.fillOval(820+100*index,50,40,40);
                g.setColor(Color.black);
                g.drawOval(820+55*index,50,40,40);
                break;
            case 4:
                g.setColor(new Color(215,62,239));
                g.fillOval(1620,560+100*index,40,40);
                g.setColor(Color.black);
                g.drawOval(1620,560+100*index,40,40);
                break;
        }
    }
    //重绘准备中的棋子
    public void clearPrepareDice(int player,int index)
    {
        Graphics g;
        g = boardPanel.getGraphics();
        g.setColor(new Color(255,255,255));
        switch(player)
        {
            case 1:
                g.fillOval(820+100*index,1370,40,40);
                g.drawOval(820+100*index,1370,40,40);
                break;
            case 2:
                g.fillOval(300,560+100*index,40,40);
                g.drawOval(300,560+100*index,40,40);
                break;
                case 3:
                    g.fillOval(820+100*index,50,40,40);
                    g.drawOval(820+100*index,50,40,40);
                break;
            case 4:
                g.fillOval(1620,560+100*index,40,40);
                g.drawOval(1620,560+100*index,40,40);
                break;
        }
    }

    private void rollDice() {

        if (newDice == false ) {
            currentPlayer = (currentPlayer + 1) % 5; // Switch to the next player
            if(currentPlayer == 0) currentPlayer++;
           switch (currentPlayer)
           {
               case 1: currentPlayerLabel.setText("Current player:  Yellow piece" );break; // Update the current player display TAB
               case 2: currentPlayerLabel.setText("Current player:    Blue piece");break;
               case 3: currentPlayerLabel.setText("Current player:    Red piece");break;
               case 4: currentPlayerLabel.setText("Current player:    Purple piece");break;
           }
        }else newDice = false;
        // Generate random numbers from 1 to 6
        diceValue = (int) (Math.random() * 6) + 1;
        // diceResultLabel.setText("Dice result:" + diceValue);  Update the text content of the dice result display label
        // Dice animation
        new Thread(new Runnable() {
            @Override
            public void run() {
                int rolls = 6;
                for(int i= 0; i < rolls; i++) {
                    int diceValue = (int) (Math.random() * 6) + 1;
                    diceResultLabel.setText("Rolling... " + diceValue);
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
                diceResultLabel.setText("Final value: " + diceValue);
                judge();
            }
        }).start();
    }
    public void judge()
    {
       int nextIndex =0;
        int player = currentPlayer;
        if(diceValue == 6)
        {
            switch (player)
            {
                case 1: if(piece1.getStep() == 0){piece1.inGame();clearPrepareDice(1,piece1.getInHome());newDice = true; clear(1);}else {
                    while(true)
                    {
                        clear(1);
                        nextIndex = piece1.getPosition();
                        if (nextIndex > 28) nextIndex -= 28;
                        if (nextIndex < 1) nextIndex += 28;
                        //对抽卡的判断
                        if (nextIndex == 3 || nextIndex == 5 || nextIndex == 9 || nextIndex == 11 || nextIndex == 13 || nextIndex == 17 || nextIndex == 19 || nextIndex == 23 || nextIndex == 25 || nextIndex == 27) {
                            drawCard();
                            if (diceValue == 99) {
                                newDice = true;
                                break;
                            }
                            if (diceValue == 0) break;
                        } else break;
                    }
                }
                    break;
                case 2:if (piece2.getStep() == 0){piece2.inGame();clearPrepareDice(2,piece2.getInHome());newDice = true; clear(2);}else{
                    while(true)
                    {
                        clear(2);
                        nextIndex = piece2.getPosition();
                        if (nextIndex > 28) nextIndex -= 28;
                        if (nextIndex < 1) nextIndex += 28;
                        //对抽卡的判断
                        if (nextIndex == 3 || nextIndex == 5 || nextIndex == 9 || nextIndex == 11 || nextIndex == 13 || nextIndex == 17 || nextIndex == 19 || nextIndex == 23 || nextIndex == 25 || nextIndex == 27) {
                            drawCard();
                            if (diceValue == 99) {
                                newDice = true;
                                break;
                            }
                            if (diceValue == 0) break;
                        } else break;
                    }
                }
                break;
                case 3:if(piece3.getStep()== 0 ){piece3.inGame();clearPrepareDice(3,piece3.getInHome());newDice = true; clear(3);}else {
                    while(true)
                    {
                        clear(3);
                        nextIndex = piece3.getPosition();
                        if (nextIndex > 28) nextIndex -= 28;
                        if (nextIndex < 1) nextIndex += 28;
                        //对抽卡的判断
                        if (nextIndex == 3 || nextIndex == 5 || nextIndex == 9 || nextIndex == 11 || nextIndex == 13 || nextIndex == 17 || nextIndex == 19 || nextIndex == 23 || nextIndex == 25 || nextIndex == 27) {
                            drawCard();
                            if (diceValue == 99) {
                                newDice = true;
                                break;
                            }
                            if (diceValue == 0) break;
                        } else break;
                    }
                }

                break;
                case 4: if(piece4.getStep() == 0){piece4.inGame();clearPrepareDice(4,piece4.getInHome());newDice = true; clear(4);}else {
                    while(true)
                    {
                        clear(4);
                        nextIndex = piece4.getPosition();
                        if (nextIndex > 28) nextIndex -= 28;
                        if (nextIndex < 1) nextIndex += 28;
                        //对抽卡的判断
                        if (nextIndex == 3 || nextIndex == 5 || nextIndex == 9 || nextIndex == 11 || nextIndex == 13 || nextIndex == 17 || nextIndex == 19 || nextIndex == 23 || nextIndex == 25 || nextIndex == 27) {
                            drawCard();
                            if (diceValue == 99) {
                                newDice = true;
                                break;
                            }
                            if (diceValue == 0) break;
                        } else break;
                    }
                }

                break;
            }
        }else {
            switch (player) {
                    case 1:
                        if (piece1.getStep() != 0) {
                            while(true)
                            {
                                clear(1);
                              nextIndex = piece1.getPosition();
                              if (nextIndex > 28) nextIndex -= 28;
                                if (nextIndex < 1) nextIndex += 28;
                                //对抽卡的判断
                                if (nextIndex == 3 || nextIndex == 5 || nextIndex == 9 || nextIndex == 11 || nextIndex == 13 || nextIndex == 17 || nextIndex == 19 || nextIndex == 23 || nextIndex == 25 || nextIndex == 27) {
                                    drawCard();
                                    if (diceValue == 99) {
                                        newDice = true;
                                        break;
                                    }
                                    if (diceValue == 0) break;
                                } else break;
                            }
                        }
                        break;
                    case 2:
                        if (piece2.getStep() != 0) {
                            while (true)
                            {
                                clear(2);
                              nextIndex = piece2.getPosition();
                              if (nextIndex > 28) nextIndex -= 28;
                                if (nextIndex < 1) nextIndex += 28;
                                if (nextIndex == 3 || nextIndex == 5 || nextIndex == 9 || nextIndex == 11 || nextIndex == 13 || nextIndex == 17 || nextIndex == 19 || nextIndex == 23 || nextIndex == 25 || nextIndex == 27) {
                                    drawCard();
                                    if (diceValue == 99) {
                                        newDice = true;
                                        break;
                                    }
                                    if (diceValue == 0) break;
                                } else break;
                            }
                        }
                        break;
                    case 3:
                        if (piece3.getStep() != 0) {
                            while (true)
                            {
                                clear(3);
                              nextIndex = piece3.getPosition();
                              if (nextIndex > 28) nextIndex -= 28;
                                if (nextIndex < 1) nextIndex += 28;
                                if (nextIndex == 3 || nextIndex == 5 || nextIndex == 9 || nextIndex == 11 || nextIndex == 13 || nextIndex == 17 || nextIndex == 19 || nextIndex == 23 || nextIndex == 25 || nextIndex == 27) {
                                    drawCard();
                                    if (diceValue == 99) {
                                        newDice = true;
                                        break;
                                    }
                                    if (diceValue == 0) break;
                                } else break;
                            }
                        }
                        break;
                    case 4:
                        if (piece4.getStep() != 0) {
                            while (true)
                            {
                                clear(4);
                                nextIndex = piece4.getPosition();
                                if (nextIndex > 28) nextIndex -= 28;
                                if (nextIndex < 1) nextIndex += 28;
                                if (nextIndex == 3 || nextIndex == 5 || nextIndex == 9 || nextIndex == 11 || nextIndex == 13 || nextIndex == 17 || nextIndex == 19 || nextIndex == 23 || nextIndex == 25 || nextIndex == 27) {
                                    drawCard();
                                    if (diceValue == 99) {
                                        newDice = true;
                                        break;
                                    }
                                    if (diceValue == 0) break;
                                } else break;
                            }
                        }
                        break;
            }
        }
    }
    private void drawCard()
    {
        Cards drawPanel = new Cards();
        drawPanel.setVisible(true);
                while(true)
                {
                    try {
                        Thread.sleep(100); // 100毫秒
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if(drawPanel.getHaveDraw() == true){diceValue = drawPanel.getValue();break;}
                }

    }
    public void clear(int player)
    {
        int sideLength = 80; // The length of the lattice side, adjusted to 80
        int startX = 300; // Initial abscissa
        int startY = 50; //Initial ordinate
        int currentIndex=0,currentStep=0;
        switch(player)
        {
            case 1: currentIndex = piece1.getPosition();currentStep = piece1.getStep();break;
            case 2: currentIndex = piece2.getPosition();currentStep = piece2.getStep();break;
            case 3: currentIndex = piece3.getPosition();currentStep = piece3.getStep();break;
            case 4: currentIndex = piece4.getPosition();currentStep = piece4.getStep();break;
        }
        if( currentStep ==0 )
            {
                if(Grid [currentIndex] != 0)switch (Grid [currentIndex])
                {
                    case 1: piece1.reSet();drawPrepareDice(1,piece1.getInHome());System.out.println("piece1 was replaced");break;
                    case 2: piece2.reSet();drawPrepareDice(2,piece2.getInHome());System.out.println("piece2 was replaced");break;
                    case 3: piece3.reSet();drawPrepareDice(3,piece3.getInHome());System.out.println("piece3 was replaced");break;
                    case 4: piece4.reSet();drawPrepareDice(4,piece4.getInHome());System.out.println("piece4 was replaced");break;
                }
                switch (player)
                {
                    case 1:
                        piece1.setStep(1);drawPiece(boardPanel.getGraphics(), boardXPosition[currentIndex], boardYPosition[currentIndex],1);
                        Grid [currentIndex] =1;
                        break;
                    case 2:
                        piece2.setStep(1);drawPiece(boardPanel.getGraphics(), boardXPosition[currentIndex], boardYPosition[currentIndex],2);
                        Grid [currentIndex] = 2;
                        break;
                    case 3:
                        piece3.setStep(1);drawPiece(boardPanel.getGraphics(), boardXPosition[currentIndex], boardYPosition[currentIndex],3);
                       Grid [currentIndex] = 3;
                        break;
                    case 4:
                        piece4.setStep(1);drawPiece(boardPanel.getGraphics(), boardXPosition[currentIndex], boardYPosition[currentIndex],4);
                        Grid [currentIndex] = 4;
                        break;
                }
            }else if(currentStep!=0)
            {
                //Redraw the pentagram
                if(currentIndex == 1 || currentIndex == 8 || currentIndex == 15 || currentIndex ==22)
                {
                    clearPiece(boardXPosition[currentIndex], boardYPosition[currentIndex],1);
                    if(currentIndex == 1) drawFivePointedStar(boardPanel.getGraphics(),startX + starX[3] * sideLength + sideLength / 2, startY + starY[3] * sideLength + sideLength / 2, sideLength /4);
                   if( currentIndex == 8)drawFivePointedStar(boardPanel.getGraphics(),startX + starX[0] * sideLength + sideLength / 2, startY + starY[0] * sideLength + sideLength / 2, sideLength / 4);
                   if( currentIndex == 15)drawFivePointedStar(boardPanel.getGraphics(),startX + starX[1] * sideLength + sideLength / 2, startY + starY[1] * sideLength + sideLength / 2, sideLength / 4);
                  if (currentIndex == 22)drawFivePointedStar(boardPanel.getGraphics(),startX + starX[2] * sideLength + sideLength / 2, startY + starY[2] * sideLength + sideLength / 2, sideLength / 4);
                }else if(currentIndex == 3 || currentIndex == 5 || currentIndex == 9 || currentIndex == 11 || currentIndex == 13 || currentIndex == 17 || currentIndex == 19 || currentIndex == 23 || currentIndex == 25 || currentIndex ==27)
                {
                    clearPiece(boardXPosition[currentIndex], boardYPosition[currentIndex],2);
                    //重绘抽卡
                   switch (currentIndex)
                    {
                        case 3:  paintDrawGrid(boardPanel.getGraphics(), startX + squaresX[4] * sideLength + sideLength / 2 - 15, startY + squaresY[4] * sideLength + sideLength / 2);;break;
                        case 5:  paintDrawGrid(boardPanel.getGraphics(),startX + squaresX[3] * sideLength + sideLength / 2 - 15, startY + squaresY[3] * sideLength + sideLength / 2);;break;
                        case 9:  paintDrawGrid(boardPanel.getGraphics(), startX + squaresX[0] * sideLength + sideLength / 2 - 15, startY + squaresY[0] * sideLength + sideLength / 2);;break;
                        case 11:  paintDrawGrid(boardPanel.getGraphics(), startX + squaresX[1] * sideLength + sideLength / 2 - 15, startY + squaresY[1] * sideLength + sideLength / 2);;break;
                        case 13:  paintDrawGrid(boardPanel.getGraphics(), startX + squaresX[2] * sideLength + sideLength / 2 - 15, startY + squaresY[2] * sideLength + sideLength / 2);;break;
                        case 17:  paintDrawGrid(boardPanel.getGraphics(), startX + squaresX[5] * sideLength + sideLength / 2 - 15, startY + squaresY[5] * sideLength + sideLength / 2);;break;
                        case 19:  paintDrawGrid(boardPanel.getGraphics(), startX + squaresX[6] * sideLength + sideLength / 2 - 15, startY + squaresY[6] * sideLength + sideLength / 2);;break;
                        case 23: paintDrawGrid(boardPanel.getGraphics(), startX + squaresX[9] * sideLength + sideLength / 2 - 15, startY + squaresY[9] * sideLength + sideLength / 2);;break;
                        case 25:  paintDrawGrid(boardPanel.getGraphics(),startX + squaresX[8] * sideLength + sideLength / 2 - 15, startY + squaresY[8] * sideLength + sideLength / 2);;break;
                        case 27:  paintDrawGrid(boardPanel.getGraphics(), startX + squaresX[7] * sideLength + sideLength / 2 - 15, startY + squaresY[7] * sideLength + sideLength / 2);;break;
                    }
                }else clearPiece(boardXPosition[currentIndex],boardYPosition[currentIndex],1);
                Grid[currentIndex]=0;
               movePiece(player,currentIndex,currentStep);
            }
        }
        public void showYellowVictoryPopup(){

            JFrame frame1 = new JFrame("Victory");
            frame1.setSize(1000, 800);
            frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            ImageIcon backgroundImage = new ImageIcon("src\\Coursework\\1.jpg");
            JLabel backgroundLabel = new JLabel(backgroundImage);
            backgroundLabel.setPreferredSize(new Dimension(backgroundImage.getIconWidth(), backgroundImage.getIconHeight()));
            backgroundLabel.setLayout(new FlowLayout());

            JLabel label = new JLabel("Yellow Victory");
            frame1.add(label);

            JButton quitButton = new JButton("Exit the game");
            quitButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.exit(0);
                }
            });
            JButton homeButton = new JButton("Return to HomePage");
            homeButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    frame1.dispose();
                    HomePage homePage = new HomePage();
                }
            });
            frame1.add(quitButton);
            frame1.add(homeButton);
            frame1.setLayout(new FlowLayout());
            frame1.setVisible(true);
        }
    public void showBlueVictoryPopup(){

        JFrame frame2 = new JFrame("Victory");
        frame2.setSize(1000, 800);
        frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ImageIcon backgroundImage = new ImageIcon("src\\Coursework\\2.jpg");
        JLabel backgroundLabel = new JLabel(backgroundImage);
        backgroundLabel.setPreferredSize(new Dimension(backgroundImage.getIconWidth(), backgroundImage.getIconHeight()));
        backgroundLabel.setLayout(new FlowLayout());

        JLabel label = new JLabel("Blue Victory");
        frame2.add(label);

        JButton quitButton = new JButton("Exit the game");
        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        JButton homeButton = new JButton("Return to HomePage");
        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame2.dispose(); // Close the current VictoryPopup screen
                HomePage homePage = new HomePage();// Return to the operations of the main HomePage // Return to the operations of the main HomePage
            }
        });
        frame2.add(quitButton);
        frame2.add(homeButton);
        frame2.setLayout(new FlowLayout());
        frame2.setVisible(true);
    }
    public void showRedVictoryPopup(){

        JFrame frame3 = new JFrame("Victory");
        frame3.setSize(1000, 800);
        frame3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ImageIcon backgroundImage = new ImageIcon("src\\Coursework\\3.jpg");
        JLabel backgroundLabel = new JLabel(backgroundImage);
        backgroundLabel.setPreferredSize(new Dimension(backgroundImage.getIconWidth(), backgroundImage.getIconHeight()));
        backgroundLabel.setLayout(new FlowLayout());

        JLabel label = new JLabel("Red Victory");
        frame3.add(label);

        JButton quitButton = new JButton("Exit the game");
        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        JButton homeButton = new JButton("Return to HomePage");
        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame3.dispose(); // Close the current VictoryPopup screen
                HomePage homePage = new HomePage();// Return to the operations of the main HomePage // Return to the operations of the main HomePage
            }
        });
        frame3.add(quitButton);
        frame3.add(homeButton);
        frame3.setLayout(new FlowLayout());
        frame3.setVisible(true);
    }
    public void showPurpleVictoryPopup(){

        JFrame frame4 = new JFrame("Victory");
        frame4.setSize(1000, 800);
        frame4.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ImageIcon backgroundImage = new ImageIcon("src\\Coursework\\4.jpg");
        JLabel backgroundLabel = new JLabel(backgroundImage);
        backgroundLabel.setPreferredSize(new Dimension(backgroundImage.getIconWidth(), backgroundImage.getIconHeight()));
        backgroundLabel.setLayout(new FlowLayout());

        JLabel label = new JLabel("Purple Victory");
        frame4.add(label);

        JButton quitButton = new JButton("Exit the game");
        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        JButton homeButton = new JButton("Return to HomePage");
        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame4.dispose(); // Close the current VictoryPopup screen
                HomePage homePage = new HomePage();// Return to the operation of the HomePage main interface
            }
        });
        frame4.add(quitButton);
        frame4.add(homeButton);
        frame4.setLayout(new FlowLayout());
        frame4.setVisible(true);
    }
    public void movePiece(int player,int currentIndex, int step) {
            int sideLength = 80; // The length of the lattice side, adjusted to 80
            int startX = 300; // The length of the lattice side, adjusted to 80
            int startY = 50; //The length of the lattice side, adjusted to 80
            int nextIndex = currentIndex + diceValue;
            int nextStep = step + diceValue;
            if (nextIndex > 28) nextIndex -= 28;
            if(nextIndex <1) nextIndex+= 28;
            if (nextStep > 28) //into the door
            {
                 switch (player)
                 {
                     case 1:
                         if(piece1.getInHome() < 4){
                             piece1.setInHome();
                             piece1.reSet();
                             switch (piece1.getInHome()){  //put the chess into the HOME
                                 case 1:
                                     drawPiece(boardPanel.getGraphics(),startX + sideLength * 8 +sideLength / 2 - 33, startY + sideLength * 11 + sideLength * 3 / 4-40,1);
                                     break;
                                 case 2:
                                     drawPiece(boardPanel.getGraphics(),startX + sideLength * 8 +sideLength / 2 - 33, startY + sideLength * 12 + sideLength * 3 / 4-40,1);
                                     break;
                                 case 3:
                                     drawPiece(boardPanel.getGraphics(), startX + sideLength * 8 +sideLength / 2 - 33, startY + sideLength * 13 + sideLength * 3 / 4-40,1);
                                 break;
                                 case 4:showYellowVictoryPopup();break;
                             }
                         }else {drawPiece(boardPanel.getGraphics(),startX + sideLength * 8 +sideLength / 2 - 20, startY + sideLength * 14 + sideLength * 3 / 4 ,1);System.exit(0);}
                         break;
                         case 2:
                         if(piece2.getInHome() < 4){
                             piece2.setInHome();
                             piece2.reSet();
                             switch (piece2.getInHome()){
                                 case 1: drawPiece(boardPanel.getGraphics(),startY + sideLength * 8 + sideLength * 2 / 3 -33, startX + sideLength * 5 +sideLength / 2-40,2);
                                 break;
                                 case 2:
                                     drawPiece(boardPanel.getGraphics(),startY + sideLength * 7 + sideLength * 2 / 3 -33, startX + sideLength * 5 +sideLength / 2 -40,2);
                                       break;
                                 case 3:
                                     drawPiece(boardPanel.getGraphics(),startY + sideLength * 6 + sideLength * 2 / 3 -33, startX + sideLength * 5 +sideLength / 2-40,2);
                                     break;
                                 case 4:showBlueVictoryPopup();break;
                             }
                         }else {drawPiece(boardPanel.getGraphics(),startY + sideLength * 5 + sideLength * 2 / 3 -20, startX + sideLength * 5 +sideLength / 2,2);System.exit(0);}
                         break;
                     case 3:
                         if(piece3.getInHome() < 4) {
                             piece3.setInHome();
                             piece3.reSet();
                             switch (piece3.getInHome()){
                                 case 1:
                                     drawPiece(boardPanel.getGraphics(),startX + sideLength * 8 +sideLength / 2 - 33, startY + sideLength * 5 + sideLength * 3 / 4 -40,3);
                                     break;
                                 case 2:
                                     drawPiece(boardPanel.getGraphics(),startX + sideLength * 8 +sideLength / 2 - 33, startY + sideLength * 4 + sideLength * 3 /4 -40,3);
                                     break;
                                 case 3:
                                     drawPiece(boardPanel.getGraphics(),startX + sideLength * 8 +sideLength / 2 - 33, startY + sideLength * 3 + sideLength * 3 / 4-40,3);
                                     break;
                                     case 4: showRedVictoryPopup();break;
                             }
                         }else {drawPiece(boardPanel.getGraphics(),startX + sideLength * 8 +sideLength / 2 - 20, startY + sideLength * 2 + sideLength * 3 / 4,3);System.exit(0);}
                         break;
                     case 4:
                         if(piece4.getInHome() < 4) {
                             piece4.setInHome();
                             piece4.reSet();
                             switch (piece4.getInHome()){
                                 case 1:
                                     drawPiece(boardPanel.getGraphics(),startY + sideLength * 14 + sideLength * 2 / 3 -33, startX + sideLength * 5 +sideLength / 2 -40,4 );
                                    break;
                                 case 2:
                                     drawPiece(boardPanel.getGraphics(),startY + sideLength * 15 + sideLength * 2 / 3 -33, startX + sideLength * 5 +sideLength / 2-40,4);
                                      break;
                                 case 3:
                                     drawPiece(boardPanel.getGraphics(),startY + sideLength * 16 + sideLength * 2 / 3 -33, startX + sideLength * 5 +sideLength / 2-40 ,4);
                                      break;
                                 case 4: showPurpleVictoryPopup();break;
                             }
                         }else {drawPiece(boardPanel.getGraphics(),startY + sideLength * 17 + sideLength * 2 / 3 -30, startX + sideLength * 5 +sideLength / 2,4);System.exit(0);}
                         break;
                 }
            } else {
                //Not enter the door
               //The next player has a chess piece
                if(Grid[nextIndex] != 0) {
                    switch (Grid[nextIndex]) {
                        case 1: piece1.reSet();drawPrepareDice(1,piece1.getInHome());System.out.println("piece1 was replaced");break;
                        case 2: piece2.reSet();drawPrepareDice(2,piece2.getInHome());System.out.println("piece2 was replaced");break;
                        case 3: piece3.reSet();drawPrepareDice(3,piece3.getInHome());System.out.println("piece3 was replaced");break;
                        case 4: piece4.reSet();drawPrepareDice(4,piece4.getInHome());System.out.println("piece4 was replaced");break;
                    }
                }
                    switch (player) {
                        case 1:
                            piece1.setStep(diceValue);
                            drawPiece(boardPanel.getGraphics(), boardXPosition[nextIndex], boardYPosition[nextIndex], 1);
                            Grid[nextIndex] = 1;
                            System.out.println("yellow move to " + nextIndex);
                            break;
                        case 2:
                            piece2.setStep(diceValue);
                            drawPiece(boardPanel.getGraphics(), boardXPosition[nextIndex], boardYPosition[nextIndex], 2);
                            Grid[nextIndex] = 2;
                            System.out.println("blue move to " + nextIndex);
                            break;
                        case 3:
                            piece3.setStep(diceValue);
                            drawPiece(boardPanel.getGraphics(), boardXPosition[nextIndex], boardYPosition[nextIndex], 3);
                            Grid[nextIndex] = 3;
                            System.out.println("red move to " + nextIndex);
                            break;
                        case 4:
                            piece4.setStep(diceValue);
                            drawPiece(boardPanel.getGraphics(), boardXPosition[nextIndex], boardYPosition[nextIndex], 4);
                            Grid[nextIndex] = 4;
                            System.out.println("purple move to " + nextIndex);
                            break;
                    }
                }
            }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                FourPlayerChessBoard fourPlayerChessBoard = new FourPlayerChessBoard();
                fourPlayerChessBoard.setVisible(true);
                fourPlayerChessBoard.setPosition();
            }
        });
    }
}
