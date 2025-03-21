package Boletin2.Partido;

import utils.MiEntradaSalida;

public class PartidoApp {
    public static void main(String[] args) {

        // Creamos los partido
        Partido p;
        Partido p2;
        Partido p3;

        //Creamos los equipos y sus atributos
        Equipo equipo1;
        Equipo equipo2;
        Equipo equipo3;

        // Hacemos que se repita hasta que se haya jugado el partido
        int op;
        try {

            // Definimos los atributos de los equipos
            equipo1 = new Equipo("Real Betis Balompie");
            equipo2 = new Equipo("Sevilla F.C.");
            equipo3 = new Equipo("Rayo Vallecano");

            // Definimos los atributos del partido
            p = new Partido(7, "Benito Villamarín", equipo1, equipo2);

            // Creamos el resultado del partido, se lo pasamos por parámetros al método
            op = MiEntradaSalida.solicitarEnteroPositivo("Pulsa 1 para jugar el primer partido");
            if (op == 1) {

                p.ponerResultado("2-1");
            }

            // Mostramos la información
            System.out.println(p);

            int op2;

            p2 = new Partido(12, "Estadio de Vallecas", equipo3, equipo2);

            op2 = MiEntradaSalida.solicitarEnteroPositivo("Pulsa 1 para jugar el segundo partido");
            if (op2 == 1) {

                p2.ponerResultado("0-0");
            }

            System.out.println(p2);

            int op3;

            p3 = new Partido(24, "Estadio de Vallecas", equipo3, equipo1);

            op3 = MiEntradaSalida.solicitarEnteroPositivo("Pulsa 1 para jugar el tercer partido");
            if (op3 == 1) {

                p3.ponerResultado("1-2");
            }

            System.out.println(p3);

        } catch (EquipoException e) {
            System.out.println(e.getMessage());
        }
    }
}