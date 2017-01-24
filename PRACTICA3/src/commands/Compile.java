package commands;



import exception.ArrayException;
import exception.ExceptionDivisionByZero;
import exception.LexicalAnalisisException;
import exception.StackException;
import paquete.Engine;

public class Compile implements Command {

	@Override
	public void execute(Engine engine) throws LexicalAnalisisException,ArrayException, ExceptionDivisionByZero,
			StackException {
		engine.compile();
	}

	@Override
	public Command parse(String[] s) {
		s[0] = s[0].toUpperCase();
		if (s.length!=1 || !s[0].equalsIgnoreCase("COMPILE")) return null;
		else return new Compile();
	}

	@Override
	public String textHelp() {
		return "COMPILE: Realiza el analisis lexico del programa." +
				System.getProperty("line.separator");
				}
	
	public String toString(){
		return "Compile";
	}
}


