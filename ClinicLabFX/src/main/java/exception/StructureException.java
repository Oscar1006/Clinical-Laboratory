package exception;

public class StructureException extends Exception {

	private static final long serialVersionUID = 1L;
	
	@Override
	public String getMessage() {
		return "Structure is empty or insertion is not successful";
	}

}
