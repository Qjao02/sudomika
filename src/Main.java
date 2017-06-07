import java.util.Scanner;

public class Main {
	
	
	public static void alocaMatriz(int n,int m, Vertices[][] matriz) {
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
	
	public static void imprimirMatrizSudoku(int n, int m, Vertices[][] matrizLeitura){
		for(int i = 0 ;  i < n ; i ++){
			for(int j = 0 ; j  < m ; j ++){
				System.out.print(" " + matrizLeitura[i][j].getConteudoDoVertice());
			}
			System.out.println();
		}
	}
	
	public static void trataMatriz(int n,int linha,Vertices[][] matrizLeitura,Vertices[][] matrizTratada){
		
		for(int i = 0 ; i < n ; i++){
			for(int j = 0 ; j < n ; j++){
				matrizTratada[i][j] = matrizLeitura[i][j];
			}
		}
		
		for(int i = n ; i<n*2 ; i++){
			for(int j = 0 ; j < n ; j++){
				matrizTratada[i][j] = matrizLeitura[j][i];
			}
		}
		
	
	}
	
	

	public static void main(String[] args) {
		Scanner lerTeclado = new Scanner(System.in);
		int linha = lerTeclado.nextInt();
		int m = linha * 3;
		Grafo grafo = new Grafo(linha, m);
		System.out.println(" o numero de arestas do grafo é" + grafo.nArestas + " o numero de vertices do grafo é "
				+ grafo.nVertices);

		Vertices[][] matrizLeitura = new Vertices[linha][linha];
		alocaMatriz(linha,linha,matrizLeitura);
		imprimirMatrizSudoku(linha,linha,matrizLeitura);
		Vertices[][] matrizNew = new Vertices[m][linha];
		alocaMatriz(m,linha, matrizNew);
		
		trataMatriz(m, linha, matrizLeitura, matrizNew);
		//imprimirMatrizSudoku(matrizNew);
		
		
		// grafo.grafoPreencher();
		// grafo.imprimirGrafo();
		lerTeclado.close();

	}

}
