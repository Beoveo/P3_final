package commands;


import java.io.FileNotFoundException;

import exception.ArrayException;

import paquete.Engine;

public class LoadFich implements Command {
	protected String fich;
	public LoadFich(){}
	public LoadFich (String fichero){
		this.fich = fichero;
	}
	
	@Override
	public void execute(Engine engine) throws ArrayException, FileNotFoundException {
		engine.LoadFichero(fich);
	}

	@Override
	public Command parse(String[] s) {
		s[0] = s[0].toUpperCase();
		if (s.length!=2 || !s[0].equalsIgnoreCase("LOAD")) return null;
		else{
			return new LoadFich(s[1]);
				
		}
	}

	@Override
	public String textHelp() {
		return "LOAD: Carga el fichero." +
				System.getProperty("line.separator");
	}
	
	public String toString(){
		return "LOAD " + this.fich;
	}
}

