package exception;

@SuppressWarnings("serial")
public class StackException extends ExecutionErrorException {	
	public StackException(){ super();}
	public StackException(String message){ super(message);}
}
