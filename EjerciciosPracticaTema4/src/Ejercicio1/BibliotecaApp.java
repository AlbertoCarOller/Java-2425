package Ejercicio1;

import utils.MiEntradaSalida;

import java.net.MalformedURLException;

public class BibliotecaApp {
    public static void main(String[] args) {

        // Creamos la biblioteca
        Biblioteca biblioteca = new Biblioteca();
        int op = 0;
        do {
            try {
                System.out.println("1. Añadir documento");
                System.out.println("2. Mostrar los documentos");
                System.out.println("3. Consultar lo más populares");
                System.out.println("4. Salir");

                op = MiEntradaSalida.solicitarEnteroPositivo("Elija una opción");

                switch (op) {
                    case 1:
                        System.out.println("1. Crear un libro");
                        System.out.println("2. Crear un artículo ciétifico");

                        int op2 = MiEntradaSalida.solicitarEntero("Elija una opción");

                        switch (op2) {
                            case 1:
                                String tituloLibro = MiEntradaSalida.solicitarCadena("Introduce el título del libro");
                                String autorLibro = MiEntradaSalida.solicitarCadena("Introduce el autor del libro");
                                int numPaginasLibro = MiEntradaSalida.solicitarEnteroPositivo("Introduce el número de paginas");
                                String editorialLibro = MiEntradaSalida.solicitarCadena("Introduce la editorial");
                                int descargasLibro = MiEntradaSalida.solicitarEnteroPositivo("Introduce el número de descargas");
                                Libro libro = new Libro(tituloLibro, autorLibro, numPaginasLibro, editorialLibro, descargasLibro);
                                biblioteca.anadirDocumento(libro);
                                break;

                            case 2:
                                String tituloArticulo = MiEntradaSalida.solicitarCadena("Introduce el título del artículo");
                                String autorArticulo = MiEntradaSalida.solicitarCadena("Introduce el autor del artículo");
                                int numCitasArticulo = MiEntradaSalida.solicitarEnteroPositivo("Introduce el número de citas");
                                String revista = MiEntradaSalida.solicitarCadena("Introduce la revista");
                                String url = MiEntradaSalida.solicitarCadena("Introduce el url");
                                int visualizacionesArticulo = MiEntradaSalida.solicitarEnteroPositivo("Introduce las visualizaciones");
                                ArticuloCientifico articuloCientifico = new ArticuloCientifico(tituloArticulo, autorArticulo,
                                        numCitasArticulo, revista, visualizacionesArticulo, url);
                                biblioteca.anadirDocumento(articuloCientifico);
                                break;
                        }
                        break;

                    case 2:
                        System.out.println(biblioteca);
                        break;

                    case 3:
                        String tituloDescargable = biblioteca.getDocumentos()[biblioteca.descargablePopular()].getTitulo();
                        String tituloLeible = biblioteca.getDocumentos()[biblioteca.leiblePopular()].getTitulo();
                        System.out.println("El descargable más popular es " + tituloDescargable + " y el leible más" +
                                " popular es " + tituloLeible);
                        break;

                    case 4:
                        System.out.println("Hasta pronto");
                        break;
                }
                // "|" significa que un catch puede capturar varias excepciones
            } catch (MalformedURLException | DocumentoExcepcion e) {
                System.out.println(e.getMessage());

            }
        } while (op != 4);
    }
}