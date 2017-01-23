package conditionals;

import generate_bc.Compiler;
import conditionals.ConditionalJumps;
import conditionals.Ifle;
import exception.ArrayException;
import analisis.Condition;
import analisis.LexicalParser;
import analisis.Term;

public class Less extends Condition {
	private Term t1;
	private Term t2;
	public Less(){}
	public Less(Term term1, Term term2){
		this.t1 = term1;
		this.t2 = term2;
	}
	
	@Override
	protected Condition parseOp(Term term1, String op, Term term2, LexicalParser lexParser) {
		if(!op.equalsIgnoreCase("<")) return null;
		else return new Less(term1, term2);

	}
	
	@Override
	protected void nextCompile(Compiler compiler)throws ArrayException {
		compiler.addByteCode(this.t1.compile(compiler));
		compiler.addByteCode(this.t2.compile(compiler));
		compiler.addByteCode(this.newCondition());
	}
	
	protected ConditionalJumps newCondition() throws ArrayException { return new Ifle();}
	protected ConditionalJumps auxSetJump(int n, ConditionalJumps cond){ return cond.newJump(n);}	
	
	public String toString(){ return this.t1 + "<" + this.t2; }
}
