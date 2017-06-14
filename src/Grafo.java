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

	public void AutoSolveBackTracking(int i, Vertices[] cores) {
		ArrayList<Integer> candidates;
		if (!cores[i].isFixo()) {

			 candidates = this.findCandidates(i);
			cores[i].setConteudoDoVertice(candidates.get(0));
			this.addCandidates(candidates.get(0), i);
			this.bt(i + 1, cores,candidates);

		} else {
			i++;
		}

	}

	public boolean bt(int i, Vertices[] cores,ArrayList<Integer> candidates) {

		if (i == this.nVertices + 1) {
			return true;
		} else {

			candidates = this.findCandidates(i);
			if (candidates.size() > 0) {
				this.addCandidates(candidates.get(0), i);
				if (!(bt(this.proxColorValidate(i, cores), cores,candidates))) {
					for (int aux = 1; aux < candidates.size(); aux++) {
						this.addCandidates(candidates.get(aux), i);
						if (bt(this.proxColorValidate(i, cores), cores,candidates)) {
							break;

						} else {
							return false;
						}
					}
				}

			} else {
				return false;
			}

		}
		return true;
	}

	public int proxColorValidate(int i, Vertices[] cores) {
		while (cores[i].isFixo())
			i++;

		return i;
	}

	public void addCandidates(int candidate, int vertice) {
		for (int i = 0; i < this.nArestas; i++) {
			if (this.matriz[i][vertice].getConteudoDoVertice() != -1) {
				this.matriz[i][vertice].setConteudoDoVertice(candidate);
			}
		}
	}

	public ArrayList<Integer> findCandidates(int vertice) {
		ArrayList<Integer> candidates = new ArrayList<Integer>();
		for (int i = 0; i < this.nArestas; i++) {
			if (this.matriz[i][vertice].getConteudoDoVertice() != -1) {
				for (int j = 0; j < this.nArestas; j++) {
					if (this.matriz[i][j].getConteudoDoVertice() != 0
							&& this.matriz[i][j].getConteudoDoVertice() != -1) {
						candidates.add(this.matriz[i][j].getConteudoDoVertice());
					}
				}

			}
		}

		return candidates;
	}

}