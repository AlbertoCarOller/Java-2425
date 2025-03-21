import java.util.Objects;

public class Jugador {

    // Atributos
    private String nombre;
    private int edad;
    private int numGolesMarcados;

    // Hacemos el constructor
    public Jugador(String nombre, int edad, int numGolesMarcados) {
        this.nombre = nombre;
        this.edad = edad;
        this.numGolesMarcados = numGolesMarcados;
    }

    // Añadir un gol a la lista de goles marcados
    public void setNumGolesMarcados(int numGolesMarcados) {
        this.numGolesMarcados = numGolesMarcados;
    }

    // Obtener el número total de goles marcados
    public int getNumGolesMarcados() {
        return numGolesMarcados;
    }

    // Hacemos un equals para comparar los nombres de los jugadores
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Jugador jugador = (Jugador) o;
        return Objects.equals(nombre, jugador.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(nombre);
    }

    // Hacemos un get para saber el nombre del jugador
    public String getNombre() {
        return nombre;
    }
}