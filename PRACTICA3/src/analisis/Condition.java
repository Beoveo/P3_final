package analisis;


import conditionals.ConditionalJumps;
import exception.ArrayException;
import generate_bc.Compiler;

public abstract class Condition {
	 private Term t1 ,t2;
	 protected ConditionalJumps condition; //para la compilacion 
	 
	 
	 public Condition(){};
	 public Condition (Term term1, Term term2){
		 this.t1 = term1;
		 this.t2 = term2;
	 }
	 
	 public Condition parse(String term1, String op, String term2, LexicalParser parser){
		 Term t1 = TermParser.parse(term1);
		 Term t2 = TermParser.parse(term2);
		 return parseOp(t1, op, t2, parser);
	 }

	 public void compile(Compiler compiler) throws ArrayException{
		 this.condition = this.newCondition();
		 compiler.addByteCode(this.condition);
	 }
	
	
	protected abstract Condition parseOp(Term t1, String op, Term t2, LexicalParser lexParser);
    protected abstract void nextCompile(Compiler compiler) throws ArrayException;
	protected abstract ConditionalJumps newCondition() throws ArrayException;
    
	public void setJump(int jump){this.condition = condition.newJump(jump);}
	

	public String toString(){
		return "" + this.t1 + "" + this.condition + "" + this.t2;
	}
}


