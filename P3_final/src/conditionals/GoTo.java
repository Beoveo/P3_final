package conditionals;

import bytecodes.ByteCode;
import bytecodes.ByteCodeOneParameter;
import paquete.CPU;

/**
 * CLASE GOTO:
 * Clase que hereda los metodos de ByteCodeOneParameter.
 * Permite parsear y ejecutar esta instruccion.
 *
 */
public class GoTo extends ByteCodeOneParameter{
	/**
	 * Constructora que permite que la instruccion GOTO tenga un parametro.
	 */
	public GoTo(){};
	public GoTo(int pos){
		super(pos);
	}
	
	/**
	 * Metodo que parsea el nombre del salto y pasa el parametro de string a Int.
	 * Si es este salto, el metodo devuelve un objeto nuevo con el parametro.
	 */
	protected ByteCode parseAux(String string1, String string2){
		int pos = Integer.parseInt(string2);
		if(!string1.equalsIgnoreCase("GOTO") || pos < 0) return null;
		else return new GoTo(pos);
	}
	
	/**
	 * Metodo toString que devuelve el nombre del salto.
	 */
	protected String toStringAux(){
		return "GOTO";
	}
	/**
	 * Metodo que ejecuta el salto.
	 */
	@Override
	public void execute(CPU cpu) {
		cpu.setProgramCounter(super.param);
	}
	
	
}
