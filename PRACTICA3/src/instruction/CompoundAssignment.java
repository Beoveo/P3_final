package instruction;


import bytecodes.Store;
import exception.ArrayException;
import exception.LexicalAnalisisException;
import analisis.LexicalParser;
import analisis.Term;
import analisis.TermParser;
import arithmetics.Add;
import arithmetics.Div;
import arithmetics.Mul;
import arithmetics.Sub;
import generate_bc.Compiler;


public class CompoundAssignment implements Instruction {
	 private String varName, operator;
	 private Term t1, t2;
	 
	 public CompoundAssignment(){}
	 public CompoundAssignment(String varName1, Term term1, String op, Term term2) {
		 this.varName = varName1;
		 this.operator = op;
		 this.t1 = term1;
		 this.t2 = term2;
	 }

	@Override
	 public Instruction lexParse(String[] words, LexicalParser lexParser) throws LexicalAnalisisException{
			 if (words.length != 5)return null;
			 else {
				Term term = TermParser.parse(words[0]);
				if(term == null || !words[1].equalsIgnoreCase("="))throw new LexicalAnalisisException("Error: asignacion incorrecta.");
				else{ 
					Term t1 = TermParser.parse(words[2]);
					Term t2 = TermParser.parse(words[4]);
					if(t1 == null || t2 == null || (!words[3].equalsIgnoreCase("+") && !words[3].equalsIgnoreCase("-") && !words[3].equalsIgnoreCase("*") && !words[3].equalsIgnoreCase("/")) ){
						throw new LexicalAnalisisException("Error: termino/s o operando incorrecto.");
					}
					else{
						lexParser.increaseProgramCounter();
						return new CompoundAssignment(words[0], t1, words[3], t2);
					 } 
				   }
			  }
		 }
	 

	 @Override
	 public void compile(Compiler compiler)throws ArrayException{ 
		 
		 
		 compiler.addByteCode(this.t1.compile(compiler));
		 compiler.addByteCode(this.t2.compile(compiler));
		 
		 if(this.operator.equalsIgnoreCase("+")) compiler.addByteCode(new Add());
		 	else if(this.operator.equalsIgnoreCase("*")) compiler.addByteCode(new Mul());
		 		else if(this.operator.equalsIgnoreCase("/")) compiler.addByteCode(new Div());
		 			else if(this.operator.equalsIgnoreCase("-")) compiler.addByteCode(new Sub());
		 
		 int pos = compiler.getIndex(this.varName);
		 compiler.addByteCode(new Store(pos)); 
	 }
	 
	 public String toString(){
		 return this.varName + " = " + this.t1 + "" + this.operator + "" +this.t2;
	 }
	 
}
