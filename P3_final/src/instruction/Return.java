package instruction;

import bytecodes.Halt;
import generate_bc.Compiler;
import analisis.LexicalParser;
import exception.ArrayException;

/**
 * Clase que parsea y compila la intruccion en caso de que sea un RETURN.
 */
public class Return implements Instruction {
	/**
	 * Metodo que parsea el sourceProgram, parsea palabra por palabra y devuelve un nuevo objeto de esta clase 
	 * si coincide.
	 */
	@Override
	public Instruction lexParse(String[] words, LexicalParser lexParser) {
		if(words.length != 1) return null;
		else{
			words[0] = words[0].toUpperCase();
			if(!words[0].equalsIgnoreCase("RETURN")) return null;
			else{		
					lexParser.increaseProgramCounter();
					return new Return();
				}
			}
	}

	 /**
	  * Metodo que compila la instruccion y genera los bytecodes correspondientes.
	  */
	@Override
	public void compile(Compiler compiler) throws ArrayException {compiler.addByteCode(new Halt());}

}
