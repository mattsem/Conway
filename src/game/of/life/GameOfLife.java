package game.of.life;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author semimat19
 */
public class GameOfLife extends javax.swing.JFrame {

    private Board board1;
    private Board board2;
    private Board CurrentBoard;
    private Board NextBoard;
    private final int SIZE = 800;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        GameOfLife game = new GameOfLife();
        game.run();

    }

    public void run() {
        JFrame frame = new JFrame("Matt's Game of Life");
        frame.setBounds(0, 0, SIZE + 100, SIZE + 100);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);

        JPanel output = new JPanel();

        frame.add(output);

        
        Graphics g = frame.getGraphics();
        output.setBackground(Color.black);

        CurrentBoard.setFreshBoard();
        CurrentBoard.printBoard(g, frame.getHeight(), frame.getWidth(), 1);
        //System.out.print("\n \n \n");
        int count = 0;
        
        while (true) {
//            if(count >= 255){
//                count = 0;
//            }
            
            updateBoard();
            NextBoard.printBoard(g, frame.getHeight(), frame.getWidth(), count);
            
            
//            try {
//                Thread.sleep(10);
//            } catch (InterruptedException ex) {
//                Logger.getLogger(GameOfLife.class.getName()).log(Level.SEVERE, null, ex);
//            }

            currentBoard();
            
            count ++;

        }
    }

    public GameOfLife() {
        board1 = new Board(SIZE, 1);
        board2 = new Board(SIZE, 1);
        CurrentBoard = board1;
        NextBoard = board2;

    }

    public void currentBoard() {

        if (CurrentBoard == board1) {
            CurrentBoard = board2;
            NextBoard = board1;
        } else {
            CurrentBoard = board1;
            NextBoard = board2;
        }

    }

    public void updateBoard() {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                
                int currentVal = CurrentBoard.getCellState(col, row);
                Boolean toLive = CurrentBoard.liveOrDie(col, row);
                
                if (toLive == true){
                    if (currentVal < 230)
                        currentVal++;
                } else {
                    currentVal = 0;
                }
                
                NextBoard.setCell(currentVal, col, row);
           }
        }

    }
}
