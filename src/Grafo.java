public class Grafo {
	public Vertices[][] matriz;
	public int nArestas;
	public int nVertices;
	public int tamanhoDoQuadrante;

	public Grafo(int n, int m) {
		this.nArestas = m;
		this.nVertices = n * n;
		this.tamanhoDoQuadrante = n;
		this.matriz = new Vertices[this.nArestas][this.nVertices];
		this.alocarMatriz();

	}

	public void alocarMatriz() {

		for (int i = 0; i < this.nArestas; i++) {
			for (int j = 0; j < this.nVertices; j++) {
				this.matriz[i][j] = new Vertices(0);
				this.matriz[i][j].setConteudoDoVertice(-1);
			}
		}
	}

	public void AutoSolveBackTracking(Vertices[] cores) {

		int i = 0;
		int candidate = 1;
		Pilha pilha = new Pilha();
		do {

			while (cores[i].isFixo())
				i++;

			candidate = candidatesColorNode(i, cores, candidate);
			if (candidate == 0) {
				candidate = pilha.remove();
				inserePossivelSolucao(0, i);
				candidate++;
				i--;
				while (cores[i].isFixo()) {
					i--;
				}
			} else {
				pilha.insere(candidate);
				cores[i].setConteudoDoVertice(candidate);
				inserePossivelSolucao(candidate, i);
				i++;
				candidate = 1;
			}
		} while (i < this.nVertices);
	}

	public int candidatesColorNode(int cor, Vertices[] cores, int wrong) {
		
		int control;
		if (wrong > 9) {
			return 0;
		}
		do {
			control = findCandidate(cor, wrong);
			if (control == 0)
				wrong++;
			if (wrong > this.tamanhoDoQuadrante)
				return 0;
		} while (control == 0);
		wrong = control;
		return wrong;
	}

	public int findCandidate(int cor, int wrong) {
		int cont = 0;
		for (int i = 0; i < this.nArestas; i++) {
			if (this.matriz[i][cor].getConteudoDoVertice() != -1) {
				for (int j = 0; j < this.nVertices; j++) {
					if (this.matriz[i][j].getConteudoDoVertice() == wrong) {
						if (this.matriz[i][j].getConteudoDoVertice() != -1)
							cont++;
						if (cont >= this.tamanhoDoQuadrante)
							break;
						return 0;
					}
				}
			}
		}

		return wrong;
	}

	public void grafoPreencher(int linha, Vertices[][] matriz) {

		for (int i = 0; i < this.nArestas; i++) {
			for (int j = 0; j < linha; j++) {
				this.matriz[i][matriz[i][j].getNumeroVertice()] = matriz[i][j];
			}
		}
	}

	public void imprimirGrafo() {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < this.nVertices; j++) {
				if (this.matriz[i][j].getConteudoDoVertice() >= 0)
					System.out.print(" " + this.matriz[i][j].getConteudoDoVertice());

			}
			System.out.println();
		}
	}

	public void inserePossivelSolucao(int candidate, int index) {

		for (int i = 0; i < this.nArestas; i++) {
			if (this.matriz[i][index].getConteudoDoVertice() != -1)
				this.matriz[i][index].setConteudoDoVertice(candidate);
		}
	}

	public void printCores(Vertices[] cores) {

		for (int i = 0; i < this.nVertices; i++) {
			System.out.print(" " + cores[i].getConteudoDoVertice());
		}
		System.out.println();
	}

}

/*
 * public boolean bt(int i, Vertices[] cores,ArrayList<Integer> candidates) {
 * 
 * if (i == this.nVertices + 1) { return true; } else {
 * 
 * this.findCandidates(i,candidates); //if (candidates.size() > 0) { //
 * this.addChiandidates(candidates.get(0), i); if
 * (!(bt(this.proxColorValidate(i, cores), cores,candidates))) { for (int aux =
 * 1; aux < candidates.size() ; aux++) { this.addCandidates(candidates.get(aux),
 * i); if (bt(this.proxColorValidate(i, cores), cores,candidates)) { break;
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
 * public void addCandidates(int candidate, int vertice) { for (int i = 0; i <
 * this.nArestas; i++) { if (this.matriz[i][vertice].getConteudoDoVertice() !=
 * -1) { this.matriz[i][vertice].setConteudoDoVertice(candidate); } } }
 * 
 * public void findCandidates(int vertice, ArrayList<Integer> candidates) { for
 * (int i = 0; i < this.nArestas; i++) { if
 * (this.matriz[i][vertice].getConteudoDoVertice() != -1) { for (int j = 0; j <
 * this.nArestas; j++) { if (this.matriz[i][j].getConteudoDoVertice() != 0 &&
 * this.matriz[i][j].getConteudoDoVertice() != -1) {
 * candidates.add(this.matriz[i][j].getConteudoDoVertice()); } }
 * 
 * } }
 * 
 * }
 */
// }