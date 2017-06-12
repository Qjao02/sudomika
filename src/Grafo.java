import java.lang.Math;
import java.util.ArrayList;

public class Grafo {
	public int nArestas;
	public int nVertices;
	public int tamanhoDoQuadrante;
	public Vertices[][] matriz;

	public Grafo(int n, int m) {
		this.nArestas = m;
		this.nVertices = n * n;
		this.tamanhoDoQuadrante = n;
		this.matriz = new Vertices[this.nArestas][this.nVertices];
		this.alocarMatriz();

	}

	public void grafoPreencher(int linha, Vertices[][] matriz) {

		for (int i = 0; i < this.nArestas; i++) {
			for (int j = 0; j < linha; j++) {
				this.matriz[i][matriz[i][j].getNumeroVertice()] = matriz[i][j];
			}
		}
	}

	public void alocarMatriz() {

		for (int i = 0; i < this.nArestas; i++) {
			for (int j = 0; j < this.nVertices; j++) {
				this.matriz[i][j] = new Vertices(0);
				this.matriz[i][j].setConteudoDoVertice(-1);
			}
		}
	}

	public void imprimirGrafo() {
		for (int i = 0; i < this.nArestas; i++) {
			for (int j = 0; j < this.nVertices; j++) {
				// if (this.matriz[i][j].getConteudoDoVertice() >= 0)
				System.out.print(" " + this.matriz[i][j].getConteudoDoVertice());

			}
			System.out.println();
		}
	}

	public ArrayList<Vertices> linearizarMatriz() {
		ArrayList<Vertices> matrizLinearizada = new ArrayList<Vertices>();
		for (int i = 0; i < this.nArestas; i++) {
			for (int j = 0; j < this.nVertices; j++) {
				matrizLinearizada.add(this.matriz[i][j]);
			}
		}

		return matrizLinearizada;
	}

	public boolean autoSolveBackTracking(int i , int vertice){
		int aux;
		ArrayList<Integer> candidates = this.findACandidate(vertice);
		if(candidates.size() != 0){
			 aux = candidates.get(0);
			this.addSolution(vertice, aux);
			this.removeFromCandidates(candidates,aux);
			moveNext
		}else{
			
		}
		
		
	
		
		return true;
	}

	public ArrayList<Integer> findACandidate(int coluna) {
		int cont = 1;
		ArrayList<Integer> candidates = new ArrayList<>();
		for (int i = 0; i < this.tamanhoDoQuadrante; i++) {
			candidates.add(cont++);
		}
		System.out.println(candidates);
		for (int i = 0; i < this.nArestas; i++) {
			if (this.matriz[i][coluna].getConteudoDoVertice() != -1) {
				for (int k = 0; k < nVertices; k++) {
					candidates.remove(this.matriz[i][k].getConteudoDoVertice());
				}
			}
		}
		return candidates;
	}

	public void addSolution(int vertice, Integer k) {
		for (int i = 0; i < this.nArestas; i++) {
			this.matriz[i][vertice].setConteudoDoVertice(k);
		}
	}

	public void removeFromCandidates(ArrayList<Integer> candidates, int remover) {
		int index = 0;
		for (int a : candidates) {
			if (a == remover) {
				candidates.remove(index);

			} else {
				index++;
			}
		}

	}

}