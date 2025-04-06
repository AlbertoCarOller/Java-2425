package Extra.ExamenCollectionsV3;

import java.util.List;

public class BibliotecaV2App {
    public static void main(String[] args) {
        try {
            // Crear una nueva biblioteca
            BibliotecaV2 biblioteca = new BibliotecaV2();

            // Agregar géneros aceptados
            biblioteca.agregarGenero("Ficción");
            biblioteca.agregarGenero("No Ficción");
            biblioteca.agregarGenero("Ciencia");

            // Crear algunos libros
            LibroV2 libro1 = new LibroV2("1234567890", "El Gran Gatsby", "Ficción", 1925, 180);
            LibroV2 libro2 = new LibroV2("0987654321", "Sapiens", "No Ficción", 2011, 400);
            LibroV2 libro3 = new LibroV2("1122334455", "Breves respuestas a las grandes preguntas", "Ciencia", 2018, 256);

            // Agregar libros al catálogo
            biblioteca.agregarLibroAColeccion(libro1);
            biblioteca.agregarLibroAColeccion(libro2);
            biblioteca.agregarLibroAColeccion(libro3);

            // Crear un socio
            SocioV2 socio1 = new SocioV2("Juan Pérez");
            biblioteca.anadirSocio(socio1);
            biblioteca.prestarLibro(libro1, socio1); // Prestar un libro

            // Obtener y mostrar los géneros más prestados
            List<String> generosMasPrestados = biblioteca.obtenerGenerosMasPrestados();
            System.out.println("Géneros más prestados: " + generosMasPrestados);

            // Obtener el género más famoso
            List<String> generoMasFamoso = biblioteca.obtenerGenerosMasPrestadosV2();
            System.out.println("Género más famoso: " + generoMasFamoso);

            // Eliminar libros por género
            biblioteca.eliminarLibrosPorGenero("Ficción");
            System.out.println("Libros después de eliminar los de género 'Ficción': " + biblioteca.getTodosLosLibrosOrdenadosPorPaginas());

        } catch (BibliotecaException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}