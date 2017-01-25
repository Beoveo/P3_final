package conditionals;

import exception.StackException;
import bytecodes.ByteCodeOneParameter;
import paquete.CPU;
/**
 * CLASE CONDITIONAL_JUMPS:
 * Clase que hereda metodos de bytecodeOneParameter,
 * si la instruccion es un salto, se ejecuta.
 *
 */
abstract public class ConditionalJumps extends ByteCodeOneParameter{
	/**
	 * Constructoras que permiten que las intrucciones de salto tengan parametros.
	 */
	public ConditionalJumps(){}
	abstract protected boolean compare(int n1, int n2);
	public ConditionalJumps(int j){ 
		super(j); 
	}
	
	/**
	 * Metodo que comprueba que existan al menos dos datos en la pila,
	 * si los hay coge la cima y la subcima y se las pasa al metodo compare(),
	 * si la comparacion devuelve true se incrementa el contador y se sigue ejecutando el programa.
	 * Si devuelve false se produce el salto a la pos de la instruccion indicada.
	 * @throws StackException 
	 */
	@Override
	public void execute(CPU cpu) throws StackException {
		if (cpu.getSizeStack()>=2){
			int n1 = cpu.getStack();
			int n2 = cpu.getStack();
			if (compare(n2,n1))cpu.increaseProgramCounter();
			else cpu.setProgramCounter(this.param);
		}
	}
	
	public ConditionalJumps newJump(int n) {
		super.param = n;
		return this;
	}
	
}
