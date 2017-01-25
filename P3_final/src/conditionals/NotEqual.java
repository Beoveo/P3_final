package conditionals;
import generate_bc.Compiler;
import conditionals.ConditionalJumps;
import conditionals.IfNeq;
import exception.ArrayException;
import analisis.Condition;
import analisis.LexicalParser;
import analisis.Term;

/**
 * Clase que hereda de condition y devuelve un objeto nuevo si la condicion es de este tipo. 
 */
public class NotEqual extends Condition {
	private Term t1;
	private Term t2;
	public NotEqual(){}
	public NotEqual(Term term1, Term term2){
		this.t1 = term1;
		this.t2 = term2;
	}
	
	/**
	 * Metodo de tipo condition que parsea el operador y si es de esta clase devuelve el objeto con
	 * los terminos.
	 */
	@Override
	protected Condition parseOp(Term t1, String op, Term t2, LexicalParser lexParser) {
		if(op.equalsIgnoreCase("!=")) return null;
		else return new NotEqual();
	}
	
	/**
	 * Metodo que compila los terminos y los incluye en el programa bytecode.
	 */
	@Override
	protected void nextCompile(Compiler compiler)throws ArrayException {
		compiler.addByteCode(this.t1.compile(compiler));
		compiler.addByteCode(this.t2.compile(compiler));
	}
	
	/**
	 * Metodo que devuelve la condicion de la clase que hereda de bytecode, para incluirlo
	 * en el bytecodeProgram.
	 */
	@Override
	protected ConditionalJumps newCondition() throws ArrayException {return new IfNeq();}
	public String toString(){ return this.t1 + "!=" + this.t2; }
	
}
