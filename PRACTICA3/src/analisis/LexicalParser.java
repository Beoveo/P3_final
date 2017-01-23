package analisis;

import instruction.Instruction;
import instruction.InstructionParser;
import exception.ArrayException;
import exception.LexicalAnalisisException;
import program.ParsedProgram;
import program.SourceProgram;

/**
 * CLASS LEXICALPARSER	
 * Esta clase se encarga de generar el Parsed program a partir del SourceProgram. 
 * Llevara un int para saber que ejecucion se esta parseando.
 * 
 */

public class LexicalParser {
	private SourceProgram sProgram;
	private int programCounter;
	
	/**
	 * La constructora inicializa los valores sProgram y el contador a 0
	 * @param scProg  SourceProgram.
	 */
	public LexicalParser(SourceProgram scProg){
		this.sProgram = scProg;
		this.programCounter = 0;
	}
	/**
	 * Este es el metodo más importante de la clase. 
	 * Se encarga de parsear el programa hasta la palabra que indique el parametro StopKey
	 * @param pProgram	ParsedProgram del main al que se añadiran las intrucciones.
	 * @param stopKey	Palabra para finalizar el analisis
	 * @throws LexicalAnalisisException
	 * @throws ArrayException
	 */
	 
	public void lexicalParser(ParsedProgram pProgram, String stopKey)
	throws LexicalAnalisisException, ArrayException {
		String []line;
		boolean stop = false;
		while (this.programCounter < sProgram.getNumeroInstrucciones() && !stop){
		String instr = sProgram.getInstruction(this.programCounter);
		line = instr.split(" +");
			if (instr.equalsIgnoreCase(stopKey)){
				stop = true;
				this.programCounter++;
			}
			else {
			Instruction instruction = InstructionParser.parse(line, this);
			pProgram.addInstruction(instruction);
			}
		}
	 }
	
	public int getProgramCounter(){ return this.programCounter;}
	public void increaseProgramCounter(){ this.programCounter++;}
}
