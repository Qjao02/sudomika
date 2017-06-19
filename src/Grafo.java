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

	public void printCores(Vertices[] cores) {

		for (int i = 0; i < this.nVertices; i++) {
			System.out.print(" " + cores[i].getConteudoDoVertice());

		}
		System.out.println();

	}

	public boolean AutoSolveBackTracking(boolean acabou, int i, Vertices[] cores) {
		// System.out.println("executando funcao");

		do {

			cores[i].setConteudoDoVertice(candidatesColorNode(i, cores));
			if (cores[i].getConteudoDoVertice() == 0) {
				return false;
			}
			if ((this.nVertices - 1) == i) {
				this.printCores(cores);
				return true;

			} else {
				//if (!cores[i + 1].isFixo()) {
					acabou = AutoSolveBackTracking(acabou, i + 1, cores);
					if (acabou) {
						return true;
					}
				/*} else {
					System.out.println("não chamou o i+1");
					int prox = proxValidVertice(i + 1, cores);
					i = prox;
					System.out.println(prox);
					acabou = AutoSolveBackTracking(acabou, i, cores);
					if (acabou) {
						return true;
					}
				}
				*/

			}
		} while (true);
	}

	public int proxValidVertice(int i, Vertices[] cores) {
		int jump = i;
		while (cores[jump].isFixo())
			jump++;

		return jump;
	}

	public int candidatesColorNode(int i, Vertices[] cores) {

		int coluna, linha;
		do {
			int altera = 0;
			// System.out.println("executando prox validate");

			cores[i].setConteudoDoVertice(((cores[i].getConteudoDoVertice() + 1) % (this.tamanhoDoQuadrante + 1)));
			// System.out.println(cores[i].getConteudoDoVertice());
			if (cores[i].getConteudoDoVertice() == 0)
				return 0;
			for (linha = 0; linha < this.nArestas; linha++) {
				if (this.matriz[linha][i].getConteudoDoVertice() != -1) {
					// System.out.println("é igual em " + linha);
					for (coluna = 0; coluna < this.nVertices; coluna++) {
						if (this.matriz[linha][coluna].getConteudoDoVertice() != -1
								&& cores[i].getConteudoDoVertice() == cores[coluna].getConteudoDoVertice()
								&& i != coluna) {
							altera = 1;
							// System.out.println(altera);
							break;
						}
					}
				}
			}
			if (altera == 0 && cores[i].getConteudoDoVertice() != 0) {
				return cores[i].getConteudoDoVertice();
			}
		} while (true);
	}

	/*
	 * public boolean bt(int i, Vertices[] cores,ArrayList<Integer> candidates)
	 * {
	 * 
	 * if (i == this.nVertices + 1) { return true; } else {
	 * 
	 * this.findCandidates(i,candidates); //if (candidates.size() > 0) { //
	 * this.addChiandidates(candidates.get(0), i); if
	 * (!(bt(this.proxColorValidate(i, cores), cores,candidates))) { for (int
	 * aux = 1; aux < candidates.size() ; aux++) {
	 * this.addCandidates(candidates.get(aux), i); if
	 * (bt(this.proxColorValidate(i, cores), cores,candidates)) { break;
	 * 
	 * } else { return false; } } }
	 * 
	 * } else { return false; }
	 * 
	 * } return true; }
	 * 
	 * public int proxColorValidate(int i, Vertices[] cores) { while
	 * (cores[i].isFixo()) i++;
	 * 
	 * return i; }
	 * 
	 * public void addCandidates(int candidate, int vertice) { for (int i = 0; i
	 * < this.nArestas; i++) { if
	 * (this.matriz[i][vertice].getConteudoDoVertice() != -1) {
	 * this.matriz[i][vertice].setConteudoDoVertice(candidate); } } }
	 * 
	 * public void findCandidates(int vertice, ArrayList<Integer> candidates) {
	 * for (int i = 0; i < this.nArestas; i++) { if
	 * (this.matriz[i][vertice].getConteudoDoVertice() != -1) { for (int j = 0;
	 * j < this.nArestas; j++) { if (this.matriz[i][j].getConteudoDoVertice() !=
	 * 0 && this.matriz[i][j].getConteudoDoVertice() != -1) {
	 * candidates.add(this.matriz[i][j].getConteudoDoVertice()); } }
	 * 
	 * } }
	 * 
	 * }
	 */
}