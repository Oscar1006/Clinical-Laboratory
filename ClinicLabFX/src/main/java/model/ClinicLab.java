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
	private Pile<Action> actionsToUndo2;

	private HashTable<String, Patient> patients;

	private PriorityQueue<Patient> generalPrioWaitList;
	private Queue<Patient> generalWaitList;

	private PriorityQueue<Patient> hematologyPrioWaitList;
	private Queue<Patient> hematologyWaitList;

	
	private int count;

	private Pile<Integer> lastTurn;
	private Pile<Integer> lastTurn2;

	public ClinicLab() {
		actionsToUndo = new Pile<>();
		
		actionsToUndo2= new Pile<>();

		patients = new HashTable<>();

		generalPrioWaitList = new PriorityQueue<>();

		generalWaitList = new Queue<>();
		
		hematologyPrioWaitList = new PriorityQueue<>();
		
		hematologyWaitList = new Queue<>();

		count = 1;
		lastTurn = new Pile<>();
		lastTurn2 = new Pile<>();
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

		addPatientToList(patient, module);
	}
	
	public void addPatientToList(Patient p, boolean module) throws StructureException {
		if(module) {

			if (p.isPregnant() | p.isSeveralDesease() | p.isDisabled() | p.isOxigenDependent() | p.getAge()<5 | p.getAge()>60) {
				hematologyPrioWaitList.insert(count, p);
				count++;
			} else {
				hematologyWaitList.enqueue(p);
			}
			Action act = new Action(Action.Type.ADD, p);
			addActionToUndo(act, false);
			
			
		}else {
			if (p.isPregnant() | p.isSeveralDesease() | p.isDisabled() | p.isOxigenDependent() | p.getAge()<5 | p.getAge()>60) {
				generalPrioWaitList.insert(count, p);
				count++;
			} else {
				generalWaitList.enqueue(p);
			}

			Action act = new Action(Action.Type.ADD, p);
			addActionToUndo(act, true);			
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

	public void addActionToUndo(Action a, boolean s) throws StructureException {
		if(s) {
			actionsToUndo.push(a);
		}
		else
			actionsToUndo2.push(a);
	}

	public void undoAction (boolean opt) throws StructureException {
		Element<Action> element;
		if(opt) {
			element = actionsToUndo.top();
		}
		else {
			element = actionsToUndo2.top();
		}
		
		Action toUndo = (Action)element.getInfo();
		switch (toUndo.getType()) {
		case ADD:
			patients.delete(toUndo.getPatient().getId());
			if(toUndo.getPatient().isDisabled()|toUndo.getPatient().isOxigenDependent()| toUndo.getPatient().isPregnant()| toUndo.getPatient().isSeveralDesease() |toUndo.getPatient().getAge()<5 | toUndo.getPatient().getAge()>60) {
				if(opt)
					undoToPQG("add");
				else
					undoToPQH("add");
			}
				
			else {
				if(opt)
					undoToQG("add");
				else
					undoToQH("add");
			}
				
			break;
		case DELETE:
			if(toUndo.getPatient().isDisabled()|toUndo.getPatient().isOxigenDependent()| toUndo.getPatient().isPregnant()| toUndo.getPatient().isSeveralDesease() |toUndo.getPatient().getAge()<5 | toUndo.getPatient().getAge()>60) {
				if(opt)
					undoToPQG("delete");
				else
					undoToPQH("delete");
			}
				
			else {
				if(opt)
					undoToQG("delete");
				else
					undoToQH("delete");
			}
		default:
			break;
		}
	
	}

	
	private void undoToQH(String t) throws StructureException {
		if(t.equalsIgnoreCase("add")) {
			Queue<Patient> temp= new Queue<>();
			while(!hematologyWaitList.isEmpty()) {
				if(hematologyWaitList.front().getInfo()!=actionsToUndo2.top().getInfo().getPatient())
					temp.enqueue(hematologyWaitList.dequeue().getInfo());
				else
					hematologyWaitList.dequeue();
			}
			hematologyWaitList=temp;
			actionsToUndo2.pop();
		}
		else {
			hematologyWaitList.enqueue(actionsToUndo2.pop().getInfo().getPatient());
		}
		
	}

	private void undoToPQH(String t) throws StructureException {
		if(t.equalsIgnoreCase("add")) {
			PriorityQueue<Patient> temp= new PriorityQueue<>();
			while(hematologyPrioWaitList.toArray().size()!=0) {
				if(hematologyPrioWaitList.toArray().get(0).getPatient()!=actionsToUndo2.top().getInfo().getPatient()) {
					PriorityNode <Patient> temp2=hematologyPrioWaitList.extractMaximum();
					temp.insert(temp2.getEntery(), temp2.getPatient());
				}
				else
					hematologyPrioWaitList.extractMaximum();	
			}
			
			hematologyPrioWaitList=temp;
			actionsToUndo2.pop();
		}
		else {
			hematologyPrioWaitList.insert(lastTurn2.pop().getInfo(), actionsToUndo2.pop().getInfo().getPatient());
		}
		
	}

	public void extractFromPQ(boolean opt) throws StructureException {
		PriorityNode<Patient> elim;
		if(opt) {
			if(generalPrioWaitList.toArray().size()!=0) {
				elim=generalPrioWaitList.extractMaximum();
				addActionToUndo(new Action(Action.Type.DELETE, elim.getPatient()), opt);
				lastTurn.push(elim.getEntery());
			}
		}
		else {
			if(hematologyPrioWaitList.toArray().size()!=0) {
				elim=hematologyPrioWaitList.extractMaximum();
				addActionToUndo(new Action(Action.Type.DELETE, elim.getPatient()), opt);
				lastTurn2.push(elim.getEntery());
			}
		}
		
	}
	
	public void dequeueFromQ(boolean opt) throws StructureException {
		Element<Patient> elim;
		if(opt) {
			elim=generalWaitList.dequeue();
		}else {
			elim=hematologyWaitList.dequeue();
		}
		addActionToUndo(new Action(Action.Type.DELETE, elim.getInfo()), opt);
		
	}
	
	public void undoToQG(String t) throws StructureException {
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
	
	public void undoToPQG(String t) throws StructureException {
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
			generalPrioWaitList.insert(lastTurn.pop().getInfo(), actionsToUndo.pop().getInfo().getPatient());
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
