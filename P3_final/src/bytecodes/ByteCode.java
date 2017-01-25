package bytecodes;


import exception.ExceptionDivisionByZero;
import exception.StackException;
import paquete.CPU;

/**
 * CLASS BYTECODE:
 * En la clase bytecode se parsean y ejecutan las instrucciones
 * en cada una de las clases de las instrucciones bytecode.
 */
public interface ByteCode {
	void execute(CPU cpu) throws ExceptionDivisionByZero, StackException;
	ByteCode parse(String[] words);
}
