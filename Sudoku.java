// Anna Gracheva
// CS 143

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
      System.out.println(board.isValidData());
      System.out.println(board.noRowDuplicates());
      System.out.println(board.noColumnDuplicates());
      System.out.println(board.isMiniSquareValid());
      System.out.println(board.isSolved());

   }
}
