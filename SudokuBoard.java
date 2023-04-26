// Anna Gracheva
// CS 143
// L.HW #2: Sudoku #2 (isValid, isSolved)
//
// This program will check if Sudoku is valid and solved

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
  ----jGRASP exec: java SudokuCheckerEngineV2
 Checking empty board...passed.
 Checking incomplete, valid board...passed.
 Checking complete, valid board...passed.
 Checking dirty data board...passed.
 Checking row violating board...passed.
 Checking col violating board...passed.
 Checking row&col violating board...passed.
 Checking mini-square violating board...passed.
 **** HORRAY: ALL TESTS PASSED ****
 
  ----jGRASP: operation complete.
 
 */