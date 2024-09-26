/**
 * @file Main.java
 * @brief Clase Main para un programa que gestiona una tabla hash de coches.
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @class Main
 * @brief Clase principal que contiene el punto de entrada para el programa de gestión de coches.
 */
public class Main {

    /**
     * Método principal donde comienza la ejecución del programa.
     * @param args Argumentos de la línea de comandos (no utilizados en este programa).
     */
    public static void main(String[] args) {
        // Crear una tabla hash
        TablaHash tabla = new TablaHash(10, 0.75);
        // Crear un escáner para leer la entrada del usuario
        Scanner scanner = new Scanner(System.in);

        // Menú principal
        while (true) {
            System.out.println("0. Generar conjunto de coches");
            System.out.println("1. Agregar coche");
            System.out.println("2. Buscar coche");
            System.out.println("3. Ver coches");
            System.out.println("4. Buscar desde fichero");
            System.out.println("5. Guardar coches en fichero binario");
            System.out.println("6. Cargar coches desde fichero binario");
            System.out.println("7. Guardar coches en fichero de texto");
            System.out.println("8. Cargar coches desde fichero de texto");
            System.out.println("9. Salir");
            System.out.println("10. Realizar pruebas de tiempo de búsqueda");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();

            // Opción 0: Generar un conjunto de coches
            if (opcion == 0) {
                // Generar un conjunto de pares clave-valor
                List<KeyValue> pares = new ArrayList<>();
                for (int i = 0; i < 5; i++) {
                    String matricula = "Matricula" + i;
                    String marca = "Marca" + i;
                    Coche coche = new Coche(matricula, marca);
                    KeyValue kv = new KeyValue(matricula, coche);
                    pares.add(kv);
                }

                // Agregar los pares a la tabla hash
                for (KeyValue kv : pares) {
                    tabla.put(kv);
                }
                System.out.println("Coches generados y agregados exitosamente.");
            }

            // Opción 1: Agregar coche
            if (opcion == 1) {
                System.out.print("Introduzca la matrícula del coche: ");
                String matricula = scanner.next();
                System.out.print("Introduzca la marca del coche: ");
                String marca = scanner.next();
                Coche coche = new Coche(matricula, marca);
                KeyValue kv = new KeyValue(matricula, coche);
                tabla.put(kv);
                System.out.println("Coche agregado exitosamente.");
            }

            // Opción 2: Buscar coche
            else if (opcion == 2) {
                System.out.print("Introduzca la matrícula del coche a buscar: ");
                String matricula = scanner.next();
                KeyValue kv = (KeyValue) tabla.get(matricula);
                if (kv != null) {
                    Coche coche = (Coche) kv.getValue();
                    System.out.println("Coche encontrado: " + coche.getMarca());
                } else {
                    System.out.println("Coche no encontrado.");
                }
            }

            // Opción 3: Ver coches
            else if (opcion == 3) {
                for (KeyValue kv : tabla.getAll()) {
                    Coche coche = (Coche) kv.getValue();
                    System.out.println("Matrícula: " + kv.getKey() + ", Marca: " + coche.getMarca());
                }
            }

            // Opción 4: Buscar desde fichero
            else if (opcion == 4) {
                System.out.print("Introduzca la matrícula del coche a buscar en el fichero: ");
                String matricula = scanner.next();
                tabla.leerFicheroTexto();
                KeyValue kv = (KeyValue) tabla.get(matricula);
                if (kv != null) {
                    Coche coche = (Coche) kv.getValue();
                    System.out.println("Coche encontrado: " + coche.getMarca());
                } else {
                    System.out.println("Coche no encontrado.");
                }
            }

            // Opción 5: Guardar coches en fichero binario
            else if (opcion == 5) {
                tabla.serializarFichero();
                System.out.println("Coches guardados en fichero.");
            }

            // Opción 6: Cargar coches desde fichero binario
            else if (opcion == 6) {
                tabla.deserializarFichero();
                System.out.println("Coches cargados desde fichero.");
            }

            // Opción 7: Guardar coches en fichero de texto
            else if (opcion == 7) {
                tabla.escribirFicheroTexto();
                System.out.println("Coches guardados en fichero de texto.");
            }

            // Opción 8: Cargar coches desde fichero de texto
            else if (opcion == 8) {
                tabla.leerFicheroTexto();
                System.out.println("Coches cargados desde fichero de texto.");
            }

            // Opción 9: Salir
            else if (opcion == 9) {
                System.out.println("Saliendo...");
                break;
            }

            // Opción 10: Realizar pruebas de tiempo de búsqueda
            else if (opcion == 10) {
                long startTime, endTime, duration;

                // Búsqueda en memoria
                for (int i = 0; i < 25; i++) {
                    startTime = System.nanoTime();
                    KeyValue kvMemoria = (KeyValue) tabla.get("Matricula" + i);
                    endTime = System.nanoTime();
                    duration = endTime - startTime;
                    System.out.println("Tiempo de búsqueda en memoria para Matricula" + i + ": " + duration + " nanosegundos");
                }

                // Búsqueda en fichero
                tabla.leerFicheroTexto(); // Carga los datos del fichero
                for (int i = 0; i < 25; i++) {
                    startTime = System.nanoTime();
                    KeyValue kvFichero = (KeyValue) tabla.get("Matricula" + i);
                    endTime = System.nanoTime();
                    duration = endTime - startTime;
                    System.out.println("Tiempo de búsqueda en fichero para Matricula" + i + ": " + duration + " nanosegundos");
                }
            }
        }
        scanner.close();
    }
}
