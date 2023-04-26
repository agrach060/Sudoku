// Anna Gracheva
// CS 143
// L.HW #3: Sudoku #3 (solve)
//
// This program will solve the Sudoku using backtracking

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
            } 
            else {
              // If invalid, add a zero to the board
               cells[i][j] = 0;
            }
         }
      }
   }

   public boolean isValid(){
      if((isValidData() == true) && (noRowDuplicates() == true) && 
      (noColumnDuplicates() == true) && (isMiniSquareValid() == true)){
         return true;
      }
      return false;
   }
   

   private boolean isValidData(){
      for(int i = 0; i < cells.length; i++){
         for(int j = 0; j < cells[i].length; j++){
            if ((cells[i][j] < 0) || (cells[i][j] >= 10)){
               return false;
            }    
         }
      }
      return true;
   }           
             
   private boolean noRowDuplicates(){
      Set<Integer> set= new HashSet<>();
      for(int i = 0; i < 9; i++){
         set.clear();
         for(int j = 0; j < 9; j++){
            if(set.contains(cells[i][j]) && cells[i][j] != 0){
               return false;
            }
            set.add(cells[i][j]);
         }
      } 
      return true;
   }
   
   private boolean noColumnDuplicates() {
      Set<Integer> set= new HashSet<>();
      for(int i = 0; i < 9; i++){
         set.clear();
         for(int j = 0; j < 9; j++){
            if(set.contains(cells[j][i]) && (cells[j][i] != 0)){
               return false;
               }
            set.add(cells[j][i]);
         }
      }
      return true;
}           
   
   private int[][] miniSquare(int spot) {
      int[][] mini = new int[3][3];
      for(int r = 0; r < 3; r++) {
         for(int c = 0; c < 3; c++) {
         // whoa - wild! This took me a solid hour to figure out (at least)
         // This translates between the "spot" in the 9x9 Sudoku board
         // and a new mini square of 3x3
            mini[r][c] = cells[(spot - 1) / 3 * 3 + r][(spot - 1) % 3 * 3 + c];
         }
      }
      return mini;
   }
   
   private boolean isMiniSquareValid(){
      Set<Integer> set = new HashSet<>();
      for(int a = 1; a < 10; a++){
         int[][] array = miniSquare(a);
         set.clear();
         for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
               if(set.contains(array[i][j]) && array[i][j] != 0){
                  return false;
               } 
               else {
                  set.add(array[i][j]);         
               }
            }
         }
      }
      return true;
   } 

   public boolean isSolved(){
      if((isValid() == true) && (occurencesInMap() == true)){
         return true;
      }
      return false;
   }
   
   private boolean occurencesInMap(){
      Map<Integer, Integer> map = new HashMap<Integer, Integer>();
      for (int i = 0; i < 9; i++){ 
         for (int j = 0; j < 9; j++){
           if (!map.containsKey(cells[i][j])){ 
               map.put(cells[i][j], 1);
           }else{
               map.put(cells[i][j], map.get(cells[i][j]) + 1); 
           } 
         }      
      }
      for(int z = 1; z < 10; z++){
         if(!map.containsKey(z) || map.get(z) != 9){
            return false;
         }
      }
      return true;
  }
   
   public boolean solve(){
      if(!isValid())
         return false;
      
      if(isSolved())
         return true;
         
      for (int row = 0; row < 9; row++) {
        for (int column = 0; column < 9; column++) {
            if (cells[row][column] == 0) {
                for (int k = 1; k <= 9; k++) {
                    cells[row][column] = k;
                    if (isValid()== true && solve() == true) {
                        return true;
                    }
                    cells[row][column] = 0;
                }
                return false;
            }
        }
    }
    return true;   
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
   
/* very-fast-solve.sdk
  ----jGRASP exec: java SudokuSolverEngine
 Initial board
  
  _  3  4   6  7  8   9  1  2 
  _  7  2   1  9  5   3  4  8 
  1  9  8   3  4  2   5  6  7 
 
  _  _  9   _  6  1   4  2  3 
  _  2  6   8  5  3   7  9  1 
  _  1  3   9  2  4   _  5  6 
 
  _  6  1   5  3  7   2  8  4 
  _  8  _   4  1  9   6  3  5 
  3  4  5   _  8  6   1  7  9 
 
 
 Solving board...SOLVED in 0,008 seconds.
 
  
  5  3  4   6  7  8   9  1  2 
  6  7  2   1  9  5   3  4  8 
  1  9  8   3  4  2   5  6  7 
 
  8  5  9   7  6  1   4  2  3 
  4  2  6   8  5  3   7  9  1 
  7  1  3   9  2  4   8  5  6 
 
  9  6  1   5  3  7   2  8  4 
  2  8  7   4  1  9   6  3  5 
  3  4  5   2  8  6   1  7  9 
 
 
  ----jGRASP: operation complete.

 fast-solve.sdk	

     ----jGRASP exec: java SudokuSolverEngine
 Initial board
  
  8  2  7   1  5  4   3  9  6 
  9  6  5   _  2  7   1  4  8 
  3  4  1   6  _  9   7  5  2 
 
  _  _  _   _  _  _   _  _  _ 
  _  _  _   _  _  _   _  _  _ 
  6  1  8   9  7  _   4  3  5 
 
  7  8  6   2  3  5   _  1  4 
  1  5  4   7  9  6   8  _  3 
  2  3  9   8  4  _   _  _  _ 
 
 
 Solving board...SOLVED in 0,017 seconds.
 
  
  8  2  7   1  5  4   3  9  6 
  9  6  5   3  2  7   1  4  8 
  3  4  1   6  8  9   7  5  2 
 
  4  7  2   5  1  3   6  8  9 
  5  9  3   4  6  8   2  7  1 
  6  1  8   9  7  2   4  3  5 
 
  7  8  6   2  3  5   9  1  4 
  1  5  4   7  9  6   8  2  3 
  2  3  9   8  4  1   5  6  7 
 
 
  ----jGRASP: operation complete.
 	   
dirty-data.sdk

     ----jGRASP exec: java SudokuSolverEngine
 Initial board
  
  _  _  _   _  _  _   _  _  _ 
  1  6  7   9  8  5   4  2  3 
  _  _  -1   _  _  _   _  _  _ 
 
  _  _  _   _  _  _   _  _  _ 
  _  _  _   -1  _  _   _  _  _ 
  _  _  _   _  _  _   _  _  _ 
 
  _  _  _   _  _  2   _  _  _ 
  _  _  32   _  _  _   _  _  _ 
  _  _  _   _  _  _   _  _  _ 
 
 
 Solving board...The board cannot be solved.
  ----jGRASP: operation complete.
 	
valid-complete.sdk

     ----jGRASP exec: java SudokuSolverEngine
 Initial board
  
  5  3  4   6  7  8   9  1  2 
  6  7  2   1  9  5   3  4  8 
  1  9  8   3  4  2   5  6  7 
 
  8  5  9   7  6  1   4  2  3 
  4  2  6   8  5  3   7  9  1 
  7  1  3   9  2  4   8  5  6 
 
  9  6  1   5  3  7   2  8  4 
  2  8  7   4  1  9   6  3  5 
  3  4  5   2  8  6   1  7  9 
 
 
 Solving board...The board is already solved.
  ----jGRASP: operation complete.
    
   
 */