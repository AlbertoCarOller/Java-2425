package Extra.Ejercicio23E;

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
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Ejercicio23E {
    public static void main(String[] args) {
        try {
            // Llamamos a los métodos
            crearXML(validarLineas());
            modificarDniPersona("Alberto Carmona", "88888888A");
            mostrarVotantesMayoresEdad();

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
     * Este método va a crear un DocumentBuilder para así facilitar esta parte del código
     * ya que siempre es la misma
     *
     * @return el DocumentBuilder
     * @throws Ejercicio23Exception
     */
    public static DocumentBuilder crearDocumentBuilder() throws Ejercicio23Exception {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        try {
            return documentBuilderFactory.newDocumentBuilder();

        } catch (ParserConfigurationException e) {
            throw new Ejercicio23Exception(e.getMessage());
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
    public static void guardarCambios(Document doc, Path archivoFinal) throws Ejercicio23Exception {
        try {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource domSource = new DOMSource(doc);
            StreamResult streamResult = new StreamResult(archivoFinal.toFile());
            transformer.transform(domSource, streamResult);

        } catch (TransformerException e) {
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
            Document doc = crearDocumentBuilder().newDocument();
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
            guardarCambios(doc, archivoXML);


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
            Document doc = crearDocumentBuilder().parse(archivoXML.toFile());
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
            if (!encontrado) {
                throw new Ejercicio23Exception("No se ha podido encontrar al votante");
            }
            // Guardamos los cambios realizados
            guardarCambios(doc, archivoXML);

        } catch (Exception e) {
            throw new Ejercicio23Exception(e.getMessage());
        }
    }

    /**
     * Este método va a mostrar por pantalla el nombre de los votantes que
     * sean mayor de edad
     *
     * @throws Ejercicio23Exception
     */
    public static void mostrarVotantesMayoresEdad() throws Ejercicio23Exception {
        try {
            // El archivo XML que vamos a leer
            Path archivoXML = Path.of("Boletin_Tema6/src/Extra/Ejercicio23E/votantes.xml");
            // Lo transformamos a un Document para poder leerlo
            Document doc = crearDocumentBuilder().parse(archivoXML.toFile());
            // Obtenemos un NodeList con los votantes
            NodeList votantes = doc.getDocumentElement().getChildNodes();
            List<String> mayoresEdad = new ArrayList<>();
            // Recorremos los votantes en busca de los mayores de edad
            for (int i = 0; i < votantes.getLength(); i++) {
                if (votantes.item(i).getNodeType() == Node.ELEMENT_NODE) {
                    // Transformamos el nodo en un elemento para poder trabajar mejor con él
                    Element votante = (Element) votantes.item(i);
                    // Cogemos la fecha del votante
                    Element fecha = (Element) votante.getElementsByTagName("fecha").item(0);
                    /* Transformamos la fecha de un String a un LocalDate especificándole al .parse()
                     * cuál es el formato de la fecha que se le ha pasado */
                    LocalDate fechaNacimiento = LocalDate.parse(fecha.getTextContent(),
                            DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                    // Si han pasado 18 años o más desde la 'fechaNacimiento' hasta la fecha actual entra
                    if (Math.abs(ChronoUnit.YEARS.between(LocalDate.now(), fechaNacimiento)) >= 18) {
                        // Se añade a la lista el nombre del votante que cumpla la condición
                        mayoresEdad.add(votante.getElementsByTagName("nombre").item(0).getTextContent());
                    }
                }
            }
            // Imprimimos por pantalla los nombres de los votantes que son mayores de edad
            mayoresEdad.forEach(System.out::println);

        } catch (InvalidPathException | SAXException | IOException e) {
            throw new Ejercicio23Exception(e.getMessage());
        }
    }
}