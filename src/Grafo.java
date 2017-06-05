import java.lang.Math;
public class Grafo {
	public int nArestas;
	public int nVertices;
	public int[][] matriz;
	
	
	
	
	
	
	public Grafo(int n , int m){
		this.nArestas = m;
		this.nVertices = n*n;
		this.matriz = new int[m][n * n];
		
		
	}
	
	
	public void grafoPreencher(int n , int m,int matrizLeitura){
		int i = 0;
		int j = 0;
		int verticeAtual = 0;
		
		while(verticeAtual != this.nVertices){
			for(i = 0 ; i < this.nArestas ; i++){
				this.matriz[i][verticeAtual] =  1;
			}
		}
		
	}
	
	
	
}