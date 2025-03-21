package Avion;

import java.time.LocalDate;
import java.time.Period;

public class AvionApp {
    public static void main(String[] args) {

        try {
            Avion avion = new Avion("Apple", "Atisbedo", 2);
            Revision revision1 = new Revision(LocalDate.of(2024, 11, 23), "correcto", Period.ofDays(250));
            Revision revision2 = new Revision(LocalDate.of(2025, 1, 6), "correcto", Period.ofDays(250));
            avion.hacerRevision(revision1);
            avion.comprobarUltimaRevision(200);
            avion.rellenarDiposito();
            System.out.println(avion.getDeposito());
            avion.hacerRevision(revision2);
            avion.comprobarUltimaRevision(100);
            System.out.println(avion);

        } catch (AvionExcepcion e) {
            System.out.println(e.getMessage());
        }
    }
}
