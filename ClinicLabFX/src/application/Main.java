package application;
	
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;

public class Main extends Application {
	
	public static final String ICON_IMG = "file:../../images/medicine-logo.png";
	
	private Parent root;
	private Stage newStage;
	private Scene scene;
	private FXMLLoader loader;
	
	@Override
	public void start(Stage primaryStage) {

		
		try {
			loader = new FXMLLoader(getClass().getResource("../ui/Principal.fxml"));
			
			root = loader.load();
			
			
			
			scene = new Scene(root);
			
			newStage = new Stage();
			newStage.setTitle("Laboratorio");
			newStage.getIcons().add(new Image(ICON_IMG));
			newStage.setScene(scene);
			newStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
