package paquete;

import exception.ArrayException;
import exception.ExceptionDivisionByZero;
import exception.StackException;
import bytecodes.ByteCode;
import bytecodes.ByteCodeProgram;

/**
 * CLASS CPU:
 *La CPU contiene todos los metodos de las instrucciones bytecode y
 *se encarga de ejecutarlos si son validos.
 */
public class CPU {
	private Memory memoria = new Memory();
    private OperandStack pila = new OperandStack();
    private ByteCodeProgram bcProgram = new ByteCodeProgram();
    private boolean exeHalt = false;
    private int programCounter = 0; 
	public CPU(ByteCodeProgram program){ this.bcProgram = program; }
	  
	
	/**
	 * Metodo que permite la ejecucion de cada instruccion del programa hasta que se encuentre con la isntruccion HALT
	 * o llegue al final del programa.
	 * @return Devuelve true si se han ejecutado todas las instrucciones.
	 * @throws ExceptionDivisionByZero 
	 * @throws StackException 
	 */
	public boolean run() throws ExceptionDivisionByZero, StackException{
		this.programCounter = 0;
		boolean error = false;
		int numBC = bcProgram.getNumBC();
		while (this.programCounter < numBC && !error) {
		ByteCode bc = bcProgram.getByteCode(this.programCounter);
		bc.execute(this);
		if (this.exeHalt){
			error = true;
		}
		}return !error;
	}
	
	/**
	 * Metodo que reemplaza una instruccion del programa de bytecodes.
	 * @param pos: Posicion de la instruccion a reemplazar.
	 * @param bc: ByteCode que la reemplaza.
	 */
	public void replaceBC(int pos, ByteCode bc) throws ArrayException{
		this.bcProgram.replace(pos, bc);
	}
	
	/**
	 * Metodo que devuelve el numero de bytecodes almacenados.
	 * @return NumBC.
	 */
	public int getNumBC(){
		return this.bcProgram.getNumBC();
	}
	
	/**
	 * Metodo que devuelve el bytecode de X posicion.
	 * @param pos: Posicion del bytecode a devolver.
	 * @return Bytecode(pos).
	 */
	public ByteCode getByteCode(int pos){
		return this.bcProgram.getByteCode(pos);
	}
	
	/**
	 * Metodo que devuelve el valor de exeHalt.
	 */
   	public void halt(){ this.exeHalt = true; }
	  
	  /**
	   * El metodo add realiza la suma de la cima y la subcima.
	   * Primero comprueba si hay al menos 2 elementos para poder realizar la operacion.
	   * Si se cumple, entonces se suman los datos de la pila.
	   * @return Devuelve el valor resultante de la suma y se guarda en la pila.
	   * En caso de que no se cumpla la condicion, se devuelve false.
	   */

	public void add() throws StackException{
		int valor1, valor2;
		if(pila.getNumElements() >= 2){
			valor1 = pila.getCima();
			pila.deleteCima();
			valor2 = pila.getCima();
			pila.deleteCima();
			pila.push(valor1 + valor2);
		}
	}
		
	/**
	 * El metodo sub realiza la resta de la subcima y la cima.
	 * Primero comprueba si hay al menos 2 elementos para poder realizar la operacion.
	 * Si se cumple, entonces se restan los datos.
	 * @return Devuelve el valor resultante de la resta y se guarda en la pila.
	 * En caso de que no se cumpla la condicion, se devuelve false.
	 */
	public void sub() throws StackException{
		int valor1, valor2;
		if(pila.getNumElements() >= 2){
			valor1 = pila.getCima();
			pila.deleteCima();
			valor2 = pila.getCima();
			pila.deleteCima();
			pila.push(valor2 - valor1);
		}
	}

