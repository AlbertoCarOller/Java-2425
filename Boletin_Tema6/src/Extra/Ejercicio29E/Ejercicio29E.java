package Extra.Ejercicio29E;

import Extra.Ejercicio23E.Ejercicio23Exception;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;

public class Ejercicio29E {
    public static void main(String[] args) {
        try {
            imprimirTitulo();
            System.out.println("Hay " + contarParrafos() + " párrafos");
            imprimirEnlacesYContenido();
            imprimirTitulosDeLibrosDestacados();
            mostrarElPieDePagina();
            mostrarGenerosMenuLateral();
            mostrarTituloYAutorLibro();

        } catch (Ejercicio29EException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Este método va a crear un DocumentBuilder para así facilitar esta parte del código
     * ya que siempre es la misma
     *
     * @return el DocumentBuilder
     * @throws Ejercicio23Exception
     */
    public static DocumentBuilder crearDocumentBuilder() throws Ejercicio29EException {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        try {
            return documentBuilderFactory.newDocumentBuilder();

        } catch (ParserConfigurationException e) {
            throw new Ejercicio29EException(e.getMessage());
        }
    }

    /**
     * Este método imprime el título ubicado en la cabeza del html
     *
     * @throws Ejercicio29EException en caso de que el Path sea inválido
     *                               o al parsear haya algún otro tipo de error
     */
    public static void imprimirTitulo() throws Ejercicio29EException {
        try {
            Path archivoXHTML = Path.of("Boletin_tema6/src/Extra/Ejercicio29E/libros.html");
            Document doc = crearDocumentBuilder().parse(archivoXHTML.toFile());
            System.out.println(((Element) doc.getDocumentElement().getElementsByTagName("head").item(0))
                    .getElementsByTagName("title").item(0).getTextContent());

        } catch (InvalidPathException | IOException | SAXException e) {
            throw new Ejercicio29EException(e.getMessage());
        }
    }

    /**
     * Este método va a contar el número de párrafos del documento completo
     *
     * @return el número total de párrafos
     * @throws Ejercicio29EException en caso de que el Path no sea válido o haya
     *                               algún problema al parsear
     */
    public static int contarParrafos() throws Ejercicio29EException {
        try {
            Path archivoXHTML = Path.of("Boletin_tema6/src/Extra/Ejercicio29E/libros.html");
            Document doc = crearDocumentBuilder().parse(archivoXHTML.toFile());
            /* Recordamos que .getElementsByTagName() obtiene los nodos indiferentemente de su nivel de identación,
             * PERO OBTENDRÁ A PARTIR DEL NIVEL QUE EN EL QUE ESTÁ, ES DECIR, LO QUE ESTÉ DEBAJO DEL PADRE ACTUAL */
            return doc.getDocumentElement().getElementsByTagName("p").getLength();

        } catch (InvalidPathException | IOException | SAXException e) {
            throw new Ejercicio29EException(e.getMessage());
        }
    }

    /**
     * Este método va a imprimir los enlaces y su contenido de texto
     *
     * @throws Ejercicio29EException en caso de Path inválido o problema
     *                               al parsear
     */
    public static void imprimirEnlacesYContenido() throws Ejercicio29EException {
        try {
            Path archivoXHTML = Path.of("Boletin_tema6/src/Extra/Ejercicio29E/libros.html");
            Document doc = crearDocumentBuilder().parse(archivoXHTML.toFile());
            NodeList enlaces = doc.getDocumentElement().getElementsByTagName("a");
            for (int i = 0; i < enlaces.getLength(); i++) {
                Element enlace = (Element) enlaces.item(i);
                System.out.println("Enlace: " + enlace.getAttribute("href") + " Contenido: " + enlace.getTextContent());
            }

        } catch (InvalidPathException | IOException | SAXException e) {
            throw new Ejercicio29EException(e.getMessage());
        }
    }

    /**
     * Este método imprime los títulos de los libros que están en un
     * artículo destacado
     *
     * @throws Ejercicio29EException si el Path es inválido o hay problemas
     *                               al parsear
     */
    public static void imprimirTitulosDeLibrosDestacados() throws Ejercicio29EException {
        try {
            Path archivoXHTML = Path.of("Boletin_tema6/src/Extra/Ejercicio29E/libros.html");
            Document doc = crearDocumentBuilder().parse(archivoXHTML.toFile());
            NodeList articulos = doc.getDocumentElement().getElementsByTagName("article");
            for (int i = 0; i < articulos.getLength(); i++) {
                Element articulo = (Element) articulos.item(i);
                if (articulo.getAttribute("class").equals("destacado")) {
                    System.out.println(articulo.getElementsByTagName("h3").item(0).getTextContent());
                }
            }

        } catch (InvalidPathException | IOException | SAXException e) {
            throw new Ejercicio29EException(e.getMessage());
        }
    }

    /**
     * Este método va a mostrar el pie de página <footer></footer>
     *
     * @throws Ejercicio29EException en caso de que el Path sea inválido
     *                               o que al parsear haya error
     */
    public static void mostrarElPieDePagina() throws Ejercicio29EException {
        try {
            Path archivoXHTML = Path.of("Boletin_Tema6/src/Extra/Ejercicio29E/libros.html");
            Document doc = crearDocumentBuilder().parse(archivoXHTML.toFile());
            System.out.println(doc.getDocumentElement().getElementsByTagName("footer").item(0).getTextContent());

        } catch (InvalidPathException | IOException | SAXException e) {
            throw new Ejercicio29EException(e.getMessage());
        }
    }

    /**
     * Este método va a mostrar los géneros del menú lateral
     *
     * @throws Ejercicio29EException si el Path es inválido
     *                               o hay algún error al parsear
     */
    public static void mostrarGenerosMenuLateral() throws Ejercicio29EException {
        try {
            Path archivoXHTML = Path.of("Boletin_Tema6/src/Extra/Ejercicio29E/libros.html");
            Document doc = crearDocumentBuilder().parse(archivoXHTML.toFile());
            NodeList menus = doc.getElementsByTagName("nav");
            for (int i = 0; i < menus.getLength(); i++) {
                Element menu = (Element) menus.item(i);
                if (menu.getAttribute("id").matches("generos")) {
                    Element anteMenu = ((Element) menu.getElementsByTagName("ul").item(0));
                    NodeList opciones = anteMenu.getChildNodes();
                    for (int j = 0; j < opciones.getLength(); j++) {
                        if (opciones.item(j).getNodeType() == Node.ELEMENT_NODE) {
                            System.out.println(opciones.item(j).getTextContent());
                        }
                    }
                }
            }

        } catch (InvalidPathException | IOException | SAXException e) {
            throw new Ejercicio29EException(e.getMessage());
        }
    }

    /**
     * Este método muestra el título y el autor del libro
     *
     * @throws Ejercicio29EException si el Path es inválido
     *                               o falla al parsear
     */
    public static void mostrarTituloYAutorLibro() throws Ejercicio29EException {
        try {
            Path archivoXHTML = Path.of("Boletin_Tema6/src/Extra/Ejercicio29E/libros.html");
            Document doc = crearDocumentBuilder().parse(archivoXHTML.toFile());
            NodeList secciones = doc.getElementsByTagName("section");
            for (int i = 0; i < secciones.getLength(); i++) {
                Element seccion = (Element) secciones.item(i);
                NodeList hijos = seccion.getChildNodes();
                for (int j = 0; j < hijos.getLength(); j++) {
                    if (hijos.item(j).getNodeType() == Node.ELEMENT_NODE) {
                        System.out.println(hijos.item(j).getTextContent());
                    }
                }
            }

        } catch (InvalidPathException | IOException | SAXException e) {
            throw new Ejercicio29EException(e.getMessage());
        }
    }
}