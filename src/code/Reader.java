package code;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JFileChooser;

import javafx.stage.FileChooser;

public class Reader {

	public static void alocaMatriz(int n, int m, Vertices[][] matriz) {

		int cont = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				matriz[i][j] = new Vertices(cont);
				cont++;
			}
		}
	}

	public static void alocaVetorDeCores(int numeroDeVertices, Vertices[] cores) {
		for (int i = 0; i < numeroDeVertices; i++)
			cores[i] = new Vertices(i + 1);
	}

	public static void imprimirMatrizSudoku(int n, int m, Vertices[][] matrizLeitura) {
		System.out.println("imprimir matriz");
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				System.out.print(" " + matrizLeitura[i][j].getConteudoDoVertice());
			}
			System.out.println();
		}
	}

	public static void leitura(int linhaSize, int colunaSize, Vertices[][] matriz, Vertices[] cores,int max) {
		Scanner ler = new Scanner(System.in);
		String linha = "blabla";
		String nome = "sudoku.txt";
		FileChooser filechooser = new FileChooser();
		
		char[] letras;
		try {
			File file = filechooser.showOpenDialog(null); 
			FileReader arq = new FileReader(file);
			BufferedReader lerArq = new BufferedReader(arq);
			
			Random st = new Random();
			for (int i = 0; i < max; i++) {
				linha = lerArq.readLine();

			}

			

			letras = linha.toCharArray();
			int a = 0;
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
			System.out.println("Erro na abertura do arquivo: %s.\n" + e.getMessage());
		}
	}

	public static void percorre(int d, Vertices[][] matrizLeitura, Vertices[][] matrizTratada) {
		int i, j, fim = 0;
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
		}
		percorre(linha, matrizLeitura, matrizTratada);

	}
}
