package instruction;



import conditionals.GoTo;
import analisis.Condition;
import analisis.ConditionParser;
import analisis.LexicalParser;
import program.ParsedProgram;
import exception.ArrayException;
import exception.LexicalAnalisisException;
import generate_bc.Compiler;

public class While implements Instruction{
	private Condition condition;
	private ParsedProgram whileBody;
	
	public While(){}
	public While(Condition cond, ParsedProgram wBody){
		this.condition = cond;
		this.whileBody = wBody;
	}
	 
	public Instruction lexParse(String[] words, LexicalParser lexParser) throws ArrayException, LexicalAnalisisException{
		Condition cond2;
		if(words.length != 4) return null;
		else {
			if(!words[0].equalsIgnoreCase("WHILE")) return null;
			else {
				cond2 = ConditionParser.parse(words[1], words[2], words[3], lexParser);
				if(cond2 == null) throw new LexicalAnalisisException("Error: condicion no valida.");
				lexParser.increaseProgramCounter();
				ParsedProgram wBody = new ParsedProgram();
				lexParser.lexicalParser(wBody, "ENDWHILE");
				return new While(cond2, wBody);
			}
		}
	 }

	 
	 public void compile(Compiler compiler) throws ArrayException{
		int nProg = compiler.getCurrentNumOfBc(); // num de bc antes de compilar while
		this.condition.compile(compiler);
		compiler.compile(this.whileBody);
		this.condition.setJump(compiler.getCurrentNumOfBc() + 1);
		compiler.addByteCode(new GoTo(nProg));
	 }
	 
}
