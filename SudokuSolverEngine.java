import java.io.*;
public class SudokuSolverEngine {

   public static void main(String[] args) throws FileNotFoundException {
      // Here I have called my class `MySudokuBoard` if you named your class
      // differently, modify the line below to use your own class name
      SudokuBoard board = new SudokuBoard("boards/valid-complete.sdk");
      System.out.println("Initial board");
      System.out.println(board);
      System.out.println();

      System.out.print("Solving board...");
      long start = System.currentTimeMillis();
      if(!board.isValid())
         System.out.print("The board cannot be solved.");
      else if(board.isSolved())
         System.out.print("The board is already solved."); 
      else{   
      board.solve();
      long stop = System.currentTimeMillis();
      System.out.printf("SOLVED in %.3f seconds.\n", ((stop-start)/1000.0));
      System.out.println();
      System.out.println(board);
      }      
   }
}