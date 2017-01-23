package paquete;

import exception.StackException;

/**
 * 
 * CLASS OPERANDSTACK:
 * La clase OperandStack gestiona todo lo que tenga que ver con la pila
 * Operaciones que maneja, inicializacion, reset, devolver informacion..
 * El tamano maximo de la pila es de 80. 
 */

public class OperandStack {
	public static final int MAX_STACK = 100;
	private int numElements;											
	private int[] stack;
	
	/**
	 *  La constructora inicializa el contador de elementos numElements a cero
	 *  y la pila.
	 */
	
	public OperandStack (){
		this.numElements = 0;
		this.stack = new int[OperandStack.MAX_STACK];
	}
	
	/**
	 * Este metodo vacia la pila poniendo vacio a true y el contador a cero.
	 * No recibe nada.
	 * @return Devuelve si la pila esta vacia.
	 */
	
	public boolean vacio(){
		boolean vacio = false;
		if(this.numElements == 0) vacio = true;
		return vacio;
	}
	/**
	 * Este metodo introduce en la pila los valores que se le pasen siempre y cuando
	 * tenga espacio.
	 * Recibe:
	 * @param valor : Dato a añadir.
	 * @return true; si lo ha podido añadir, y aumenta el contador.
	 * false ; si no hay espacio.
	 */
	
	public void push(int valor) throws StackException{											
		if (this.numElements >= MAX_STACK) throw new StackException("Error: La pila esta llena.");
		else{
			stack[this.numElements] = valor;
			this.numElements++;
		}
	}

	/**
	 * Este metodo devuelve el ultimo valor de la pila y lo elimina.
	 * No recibe nada.
	 * @return valor : Devuelve la cima de la pila y elimina el dado.
	 * @throws StackException 
	 */
	 
	public int load() throws StackException{					
		int valor = getCima();
		this.numElements--;
		return valor;
	}
	
	/**
	 * Este metodo devuelve el contador o numero de elementos.
	 * @return numElements.
	 */
	public int getNumElements(){
		return this.numElements;
	}
	
	/**
	 * Este metodo devuelve la cima de la pila.
	 * @return cima.
	 */
	public int getCima() throws StackException{
		if(this.numElements == 0) throw new StackException("Error: La pila esta vacia.");
		else return stack[this.numElements - 1];
	}
	

	/**
	 * Este metodo borra la cima de la pila.
	 * No devuelve nada.
	 */
	
	public void deleteCima(){
		this.numElements--;
	}
	
	/**
	 *  Este metodo borra todo el contenido de la pila poniendo el contador a cero.
	 */
	
	public void reset(){
		for(int i = 0; i <= this.numElements; i++) stack[i] = 0; 
		this.numElements = 0;
	}
	
	/**
	 * El metodo toString de esta clase devuelve el contenido de la pila si tiene algun elemento
	 * y vacio ni no posee nada. 
	 */
	
	public String toString(){
		if (this.numElements==0) 
			return "<vacia>";
		else {
				String s="";
				for (int i=0; i < this.numElements; i++) s = s + this.stack[i] + " ";
				return s;
			}
	}
	

}