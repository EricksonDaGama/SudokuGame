/**
 * A classe {@code Sudoku2} tem uma possivel resolucao da 2a fase
 * do trabalho de IP, sobre a leitura e verificacao de uma quadricula 9x9
 * verificando se a mesma e valida. E sobre a geracao de quadriculas fornecidas
 * no enunciado do trabalho.
 *
 * Compile:	javac Sudoku2.java
 * Execute:	java Sudoku2
 *
 * @author Erickson da Gama Morais Cacondo
 * Número do aluno: 53653
 * @author Thiago Tótola Valadares Duarte
 * Número: 53636.
  */



public class SudokuGame{

private SudokuPuzzle puzzle;
private SudokuPuzzle puzzle2;
public SudokuGame(SudokuPuzzle puzzle){

this.puzzle =puzzle;
this.puzzle2=puzzle;


}


public boolean revealed (int l, int c){

if(l >=0 && l<=8 && c>=0 && c<=8){
return puzzle.revealed(l,c);
}
return false;
}

public boolean filled(int l,int c){

boolean v=false;
if(puzzle.revealed(l,c)==true){
 return true;
}
 if(puzzle.revealed(l,c)==false){
 if(puzzle2.cellValue(l,c) !=puzzle.cellValue(l,c)){
   System.out.println(puzzle2.cellValue(l,c)+ puzzle.cellValue(l,c));
 return true;
}

  else{
      System.out.println(puzzle2.cellValue(l,c)+ puzzle.cellValue(l,c));
    return false;
    
  }
}


return false;
}


public void fill(int l,int c,int value){

if(l >=0 && l <=8 && c>=0 && c<=8){
if(puzzle.revealed(l,c)== false ){

  puzzle2.grid[l][c]=value;



}


}



}


public void unfill(int l,int c){

if(l >=0 && l <=8 && c>=0 && c<=8){
  if(puzzle.revealed(l,c)== true ){

  puzzle.grid[l][c]=0;



  }
}

}


public int value(int l, int c){



  return (puzzle.cellValue(l,c) !=0) ? puzzle.cellValue(l,c):0;








}



public boolean isCorrect(){


return true;

}




public boolean isCorrectSoFar(){


return true;

}






}
