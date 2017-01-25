package instruction;

import exception.ArrayException;
import exception.LexicalAnalisisException;
import analisis.LexicalParser;

/**
 * Clase que al parsear comprueba de que instruccion del tipo estatico se trata. Si no es ninguna de esas
 * instrucciones devuelve null. 
 */
public class InstructionParser {
	private final static Instruction[] instructions={
		 new SimpleAssignment(), new CompoundAssignment(),
		 new Write(), new Return(), new While(), new IfThen()};
	
	public static Instruction parse(String[] instr, LexicalParser lexicalParser) throws LexicalAnalisisException, ArrayException {
		Instruction it;
		for (Instruction i:instructions) {
		it = i.lexParse(instr, lexicalParser);
		if (it!=null) return it;
		}
		return null;
	}
}
