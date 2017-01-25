package paquete;


import java.io.FileNotFoundException;

import exception.ArrayException;
import exception.BadFormatByteCodeException;
import exception.ExecutionErrorException;
import exception.LexicalAnalisisException;



/**
 *CLASS MAIN: 
 *
 *El main se usa para poner la maquina en marcha.
 *Crea un nuevo objeto engine y llama al metodo start.
 */
public class Main {
	public static void main(String args[]) throws ExecutionErrorException, ArrayException, LexicalAnalisisException, BadFormatByteCodeException, FileNotFoundException{
	 Engine engine = new Engine();
	 try{
		 engine.start(); 
	 }catch (ExecutionErrorException | ArrayException | LexicalAnalisisException |
			 FileNotFoundException | BadFormatByteCodeException e){
		 System.out.println(e.getMessage());
	 }
	}	 
}

