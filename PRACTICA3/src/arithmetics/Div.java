package arithmetics;

import exception.ExceptionDivisionByZero;
import exception.StackException;
import bytecodes.ByteCode;
import paquete.CPU;

/**
 * CLASE DIV: 
 * Es una clase aritmetica que hereda los metodos de la clase abstracta arithmetics,
 * los cuales permiten ejecutar la operacion y parsear la linea introducida por teclado.
 *
 */
public class Div implements ByteCode {
	
	/**
	 * Metodo que ejecuta el metodo div de la CPU para
	 * que se realice la division de la subcima y la cima.
	 * Si se realiza la division se incrementa el contador de 
	 * programa y devuelve true, sino devuelve false.
	 * @throws StackException 
	 */
	public void execute(CPU cpu) throws ExceptionDivisionByZero, StackException {
			cpu.div();
			cpu.increaseProgramCounter(); 
	}
	
	/**
	 *Metodo que parsea la linea introducida por teclado
	 *que ya se encuentra separada en 1 o 2 palabras.
	 *Si la instruccion es DIV y no hay mas de 1 palabra devuelve un 
	 *nuevo Objeto Div, sino devuelve null. 
	 */
	public ByteCode parse(String[] words){
		if(words.length !=1 || !words[0].equalsIgnoreCase("DIV")){
			return null;
		} else return new Div();
	}	
	
	/**
	 * Metodo toString que devuelve el nombre de la instruccion.
	 */
	public String toString(){ return "DIV";}
}