	/**
	 * El metodo mul realiza la multiplicacion de la cima y la subcima.
	 * Primero comprueba si hay al menos 2 elementos para poder realizar la operacion.
	 * Si se cumple, entonces se multiplican los datos.
	 * @return Devuelve el valor resultante de la multiplicacion y se guarda en la pila.
	 * En caso de que no se cumpla la condicion, se devuelve false.
	 */
	public void mul() throws StackException{
		int valor1, valor2;
		if(pila.getNumElements() >= 2){
			valor1 = pila.getCima();
			pila.deleteCima();
			valor2 = pila.getCima();
			pila.deleteCima();
			pila.push(valor1 * valor2);
		}
	}

	
	/**
	 * El metodo div realiza la division de la subcima y la cima.
	 * Primero comprueba si hay al menos 2 datos para poder realizar la division,
	 *  si los hay comprueba que la cima sea distinta a cero, ya que cualquier numero dividido entre cero da infinito.
	 *  Si se cumplen estas condiciones realiza la division y guarda el valor en la pila.
	 * @return Devuelve false en caso de que no se cumplan alguna de las dos condiciones y si se cumplen devuelve
	 * el valor llamando al metodo que lo guarda en la pila.
	 * @throws StackException 
	 */

	public void div() throws ExceptionDivisionByZero, StackException{
		int valor1, valor2;
		if(pila.getNumElements() >= 2){
			if(pila.getCima() == 0) throw new ExceptionDivisionByZero("Error: Division por cero.");
			else{
				valor1 = pila.getCima();
				pila.deleteCima();
				valor2 = pila.getCima();
				pila.deleteCima();
				pila.push(valor2 / valor1);
				}
		}
	}
	
	/**
	 * Metodo cambia la siguiente instruccion a ejecutar.
	 * Es decir en vez de ejecutarse la siguiente instruccion, el programa 
	 * va directamente a la posicion de la instruccion que se pasa como parametro.
	 * @param posInstr: Siguiente instruccion a ejecutar.
	 */
	public void goTo(int posInstr){ setProgramCounter(posInstr); }
	
	/**
	 * Metodo que devuelve el valor que se encuentre en esa posicion de memoria.
	 * @param pos: Posicion de memoria a cargar.
	 * @return Valor que se encuentra en esa posicion.
	 * @throws StackException 
	 */
	public void load(int pos) throws StackException{ pila.push(memoria.read(pos));}
	
	/**
	 * Metodo que introduce un dato de memoria en la pila.
	 * @param i: Dato a introducir.
	 * @return Introduce el dato.
	 * @throws StackException 
	 */
	public void push(int i) throws StackException { pila.push(i);}
	
	/**
	 * Metodo que lee un dato de la memoria.
	 * @param param: Posicion de memoria del dato a leer.
	 * @return Devuelve el dato leido.
	 */
	public boolean read(int param) { this.memoria.read(param); return true; }
	
	/**
	 * Metodo que introduce un dato de la pila en la memoria.
	 * @param pos: Posicion de memoria en la que lo introduce.
	 * @return Devuelve true si el dato ha sido introducido en memoria.
	 * @throws StackException 
	 */
	public void write(int pos) throws StackException{memoria.write(pos, pila.load());}
	
	/**
	 * Metodo que saca la cima de la pila.
	 * @return Cima.
	 * @throws StackException 
	 */
	public int getStack() throws StackException{ return pila.load();}
	
	/**
	 * Metodo que devuelve el tama�o de la pila.
	 * @return Tamano pila.
	 */
	public int getSizeStack(){ return pila.getNumElements();}
	
	/**
	 * Metodo que incrementa el contador de programa.
	 */
    public void increaseProgramCounter(){ this.programCounter++;}
	
    /**
     * Metodo que reestablece la siguiente instruccion a ejecutar.
     * @param jump: Posicion de la siguiente instruccion a ejecutar.
     */
	public void setProgramCounter(int jump){ this.programCounter = jump;}
	
	/**
	 * Metodo que obtiene la cima de la pila.
	 * @return Devuelve la cima.
	 */
	public int out() throws StackException{ return this.pila.getCima();}
		
		/**
		 * El metodo reset de la CPU resetea la pila y la memoria.
		 */
	public void reset(){
		pila.reset();
		memoria.reset();
	}
		
		/**
		 * Este metodo cambia el estado de exeHalt una vez que se ejecuta HALT. 
		 * Lo vuelve a cambiar a false.
		 */
	public void resetHalt(){exeHalt = false;}

		/**
		 * Este metodo toString devuelve el estado de la CPU.
		 */
	public String toString(){
		  String s = System.getProperty("line.separator") +
		  "Estado de la CPU: " + System.getProperty("line.separator") +
		  " Memoria: " + this.memoria + System.getProperty("line.separator") + 
		 " Pila: " + this.pila + System.getProperty("line.separator");
		  return s;
    }
}
