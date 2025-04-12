package Coursework;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.JLabel;
import javax.swing.JFrame;
import java.awt.Font;

public class Cards extends JFrame {

    private JLabel displayLabel;
    private Random random;
    private  int value = 0;
    private boolean haveDraw;

    public Cards() {
        // Initialization window
        JFrame frame = new JFrame("Random String Generator");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        haveDraw = false;

        frame.setLayout(new BorderLayout());

        // Creating tags
        displayLabel = new JLabel("Cards");
        displayLabel.setFont(new Font("Microsoft Accor black", Font.PLAIN, 30));
        displayLabel.setHorizontalAlignment(JLabel.CENTER); // Center the text
        frame.add(displayLabel, BorderLayout.CENTER);


        // Initialize the random number generator
        random = new Random();

        // Create button, click to generate random string
       JButton generateButton = new JButton("click");
       generateButton.setFont(new Font("Microsoft Accor black", Font.PLAIN, 30));
       generateButton.setHorizontalAlignment(JButton.CENTER);
       generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int randomIndex = random.nextInt(words.length); // Generate random numbers from 0 to words.length-1
                displayLabel.setText(words[randomIndex]); // Replace the display with the string corresponding to the random number index
                String word = words[randomIndex];
                getStringToInt(word); // Convert to an integer value
                displayLabel.setText(word); // Replace the display with the string corresponding to the random number index
                Timer timer = new Timer(2000, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        dispose(); // Close the window after 3 seconds
                    }
                });
                timer.setRepeats(false); // Set the timer to fire only once
                timer.start(); // Start the timer
            }
        });

          // Create Panel
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(displayLabel);
        panel.add(generateButton);

        // Set window content
        setContentPane(panel);
    }

    private static final String[] words = {"Pity! Stay there", "Move forward one grid", "Cast the dice again", "Move forward four grids","Go back one grid","Go back three grids","Move forward two grids","Move forward three grids"}; // You can add more words

    private void getStringToInt(String word) {
        switch (word) {
            case "Pity! Stay there":
                value = 0; // If it is "Skip one round", return 0
                break;
            case "Move forward one grid":
                value = 1; // "Move forward one grid" -> 1
                break;
            case "Cast the dice again":
                value = 99; // "Cast the dice again" -> 2
                break;
            case "Move forward four grids":
                value = 5; // "Move forward five grids" -> 5
                break;
            case "Go back one grid":
                value = -1; // "Go back one grid" -> -1
                break;
            case "Go back three grids":
                value = -3; // "Go back three grids" -> -3
                break;
            case "Move forward two grids":
                value = 2;
                break;
            case "Move forward three grids":
                value = 3;
                break;
        }
        System.out.println("value = "+ value);
        haveDraw = true;

    }

    public int getValue()
    {
        return value;
    }
    public boolean getHaveDraw(){return haveDraw;}

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Cards().setVisible(true);
            }
        });
    }
}