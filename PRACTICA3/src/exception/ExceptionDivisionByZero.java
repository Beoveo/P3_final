package exception;


@SuppressWarnings("serial")
public class ExceptionDivisionByZero extends ExecutionErrorException{
	public ExceptionDivisionByZero(){ super();}
	public ExceptionDivisionByZero(String message){ super(message); }
}
