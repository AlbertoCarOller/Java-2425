package Ejercicio4;

import java.time.LocalDate;
import java.time.Year;

public class ObraApp {

    // Creamos el tamaño del array
    static int TAMANO_MAXIMO = 10;
    // Creamos un array de obras
    static Obra[] obras = new Obra[TAMANO_MAXIMO];

    public static void main(String[] args) {

        try {
            // Creamos las obras
            ImagenDigital imagen1 = new ImagenDigital("sarita", "Sara", Year.of(2009), 2000, 480, Formato.PNG,
                    LocalDate.of(2025, 3, 1), 20, 15);
            Modelo3D modelo3D1 = new Modelo3D("atisbedo", "Alberto", Year.of(2024), 5000, 4,
                    23, 12, Interaccion.REALIDAD_VIRTUAL);
            Modelo3D modelo3D2 = new Modelo3D("respicio godefrio", "Lolo", Year.of(2020), 2900, 2,
                    12, 6, Interaccion.MANIPULACION_TACTIL);

            obras[0] = imagen1;
            obras[1] = modelo3D1;
            obras[2] = modelo3D2;

            buscarImagenesPopulares();
            buscarModelosPopulares();

        } catch (ObraException e) {
            System.out.println(e.getMessage());
        }
    }

    // Hacemos un método para buscar la imagen más popular
    public static void buscarImagenesPopulares() throws ObraException {

        int mayor = -1;
        int indice = -1;

        for (int i = 0; i < obras.length; i++) {
            if (obras[i] instanceof ImagenDigital imagenDigital) {
                if (mayor < imagenDigital.getVisitasRegistradas()) {
                    mayor = imagenDigital.getVisitasRegistradas();
                    indice = i;
                }
            }
        }
        if (indice == -1) {
            throw new ObraException("No se han econtrado imagenes populares");
        }
        System.out.println("La imagen más popular es " + obras[indice].getTitulo());
    }

    // Hacemos un método para buscar el modelo 3D más popular
    public static void buscarModelosPopulares() throws ObraException {

        int mayor = -1;
        int indice = -1;

        for (int i = 0; i < obras.length; i++) {
            if (obras[i] instanceof Modelo3D modelo3D) {
                if (mayor < modelo3D.getNumInteracciones()) {
                    mayor = modelo3D.getNumInteracciones();
                    indice = i;
                }
            }
        }
        if (indice == -1) {
            throw new ObraException("No se han econtrado modelos 3D populares");
        }
        System.out.println("El modelo 3D más popular es " + obras[indice].getTitulo());
    }
}