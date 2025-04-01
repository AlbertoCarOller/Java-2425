package Extra.Ejercicio20;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class BibliotecaDeCine {

    // Creamos los atributos
    private Map<String, Set<Frase>> frases;

    // Creamos el cosntructor
    public BibliotecaDeCine(Map<String, Set<Frase>> frases) {
        this.frases = new HashMap<>();
    }

    // Hacemos un toString
    @Override
    public String toString() {
        if (this.frases.isEmpty()) {
            return "Frases: No hay categorías registradas";

        } else {
            return String.format("Frases: %s", this.frases);
        }
    }

    // Hacemos un método para añadir una categoría
    public void addCategoria(String categoria) throws FraseException {
        if (frases.keySet().stream().filter(categoria::equalsIgnoreCase)
                .findFirst().orElseThrow(null) != null) {
            throw new FraseException("La categoría ya existe");
        }
        frases.put(categoria, new HashSet<>());
    }

    // Hacemos un método para añadir una frase a una categoría
    public void addFrase(String categoria, Frase frase) throws FraseException {
        if (!frases.get(frases.keySet().stream().filter(categoria::equalsIgnoreCase).findFirst()
                        .orElseThrow(() -> new FraseException("La categoría no está registrada")))
                .add(frase)) {
            throw new FraseException("La frase ya está registrada en esta categoría");
        }
    }

    // TODO: hacer el resto de métodos y el main
}