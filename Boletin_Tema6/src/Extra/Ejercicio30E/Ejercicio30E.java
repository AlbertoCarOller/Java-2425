package Extra.Ejercicio30E;


import Extra.Ejercicio23E.Ejercicio23Exception;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;

public class Ejercicio30E {
    public static void main(String[] args) {
        try {
            mostrarPlatosBaratos();
            System.out.println();
            mostrarPlatosBajoEnCalorias();

        } catch (Ejercicio30EException e) {
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
    public static DocumentBuilder crearDocumentBuilder() throws Ejercicio30EException {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        try {
            return documentBuilderFactory.newDocumentBuilder();

        } catch (ParserConfigurationException e) {
            throw new Ejercicio30EException(e.getMessage());
        }
    }

    /**
     * Este método va a mostrar por pantalla el nombre de los platos que su
     * precio sea menor a 7 euros
     *
     * @throws Ejercicio30EException si el Path si es inválido o si al
     *                               parsear da errores
     */
    public static void mostrarPlatosBaratos() throws Ejercicio30EException {
        try {
            Path archivoXML = Path.of("Boletin_Tema6/src/Extra/Ejercicio30E/menu_semana.xml");
            Document doc = crearDocumentBuilder().parse(archivoXML.toFile());
            NodeList platos = doc.getDocumentElement().getElementsByTagName("plato");
            for (int i = 0; i < platos.getLength(); i++) {
                Element plato = (Element) platos.item(i);
                if (plato.getElementsByTagName("precio").item(0).getTextContent().matches("[0-6]\\.[0-9]{1,2}")) {
                    System.out.println(plato.getElementsByTagName("nombre").item(0).getTextContent());
                }
            }

        } catch (InvalidPathException | IOException | SAXException e) {
            throw new Ejercicio30EException(e.getMessage());
        }
    }

    /**
     * Mostramos los platos que tengan menos de 600 calorías
     *
     * @throws Ejercicio30EException si el Path es inválido o
     *                               al parsear da error
     */
    public static void mostrarPlatosBajoEnCalorias() throws Ejercicio30EException {
        try {
            Path archivoXML = Path.of("Boletin_Tema6/src/Extra/Ejercicio30E/menu_semana.xml");
            Document doc = crearDocumentBuilder().parse(archivoXML.toFile());
            NodeList platos = doc.getDocumentElement().getElementsByTagName("plato");
            for (int i = 0; i < platos.getLength(); i++) {
                Element plato = (Element) platos.item(i);
                if (plato.getElementsByTagName("calorias").item(0).getTextContent()
                        .matches("([1-9]{1,2}|[1-5][0-9]{2})")) {
                    System.out.println(plato.getElementsByTagName("nombre").item(0).getTextContent());
                }
            }

        } catch (InvalidPathException | IOException | SAXException e) {
            throw new Ejercicio30EException(e.getMessage());
        }
    }

    // TODO: hacer el resto de métodos
}