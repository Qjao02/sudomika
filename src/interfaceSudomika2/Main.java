package interfaceSudomika2;
	
import java.net.URL;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import code.*;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
		
			URL arquivoFXML = getClass().getResource("InterfaceSudomika.fxml");
			Parent fxmlParent = (Parent) FXMLLoader.load(arquivoFXML);
			primaryStage.setScene(new Scene(fxmlParent,880,680));
		    primaryStage.setResizable(false);
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
		
		
		
	
	}
}
