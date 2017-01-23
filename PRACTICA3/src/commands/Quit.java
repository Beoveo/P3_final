package commands;

import paquete.Engine;

/**
 * CLASE QUIT:
 * Clase que hereda los metodos de command, en la cual se parsea la linea introducida por teclado y contiene el metodo 
 * para ejecutar el comando.
 */
public class Quit implements Command {
	/**
	 * Metodo que ejecuta el comando.
	 */
	@Override
	public void execute(Engine engine) {
		engine.endExecution();
	}
	
	/**
	 * Metodo que parsea el comando y comprueba cual es de
	 * todos los disponibles.
	 */
	@Override
	public Command parse(String[] s) {
		if (s.length!=1 || !s[0].equalsIgnoreCase("QUIT")) return null;
	else return new Quit();
	}
	
	/**
	 * Metodo que muestra una ayuda para indicar al usuario la utilidad de 
	 * cada uno.
	 */
	@Override
	public String textHelp() {
		return "QUIT: Cierra la aplicacion." +
	System.getProperty("line.separator");
	}
	
	/**
	 * Metodo toString que devuelve el nombre del comando.
	 */
	public String toString(){
		return "QUIT";
	}
	
	
	
	
	
}
