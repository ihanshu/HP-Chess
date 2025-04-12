package Coursework;

import javax.swing.*;

public class FourPlayersMode extends JFrame {
    static FourPlayerChessBoard fourPlayerChessBoard;

    public FourPlayersMode() {
        fourPlayerChessBoard = new FourPlayerChessBoard();
        fourPlayerChessBoard.setVisible(true);
        fourPlayerChessBoard.setTitle("Four Players Mode");
        fourPlayerChessBoard.setPosition();
    }

}
