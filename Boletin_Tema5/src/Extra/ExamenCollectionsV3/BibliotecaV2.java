package Extra.ExamenCollectionsV3;


import java.util.*;
import java.util.stream.Collectors;

public class BibliotecaV2 {
    private List<LibroV2> catalogoLibros;
    private List<SocioV2> sociosRegistrados;
    private List<String> generosAceptados;

    public BibliotecaV2() {
        this.catalogoLibros = new LinkedList<>(); // Decidir implementacion
        this.sociosRegistrados = new LinkedList<>(); // Decidir implementacion
        this.generosAceptados = new LinkedList<>(); // Decidir implementacion
    }

    // Hacemos un método para añadir un socio sino existe
    public void anadirSocio(SocioV2 socioV2) throws BibliotecaException {
        if (sociosRegistrados.contains(socioV2)) {
            throw new BibliotecaException("El socio ya existe");
        }
        sociosRegistrados.add(socioV2);
    }

    // Hacemos un método que va a prestar un libro
    public void prestarLibro(LibroV2 libroV2, SocioV2 socioV2) throws BibliotecaException {
        if (!catalogoLibros.contains(libroV2) || !sociosRegistrados.contains(socioV2)) {
            throw new BibliotecaException("No se ha podido prestar el libro");
        }
        socioV2.tomarPrestado(libroV2);
    }

    // Hacemos un método que va a devolver el índice del libro
    public int buscarPosicionLibro(LibroV2 libro) {
        return catalogoLibros.indexOf(libro);
    }

    // Hacemos un método que va a añadir un libro en una posición especificada
    public void agregarLibroEnPosicion(int indice, LibroV2 libro) throws BibliotecaException {
        if (indice < 0 || indice > catalogoLibros.size()) {
            throw new BibliotecaException("El índice no es válido");
        }
        if (catalogoLibros.contains(libro)) {
            throw new BibliotecaException("El libro ya existe");
        }
        catalogoLibros.add(indice, libro);
    }

    // Hacemos un método que va a añadir el libro nuevo donde estaba el viejo y eliminar este último
    public void reemplazarLibro(LibroV2 antiguo, LibroV2 nuevo) throws BibliotecaException {
        if (!catalogoLibros.contains(antiguo)) {
            throw new BibliotecaException("El libro antiguo no existe");
        }
        agregarLibroEnPosicion(catalogoLibros.indexOf(antiguo), nuevo);
        catalogoLibros.remove(antiguo);
    }

    // Obtenemos una lista de los libros desde un índice hasta otro
    public List<LibroV2> obtenerLibrosRecientes(int desdeIndice, int hastaIndice) throws BibliotecaException {
        if (desdeIndice < 0 || desdeIndice >= hastaIndice) {
            throw new BibliotecaException("Índices inválidos");
        }
        if (hastaIndice > catalogoLibros.size()) {
            throw new BibliotecaException("Índices inválidos");
        }
        //return catalogoLibros.subList(desdeIndice, hastaIndice); (Sin flujos)
        return catalogoLibros.stream().skip(desdeIndice).limit(hastaIndice - 1).toList();
    }

    // Eliminamos los libros que sean del género especificado
    public void eliminarLibrosPorGenero(String genero) throws BibliotecaException {
        catalogoLibros.removeAll(catalogoLibros.stream().filter(p -> p.getGenero().equalsIgnoreCase(genero)).toList());
    }

    // Eliminamos los libros que sean del género especificado (otra forma)
    public void eliminarLibrosPorGeneroV2(String genero) throws BibliotecaException {
        catalogoLibros.removeIf(l -> l.getGenero().equalsIgnoreCase(genero));
    }

    // Retenemos de la lista de libros solo los libros que sean del año especificado
    public void retenerLibrosPorAno(int ano) {
        catalogoLibros.retainAll(catalogoLibros.stream().filter(l -> l.getAnnoPublicacion() == ano).toList());
    }

    // Hacemos un método para añadir un género
    public void agregarGenero(String genero) throws BibliotecaException {
        if (generosAceptados.contains(genero)) {
            throw new BibliotecaException("El género ya existe");
        }
        generosAceptados.add(genero);
    }

    // Hacemos un método para añadir un libro en caso de que se pueda
    public void agregarLibroAColeccion(LibroV2 libro) throws BibliotecaException {
        if (catalogoLibros.contains(libro) || !generosAceptados.contains(libro.getGenero())) {
            throw new BibliotecaException("No se puede añadir el libro");
        }
        catalogoLibros.add(libro);
    }

    // Hacemos un método que va a deolver un conjunto de los géneros que contienen algún libro sin páginas
    public Set<String> generosConLibrosSinPaginas() {
        return catalogoLibros.stream().filter(l -> l.getNumPaginas() == 0).map(LibroV2::getGenero)
                .collect(Collectors.toSet());
    }

    // Hacemos un método que va a devolver el género del libro especificado
    public String generosDelLibro(LibroV2 libro) throws BibliotecaException {
        return catalogoLibros.stream().filter(libro::equals).findFirst()
                .orElseThrow(() -> new BibliotecaException("No se encuentra el libro")).getGenero();
    }

    // Devolver lista sin duplicados, ordenada por número de páginas (mayor a menor)
    public List<LibroV2> getTodosLosLibrosOrdenadosPorPaginas() {
        return catalogoLibros.stream().sorted(Comparator.comparingInt(LibroV2::getNumPaginas))
                .toList().reversed();
    }

    // Eliminar el libro del catálogo si existe
    public void eliminarLibroDelCatalogo(LibroV2 libro) {
        catalogoLibros.remove(libro);
    }

    /* Devuelve una lista de libros ordenados por año de publicación (de más reciente a más antiguo),
    que han sido prestados al menos una vez por algún socio. La lista no debe contener duplicados. */
    public List<LibroV2> librosMasPrestadosOrdenadosPorAnno() {
        return sociosRegistrados.stream().flatMap(s -> s.getLibrosPrestados().stream())
                .distinct().sorted(Comparator.comparingInt(LibroV2::getAnnoPublicacion))
                .toList().reversed();
    }

    /* Devuelve una lista con los géneros más populares (es decir, más veces prestados)
    entre todos los libros que han sido prestados por los socios.
    La lista debe estar ordenada de mayor a menor según la cantidad de préstamos por género.
    En caso de empate en la cantidad, los géneros pueden aparecer en cualquier orden relativo. */
    public List<String> obtenerGenerosMasPrestados() {
        return sociosRegistrados.stream().flatMap(s -> s.getLibrosPrestados().stream())
                .distinct().collect(Collectors.groupingBy(LibroV2::getGenero,
                        Collectors.counting())).entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .map(Map.Entry::getKey).toList();
    }

    // Hacemos un método que va a devolver una lista de los géneros más famosos
    public List<String> obtenerGenerosMasPrestadosV2() {
        Map<String, Long> mapaGeneros = sociosRegistrados.stream().flatMap(s -> s.getLibrosPrestados().stream())
                .distinct().collect(Collectors.groupingBy(LibroV2::getGenero,
                        Collectors.counting()));
        return mapaGeneros.entrySet().stream().filter(m -> m.getValue().equals(mapaGeneros.values()
                        .stream().max(Long::compareTo).orElse(0L))).map(Map.Entry::getKey)
                .toList();
    }
}
