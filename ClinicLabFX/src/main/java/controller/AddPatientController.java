package controller;

import exception.PileException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class AddPatientController {
	
	Main m;

	private ToggleGroup tgDisabled;
    @FXML
    private RadioButton rbDisabled;
    @FXML
    private RadioButton rbDisabledNo;

    private ToggleGroup tgOxigen;
    @FXML
    private RadioButton rbOxigen;
    @FXML
    private RadioButton rbOxigenNo;

    private ToggleGroup tgPregnant;
    @FXML
    private RadioButton rbPregnant;
    @FXML
    private RadioButton rbPregnantNo;

    private ToggleGroup tgSeveral;
    @FXML
    private RadioButton rbSeveral;
    @FXML
    private RadioButton rbSeveralNo;

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
    public void initialize() {
    	tgDisabled = new ToggleGroup();
    	rbDisabled.setToggleGroup(tgDisabled);
    	rbDisabledNo.setToggleGroup(tgDisabled);
    	rbDisabledNo.setSelected(true);
    	
    	tgOxigen = new ToggleGroup();
    	rbOxigen.setToggleGroup(tgOxigen);
    	rbOxigenNo.setToggleGroup(tgOxigen);
    	rbOxigenNo.setSelected(true);
    	
    	tgPregnant = new ToggleGroup();
    	rbPregnant.setToggleGroup(tgPregnant);
    	rbPregnantNo.setToggleGroup(tgPregnant);
    	rbPregnantNo.setSelected(true);
    	
    	tgSeveral = new ToggleGroup();
    	rbSeveral.setToggleGroup(tgSeveral);
    	rbSeveralNo.setToggleGroup(tgSeveral);
    	rbSeveralNo.setSelected(true);
    	
    }

    @FXML
    void addPatient(ActionEvent event) {
    	String name = txtName.getText();
    	String id = txtId.getText();
    	int age = Integer.parseInt(txtAge.getText());
    	String address = txtAddress.getText();
    	String email = txtEmail.getText();
    	boolean pregnant = false;
    	if(tgPregnant.getSelectedToggle().equals(rbPregnant)) {
    		pregnant = true;
    	}
    	boolean disabled = false;
    	if(tgDisabled.getSelectedToggle().equals(rbDisabled)) {
    		disabled = true;
    	}
    	
    	boolean oxigen = false;
    	if(tgOxigen.getSelectedToggle().equals(rbOxigen)) {
    		oxigen = true;
    	}
    	
    	boolean several = false;
    	if(tgSeveral.getSelectedToggle().equals(rbSeveral)) {
    		several = true;
    	}
    	try {
			m.getCl().addPatient(name, id, age, address, email, pregnant, several, disabled, oxigen);
		} catch (PileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public void setM(Main m) {
		this.m = m;
	}

}
