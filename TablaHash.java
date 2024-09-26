import java.util.ArrayList;
import java.util.List;

/**
 * @brief Implementación de una tabla hash con métodos de serialización y
 *        deserialización.
 *
 *        Esta clase proporciona una estructura básica de tabla hash con métodos
 *        para agregar, eliminar y recuperar pares clave-valor.
 */
public class TablaHash {

    // Atributos de la clase
    private ListaDinamicaDE[] tabla;
    private double loadFactor;
    private int capacity;
    private int size;
    private final static String RUTA_TEXTO = "data\\texto\\";
    private final static String RUTA_BINARIO = "data\\binario\\";

    /**
     * @brief Constructor para la clase TablaHash.
     *
     *        Inicializa una tabla hash con la capacidad y el factor de carga dados.
     *
     * @param capacity   La capacidad inicial de la tabla hash.
     * @param loadFactor El factor de carga para determinar cuándo volver a hashear
     *                   la tabla.
     */
    public TablaHash(int capacity, double loadFactor) {
        this.capacity = capacity;
        this.loadFactor = loadFactor;
        this.tabla = new ListaDinamicaDE[capacity];
        for (int i = 0; i < capacity; i++) {
            this.tabla[i] = new ListaDinamicaDE();
        }
    }

    /**
     * @brief Calcula el factor de carga actual de la tabla hash.
     *
     * @return El factor de carga actual.
     */
    private double calculoLoadFactor() {
        return size / capacity;
    }

    /**
     * @brief Verifica si el factor de carga supera el umbral establecido.
     *
     * @return True si el factor de carga es mayor o igual al umbral, false en caso
     *         contrario.
     */
    private boolean checkLoad() {
        if (calculoLoadFactor() >= loadFactor) {
            return true;
        }
        return false;
    }

    /**
     * @brief Función de hash para una clave dada.
     *
     * @param key La clave para la cual se calculará el hash.
     * @return El índice calculado en la tabla hash.
     */
    private int hash(String key) {
        int suma = 0;
        for (int i = 0; i < key.length(); i++) {
            suma += key.charAt(i) * Math.pow(31, (i + 1));
        }
        return (suma % capacity);
    }

    /**
     * @brief Realiza la rehashing de la tabla cuando el factor de carga supera el
     *        umbral.
     */
    private void reHash() {
        int nuevaCapacidad = 2 * capacity;
        TablaHash hstemp = new TablaHash(nuevaCapacidad, loadFactor);
        for (int i = 0; i < capacity; i++) {
            ListaDinamicaDE lista = tabla[i];
            for (int j = 0; j < lista.getSize(); j++) {
                hstemp.put((KeyValue) lista.removeFirst());
            }
        }
        this.tabla = hstemp.tabla;
        this.capacity = nuevaCapacidad;
    }

    /**
     * @brief Recupera el valor asociado a una clave dada.
     *
     * @param k La clave para la cual se busca el valor.
     * @return El valor asociado a la clave, o null si la clave no está presente.
     */
    public Object get(String k) {
        int pos = hash(k);
        ListaDinamicaDE lista = tabla[pos];
        Object res = lista.buscarObj(new KeyValue(k, null));
        return res;
    }

    /**
     * @brief Agrega un par clave-valor a la tabla hash.
     *
     * @param kv El par clave-valor que se agregará.
     * @return True si la adición es exitosa, false en caso contrario.
     */
    public boolean put(KeyValue kv) {
        if (get(kv.getKey()) == null) {
            int pos = hash(kv.getKey());
            tabla[pos].addLast(kv);
            size++;
            if (checkLoad()) {
                reHash();
            }
            return true;
        }
        return false;
    }

    /**
     * @brief Elimina un elemento de la tabla hash dado su clave.
     *
     * @param clave La clave del elemento que se eliminará.
     * @return True si la eliminación es exitosa, false en caso contrario.
     */
    public boolean remove(String clave) {
        int posicion = hash(clave);
        int indice = tabla[posicion].remove(new KeyValue(clave, null));
        if (indice != -1) {
            size--;
            return true;
        }
        return false;
    }
    public List<KeyValue> getAll() {
        List<KeyValue> allItems = new ArrayList<>();
        for (ListaDinamicaDE lista : tabla) {
            for (int i = 0; i < lista.getSize(); i++) {
                allItems.add((KeyValue) lista.get(i));
            }
        }
        return allItems;
    }

    /**
     * @brief Reemplaza el valor asociado a una clave dada con un nuevo par
     *        clave-valor.
     *
     * @param kv El nuevo par clave-valor.
     * @return True si el reemplazo es exitoso, false en caso contrario.
     */
    public boolean replace(KeyValue kv) {
        int posicion = hash(kv.getKey());
        return tabla[posicion].set(kv);
    }

    /**
     * @brief Serializa la tabla hash y guarda los datos en archivos binarios.
     */
    public void serializarFichero() {
        for (int i = 0; i < capacity; i++) {
            this.tabla[i].serializarFichero(RUTA_BINARIO + i + ".data");
        }
    }

    /**
     * @brief Deserializa la tabla hash a partir de archivos binarios.
     */
    public void deserializarFichero() {
        for (int i = 0; i < capacity; i++) {
            this.tabla[i].deserializarFichero(RUTA_BINARIO + i + ".data");
            this.size += this.tabla[i].getSize();
        }
    }

    /**
     * @brief Escribe la tabla hash en archivos de texto.
     */
    public void escribirFicheroTexto() {
        for (int i = 0; i < capacity; i++) {
            this.tabla[i].escribirFicheroTexto(RUTA_TEXTO + i + ".data");
        }
    }

    /**
     * @brief Lee la tabla hash desde archivos de texto y la reconstruye.
     */
    public void leerFicheroTexto() {
        for (int i = 0; i < capacity; i++) {
            String texto = this.tabla[i].leerFicheroTexto(RUTA_TEXTO + i + ".data");
            String[] pares = texto.split("\n");
            for (int j = 0; j < pares.length - 1; j++) {
                String[] par = pares[j].split("#");
                String[] atributos = par[1].split("@");
                // TODO: ESTO SE CAMBIA, NO METER COCHE/
                KeyValue kv = new KeyValue(par[0], new Coche(atributos[0], atributos[1]));
                this.put(kv);
            }
        }
    }
}
