import java.util.ArrayList;

public class Pilha {
	private ArrayList<Integer> pilha = new ArrayList<Integer>();
	
	
	
	
	
	public void insere(int i ){
		this.pilha.add(i);
	}
	
	public int remove(){
		int valorRetornado;
		if(this.pilha.size() == 0 ){
			valorRetornado = this.pilha.get(this.pilha.size());
			this.pilha.remove(this.pilha.size());
			return valorRetornado;
		}
		valorRetornado = this.pilha.get(this.pilha.size() - 1);
		this.pilha.remove(this.pilha.size() - 1);
		return valorRetornado;	
	}
	
}
