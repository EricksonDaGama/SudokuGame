/**
 * A classe {@code Sudoku2} tem uma possivel resolucao da 2a fase do trabalho de IP.
 * Trata-se de um programa que permite ler, gerar e transformar quadriculas Sudoku.
 *
 * Compile:	javac Sudoku2.java
 * Execute:	java Sudoku2
 *
 * @author antonialopes IP1819
 */

import java.util.Scanner;
import java.util.Random;

class Sudoku2 {

	//constantes com opcoes do menu
	private static String [] OPCOES = {
			"Sair",
			"Aplicar permutacao de dois numeros",
			"Aplicar permutacao de duas linhas de uma mesma faixa horizontal",
			"Aplicar permutacao de duas colunas de uma mesma faixa vertical",
			"Aplicar permutacao de duas faixas horizontais",
			"Aplicar permutacao de duas faixas verticais",
			"Aplicar reflexao horizontal",
			"Aplicar reflexao vertical",
			"Indicar quadricula"
	};
	//num da opcao que serve para sair
	private static int OPCAO_SAIR = 0;

	//constantes com mensaens de erro para cada opcao do menu (caso exista)
	private static final String[] MSG_ERRO_ACCAO_OPCAO = {
			"",
			"",
			"Alvos incorretos para a transformacao escolhida.",
			"Alvos incorretos para a transformacao escolhida.",
			"Alvos incorretos para a transformacao escolhida.",
			"Alvos incorretos para a transformacao escolhida.",
			"",
			"",
			"Quadricula incorreta."
	};

	/**
	 * @param args
	 */
	public static void main(String [] args){
		Scanner leitor = new Scanner(System.in);

		int[][] quadricula = new int[9][9];

		preencheComFuncaoGQ(quadricula);
		System.out.println("Quadricula corrente: ");
		imprimeQuadricula(quadricula);

		int opcao;
		do{
			imprimirMenu(OPCOES);
			opcao = lerInteiroNoIntervalo(0, OPCOES.length, leitor,
					"Opcao invalida. Insira valor entre 0 e " + OPCOES.length);
			if (opcao != OPCAO_SAIR){
				boolean sucesso = executaAcao(opcao, quadricula, leitor);
				if (sucesso) {
					System.out.println("Quadricula corrente: ");
					imprimeQuadricula(quadricula);
				}
				else
					System.out.println(MSG_ERRO_ACCAO_OPCAO[opcao]);
			}
		}while (opcao != OPCAO_SAIR);

	}

	/**
	 * Executa acao correspondente a opcao dada
	 * @param opcao numero da opcao
	 * @param quadricula a quadricula sobre a qual a operacao e' efectuada
	 * @param leitor o canal de leitura ligado ao teclado
	 * @requires {@code quadricula} e' matriz 9x9
	 * @requires {@code 1 <= opcao <= OPCOES.length}
	 * @requires {@code leitor != null}
	 * @return se a operacao foi efectuada
	 */
	static boolean executaAcao(int opcao, int[][] quadricula, Scanner leitor) {
		boolean sucesso = true;

		switch(opcao){
		case 1 :
			permutaDoisNumeros(quadricula);
			break;
		case 2 :
			sucesso = permutaDuasLinhas(quadricula, leitor);
			break;
		case 3 :
			sucesso =  permutaDuasColunas(quadricula, leitor);
			break;
		case 4 :
			sucesso = permutaDuasFaixasHorizontais(quadricula, leitor);
			break;
		case 5 :
			sucesso = permutaDuasFaixasVerticais(quadricula, leitor);
			break;
		case 6 :
			reflecteHorizontal(quadricula);
			break;
		case 7 :
			reflecteVertical(quadricula);
			break;
		case 8 :
			sucesso = leQuadricula(quadricula, leitor);
			break;
		}
		return sucesso;
	}

