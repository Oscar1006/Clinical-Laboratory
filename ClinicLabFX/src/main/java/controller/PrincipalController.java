package controller;

import java.util.ArrayList;

import data_structures.PriorityQueue;
import data_structures.Queue;
import exception.StructureException;
import javafx.application.Platform;
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
	
	private boolean conti;
	
	private int count;
	

	@FXML
	private void initialize() {

		// Table no priority
		tvPatient.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		tcPatient.setCellValueFactory(new PropertyValueFactory<>("name"));

		// Table priority
		tvPrio.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		tcPrio.setCellValueFactory(new PropertyValueFactory<>("name"));
		
		conti=false;
		
		count=0;
		

	}

	@FXML
	public void addNewPatient(ActionEvent event) {
		m.showAddPatient();
	}

	@FXML
	public void searchPatient(ActionEvent event) {

	}

	@FXML
	public void removePatient(ActionEvent event) {

	}

	@FXML
	public void undo(MouseEvent event) {
		try {
			m.getCl().undoAction();
		} catch (StructureException e) {
			e.printStackTrace();
		}
		
		initializeData();
	}

	@FXML
	public void redo(MouseEvent event) {
		initializeData();
	}

	@FXML
	public void initializeData() {

		ArrayList<Patient> patientsArrayList = new ArrayList<>();

		Queue<Patient> temporal = new Queue<>();

		try {
			while (!m.getCl().getNormalWaitList().isEmpty()) {
				temporal.enqueue(m.getCl().getNormalWaitList().front().getInfo());
				patientsArrayList.add(m.getCl().getNormalWaitList().dequeue().getInfo());
			}
		} catch (StructureException e) {
			e.printStackTrace();
		}
		ObservableList<Patient> p = FXCollections.observableArrayList(patientsArrayList);
		tvPatient.setItems(p);
		m.getCl().setNormalPatients(temporal);
		

		ArrayList<Patient> patientsPrioArrayList = new ArrayList<>();

		PriorityQueue<Patient> temporalPrio = new PriorityQueue<>();

		while (m.getCl().getWaitList().toArray().size()>0) {
			temporalPrio.insert(m.getCl().getWaitList().maximum().getEntery(), m.getCl().getWaitList().maximum().getPatient());
			patientsPrioArrayList.add(m.getCl().getWaitList().extractMaximum().getPatient());
		}
		ObservableList<Patient> pp = FXCollections.observableArrayList(patientsPrioArrayList);
		tvPrio.setItems(pp);
		m.getCl().setWaitList(temporalPrio);

	}

	public void setM(Main m) {
		this.m = m;
	}
	
	public void startThread() {
		Thread taskThread = new Thread(new Runnable() {
			@Override
			public void run() {
				while(conti) {

					try {
						Thread.sleep(60000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					Platform.runLater(new Runnable() {
						@Override
						public void run() {
							try {
								if(m.getCl().getWaitList().toArray().size()!=0 && (count%4!=0 | count==0)) {
									m.getCl().extractFromPQ();
								}
								else if((!m.getCl().getNormalWaitList().isEmpty() && count%4==0)|(!m.getCl().getNormalWaitList().isEmpty() && m.getCl().getWaitList().toArray().size()==0)) {
									m.getCl().dequeueFromQ();
								}
								initializeData();
								count++;

								if(m.getCl().getWaitList().toArray().size()==0 && m.getCl().getNormalWaitList().isEmpty()) {
									conti=false;
								}

							} catch (StructureException e) {
								e.printStackTrace();
							}
						}
					}); 
				}
			}
		});
		taskThread.start();
	}
	
	public void threadQueue() {
		try {
			m.getCl().dequeueFromQ();
		} catch (StructureException e) {
			e.printStackTrace();
		}
	}
	
	public boolean statusConti() {
		boolean ok= conti;
		conti=true;
		return ok;
	}
}
