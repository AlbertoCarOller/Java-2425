import utils.MiEntradaSalida;

public class MundialApp {
    public static void main(String[] args) {

        // Creamos el objeto mundial
        Mundial mundial = new Mundial();

        int op;

        do {
            System.out.println("1. Crear un nuevo equipo");
            System.out.println("2. Añadir un nuevo jugador a un equipo existente");
            System.out.println("3. Ver el número total de goles marcados por un equipo existente");
            System.out.println("4. Ver el número total de goles marcados por un jugador existente");
            System.out.println("5. Ver el equipo con más goles marcados en el mundial");
            System.out.println("6. Ver el jugador con más goles marcados en el mundial");
            System.out.println("7. Salir");

            op = MiEntradaSalida.solicitarEnteroPositivo("Elija una opción");
            try {
                mundial.menu(op);

            } catch (EquipoException e) {
                System.out.println(e.getMessage());
            }
        } while (op != 7);
    }
}