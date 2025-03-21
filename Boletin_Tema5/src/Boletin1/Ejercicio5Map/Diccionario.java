package Boletin1.Ejercicio5Map;

import java.util.*;

public class Diccionario {

    // Creamos los atributos
    private Map<String, List<String>> palabras;

    // Creamos el constructor
    public Diccionario() {
        this.palabras = new HashMap<>();
    }

    // Hacemos los get y set
    public Map<String, List<String>> getPalabras() {
        return palabras;
    }

    private void setPalabras(Map<String, List<String>> palabras) {
        this.palabras = palabras;
    }

    // Hacemos un método para añadir una palabra
    public void anadirPalabra(String palabra, String significado) {
        if (palabras.containsKey(palabra)) {
            palabras.get(palabra).add(significado);

        } else {
            palabras.put(palabra, new ArrayList<>());
            palabras.get(palabra).add(significado);
        }
    }

    // Hacemos un método para buscar una palabra en el diccionario
    public String buscarPalabra(String palabra) throws DiccionarioException {
        StringBuilder sb = new StringBuilder();
        if (palabras.containsKey(palabra)) {
            sb.append("Significado de ").append(palabra).append(palabras.get(palabra));

        } else {
            throw new DiccionarioException("No se ha encontrado la palabra");
        }
        return sb.toString();
    }

    // Hacemos un método para eliminar una palabra
    public void eliminarPalabra(String palabra) throws DiccionarioException {
        if (palabras.containsKey(palabra)) {
            palabras.remove(palabra);

        } else {
            throw new DiccionarioException("No se ha encontrado la palabra");
        }
    }

    // Hacemos un método que va a devolver una lista de las palabras que empiecen por un prefijo ordenado alfabéticamente
    public List<String> listaOrdenada(String prefijo) throws DiccionarioException {
        // Hacemos una lista donde vamos a guardar las palabras encontradas
        List<String> listaOrdenada = new ArrayList<>();
        // Recorremos la lista de claves del mapa
        for (String palabra : palabras.keySet()) {
            // Si la palabra empieza por el prefijo lo añadimos a la lista
            if (palabra.startsWith(prefijo)) {
                listaOrdenada.add(palabra);
            }
        }
        if (listaOrdenada.isEmpty()) {
            throw new DiccionarioException("No se han encontrado palabras");
        }
        listaOrdenada.sort(null);
        return listaOrdenada;
    }

    // Hacemos un toString
    @Override
    public String toString() {
        return String.format("Palabras del diccionario: %s", this.palabras);
    }
}