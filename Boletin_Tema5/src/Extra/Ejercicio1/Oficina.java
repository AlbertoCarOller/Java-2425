package Extra.Ejercicio1;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Oficina {
    static List<Impresora> impresoras = new ArrayList<>();

    public static void main(String[] args) {
        try {
            Documento documento1 = new Documento("Atisbedo", "hola Atis", Prioridad.BAJA, "Respicio");
            Documento documento2 = new Documento("Cheulu's House", "hola Chelu", Prioridad.MEDIA, "Chelu");
            Documento documento3 = new Documento("Resoicio Godefrio", "hola Respicio", Prioridad.MEDIA, "Atisbedo");
            Documento documento4 = new Documento("La caida del Sensei", "hola Sensei", Prioridad.MEDIA, "Sensei");
            Documento documento5 = new Documento("El ascenso del pequeño saltamonte", "hola pequeño saltamonte",
                    Prioridad.ALTA, "Pequeño Saltamonte");

            Impresora impresora1 = new Impresora("Primera impresora");
            Impresora impresora2 = new Impresora("Segunda impresora");

            impresoras.add(impresora1);
            impresoras.add(impresora2);

            anadirAImpresoraConMenosDocumentos(documento1);
            anadirAImpresoraConMenosDocumentos(documento2);
            anadirAImpresoraConMenosDocumentos(documento3);
            anadirAImpresoraConMenosDocumentos(documento4);
            anadirAImpresoraConMenosDocumentos(documento5);

            impresora1.imprimirDocumento();
            impresora2.imprimirDocumento();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // Hacemos un método que devuelva la impresora con menos documentos
    public static void anadirAImpresoraConMenosDocumentos(Documento documento) throws DocumentoException {
        if (impresoras.isEmpty()) {
            throw new DocumentoException("No hay impresoras");
        }
        Iterator<Impresora> it = impresoras.iterator();
        boolean primeraVez = true;
        Impresora impresora;
        Impresora impresoraMenor = null;
        while (it.hasNext()) {
            impresora = it.next();
            if (primeraVez) {
                primeraVez = false;
                impresoraMenor = impresora;
            }
            if (impresoraMenor.getDocumentos().size() > impresora.getDocumentos().size()) {
                impresoraMenor = impresora;
            }
        }
        if (impresoraMenor != null) {
            impresoraMenor.anadirDocumento(documento);
        }
    }
}