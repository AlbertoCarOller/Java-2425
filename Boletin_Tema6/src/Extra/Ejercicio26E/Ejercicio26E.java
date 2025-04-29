package Extra.Ejercicio26E;

import Extra.Ejercicio23E.Ejercicio23Exception;
import Extra.Ejercicio24E.PeliculaException;
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
import java.nio.file.StandardOpenOption;

public class Ejercicio26E {
    public static void main(String[] args) {
        try {
            validarRecetas();
            agregarReceta("R409", "Chelu a la brasa", "Chelu y carbón",
                    "200 minutos", "Fácil");

        } catch (Ejercicio26EException e) {
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
    public static DocumentBuilder crearDocumentBuilder() throws Ejercicio26EException {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        try {
            return documentBuilderFactory.newDocumentBuilder();

        } catch (ParserConfigurationException e) {
            throw new Ejercicio26EException(e.getMessage());
        }
    }

    /**
     * Este método va a recorrer las diferentes recetas en busca
     * de invalidaciones
     *
     * @throws Ejercicio26EException
     */
    public static void validarRecetas() throws Ejercicio26EException {
        try {
            // Path del archivo XML a parsear
            Path archivoXML = Path.of("Boletin_Tema6/src/Extra/Ejercicio26E/recetas.xml");
            Path escribirV = Path.of("Boletin_Tema6/src/Extra/Ejercicio26E/RecetasCorrectas.txt");
            Path escribirI = Path.of("Boletin_Tema6/src/Extra/Ejercicio26E/RecetasIncorrectas.txt");
            // Parseamos el archivo XML a un Document
            Document doc = crearDocumentBuilder().parse(archivoXML.toFile());
            NodeList recetas = doc.getDocumentElement().getChildNodes();
            // Recorremos las recetas
            for (int i = 0; i < recetas.getLength(); i++) {
                if (recetas.item(i).getNodeType() == Node.ELEMENT_NODE) {
                    // Construimos la frase
                    StringBuilder sb = new StringBuilder();
                    // Transformamos la receta a un Element para poder trabajar mejor con este
                    Element receta = (Element) recetas.item(i);
                    appendMensajesError(receta, sb, escribirV, escribirI);
                }
            }

        } catch (InvalidPathException | IOException | SAXException e) {
            throw new Ejercicio26EException(e.getMessage());
        }
    }

    /**
     * Este método va a comprobar si son correctos los formatos de cada
     * apartado y añadir al StringBuilder los mensajes de error correspondiente,
     * este es un método auxiliar
     *
     * @param receta el elemento receta que estamos recorriendo
     * @param sb     el StringBuilder al que se le van a añadir los mensajes
     * @param v      el Path de los válidos
     * @param i      el Path de los inválidos
     * @throws Ejercicio26EException
     */
    public static void appendMensajesError(Element receta, StringBuilder sb, Path v, Path i) throws Ejercicio26EException {
        try {
            // Si al menos hay un error pasará a true
            boolean alMenosUn = false;
            // Validamos los campos necesarios y construimos las líneas basándonos en esto
            if (!receta.getAttribute("id").matches("^R\\d{3}$")) {
                sb.append("-> El id no es correcto\n");
                alMenosUn = true;

            }
            String nombre = receta.getElementsByTagName("nombre").item(0).getTextContent();
            if (!nombre.matches("^\\p{L}[\\p{L}\\s,]+$")) {
                sb.append("-> El nombre no es correcto\n");
                alMenosUn = true;

            }
            String tiempoPreparacion = receta.getElementsByTagName("tiempoPreparacion").item(0)
                    .getTextContent();
            if (!tiempoPreparacion.matches("^([1-9]|[123456789][0-9]|1[0-9]{2}|2[0-3][0-9]|240) minutos$")) {
                sb.append("-> El tiempo de preparación no es correcto\n");
                alMenosUn = true;

            }
            String dificultad = receta.getElementsByTagName("dificultad").item(0).getTextContent();
            if (!dificultad.matches("^Fácil|Media|Difícil$")) {
                sb.append("-> La dificultad no es correcta\n");
                alMenosUn = true;
            }
            // Construimos la información de la receta
            sb.append(receta.getAttribute("id")).append(" | ").append(nombre).append(" | ")
                    .append(receta.getElementsByTagName("ingredientes").item(0).getTextContent())
                    .append(" | ").append(tiempoPreparacion).append(" | ").append(dificultad);
            // Escribimos el contenido donde corresponda
            if (alMenosUn) {
                Files.writeString(i, sb + "\n", StandardOpenOption.APPEND);

            } else {
                Files.writeString(v, sb + "\n", StandardOpenOption.APPEND);
            }
        } catch (IOException e) {
            throw new Ejercicio26EException(e.getMessage());
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
    public static void guardarCambios(Document doc, Path archivoFinal) throws Ejercicio26EException {
        try {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource domSource = new DOMSource(doc);
            StreamResult streamResult = new StreamResult(archivoFinal.toFile());
            transformer.transform(domSource, streamResult);

        } catch (TransformerException e) {
            throw new Ejercicio26EException(e.getMessage());
        }
    }

    /**
     * Este método va a añadir una receta a la estructura XML en caso de que todos los campos
     * sean válidos
     *
     * @param id
     * @param nombre
     * @param ingredientes
     * @param tiempoPreparacion
     * @param dificultad
     * @throws Ejercicio26EException
     */
    public static void agregarReceta(String id, String nombre, String ingredientes, String tiempoPreparacion,
                                     String dificultad) throws Ejercicio26EException {
        try {
            // Path del archivo XML
            Path archivoXML = Path.of("Boletin_Tema6/src/Extra/Ejercicio26E/recetas.xml");
            // Parseamos el archivo XML a Document para poder trabajar con él
            Document doc = crearDocumentBuilder().parse(archivoXML.toFile());
            // Creamos la Receta y sus atributos (nodos hijos) comprobando que tengan un formato correcto
            Element receta = doc.createElement("receta");
            if (!id.matches("^R\\d{3}$")) {
                throw new Ejercicio26EException("El id no tiene un formato correcto");
            }
            receta.setAttribute("id", id);
            if (!nombre.matches("^\\p{L}[\\p{L}\\s,]+$")) {
                throw new Ejercicio26EException("El nombre no tiene un formato correcto");
            }
            Element nombreE = doc.createElement("nombre");
            nombreE.setTextContent(nombre);
            receta.appendChild(nombreE);
            Element ingredientesE = doc.createElement("ingredientes");
            ingredientesE.setTextContent(ingredientes);
            receta.appendChild(ingredientesE);
            if (!tiempoPreparacion.matches("^([1-9]|[123456789][0-9]|1[0-9]{2}|2[0-3][0-9]|240) minutos$")) {
                throw new Ejercicio26EException("El tiempo de preparación no tiene un formato correcto");
            }
            Element tiempoPreparacionE = doc.createElement("tiempoPreparacion");
            tiempoPreparacionE.setTextContent(tiempoPreparacion);
            receta.appendChild(tiempoPreparacionE);
            if (!dificultad.matches("^Fácil|Media|Difícil$")) {
                throw new Ejercicio26EException("La dificultad no tiene un formato correcto");
            }
            Element dificultadE = doc.createElement("dificultad");
            dificultadE.setTextContent(dificultad);
            receta.appendChild(dificultadE);
            // Colocamos en el Document la receta ya completa
            doc.getDocumentElement().appendChild(receta);
            // Guardamos los cambios
            guardarCambios(doc, archivoXML);

        } catch (InvalidPathException | IOException | SAXException e) {
            System.out.println(e.getMessage());
        }
    }
}