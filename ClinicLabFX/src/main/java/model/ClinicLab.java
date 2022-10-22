package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import data_structures.Pile;
import data_structures.PriorityNode;
import data_structures.HashTable;
import data_structures.PriorityQueue;
import data_structures.Queue;
import data_structures.Element;
import data_structures.HashNode;
import exception.StructureException;

public class ClinicLab {

	private Pile<Action> actionsToUndo;

	private HashTable<String, Patient> patients;

	private PriorityQueue<Patient> generalPrioWaitList;
	private Queue<Patient> generalWaitList;

	private PriorityQueue<Patient> hematologyPrioWaitList;
	private Queue<Patient> hematologyWaitList;

	
	private int count;

	private int lastTurn;

	public ClinicLab() {
		actionsToUndo = new Pile<>();

		patients = new HashTable<>();

		generalPrioWaitList = new PriorityQueue<>();

		generalWaitList = new Queue<>();
		
		hematologyPrioWaitList = new PriorityQueue<>();
		
		hematologyWaitList = new Queue<>();

		count = 1;
		lastTurn = 0;
	}

	public HashTable<String, Patient> getPatients() {
		return patients;
	}

	public PriorityQueue<Patient> getGeneralPrioWaitList() {
		return generalPrioWaitList;
	}

	public Queue<Patient> getGeneralWaitList() {
		return generalWaitList;
	}

	public PriorityQueue<Patient> getHematologyPrioWaitList() {
		return hematologyPrioWaitList;
	}
	
	public Queue<Patient> getHematologyWaitList() {
		return hematologyWaitList;
	}
	
	public void setGeneralPatients(Queue<Patient> normalPatients) {
		this.generalWaitList = normalPatients;
	}

	public void setGeneralPrioPatients(PriorityQueue<Patient> waitList) {
		this.generalPrioWaitList = waitList;
	}
	
	public void setHematologyWaitList(Queue<Patient> hematologyWaitList) {
		this.hematologyWaitList = hematologyWaitList;
	}
	
	public void setHematologyPrioWaitList(PriorityQueue<Patient> hematologyPrioWaitList) {
		this.hematologyPrioWaitList = hematologyPrioWaitList;
	}

	public void addPatient(String name, String id, int age, String address, String email, boolean pregnant,
			boolean several, boolean disabled, boolean oxigen, boolean module)
			throws StructureException, NullPointerException, IOException {
		Patient patient = new Patient(name, id, age, address, email, pregnant, several, disabled, oxigen);
		
		patients.insert(id, patient);

		if(module) {

			if (pregnant | several | disabled | oxigen | age<5 | age>60) {
				hematologyPrioWaitList.insert(count, patient);
				count++;
			} else {
				hematologyWaitList.enqueue(patient);
			}

		}else {
			if (pregnant | several | disabled | oxigen | age<5 | age>60) {
				generalPrioWaitList.insert(count, patient);
				count++;
			} else {
				generalWaitList.enqueue(patient);
			}

			Action act = new Action(Action.Type.ADD, patient);
			addActionToUndo(act);			
		}
	}

	public Patient searchPatient(String id) {
		HashNode<String, Patient> myNode = patients.search(id);
		if (myNode != null)
			return myNode.getValue();
		else
			return null;
	}

	public boolean deletePatient(String id) {
		return patients.delete(id);
	}

	public void addActionToUndo(Action a) throws StructureException {
		actionsToUndo.push(a);
	}

	public void undoAction () throws StructureException {
		Element<Action> element = actionsToUndo.top();
		Action toUndo = (Action)element.getInfo();
		
		switch (toUndo.getType()) {
		case ADD:
			patients.delete(toUndo.getPatient().getId());
			if(toUndo.getPatient().isDisabled()|toUndo.getPatient().isOxigenDependent()| toUndo.getPatient().isPregnant()| toUndo.getPatient().isSeveralDesease() |toUndo.getPatient().getAge()<5 | toUndo.getPatient().getAge()>60)
				undoToPQ("add");
			else
				undoToQ("add");
			break;
		case DELETE:
			if(toUndo.getPatient().isDisabled()|toUndo.getPatient().isOxigenDependent()| toUndo.getPatient().isPregnant()| toUndo.getPatient().isSeveralDesease() |toUndo.getPatient().getAge()<5 | toUndo.getPatient().getAge()>60)
				undoToPQ("delete");
			else
				undoToQ("delete");
		default:
			break;
		}
	
	}

	
	public void extractFromPQ() throws StructureException {
		if(generalPrioWaitList.toArray().size()!=0) {
			PriorityNode<Patient> elim=generalPrioWaitList.extractMaximum();
			addActionToUndo(new Action(Action.Type.DELETE, elim.getPatient()));
			lastTurn=elim.getEntery();
		}
	}
	
	public void dequeueFromQ() throws StructureException {
		Element<Patient> elim=generalWaitList.dequeue();
		addActionToUndo(new Action(Action.Type.DELETE, elim.getInfo()));
	}
	
	public void undoToQ(String t) throws StructureException {
		if(t.equalsIgnoreCase("add")) {
			Queue<Patient> temp= new Queue<>();
			while(!generalWaitList.isEmpty()) {
				if(generalWaitList.front().getInfo()!=actionsToUndo.top().getInfo().getPatient())
					temp.enqueue(generalWaitList.dequeue().getInfo());
				else
					generalWaitList.dequeue();
			}
			generalWaitList=temp;
			actionsToUndo.pop();
		}
		else {
			generalWaitList.enqueue(actionsToUndo.pop().getInfo().getPatient());
		}
	}
	
	public void undoToPQ(String t) throws StructureException {
		if(t.equalsIgnoreCase("add")) {
			PriorityQueue<Patient> temp= new PriorityQueue<>();
			while(generalPrioWaitList.toArray().size()!=0) {
				if(generalPrioWaitList.toArray().get(0).getPatient()!=actionsToUndo.top().getInfo().getPatient()) {
					PriorityNode <Patient> temp2=generalPrioWaitList.extractMaximum();
					temp.insert(temp2.getEntery(), temp2.getPatient());
				}
				else
					generalPrioWaitList.extractMaximum();	
			}
			
			generalPrioWaitList=temp;
			actionsToUndo.pop();
		}
		else {
			generalPrioWaitList.insert(lastTurn, actionsToUndo.pop().getInfo().getPatient());
		}
	}

	public void readDataBase() {
		BufferedReader reader = null;
		try {

			reader = new BufferedReader(new FileReader("src/main/data/model/data-base.csv"));

			
			String line = reader.readLine();


			while ((line= reader.readLine()) != null) {

				String[] atributes = line.split(",");
				
				Patient toAdd = new Patient(atributes);
				
				patients.insert(atributes[1], toAdd);

				line = reader.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}
	
	public void writeDataBase () {
		try {
			File f = new File("src/main/data/model/data-base.csv");
			
			FileWriter fw;
			fw = new FileWriter(f);
			
			try (BufferedWriter bw = new BufferedWriter(fw)) {
				
				@SuppressWarnings("rawtypes")
				HashNode[] nodes =patients.getNodes();
				for (int i = 0; i < nodes.length; i++) {
					if (nodes[i] != null) {
						bw.write(nodes[i].getValue().toString());
						bw.newLine();
						@SuppressWarnings("unchecked")
						HashNode<String, Patient> temp = nodes[i];
						while (temp.getNext()!=null) {
							temp = temp.getNext();
							bw.write(temp.getValue().toString());
							bw.newLine();
						}
						
					}
				}
			} 
		
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		 
	}

}
