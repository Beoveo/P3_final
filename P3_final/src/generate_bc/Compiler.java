package generate_bc;

import exception.ArrayException;
import instruction.Instruction;
import program.ParsedProgram;
import bytecodes.ByteCode;
import bytecodes.ByteCodeProgram;

public class Compiler {
	 private String[] varTable; //guarda las variables en una pos de memoria
	 private ByteCodeProgram bcProgram;
	 private int numVars;
	
	 public Compiler(ByteCodeProgram bcProg){
		 this.varTable = new String[100];
		 this.bcProgram = bcProg;
		 this.numVars = 0;
	 }
	 
	 public void compile(ParsedProgram pProgram) throws ArrayException {
		 int i = 0;
		 while (i < pProgram.getNumInstr()){
		 		Instruction instr = pProgram.getInstr(i);
		 		instr.compile(this);
		 		i++;
		 	}
	 }
	 
	 public void addByteCode(ByteCode b)throws ArrayException {this.bcProgram.insertarByteCode(b);}
	 
	 
	 public int getIndex(String varName) {
		 int pos = 0;
		 boolean find = false;
		 while(pos < this.numVars && !find){
			 if(varTable[pos].equalsIgnoreCase(varName)){
				 find = true;
			 }else pos++;
		 }
		 if(pos == this.numVars){
			 this.varTable[this.numVars] = varName;
			 pos = this.numVars;
			 this.numVars++;
		 }
		return pos;
	 }
	 
	 public int getCurrentNumOfBc() {return bcProgram.getNumBC();} //tamano del programa entero, hasta el END

	}

	
	 


