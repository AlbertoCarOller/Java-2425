package Extra.Ejercicio13;

import java.util.*;
import java.util.stream.Collectors;

public class LibroApp {
    static List<Autor> autores = new ArrayList<>();

    public static void main(String[] args) {
        try {
            // Creamos libros
            Libro libro = new Libro("Yuberkis´s history", "Mobilario", 25);
            Libro libro1 = new Libro("Carles Xavier action man", "Mobilario", 30);
            Libro libro2 = new Libro("Respecio Godefrio´s history", "Acción", 40);
            Libro libro3 = new Libro("Atisbedo´s history", "Fantasia", 38);
            // Creamos los autores
            Autor autor = new Autor("Saragarcon");
            Autor autor1 = new Autor("Lolitogoku");
            // Registramos los autores
            registrarAutor(autor);
            registrarAutor(autor1);
            // Añadimos los libros al autor
            anadirLibro(autor, libro);
            anadirLibro(autor, libro1);
            anadirLibro(autor1, libro2);
            anadirLibro(autor1, libro3);
            // Llamamos a los métodos para mostrar
            System.out.println(librosPorGenero("Mobilario"));
            System.out.println(autorConMayorLibro(38));
            System.out.println(mapaDeAutores());
        } catch (LibroException e) {
            System.out.println(e.getMessage());
        }
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

    // Hacemos un método para registrar un autor
    public static void registrarAutor(Autor autor) throws LibroException {
        if (autores.stream().anyMatch(autor::equals)) {
            throw new LibroException("El autor ya existe");
        }
        autores.add(autor);
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
        if (sb.isEmpty()) {
            throw new LibroException("No se ha encontrado ninguno con más de " + num + " de páginas");
        }
        return sb.toString();
    }

    /* Hacemos un método para obtener un mapa que relacione cada autor con el número total de páginas de todos
     sus libros y ordenarlo alfabéticamente */
    public static Map<String, Integer> mapaDeAutores() throws LibroException {
        Map<String, Integer> map = Optional.of(autores.stream().map(a -> Map.entry(a.getNombre(),
                                a.getLibrosEnviados().stream().mapToInt(Libro::getNumPaginas)
                                        .reduce(Integer::sum).orElse(0)))
                        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)))
                .filter(m -> !m.isEmpty()).orElseThrow(() -> new LibroException("No hay autores"));
        return new TreeMap<>(map);
    }

    // Hacemos un método para eliminar todos los libros de un autor en concreto
    public static void eliminarLibrosAutor(Autor autor, String genero) throws LibroException {
        boolean encontradoGenero = false;
        if (autores.stream().noneMatch(autor::equals)) {
            throw new LibroException("No se encuentra el autor");
        }
        int indiceAutor = autores.indexOf(autor);
        Iterator<Libro> it = autores.get(indiceAutor).getLibrosEnviados().iterator();
        while (it.hasNext()) {
            if (it.next().getGenero().equalsIgnoreCase(genero)) {
                encontradoGenero = true;
                it.remove();
            }
        }
        if (!encontradoGenero) {
            throw new LibroException("No se ha encontrado ningún libro con ese género");
        }
    }
}