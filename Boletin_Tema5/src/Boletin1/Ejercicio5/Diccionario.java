package Boletin1.Ejercicio5;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Diccionario {

    // Creamos el atributo, que va a ser una lista de palabras
    private List<Palabra> palabras;

    // Creamos el constructor
    public Diccionario() {
        this.palabras = new ArrayList<>();
    }

    // Hacemos los get y set
    public List<Palabra> getPalabras() {
        return palabras;
    }

    private void setPalabras(List<Palabra> palabras) {
        this.palabras = palabras;
    }

    // Hacemos un toString
    @Override
    public String toString() {
        return String.format("Palabras: %s", this.palabras);
    }

    // Hacemos un método para añadir una palabra al diccionario
    public void anadirPalabra(Palabra palabra) {
        // Si la palabra ya existe, se le añadirá un nuevo significado
        if (palabras.contains(palabra)) {
            Iterator<Palabra> it = palabras.iterator();
            int contador = 0;
            while (it.hasNext()) {
                if (palabra.equals(it.next())) {
                    palabras.get(contador).getSignificados().addAll(palabra.getSignificados());
                    break;
                }
                contador++;
            }
            // Si la palabra no existe se le añade la palabra directamente
        } else {
            palabras.add(palabra);
        }
    }

    // Hacemos un método para buscar una palabra en el diccionario y mostrar sus significados
    public String buscarPalabra(Palabra palabra) throws DireccionExcepcion {
        StringBuilder sb = new StringBuilder();
        if (palabras.contains(palabra)) {
            Iterator<Palabra> it = palabras.iterator();
            int contador = 0;
            while (it.hasNext()) {
                if (palabra.equals(it.next())) {
                    sb.append("Significados de ").append(palabra.getNombre()).append(palabras.get(contador).getSignificados());
                    break;
                }
                contador++;
            }
        } else {
            throw new DireccionExcepcion("No se ha encontrado la palabra");
        }
        return sb.toString();
    }

    // Hacemos un método para eliminar una palabra
    public void eliminarPalabra(Palabra palabra) throws DireccionExcepcion {
        if (palabras.contains(palabra)) {
            Iterator<Palabra> it = palabras.iterator();
            while (it.hasNext()) {
                if (palabra.equals(it.next())) {
                    it.remove();
                    break;
                }
            }
        } else {
            throw new DireccionExcepcion("No se ha encontrado la palabra");
        }
    }

    // Hacemos un método para mostrar una lista con palabras
    public List<Palabra> copiaOrdenada(String prefijo) throws DireccionExcepcion {
        ArrayList<Palabra> listaPrefijosOrdenadas = new ArrayList<>();
        boolean encontrada = false;
        for (Palabra palabra : palabras) {
            // Si la palabra empieza por el prefijo entra
            if (palabra.getNombre().startsWith(prefijo)) {
                listaPrefijosOrdenadas.add(palabra);
                encontrada = true;
            }
        }
        if (!encontrada) {
            throw new DireccionExcepcion("No se han encontrado palabras con el prefijo");
        }
        listaPrefijosOrdenadas.sort(null);
        return listaPrefijosOrdenadas;
    }
}