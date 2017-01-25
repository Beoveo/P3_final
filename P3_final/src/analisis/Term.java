package analisis;

import bytecodes.ByteCode;
import exception.ArrayException;
import generate_bc.Compiler;

public interface Term {
	Term parse(String term);
	ByteCode compile(Compiler compiler) throws ArrayException;
}
