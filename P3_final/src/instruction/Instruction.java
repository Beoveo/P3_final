package instruction;

import analisis.LexicalParser;
import exception.ArrayException;
import exception.LexicalAnalisisException;
import generate_bc.Compiler;

/**
 * Interfaz que contiene los metodos abstractos para que las clases que heredan de ella, se parseen y compilen.
 */
public interface Instruction {
	Instruction lexParse(String[] words, LexicalParser lexParser) throws LexicalAnalisisException, ArrayException;
	void compile(Compiler compiler) throws ArrayException;
}
