package code;

public class Vertices {
	public int numeroVertice;
	public int conteudoDoVertice;
	public boolean fixo;

	public Vertices(int cont) {
		this.numeroVertice = cont;
		this.conteudoDoVertice = 0;
		this.setFixo(false);
	}
	
	

	public boolean isFixo() {
		return fixo;
	}



	public void setFixo(boolean fixo) {
		this.fixo = fixo;
	}



	public void imprimirVertices() {

		System.out.print(" " + this.numeroVertice);
	}

	public int getNumeroVertice() {
		return numeroVertice;
	}

	public void setNumeroVertice(int numeroVertice) {
		this.numeroVertice = numeroVertice;
	}

	public int getConteudoDoVertice() {
		return conteudoDoVertice;
	}

	public void setConteudoDoVertice(int conteudoDoVertice) {
		this.conteudoDoVertice = conteudoDoVertice;
	}

}
