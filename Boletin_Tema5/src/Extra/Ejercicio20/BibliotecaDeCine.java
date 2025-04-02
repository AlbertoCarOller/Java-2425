package Extra.Ejercicio20;

import java.time.Duration;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

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

    /**
     * Añadimos una categoría al Mapa, en caso de que se haya añadido correctamente el add me devolverá
     * null, en caso contrario saltará la excepción, esto querrá decir que ya existía esa misma categoría
     *
     * @param categoria
     * @throws FraseException
     */
    public void addCategoria(String categoria) throws FraseException {
        if (frases.putIfAbsent(categoria, new HashSet<>()) != null) {
            throw new FraseException("La categoría ya existe");
        }
    }

    /**
     * Añadimos una frase a varias o una categoría, en caso de que ya exista en algunas categorías no pasa
     * nada, pero si alguna de las categorías pasadas por parámetros no existen saltará una excepción
     *
     * @param frase
     * @param categoria
     * @throws FraseException
     */
    public void addFrase(Frase frase, String... categoria) throws FraseException {
        if (frases.keySet().stream().filter(c -> Arrays.stream(categoria).anyMatch(c::equalsIgnoreCase))
                .filter(c -> {
                    if (frases.get(c).add(frase)) {
                        return true;
                    }
                    return true;
                }).count() != categoria.length) {
            throw new FraseException("Hay categorías que no están registradas");
        }
    }

    /**
     * Creamos una lista con las categorías que tengan alguna frase
     * sin valoración
     *
     * @return categoriasSinValor
     * @throws FraseException
     */
    public List<String> categoriasSinFraseValor() throws FraseException {
        return Optional.of(frases.keySet().stream().filter(c -> frases.get(c)
                                .stream().anyMatch(f -> f.getValoracion() == 0))
                        .toList()).filter(l -> !l.isEmpty())
                .orElseThrow(() -> new FraseException("No se han encontrado frases"));
    }

    /**
     * Hacemos un método que va a devolver una lista de las categorías que contenga
     * la frase pasada por parámetros
     *
     * @param frase
     * @return categorias
     * @throws FraseException
     */
    public List<String> categoriasDeFrase(Frase frase) throws FraseException {
        List<String> categorias = frases.keySet().stream().filter(c -> frases.get(c).contains(frase))
                .toList();
        if (categorias.isEmpty()) {
            throw new FraseException("No se encuentran resultados");

        } else {
            return categorias;
        }
    }

    /**
     * Hacemos un método que va a devolver una lista de las frases ordenadas por valoración
     * de todas las categorías, sin repeticiones
     *
     * @return frasesOrdenadas
     * @throws FraseException
     */
    public List<Frase> frasesOrdenadasPorValoracion() throws FraseException {
        List<Frase> frasesOrdenadas = frases.keySet().stream().flatMap(c -> frases.get(c).stream())
                .distinct().sorted((f1, f2) -> Integer.compare(f2.getValoracion(), f1.getValoracion()))
                .toList();
        if (frasesOrdenadas.isEmpty()) {
            throw new FraseException("No hay frases");

        } else {
            return frasesOrdenadas;
        }
    }

    /**
     * Hacemos un método que va a eliminar la frase pasada por parámetros de todas
     * las categorías en las que esté
     *
     * @param frase
     * @throws FraseException
     */
    public void eliminarFrases(Frase frase) throws FraseException {
        if (frases.keySet().stream().filter(c -> frases.get(c).remove(frase))
                .toList().isEmpty()) {
            throw new FraseException("No se ha encontrado la frase");
        }
    }

    /**
     * Hacemos un método que va a devolver un conjunto de las frases añadidas en este último año
     *
     * @return frasesUltimo
     */
    public Set<Frase> frasesUltimoAno() throws FraseException {
        Set<Frase> frasesUltimo = frases.keySet().stream().flatMap(c -> frases.get(c).stream())
                .distinct().filter(f -> Duration.between(f.getFechaIncorporacion(), LocalDate.now()).toDays() <= 365)
                .collect(Collectors.toSet());
        if (frasesUltimo.isEmpty()) {
            throw new FraseException("No se han encontrado frases de este año");

        } else {
            return frasesUltimo;
        }
    }

    /**
     * Hacemos un método que va a devolver las frases que superen el valor especificado
     * por parámetros
     *
     * @param valor
     * @return frasesValor
     * @throws FraseException
     */
    public List<Frase> frasesConValor(int valor) throws FraseException {
        List<Frase> frasesValor = frases.keySet().stream().flatMap(c -> frases.get(c).stream())
                .distinct().filter(f -> f.getValoracion() > valor).toList();
        if (frasesValor.isEmpty()) {
            throw new FraseException("No se encuentran resultados");

        } else {
            return frasesValor;
        }
    }

    /**
     * Hacemos un método que va a devolver un double, este es la media del valor de las frases
     * de todas las categorías
     *
     * @return media
     * @throws FraseException
     */
    public double valoracionPromediaFrases() throws FraseException {
        return frases.keySet().stream().flatMap(c -> frases.get(c).stream())
                .distinct().mapToInt(Frase::getValoracion).average()
                .orElseThrow(() -> new FraseException("No hay datos"));
    }

    /**
     * Hacemos un método que va a devolver la frase con el valor más alto
     *
     * @return frase con la valoración más alta
     * @throws FraseException
     */
    public Frase mayorValoracionFrase() throws FraseException {
        return frases.keySet().stream().flatMap(c -> frases.get(c).stream())
                .distinct().max(Comparator.comparingInt(Frase::getValoracion))
                .orElseThrow(() -> new FraseException("No se han encontrado frases"));
    }

    /**
     * Hacemos un método que va a devolver un mapa con las categorías y el valor de su frase con menos
     * valor
     *
     * @return mapa
     * @throws FraseException
     */
    public Map<String, Integer> mapaDeMin() throws FraseException {
        return Optional.of(frases.keySet().stream()
                        .map(c -> Map.entry(c, frases.get(c).stream()
                                .mapToInt(Frase::getValoracion).min().orElse(-1)))
                        .filter(m -> m.getValue() != -1).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)))
                .filter(m -> !m.isEmpty())
                .orElseThrow(() -> new FraseException("No hay resultados"));
    }

    /**
     * Hacemos un método que va a devolver un String con el texto de las frases separadas por
     * coma y espacio
     *
     * @return un string con la información
     * @throws FraseException
     */
    public String infoTextos() throws FraseException {
        return Optional.of(frases.keySet().stream().flatMap(c -> frases.get(c).stream())
                        .distinct().map(Frase::getTexto).collect(Collectors.joining(", ")))
                .orElseThrow(() -> new FraseException("No hay información"));
    }

    /**
     * Hacemos un método que va a devolver una lista de frases que sean de la
     * película pasada por parámetros
     *
     * @param pelicula
     * @return una lista de las frases
     * @throws FraseException
     */
    public List<Frase> frasesDePelicula(String pelicula) throws FraseException {
        return Optional.of(frases.keySet().stream().flatMap(c -> frases.get(c).stream())
                        .distinct().filter(f -> f.getPelicula().equalsIgnoreCase(pelicula))
                        .toList()).filter(l -> !l.isEmpty())
                .orElseThrow(() -> new FraseException("No hay frases de " + pelicula));
    }

    /**
     * Hacemos un método que va a devolver una lista de frases ordenada alfabéticamente
     * por película
     *
     * @return lista de frases
     * @throws FraseException
     */
    public List<Frase> frasesOrdenadasPorPeliculaAlfabeticamente() throws FraseException {
        return Optional.of(frases.keySet().stream().flatMap(c -> frases.get(c).stream())
                        .distinct().sorted(Comparator.comparing(Frase::getPelicula))
                        .toList())
                .orElseThrow(() -> new FraseException("No hay frases"));
    }

    /**
     * Hacemos un método que devuelve un mapa con el nombre del actor y una lista de los
     * textos del mismo
     *
     * @return devuelve un mapa
     * @throws FraseException
     */
    public Map<String, List<String>> mapaConActorYTextos() throws FraseException {
        return Optional.of(frases.keySet().stream().flatMap(c -> frases.get(c).stream())
                        .distinct().collect(Collectors
                                .groupingBy(Frase::getActor, Collectors.mapping(Frase::getTexto, Collectors.toList()))))
                .filter(m -> !m.isEmpty()).orElseThrow(() -> new FraseException("No hay frases"));
    }

    public String peliculasRepresentadas() throws FraseException {
        return Optional.of(frases.keySet().stream().flatMap(c -> frases.get(c).stream())
                        .distinct().map(Frase::getPelicula).distinct()
                        .collect(Collectors.joining(", ")))
                .orElseThrow(() -> new FraseException("No hay frases"));
    }
}