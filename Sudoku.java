// Anna Gracheva
// CS 143
// HW Core Topics: Sudoku #1(Board Setup)
//
// This program will import a Sudoku from a file and print the board to the screen

import java.util.*;
import java.io.*;

public class Sudoku{

   public static void main(String[] args) throws FileNotFoundException {
      Scanner input = new Scanner(System.in);
      // Take user input for data file
      System.out.println("Please enter the file name: ");
      String fileName = input.nextLine();
      // Create sudoku board by passing file name to class constructor
      SudokuBoard board = new SudokuBoard(fileName);
      // Print the board
      System.out.println(board.toString());
   }
}

/*     ----jGRASP exec: java Sudoku
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