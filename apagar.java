import java.util.Scanner;
import java.util.Random;
public class apagar{


public static void main(String[] args) {


boolean n=false;
if(!n){

System.out.println("olaaaaaaaaa");

}
 int[][] validGrid = {
  {5,3,4,6,7,8,9,1,2},
  {6,7,2,1,9,5,3,4,8},
  {1,9,8,3,4,2,5,6,7},
  {8,5,9,7,6,1,4,2,3},
  {4,2,6,8,5,3,7,9,1},
  {7,1,3,9,2,4,8,5,6},
  {9,6,1,5,3,7,2,8,4},
  {2,8,7,4,1,9,6,3,5},
  {3,4,5,2,8,6,1,7,0}
};





int[][] test= validGrid;
int[][] test2= new int[9][9];


test[0][0  ]= 1;


System.out.println(validGrid[0][0]);






SudokuSolver s= new SudokuSolver(validGrid);


System.out.println(s.howManySolutions(30));
System.out.println(validGrid[8][8]);
System.out.println(s.findSolutions(1));
}


}
