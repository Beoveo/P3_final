package instruction;



import bytecodes.Store;
import exception.ArrayException;
import analisis.LexicalParser;
import analisis.Term;
import analisis.TermParser;
import generate_bc.Compiler;
import exception.LexicalAnalisisException;


public class SimpleAssignment implements Instruction{
	 private String varName;
	 private Term rhs;
	 
	 public SimpleAssignment() {}
	 public SimpleAssignment(String varName2, Term rhs2) {
		this.varName = varName2;
		this.rhs = rhs2;
	}

	

	public Instruction lexParse(String[] words, LexicalParser lexParser) throws LexicalAnalisisException{
		if (words.length != 3) return null;
		 else {
			Term term = TermParser.parse(words[0]);
			if(term == null || !words[1].equalsIgnoreCase("=")) throw new LexicalAnalisisException("Error: asignacion incorrecta.");
			else{ 
				 Term rhs = TermParser.parse(words[2]);
				 if(rhs == null) throw new LexicalAnalisisException("Error: variable o numero incorrecto.");
				 else {
					 lexParser.increaseProgramCounter();
					 return new SimpleAssignment(words[0], rhs);
				 } 
			 }
		 }
	}

	 
	 public void compile(Compiler compiler) throws ArrayException{ 
		 compiler.addByteCode(this.rhs.compile(compiler));
		 int pos = compiler.getIndex(this.varName);
		 compiler.addByteCode(new Store(pos)); 
	 }
	 
	 public String toString(){
		 return this.varName + " = " + this.rhs; 
	 }
	
}
