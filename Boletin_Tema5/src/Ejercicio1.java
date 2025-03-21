import java.util.ArrayList;
import java.util.Collection;

public class Ejercicio1 {
    public static void main(String[] args) {
        // Creamos una colección de strings
        Collection<String> almacen = new ArrayList<>();
        // Añadimos los nombres
        almacen.add("pepe");
        almacen.add("pepa");
        almacen.add("pepito");

        // Si la lista contiene 'pepe' lo eliminará
        if (almacen.contains("pepe")) {
            almacen.remove("pepe");
        }
        // Imprimimos la lista
        System.out.println(almacen);
    }
}