package instruction;


import exception.ArrayException;
import exception.LexicalAnalisisException;
import generate_bc.Compiler;
import bytecodes.Load;
import bytecodes.Out;
import analisis.LexicalParser;

/**
 * Clase que parsea y compila la intruccion en caso de que sea un WRITE.
 */
public class Write implements Instruction {
	private String varName;
	
	public Write(){}
	public Write(String variable) {
		this.varName = variable;
	}

	/**
	 * Metodo que parsea el sourceProgram, parsea palabra por palabra y devuelve un nuevo objeto de esta clase 
	 * si coincide.
	 */
	@Override
	public Instruction lexParse(String[] words, LexicalParser lexParser) throws LexicalAnalisisException {
		String varName;
		if(words.length != 2) return null;
		else{
			words[0] = words[0].toUpperCase();
			if(!words[0].equalsIgnoreCase("WRITE"))return null;
			else {
				varName = words[1];
				if (varName.length() !=1) throw new LexicalAnalisisException("Error: tamano de la variable erroneo.");
				 else {
					 char name = varName.charAt(0);
					 if ('a' <= name && name <= 'z') {
						 lexParser.increaseProgramCounter();
						 return new Write(varName); 
					 } else throw new LexicalAnalisisException("Error: la variable debe de ser una letra entre la a y la z.");
				 }
			}
		}
	}
	
	 /**
	  * Metodo que compila la instruccion y genera los bytecodes correspondientes.
	  */
	@Override
	public void compile(Compiler compiler) throws ArrayException {
		int index = compiler.getIndex(this.varName); 
		compiler.addByteCode(new Load(index)); 
		compiler.addByteCode(new Out());
	}

	
	public String toString(){
		return "write " + this.varName;
	}
	
}
