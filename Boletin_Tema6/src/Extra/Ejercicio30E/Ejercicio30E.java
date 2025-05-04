package Extra.Ejercicio30E;

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

public class Ejercicio30E {
    public static void main(String[] args) {
        try {
            mostrarPlatosBaratos();
            System.out.println();
            mostrarPlatosBajoEnCalorias();
            anadirCodigo();
            anadirPlatoVegetariano("Antonio a la vegana", "48.5", "550");
            eliminarInsalubres();

        } catch (Ejercicio30EException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Este método va a crear un DocumentBuilder para así facilitar esta parte del código,
     * ya que siempre es la misma
     *
     * @return
     * @throws Ejercicio30EException
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
     * Este método guarda los cambios realizados o la creación del nuevo Document
     * en el archivo especificado, este método es simplemente para optimizar, ya que
     * el proceso siempre es el mismo
     *
     * @param doc          el Document
     * @param archivoFinal el archivo destino (final)
     * @throws Ejercicio30EException
     */
    public static void guardarCambios(Document doc, Path archivoFinal) throws Ejercicio30EException {
        try {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource domSource = new DOMSource(doc);
            StreamResult streamResult = new StreamResult(archivoFinal.toFile());
            transformer.transform(domSource, streamResult);

        } catch (TransformerException e) {
            throw new Ejercicio30EException(e.getMessage());
        }
    }

    /**
     * Este método va a mostrar por pantalla el nombre de los platos que su precio sea
     * menor a 7 euros
     *
     * @throws Ejercicio30EException
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
     * @throws Ejercicio30EException
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

    /**
     * Este método va a añadir como atributo de los platos códigos,
     * los cuales empiezan en 1001 e irá aumentando a medida que haya
     * más platos
     *
     * @throws Ejercicio30EException
     */
    public static void anadirCodigo() throws Ejercicio30EException {
        try {
            Path archivoXML = Path.of("Boletin_tema6/src/Extra/Ejercicio30E/menu_semana.xml");
            Document doc = crearDocumentBuilder().parse(archivoXML.toFile());
            NodeList platos = doc.getDocumentElement().getElementsByTagName("plato");
            int codigoPlato = 1001;
            for (int i = 0; i < platos.getLength(); i++) {
                Element plato = (Element) platos.item(i);
                plato.setAttribute("codigo", String.valueOf(codigoPlato++));
            }
            // Guardamos los cambios
            guardarCambios(doc, archivoXML);

        } catch (InvalidPathException | IOException | SAXException e) {
            throw new Ejercicio30EException(e.getMessage());
        }
    }

    /**
     * Este método va a añadir un plato vegetariano en el primer día
     * de la semana
     *
     * @param nombre   el nombre del plato
     * @param precio   el precio del plato
     * @param calorias las calorías del plato
     * @throws Ejercicio30EException
     */
    public static void anadirPlatoVegetariano(String nombre, String precio, String calorias) throws Ejercicio30EException {
        try {
            Path archivoXML = Path.of("Boletin_Tema6/src/Extra/Ejercicio30E/menu_semana.xml");
            Document doc = crearDocumentBuilder().parse(archivoXML.toFile());
            NodeList dias = doc.getDocumentElement().getChildNodes();
            for (int i = 0; i < dias.getLength(); i++) {
                if (dias.item(i).getNodeType() == Node.ELEMENT_NODE) {
                    Element dia = (Element) dias.item(i);
                    if (dia.getAttribute("nombre").equals("Lunes")) {
                        Element plato = doc.createElement("plato");
                        if (!nombre.matches("(\\p{L}+\\s?)+")) {
                            throw new Ejercicio30EException("El nombre del plato no es válido");
                        }
                        Element nombreE = doc.createElement("nombre");
                        nombreE.setTextContent(nombre);
                        if (!precio.matches("([0-9]|[1-9][0-9])\\.\\d{1,2}")) {
                            throw new Ejercicio30EException("El precio no tiene un formato válido");
                        }
                        Element precioE = doc.createElement("precio");
                        precioE.setTextContent(precio);
                        if (!calorias.matches("[1-9]\\d{1,2}")) {
                            throw new Ejercicio30EException("Las calorías no son válidas");
                        }
                        Element caloriasE = doc.createElement("calorias");
                        caloriasE.setTextContent(calorias);
                        // Apendamos los nodos hijos al plato
                        plato.appendChild(doc.createTextNode("\n\t\t\t"));
                        plato.appendChild(nombreE);
                        plato.appendChild(doc.createTextNode("\n\t\t\t"));
                        plato.appendChild(precioE);
                        plato.appendChild(doc.createTextNode("\n\t\t\t"));
                        plato.appendChild(caloriasE);
                        plato.appendChild(doc.createTextNode("\n\t\t"));
                        // Añadimos el plato al lunes
                        dia.appendChild(doc.createTextNode("\t"));
                        dia.appendChild(plato);
                        dia.appendChild(doc.createTextNode("\n\t"));
                        // Guardamos los cambios
                        guardarCambios(doc, archivoXML);
                    }
                }
            }

        } catch (InvalidPathException | IOException | SAXException e) {
            throw new Ejercicio30EException(e.getMessage());
        }
    }

    /**
     * Este método va a eliminar del DOM los platos que tengan más de 700 calorías
     * y crear un nuevo archivo XML con el resto
     *
     * @throws Ejercicio30EException
     */
    public static void eliminarInsalubres() throws Ejercicio30EException {
        try {
            Path archivoXML = Path.of("Boletin_Tema6/src/Extra/Ejercicio30E/menu_semana.xml");
            Path archivoNuevoXML = Path.of("Boletin_Tema6/src/Extra/Ejercicio30E/menu_saludable.xml");
            Document doc = crearDocumentBuilder().parse(archivoXML.toFile());
            NodeList dias = doc.getDocumentElement().getChildNodes();
            for (int i = 0; i < dias.getLength(); i++) {
                if (dias.item(i).getNodeType() == Node.ELEMENT_NODE) {
                    Element dia = (Element) dias.item(i);
                    NodeList platos = dia.getChildNodes();
                    for (int j = 0; j < platos.getLength(); j++) {
                        if (platos.item(j).getNodeType() == Node.ELEMENT_NODE) {
                            Element plato = (Element) platos.item(j);
                            if (!plato.getElementsByTagName("calorias").item(0).getTextContent()
                                    .matches("([1-9]|[1-9][0-9]|[1-6]\\d{2}|700)")) {
                                dia.removeChild(plato);
                            }
                        }
                    }
                }
            }
            // Guardamos los cambios en el archivo nuevo
            guardarCambios(doc, archivoNuevoXML);

        } catch (InvalidPathException | IOException | SAXException e) {
            throw new Ejercicio30EException(e.getMessage());
        }
    }
}