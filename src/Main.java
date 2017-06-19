import java.util.ArrayList;
import java.util.Scanner;

import com.sun.org.apache.bcel.internal.generic.ARRAYLENGTH;

import java.lang.Math;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main {

	public static void percorre(int d, Vertices[][] matrizLeitura, Vertices[][] matrizTratada) {
		int i, j, cont = 0, fim = 0;
		int k = (d * 2), l = 0;
		int inicioL = 0;
		int inicioC = 0;
		int tamanhoQuadrante = (int) Math.sqrt(d);
		while (fim == 0) {
			l = 0;
			for (i = inicioL; i < tamanhoQuadrante + inicioL; i++) {

				for (j = inicioC; j < tamanhoQuadrante + inicioC; j++) {
					matrizTratada[k][l].setConteudoDoVertice(matrizLeitura[i][j].getConteudoDoVertice());
					matrizTratada[k][l].setNumeroVertice(matrizLeitura[i][j].getNumeroVertice());
					matrizTratada[k][l].setFixo(matrizLeitura[i][j].isFixo());

					l++;
				}
			}
			if (inicioC + tamanhoQuadrante < d) {
				inicioC += tamanhoQuadrante;
			} else {
				inicioC = 0;
				inicioL += tamanhoQuadrante;
			}

			if (inicioL > d - 1) {
				fim = 1;
			}
			k++;

		}
	}

	public static void alocaMatriz(int n, int m, Vertices[][] matriz) {
		System.out.println(" " + n);
		System.out.println(" " + m);

		int cont = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				matriz[i][j] = new Vertices(cont);
				cont++;
			}
		}
	}

	public static void imprimirMatrizSudoku(int n, int m, Vertices[][] matrizLeitura) {
		System.out.println("imprimir matriz");
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				System.out.print(" " + matrizLeitura[i][j].isFixo());
			}
			System.out.println();
		}
	}

	public static void trataMatriz(int n, int linha, Vertices[][] matrizLeitura, Vertices[][] matrizTratada) {
		int i = 0;
		for (i = 0; i < linha; i++) {
			for (int j = 0; j < linha; j++) {
				matrizTratada[i][j].setConteudoDoVertice(matrizLeitura[i][j].getConteudoDoVertice());
				matrizTratada[i][j].setNumeroVertice(matrizLeitura[i][j].getNumeroVertice());
				matrizTratada[i][j].setFixo(matrizLeitura[i][j].isFixo());

				matrizTratada[i + linha][j].setConteudoDoVertice(matrizLeitura[j][i].getConteudoDoVertice());
				matrizTratada[i + linha][j].setNumeroVertice(matrizLeitura[j][i].getNumeroVertice());
				matrizTratada[i + linha][j].setFixo(matrizLeitura[j][i].isFixo());

			}

			percorre(linha, matrizLeitura, matrizTratada);

		}
	}

	public static void leitura(int linhaSize, int colunaSize, Vertices[][] matriz, Vertices[] cores) {
		Scanner ler = new Scanner(System.in);
		String linha;
		// System.out.printf("Informe o nome de arquivo texto:\n");
		String nome = "sudoku.txt";
		char[] letras;
		System.out.printf("\n Conteudo do arquivo texto:\n");
		try {
			FileReader arq = new FileReader(nome);
			BufferedReader lerArq = new BufferedReader(arq);

			linha = lerArq.readLine(); // lê a primeira linha
			// a variável "linha" recebe o valor "null" quando o processo
			// de repetição atingir o final do arquivo texto
			letras = linha.toCharArray();
			int a = 0;
			for (int i = 0; i < letras.length; i++) {
				System.out.print(letras[a]);
			}

			/// ystem.out.println(linha);
			for (int i = 0; i < linhaSize; i++) {
				for (int j = 0; j < colunaSize; j++) {
					matriz[i][j].setConteudoDoVertice(Character.getNumericValue(letras[a]));
					if (Character.getNumericValue(letras[a]) > 0) {
						matriz[i][j].setFixo(true);

					}
					cores[a].setConteudoDoVertice(matriz[i][j].getConteudoDoVertice());
					cores[a].setFixo(matriz[i][j].isFixo());

					a++;
				}
			}

			arq.close();
		} catch (IOException e) {
			System.err.printf("Erro na abertura do arquivo: %s.\n", e.getMessage());
		}

		System.out.println();

	}

	public static void alocaVetorDeCores(int numeroDeVertices, Vertices[] cores) {
		for (int i = 0; i < numeroDeVertices; i++)
			cores[i] = new Vertices(i + 1);
	}

	public static void main(String[] args) {
		Scanner lerTeclado = new Scanner(System.in);
		int linha = lerTeclado.nextInt();
		int m = linha * 3;
		Grafo grafo = new Grafo(linha, m);
		System.out.println(" o numero de arestas do grafo é" + grafo.nArestas + " o numero de vertices do grafo é "
				+ grafo.nVertices);
		// grafo.imprimirGrafo();
		Vertices[][] matrizLeitura = new Vertices[linha][linha];
		alocaMatriz(linha, linha, matrizLeitura);
		Vertices[] cores = new Vertices[linha * linha];
		alocaVetorDeCores(linha * linha, cores);

		leitura(linha, linha, matrizLeitura, cores);
		Vertices[][] matrizNew = new Vertices[m][linha];
		alocaMatriz(m, linha, matrizNew);
		imprimirMatrizSudoku(linha, linha, matrizLeitura);
		System.out.println("");
		trataMatriz(m, linha, matrizLeitura, matrizNew);
		// imprimirMatrizSudoku(m, linha, matrizNew);
		// grafo.imprimirGrafo();
		grafo.grafoPreencher(linha, matrizNew);
		//grafo.imprimirGrafo();
		
		System.out.println();
		for(int i = 0 ; i < linha*linha ; i++){
			System.out.print(" " +cores[i].getConteudoDoVertice());
		}
		System.out.println();
		grafo.AutoSolveBackTracking(true,0, cores);
		for(int i = 0 ; i < linha*linha ; i++){
			System.out.print(" " +cores[i].getConteudoDoVertice());
		}
		lerTeclado.close();

	}

}
