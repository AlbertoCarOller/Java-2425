package Extra.ExamenCollections2025;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

public class Personaje {

    // Creamos los atributos
    private String nombre;
    private TRaza raza;
    private int nivelVida;
    private int nivelKi;
    private Set<Ataque> ataques;
    private final int VIDA_MAX;
    private final int KI_MAX;

    // Creamos el constructor
    public Personaje(String nombre, TRaza raza, int VIDA_MAX, int nivelVida, int KI_MAX, int nivelKi) throws DBException {
        this.nombre = nombre.toLowerCase();
        this.raza = raza;
        this.VIDA_MAX = VIDA_MAX;
        setNivelVida(nivelVida);
        this.KI_MAX = KI_MAX;
        setNivelKi(nivelKi);
        this.ataques = new LinkedHashSet<>();
    }

    // Hacemos los get y set
    public String getNombre() {
        return nombre;
    }

    public TRaza getRaza() {
        return raza;
    }

    public void setNivelVida(int nivelVida) throws DBException {
        if (nivelVida > this.VIDA_MAX || nivelVida < 0) {
            throw new DBException("La vida no es válida");
        }
        this.nivelVida = nivelVida;
    }

    public void setNivelKi(int nivelKi) throws DBException {
        if (nivelKi > this.KI_MAX || nivelKi < 0) {
            throw new DBException("El nivel del ki no es válido");
        }
        this.nivelKi = nivelKi;
    }

    public Set<Ataque> getAtaques() {
        return ataques;
    }

    public int getNivelVida() {
        return nivelVida;
    }

    public int getNivelKi() {
        return nivelKi;
    }

    // Hacemos un equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Personaje personaje = (Personaje) o;
        return Objects.equals(nombre, personaje.nombre) && raza == personaje.raza;
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, raza);
    }

    // Hacemos un toString
    @Override
    public String toString() {
        return String.format("Nombre: %s, Raza: %s, Max vida: %s,Nivel de vida: %d, Max ki: %s, Nivel Ki: %d, Ataques: %s", this.nombre,
                this.raza, this.VIDA_MAX, this.nivelVida, this.KI_MAX, this.nivelKi, this.ataques);
    }

    /**
     * Hacemos un método para añadir un ataque
     */
    public void addAtaque(Ataque ataque) throws DBException {
        if (!ataques.add(ataque)) {
            throw new DBException("El ataque ya existe");
        }
    }
}