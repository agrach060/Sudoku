// Anna Gracheva
// CS 143
// HW Core Topics: Sudoku #1(Board Setup)
//
// This program will import a Sudoku from a file and print the board to the screen

import java.io.*;
import java.util.*;

public class SudokuBoard{

   private int[][] cells = new int[9][9];
   
   public SudokuBoard(String fileName) throws FileNotFoundException{
      File boardFile = new File(fileName);
      Scanner fileScan = new Scanner(boardFile);
      for(int i = 0; i < 9; i++){
         String line = fileScan.nextLine();
         for(int j = 0; j < 9; j++){
            char digit = line.charAt(j);
            if (digit != '.') {
                 // Add the valid number to the board
               cells[i][j] = Character.getNumericValue(digit);
            } else {
              // If invalid, add a zero to the board
               cells[i][j] = 0;
            }
         }
      }
   }
   public String toString(){
        String printBoard =" ";
        for(int i = 0; i < 9; i++) {
            printBoard += "\n";
            if((i == 3) || (i == 6)){
               printBoard += "\n";
            }
            for(int j = 0; j < 9; j++) {
               if((j == 3) || (j == 6)){
               printBoard += " ";
               }
               if(cells[i][j] == 0) {
                  printBoard += " _ "; //print this if the number isn't included.
               }
               else printBoard += " " + cells[i][j] + " ";
            }
        }
        printBoard += "\n";
        return printBoard;
    }
} 
   
/* 
 ----jGRASP exec: java Sudoku
 Please enter the file name: 
 data1.sdk
  
  2  _  _   1  _  5   _  _  3 
  _  5  4   _  _  _   7  1  _ 
  _  1  _   2  _  3   _  8  _ 
 
  6  _  2   8  _  7   3  _  4 
  _  _  _   _  _  _   _  _  _ 
  1  _  5   3  _  9   8  _  6 
 
  _  2  _   7  _  1   _  6  _ 
  _  8  1   _  _  _   2  4  _ 
  7  _  _   4  _  2   _  _  1 
 
 
  ----jGRASP: operation complete.
 */