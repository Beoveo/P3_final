package paquete;
/**
 * CLASS MEMORY:
 * En memory tratamos la memoria mediante escritura y lectura.
 * Ademas si se ocupa todo el tamano esta se redimensiona.
 */
public class Memory {
	
	private final static int MAX_MEM = 4;
	private Integer[] memory;
	private int tamMem;
	private boolean vacio;

/**
 * En esta constructora debemos inicializar vacio, ya que la memoria empieza estando vacia.
 * Tambien se inicializa el array de enteros, con la longitud MAX_MEM.
 * Ademas de inicializarse el tamMem y la memoria, porque no en todos los sistemas operativos se inicializa sola.
 */
	public Memory(){
		this.vacio = true;
		this.memory = new Integer[Memory.MAX_MEM];
		this.tamMem = Memory.MAX_MEM;
		for (int i=0; i< this.tamMem; i++) this.memory[i]=null; //inicializa la memoria ya que no todos los Sist. Operativos la inicializan por defecto.
	}
	
	/**
	 * Este metodo redimensiona la memoria en caso de que no haya espacio suficiente o lo que es lo mismo,
	 * que la posicion indicada no exista.
	 * @param pos Es la pos introducida por el usuario, la cual indica la posicion de memoria en la que introduciremos la cima de la pila.
	 */
	private void redimensionar(int pos){
		int oldTam = this.tamMem;
		if(pos >= this.tamMem){
			this.tamMem = pos * 2;
			Integer[] aux = new Integer[this.tamMem];
			for(int i = 0; i < oldTam; i++){
				aux[i]= memory[i];
			}
			this.memory = aux;
		}
	}
	
	/**
	 * Este metodo escribe la cima de la pila
	 * en la pos de memoria que se indique (STORE).
	 * Aqui comprueba si debe redimensionarse o no la memoria.
	 * @param pos Es la pos de memoria introducida por teclado.
	 * @param valor Es la cima de la pila.
	 * @return Devuelve true si se ha asignado el valor correctamente y false si ocurre lo contrario.
	 */
	
	public void write(int pos, int valor){
		redimensionar(pos);
		this.memory[pos] = valor;
		this.vacio = false;
			
	}
	
	/**
	 * Este metodo lee el dato de la pos de memoria
	 * indicada y lo devuelve a la pila (LOAD).
	 * @param pos Es la pos introducida por teclado que nos indica el valor que debemos sacar de la memoria.
	 * @return Devuelve el valor de esa pos de memoria que se pasara a la pila.
	 */

	public int read(int pos){
		int valor;
		if(this.memory[pos] == null){
			valor = 0;
		} else
		valor = this.memory[pos];
		return valor;
	}
	
	/**
	 * Este metodo resetea la memoria. Y cambia el estado de vacio a true.
	 */
	
	public void reset(){
		for (int i = 0; i < this.tamMem; i++) this.memory[i] = null;
		this.vacio = true;
	}
	
	/**
	 * El metodo toString muestra por pantalla todos los datos almacenados en la memoria,
	 *  en caso de que este vacia, muestra "vacia".
	 */
	public String toString(){
		String s = "";
		if (this.vacio) return "<vacia>"; 
		else {
			for (int i = 0; i < this.tamMem; i++)
				if (this.memory[i]!=null) s = s + " [" + i +"]:" + this.memory[i]; 
			}return s;
	}
	
}
