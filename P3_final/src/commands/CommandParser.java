package commands;



/**
 * 
 * CLASS COMMAND_PARSER:
 * Clase que comprueba de que comando se trata y devuelve un nuevo objeto de esa clase.
 * 
 */

public class CommandParser {
	private final static Command[] commands = {new Help(),new Quit(), new Reset(),
		 new Replace(),new Run(), new Compile(), new LoadFich()};
	
	/**
	 * Metodo que parsea el comando, que es lo primero que se introduce por teclado.
	 * @param line: Linea introducida por teclado.
	 * @return Devuelve un nuevo objeto de ese comando, si coincide con alguno de los disponibles.
	 */
	public static Command parse(String line) { 
		line = line.trim(); 
		String []words = line.split(" +"); 
			words[0] = words[0].toLowerCase(); 	
		boolean found = false;
		int i=0;
		Command c = null;
		while (i < commands.length && !found){
		c = commands[i].parse(words);
		if (c!=null) found=true;
			else i++;
		 }
		 return c;
		 }
	
	/**
	 * Muestra la ayuda de todos los comandos.
	 */
	 public static void showHelp() {
		 for (int i=0; i < CommandParser.commands.length; i++)
		 System.out.println(CommandParser.commands[i].textHelp());
		 }
		
}
	
	