package Extra.Ejercicio23E;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Ejercicio23E {
    public static void main(String[] args) {
        try {
            crearXML(validarLineas()); // Llamamos al método
            modificarDniPersona("Alberto Carmona", "88888888A");

        } catch (Ejercicio23Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Este método valída las líneas que tengan un formato correcto y devuelve
     * una lista con las líneas correctas
     *
     * @return lista con las líneas válidas
     * @throws Ejercicio23Exception
     */
    public static List<String> validarLineas() throws Ejercicio23Exception {
        try {
            // El path del fichero a leer
            Path leerlo = Path.of("Boletin_Tema6/src/Extra/Ejercicio23E/ciudadanos.txt");
            // Creamos un flujo de todas las líneas del fichero especificado
            try (Stream<String> lineas = Files.lines(leerlo)) {
                Pattern pattern = Pattern.compile("^(\\p{Lu}\\p{Ll}+ \\p{Lu}\\p{Ll}+);(\\d{8}[A-Z]);([679]\\d{8});" +
                        "((?:0[1-9]|1[0-9]|2[0-9]|3[01])/(?:0[1-9]|1[0-2])/(?:19[0-9]{2}|20[0-9]{2}))$");
                // Validaremos cada línea del fichero y devolveremos la lista de las líneas válidas
                return lineas.filter(s -> {
                    Matcher matcher = pattern.matcher(s);
                    return matcher.matches();
                }).toList();
            }

        } catch (InvalidPathException | IOException e) {
            throw new Ejercicio23Exception(e.getMessage());
        }
    }

    /**
     * Este método crea el archivo XML a partir de los elementos escritos
     * en las líneas correctas
     *
     * @param lineasCorrectas las líneas que han pasado la validación
     * @throws Ejercicio23Exception
     */
    public static void crearXML(List<String> lineasCorrectas) throws Ejercicio23Exception {
        try {
            // Path del archivo XML a crear
            Path archivoXML = Path.of("Boletin_Tema6/src/Extra/Ejercicio23E/votantes.xml");
            // Creamos un nuevo Document para poder crear los nodos a partir de las líneas correctas
            DocumentBuilderFactory dBFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dB = dBFactory.newDocumentBuilder();
            Document doc = dB.newDocument();
            // Creamos el nodo padre
            Element root = doc.createElement("votantes");
            // Lo añadimos al árbol de nodos
            doc.appendChild(root);
            // Recorremos las líneas para crear a los votantes y sus nodos hijos
            lineasCorrectas.forEach(l -> {
                // Separamos las partes de la línea
                String[] partes = l.split(";");
                Element votante = doc.createElement("votante");
                // Creamos el atributo dni por cada votante
                votante.setAttribute("dni", partes[1]);
                Element nombre = doc.createElement("nombre");
                nombre.setTextContent(partes[0]);
                Element telefono = doc.createElement("telefono");
                telefono.setTextContent(partes[2]);
                Element fecha = doc.createElement("fecha");
                fecha.setTextContent(partes[3]);
                // Asignamos los nodos donde corresponde
                votante.appendChild(nombre);
                votante.appendChild(telefono);
                votante.appendChild(fecha);
                doc.getDocumentElement().appendChild(votante);
            });
            // Guardamos los cambios realizados y creamos el documento XML
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource domSource = new DOMSource(doc);
            StreamResult streamResult = new StreamResult(archivoXML.toFile());
            transformer.transform(domSource, streamResult);

        } catch (Exception e) {
            throw new Ejercicio23Exception(e.getMessage());
        }
    }

    /**
     * Este método va a buscar al votante con el nombre especificado,
     * una vez encontrado le cambiaremos el dni por el dni especificado
     *
     * @param nombrePersona el nombre del votante a buscar
     * @param dniNuevo      el nuevo dni del votante especificado
     * @throws Ejercicio23Exception
     */
    public static void modificarDniPersona(String nombrePersona, String dniNuevo) throws Ejercicio23Exception {
        try {
            // Ubicación del archivo XML
            Path archivoXML = Path.of("Boletin_Tema6/src/Extra/Ejercicio23E/votantes.xml");
            // Parseamos el archivo XML a un Document para poder manipularlo
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document doc = documentBuilder.parse(archivoXML.toFile());
            // Recorremos los nodos hijos del nodo padre
            NodeList hijosRoot = doc.getDocumentElement().getChildNodes();
            // Nos servirá para dejar de recorrer una vez encontrado el votante o en caso de que no lanzar excepción
            boolean encontrado = false;
            // Recorremos los nodos hijos del nodo padre, es decir 'votante'
            for (int i = 0; i < hijosRoot.getLength(); i++) {
                // Si se ha encontrado salir del for
                if (encontrado) {
                    break;
                }
                // Si es un nodo elemento entra
                if (hijosRoot.item(i).getNodeType() == Node.ELEMENT_NODE) {
                    // Ahora obtenemos una "lista" de los nodos hijos de cada 'votante'
                    NodeList hijosHijosRoot = hijosRoot.item(i).getChildNodes();
                    for (int j = 0; j < hijosHijosRoot.getLength(); j++) {
                        // Recorremos la lista anterior
                        if (hijosHijosRoot.item(j).getTextContent().equalsIgnoreCase(nombrePersona)) {
                            Element element = (Element) hijosRoot.item(i);
                            element.setAttribute("dni", dniNuevo);
                            encontrado = true;
                            break;
                        }
                    }
                }
            }
            // Guardamos los cambios realizados
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource domSource = new DOMSource(doc);
            StreamResult streamResult = new StreamResult(archivoXML.toFile());
            transformer.transform(domSource, streamResult);

            if (!encontrado) {
                throw new Ejercicio23Exception("No se ha podido encontrar al votante");
            }

        } catch (Exception e) {
            throw new Ejercicio23Exception(e.getMessage());
        }
    }

    //TODO: método para mostrar por pantalla los votantes mayores de edad y optimizar código (meta personal)
}