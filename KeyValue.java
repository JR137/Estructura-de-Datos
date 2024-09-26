import java.io.Serializable;

/**
 * @brief Clase que representa un par clave-valor, con soporte para serialización.
 *
 * Esta clase implementa la interfaz Serializable para permitir la serialización de objetos.
 */
public class KeyValue implements Serializable {

    // Atributos de la clase
    private String key;
    private Object value;

    /**
     * @brief Constructor de la clase KeyValue.
     *
     * @param key La clave del par clave-valor.
     * @param value El valor asociado a la clave.
     */
    public KeyValue(String key, Object value) {
        this.key = key;
        this.value = value;
    }

    /**
     * @brief Obtiene la clave del par clave-valor.
     *
     * @return La clave del par.
     */
    public String getKey() {
        return key;
    }

    /**
     * @brief Establece la clave del par clave-valor.
     *
     * @param key La nueva clave a establecer.
     */
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * @brief Obtiene el valor asociado a la clave.
     *
     * @return El valor asociado a la clave.
     */
    public Object getValue() {
        return value;
    }

    /**
     * @brief Establece el valor asociado a la clave.
     *
     * @param value El nuevo valor a asociar.
     */
    public void setValue(Object value) {
        this.value = value;
    }

    /**
     * @brief Compara dos objetos KeyValue para determinar si son iguales.
     *
     * @param obj El objeto a comparar.
     * @return True si los objetos son iguales, false en caso contrario.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj != null) {
            return (this.key.equals(((KeyValue) obj).getKey()));
        }
        return false;
    }

    /**
     * @brief Representación en cadena del par clave-valor.
     *
     * @return Una cadena que representa el par en el formato "clave#valor".
     */
    @Override
    public String toString() {
        return key + "#" + value;
    }
}
