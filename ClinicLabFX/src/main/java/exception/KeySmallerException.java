package exception;

public class KeySmallerException extends Exception {

	private static final long serialVersionUID = 2L;
	
	@Override
	public String getMessage() {
		return "New key is smaller than current key";
	}
}
