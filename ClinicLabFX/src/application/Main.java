package application;
	
import java.io.IOException;

import controller.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import model.ClinicLab;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;

public class Main extends Application {
	
	public static final String ICON_IMG = "file:src\\images\\medicine-logo.png";
	
	private ClinicLab cl = new ClinicLab();
	
	private Parent root;
	private Stage newStage;
	private Scene scene;
	private FXMLLoader loader;
	
	@Override
	public void start(Stage primaryStage) {

		PrincipalController principal;
		try {
			loader = new FXMLLoader(getClass().getResource("../ui/Principal.fxml"));
			
			root = loader.load();
			principal = loader.getController();
			principal.setM(this);
			
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
	
	public void showAddPatient() {
		AddPatientController controller = new AddPatientController();
		try {
			loader = new FXMLLoader(getClass().getResource("../ui/AddPatient.fxml"));
			
			root = loader.load();
			controller = loader.getController();
			
			controller.setM(this);
			
			
			
			scene = new Scene(root);
			
			newStage = new Stage();
			newStage.setTitle("Añadir paciente");
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
	
	public ClinicLab getCl() {
		return cl;
	}
	
	
}
