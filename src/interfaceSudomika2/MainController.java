package interfaceSudomika2;

import java.awt.Font;
import java.io.File;
import java.util.Random;

import javax.xml.soap.Node;

import code.*;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventDispatchChain;
import javafx.event.EventDispatcher;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;

public class MainController {
	private static final int MAX_SIZE = 50;

	/*
	 * variables From Code
	 */
	Grafo grafo;
	Vertices[][] matrizLeitura;
	Vertices[] cores;
	Vertices[][] matrizNew;
	int[] vetor;
	// -------------------------------------------
	private int choice;

	// Images
	@FXML
	ImageView blocoCinza;

	@FXML
	ImageView blocoCinza2;

	@FXML
	ImageView blocoCinza3;

	@FXML
	ImageView victory;

	@FXML
	ImageView youlose;
	/*
	 * grids
	 */
	@FXML
	GridPane grid = new GridPane();

	@FXML
	GridPane grid2 = new GridPane();

	@FXML
	GridPane grid3 = new GridPane();

	/*
	 * textFields
	 */
	@FXML
	private TextField value = new TextField();
	@FXML
	private TextField line = new TextField();
	@FXML
	private TextField column = new TextField();

	/*
	 * buttons
	 */
	@FXML
	private Button finished;
	@FXML
	private Button insert;
	@FXML
	private Button tamanho9;
	@FXML
	private Button tamanho16;
	@FXML
	private Button tamanho25;
	@FXML
	private Button nivelSumikaButton;
	@FXML
	Button nivelGraduando;

	/*
	 * Labels
	 */
	@FXML
	public Label[] labels;

	public MainController() {

	}

	public void insereValorNaGrid(ActionEvent event) {
		int linha = Integer.parseInt(line.getText());
		int coluna = Integer.parseInt(column.getText());
		int cont = 0;

		for (int i = 0; i < linha - 1; i++) {
			for (int j = 0; j < choice; j++) {
				cont++;

			}
		}

		cont = cont + (coluna - 1);
		if (cont < 0)
			cont++;
		if (!(cores[cont].isFixo())) {
			labels[cont].setText(value.getText());
			matrizLeitura[linha - 1][coluna - 1].setConteudoDoVertice(Integer.parseInt(value.getText()));

			for (int i = 0; i < choice; i++) {
				for (int j = 0; j < choice; j++) {
					System.out.print(matrizLeitura[i][j].getConteudoDoVertice());
				}
				System.out.println();
			}
		}
		System.out.println();

	}

	public void finishedAction(ActionEvent event) {
		System.out.println("conferindo");
		finished.setDisable(true);
		Grafo grafo4 = new Grafo(choice, 3 * choice);
		Reader.trataMatriz(choice * 3, choice, matrizLeitura, matrizNew);
		grafo4.grafoPreencher(choice, matrizNew);
		for (int i = 0; i < 27; i++) {
			for (int j = 0; j < 81; j++) {
				if (grafo4.matriz[i][j].getConteudoDoVertice() != -1)
					System.out.print(" " + grafo4.matriz[i][j].getConteudoDoVertice());
			}
			System.out.println();
		}
		int resposta = grafo4.grafoConfere();

		if (resposta == 1) {
			victory.setVisible(true);

		}

	}

	public void inicia9(ActionEvent event) {

		grid.setVisible(true);
		blocoCinza.setVisible(true);

		tamanho9.setDisable(true);
		tamanho16.setDisable(true);
		tamanho25.setDisable(true);

		choice = 9;
		Grafo grafo = new Grafo(choice, 3 * choice);
		matrizLeitura = new Vertices[choice][choice];
		code.Reader.alocaMatriz(choice, choice, matrizLeitura);
		cores = new Vertices[choice * choice];
		code.Reader.alocaVetorDeCores(choice * choice, cores);
		Random st = new Random();
		int max;
		code.Reader.leitura(choice, choice, matrizLeitura, cores, max = st.nextInt(49000) );
		matrizNew = new Vertices[choice * choice][choice];
		code.Reader.alocaMatriz(choice * 3, choice, matrizNew);
		code.Reader.trataMatriz(choice * 3, choice, matrizLeitura, matrizNew);
		grafo.grafoPreencher(choice, matrizNew);
		grafo.criaMatrizIncidencia();
		grafo.criaMatrizNew();
		insereLabelnaGrid(9, 9);
		grafo.AutoSolveBackTracking(cores);
		vetor = new int[choice * choice];
		Grafo grafo2 = new Grafo(choice, 3 * choice);
		code.Reader.trataMatriz(choice * 3, choice, matrizLeitura, matrizNew);

		grafo2.grafoPreencher(choice, matrizNew);
		grafo2.heuristica();

		grafo2.imprimirGrafo(vetor);
		for (int i = 0; i < 81; i++)
			System.out.print(vetor[i] + " ");

	}

	public void inicia16(ActionEvent event) {
		tamanho9.setDisable(true);
		tamanho16.setDisable(true);
		tamanho25.setDisable(true);
		choice = 16;
		grid2.setVisible(true);
		blocoCinza2.setVisible(true);
		tamanho9.setDisable(true);
		tamanho16.setDisable(true);
		tamanho25.setDisable(true);

		Grafo grafo = new Grafo(choice, 3 * choice);
		matrizLeitura = new Vertices[16][16];
		code.Reader.alocaMatriz(choice, choice, matrizLeitura);
		cores = new Vertices[choice * choice];
		code.Reader.alocaVetorDeCores(choice * choice, cores);
		code.Reader.leitura(choice, choice, matrizLeitura, cores,1);
		matrizNew = new Vertices[choice * choice][choice];
		code.Reader.alocaMatriz(choice * 3, choice, matrizNew);
		code.Reader.trataMatriz(choice * 3, choice, matrizLeitura, matrizNew);
		grafo.grafoPreencher(choice, matrizNew);
		grafo.criaMatrizIncidencia();
		grafo.criaMatrizNew();
		insereLabelNaGrid2(16, 16);
		//grafo.AutoSolveBackTracking(cores);
		vetor = new int[choice * choice];
		Grafo grafo2 = new Grafo(choice, 3 * choice);
		code.Reader.trataMatriz(choice * 3, choice, matrizLeitura, matrizNew);

		grafo2.grafoPreencher(choice, matrizNew);
		grafo2.heuristica();

		grafo2.imprimirGrafo(vetor);
	}

