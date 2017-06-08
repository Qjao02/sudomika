
public class Vertices {
	private int numeroVertice;
	private int conteudoDoVertice;


	public Vertices(int cont) {
		this.numeroVertice = cont;
		this.conteudoDoVertice = 1;
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
