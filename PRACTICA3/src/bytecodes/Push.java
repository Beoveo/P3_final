package bytecodes;

import exception.StackException;
import paquete.CPU;

/**
 * CLASE PUSH:
 * Clase que hereda metodos de ByteCodeOneParameter. Parsea lo introducido por teclado
 * y ejecuta la instruccion.
 */
public class Push extends ByteCodeOneParameter {
	
	/**
	 * Constructora que nos permite coger las instrucciones con un parametro.
	 */
	public Push(){};
	public Push(int param){
		super(param);
	}
	
	/**
	 * Metodo que ejecuta la instruccion PUSH. A la cual se le pasa el parametro
	 * que es un elemento de la pila.
	 * @throws StackException 
	 */
	public void execute(CPU cpu) throws StackException {
		cpu.push(super.param);
		cpu.increaseProgramCounter(); 	
	}
	
	/**
	 * Metodo que ya tiene la linea divida en dos palabras, la primera es el nombre de la
	 * instruccion y la segunda es el parametro/valor que usara.
	 * Si coincide con la instruccion devuelve un nuevo objeto de esta, sino devuelve null.
	 */
	@Override
	protected ByteCode parseAux(String string1, String string2) {
		int param = Integer.parseInt(string2);
		if(!string1.equalsIgnoreCase("PUSH") || param < 0) return null;
		else return new Push(param);
	}

	/**
	 * Metodo toString que devuelve el nombre de la instruccion.
	 */
	@Override
	protected String toStringAux() {return "PUSH";}
}
