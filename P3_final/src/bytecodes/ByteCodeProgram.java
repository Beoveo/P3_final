package bytecodes;
import exception.ArrayException;
/**
 * 
 * CLASS BYTECODE_PROGRAM:
 * Esta clase administra todas las instrucciones bytecode en un array donde las almacena.
 * El numero maximo en este caso es de 100. Teniendo como contador numBC.
 */

public class ByteCodeProgram {
	private static final int MAX_INSTR = 100;
	private ByteCode[] program;
	private int numBC;
	
	/**
	 * Esta constructora inicializa del bytecodeProgram.
	 */
	
	public ByteCodeProgram(){
		this.program = new ByteCode[ByteCodeProgram.MAX_INSTR]; 
		this.numBC = 0;
		}
	
	
	/**
	 * Este metodo devuelve el tamano del array, mediante su contador.
	 * @return Devuelve el numero de bytecodes que contiene el programa.
	 * Es decir, la dimension del array.
	 */
	public int getNumBC(){
		return this.numBC;
	}
	
	/**
	 * Metodo que devuelve el bytecode segun la posicion que se le introduzca.
	 * @param i ; posicion que se desea devolver.
	 * @return Devuelve el bytecode que se encuentre en la pos indicada del programa.
	 */
	
	public ByteCode getByteCode(int i){
		return this.program[i];
	}
	
	/**
	 * Metodo que inserta un bytecode en la lista. Además aumenta el contador en una unidad.
	 * @param instr ; intruccion a añadir.
	 * @return true; si se ha podido añadir.
	 * false ; si no hay espacio o si no hay bytecode.
	 */
	//exception array
	public void insertarByteCode(ByteCode instr) throws ArrayException{  
		if(program[this.numBC] != null || this.numBC == ByteCodeProgram.MAX_INSTR - 1) throw new ArrayException("Error: Bytecode Program lleno");
		else{
			program[this.numBC] = instr;
			this.numBC++;
		} 
	}
	
	/** 
	 * Metodo que reemplaza un bytecode en la posicion indicada.
	 * @param replace ; posicion a reemplazar.
	 * @param bc ; bytecode que se añade en dicha posicion.
	 */
	
	public void replace(int replace, ByteCode bc) throws ArrayException{
		if(replace > this.numBC) throw new ArrayException ("Error: Posicion a reemplazar incorrecta");
		else this.program[replace] = bc;
	}
	
	/** 
	 * Metodo que resetea todo el array de bytecode program. Ademas pone el contador a cero.
	 */
	
	public void reset(){  // Reseteamos todo el bytecode program
		for (int i = 0; i <= getNumBC(); i++) this.program[i] = null;
		this.numBC = 0;
	}
	
	
	/**
	 * Metodo toString de la clase que devuelve el string con
	 * el array de bytecodes hasta la posicion del contador.
	 */
	public String toString(){   // Devolvemos un string, al cual vamos sumando cada bytecode program y separandolo mediante line.separator
		String bc = "";
		for (int i = 0; i < this.numBC; i++){
		bc = bc + i + ":" + "" + this.program[i].toString() + System.getProperty("line.separator");
		}
		return bc;
	}
	
}
