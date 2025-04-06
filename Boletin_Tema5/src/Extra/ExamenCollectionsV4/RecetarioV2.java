package Extra.ExamenCollectionsV4;


import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

public class RecetarioV2 {
    private Map<String, RecetaV2> recetas; // Clave: nombre + "||" + autor

    public RecetarioV2() {
        this.recetas = new HashMap<>(); // Decidir que implementacion usar
    }
    /*********************************************************************************************/
    /* DIFICULTAD BASICO                                                                         */
    /*********************************************************************************************/

    /**
     * Añade una nueva receta al recetario.
     * Si ya existe una receta con el mismo nombre y autor, lanza RecetarioException.
     */
    public void addReceta(RecetaV2 r) throws RecetarioException {
        if (recetas.putIfAbsent(r.getNombre() + r.getAutor(), r) != null) {
            throw new RecetarioException("La receta ya está añadida");
        }
    }

    /**
     * Añade una valoración a la receta indicada por nombre y autor.
     * Si la receta no existe, lanza RecetarioException.
     */
    public void addValoracion(String nombre, String autor, ValoracionV2 v) throws RecetarioException {
        if (!recetas.containsKey(nombre + autor)) {
            throw new RecetarioException("La receta no existe");
        }
        recetas.get(nombre + autor).anadirValoracion(v);
    }

    /**
     * Devuelve una lista con todas las recetas que pertenecen a una categoría específica.
     * Las recetas deben estar ordenadas alfabéticamente por nombre.
     */
    public List<RecetaV2> getRecetasPorCategoria(String categoria) {
        return recetas.values().stream().filter(r -> r.getCategoria().equalsIgnoreCase(categoria))
                .sorted(Comparator.comparing(RecetaV2::getNombre)).toList();
    }

    /**
     * Calcula y devuelve la media de puntuaciones de una receta.
     * Si no tiene valoraciones, devuelve 0.
     * Si la receta no existe, lanza RecetarioException.
     */
    public double mediaValoraciones(String nombre, String autor) throws RecetarioException {
        if (!recetas.containsKey(nombre + autor)) {
            throw new RecetarioException("No existe la receta");
        }
        return recetas.keySet().stream().filter(k -> k.equalsIgnoreCase(nombre + autor))
                .flatMap(k -> recetas.get(k).getValoraciones().stream()).mapToDouble(ValoracionV2::getPuntuacion)
                .average().orElse(0);
    }


    /*********************************************************************************************/
    /* DIFICULTAD MEDIA                                                                          */
    /*********************************************************************************************/

    /**
     * Devuelve un conjunto con los nombres de los usuarios que han publicado
     * valoraciones en los últimos 7 días.
     */
    public Set<String> usuariosActivosUltimaSemana() {
        return recetas.values().stream().flatMap(r -> r.getValoraciones().stream())
                .filter(v -> Math.abs(ChronoUnit.DAYS.between(v.getFecha(), LocalDate.now())) <= 7)
                .map(ValoracionV2::getUsuario).collect(Collectors.toSet());
    }


    /**
     * Devuelve un conjunto de todas las categorías de recetas disponibles en el recetario.
     */
    public Set<String> getCategoriasDisponibles() {
        return recetas.values().stream().map(RecetaV2::getCategoria).collect(Collectors.toSet());
    }

    /**
     * Devuelve una lista de todas las recetas publicadas por un usuario específico, ordenadas por nombre.
     */
    public List<RecetaV2> getRecetasDeAutor(String autor) {
        return recetas.values().stream().filter(r -> r.getAutor().equalsIgnoreCase(autor))
                .sorted(Comparator.comparing(RecetaV2::getAutor))
                .toList();
    }

    /**
     * Devuelve una lista de las recetas que contienen un ingrediente específico, sin importar la categoría.
     */
    public List<RecetaV2> buscarRecetasPorIngrediente(String ingrediente) {
        return recetas.values().stream().filter(r -> r.getIngredientes().stream()
                .anyMatch(ingrediente::equalsIgnoreCase)).toList();
    }

    /*********************************************************************************************/
    /* DIFICULTAD ALTA                                                                           */
    /*********************************************************************************************/

    /**
     * Devuelve un conjunto de recetas que tienen al menos
     * una valoración con puntuación menor o igual a 2.
     */
    public Set<RecetaV2> recetasConValoracionesBajas() {
        return recetas.values().stream().filter(r -> r.getValoraciones().stream()
                        .anyMatch(v -> v.getPuntuacion() <= 2))
                .collect(Collectors.toSet());
    }

    /**
     * Devuelve una lista de ingredientes ordenados de mayor a menor
     * según el número de recetas en las que aparecen.
     */
    public List<String> ingredientesMasUsados() {
        return recetas.values().stream().flatMap(r -> r.getIngredientes().stream())
                .sorted(Comparator.<String>comparingLong(i -> recetas.values().stream()
                        .flatMap(r1 -> r1.getIngredientes().stream())
                        .filter(i::equalsIgnoreCase).count()).reversed()).toList();
    }

    /**
     * Devuelve una lista de ingredientes ordenados de mayor a menor
     * según el número de recetas en las que aparecen (V2).
     */
    public List<String> ingredientesMasUsadosV2() {
        return recetas.values().stream().flatMap(r -> r.getIngredientes().stream())
                .collect(Collectors.groupingBy(i -> i, Collectors.counting()))
                .entrySet().stream().sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .map(Map.Entry::getKey).toList();
    }


    /**
     * Devuelve una lista de recetas ordenadas de mayor a menor
     * según su puntuación media.
     */
    public List<RecetaV2> getRecetasOrdenadasPorPuntuacionMedia() {
        return recetas.values().stream().sorted(Comparator.<RecetaV2>comparingDouble(r -> r.getValoraciones()
                        .stream().mapToInt(ValoracionV2::getPuntuacion).average().orElse(0)).reversed())
                .toList();
    }
}