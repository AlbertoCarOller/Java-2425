import java.util.Objects;

public class Equipo {

    // Atributos
    private String paisRepresenta;
    private String nombreEntrenador;
    private Jugador[] listaJugadores;

    // Creamos una variable para contar el número de goles total del equipo
    private int numGolesTotal;

    // Hacemos el constructor
    public Equipo(String paisRepresenta, String nombreEntrenador) {
        this.paisRepresenta = paisRepresenta;
        this.nombreEntrenador = nombreEntrenador;
        this.listaJugadores = new Jugador[11];
    }

    // Hacemos un método para añadir un jugador a la lista de jugadores
    public void anadirJugador(Jugador jugador) throws EquipoException {

        int hayEspacio = -1;
        boolean esDistinto = true;

        for (int i = 0; i < listaJugadores.length; i++) {

            if (listaJugadores[i] == null && hayEspacio == -1) {
                hayEspacio = i;
            }

            if (jugador.equals(listaJugadores[i])) {
                esDistinto = false;
            }
        }

        if (hayEspacio != -1 && esDistinto) {
            listaJugadores[hayEspacio] = jugador;

        } else if (!esDistinto) {
            throw new EquipoException("El jugador ya existe en la plantilla");

        } else {
            throw new EquipoException("La plantilla ya está completa");
        }
    }

    // Hacemos un método para eliminar un jugador de la plantilla
    public void eliminarJugadorPlantilla(Jugador jugador) throws EquipoException {

        boolean encontrado = false;

        for (int i = 0; i < listaJugadores.length; i++) {

            if (listaJugadores[i].equals(jugador)) {
                listaJugadores[i] = null;
                encontrado = true;
            }
        }
        if (!encontrado) {
            throw new EquipoException("El jugador no se ha encontrado en la plantilla");
        }
    }

    // Hacemos el get para obtener el nombre del entrenador del equipo
    public String getNombreEntrenador() {
        return nombreEntrenador;
    }

    // Hacemos un método para contar el número total de goles del equipo
    public void totalGoles() {

        for (int i = 0; i < listaJugadores.length; i++) {

            if (listaJugadores[i] != null) {
                numGolesTotal += listaJugadores[i].getNumGolesMarcados();
            }
        }
        System.out.println("El equipo tiene " + numGolesTotal + " goles");
    }

    // Hacemos un get para obtener el número de goles total del equipo
    public int getNumGolesTotal() {
        return numGolesTotal;
    }

    // Hacemos un equals para comparar los nombres de los entrenadores de los equipos
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Equipo equipo = (Equipo) o;
        return Objects.equals(nombreEntrenador, equipo.nombreEntrenador);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(nombreEntrenador);
    }

    // Hacemos get de la lista de jugadores del equipo
    public Jugador[] getListaJugadores() {
        return listaJugadores;
    }

    // Hacemos un toString
    @Override
    public String toString() {
        return "Equipo{" +
                "paisRepresenta='" + paisRepresenta + '\'' +
                ", nombreEntrenador='" + nombreEntrenador + '\'' +
                '}';
    }
}