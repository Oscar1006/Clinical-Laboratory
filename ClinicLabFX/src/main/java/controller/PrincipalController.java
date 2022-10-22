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
	private TableColumn<Patient, String> tcPrio;

	@FXML
	private TableColumn<Patient, String> tcPatientH;

	@FXML
	private TableColumn<Patient, String> tcPrioH;

	@FXML
	private TableView<Patient> tvPatient;

	@FXML
	private TableView<Patient> tvPrio;

	@FXML
	private TableView<Patient> tvPatientH;

	@FXML
	private TableView<Patient> tvPrioH;

	@FXML
	private TextField txtSearch;

	private boolean conti;

	private int count;

	@FXML
	private void initialize() {

		tcPatient.setCellValueFactory(new PropertyValueFactory<>("name"));
		tcPrio.setCellValueFactory(new PropertyValueFactory<>("name"));

		tcPatientH.setCellValueFactory(new PropertyValueFactory<>("name"));
		tcPrioH.setCellValueFactory(new PropertyValueFactory<>("name"));

		// Table no priority
		tvPatient.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

		// Table priority
		tvPrio.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

		
		// Table no priority hema
		tvPatientH.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

		// Table priority hema
		tvPrioH.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

		conti = false;

		count = 0;

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
		//General tv
		Queues gq = giveQueues(m.getCl().getGeneralWaitList());
		ArrayList<Patient> generalPatientsArrayList = gq.getAl();
		ObservableList<Patient> p = FXCollections.observableArrayList(generalPatientsArrayList);
		tvPatient.setItems(p);
		m.getCl().setGeneralPatients(gq.getQ());
		
		Queues gpq = givePrios(m.getCl().getGeneralPrioWaitList());
		ArrayList<Patient> generalPatientsPrioArrayList = gpq.getAl();
		ObservableList<Patient> pp = FXCollections.observableArrayList(generalPatientsPrioArrayList);
		tvPrio.setItems(pp);
		m.getCl().setGeneralPrioPatients(gpq.getPq());
		
		//Hematology tv
		Queues hq = giveQueues(m.getCl().getHematologyWaitList());
		ArrayList<Patient> hematologyPatientsArrayList = hq.getAl();
		ObservableList<Patient> hp = FXCollections.observableArrayList(hematologyPatientsArrayList);
		tvPatientH.setItems(hp);
		m.getCl().setHematologyWaitList(hq.getQ());
		
		Queues hpq = givePrios(m.getCl().getHematologyPrioWaitList());
		ArrayList<Patient> hematologyPatientsPrioArrayList = hpq.getAl();
		ObservableList<Patient> hpp = FXCollections.observableArrayList(hematologyPatientsPrioArrayList);
		tvPrioH.setItems(hpp);
		m.getCl().setHematologyPrioWaitList(hpq.getPq());
		
	}
	
	public Queues giveQueues (Queue<Patient> q){
		ArrayList<Patient> patientsArrayList = new ArrayList<>();
		Queue<Patient> temporalQueue = new Queue<>();
		try {
			while (!q.isEmpty()) {
				temporalQueue.enqueue(q.front().getInfo());
				patientsArrayList.add(q.dequeue().getInfo());
			}
		} catch (StructureException e) {
			e.printStackTrace();
		}
		Queues qs = new Queues(patientsArrayList, temporalQueue);
		return qs;
		
	}

	
	public Queues givePrios (PriorityQueue<Patient> q){
		ArrayList<Patient> patientsPrioArrayList = new ArrayList<>();

		PriorityQueue<Patient> temporalPrio = new PriorityQueue<>();

		while (q.toArray().size() > 0) {
			temporalPrio.insert(q.maximum().getEntery(),q.maximum().getPatient());
			try {
				patientsPrioArrayList.add(q.extractMaximum().getPatient());
			} catch (StructureException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		Queues pqs = new Queues(patientsPrioArrayList, temporalPrio);
		return pqs;
	
	
	
	}
	
	public void setM(Main m) {
		this.m = m;
	}

	public void startThread() {
		Thread taskThread = new Thread(new Runnable() {
			@Override
			public void run() {
				while (conti) {

					try {
						Thread.sleep(60000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					Platform.runLater(new Runnable() {
						@Override
						public void run() {
							try {
								if (m.getCl().getGeneralPrioWaitList().toArray().size() != 0 && (count % 4 != 0 | count == 0)) {
									m.getCl().extractFromPQ();
								} else if ((!m.getCl().getGeneralWaitList().isEmpty() && count % 4 == 0)
										| (!m.getCl().getGeneralWaitList().isEmpty()
												&& m.getCl().getGeneralPrioWaitList().toArray().size() == 0)) {
									m.getCl().dequeueFromQ();
								}
								initializeData();
								count++;

								if (m.getCl().getGeneralPrioWaitList().toArray().size() == 0
										&& m.getCl().getGeneralWaitList().isEmpty()) {
									conti = false;
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
		boolean ok = conti;
		conti = true;
		return ok;
	}
}
