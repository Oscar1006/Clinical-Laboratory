package controller;

import exception.StructureException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

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
    
    @FXML
    void undo(MouseEvent event) {
    	try {
			m.getCl().undoAction();
		} catch (StructureException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    @FXML
    void redo(MouseEvent event) {

    }
    
    public void setM(Main m) {
		this.m = m;
	}

}
