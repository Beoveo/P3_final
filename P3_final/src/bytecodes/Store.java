package bytecodes;

import exception.StackException;
import paquete.CPU;
/**
 * CLASE STORE:
 * Clase que hereda metodos de ByteCodeOneParameter. Parsea lo introducido por teclado
 * y ejecuta la instruccion.
 */
public class Store extends ByteCodeOneParameter {
	/**
	 * Constructora que nos permite coger las instrucciones con un parametro.
	 */
	public Store(){};
	public Store(int param){
		super(param);
	}
	/**
	 * Metodo que ejecuta la instruccion STORE. A la cual se le pasa el parametro
	 * que es la posicion de memoria en la que guardaremos el elemento de la pila.
	 * @throws StackException 
	 */
	public void execute(CPU cpu) throws StackException {
		cpu.write(super.param);
		cpu.increaseProgramCounter();
	}
	
	@Override
	/**
	 * Metodo que ya tiene la linea divida en dos palabras, la primera es el nombre de la
	 * instruccion y la segunda es el parametro/valor que usara.
	 * Si coincide con la instruccion devuelve un nuevo objeto de esta, sino devuelve null.
	 */
	protected ByteCode parseAux(String string1, String string2) {
		int param = Integer.parseInt(string2);
		if(!string1.equalsIgnoreCase("STORE") || param < 0) return null;
		else return new Store(param);
	}

	/**
	 * Metodo toString que devuelve el nombre de la instruccion.
	 */
	@Override
	protected String toStringAux() {return "STORE";}
	}
	
