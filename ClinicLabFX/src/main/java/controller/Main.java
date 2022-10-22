package controller;
	
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import model.ClinicLab;
import model.Patient;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;

public class Main extends Application {
	
	public static final String ICON_IMG = "file:src/main/resources/controller/medicine-logo.png";
	
	public static final String HEMATOLOGY_IMG = "file:src/main/resources/images/hermatologia.png";
	public static final String GENERAL_IMG = "file:src/main/resources/images/lab-logo.png";
	
	
	private ClinicLab cl = new ClinicLab();
	
	private Parent root;
	private Stage newStage;
	private Scene scene;
	private FXMLLoader loader;
	
	private PrincipalController principal;
	private AddPatientController add;

	
	@Override
	public void start(Stage primaryStage) {
		cl.readDataBase();

		try {
			loader = new FXMLLoader(getClass().getResource("Principal.fxml"));
			
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
	
	@Override
    public void stop() {
        // executed when the application shuts down
		cl.writeDataBase();
    }
	
	public void showAddPatient() {
		add = new AddPatientController();
		try {
			loader = new FXMLLoader(getClass().getResource("AddPatient.fxml"));
			
			root = loader.load();
			add = loader.getController();
			
			add.setM(this);
			
			
			
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
	public void setPrincipal(PrincipalController principal) {
		this.principal = principal;
	}
	public PrincipalController getPrincipal() {
		return principal;
	}
	public AddPatientController getAddController() {
		return add;
	}
	
}
