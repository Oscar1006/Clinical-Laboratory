package controller;

import java.util.ArrayList;

import data_structures.PriorityQueue;
import data_structures.Queue;
import model.Patient;

public class Queues {
	private ArrayList<Patient> al;
	private Queue<Patient> q;
	private PriorityQueue<Patient> pq;
	public Queues(ArrayList<Patient> al, Queue<Patient> q) {
		this.al = al;
		this.q = q;
	}
	public Queues(ArrayList<Patient> al, PriorityQueue<Patient> pq) {
		this.al = al;
		this.pq = pq;
	}
	public ArrayList<Patient> getAl() {
		return al;
	}
	public Queue<Patient> getQ() {
		return q;
	}
	public PriorityQueue<Patient> getPq() {
		return pq;
	}
}
