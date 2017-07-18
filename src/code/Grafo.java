package code;

import java.util.Stack;

import javafx.scene.control.Label;
import javafx.scene.paint.Color;

public class Grafo {
	public Vertices[][] matriz;
	public int nArestas;
	public int nVertices;
	public int tamanhoDoQuadrante;
	public int maiorOpcao = 1;
	public int[][] matrizIncidencia;
	public Vertices[][] matrizNew;

	public void criaMatrizIncidencia() {
		this.matrizIncidencia = new int[this.nVertices][3];

		int vertice, i, j, qtd = 0, linha = 0;
		for (i = 0; i < nArestas; i++) {
			qtd = 0;
			for (j = 0; j < nVertices; j++) {
				if (matriz[i][j].getConteudoDoVertice() >= 0 && j == matriz[i][j].getNumeroVertice()) {
					vertice = j;
					matrizIncidencia[vertice][linha] = i;
					qtd++;
					if (qtd == this.tamanhoDoQuadrante)
						break;
				}
			}
			qtd = 0;
			if (i == 8) {
				linha++;
			}
			if (i == 17) {
				linha++;
			}
		}
	}

	public void criaMatrizNew() {
		matrizNew = new Vertices[nArestas][tamanhoDoQuadrante];
		int i, j, cont = 0;
		for (i = 0; i < nArestas; i++) {
			for (j = 0; j < nVertices; j++) {
				if (matriz[i][j].getConteudoDoVertice() >= 0) {
					matrizNew[i][cont] = matriz[i][j];
					cont++;
				}
			}
			cont = 0;
		}
	}

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

	/* -------------------------------------------------------------- */
	public void AutoSolveBackTracking(Vertices[] cores) {
		System.out.println("Solving...");
		int i = 0;
		int candidate = 1;
		Stack<Integer> pilha = new Stack<Integer>();
		do {

			while (cores[i].isFixo()) {
				i++;
				if (i >= this.nVertices)
					break;
			}
			if (i < this.nVertices) {

				candidate = candidatesColorNode(i, cores, candidate);
				if (candidate == 0) {
					candidate = pilha.pop();
					inserePossivelSolucao(0, i);
					candidate++;
					i--;
					while (cores[i].isFixo()) {
						i--;
					}
				} else {
					pilha.push(candidate);
					cores[i].setConteudoDoVertice(candidate);
					inserePossivelSolucao(candidate, i);
					i++;
					candidate = 1;
				}
			}
		} while (i < this.nVertices);
	}

	public int candidatesColorNode(int cor, Vertices[] cores, int wrong) {

		int control;
		if (wrong > this.tamanhoDoQuadrante) {
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

	public int findCandidate(int vertice, int candidate) {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < tamanhoDoQuadrante; j++) {
				if (matrizNew[matrizIncidencia[vertice][i]][j].getConteudoDoVertice() == candidate) {
					return 0;
				}
			}
		}
		return candidate;
	}

	public void grafoPreencher(int linha, Vertices[][] matriz) {
		for (int i = 0; i < this.nArestas; i++) {
			for (int j = 0; j < linha; j++) {
				this.matriz[i][matriz[i][j].getNumeroVertice()] = matriz[i][j];
			}
		}
	}

	public void imprimirGrafo(int[] vetor) {
		int cont = 0 ;
		for (int i = 0; i < this.tamanhoDoQuadrante  ; i++) {
			for (int j = 0; j < this.nVertices; j++) {
				if (this.matriz[i][j].getConteudoDoVertice() >= 0) {
					vetor[cont] = (matriz[i][j].getConteudoDoVertice());
					cont++;
				}
			}
			System.out.println();
		}
	}

	public void inserePossivelSolucao(int candidate, int index) {
		for (int i = 0; i < 3; i++) {
			matriz[matrizIncidencia[index][i]][index].setConteudoDoVertice(candidate);
		}
	}

	public void printCores(Vertices[] cores) {
		for (int i = 0; i < this.nVertices; i++) {
			System.out.print(" " + cores[i].getConteudoDoVertice());
		}
		System.out.println();
	}

	public int grafoConfere() {
		int[] aux = new int[this.tamanhoDoQuadrante+1];
		for (int i = 0; i < this.tamanhoDoQuadrante  ; i++) {
			for (int j = 0; j < this.nVertices; j++) {
				if (this.matriz[i][j].getConteudoDoVertice() >= 0) {
					aux[this.matriz[i][j].getConteudoDoVertice()+1]++;
				}
				
				for(int k = 0 ; k< this.tamanhoDoQuadrante ; k++) {
					if(aux[k] > 1 || aux[k] == 0) {
						System.out.println(0);
						return 0;
					}
				}
			}
		}
		System.out.println(1);
		return 1;
	}

	// -----------------------------------------------------------------//

	public boolean opcaoDisponivel(int linha, int opcao) {
		for (int i = 0; i < nVertices; i++) {
			if (this.matriz[linha][i].conteudoDoVertice == opcao) {
				return false;
			}
		}
		return true;
	}

	public boolean posicaoPreeenchida(int i, int j) {
		if (this.matriz[i][j].conteudoDoVertice == 0) {
			return false;
		}
		return true;
	}

	public void tentaColorir(int aresta, int vertice) {
		int opcao = 1, aresta2 = 0, aresta3 = 0, k;
		for (k = aresta + 1; k < nVertices; k++) {
			if (this.matriz[k][vertice].conteudoDoVertice == 0) {
				aresta2 = k;
				break;
			}
		}
		for (k = aresta2 + 1; k < nVertices; k++) {
			if (this.matriz[k][vertice].conteudoDoVertice == 0) {
				aresta3 = k;
				break;
			}
		}

		while ((opcaoDisponivel(aresta, opcao) == false) || (opcaoDisponivel(aresta2, opcao) == false)
				|| (opcaoDisponivel(aresta3, opcao) == false)) {
			opcao++;
		}

		this.matriz[aresta][vertice].conteudoDoVertice = opcao;
		this.matriz[aresta2][vertice].conteudoDoVertice = opcao;
		this.matriz[aresta3][vertice].conteudoDoVertice = opcao;
		if (opcao > maiorOpcao) {
			maiorOpcao = opcao;
		}
	}

	public int heuristica() {
		int i, j;
		maiorOpcao = 1;
		for (i = 0; i < nArestas; i++) {
			for (j = 0; j < nVertices; j++) {
				while (posicaoPreeenchida(i, j) == false) {
					tentaColorir(i, j);
				}
			}
		}
		return maiorOpcao;
	}
}
