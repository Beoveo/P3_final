package commands;

import paquete.Engine;

/**
 * CLASE HELP:
 * Clase que hereda los metodos de command, en la cual se parsea la linea introducida por teclado y contiene el metodo 
 * para ejecutar el comando.
 */
public class Help implements Command{
	/**
	 * Metodo que ejecuta el comando.
	 */
	@Override
	public void execute(Engine engine) {engine.help();}
	
	/**
	 * Metodo que parsea el comando y comprueba cual es de
	 * todos los disponibles.
	 */
	@Override
	public Command parse(String[] s) {
		if (s.length!=1 || !s[0].equalsIgnoreCase("HELP")) return null;
	else return new Help();
	}
	/**
	 * Metodo que muestra una ayuda para indicar al usuario la utilidad de 
	 * cada uno.
	 */
	@Override
	public String textHelp() {
		return "HELP: Muestra esta ayuda." +
	System.getProperty("line.separator");
	}
	
	/**
	 * Metodo toString que devuelve el nombre del comando.
	 */
	public String toString(){
		return "HELP";
	}
	
}
