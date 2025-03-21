package Extra.Ejercicio3;

import java.time.LocalDate;

public class AvistamientoApp {
    public static void main(String[] args) {
        try {
            // Creamos el centro de investigación
            CentroInvestigacion centroInvestigacion = new CentroInvestigacion("Chelu´s investigation");
            // Añadimos distintas especies
            centroInvestigacion.anadirEspecie("Cheloupard");
            centroInvestigacion.anadirEspecie("Ahelu´s lion");
            centroInvestigacion.anadirEspecie("Chelu´s tiger");
            // Creamos avistamientos
            Avistamiento avistamiento1 = new Avistamiento("Cheloupard", "Lepe", LocalDate.of
                    (2010, 3, 19));
            Avistamiento avistamiento2 = new Avistamiento("Cheloupard", "Villablanca", LocalDate.of
                    (2010, 1, 20));
            Avistamiento avistamiento3 = new Avistamiento("Ahelu´s lion", "Villalba", LocalDate.of
                    (2019, 6, 20));
            Avistamiento avistamiento4 = new Avistamiento("Ahelu´s lion", "Lepe", LocalDate.of
                    (2009, 10, 23));
            // Añadimos los avistamientos
            centroInvestigacion.anadirAvistamiento(avistamiento1);
            centroInvestigacion.anadirAvistamiento(avistamiento2);
            centroInvestigacion.anadirAvistamiento(avistamiento3);
            centroInvestigacion.anadirAvistamiento(avistamiento4);
            // Obtenemos una lista con las especies encontradas en el lugar introducido
            System.out.println(centroInvestigacion.avistamientosEnUbicacion("Lepe"));
            // Obtenemos una lista de las especies ordenadas alfabéticamente
            System.out.println(centroInvestigacion.ordenarAlfabeticamente());
            // Obtenemos la especie con más avistamientos o la descubierta antes
            System.out.println("La especie con más avistamientos o descubierta antes es " + centroInvestigacion.especieConMasAvistamientos());


        } catch (AvistamientoException e) {
            System.out.println(e.getMessage());
        }
    }
}