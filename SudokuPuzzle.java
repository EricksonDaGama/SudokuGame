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

public class SudokuPuzzle{

public static int[][] grid;

public static boolean[][] filter;



/**
* funcao que valida se uma quadricula é valida para contruir uma quadricula e se uma matriz e valida para definir pistas

* @param grid A matriz com os valores para validar
* @param filter A matriz para definir as pistas
* @requires {@code grid !=null}
* @requires {@code filter} e matriz 9x9
* @return se as matrizes forem validas

*/


  public static boolean valid(int[][] grid,boolean[][] filter){


   if(Sudoku2.quadriculaValida(grid)){

     //preencher matrix aux
     int[][] gridAux=copyMatriz(grid);
     gridAux=applyFilter(gridAux,filter);


     SudokuSolver s =new SudokuSolver(gridAux);


     return s.howManySolutions(2)==1;



   }

return false;


  }


  /**



  */

static int[][] copyMatriz(int[][] m){


  int[][] grid=new int[9][9];

  for(int i=0;i< grid.length;i++){

     for(int j=0;j<grid.length;j++){

       grid[i][j]=m[i][j];


     }


  }

  return grid;

}

/**



*/

static int[][] applyFilter(int[][] m,boolean[][] f){


  for(int i=0;i< m.length;i++){

     for(int j=0;j<m.length;j++){

       if(f[i][j]==false){

         m[i][j]=0;



       }


     }

  }



  return m;


}


/**



*/

  public SudokuPuzzle(int[][] grid, boolean[][] filter){

      this.grid=grid;
      this.filter=filter;


  }

/**
* funcao que da o valor da celula numa determinada linha e coluna
* @param l corresponde a linha do puzzel
* @param c corresponde a coluna do puzzel
* @requires {@code l >= 0 &&  l <=8 }
* @requires {@code c >=0 && c <=8 }
* @return Um determinado valor do puzzel

*/


  public  int cellValue(int l, int c){

if(l>=0 && l <=8 && c >=0 && c<=8){

    return this.grid[l][c];


}

return 0;

  }

/**
* Funcao que revala se um determinado valor de uma celula pertence a pista do Puzzle
* @param l corresponde a linha do Puzzle
* @param c corresponde a coluna do Puzzle
* @ requires {@code l >=0 && l<=8}
* @ requires {@code c >=0 && c<=8}
* @ return se o valor da celula  é dado como  como pista do Puzzle
*/


public  boolean revealed(int l, int c){



  return (this.filter[l][c]==true) ? true:false;



}


/**



*/

public String toString(){

String s;

Sudoku2.imprimeQuadricula(grid);

for(int i = 0; i < 9; i++){
  if(i % 3 == 0)
  System.out.println("-------------------------");
    for(int j = 0; j < 9; j++){
      if(j % 3 == 0)
      System.out.print("| ");
      if(this.filter[i][j]==true){
      System.out.print(this.grid[i][j] + " ");
  }
  else{
       System.out.print(" " + " ");

  }
  }
  System.out.println("|");
}
System.out.println("-------------------------");



return "";





}





}
