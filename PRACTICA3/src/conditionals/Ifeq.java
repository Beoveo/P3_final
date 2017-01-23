package conditionals;

import bytecodes.ByteCode;

/**
 * CLASE IFEQ:
 * Clase que hereda los metodos de ConditionalJumps.
 * Permite parsear y realizar la comparacion de esta instruccion.
 *
 */
public class Ifeq extends ConditionalJumps {
	
	/**
	 * Constructora que permite que la instruccion IFEQ tenga un parametro.
	 */
	public Ifeq(){};
	public Ifeq(int pos){
		super(pos);
	}
	
	/**
	 * Metodo que parsea el nombre del salto y pasa el parametro de string a Int.
	 * Si es este salto, el metodo devuelve un objeto nuevo con el parametro.
	 */
	protected ByteCode parseAux(String string1, String string2){
		int pos = Integer.parseInt(string2);
		if(!string1.equalsIgnoreCase("IFEQ") || pos < 0) return null;
		else return new Ifeq(pos);
	}
	
	/**
	 * Metodo que realiza la comparacion segun el tipo de salto condicional.
	 */
	protected boolean compare(int n1, int n2){
		if (n1 == n2) return true;
		return false;
	}
	
	/**
	 * Metodo toString que devuelve el nombre del salto.
	 */
	protected String toStringAux(){
		return "IFEQ";
	}
}
