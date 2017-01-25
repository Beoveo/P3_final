package paquete;



import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import program.ParsedProgram;
import program.SourceProgram;
import analisis.LexicalParser;
import bytecodes.ByteCode;
import bytecodes.ByteCodeParser;
import bytecodes.ByteCodeProgram;
import commands.Command;
import commands.CommandParser;
import exception.ArrayException;
import exception.BadFormatByteCodeException;
import exception.ExceptionDivisionByZero;
import exception.ExecutionErrorException;
import exception.LexicalAnalisisException;
import exception.StackException;
import generate_bc.Compiler;


/** 
 *CLASS ENGINE:
 * En la clase engine se introducira el comando por teclado, despues se parseara y se comprobara que esta bien escrito y
 * si esto se cumple se ejecutara llamando a executeCommand (que a su vez llamara a engine).
 * Una vez ejecutado si es correcta la ejecuccion se mostrara el programa almacenado, sino se dara error.
 */
	public class Engine {
		private SourceProgram sProgram = new SourceProgram();
		private ParsedProgram parsedProgram = new ParsedProgram();
		private ByteCodeProgram bcProgram = new ByteCodeProgram();
		private ByteCode bc;
		private boolean end;
		private static Scanner in = new Scanner(System.in);
		private static Scanner sc;
		private CPU cpu;
		private Command comando = null;
		
		/**
		 * La constructora engine inicializa el booleano end que usaremos en el metodo quit.
		 * Ademas inicializa un nuevo objeto del tipo ByteCodeProgram, 
		 * que usaremos para mostrar el programa almacenado por pantalla.
		 */
		public Engine(){
			this.end = false;
			this.bcProgram = new ByteCodeProgram();
		}
		
		public void LoadFichero(String archivo) throws ArrayException, FileNotFoundException{
			String line;
			boolean fileNotFound = archivo == null;
			
			if(fileNotFound) throw new FileNotFoundException("Error: Archivo no encontrado. ");
			else{
				File fr = new File(archivo);
				sc = new Scanner(fr);
				sProgram.reset();
				while(sc.hasNextLine()){
					line = sc.nextLine();
					this.sProgram.addSourceProgram(line);
				}
				sc.close();
				parsedProgram.reset();
				bcProgram.reset();
			}	
		}
		
		/**
		 * Metodo que lee el comando a ejecutar y si se corresponde con los disponibles se ejecuta y se muestra el programa almacenado.
		 * Si el comando es null, vuelve a pedir un comando.
		 * @throws LexicalAnalisisException 
		 * @throws IOException 
		 * @throws ArrayException 
		 * @throws BadFormatByteCodeException 
		 * 
		 */
		public void start(){
			this.end = false;
			String line = " ";
				while(!end){
				System.out.println(">"); 
				line = in.nextLine();
				comando = CommandParser.parse(line); 
				if(comando == null) System.out.println("Error: Vuelva introducir el comando.");
				else {
					System.out.println("Comienza la ejecucion de " + comando.toString());
					try{
						comando.execute(this);
						System.out.println("Programa fuente almacenado: " + System.getProperty("line.separator") + sProgram.toString());
						System.out.println("Programa parseado almacenado: " + System.getProperty("line.separator") + bcProgram.toString());
					}catch (ExecutionErrorException | ArrayException | LexicalAnalisisException |
							 FileNotFoundException | BadFormatByteCodeException e){
						 System.out.println(e.getMessage());
					}
				}
			}
			in.close();
		}
		
		/**
		 * Este metodo resetea el programa y la cpu, es decir, resetea toda la maquina virtual.
		 * Se ejecuta cuando llamamos a RESET.
		 * @return Devuelve true si se ha ejecutado.
		 */
		public boolean resetProgram(){
			sProgram.reset();
			parsedProgram.reset();
			bcProgram.reset();
			cpu.reset();
			return true;
		}
		
		/**
		 * Este metodo cambia el valor del booleano global end a true, lo que significa que se va ha ejecutar quit.
		 * @return Devuelve el nuevo valor de end.
		 */
		public boolean endExecution(){ return this.end = true;}
		
		
		 /**
		  * El metodo executeCommandRun ejecuta el comando RUN.
		  * Este ejecutarÃ¡ en la CPU cada bytecode almacenado en bcProgram e 
		  * irÃ¡ mostrando por pantalla el estado de la cpu, siempre y cuando no se ejecute HALT.
		  * Si se ejecuta HALT la maquina ejecutara todas las instrucciones hasta esta, en la cual se para.
		  * Una vez salga del bucle, se llama a resetHalt(), un metodo que vuelve a poner halt() a false.
		  * @return Devuelve si se ha ejecutado el comando RUN.
		  */
		public boolean executeCommandRun() throws ExceptionDivisionByZero, StackException {
			boolean exeOk;
			exeOk = cpu.run();
			System.out.println("El estado de la maquina tras ejecutar el programa es:" +
								System.getProperty("line.separator"));
			System.out.println(cpu.toString());
			cpu.reset();
			parsedProgram.reset();
			cpu.resetHalt();
			return exeOk; 
}

		/**
		 * Metodo que ejecuta el comando HELP. Muestra la ayuda por pantalla.
		 * @return Devuelve ok si se ha podido mostrar.
		 */
		public boolean help(){
			boolean ok = true;
			CommandParser.showHelp();
			return ok;
		}

		/**
		 * El metodo replace comprueba que la pos sea valida, que exista el programa de instrucciones 
		 * y que no reemplacemos en una pos vacia.
		 * Si se cumplen estas condiciones, se muestra por pantalla el mensaje "Nueva instruccion: ", se lee por teclado
		 * la instruccion a reemplazar y se llama al metodo que lo reemplaza.
		 * @param replace Es la pos en la cual se encuentra la instruccion a reemplazar.
		 * @return Devuelve si se ha podido realizar el cambio.
		 */
		public boolean replace(int replace) throws BadFormatByteCodeException, ArrayException{
			String line;
			boolean replaceOK = false;
			if (cpu.getNumBC() > 0 && cpu.getByteCode(replace) != null){
				replaceOK = true;
			System.out.println("Nueva instruccion: ");
			line = in.nextLine();
			bc = ByteCodeParser.parse(line);
			if(bc == null) throw new BadFormatByteCodeException("Error: No se pudo parsear el bytecode.");
			cpu.replaceBC(replace, bc);
			}
			return replaceOK;
		}
		/**
		 * Metodo Compile que se encarga del analisis lexico de Source 
		 * Program y su posterior generacion del bytecode.
		 * @throws LexicalAnalisisException 
		 * @throws ArrayException
		 */

		public void compile() throws ArrayException, LexicalAnalisisException{
			    this.lexicalAnalysis();
			    this.generateByteCode();
		}
		
		/**
		 * Genera el bytecode mediante la compilacion del codigo.
		 * @throws ArrayException
		 */

		private void generateByteCode() throws ArrayException{
			Compiler comp = new Compiler(bcProgram);
			comp.compile(this.parsedProgram);
			cpu = new CPU(bcProgram);
		}
		
		/**
		 * Genera el Programa parseado mediante la invocacion de lexicalParser.
		 * Además añade "END" para el marcar el final de la ejecucion.
		 * @throws LexicalAnalisisException
		 * @throws ArrayException
		 */

		private void lexicalAnalysis() throws LexicalAnalisisException, ArrayException {
			LexicalParser lexicalParser;
			lexicalParser = new LexicalParser(this.sProgram);
			lexicalParser.lexicalParser(this.parsedProgram, "END");
		}
}
