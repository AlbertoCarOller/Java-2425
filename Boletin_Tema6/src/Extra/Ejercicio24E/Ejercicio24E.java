package Extra.Ejercicio24E;

import Extra.Ejercicio23E.Ejercicio23Exception;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.util.*;

public class Ejercicio24E {
    public static void main(String[] args) {
        try {
            crearPeliculas();
            anadirPeliculaXML(new Pelicula("P888", "Alberto Mobilario", "No Mobilario", 3.4));
            eliminarPeliculasPorGenero();

        } catch (PeliculaException e) {
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
    public static DocumentBuilder crearDocumentBuilder() throws PeliculaException {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        try {
            return documentBuilderFactory.newDocumentBuilder();

        } catch (ParserConfigurationException e) {
            throw new PeliculaException(e.getMessage());
        }
    }

    /**
     * Este método guarda los cambios realizados o la creación del nuevo Document
     * en el archivo especificado, este método es simplemente para optimizar, ya que
     * el proceso siempre es el mismo
     *
     * @param doc          el Document
     * @param archivoFinal el archivo destino (final)
     * @throws Ejercicio23Exception
     */
    public static void guardarCambios(Document doc, Path archivoFinal) throws PeliculaException {
        try {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource domSource = new DOMSource(doc);
            StreamResult streamResult = new StreamResult(archivoFinal.toFile());
            transformer.transform(domSource, streamResult);

        } catch (TransformerException e) {
            throw new PeliculaException(e.getMessage());
        }
    }

    /**
     * Este método lee las películas del archivo XML y también sus
     * atributos para así poder transformarla en objetos de tipo
     * 'Pelicula' y así imprimirlas por pantalla
     *
     * @throws PeliculaException
     */
    public static void crearPeliculas() throws PeliculaException {
        try {
            // Path del archivo de donde vamos a obtener las películas
            Path archivoXML = Path.of("Boletin_Tema6/src/Extra/Ejercicio24E/peliculas.xml");
            // Creamos el Document para poder trabajar con el archivo XML
            Document doc = crearDocumentBuilder().parse(archivoXML.toFile());
            // Obtenemos un NodeList de las películas
            NodeList peliculas = doc.getDocumentElement().getChildNodes();
            // Conjunto que va a almacenar las películas
            Set<Pelicula> peliculasList = new HashSet<>();
            // Recorremos las películas
            for (int i = 0; i < peliculas.getLength(); i++) {
                /* Si es un nodo de tipo elemento entra, esto es más que nada para que se salte
                 * los nodos de tipo texto, que suelen ser saltos de línea */
                if (peliculas.item(i).getNodeType() == Node.ELEMENT_NODE) {
                    // Transformamos la película de tipo elemento para poder trabajar mejor así
                    Element pelicula = (Element) peliculas.item(i);
                    // Creamos por cada película del XML una película de la clase 'Pelicula'
                    peliculasList.add(new Pelicula(pelicula.getAttribute("codigo"),
                            pelicula.getElementsByTagName("titulo").item(0).getTextContent(),
                            pelicula.getElementsByTagName("genero").item(0).getTextContent(),
                            Double.parseDouble(pelicula.getElementsByTagName("duracion").item(0).getTextContent())));
                }
            }
            // Imprimimos las películas creadas
            peliculasList.forEach(System.out::println);

        } catch (InvalidPathException | SAXException | IOException e) {
            throw new PeliculaException(e.getMessage());
        }
    }

    /**
     * Este método va a añadir al archivo XML la película especificada
     * por parámetros
     *
     * @param pelicula la película a añadir
     * @throws PeliculaException
     */
    public static void anadirPeliculaXML(Pelicula pelicula) throws PeliculaException {
        // Comprobamos que el código tenga el formato correcto especificado
        if (!pelicula.getCodigo().matches("^P[0-9]{3}$")) {
            throw new PeliculaException("El código de la película no es correcto");
        }
        try {
            // Creamos el Path que apunta al archivo XML
            Path archivoXML = Path.of("Boletin_Tema6/src/Extra/Ejercicio24E/peliculas.xml");
            // Parseamos el XML a un Document para poder trabajar con él
            Document doc = crearDocumentBuilder().parse(archivoXML.toFile());
            // Creamos los nodos de la nueva película
            Element peliculaElement = doc.createElement("pelicula");
            peliculaElement.setAttribute("codigo", pelicula.getCodigo());
            Element titulo = doc.createElement("titulo");
            titulo.setTextContent(pelicula.getTitulo());
            Element genero = doc.createElement("genero");
            genero.setTextContent(pelicula.getGenero());
            Element duracion = doc.createElement("duracion");
            duracion.setTextContent(String.valueOf(pelicula.getDuracion()));
            // Añadimos los nodos hijos de la película
            peliculaElement.appendChild(titulo);
            peliculaElement.appendChild(genero);
            peliculaElement.appendChild(duracion);
            // Añadimos al nodo padre la película
            doc.getDocumentElement().appendChild(peliculaElement);
            // Guardamos los cambios
            guardarCambios(doc, archivoXML);


        } catch (InvalidPathException | SAXException | IOException e) {
            throw new PeliculaException(e.getMessage());
        }
    }

    /**
     * Este método va a eliminar las películas que cumplan con
     * el regex especificado, más concretamente el género de las
     * películas que cumplan con el regex
     *
     * @throws PeliculaException
     */
    public static void eliminarPeliculasPorGenero() throws PeliculaException {
        try {
            // Ubicación del archivo XML
            Path archivoXML = Path.of("Boletin_Tema6/src/Extra/Ejercicio24E/peliculas.xml");
            // Parseamos el archivo XML a un Document para poder manipularlo
            Document doc = crearDocumentBuilder().parse(archivoXML.toFile());
            // Obtenemos una lista de nodos con las películas
            // TODO: preguntar por qué usar directamente doc.getChildNodes(); no me elimina el nodo
            NodeList peliculas = doc.getDocumentElement().getChildNodes();
            // Nos creamos una lista de nodos con los nodos a eliminar, ya que no se pueden eliminar directamente
            List<Node> nodosAEliminar = new ArrayList<>();
            // Recorremos las películas
            for (int i = 0; i < peliculas.getLength(); i++) {
                // Si es un nodo de tipo elemento entra
                if (peliculas.item(i).getNodeType() == Node.ELEMENT_NODE) {
                    Element pelicula = (Element) peliculas.item(i);
                    // Si el género de la película coincide con el regex especificado entra
                    if (pelicula.getElementsByTagName("genero").item(0).getTextContent()
                            .matches("^\\p{L}+ \\p{L}+$")) {
                        /* No se puede eliminar directamente, ya que NodeList está unido directamente
                         * al árbol de nodos, es decir cuando eliminas un nodo del árbol de nodos
                         * también se elimina del NodeList, ya que este es como una "ventana viva"
                         * del DOM */
                        //doc.getDocumentElement().removeChild(peliculas.item(i));
                        nodosAEliminar.add(peliculas.item(i));
                    }
                }
            }
            // Eliminamos las películas del DOM que coincidan con el regex
            nodosAEliminar.forEach(e -> doc.getDocumentElement().removeChild(e));
            // Guardamos los cambios
            guardarCambios(doc, archivoXML);

        } catch (InvalidPathException | SAXException | IOException e) {
            throw new PeliculaException(e.getMessage());
        }
    }
}