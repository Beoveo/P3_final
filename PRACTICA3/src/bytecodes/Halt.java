package bytecodes;


import paquete.CPU;

/**
 * CLASE HALT:
 * Clase que hereda metodos de ByteCodeOneParameter. Parsea lo introducido por teclado
 * y ejecuta la instruccion.
 */
public class Halt implements ByteCode {
	/**
	 * Metodo que ejecuta la instruccion HALT. Hace que la maquina pare de ejecutar
	 * instrucciones.
	 */
	public void execute(CPU cpu) {
		cpu.halt();
	}
	
	/**
	 * Metodo que parsea la linea introducida por teclado y comprueba de que
	 * instruccion bytecode se trata. Si es este BYTECODE devuelve un nuevo objeto.
	 */
	public ByteCode parse(String[] words) {
		if(words.length !=1 || !words[0].equalsIgnoreCase("HALT")){
			return null;
		}else return new Halt();
	}
	
	/**
	 * Metodo toString que devuelve el nombre de la instruccion.
	 */
	public String toString(){ return "HALT"; }
}

