package Boletin1.Ejercicio7;

import java.util.*;

public class Recetario {

    // Creamos los atributos
    private Map<String, Receta> recetas;

    // Creamos el constructor
    public Recetario() {
        this.recetas = new HashMap<>();
    }

    // Hacemos los get y set
    public Map<String, Receta> getRecetas() {
        return recetas;
    }

    private void setRecetas(Map<String, Receta> recetas) {
        this.recetas = recetas;
    }

    // Hacemos un método para añadir una receta
    public void anadirReceta(Receta nuevaReceta) throws IngredienteException {
        if (!recetas.containsValue(nuevaReceta)) {
            recetas.put(nuevaReceta.getNombre(), nuevaReceta);

        } else {
            throw new IngredienteException("Ya existe la receta");
        }
    }

    // Hacemos un método que devuelve las recetas ordenadas
    public String listadoRecetasOrdenadasAlfabeticamente() throws IngredienteException {
        if (recetas.isEmpty()) {
            throw new IngredienteException("No hay recetas");
        }
        StringBuilder sb = new StringBuilder();
        SortedMap<String, Receta> copiaReceta = new TreeMap<>(recetas);
        sb.append("Recetas ordenadas alfabéticamente: ").append(copiaReceta.values());
        return sb.toString();
    }

    /* Hacemos un método que devuelve un String con las recetas que contienen un ingrediente ordenado por el tiempo
     de preparación */
    public String listadoRecetasConIngredienteOrdenadasPorTiempoPreparacion(String nombreIngrediente) throws IngredienteException {
        List<Receta> recetasOrdenada = new LinkedList<>();
        Iterator<Receta> it = recetas.values().iterator();
        boolean ingredienteEncontrado = false;
        while (it.hasNext()) {
            Receta receta = it.next();
            Iterator<Ingrediente> ingredienteIt = receta.getIngredientes().iterator();
            while (ingredienteIt.hasNext()) {
                Ingrediente ingrediente = ingredienteIt.next();
                if (ingrediente.getNombre().equalsIgnoreCase(nombreIngrediente)) {
                    recetasOrdenada.add(receta);
                    ingredienteEncontrado = true;
                    break;
                }
            }
        }
        if (!ingredienteEncontrado) {
            throw new IngredienteException("No se ha encontrado el ingrediente");
        }
        recetasOrdenada.sort(null);
        return recetasOrdenada.toString();
    }

    // Hacemos el toString
    @Override
    public String toString() {
        return String.format("Las recetas son: %s ", this.recetas);
    }

    // Hacemos un método para calcular el tiempo medio de preparación de cada receta
    public double calcularMediaPreparacion() throws IngredienteException {
        return recetas.values().stream()
                .mapToInt(Receta::getTiempoPreparacion)
                .average().orElseThrow(() -> new IngredienteException("No se puede calcular la media"));
    }

    // Hacemos un método para calcular el tiempo medio de preparación de cada receta de otra forma
    public double calcularMediaAlternativa() throws IngredienteException {
        return recetas.values().stream().mapToDouble(Receta::getTiempoPreparacion).reduce(Double::sum).getAsDouble() / recetas.size();
    }
}