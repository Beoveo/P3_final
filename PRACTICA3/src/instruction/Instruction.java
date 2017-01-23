package instruction;

import analisis.LexicalParser;
import exception.ArrayException;
import exception.LexicalAnalisisException;
import generate_bc.Compiler;


public interface Instruction {
	Instruction lexParse(String[] words, LexicalParser lexParser) throws LexicalAnalisisException, ArrayException;
	void compile(Compiler compiler) throws ArrayException;
}
