import java.lang.Math;

public class Grafo {
	public int nArestas;
	public int nVertices;
	public int tamanhoDoQuadrante;
	public int[][] matriz;

	public Grafo(int n, int m) {
		this.nArestas = m;
		this.nVertices = n * n;
		this.matriz = new int[m][n * n];
		this.tamanhoDoQuadrante = n;
		
	}

	public void grafoPreencher() {

		int verticeAtual = 0;
		
		for(int i = 0 ; i < this.nArestas ; i++ ){
			for(int j = 0 ; j < this.nVertices ; j++){
				
				
			}
		}
		
	}

	public void imprimirGrafo() {
		for (int i = 0; i < this.nArestas; i++) {
			for (int j = 0; j < this.nVertices; j++) {
				System.out.print(" " + this.matriz[i][j]);
			}
			System.out.println();
		}
	}

}