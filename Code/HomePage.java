package Coursework;
import javax.swing.JFrame;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
public class HomePage extends JFrame {
    private JFrame frame;
    private JCheckBox checkBox;
    private JButton button1;
    private JButton button2;

    public HomePage() {

        try {
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(new File("src\\Coursework\\John Williams - Hedwig's Theme 00_00_00-00_02_30.wav")));
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (Exception e) {
            e.printStackTrace();
        }

        frame = new JFrame("Flying chess");
        frame.setSize(1700, 1400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        try {
            // Load the background image and scale it
            BufferedImage backgroundImage = ImageIO.read(new File("src\\Coursework\\HP.jpg"));
            BufferedImage scaledImage = scaleImage(backgroundImage, 1.6); // The scale is 0.8

            // Create a background image's JLabel
            JLabel backgroundLabel = new JLabel(new ImageIcon(applyBlur(scaledImage, 100)));
            backgroundLabel.setLayout(new BorderLayout());

            // Create a panel that contains the background image
            JPanel backgroundPanel = new JPanel(new BorderLayout());
            backgroundPanel.add(backgroundLabel, BorderLayout.CENTER);

            // Adds the background picture panel to the main panel
            JPanel mainPanel = new JPanel(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.weighty = 0.5;
            mainPanel.add(Box.createVerticalGlue(), gbc);

            JLabel titleLabel = new JLabel("Triwizard Maze Game", SwingConstants.CENTER);
            titleLabel.setFont(new Font("Edwardian Script ITC", Font.BOLD, 80));
            titleLabel.setForeground(new Color(30, 144, 255));
            gbc.gridy++;
            mainPanel.add(titleLabel, gbc);

            JLabel selectLabel = new JLabel("Select mode", SwingConstants.LEFT);
            selectLabel.setFont(new Font("Edwardian Script ITC", Font.PLAIN, 100));
            gbc.gridy++;
            gbc.insets = new Insets(20, 0, 20, 0);
            gbc.anchor = GridBagConstraints.WEST;
            mainPanel.add(selectLabel, gbc);


            JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 50, 0));
            checkBox = new JCheckBox("I already understand the game rules");
            checkBox.setFont(new Font("Arial", Font.PLAIN, 30));
            checkBox.addItemListener(new ItemListener() {
                public void itemStateChanged(ItemEvent e) {
                    if (e.getStateChange() == ItemEvent.SELECTED) {
                        button1.setEnabled(true);
                        button2.setEnabled(true);
                    } else {
                        button1.setEnabled(false);
                        button2.setEnabled(false);
                    }
                }
            });
            buttonPanel.add(checkBox);
            button1 = new JButton("4 Players");
            button1.setFont(new Font("Edwardian Script ITC", Font.PLAIN, 50));
            button1.setPreferredSize(new Dimension(250, 100));
            button1.setEnabled(false);
            button1.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    openFourPlayersMode();
                }
            });
            buttonPanel.add(button1);

            button2 = new JButton("2 Players");
            button2.setFont(new Font("Edwardian Script ITC", Font.PLAIN, 50));
            button2.setPreferredSize(new Dimension(250, 100));
            button2.setEnabled(false);
            button2.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    openTwoPlayersMode();
                }
            });
            buttonPanel.add(button2);

            gbc.gridy++;
            gbc.anchor = GridBagConstraints.CENTER;
            mainPanel.add(buttonPanel, gbc);

            gbc.gridy++;
            gbc.weighty = 0.5;
            mainPanel.add(Box.createVerticalGlue(), gbc);

            // Add the main panel to the background picture panel
            backgroundPanel.add(mainPanel, BorderLayout.SOUTH);

            // Adds the background picture panel to the window
            frame.add(backgroundPanel);
        } catch (IOException e) {
            e.printStackTrace();
        }

        frame.setVisible(true);
    }

    private BufferedImage applyBlur(BufferedImage image, int radius) {
        BufferedImage blurredImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics = blurredImage.createGraphics();
        graphics.drawImage(image, 0, 0, null);
        graphics.dispose();
        return blurredImage;
    }

    private BufferedImage scaleImage(BufferedImage image, double scale) {
        int scaledWidth = (int) (image.getWidth() * scale);
        int scaledHeight = (int) (image.getHeight() * scale);
        Image scaledImage = image.getScaledInstance(scaledWidth, scaledHeight, Image.SCALE_SMOOTH);
        BufferedImage bufferedImage = new BufferedImage(scaledWidth, scaledHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics = bufferedImage.createGraphics();
        graphics.drawImage(scaledImage, 0, 0, null);
        graphics.dispose();
        return bufferedImage;
    }

    private void openFourPlayersMode() {

        FourPlayersMode fourPlayersMode = new FourPlayersMode();

    }


    private void openTwoPlayersMode() {
        TwoPlayersMode twoPlayersMode = new TwoPlayersMode();
    }
}
