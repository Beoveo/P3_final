package bytecodes;

import exception.StackException;
import paquete.CPU;

/**
 * CLASE OUT:
 * Clase que hereda metodos de ByteCodeOneParameter. Parsea lo introducido por teclado
 * y ejecuta la instruccion.
 */
public class Out implements ByteCode {
	/**
	 * Metodo que ejecuta el metodo out de CPU si hay algun elemento en
	 * la pila, si lo hay se ejecuta y se incrementa el contador de programa.
	 */
	public void execute(CPU cpu) throws StackException{
		if(cpu.getSizeStack() <= 0) throw new StackException("Error: La pila esta vacia.");
		else{
			System.out.println(cpu.out());
			cpu.increaseProgramCounter(); 
		}
	}
	
	/**
	 * Metodo que parsea la linea introducida por teclado y comprueba de que
	 * instruccion bytecode se trata. Si es este BYTECODE devuelve un nuevo objeto.
	 */
	public ByteCode parse(String[] words) {
		if(words.length !=1 || !words[0].equalsIgnoreCase("OUT")){
			return null;
		}else return new Out();
	}
	
	/**
	 * Metodo toString que devuelve el nombre de la instruccion.
	 */
	public String toString(){ return "OUT"; }
}
