package program;

import exception.ArrayException;

public class SourceProgram {
	private static final int MAX_SOURCE_PROGRAM = 100;
	private String[] sProgram;
	private int numInstruction;
	
	public SourceProgram(){
		this.sProgram = new String[MAX_SOURCE_PROGRAM];
		this.numInstruction = 0;
	}
	 
	/**
	 * Incluye una instruccion/linea al array de Strings si no ha llegado al maximo del array del source program.
	 * @param instr: La linea a insertar. Que son las instrucciones del programa.
	 */
	public void addSourceProgram(String line)throws ArrayException{
		if(this.numInstruction == MAX_SOURCE_PROGRAM - 1) throw new ArrayException("Error: Ha ocupado todas las posiciones del Programa Fuente");
		sProgram[this.numInstruction] = line;
		this.numInstruction++;
	}
	 
	/**
	 * Devuelve la instruccion del array que se encuentra en la pos que se le pasa como parametro.
	 * @param programCounter: Pos del array.
	 * @return Instruccion[pos]
	 * @throws ArrayException
	 */
	public String getInstruction(int programCounter) throws ArrayException{
		if(this.numInstruction == 0) throw new ArrayException("Error: Intruccion no encontrada");
		else return this.sProgram[programCounter];
			
	}
	
	/**
	 * Metodo que resetea todo el source program.
	 */
	public void reset(){  
		for (int i = 0; i <= getNumeroInstrucciones(); i++) this.sProgram[i] = null;
		this.numInstruction = 0;
	}
	
	/**
	 * Devuelve el numero de instrucciones del array de Source Program.
	 * @return Numero de instrucciones.
	 */
	public int getNumeroInstrucciones() {
		return this.numInstruction;
	}
	
	public String toString(){
		String sc = "";
		for(int i = 0; i < this.numInstruction; i++){
			sc = sc + i + ":" + " " + this.sProgram[i] + System.getProperty("line.separator");
		}
		return sc;
	}
}
