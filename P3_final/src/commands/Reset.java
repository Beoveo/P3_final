package commands;

import paquete.Engine;

/**
 * CLASE RESET:
 * Clase que hereda los metodos de command, en la cual se parsea la linea introducida por teclado y contiene el metodo 
 * para ejecutar el comando.
 */
public class Reset implements Command {
	
	/**
	 * Metodo que ejecuta el comando.
	 */
	public void execute(Engine engine) {
		 engine.resetProgram();
	}
	
	/**
	 * Metodo que parsea el comando y comprueba cual es de
	 * todos los disponibles.
	 */
	@Override
	public Command parse(String[] s) {
		s[0] = s[0].toUpperCase();
	if (s.length!=1 || !s[0].equalsIgnoreCase("RESET")) return null;
		else return new Reset();
	}
	
	/**
	 * Metodo que muestra una ayuda para indicar al usuario la utilidad de 
	 * cada uno.
	 */
	@Override
	public String textHelp() {
	 return " RESET: Permite resetear un programa " +
		 System.getProperty("line.separator");
	}
	
	/**
	 * Metodo toString que devuelve el nombre del comando.
	 */
	public String toString(){
		return "RESET";
	}
	
	
}
