package controller;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class PrincipalController {
	
	Main m;

    @FXML
    private TableColumn<?, ?> tcPatient;

    @FXML
    private TableView<?> tvPatient;

    @FXML
    private TextField txtSearch;

    @FXML
    void addNewPatient(ActionEvent event) {
    	m.showAddPatient();
    }

    @FXML
    void searchPatient(ActionEvent event) {

    }
    @FXML
    void removePatient(ActionEvent event) {

    }
    
    public void setM(Main m) {
		this.m = m;
	}

}
