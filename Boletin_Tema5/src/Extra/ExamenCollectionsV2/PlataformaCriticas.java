package Extra.ExamenCollectionsV2;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

public class PlataformaCriticas {

    // Creamos los atributos
    private String nombre;
    private Set<Pelicula> peliculas;

    // Creamos el constructor
    public PlataformaCriticas(String nombre) {
        this.nombre = nombre;
        this.peliculas = new HashSet<>();
    }

    // Hacemos el get del nombre
    public String getNombre() {
        return nombre;
    }

    // Hacemos un toString
    @Override
    public String toString() {
        return String.format("Nombre: %s, Peliculas: %s", this.nombre, this.peliculas);
    }

    /**
     * Hacemos un método para añadir una película y en caso de que ya esté registrada
     * lance una excepción
     *
     * @param p
     * @throws PeliculaException
     */
    public void addPelicula(Pelicula p) throws PeliculaException {
        if (!peliculas.add(p)) {
            throw new PeliculaException("La película ya está registrada");
        }
    }

    /**
     * Hacemos un método que va a buscar la película, en caso de que no exista se lanza
     * una excepción, en caso de que exista se añadirá la reseña en caso de que no esté
     * ya registrada
     *
     * @param titulo
     * @param ano
     * @param r
     * @throws PeliculaException
     */
    public void addResena(String titulo, int ano, Resena r) throws PeliculaException {
        Pelicula pelicula = new Pelicula(titulo.trim(), ano, "", "");
        this.peliculas.stream().filter(pelicula::equals).findFirst()
                .orElseThrow(() -> new PeliculaException("La película no existe")).addResena(r);
    }

    /**
     * Hacemos un método que va a buscar las películas que pertenezcan a un
     * género completo, ordenado alfabéticamente
     *
     * @param genero
     * @return lista de películas
     */
    public List<Pelicula> getPeliculasConGeneroV1(String genero) {
        return peliculas.stream().filter(p -> p.getGenero().equalsIgnoreCase(genero.trim()))
                .sorted(Comparator.comparing(Pelicula::getTitulo)).toList();
    }

    /**
     * Hacemos un método que va a buscar las películas que pertenezcan a una serie
     * de géneros
     *
     * @param generos
     * @return lista de películas
     */
    public List<Pelicula> getPeliculaConGeneroV2(String... generos) {
        return peliculas.stream().filter(p -> Arrays.stream(generos).anyMatch(g ->
                        p.getGenero().equalsIgnoreCase(g))).sorted(Comparator.comparing(Pelicula::getTitulo))
                .toList();
    }

    /**
     * Hacemos un método para calcular la media de las reseñas de una
     * película
     *
     * @param titulo
     * @param ano
     * @return media de las valoraciones
     * @throws PeliculaException
     */
    public double mediaValoraciones(String titulo, int ano) throws PeliculaException {
        Pelicula pelicula = new Pelicula(titulo, ano, "", "");
        return peliculas.stream().filter(pelicula::equals).flatMap(p -> p.getResenas().stream())
                .mapToInt(Resena::getValoracion).average().orElse(0);
    }

    /**
     * Hacemos un método que va a buscar los usuarios que tengan una reseña dentro de 1 mes
     *
     * @return conjunto de usuarios
     */
    public Set<String> usuariosConResenasReciente() {
        return peliculas.stream().flatMap(p -> p.getResenas().stream()).distinct()
                .filter(r -> ChronoUnit.MONTHS.between(LocalDate.now(), r.getFechaPublicacion()) <= 1)
                .map(Resena::getUsuario).collect(Collectors.toSet());
    }

    /**
     * Hacemos un método que va a ordenar las películas por la media de su valoración
     *
     * @return lista de películas
     */
    public List<Pelicula> getPeliculasOrdenadasPorValoracionMedia() {
        return peliculas.stream().sorted((p1, p2) -> Double.compare(p2.mediaValoracion(), p1.mediaValoracion()))
                .toList();
    }

    /**
     * Hacemos un método que va a devolver las películas con reseñas negativas
     *
     * @return lista de películas
     */
    public List<Pelicula> peliculasConResenaNegativa() {
        return peliculas.stream().filter(p -> p.getResenas().stream().anyMatch(r -> r.getValoracion() <= 3))
                .toList();
    }

    /**
     * Hacemos un método que va a eliminar las reseñas que contenga al usuario especificado
     *
     * @param usuario
     */
    public void eliminarResenasDeUsuarioV1(String usuario) {
        peliculas.forEach(p -> {
            Iterator<Resena> it = p.getResenas().iterator();
            while (it.hasNext()) {
                Resena resenaIt = it.next();
                if (resenaIt.getUsuario().equalsIgnoreCase(usuario)) {
                    it.remove();
                }
            }
        });
    }

    /**
     * Hacemos un método que va a eliminar las reseñas que contengan al usuario especificado
     *
     * @param usuario
     */
    public void eliminarResenasDeUsuarioV2(String usuario) {
        peliculas.forEach(p -> p.getResenas().removeIf(r -> r.getUsuario().equalsIgnoreCase(usuario)));
    }

    /**
     * Hacemos un método que va a devolver un mapa con los géneros y el número de reseñas
     *
     * @return mapa de géneros
     */
    public Map<String, Integer> mapaGenerosV1() {
        return peliculas.stream().map(p -> Map.entry(p.getGenero(), p.getResenas().size()))
                .collect(Collectors.groupingBy(Map.Entry::getKey,
                        Collectors.reducing(0, Map.Entry::getValue, Integer::sum)));
    }

    /**
     * Hacemos un método que se va a quedar con los géneros que sea mayor al valor mayor de un key
     *
     * @return lista de géneros más famosos
     */
    public List<String> generosFamososV1() {
        return new ArrayList<>(mapaGenerosV1().entrySet().stream().filter(m -> m.getValue().equals(mapaGenerosV1()
                        .values().stream().max(Integer::compare).orElse(0)))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)).keySet());
    }

    /**
     * Hacemos un método que va a hacer un mapa para posteriormente ordenar de mayor a menor
     * las veces que aparece un género (mediante el counting()) y se va a quedar con la key,
     * que es en este caso los géneros y lo pasamos a una lista, manteniendo así el orden
     * @return lista de géneros ordenados de mayor a menor
     */
    public List<String> mapaGenerosV2() {
        return peliculas.stream().collect(Collectors.groupingBy(Pelicula::getGenero, Collectors.counting()))
                .entrySet().stream().sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .map(Map.Entry::getKey).toList();
    }
}