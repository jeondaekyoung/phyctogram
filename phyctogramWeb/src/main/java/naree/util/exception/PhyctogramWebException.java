package naree.util.exception;

public class PhyctogramWebException extends RuntimeException {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3723074792587584831L;

	public PhyctogramWebException(String message){
		super(message, null, false, false);
	}
	
	public PhyctogramWebException(Exception exception){
		super(exception.getMessage(), null, false, false);
	}
}
