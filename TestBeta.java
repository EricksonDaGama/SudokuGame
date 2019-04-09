/**
 * Esta classe testa as funcoes e metodos descritos na API das classes SudokuPuzzle
 * e SudokuGame em alguns cenarios simples.
 * A API destas classes encontra-se no enunciado da terceiro fase do trabalho de
 * Introducao a Programacao (2018/19, FCUL).
 *
 * @author Joao Batista
 * @date 25 Nov 2018
 */
public class TestBeta {
	// O exemplo do enunciado
	private static int[][] validGrid = {
		{5,3,4,6,7,8,9,1,2},
		{6,7,2,1,9,5,3,4,8},
		{1,9,8,3,4,2,5,6,7},
		{8,5,9,7,6,1,4,2,3},
		{4,2,6,8,5,3,7,9,1},
		{7,1,3,9,2,4,8,5,6},
		{9,6,1,5,3,7,2,8,4},
		{2,8,7,4,1,9,6,3,5},
		{3,4,5,2,8,6,1,7,9}
	};

	// O exemplo do enunciado onde foi introduzido um erro na 7a linha
	private static int[][] invalidGrid = {
		{5,3,4,6,7,8,9,1,2},
	    {6,7,2,1,9,5,3,4,8},
	    {1,9,8,3,4,2,5,6,7},
	    {8,5,9,7,6,1,4,2,3},
	    {4,2,6,8,5,3,7,9,1},
	    {7,1,3,9,2,4,8,5,6},
	    {9,6,5,1,3,7,2,8,4},
	    {2,8,7,4,1,9,6,3,5},
	    {3,4,5,2,8,6,1,7,9}
	};

	// O filtro do exemplo do enunciado
	private static boolean[][] filter = {
		{true,true,false,false,true,false,false,false,false},
		{true,false,false,true,true,true,false,false,false},
		{false,true,true,false,false,false,false,true,false},
		{true,false,false,false,true,false,false,false,true},
		{true,false,false,true,false,true,false,false,true},
		{true,false,false,false,true,false,false,false,true},
		{false,true,false,false,false,false,true,true,false},
		{false,false,false,true,true,true,false,false,true},
		{false,false,false,false,true,false,false,true,true}
     };

	// Um outro filtro valido para a grelha do enunciado
	private static boolean[][] anotherFilter = new boolean[][] {
		{true,true,true,true,true,true,true,true,true},
		{true,true,true,true,true,true,true,true,true},
		{true,true,true,true,true,true,true,true,true},
		{true,true,true,true,true,true,true,true,true},
		{true,true,true,true,true,true,true,true,true},
		{true,true,true,true,true,true,true,true,true},
		{true,true,true,true,true,true,true,true,true},
		{true,true,true,true,true,true,true,true,true},
		{true,true,true,true,true,true,true,true,false}
	};

	// ainda um outro filtro, invalido para a grelha do enunciado
	private static boolean[][] badFilter = new boolean[][] {
		{true,false,false,false,false,false,false,false,false},
		{false,false,false,false,false,false,false,false,false},
		{false,false,false,false,false,false,false,false,false},
		{false,false,false,false,false,false,false,false,false},
		{false,false,false,false,false,false,false,false,false},
		{false,false,false,false,false,false,false,false,false},
		{false,false,false,false,false,false,false,false,false},
		{false,false,false,false,false,false,false,false,false},
		{false,false,false,false,false,false,false,false,true}
	};


	/**
	 * Executa os testes sobre as classes SudokuPuzzle e SudokuGame
	 * @param args
	 */
	public static void main(String[] args) {
		testSudokuPuzzle();
		testsSudokuGame();

	}

	/**
	 * Testa as diferentes funcoes e metodos da classe SudokuPuzzle:
	 * - static valid(int[][], boolean[][]);
	 * - cellValue(int, int);
	 * - revealed(int, int);
	 * - toString().
	 */
	private static void testSudokuPuzzle() {
		System.out.println(">>> Testes 'a classe SudokuPuzzle:");
		SudokuPuzzle g = new SudokuPuzzle(validGrid,filter);

		System.out.println("\n>> Testes 'a funcao SudokuPuzzle.valid(int[][], boolean[][]):");
		System.out.println("> Com uma quadricula valida: ");
		if(SudokuPuzzle.valid(validGrid, filter)) {
			System.out.println("OK");
		}else {
			System.out.println("ERRO: A funcao nao validou uma quadricula valida.");
		}

		System.out.println("> Com uma quadricula invalida: ");
		if(!SudokuPuzzle.valid(invalidGrid, filter)) {
			System.out.println("OK");
		}else {
			System.out.println("ERRO: A funcao validou uma quadricula invalida.");
		}

		System.out.println("> Com uma quadricula valida mas combinada "+
				"com filtro que da' mais que uma solucao: ");
		if(!SudokuPuzzle.valid(validGrid, badFilter)) {
			System.out.println("OK");
		}else {
			System.out.println("ERRO: A funcao validou uma combinacao quadricula+filtro invalida.");
		}


		System.out.println("\n>> Testes ao metodo SudokuPuzzle.cellValue(int, int): ");
		System.out.println("> Num espaco revelado:");
		int v1 = g.cellValue(2,2);
		if(v1 == 8) {
			System.out.println("OK");
		}else {
			System.out.println("ERRO: Foi lido o valor "+v1+" quando o valor esperado era 8.");
		}

		System.out.println("> Num espaco nao revelado: ");
		int v2 = g.cellValue(4,4);
		if(v2 == 5) {
			System.out.println("OK");
		}else {
			System.out.println("ERRO: Foi lido o valor "+v2+" quando o valor esperado era 5.");
		}


		System.out.println("\n>> Testes ao metodo SudokuPuzzle.revealed(int, int):");
		System.out.println("> Num espaco revelado:");
		boolean r1 = g.revealed(2,2);
		if(r1) {
			System.out.println("OK");
		}else {
			System.out.println("ERRO: A funcao indica que a posicao (2,2) nao se encontra revelada.");
		}

		System.out.println("> Num espaco nao revelado: ");
		boolean r2 = g.revealed(4,4);
		if(!r2) {
			System.out.println("OK");
		}else {
			System.out.println("ERRO: A funcao indica que a posicao (4,4) encontra-se revelada.");
		}


		System.out.println("\n>> Teste ao metodo SudokuPuzzle.toString():");
		System.out.println(g+"\n");



	}

