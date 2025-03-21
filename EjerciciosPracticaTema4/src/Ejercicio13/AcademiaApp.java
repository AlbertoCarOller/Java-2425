package Ejercicio13;

import java.time.LocalDate;

public class AcademiaApp {
    public static void main(String[] args) {
        try {
            Academia academia = new Academia("Chelu´s academy");
            Caso caso1 = new Caso("Caso Chelu desaparecido", Dificultad.MEDIO);
            Caso caso2 = new Caso("Caso Sensei Vallejo", Dificultad.ALTO);
            Caso caso3 = new Caso("Caso Atisbedo", Dificultad.BAJO);
            Caso caso4 = new Caso("Caso Respicio Godefrio", Dificultad.MEDIO);

            DetectiveExperto detectiveExperto1 = new DetectiveExperto("Chelu", LocalDate.of(2023, 3, 3));
            DetectivePrincipiante detectivePrincipiante1 = new DetectivePrincipiante("Vallejo", LocalDate.of(2020, 6, 13));
            DetectivePrincipiante detectivePrincipiante2 = new DetectivePrincipiante("Atisbedo", LocalDate.of(2013, 9, 23));

            academia.anadirDetective(detectiveExperto1);
            academia.anadirDetective(detectivePrincipiante1);

            academia.asignarCaso(caso1, "Chelu");
            detectiveExperto1.resolver(caso1, academia);
            academia.asignarCaso(caso2, "Chelu");
            academia.asignarCaso(caso3, "Vallejo");
            detectivePrincipiante2.resolver(caso4, academia);

        } catch (DetectiveException e) {
            System.out.println(e.getMessage());
        }
    }
}