import java.io.Serializable;

/**
 * @brief Clase que representa una lista doblemente enlazada con soporte para serialización.
 *
 * Esta clase implementa la interfaz Serializable para permitir la serialización de objetos.
 */
public class ListaDinamicaDE implements Serializable {

    // Atributos de la clase
    private int size;
    private Node root;
    private Node last;

    /**
     * @brief Constructor de la clase ListaDinamicaDE.
     *
     * Inicializa una lista doblemente enlazada vacía.
     */
    public ListaDinamicaDE() {
        setSize(0);
        root = last = null;
    }

    // O(n)
    /**
     * Añade un elemento en la posición indicada.
     *
     * @param indice Índice donde se añadirá el elemento.
     * @param o      Objeto que se va a introducir en la lista.
     * @return True si se ha introducido el elemento correctamente, false si no.
     */
    public boolean add(int indice, Object o) {
		if((indice>=0)&&(indice<size)) {
			Node aux;
			if(indice<(size/2)) {
				aux = root;
				int i=0;
				while(i<(indice)) {
					aux=aux.getNext();
					i++;
				}
			}else {
				aux = last;
				int i=size-1;
				while(i>indice) {
					aux=aux.getBack();
					i--;
				}
			}
			Node newNode = new Node(o,aux,aux.getBack());
			aux.getBack().setNext(newNode);
			aux.setBack(newNode);
			size++;
			return true;
		}	
		else if(indice==0) 
			return addFirst(o);
		else if(indice==size) 
			return addLast(o);
		return false;
	}
	
    // O(1)
    /**
     * Añade un elemento al principio de la lista.
     *
     * @param o Objeto que se añadirá al principio de la lista.
     * @return True si se ha introducido el elemento correctamente, false si no.
     */
    public boolean addFirst(Object o) {
		Node newNode= new Node (o,root,null);
		if(!empty()) {
			root.setBack(newNode);
			root=newNode;
		}
		else 
			root=last=newNode;	
		size++;
		return true;
	}
	
 // O(1)
    /**
     * Añade un elemento al final de la lista.
     *
     * @param o Objeto que se añadirá al final de la lista.
     * @return True si se ha introducido el elemento correctamente, false si no.
     */
    public boolean addLast(Object o) {
		Node newNode= new Node (o,null,last);
		if(!empty()) {
			last.setNext(newNode);
			last=newNode;
		}
		else 
			root=last=newNode;					
		size++;
		return true;
	}	
	
    // O(n)
    /**
     * Elimina el elemento en la posición indicada.
     *
     * @param indice Índice del elemento que se va a borrar.
     * @return Elemento borrado, o null si no se ha borrado ningún elemento.
     */
    public Object remove(int indice) {
		if((indice>0)&&(indice<(size-1))) {
			Node aux;
			if(indice<(size/2)) {
				aux = root;
				int i=0;
				while(i<(indice)) {
					aux=aux.getNext();
					i++;
				}
			}else {
				aux = last;
				int i=size-1;
				while(i>indice) {
					aux=aux.getBack();
					i--;
				}
			}
			Object data = aux.getData();	
			aux.getBack().setNext(aux.getNext());
			aux.getNext().setBack(aux.getBack());
			size--;
			return data;
		}	
		else if(indice==0) 
			return removeFirst();
		else if(indice==(size-1)) 
			return removeLast();
		return null;
	}
	
 // O(1)
    /**
     * Elimina el último elemento de la lista.
     *
     * @return Último elemento de la lista, o null si la lista está vacía.
     */
    public Object removeLast() {
		if(!empty()) {
			Object oRet=last.getData();
			last=last.getBack();
			last.setNext(null);
			size--;
			return oRet;
		}
		return null;
	}
	
 // O(1)
    /**
     * Elimina el primer elemento de la lista.
     *
     * @return Primer elemento de la lista, o null si la lista está vacía.
     */
    public Object removeFirst() {
		if(!empty()) {
			Object oRet=root.getData();
			if(size>1) {
				root = root.getNext();
				root.setBack(null);
			}
			else {
				root = last = null;
			}
			size--;
			return oRet;
		}
		return null;
	}

 // O(n)
    /**
     * Obtiene el elemento en la posición indicada.
     *
     * @param indice Índice del elemento que se va a obtener.
     * @return Elemento en la posición indicada, o null si no hay elemento en esa posición.
     */
    public Object get(int indice) {
		if((indice>=0)&&(indice<size)){
			Node aux;
			if(indice>=0) {
				aux = root;
				int i=0;
				while(i<indice) {
					aux=aux.getNext();
					i++;
				}
			}
			else {
				aux = last;
				int i = (size-1);
				while(indice<=i) {
					aux = aux.getBack();
					i--;
				}
			}
			
			return aux.getData();
		}
		return null;
	}
	
 // O(n)
    /**
     * Reemplaza el elemento en la posición indicada con un nuevo objeto.
     *
     * @param indice Índice del elemento que se va a reescribir.
     * @param o      Nuevo objeto que se va a introducir en la posición indicada.
     * @return True si se ha realizado de forma correcta, false si no.
     */
    public boolean set(int indice, Object o) {
		if(indice>=0 && indice<size){
			Node aux;
			if(indice>=0) {
				aux = root;
				int i=0;
				while(i<indice) {
					aux=aux.getNext();
					i++;
				}
			}
			else {
				aux = last;
				int i = (size-1);
				while(indice<=i) {
					aux = aux.getBack();
					i--;
				}
			}
			aux.setData(o);
			return true;
		}
		return false;
	}
	