	/**
	 * Executa testes os diferentes metodos da classe SudokuGame:
	 * - filled(int, int);
	 * - fill(int, int, int);
	 * - unfill(int, int);
	 * - isCorrectSoFar();
	 * - isCorrect();
	 * - toString().
	 */





	 private static void testsSudokuGame() {
	 		System.out.println(">>> Testes 'a classe SudokuGame:");

	 		SudokuPuzzle g = new SudokuPuzzle(validGrid,filter);
	 		SudokuGame game = new SudokuGame(g);

	 		SudokuPuzzle g2 = new SudokuPuzzle(validGrid,anotherFilter);
	 		SudokuGame almostComplete = new SudokuGame(g2);


	 		System.out.println("\n>> Testes ao metodo SudokuGame.filled(int, int):");
	 		System.out.println("> Num espaco revelado:");
	 		boolean r1 = game.filled(2,2);
	 		if(r1) {
	 			System.out.println("OK");
	 		}else {
	 			System.out.println("ERRO: A funcao indica que a posicao (2,2) nao se encontra revelada.");
	 		}

	 		System.out.println("> Num espaco nao revelado e nao preenchido pelo utilizador:");
	 		boolean r2 = game.filled(4,4);
	 		if(!r2) {
	 			System.out.println("OK");
	 		}else {
	 			System.out.println("ERRO: A funcao indica que a posicao (4,4) se encontra preenchida.");
	 		}

	 		System.out.println("> Num espaco preenchido pelo utilizador:");
	 		game.fill(0, 3, 6);
	 		boolean r3 = game.filled(0,3);
	 		if(r3) {
	 			System.out.println("OK");
	 		}else {
	 			System.out.println("ERRO: A funcao indica que a posicao (0,3) nao se encontra preenchida");
	 		}


	 		System.out.println("\n>> Testes ao metodo SudokuGame.value(int, int) e "+
	 										"SudokuGame.fill(int, int,int): ");
	 		System.out.println("> Num espaco revelado:");
	 		int v1 = game.value(2,2);
	 		if(v1 == 8) {
	 			System.out.println("OK");
	 		}else {
	 			System.out.println("ERRO: Foi lido o valor "+v1+" quando o valor esperado era 8.");
	 		}

	 		System.out.println("> Num espaco nao revelado e nao preenchido pelo utilizador: ");
	 		int v2 =  game.value(4,4);
	 		if(v2 == 0) {
	 			System.out.println("OK");
	 		}else {
	 			System.out.println("ERRO: Foi lido o valor "+v2+" quando o valor esperado era 0.");
	 		}

	 		System.out.println("> Num espaco preenchido pelo utilizador:");
	 		game.fill(0, 3, 6);
	 		int v3 = game.value(0,3);
	 		if(v3 == 6) {
	 			System.out.println("OK");
	 		}else {
	 			System.out.println("ERRO: Foi lido o valor "+v3+" de (0,3) quando o valor esperado era 6.");
	 		}


	 		System.out.println("\n>> Teste ao metodo SudokuGame.fill(int, int, int):");
	 		System.out.println("> Num espaco revelado:");
	 		game.fill(3, 0, 6);
	 		int f1 = game.value(3,0);
	 		if(f1 != 6) {
	 			System.out.println("OK");
	 		}else {
	 			System.out.println("ERRO: A funcao alterou um espaco revelado.");
	 		}

	 		System.out.println("> Num espaco nao revelado:");
	 		game.fill(8, 0, 3);
	 		int f2 = game.value(8,0);
	 		if(f2 == 3) {
	 			System.out.println("OK");
	 		}else {
	 			System.out.println("ERRO: A funcao nao alterou o espaco escolhido.");
	 		}


	 		System.out.println("\n>> Teste 'a funcao SudokuGame.unfill(int, int):");
	 		System.out.println("> Num espaco revelado:");
	 		game.unfill(3, 0);
	 		int u1 = game.value(3,0);
	 		if(u1 == 8) {
	 			System.out.println("OK");
	 		}else {
	 			System.out.println("ERRO: A funcao alterou um espaco revelado.");
	 		}

	 		System.out.println("> Num espaco nao revelado:");
	 		game.unfill(8, 0);
	 		int u2 = game.value(8,0);
	 		if(u2 == 0) {
	 			System.out.println("OK");
	 		}else {
	 			System.out.println("ERRO: A funcao nao alterou o espaco escolhido.");
	 		}




		}


}
