package program;


import instruction.Instruction;
import exception.ArrayException;

public class ParsedProgram {
	private static final int MAX_INSTR = 200;
	private Instruction[] pProgram;
	private int numInstrParsed;
	
	public ParsedProgram(){
		this.pProgram = new Instruction[ParsedProgram.MAX_INSTR]; 
		this.numInstrParsed = 0;
	}
	
	/**
	 * Incluye una instruccion al array de instrucciones si la pos esta vacia y si no ha llegado al maximo del array
	 * @param instr: La instruccion a insertar.
	 */
	public void addInstruction(Instruction instr) throws ArrayException{
		if(this.numInstrParsed == ParsedProgram.MAX_INSTR) throw new ArrayException("Error: Ha ocupado todas las posiciones del Parsed Program");
		else{
			pProgram[this.numInstrParsed] = instr;
			this.numInstrParsed++;
		} 
	}
	
	/**
	 * Metodo que resetea todo el parsed program.
	 */
	public void reset(){  
		for (int i = 0; i <= getNumInstr(); i++) this.pProgram[i] = null;
		this.numInstrParsed = 0;
	}
	
	/**
	 * Devuelve el numero de instrucciones del programa parseado
	 * @return
	 */
	public int getNumInstr() { return this.numInstrParsed; }
	/**
	 * Devuelve la instruccion del array tipo Instruction[] que se encuentra en la pos indicada.
	 * @param pos: Pos de la instruccion solicitada.
	 * @return Devuelve la instruccion.
	 */
	public Instruction getInstr(int pos) { return this.pProgram[pos]; }
}
