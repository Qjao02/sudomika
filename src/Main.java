import java.util.Scanner;

public class Main {

	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner lerTeclado = new Scanner(System.in);
		int linha = lerTeclado.nextInt();
		int m = linha * 3;
		Grafo grafo = new Grafo(linha,m);
	}

}