	/**
	 * Preenche quadricula com valores lidos atraves do leitor dado
	 * ligado ao teclado
	 * @param quadricula a quadricula sobre a qual a operacao e' efectuada
	 * @param leitor o canal de leitura
	 * @requires {@code quadricula} e' matriz 9x9
	 * @requires {@code leitor != null}
	 * @return se a operacao foi efectuada
	 */
	static boolean leQuadricula(int[][] quadricula, Scanner leitor) {
		int[][] matrizLida = leMatriz(leitor, 9, 9);
		boolean valida = quadriculaValida(matrizLida);
		if (valida)
			copia(matrizLida, quadricula);
		return valida;
	}

	/**
	 * Copia o conteudo de uma array de arrays para outro
	 * @param origem array cujos valores vao ser copiados
	 * @param destino o array que vai receber os valores copiados
	 * @requires {@code origem, destino} tem as mesmas dimensoes
	 */
	static void copia(int[][] origem, int[][] destino) {
		for(int i = 0; i < origem.length; i++)
			for(int j = 0; j < origem[i].length; j++)
				destino[i][j] = origem[i][j];
	}

	/**
	 * Preenche uma quadricula com valores dados por funcao GQ
	 * @param quadricula a quadricula de Sudoku a preencher
	 * @requires {@code quadricula} e' matriz 9x9
	 */
	static void preencheComFuncaoGQ(int[][] quadricula){
		for(int i = 0; i < 9; i++)
			for(int j = 0; j < 9; j++)
				quadricula[i][j] = (i / 3 + 3 * (i % 3) + j) % 9 + 1;
	}

	/**
	 * Imprime a quadricula Sudoku no standard output
	 * @param quadricula a quadricula a imprimir
	 * @requires {@code quadricula} e' matriz 9x9
	 */
	static void imprimeQuadricula(int[][] quadricula) {
		for(int i = 0; i < 9; i++){
			if(i % 3 == 0)
				System.out.println("-------------------------");
			for(int j = 0; j < 9; j++){
				if(j % 3 == 0)
					System.out.print("| ");
				System.out.print(quadricula[i][j] + " ");
			}
			System.out.println("|");
		}
		System.out.println("------------------------");
	}


	/**
	 * Imprime menu com as opções possiveis
	 * @param opcoes as opcoes possiveis
	 * @requires {@code opcoes != null}
	 */
	static void imprimirMenu(String[] opcoes){
		System.out.println("Escolha opcao:");
		for(int i = 0; i < opcoes.length; i++)
			System.out.println("\t" + i + " - " + opcoes[i]);
		System.out.print("> ");
	}

