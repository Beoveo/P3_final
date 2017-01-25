package exception;

@SuppressWarnings("serial")
public class ExecutionErrorException extends Throwable{
	public ExecutionErrorException(){ super();}
	public ExecutionErrorException(String message){ super(message);}
}