	public void inicia25(ActionEvent event) {
		tamanho9.setDisable(true);
		tamanho16.setDisable(true);
		tamanho25.setDisable(true);

		grid3.setVisible(true);
		blocoCinza3.setVisible(true);
		tamanho9.setDisable(true);
		tamanho16.setDisable(true);
		tamanho25.setDisable(true);
		choice = 25;

		Grafo grafo = new Grafo(choice, 3 * choice);
		matrizLeitura = new Vertices[choice][choice];
		code.Reader.alocaMatriz(choice, choice, matrizLeitura);
		cores = new Vertices[choice * choice];
		code.Reader.alocaVetorDeCores(choice * choice, cores);
		code.Reader.leitura(choice, choice, matrizLeitura, cores,1);
		matrizNew = new Vertices[choice * choice][choice];
		code.Reader.alocaMatriz(choice * 3, choice, matrizNew);
		code.Reader.trataMatriz(choice * 3, choice, matrizLeitura, matrizNew);
		grafo.grafoPreencher(choice, matrizNew);
		grafo.criaMatrizIncidencia();
		grafo.criaMatrizNew();
		insereLabelNaGrid3(25, 25);
	//	grafo.AutoSolveBackTracking(cores);
		vetor = new int[choice * choice];
		Grafo grafo2 = new Grafo(choice, 3 * choice);
		code.Reader.trataMatriz(choice * 3, choice, matrizLeitura, matrizNew);

		grafo2.grafoPreencher(choice, matrizNew);
		grafo2.heuristica();

		grafo2.imprimirGrafo(vetor);
	}

	public void autoSolvebt(ActionEvent event) {
		nivelSumikaButton.setDisable(true);
		int cont = 0;
		for (int i = 0; i < choice * choice; i++) {
			int verticeFixo = cores[i].getConteudoDoVertice();
			labels[i].setText(Integer.toString(verticeFixo));
			labels[i].setTextFill(Color.web("#FF0000"));
			labels[i].setMaxHeight(MAX_SIZE);
		}

		for (int i = 0; i < choice; i++) {
			for (int j = 0; j < choice; j++) {
				matrizLeitura[i][j].setConteudoDoVertice(cores[cont].getConteudoDoVertice());
			}
		}

	}

	public void heuristicaButon(ActionEvent event) {
		nivelGraduando.setDisable(true);
		int cont = 0;
		for (int i = 0; i < 81; i++)
			System.out.println(vetor[i]);
		for (int i = 0; i < choice * choice; i++) {
			int verticeFixo = vetor[i];
			labels[i].setText(Integer.toString(verticeFixo));
			labels[i].setTextFill(Color.web("#FF0000"));
			labels[i].setMaxHeight(MAX_SIZE);
		}

		for (int i = 0; i < choice; i++) {
			for (int j = 0; j < choice; j++) {
				matrizLeitura[i][j].setConteudoDoVertice(cores[cont].getConteudoDoVertice());
			}
		}

	}

	public void insereLabelnaGrid(int linha, int coluna) {

		labels = new Label[choice * choice];
		int cont = 0;
		for (int i = 0; i < linha * linha; i++) {
			int verticeFixo = cores[i].getConteudoDoVertice();
			labels[i] = new Label();
			labels[i].setText(Integer.toString(verticeFixo));
			labels[i].setTextFill(Color.web("#FF0000"));
			labels[i].setMaxHeight(MAX_SIZE);
		}

		for (int i = 0; i < linha; i++) {
			for (int j = 0; j < coluna; j++) {
				grid.add(labels[cont], j, i);
				cont++;

			}
		}
	}

	public void insereLabelNaGrid2(int linha, int coluna) {
		labels = new Label[choice * choice];
		int cont = 0;
		for (int i = 0; i < linha * linha; i++) {
			int verticeFixo = cores[i].getConteudoDoVertice();
			labels[i] = new Label();
			labels[i].setText(Integer.toString(verticeFixo));
			labels[i].setTextFill(Color.web("#FF0000"));
			labels[i].setMaxHeight(MAX_SIZE);
		}
		for (int i = 0; i < linha; i++) {
			for (int j = 0; j < coluna; j++) {
				grid2.add(labels[cont], j, i);
				cont++;

			}
		}
	}

	public void insereLabelNaGrid3(int linha, int coluna) {
		labels = new Label[choice * choice];
		int cont = 0;
		for (int i = 0; i < linha * linha; i++) {
			int verticeFixo = cores[i].getConteudoDoVertice();
			labels[i] = new Label();
			labels[i].setText(Integer.toString(verticeFixo));
			labels[i].setTextFill(Color.web("#FF0000"));
			labels[i].setMaxHeight(MAX_SIZE);
		}
		for (int i = 0; i < linha; i++) {
			for (int j = 0; j < coluna; j++) {
				grid3.add(labels[cont], j, i);
				cont++;

			}
		}
	}

}