import java.io.Serializable;

/**
 * @brief Clase que representa un nodo de una lista doblemente enlazada, con soporte para serialización.
 *
 * Esta clase implementa la interfaz Serializable para permitir la serialización de objetos.
 */
public class Node implements Serializable {
    
    // Atributos de la clase
    private Object data;
    private Node next;
    private Node back;

    /**
     * @brief Constructor de la clase Node con datos, nodo siguiente y nodo anterior.
     *
     * @param o Los datos almacenados en el nodo.
     * @param n El nodo siguiente en la lista.
     * @param b El nodo anterior en la lista.
     */
    public Node(Object o, Node n, Node b) {
        this.data = o;
        this.next = n;
        this.back = b;
    }

    /**
     * @brief Constructor de la clase Node con datos.
     *
     * @param o Los datos almacenados en el nodo.
     */
    public Node(Object o) {
        this.data = o;
        this.next = null;
        this.back = null;
    }

    /**
     * @brief Constructor de la clase Node sin datos.
     */
    public Node() {
        this.data = null;
        this.next = null;
        this.back = null;
    }

    /**
     * @brief Obtiene los datos almacenados en el nodo.
     *
     * @return Los datos almacenados en el nodo.
     */
    public Object getData() {
        return data;
    }

    /**
     * @brief Establece los datos almacenados en el nodo.
     *
     * @param data Los nuevos datos a establecer.
     */
    public void setData(Object data) {
        this.data = data;
    }

    /**
     * @brief Obtiene el nodo siguiente en la lista.
     *
     * @return El nodo siguiente en la lista.
     */
    public Node getNext() {
        return next;
    }

    /**
     * @brief Establece el nodo siguiente en la lista.
     *
     * @param next El nuevo nodo siguiente.
     */
    public void setNext(Node next) {
        this.next = next;
    }

    /**
     * @brief Obtiene el nodo anterior en la lista.
     *
     * @return El nodo anterior en la lista.
     */
    public Node getBack() {
        return back;
    }

    /**
     * @brief Establece el nodo anterior en la lista.
     *
     * @param back El nuevo nodo anterior.
     */
    public void setBack(Node back) {
        this.back = back;
    }
}
