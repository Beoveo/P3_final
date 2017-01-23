package bytecodes;

import arithmetics.Add;
import arithmetics.Div;
import arithmetics.Mul;
import arithmetics.Sub;
import conditionals.IfLeq;
import conditionals.IfNeq;
import conditionals.Ifeq;
import conditionals.Ifle;
import conditionals.GoTo;

/**
 *CLASS BYTECODE-PARSER: 
 *Clase que comprueba de que instruccion bytecode se trata y devuelve un nuevo objeto de esa clase.
 */
public class ByteCodeParser {
	
	private final static ByteCode[] bytecodes = {new Add(),new Sub(), new Div(),
		 new Mul(),new Load(),new Push(), new Ifeq(), new Ifle(), new IfLeq(), new IfNeq(), 
		 new GoTo(), new Store(), new Out(), new Halt()};
	
	/**
	 * Metodo que parsea el bytecode introducido por teclado.
	 * Comprueba qué bytecode es de todos los disponibles y si es un bytecode
	 * devuelve un nuevo objeto de este, sino devuelve null.
	 * @param s: Linea que contiene la instruccion bytecode.
	 * @return Devuelve un nuevo objeto de la instruccion bytecode u
	 * en caso contrario null.
	 */
	public static ByteCode parse(String s){
		s = s.trim();
		String[] words = s.split(" +");
		if (words.length == 0) return null; 
		else {
		boolean found = false;
		int i = 0;
		ByteCode c = null;
		while (i < bytecodes.length && !found){
		c = bytecodes[i].parse(words);
		if (c!=null) found=true;
			else i++;
		 }
		 return c;
		}
	}
		
}
