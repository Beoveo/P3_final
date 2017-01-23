package analisis;

import bytecodes.ByteCode;
import bytecodes.Load;
import exception.ArrayException;
import generate_bc.Compiler;

public class Variable implements Term{
	private String varName;
	 
	public Variable(){}
	public Variable(String term) {
		this.varName = term; 
	}

	@Override
    public Term parse(String term) {
	 if (term.length() !=1) return null;
	 else {
		 char name = term.charAt(0);
		 if ('a' <= name && name <= 'z') return new Variable(term);
		 else return null;
	 	 }
	 }

	@Override
	 public ByteCode compile(Compiler compiler)throws ArrayException{ 
		 int i = compiler.getIndex(this.varName);
		 return new Load(i);
	 }
	
	 public String toString(){ return this.varName; }
	
}
