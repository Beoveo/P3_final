package commands;


import exception.ExceptionDivisionByZero;
import exception.StackException;
import paquete.Engine;

/**
 * CLASE RUN:
 * Clase que hereda los metodos de command, en la cual se parsea la linea introducida por teclado y contiene el metodo 
 * para ejecutar el comando.
 */
public class Run implements Command {
	/**
	 * Metodo que ejecuta el comando.
	 * @throws ExceptionDivisionByZero 
	 * @throws StackException 
	 */
	@Override
	public void execute(Engine engine) throws ExceptionDivisionByZero, StackException{
		engine.executeCommandRun();
	}
	
	/**
	 * Metodo que parsea el comando y comprueba cual es de
	 * todos los disponibles.
	 */
	@Override
	public Command parse(String[] s) {
		if (s.length!=1 || !s[0].equalsIgnoreCase("RUN")) return null;
	else return new Run();
	}
	
	/**
	 * Metodo que muestra una ayuda para indicar al usuario la utilidad de 
	 * cada uno.
	 */
	@Override
	public String textHelp() {
		return "RUN: Ejecuta el programa." +
	System.getProperty("line.separator");
	}
	
	/**
	 * Metodo toString que devuelve el nombre del comando.
	 */
	public String toString(){
		return "RUN";
	}
	
	
}