	/**
	 * Verifica se uma matriz 9x9 e' uma quadricula Sudoku
	 * @param quadricula a quadricula a validar
	 * @requires {@code quadricula} e' matriz 9x9
	 * @return se quadricula e' uma quadricula Sudoku valida
	 */
	static boolean quadriculaValida(int[][] quadricula){
		//linhas ok
		for (int i = 0; i < 9; i++)
			if (!numeros1a9(quadricula[i]))
				return false;
		//colunas ok
		for (int i = 0; i < 9; i++)
			if (!numeros1a9(coluna(quadricula, i)))
				return false;
		//blocos ok
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++)
				if (!numeros1a9(bloco(quadricula, i, j)))
					return false;
		return true;
	}

	/**
	 * Obtem vetor com coluna da matriz
	 * @param matriz a matriz
	 * @param j o numero da coluna a obter
	 * @requires {@code matriz} e' matriz
	 * @requires {@code 0 <= j && j <= matriz.length }
	 * @return o vector contem os valores na coluna j da matriz
	 */
	static int[] coluna(int[][] matriz, int j) {
		int[] col = new int[matriz.length];
		for(int i = 0; i < col.length; i++)
			col[i] = matriz[i][j];
		return col;
	}

	/**
	 * Obtem vetor com bloco da matriz
	 * @param quadricula a quadricula
	 * @param l o numero da linha do bloco a obter
	 * @param c o numero da coluna do bloco a obter
	 * @requires {@code audricula} e' matriz
	 * @requires {@code 0 <= l,c < 3}
	 * @return o vector contem os valores no bloco k da quadricula
	 */
	static int[] bloco(int[][] quadricula, int l, int c) {
		int[] bloco = new int[9];
		for(int i = 0; i < 3; i++)
			for(int j=0; j < 3; j++)
				bloco[3*i+j] = quadricula[i+3*l][j+3*c];
		return bloco;
	}

	/**
	 * Verifica se o vector contem exatamente os valores de 1 a 9
	 * @param v vector a validar
	 * @requires {@code v != null}
	 * @return se o vector tem tamanho 9 e contem os valores de 1 a 9
	 */
	static boolean numeros1a9(int[] v) {
		if (v.length != 9)
			return false;

		boolean[] nums = new boolean[9];
		for (int x: v) {
			if (x >= 1 && x <= 9)
				nums[x-1] = true;
		}

		boolean result = true;
		for (int i = 0; i < 9; i++)
			result = result && nums[i];
		return result;
	}

	/**
	 * Le uma matriz de inteiros com valores lidos de um canal
	 * ligado a teclado (naa e' robusta face a outro input)
	 * @param leitor o canal de leitura a usar
	 * @param linhas o numero de linhas da matriz
	 * @param colunas o numero de colunas da matriz
	 * @requires {@code leitor != null}
	 * @ensures {@code\result} e' matriz linhasxcolunas
	 */
	static int[][] leMatriz(Scanner leitor, int linhas, int colunas){
		int[][] quadriculaLida = new int[linhas][colunas];
		System.out.println("Insira os valores da quadricula > ");
		for(int i = 0; i < linhas; i++)
			for(int j = 0; j < colunas; j++)
				quadriculaLida[i][j] = leitor.nextInt();
		return quadriculaLida;
	}

	/**
	 * Troca todas as ocorrencias de dois numeros aleatorios na quadricula
	 * @param quadricula a quadricula que contem os numeros a trocar
	 * @requires {@code audricula} e' matriz
	 */
	static void permutaDoisNumeros(int[][] quadricula){
		Random r = new Random(quadricula[0][0]); //fixada a semente para efeitos de teste
		//Os numeros a serem trocados
		int numero1 = r.nextInt(9) + 1;
		int numero2 = r.nextInt(9) + 1;
		permutaDoisNumeros(quadricula, numero1, numero2);
	}

	/**
	 * Troca dois numeros da quadricula dada
	 * @param quadricula a quadricula sobre a qual a operacao e' efectuada
	 * @param numero1 um dos numeros a trocar
	 * @param numero2 o outro dos números a trocar
	 * @requires {@code audricula} e' matriz
	 */
	static void permutaDoisNumeros(int[][] quadricula, int numero1, int numero2) {
		for (int i = 0; i < 9; i++)
			for (int j = 0; j < 9; j++)
				if (quadricula[i][j] == numero1)
					quadricula[i][j] = numero2;
				else if (quadricula[i][j] == numero2)
					quadricula[i][j] = numero1;
	}

	/**
	 * Troca duas linhas (de uma mesma faixa horizontal) da quadricula dada,
	 * sendo que as linhas sao lidas atraves do leitor dado	 (ligado ao teclado)
	 * @param quadricula a quadricula sobre a qual a operacao e' efectuada
	 * @param leitor o canal de leitura
	 * @requires {@code audricula} e' matriz
	 * @requires {@code leitor != null}
	 * @return se a operacao foi efectuada
	 */
	static boolean permutaDuasLinhas(int[][] quadricula, Scanner leitor){
		System.out.print("Indique os dois alvos de premutação > ");
		int alvo1 = leitor.nextInt();
		int alvo2 = leitor.nextInt();
		if (alvosDaMesmaFaixa(alvo1, alvo2)){
			permutaDuasLinhas(quadricula, alvo1, alvo2);
			return true;
		}
		return false;
	}

	/**
	 * Troca duas linhas (de uma mesma faixa horizontal) da quadricula dada
	 * @param quadricula a quadricula sobre a qual a operacao e' efectuada
	 * @param alvo1 o numero de uma das linhas a trocar
	 * @param alvo2 o numero de outra das linhas a trocar
	 * @requires {@code audricula} e' matriz
	 * @requires {@code alvosDaMesmaFaixa(alvo1, alvo2)}
	 */
	static void permutaDuasLinhas(int[][] quadricula, int alvo1, int alvo2) {
		int[] auxiliar = quadricula[alvo1-1];
		quadricula[alvo1 - 1] = quadricula[alvo2 - 1];
		quadricula[alvo2 - 1] = auxiliar;
	}

	/**
	 * Verifica se dois alvos sao da mesma faixa
	 * @param alvo1 o alvo 1
	 * @param alvo2 o alvo 2
	 * @return se os alvos são da mesma faixa
	 */
	static boolean alvosDaMesmaFaixa(int alvo1, int alvo2){
		return (alvo1 > 0 && alvo2 > 0 && alvo1 <= 3 && alvo2 <= 3) ||
				( alvo1 > 3 && alvo2 > 3 && alvo1 <=6 && alvo2 <=6) ||
				( alvo1 > 6 && alvo2 > 6 && alvo1 <= 9 && alvo2 <=9);
	}

	/**
	 * Troca duas colunas (de uma mesma faixa vertical) da quadricula dada,
	 * sendo que as colunas sao lidas atraves do leitor dado (ligado ao teclado)
	 * @param quadricula a quadricula sobre a qual a operacao e' efectuada
	 * @param leitor o canal de leitura
	 * @requires {@code audricula} e' matriz
	 * @requires {@code leitor != null}
	 * @return se a operacao foi efectuada
	 */
	static boolean permutaDuasColunas(int[][] quadricula, Scanner leitor){
		System.out.print("Indique os dois alvos de premutação > ");
		int alvo1 = leitor.nextInt();
		int alvo2 = leitor.nextInt();
		if (alvosDaMesmaFaixa(alvo1, alvo2)){
			permutaDuasColunas(quadricula, alvo1, alvo2);
			return true;
		}
		return false;
	}

	/**
	 * Troca duas  colunas (de uma mesma faixa vertical) da quadricula dada
	 * @param quadricula a quadricula sobre a qual a operacao e' efectuada
	 * @param alvo1 o numero de uma das colunas a trocar
	 * @param alvo2 o numero de outra das colunas a trocar
	 * @requires {@code audricula} e' matriz
	 * @requires {@code alvosDaMesmaFaixa(alvo1, alvo2)}
	 */
	static void permutaDuasColunas(int[][] quadricula, int alvo1, int alvo2) {
		for(int i = 0; i < 9; i++){
			int auxiliar = quadricula [i][alvo1 - 1];
			quadricula[i][alvo1 - 1] = quadricula[i][alvo2 - 1];
			quadricula[i][alvo2 - 1] = auxiliar;
		}
	}

	/**
	 * Troca duas faixas horizontais da quadricula dada,
	 * sendo que as faixas sao lidas atraves do leitor dado (ligado ao teclado)
	 * @param quadricula a quadricula sobre a qual a operacao e' efectuada
	 * @param leitor o canal de leitura
	 * @requires {@code audricula} e' matriz
	 * @requires {@code leitor != null}
	 * @return se a operacao foi efectuada
	 */
	static boolean permutaDuasFaixasHorizontais(int[][] quadricula, Scanner leitor){
		System.out.print("Indique os dois alvos de premutacao > ");
		int alvo1 = leitor.nextInt();
		int alvo2 = leitor.nextInt();
		if (1 <= alvo1 && alvo1 <= 3 && 1 <= alvo2 && alvo2 <= 3){
			permutaDuasFaixasHorizontais(quadricula, alvo1, alvo2);
			return true;
		}
		return false;
	}

	/**
	 * Troca duas faixas horizontais
	 * @param quadricula a quadricula sobre a qual a operacao e' efectuada
	 * @param alvo1 o numero de uma das faixas a trocar
	 * @param alvo2 o numero de outra das faixas a trocar
	 * @requires {@code audricula} e' matriz
	 * @requires {@code 1 <= alvo1 && alvo1 <= 3 && 1 <= alvo2 && alvo2 <= 3}
	 */
	static void permutaDuasFaixasHorizontais(int[][] quadricula, int alvo1, int alvo2) {
		for(int i = 0; i < 3; i++){
			int[] auxiliar = quadricula[(alvo1 - 1) * 3 + i];
			quadricula[(alvo1 - 1) * 3 + i] = quadricula[(alvo2 - 1) * 3 + i];
			quadricula[(alvo2 - 1) * 3 + i] = auxiliar;
		}
	}

	/**
	 * Troca duas faixas verticais, lidas atraves do leitor dado
	 * @param quadricula a quadricula sobre a qual a operacao e' efectuada
	 * @param leitor o canal de leitura
	 * @requires {@code audricula} e' matriz
	 * @requires {@code leitor != null}
	 * @return se a operacao foi efectuada
	 */
	static boolean permutaDuasFaixasVerticais(int[][] quadricula, Scanner leitor){
		System.out.print("Indique os dois alvos de premutacao > ");
		int alvo1 = leitor.nextInt();
		int alvo2 = leitor.nextInt();
		if (1 <= alvo1 && alvo1 <= 3 && 1 <= alvo2 && alvo2 <= 3){
			permutaDuasFaixasVerticais(quadricula, alvo1, alvo2);
			return true;
		}
		return false;
	}

	/**
	 * Troca duas faixas verticais
	 * @param quadricula a quadricula sobre a qual a operacao e' efectuada
	 * @param alvo1 o numero de uma das faixas a trocar
	 * @param alvo2 o numero de outra das faixas a trocar
	 * @requires {@code audricula} e' matriz
	 * @requires {@code 1 <= alvo1 && alvo1 <= 3 && 1 <= alvo2 && alvo2 <= 3}
	 */
	static void permutaDuasFaixasVerticais(int[][] quadricula, int alvo1, int alvo2) {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 9; j++){
				int auxiliar = quadricula[j][(alvo1-1)*3+i];
				quadricula[j][(alvo1-1)*3+i] = quadricula[j][(alvo2-1)*3+i];
				quadricula[j][(alvo2-1)*3+i] = auxiliar;
			}
		}
	}

	/**
	 * Reflecte a quadricula dada pelo eixo dos x
	 * e a linha do meio permanece igual
	 * @param a quadricula a reflectir
	 * @requires {@code audricula} e' matriz
	 */
	static void reflecteHorizontal(int[][] quadricula){
		int [] auxiliar;
		for (int i = 0; i <= 4; i++){
			auxiliar = quadricula[9 - i - 1];
			quadricula[9 - i - 1] = quadricula[i];
			quadricula[i] = auxiliar;
		}
	}

	/**
	 * Reflecte, a quadricula dada, pelo eixo dos y
	 * e a coluna do meio permanece igual
	 * @param a quadricula a reflectir
	 * @requires {@code audricula} e' matriz
	 */
	static void reflecteVertical(int[][] quadricula){
		int auxiliar;
		for (int j = 0; j <= 3; j++)
			for (int i = 0; i < 9; i++){
				auxiliar = quadricula[i][9 - j - 1];
				quadricula[i][9 - j - 1] = quadricula[i][j];
				quadricula[i][j] = auxiliar;
			}
	}

	/**
	 * Le um numero inteiro em [min,max] usando leitor ligado ao teclado.
	 * Verifica a condicao e, se invalida,
	 * repete a leitura depois de mostrar a mensagem de erro.
	 * @param min o minimo do intervalo
	 * @param max o maximo do intervalo
	 * @param leitor o canald de leitura
	 * @param msgErro - message to print for values {@code  > max || < min}
	 * @return o inteiro lido
	 * @requires {@code min < max && errorMsg != null && leitor != null}
	 * @ensures {@code  min <= \return <= max}
	 */
	static int lerInteiroNoIntervalo(int min, int max, Scanner leitor, String msgErro){
		int lido;
		boolean erro;
		do{
			lido = leitor.nextInt();
			erro = (lido > max || lido < min);
			if (erro)
				System.out.println(msgErro);
		} while(erro);
		return lido;
	}

}
