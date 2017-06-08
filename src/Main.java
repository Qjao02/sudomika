import java.util.Scanner;
import java.lang.Math;

public class Main {

	public static void percorre(int d, Vertices[][] matrizLeitura, Vertices[][] matrizTratada) {
		int i, j, cont = 0, fim = 0;
		int k = (d*2),l = 0;
		int inicioL = 0;
		int inicioC = 0;
		int tamanhoQuadrante = (int) Math.sqrt(d);
		while (fim == 0) {
			l = 0;
			for (i = inicioL; i < tamanhoQuadrante + inicioL; i++) {
				
				for (j = inicioC; j < tamanhoQuadrante + inicioC; j++) {
					matrizTratada[k][l] = matrizLeitura[i][j];
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
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				System.out.print(" " + matrizLeitura[i][j].getNumeroVertice());
			}
			System.out.println();
		}
	}

	public static void trataMatriz(int n, int linha, Vertices[][] matrizLeitura, Vertices[][] matrizTratada) {
		int i = 0;
		for (i = 0; i < linha; i++) {
			for (int j = 0; j < linha; j++) {
				matrizTratada[i][j] = matrizLeitura[i][j];
				matrizTratada[i + linha][j] = matrizLeitura[j][i];
			}
			
			
			percorre(linha, matrizLeitura, matrizTratada);

		}
	}

	

	public static void main(String[] args) {
		Scanner lerTeclado = new Scanner(System.in);
		int linha = lerTeclado.nextInt();
		int m = linha * 3;
		Grafo grafo = new Grafo(linha, m);
		System.out.println(" o numero de arestas do grafo é" + grafo.nArestas + " o numero de vertices do grafo é "
				+ grafo.nVertices);
		grafo.imprimirGrafo();
		Vertices[][] matrizLeitura = new Vertices[linha][linha];
		alocaMatriz(linha, linha, matrizLeitura);
		Vertices[][] matrizNew = new Vertices[m][linha];
		alocaMatriz(m, linha, matrizNew);
		//imprimirMatrizSudoku(linha,linha , matrizLeitura);
		System.out.println("");
		trataMatriz(m, linha, matrizLeitura, matrizNew);
		//imprimirMatrizSudoku(m, linha, matrizNew);

		 grafo.grafoPreencher(linha, matrizNew);
		 grafo.imprimirGrafo();


		lerTeclado.close();

	}

}
