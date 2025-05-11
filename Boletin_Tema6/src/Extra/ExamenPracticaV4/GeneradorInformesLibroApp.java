package Extra.ExamenPracticaV4;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GeneradorInformesLibroApp {
    public static void main(String[] args) {
        try {
            asignarID();
            agregarLibro("La pasión de Bermucristo", "Bermudín",
                    "1999", "Drama",
                    "Bermucristo salvó la vida de todos los programadores del planeta");
            List<Libro> libros = transformarObjetosLibro();
            List<Path> informes = crearInformes(libros);
            mostrarInformaion(libros, informes);

        } catch (GeneradorInformesLibroException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Este método va a añadir un libro con sus atributos al XML
     *
     * @param titulo   título del nuevo libro
     * @param autor    autor del nuevo libro
     * @param anio     año del nuevo libro
     * @param genero   género del nuevo libro
     * @param sinopsis sinopsis del nuevo libro
     * @throws GeneradorInformesLibroException
     * @throws TransformerException
     */
    public static void agregarLibro(String titulo, String autor, String anio, String genero, String sinopsis)
            throws GeneradorInformesLibroException {
        try {
            Path archivoXML = Path.of("Boletin_Tema6/src/Extra/ExamenPracticaV4/biblioteca_digital.xml");
            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(archivoXML.toFile());
            // Creamos el nodo libro
            Element libro = doc.createElement("libro");
            // Creamos los nodos hijos con sus comprobaciones
            if (!titulo.matches("[\\p{L}\\p{N}\\s]+")) {
                return;
            }
            Element tituloE = doc.createElement("titulo");
            tituloE.setTextContent(titulo);
            if (!autor.matches("(\\p{L}+\\s?)+")) {
                return;
            }
            Element autorE = doc.createElement("autor");
            autorE.setTextContent(autor);
            if (!anio.matches("(1[89]\\d{2}|20([01]\\d)|2[0-5])")) {
                return;
            }
            Element anioE = doc.createElement("anio");
            anioE.setTextContent(anio);
            if (!genero.matches("(\\p{L}+\\s?)+")) {
                return;
            }
            Element generoE = doc.createElement("genero");
            generoE.setTextContent(genero);
            Element sinopsisE = doc.createElement("sinopsis");
            sinopsisE.setTextContent(sinopsis);
            // Llamamos al método auxiliar que va a añadir un id al libro
            auxiliarCargarID(doc.getDocumentElement(), libro);
            // Llamamos al método auxiliar para añadir
            auxiliarAgregarNodos(doc.getDocumentElement(), libro, tituloE, autorE, anioE, generoE, sinopsisE);
            // Guardamos los cambios
            guardarCambiosIndent(doc, archivoXML);

        } catch (InvalidPathException | ParserConfigurationException | SAXException | IOException e) {
            throw new GeneradorInformesLibroException(e.getMessage());
        }
    }

    /**
     * Este método va a crear un 'id' para el último libro, basándose
     * en el último libro existente del XML
     *
     * @param root  el nodo padre del Document
     * @param libro el libro al que se le va a añadir el 'id'
     */
    public static void auxiliarCargarID(Element root, Element libro) {
        String valorIdS = "BK";
        int ultimoLibro = root.getElementsByTagName("libro").getLength() - 1;
        String ultimoID = ((Element) root.getElementsByTagName("libro").item(ultimoLibro)).getAttribute("id");
        int valorIdI = Integer.parseInt(ultimoID.substring(2)) + 1;
        libro.setAttribute("id", valorIdS.concat(String.valueOf(valorIdI)));
    }

    /**
     * Método auxiliar del anterior que va a añadir el nodo libro al nodo
     * padre y los nodos hijos del libro al nodo libro
     *
     * @param root      el nodo padre
     * @param libro     el elemento libro (padre de sus atributos)
     * @param tituloE   título del libro
     * @param autorE    autor del libro
     * @param anioE     año del libro
     * @param generoE   género del libro
     * @param sinopsisE sinopsis del libro
     */
    public static void auxiliarAgregarNodos(Element root, Element libro, Element tituloE, Element autorE, Element anioE,
                                           Element generoE, Element sinopsisE) {
        // Añadimos el nodo libro al nodo padre
        root.appendChild(libro);
        // Añadimos los nodos hijos al nodo padre (los atributos de libro)
        libro.appendChild(tituloE);
        libro.appendChild(autorE);
        libro.appendChild(anioE);
        libro.appendChild(generoE);
        libro.appendChild(sinopsisE);
    }

    /**
     * Este método guarda los cambios realizados en un archivo XML y lo
     * indenta
     *
     * @param doc        el Document
     * @param archivoXML el archivo XML destino
     * @throws GeneradorInformesLibroException
     */
    public static void guardarCambiosIndent(Document doc, Path archivoXML) throws GeneradorInformesLibroException {
        try {
            // Guardamos los cambios
            DOMSource domSource = new DOMSource(doc);
            StreamResult streamResult = new StreamResult(archivoXML.toFile());
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
            transformer.transform(domSource, streamResult);

        } catch (InvalidPathException | TransformerException e) {
            throw new GeneradorInformesLibroException(e.getMessage());
        }
    }

    /**
     * Este método va a crear un atributo id a los libros,
     * le asignamos un valor mediante una parte String que
     * nunca cambiará y otra parte entera que irá aumentando 1
     * por cada libro que vaya repasando
     *
     * @throws GeneradorInformesLibroException
     */
    public static void asignarID() throws GeneradorInformesLibroException {
        try {
            // Path donde se encuentra el archivo XML
            Path archivoXML = Path.of("Boletin_Tema6/src/Extra/ExamenPracticaV4/biblioteca_digital.xml");
            // Cargamos la el archivo en un Document, así podemos manipular los nodos
            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(archivoXML.toFile());
            NodeList libros = doc.getDocumentElement().getElementsByTagName("libro");
            String valorIdS = "BK";
            int valorIdI = 100;
            // Repasamos todos los libros
            for (int i = 0; i < libros.getLength(); i++) {
                Element libro = (Element) libros.item(i);
                // Añadimos el id como atributo de cada libro aumentando su parte valor int
                libro.setAttribute("id", valorIdS.concat(String.valueOf(++valorIdI)));
            }
            // Guardamos los cambios
            DOMSource domSource = new DOMSource(doc);
            StreamResult streamResult = new StreamResult(archivoXML.toFile());
            TransformerFactory.newInstance().newTransformer().transform(domSource, streamResult);

        } catch (InvalidPathException | ParserConfigurationException | SAXException | IOException |
                 TransformerException e) {
            throw new GeneradorInformesLibroException(e.getMessage());
        }
    }

    /**
     * Este método va a recorrer todos los nodos <libro></libro> del
     * XML y mediante un método auxiliar creamos objetos de tipo
     * Libro y lo guardamos en una lista
     *
     * @return una lista de los libros creados
     * @throws GeneradorInformesLibroException
     */
    public static List<Libro> transformarObjetosLibro() throws GeneradorInformesLibroException {
        try {
            Path archivoXML = Path.of("Boletin_Tema6/src/Extra/ExamenPracticaV4/biblioteca_digital.xml");
            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(archivoXML.toFile());
            NodeList libros = doc.getDocumentElement().getElementsByTagName("libro");
            List<Libro> librosCreados = new ArrayList<>();
            // Repasamos los nodos libros
            for (int i = 0; i < libros.getLength(); i++) {
                Element libro = (Element) libros.item(i);
                // Llamámos al método auxiliar y guardamos el Libro
                Libro libroCreado = auxiliarTransformarObjetosLibro(libro);
                // Si no es null entra
                if (libroCreado != null) {
                    // Se añade el libro a una lista
                    librosCreados.add(libroCreado);
                }
            }
            return librosCreados;

        } catch (InvalidPathException | ParserConfigurationException | SAXException | IOException e) {
            throw new GeneradorInformesLibroException(e.getMessage());
        }
    }

    /**
     * Este método es un auxiliar del método anterior, este crea el Libro
     * o devuelve null dependiendo de la validéz de sus atributos
     *
     * @param libro el Element libro que se está repasando
     * @return el objeto de tipo Libro ya creado
     */
    public static Libro auxiliarTransformarObjetosLibro(Element libro) {
        /* Creamos los atributos extrayéndolos del nodo padre, en caso de que una parte obligatoria falle,
         * no se creará el libro */
        Node titulo = libro.getElementsByTagName("titulo").item(0);
        if (titulo == null) {
            System.out.println("El libro " + libro.getAttribute("id") + " no tiene título");
            return null;
        }
        Node autor = libro.getElementsByTagName("autor").item(0);
        if (autor == null) {
            System.out.println("El libro " + libro.getAttribute("id") + " no tiene autor");
            return null;
        }
        Node anio = libro.getElementsByTagName("anio").item(0);
        if (anio == null) {
            System.out.println("El libro " + libro.getAttribute("id") + " no tiene año");
            return null;
        }
        Node genero = libro.getElementsByTagName("genero").item(0);
        if (genero == null) {
            System.out.println("El libro " + libro.getAttribute("id") + " no tiene género");
            return null;
        }
        String sinopsis = "";
        Node sinopsisN = libro.getElementsByTagName("sinopsis").item(0);
        if (sinopsisN != null) {
            sinopsis = sinopsisN.getTextContent();
        }
        return new Libro(libro.getAttribute("id"), titulo.getTextContent(), autor.getTextContent(),
                Integer.parseInt(anio.getTextContent()), genero.getTextContent(), sinopsis);
    }

    /**
     * Este método va a devolver el ISBN del libro y en caso de que
     * no tuviera devuelve un mensaje de que no tiene ISBN
     *
     * @param libro el libro del que se va a extraer el ISBN
     * @return el ISBN o un mensaje diciendo que no tiene
     * @throws GeneradorInformesLibroException
     */
    public static String extraerISBN(Libro libro) {
        Pattern pattern = Pattern.compile("\\b(ISBN(:)?\\s(\\d+-?)+)\\b");
        Matcher matcher = pattern.matcher(libro.getSinopsis());
        return (matcher.find() ? matcher.group(1) : "No tiene ISBN");
    }

    /**
     * Este método va a crear informes de cada libro guardándolos en
     * ficheros dentro de una carpeta de informes
     *
     * @param libros la lista de libros creados
     * @return la lista de Path de los ficheros creados
     * @throws GeneradorInformesLibroException
     */
    public static List<Path> crearInformes(List<Libro> libros) throws GeneradorInformesLibroException {
        try {
            Path directorioInformes = Path.of("Boletin_Tema6/src/Extra/ExamenPracticaV4/Informes");
            if (!Files.exists(directorioInformes)) {
                Files.createDirectory(directorioInformes);
            }
            List<Path> informes = new ArrayList<>();
            libros.forEach(l -> {
                Path informeLibro = Path.of("Boletin_Tema6/src/Extra/ExamenPracticaV4/Informes")
                        .resolve(l.getTitulo().toLowerCase().replaceAll("[\\s\\p{S}]", "_")
                                .concat("_").concat(l.getId()).concat(".txt"));
                try (PrintWriter pw = new PrintWriter(new FileWriter(informeLibro.toFile()))) {
                    auxiliarEscribirContenido(pw, l);
                    informes.add(informeLibro);

                } catch (IOException | InvalidPathException ignored) {
                }
            });
            return informes;

        } catch (InvalidPathException | IOException e) {
            throw new GeneradorInformesLibroException(e.getMessage());
        }
    }

    /**
     * Este método es un auxiliar de el de arriba, va a escribir
     * el contenido que debe de llevar el fichero
     *
     * @param pw el PrintWriter
     * @param l  el libro que se está repasando
     */
    public static void auxiliarEscribirContenido(PrintWriter pw, Libro l) {
        pw.println("=========================");
        pw.println("informe del libro digital".toUpperCase());
        pw.println("=========================");
        pw.println("ID: " + l.getId());
        pw.println("Título: " + l.getTitulo());
        pw.println("Autor: " + l.getAutor());
        pw.println("Año de publicación: " + l.getAnio());
        pw.println("Género: " + l.getGenero());
        pw.println("ISBN: " + (!extraerISBN(l).equals("No tiene ISBN")
                ? extraerISBN(l).substring(4).trim() : extraerISBN(l)));
        pw.println("--------------------------");
        pw.println("Sinopsis: " + (!l.getSinopsis().isEmpty() ? l.getSinopsis() : "No tiene sinopsis"));
        pw.println("==========================");
    }

    /**
     * Este método va a mostrar información básica adicional
     * @param libros la lista de libros válidos
     * @param informes la lista de informes creados que a su misma vez son ficheros
     */
    public static void mostrarInformaion(List<Libro> libros, List<Path> informes) {
        System.out.println("Total de libros leídos: " + libros.size());
        System.out.println("Total de informes: " + informes.size());
        System.out.println("Total de ficheros creados: " + informes.size());
    }
}