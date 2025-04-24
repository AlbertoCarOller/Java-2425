package Ayuda.EjerciciosApoyoXML;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;

public class PracticaBaseXML {
    public static void main(String[] args) {
        try {
            leerXML();

        } catch (PracticaBaseXMLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Este método procesa un archivo xml accediendo al nodo <persona></persona>
     * e imprimiendo sus nodos hijos, evitando los nodos de tipo texto
     *
     * @throws PracticaBaseXMLException
     */
    public static void leerXML() throws PracticaBaseXMLException {
        try {
            // Creamos el Path del archivo 'persona.xml'
            Path archivoXML = Path.of("Boletin_Tema6/src/Ayuda/EjerciciosApoyoXML/persona.xml");
            /* DocumentBuilderFactory es una clase abstracta, con su método .newInstance() creamos
             * una instancia de la fábrica de documentos. La "fábrica" es la que permite crear el objeto
             * DocumentBuilder, este objeto va a analizar el archivo xml */
            DocumentBuilderFactory dBFactory = DocumentBuilderFactory.newInstance();
            /* DocumentBuilder es una clase abstracta, esta se encarga de parsear el archivo xml
             * en un árbol de nodos que podemos manipular en memoria, este árbol se le llama
             * Document */
            DocumentBuilder dB = dBFactory.newDocumentBuilder();
            /* Document es una interfaz, IMPORTANTE, EXISTEN DOS INTERFACES Document DE DOS PAQUETES
             * DISTINTOS, EL QUE TRABAJA CON XML ES 'org.w3c.dom.Document', Document es la interfaz
             * que representa el archivo xml mediante una jerarquía de nodos, con los diferentes
             * métodos de Document podremos recorrer los elementos, obtener sus valores y manipularlos */
            Document doc = dB.parse(archivoXML.toFile());
            /* Normaliza desde el nodo raíz con .getDocumentElement() (obtener nodo raíz) hasta el
             * resto de nodos que están dentro de este, pero empieza por el nodo raíz, fusiona
             * nodos de textos adyacentes, por ejemplo, si en el xml, hay algo así
             * <nombre>Lu</nombre><nombre>cía</nombre>, lo fusiona en uno solo '<nombre>Lucía</nombre>'
             * limpia los nodos de tipo texto vacíos (""), pero no si tienen espacios o saltos
             * de línea */
            //doc.getDocumentElement().normalize();
            /* Normaliza el documento completo al igual que el método anterior, pero no
             * empieza por el nodo raíz, sino que se aplica al documento completo directamente */
            doc.normalize();
            // getElementsByTagName() lista los nodos con ese nombre, devuelve una lista de nodos
            NodeList personas = doc.getElementsByTagName("persona");
            // En este caso con .item() cogemos el primer elemento de la lista de nodos
            Node primeraPersona = personas.item(0);
            // .getChildNodes() lista los nodos hijos de un nodo, devuelve una lista de nodos (con nombre y edad)
            NodeList nodosPersona = primeraPersona.getChildNodes();
            // Recorremos la lista de los nodos hijos de persona e imprimimos su nombre y contenido
            for (int i = 0; i < nodosPersona.getLength(); i++) {
                // Nos saltamos los nodos de tipo texto, ya que son espacios y no queremos espacios innecesarios
                if (/*!nodosPersona.item(i).getNodeName().equalsIgnoreCase("#text")*/
                        nodosPersona.item(i).getNodeType() == Node.ELEMENT_NODE) {
                    System.out.println(nodosPersona.item(i).getNodeName() + ": " + nodosPersona.item(i).getTextContent());
                }
            }

        } catch (InvalidPathException | ParserConfigurationException | SAXException | IOException e) {
            throw new PracticaBaseXMLException(e.getMessage());
        }
    }
}