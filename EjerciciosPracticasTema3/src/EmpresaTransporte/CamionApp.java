package EmpresaTransporte;

import java.time.LocalDate;
import java.time.Period;

public class CamionApp {
    public static void main(String[] args) {

        try {
            // Hacemos un camión
            Camion camion = new Camion("Apple", "Atisbedo", 2);
            Revision revision1 = new Revision(LocalDate.of(2024, 6, 2), true, Period.ofDays(250));
            Revision revision2 = new Revision(LocalDate.of(2025, 1, 1), true, Period.ofDays(150));
            camion.anadirPeriodo(revision1);
            camion.realizarViaje(50);
            camion.rellenarDeposito();
            camion.anadirPeriodo(revision2);
            camion.realizarViaje(50);
            System.out.println(camion);

        } catch (CamionException e) {
            System.out.println(e.getMessage());
        }
    }
}