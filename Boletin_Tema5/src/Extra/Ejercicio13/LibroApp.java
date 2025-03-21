package Extra.Ejercicio13;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class LibroApp {
    static List<Autor> autores = new ArrayList<>();

    public static void main(String[] args) {
    }

    // Hacemos un método para añadir un libro a un autor, si el autor no existe, se creará uno nuevo
    public static void anadirLibro(Autor autor, Libro libro) throws LibroException {
        if (autores.stream().noneMatch(autor::equals)) {
            autor.getLibrosEnviados().add(libro);
            autores.add(autor);

        } else {
            int indiceAutor = autores.indexOf(autor);
            if (autores.get(indiceAutor).getLibrosEnviados().contains(libro)) {
                throw new LibroException("El libro ya está registrado");
            }
            autores.get(indiceAutor).getLibrosEnviados().add(libro);
        }
    }

    /* Hacemos un método para obtener todos los libros filtrados por género y ordenado alfabéticamente por el título y
     si son iguales por el número de páginas */
    public static String librosPorGenero(String genero) throws LibroException {
        return Optional.of(autores.stream().flatMap(a -> a.getLibrosEnviados().stream())
                        .filter(l -> l.getGenero().equalsIgnoreCase(genero.trim()))
                        .distinct().sorted((l1, l2) -> {
                            int i = l1.getTitulo().compareTo(l2.getTitulo());
                            if (i == 0) {
                                return Integer.compare(l1.getNumPaginas(), l2.getNumPaginas());
                            }
                            return i;
                        }).map(Libro::getTitulo).collect(Collectors.joining(", ")))
                .filter(s -> !s.isEmpty()).orElseThrow(() -> new LibroException("No hay libros con ese género"));
    }

    /* Hacemos un método que devuelva los autores que tengan un libro cuya cantidad de páginas sea mayor
     al número pasado por parámetros */
    public static String autorConMayorLibro(int num) throws LibroException {
        StringBuilder sb = new StringBuilder();
        autores.stream().filter(a -> a.getLibrosEnviados().stream()
                        .anyMatch(l -> l.getNumPaginas() > num))
                .forEach(l -> sb.append(l.getNombre()));
        return sb.toString();
    }
}