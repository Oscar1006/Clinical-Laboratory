package controller;

import java.util.ArrayList;

import data_structures.Queue;
import exception.StructureException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import model.Patient;

public class PrincipalController {
	
	Main m;

    @FXML
    private TableColumn<Patient, String> tcPatient;

    @FXML
    private TableView<Patient> tvPatient;
    
    
    @FXML
    private TableColumn<Patient, String> tcPrio;

    @FXML
    private TableView<Patient> tvPrio;

    @FXML
    private TextField txtSearch;
    
    
	@FXML
	private void initialize() {

		// Table no priority
		tvPatient.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		tcPatient.setCellValueFactory(new PropertyValueFactory<>("name"));
		
		// Table priority
		tvPrio.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		tcPrio.setCellValueFactory(new PropertyValueFactory<>("name"));
				
	}
	

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
			e.printStackTrace();
		}
    }
    
    @FXML
    void redo(MouseEvent event) {
    	initializeData();
    }
    
    @FXML
    public void initializeData() {
    	
    	ArrayList<Patient> patientsArrayList = new ArrayList<>();
    	
		Queue<Patient> temporal = new Queue<>();
    	
    	try {
			while(!m.getCl().getNormalWaitList().isEmpty()) {
				temporal.enqueue(m.getCl().getNormalWaitList().front().getInfo());
				patientsArrayList.add(m.getCl().getNormalWaitList().dequeue().getInfo());
			}
		} catch (StructureException e) {
			e.printStackTrace();
		}
		ObservableList<Patient> p = FXCollections.observableArrayList(patientsArrayList);
		tvPatient.setItems(p);
		m.getCl().setNormalPatients(temporal);
		
		ObservableList<Patient> prio = FXCollections.observableArrayList(m.getCl().getWaitList().getList());
		tvPrio.setItems(prio);
    }
    
    
    public void setM(Main m) {
		this.m = m;
	}

}
