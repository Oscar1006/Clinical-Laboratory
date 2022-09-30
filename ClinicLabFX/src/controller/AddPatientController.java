package controller;
import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

public class AddPatientController {
	
	Main m;

    @FXML
    private RadioButton rbDisabled;

    @FXML
    private RadioButton rbDisabledNo;

    @FXML
    private RadioButton rbOxigen;

    @FXML
    private RadioButton rbOxigenNot;

    @FXML
    private RadioButton rbPregnant;

    @FXML
    private RadioButton rbPregnantNot;

    @FXML
    private RadioButton rbSeveral;

    @FXML
    private RadioButton rbSeveralNot;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtAge;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtName;

    @FXML
    void addPatient(ActionEvent event) {

    }
    
    public void setM(Main m) {
		this.m = m;
	}

}
