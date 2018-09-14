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
import javafx.util.Pair;

/**
 *
 * @author Matt
 */
public class Board {
    private ArrayList <ArrayList<Integer>> rows;
    private ArrayList <Integer> cols;
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
            cols = new ArrayList<Integer>();
            
            for (int col = 0; col < fullBoardSize; col++) {
                cols.add(0);
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

        //g.setColor(Color.getHSBColor(hue/255.0f, 1f, 1f));
        
        
        
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                
                //g.drawRect(50 + 3 * j, 50 + 3 * i, 3, 3);
                if(getCellState(i,j) > 0){
                    g.setColor(Color.getHSBColor(getCellState(i,j)/255.0f ,1.0f,1.0f ));
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
    
    public void setCell(int state,int vx, int vy){
        int x = vx + buffer;
        int y = vy + buffer;
        
        rows.get(y).set(x, state);

    }    
    
    
    public int getCellState(int vx, int vy){
        int x = vx + buffer;
        int y = vy + buffer;
        
        return rows.get(y).get(x);
        
    }
    
    
    
    public void setFreshBoard(){
       randomLoad();
//      alternateLoad();
     //   gliders();
    }
    
    public void randomLoad(){
        int num;
        Random rand = new Random();
        
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                num = rand.nextInt(2)+ 1;
                
                if(num % 2 == 0){
                    setCell(1,col,row);
                }
                
            }
        }
        
    }
    
    public void alternateLoad(){
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                if(col % 2 == 0){
                   
                   setCell(1,col,row);
                } 
            }  
        }
    }
    static final int[] GUN_POINTS_X = {
        1,1,2,2,11,11,11,12,12,13,13,14,14,15,16,16,17,17,17,18,21,21,21,22,22,22,23,23,25,25,25,25,35,35,36,36
    };
    static final int[] GUN_POINTS_Y = {
        5,6,5,6,6,7,8,4,9,3,10,3,10,6,4,8,5,6,7,6,3,4,5,3,4,5,2,6,1,2,6,7,3,4,3,4
    };
    public void gliders(){
        for (int i = 0; i < GUN_POINTS_Y.length; i++) {
            setCell(1,GUN_POINTS_X[i] + 100 * buffer,GUN_POINTS_Y[i] + 100 * buffer);
        }
        
        
    }
   
    
    public Boolean liveOrDie(int vx, int vy){

        
        
        int state = getCellState(vx,vy);
        
        
        int surrounding = 0;
            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    if (i == 0 && j == 0) {
                        continue;
                    }   
                     
                    if (getCellState(vx + i, vy + j) > 0) {
                        surrounding++;
                    } 
                }
            }
        

        if(surrounding < 2 || surrounding > 3 ){
            return false;
        }
        
        if(state == 0 && surrounding == 3){
            return true;
        }
        
        return state > 0;
    }
    
    
    
}
