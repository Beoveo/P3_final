package paquete;


import java.io.FileNotFoundException;

import exception.ArrayException;
import exception.BadFormatByteCodeException;
import exception.ExceptionDivisionByZero;
import exception.LexicalAnalisisException;
import exception.StackException;



/**
 *CLASS MAIN: 
 *
 *El main se usa para poner la maquina en marcha.
 *Crea un nuevo objeto engine y llama al metodo start.
 */
public class Main {
	public static void main(String args[]) throws ExceptionDivisionByZero, ArrayException, LexicalAnalisisException, BadFormatByteCodeException, FileNotFoundException{
	 Engine engine = new Engine();
	 try{
		 engine.start(); 
	 }catch (ExceptionDivisionByZero | StackException | ArrayException | LexicalAnalisisException | 
			 BadFormatByteCodeException e){
		 System.out.println(e.getMessage());
	 }
	}	 
}

