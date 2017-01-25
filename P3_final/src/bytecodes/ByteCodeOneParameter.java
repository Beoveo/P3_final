package bytecodes;

/**
 * CLASE BYTECODE_ONE_PARAMETER:
 * Clase que heredan otras clases que necesiten un parametro para poder ser ejecutadas.
 */
public abstract class ByteCodeOneParameter implements ByteCode { 
	protected int param;
	
	/**
	 * Constructora que nos permite llamar a clases que necesitan un parametro
	 * para ejecutarse.
	 */
	public ByteCodeOneParameter(){};
	
	public ByteCodeOneParameter(int p){ this.param = p; }
	
	/**
	 * Metodo que divide la linea introducida por teclado en palabras.
	 * Devuelve otro metodo (parseAux)con las palabras de parametro, solo 
	 * si hay al menos 2 palabras. 
	 */ 
	@Override
	public ByteCode parse(String[] words) {
		if (words.length!=2) return null;
		else return this.parseAux(words[0],words[1]);
	}
	
	abstract protected ByteCode parseAux(String string1, String string2);
	
	/**
	 * Metodo que devuelve la instruccion y el parametro. 
	 */
	public String toString(){
		return this.toStringAux() + " " + this.param;
	}
	
	abstract protected String toStringAux(); 
	
}
