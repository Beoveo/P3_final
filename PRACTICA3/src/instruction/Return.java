package instruction;

import bytecodes.Halt;
import generate_bc.Compiler;
import analisis.LexicalParser;
import exception.ArrayException;


public class Return implements Instruction {

	@Override
	public Instruction lexParse(String[] words, LexicalParser lexParser) {
		if(words.length != 1) return null;
		else{
			if(!words[0].equalsIgnoreCase("return")) return null;
			else{		
					lexParser.increaseProgramCounter();
					return new Return();
				}
			}
	}

	@Override
	public void compile(Compiler compiler) throws ArrayException {compiler.addByteCode(new Halt());}

}
