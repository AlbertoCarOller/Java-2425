package Extra.Ejercicio21;

import java.util.*;
import java.util.stream.Collectors;

public class PlataformaStreaming {

    // Creamos los atributos
    private String nombre;
    private Set<Serie> series;
    private Set<Usuario> usuarios;

    // Creamos el constructor
    public PlataformaStreaming(String nombre) {
        this.nombre = nombre;
        this.series = new LinkedHashSet<>();
        this.usuarios = new HashSet<>();
    }

    // Hacemos el get
    public String getNombre() {
        return nombre;
    }

    // Hacemos un equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlataformaStreaming that = (PlataformaStreaming) o;
        return Objects.equals(nombre, that.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(nombre);
    }

    // Hacemos un toString
    @Override
    public String toString() {
        return String.format("Nombre: %s, Series: %s", this.nombre, this.series);
    }

    // Hacemos un método para registrar una serie
    public void registrarSerie(Serie serie) throws SerieException {
        if (!series.add(serie)) {
            throw new SerieException("La serie ya existe");
        }
    }

    // Hacemos un método para registrar un usuario
    public void registrarUsuario(Usuario usuario) throws SerieException {
        if (!usuarios.add(usuario)) {
            throw new SerieException("El usuario ya existe");
        }
    }

    // Hacemos un método para añadir un usuario a una serie
    public void anadirUsuarioSerie(Serie serie, Usuario usuario) throws SerieException {
        if (!(series.contains(serie) && usuarios.contains(usuario))) {
            throw new SerieException("No existe la serie o el usuario dentro de la plataforma");
        }
        serie.anadirUsuario(usuario);
    }

    // Hacemos un método para eliminar a un suscriptor de una serie
    public void eliminarUsarioSerie(Serie serie, Usuario usuario) throws SerieException {
        if (!series.contains(serie) || !usuarios.contains(usuario)) {
            throw new SerieException("La serie o el usuario no está en la plataforma");
        }
        serie.eliminarUsuario(usuario);
    }

    // Hacemos un método que va a devolver una lista de usuarios que siguen una serie ordenada alfabéticamente
    public List<Usuario> usuariosOrdenados(Serie serie) throws SerieException {
        /* return Optional.of(series.stream().filter(serie::equals).flatMap(s -> s.getSuscriptores().stream())
                        .sorted(Comparator.comparing(Usuario::getNombre)).toList())
                .filter(l -> !l.isEmpty()).orElseThrow(() -> new SerieException("No existe la serie o no tiene suscriptores")); */
        if (!series.contains(serie)) {
            throw new SerieException("La serie no existe en la plataforma");
        }
        return serie.getSuscriptores().stream().sorted(Comparator.comparing(Usuario::getNombre)).toList();
    }

    // Hacemos un método que va a devolver una lista de series que sigue un usuario ordenado por cantidad de temporadas
    public List<Serie> seriesPorUsuario(Usuario usuario) throws SerieException {
        /* return Optional.of(series.stream().filter(s -> s.getSuscriptores().stream().anyMatch(usuario::equals))
                        .sorted((s1, s2) -> Integer.compare(s2.getCantidadTemporadas(), s1.getCantidadTemporadas()))
                        .toList()).filter(l -> !l.isEmpty())
                .orElseThrow(() -> new SerieException("No existe el usuario o no ve ninguna serie")); */
        if (!usuarios.contains(usuario)) {
            throw new SerieException("La serie no existe en la plataforma");
        }
        return series.stream().filter(s -> s.getSuscriptores().stream().anyMatch(usuario::equals))
                // Aquí no es necesario el operador diamante ya que devuelve directamente un int
                .sorted(Comparator.comparingInt(Serie::getCantidadTemporadas).reversed()).toList();
    }

    // Hacemos un método para obtener las series más seguidas ordenado por el número de suscriptores (fácil)
    public List<Serie> seriesMasPopularesV1() throws SerieException {
        // Al utilizar reversed() después de un Comparator hay que especificar el tipo que compara
        /*return Optional.of(series.stream().sorted(Comparator.<Serie>comparingInt(s -> s.getSuscriptores().size())
                        .reversed()).limit(numSeries).toList()).filter(l -> !l.isEmpty())
                .orElseThrow(() -> new SerieException("No hay series"));*/
        return series.stream().filter(s -> s.numSuscriptores() == series.stream().mapToInt(Serie::numSuscriptores)
                .max().orElse(0)).filter(s -> s.numSuscriptores() != 0).toList();
    }

