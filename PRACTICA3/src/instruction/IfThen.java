package instruction;


import exception.ArrayException;
import program.ParsedProgram;
import analisis.Condition;
import analisis.ConditionParser;
import analisis.LexicalParser;
import generate_bc.Compiler;
import exception.LexicalAnalisisException;


public class IfThen implements Instruction {
	private Condition condition;
	private ParsedProgram ifBody;
	
	public IfThen(){}
	public IfThen(Condition cond, ParsedProgram body) {
		this.condition = cond;
		this.ifBody = body;
	}
	
	public Instruction lexParse(String[] words, LexicalParser lexParser) throws LexicalAnalisisException, ArrayException{
		 if(words.length != 4) return null;
			else{
				words[0] = words[0].toUpperCase();
				if(!words[0].equalsIgnoreCase("IF")) return null;
				else {
					Condition cond = ConditionParser.parse(words[1], words[2], words[3], lexParser);
					if(cond == null) throw new LexicalAnalisisException("Error: condicion no correcta.");
					else{
						lexParser.increaseProgramCounter();
						ParsedProgram ifbody = new ParsedProgram();
						lexParser.lexicalParser(ifbody, "ENDIF");
						return new IfThen(cond, ifbody);
					}
				}
			}
	 }

	public void compile(Compiler compiler) throws ArrayException{
		this.condition.compile(compiler);
		compiler.compile(this.ifBody);
		this.condition.setJump(compiler.getCurrentNumOfBc());
	}
 
	public String toString(){
		return "if " + this.condition;
	}
	
}	
