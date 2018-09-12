/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.of.life;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.JFrame;
import java.util.*;

/**
 *
 * @author Matt
 */
public class Board {
    private ArrayList <ArrayList<Boolean>> rows;
    private ArrayList <Boolean> cols;
    private int size;
    private int buffer;
    int fullBoardSize;
    int virtualBoardStart;
    int virtualBoardEnd;
    
    
    
    
    public Board(int size, int buffer){
        
        this.size = size;
        this.buffer = buffer;
        fullBoardSize = size + 2 * buffer;
        virtualBoardStart = buffer;
        virtualBoardEnd = size + buffer;
        
        
        rows = new ArrayList<>();
        for (int row = 0; row < fullBoardSize; row++) {
            cols = new ArrayList<Boolean>();
            
            for (int col = 0; col < fullBoardSize; col++) {
                cols.add(false);
            }
            rows.add(cols);
        }
        
    
    }
   
    // print virtual board
    public void printBoard(Graphics G, int height, int width, int hue){
        BufferedImage image = new BufferedImage(size + 100, size + 100, BufferedImage.TYPE_INT_ARGB);
        
       
        
        Graphics g = image.getGraphics();
        
        g.setColor(Color.black);
        
        g.fillRect(0, 0, size + 200, size + 200);
        
 //       Random color = new Random();
//        int h = color.nextInt(255)+1;
//        int s = color.nextInt(255)+1;
//        int b = color.nextInt(255)+1;

        g.setColor(Color.getHSBColor(hue/255.0f, 1f, 1f));
        
        
        
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                
                //g.drawRect(50 + 3 * j, 50 + 3 * i, 3, 3);
                if(getCellState(i,j)){
                    g.fillRect(50 + j, 50 + i, 1 , 1);
                }
            }
        }
        G.drawImage(image, 0, 0, null);
        
        
        
        
        
        
        
        
//        for (int row = 0;  row < size; row++) {
//            for (int col = 0; col < size; col++) {
//                //System.out.print(row + " " + col);
//                if(getCellState(col,row) == true){
//                    System.out.print("X");
//                } else{
//                    System.out.print(" ");
//                }
//            }
//            System.out.println("");
//            
//        }
//        System.out.print("\n \n \n");
       
        
    }
    
    public void setCell(boolean state,int vx, int vy){
        int x = vx + buffer;
        int y = vy + buffer;
        
        rows.get(y).set(x, state);

    }    
    
    
    public boolean getCellState(int vx, int vy){
        int x = vx + buffer;
        int y = vy + buffer;
        
        return rows.get(y).get(x);
        
    }
    
    
    
    public void setFreshBoard(){
        int num;
        Random rand = new Random();
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
               num = rand.nextInt(2) + 1;
               if(num % 2 == 0){
                   setCell(true,col,row);
               }
               
//               if(col % 2 == 0){
//                   
//                   setCell(true,col,row);
//               } 
            }
   
             
        }
        
    }
   
    
    public boolean liveOrDie(int vx, int vy){

        
        
        boolean live = getCellState(vx,vy);
        int surrounding = 0;
            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    if (i == 0 && j == 0) {
                        continue;
                    }   
                     
                    if (getCellState(vx + i, vy + j) == true) {
                        surrounding++;
                    } 
                }
            }
        

        if(surrounding < 2 || surrounding > 3 ){
            live = false;
            return live;
        }
        
        if(live == false && surrounding == 3){
            live = true;
            return live;
        }
        
        
        return live;
    }
    
    
    
}
