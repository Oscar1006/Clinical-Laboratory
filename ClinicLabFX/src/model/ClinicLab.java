package model;

public class ClinicLab {
	
	ActionsStack actionsToUndo = new ActionsStack();
	
	public ClinicLab() {
		
	}
	
	public void addActionToUndo (String info) {
		actionsToUndo.insert(info);
	}
	
	public void undoAction () {
		Action toUndo = actionsToUndo.poll();
		
	}

}
