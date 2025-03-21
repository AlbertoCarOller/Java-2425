package Ejercicio1;

import java.util.Arrays;

public class Biblioteca {

    // Creamos un array de documentos
    private static int MAX_DOCUMENTOS = 100;
    private Documento[] documentos;

    // Creamos el constructor
    public Biblioteca() {
        this.documentos = new Documento[MAX_DOCUMENTOS];
    }

    // Hacemos los get y set
    public Documento[] getDocumentos() {
        return documentos;
    }

    private void setDocumentos(Documento[] documentos) {
        this.documentos = documentos;
    }

    // Hacemos un método para meter documentos
    public void anadirDocumento(Documento documento) throws DocumentoExcepcion {

        boolean esIgual = false;
        int hayEspacio = -1;

        for (int i = 0; i < documentos.length && !esIgual; i++) {

            if (documento.equals(documentos[i])) {
                esIgual = true;
            }

            if (documentos[i] == null && hayEspacio == -1) {
                hayEspacio = i;
            }
        }

        if (!esIgual && hayEspacio != -1) {
            documentos[hayEspacio] = documento;

        } else {
            throw new DocumentoExcepcion("No se ha podido añadir el documento");
        }
    }

    // Hacemos un método para mostrar todos los documentos
    @Override
    public String toString() {
        return "Biblioteca{" +
                "documentos=" + Arrays.toString(documentos) +
                '}';
    }

    // Hacemos un método para comprobar el descargable más popular
    public int descargablePopular() throws DocumentoExcepcion {

        int mayorDescargable = -1;
        int indiceDescargables = -1;

        for (int i = 0; i < documentos.length; i++) {

            // Si es un libro, es descargable, por lo que debe de entrar
            if (documentos[i] instanceof Descargable descargable) {
                /* Si el mayor es más pequeño que la cantidad de descargas del descargable que está revisando mayor
                 pasa a valer el descargable en la posición i */
                if (mayorDescargable < descargable.cantDescargas()) {
                    mayorDescargable = descargable.cantDescargas();
                    // Guardamos la posición donde se encuentra el descargable más popular
                    indiceDescargables = i;
                }
            }
        }

        if (indiceDescargables == -1) {
            throw new DocumentoExcepcion("No se han encontrados descargables");
        }
        return indiceDescargables;
    }

    // Hacemos un método para comprobar el leible más popular
    public int leiblePopular() throws DocumentoExcepcion {

        int mayorLeible = -1;
        int indiceLeible = -1;

        for (int i = 0; i < documentos.length; i++) {

            if (documentos[i] instanceof LeibleOnline leibleOnline) {

                if (mayorLeible < leibleOnline.obtenerVisualizaciones()) {
                    mayorLeible = leibleOnline.obtenerVisualizaciones();
                    indiceLeible = i;
                }
            }
        }

        if (indiceLeible == -1) {
            throw new DocumentoExcepcion("No se han encontrado leibles");
        }
        return indiceLeible;
    }
}