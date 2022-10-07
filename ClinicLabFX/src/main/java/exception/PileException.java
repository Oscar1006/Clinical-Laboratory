package exception;

public class PileException extends Exception {

	private static final long serialVersionUID = 1L;
	
	@Override
	public String getMessage() {
		return "Stack is empty or insertion is not successful";
	}

}