	public boolean set(Object o) {
		Node aux = root;
		int i=0;
		while(i<size) {
			if(aux.getData().equals(o)) {
				aux.setData(o);
				return true;
			}
		aux=aux.getNext();
		i++;
		}
		return false;
	}
	 // O(n)
    /**
     * Busca un objeto en la lista y devuelve su posición.
     *
     * @param o Objeto que se busca en la lista.
     * @return Posición del objeto en la lista, o -1 si no se ha encontrado.
     */
    public int buscar(Object o) {
		Node aux = root;
		int i=0;
		while(i<size-1) {
			if(aux.getData().equals(o)) {
				return i;
			}
		aux=aux.getNext();
		i++;
		}
		return -1;
	}
	
	public Object buscarObj(Object o) {
		Node aux = root;
		int i=0;
		while(i<size-1) {
			if(aux.getData().equals(o)) {
				return aux.getData();
			}
		aux=aux.getNext();
		i++;
		}
		return null;
	}
	
	
	// O(n)
    /**
     * Elimina un objeto de la lista.
     *
     * @param o Objeto que se va a borrar de la lista.
     * @return Posición del objeto en la lista, o -1 si no se ha encontrado.
     */
    public int remove(Object o) {
		Node aux = root;
		int i = 0;
		while(i<size) {
			if(o.equals(aux.getData())) {
				if(i>0 && i<(size-1)) {
					aux.getBack().setNext(aux.getNext());
					aux.getNext().setBack(aux.getBack());
					size--;
				}
				else if (i ==0) {
					removeFirst();
				}
				else {
					removeLast();
				}
				return i;
			}
			aux = aux.getNext();
			i++;
		}
		return -1;
	}

 // O(n)
    /**
     * Método toString() para obtener una representación en cadena de la lista.
     *
     * @return Cadena que representa la lista.
     */
    @Override
    public String toString() {
		String lista="";
		Node aux = root;
		while(aux!=null) {
			lista+=aux.getData()+" ";
			aux=aux.getNext();
		}
		return lista;
	}


    // O(1)
    /**
     * Obtiene el tamaño de la lista.
     *
     * @return Tamaño de la lista.
     */
    public int getSize() {
		return size;
	}

 // O(1)
    /**
     * Establece el tamaño de la lista.
     *
     * @param size Nuevo tamaño de la lista.
     */
    public void setSize(int size) {
		this.size = size;
	}
	
	/// O(1)
    /**
     * Verifica si la lista está vacía.
     *
     * @return True si la lista está vacía, false si no.
     */
    public boolean empty() {
		return size == 0;
	}
	
 // O(n)
    /**
     * Serializa la lista y guarda el resultado en un archivo.
     *
     * @param rutaFichero Ruta del archivo donde se guardará la lista serializada.
     * @return True si la serialización y escritura fueron exitosas, false si no.
     */
    public boolean serializarFichero(String rutaFichero) {
        FileUtil f = new FileUtil(rutaFichero);
        return f.serializeToFile(this);
    }

    // O(n)
    /**
     * Deserializa un archivo y actualiza la lista con los datos deserializados.
     *
     * @param rutaFichero Ruta del archivo que contiene la lista serializada.
     */
    public void deserializarFichero(String rutaFichero) {
        FileUtil f = new FileUtil(rutaFichero);
        ListaDinamicaDE l = (ListaDinamicaDE) f.deserializeFromFile();
        if (l != null) {
            this.size = l.size;
            this.root = l.root;
            this.last = l.last;
        }
    }

    // O(n)
    /**
     * Escribe los elementos de la lista en un archivo de texto.
     *
     * @param rutaFichero Ruta del archivo de texto donde se escribirán los elementos.
     */
    public void escribirFicheroTexto(String rutaFichero) {
        FileUtil f = new FileUtil(rutaFichero);
        Node aux = root;
        String texto = "";
        while (aux != null) {
            texto += aux.getData() + "\n";
            aux = aux.getNext();
        }
        f.writeToFile(texto);
    }

    // O(n)
    /**
     * Lee el contenido de un archivo de texto y lo devuelve como una cadena.
     *
     * @param rutaFichero Ruta del archivo de texto que se va a leer.
     * @return Contenido del archivo de texto como una cadena.
     */
    public String leerFicheroTexto(String rutaFichero) {
        FileUtil f = new FileUtil(rutaFichero);
        return f.readFromFile();
    }

	//O(n^3)
	/**
	 * Método para borrar los elementos que están duplicados
	 */
	public void borrarDuplicados() {
		Node aux1 = root;
		for(int i=0; i<getSize()-1; i++) {
			Node aux2 = aux1.getNext();
			for(int j = i+1; j< getSize(); j++) {
				if(aux1.getData().equals(aux2.getData())) {
					remove(j);
					j = j-1;
				}
				aux2 = aux2.getNext();
			}
			aux1 = aux1.getNext();
		}
	}

}