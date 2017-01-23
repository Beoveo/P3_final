package exception;

public class BadFormatByteCodeException extends Throwable{
	private String msn;
	
	public BadFormatByteCodeException(){}
	public BadFormatByteCodeException(String message){
		this.msn = message;
	}
}
