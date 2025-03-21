import utils.MiEntradaSalida;

import java.util.Arrays;

public class LibroApp {

    static int numSocios = 0;
    static int numLibros = 0;

    public static void main(String[] args) {

        // Creamos los usuarios
        Usuario us1 = new Usuario("Alberto", "Carmona", "29623617Z");
        Usuario us2 = new Usuario("Sara", "García", "29123587Y");

        int op;

        do {
            System.out.println("1. Convertir a socio");
            System.out.println("2. Añadir libro a la biblioteca");
            System.out.println("3. Coger prestado un libro");
            System.out.println("4. Devolver un libro");
            System.out.println("5. Número de socios");
            System.out.println("6. Número de libros en la biblioteca");
            System.out.println("7. Datos de los libros");
            System.out.println("8. Adiós");

            op = MiEntradaSalida.solicitarEnteroPositivo("Elija una opción");

            switch (op) {

                case 1:
                    int op2 = MiEntradaSalida.solicitarEnteroPositivo("Pulsa 1 si eres " + us1.getNombre() +
                            " 2, si eres " + us2.getNombre());

                    switch (op2) {

                        case 1:
                            try {
                                us1.convertirASocio(us2);
                                numSocios++;

                            } catch (UsuarioException e) {
                                System.out.println(e.getMessage());
                                ;
                            }
                            break;

                        case 2:
                            try {
                                us2.convertirASocio(us1);
                                numSocios++;

                            } catch (UsuarioException e) {
                                System.out.println(e.getMessage());
                            }
                            break;

                        default:
                            System.out.println("No has seleccionado ninguna opción");
                            break;
                    }
                    break;

                case 2:
                    int op3 = MiEntradaSalida.solicitarEnteroPositivo("Pulsa 1 si eres " + us1.getNombre() +
                            " 2, si eres " + us2.getNombre());

                    switch (op3) {

                        case 1:
                            try {
                                String tituloLibro = MiEntradaSalida.solicitarCadena("Introduce el título");
                                String snopsisLibro = MiEntradaSalida.solicitarCadena("Introduce la snopsis");
                                String autorLibro = MiEntradaSalida.solicitarCadena("Introduce el autor");
                                Libro libro = new Libro(tituloLibro, snopsisLibro, autorLibro);
                                us1.introducirLibro(libro);
                                numLibros++;

                            } catch (UsuarioException e) {
                                System.out.println(e.getMessage());
                            }
                            break;

                        case 2:
                            try {
                                String tituloLibro = MiEntradaSalida.solicitarCadena("Introduce el título");
                                String snopsisLibro = MiEntradaSalida.solicitarCadena("Introduce la snopsis");
                                String autorLibro = MiEntradaSalida.solicitarCadena("Introduce el autor");
                                Libro libro = new Libro(tituloLibro, snopsisLibro, autorLibro);
                                us2.introducirLibro(libro);
                                numLibros++;

                            } catch (UsuarioException e) {
                                System.out.println(e.getMessage());
                            }
                            break;

                        default:
                            System.out.println("No has seleccionado ninguna opción");
                            break;
                    }
                    break;

                case 3:
                    int op4 = MiEntradaSalida.solicitarEnteroPositivo("Pulsa 1 si eres " + us1.getNombre() +
                            " 2, si eres " + us2.getNombre());

                    switch (op4) {

                        case 1:
                            try {
                                String tituloLibro = MiEntradaSalida.solicitarCadena("Introduce el título del" +
                                        " libro que buscas");
                                String snopsisLibro = MiEntradaSalida.solicitarCadena("Introduce la snopsis" +
                                        " del libro que buscas");
                                String autorLibro = MiEntradaSalida.solicitarCadena("Introduce el autor del" +
                                        " libro que buscas");
                                Libro libro = new Libro(tituloLibro, snopsisLibro, autorLibro);
                                us1.prestamoLibro(libro);

                            } catch (UsuarioException e) {
                                System.out.println(e.getMessage());
                            }
                            break;

                        case 2:
                            try {
                                String tituloLibro = MiEntradaSalida.solicitarCadena("Introduce el título del" +
                                        " libro que buscas");
                                String snopsisLibro = MiEntradaSalida.solicitarCadena("Introduce la snopsis" +
                                        " del libro que buscas");
                                String autorLibro = MiEntradaSalida.solicitarCadena("Introduce el autor del" +
                                        " libro que buscas");
                                Libro libro = new Libro(tituloLibro, snopsisLibro, autorLibro);
                                us2.prestamoLibro(libro);

                            } catch (UsuarioException e) {
                                System.out.println(e.getMessage());
                            }
                            break;

                        default:
                            System.out.println("No has seleccionado ninguna opción");
                            break;
                    }
                    break;

                case 4:
                    int op5 = MiEntradaSalida.solicitarEnteroPositivo("Pulsa 1 si eres " + us1.getNombre() +
                            " 2, si eres " + us2.getNombre());

                    switch (op5) {

                        case 1:
                            try {
                                String tituloLibro = MiEntradaSalida.solicitarCadena("Introduce el título del" +
                                        " libro que quieres devolver");
                                String snopsisLibro = MiEntradaSalida.solicitarCadena("Introduce la snopsis" +
                                        " del libro que quieres devolver");
                                String autorLibro = MiEntradaSalida.solicitarCadena("Introduce el autor del" +
                                        " libro que quieres devolver");
                                Libro libro = new Libro(tituloLibro, snopsisLibro, autorLibro);
                                us1.devolucionLibro(libro);

                            } catch (UsuarioException e) {
                                System.out.println(e.getMessage());
                            }
                            break;

                        case 2:
                            try {
                                String tituloLibro = MiEntradaSalida.solicitarCadena("Introduce el título del" +
                                        " libro que quieres devolver");
                                String snopsisLibro = MiEntradaSalida.solicitarCadena("Introduce la snopsis" +
                                        " del libro que quieres devolver");
                                String autorLibro = MiEntradaSalida.solicitarCadena("Introduce el autor del" +
                                        " libro que quieres devolver");
                                Libro libro = new Libro(tituloLibro, snopsisLibro, autorLibro);
                                us2.devolucionLibro(libro);

                            } catch (UsuarioException e) {
                                System.out.println(e.getMessage());
                            }
                            break;

                        default:
                            System.out.println("No has seleccionado ninguna opción");
                            break;
                    }
                    break;

                case 5:
                    System.out.println("El número de socios es de " + numSocios);
                    break;

                case 6:
                    System.out.println("El número de libros en la biblioteca es de " + numLibros);
                    break;

                case 7:
                    us1.imprimirBiblioteca();
                    break;

                case 8:
                    System.out.println("Adiós");
                    break;

                default:
                    System.out.println("No has seleccionado ninguna opción");
                    break;
            }
        } while (op != 8);
    }
}