    /* Hacemos un método para obtener las series más seguidas ordenado por el número de suscriptores (No se puede)
    public List<Serie> seriesMasPopularesV2() throws SerieException {
                                -No se puede comparar un entero con una función, aunque esta devuelva el mismo tipo
        series.stream().filter(s -> s.numSuscriptores() == s -> {
            try {
                return series.stream().max(Comparator.comparingInt(Serie::numSuscriptores))
                        .orElseThrow(() -> new SerieException("No hay series")).numSuscriptores();

            } catch (SerieException e) {
                throw new RuntimeException(e);
            }
        })
    } */

    // Hacemos un método para obtener las series más seguidas ordenado por el número de suscriptores (fácil)
    public List<Serie> seriesMasPopularesV2() throws SerieException {
        return Optional.of(series.stream().filter(s -> s.numSuscriptores() == series.stream()
                                .mapToInt(Serie::numSuscriptores).max().orElse(0))
                        .filter(s -> s.numSuscriptores() != 0).toList())
                .orElseThrow(() -> new SerieException("No hay series"));
    }

    // Hacemos un método que va a devolver un mapa con la serie y la lista de suscriptores (Opcional)
    public Map<Serie, Set<Usuario>> mapaSerieYUsusarios() throws SerieException {
        return Optional.of(series.stream().filter(s -> !s.getSuscriptores().isEmpty())
                        /* Al hacer el toMap() el compilador no puede asegurar que el HashSet sea un Set, por
                         lo que hacemos un casting */
                        .collect(Collectors.toMap(s -> s, s -> (Set<Usuario>) new HashSet(s.getSuscriptores()))))
                .filter(m -> !m.isEmpty()).orElseThrow(() -> new SerieException("No hay datos"));
    }

    // Hacemos un método que va a devolver un Set de los usuarios ordenado alfabéticamente por el nombre (Opcional)
    public Set<Usuario> conjuntoUsuariosOrdenados() throws SerieException {
        return Optional.of(usuarios.stream().sorted(Comparator.comparing(Usuario::getNombre))
                        .collect(Collectors.toCollection(LinkedHashSet::new)))
                .filter(s -> !s.isEmpty()).orElseThrow(() -> new SerieException("No hay datos"));
    }

    // Hacemos un método para obtener un mapa del género con la cantidad de suscriptores (auxiliar)
    public Map<String, Integer> mapaGenerosV1() {
        return series.stream().collect(Collectors.groupingBy(Serie::getGenero, Collectors.reducing(0,
                Serie::numSuscriptores, Integer::sum)));
    }

    // Hacemos un método para obtener un mapa del género con la cantidad de suscriptores (auxiliar, V2)
    public Map<String, Integer> mapaGenerosV2() {
        return series.stream().map(s -> Map.entry(s.getGenero(), s.numSuscriptores()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, Integer::sum));
    }

    // Hacemos un método que va a devolver una lista de los géneros más famosos
    public List<String> generosMasFamososV1() throws SerieException {
        return Optional.of(new ArrayList<>(mapaGenerosV1().entrySet().stream().filter(m
                                -> m.getValue() == mapaGenerosV1().entrySet().stream()
                                .mapToInt(Map.Entry::getValue).max().orElse(0))
                        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)).keySet()))
                .filter(l -> !l.isEmpty()).orElseThrow(() -> new SerieException("No hay datos"));
    }

    // Hacemos un método que va a devolver una lista de los géneros más famosos (V2)
    public List<String> generosMasFamososV2() throws SerieException {
        return Optional.of(mapaGenerosV2().keySet().stream().filter(g -> mapaGenerosV2().get(g) == mapaGenerosV2().keySet().stream()
                        .mapToInt(g1 -> mapaGenerosV2().get(g1)).max().orElse(0)).filter(g -> mapaGenerosV2().get(g) != 0)
                .toList()).filter(l -> !l.isEmpty()).orElseThrow(() -> new SerieException("No hay resultados"));
    }

    // Hacemos un método para eliminar una serie
    public void eliminarSerie(Serie serie) throws SerieException {
        if (!series.remove(serie)) {
            throw new SerieException("La serie no está registrada");
        }
    }
}