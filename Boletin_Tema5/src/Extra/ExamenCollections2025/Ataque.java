package Extra.ExamenCollections2025;

import java.util.Objects;

public class Ataque {
    // Creamos los atributos
    private String nombre;
    private int kiNecesario;
    private int nivelPerfeccion;
    private int dano;

    // Creamos el constructor
    public Ataque(String nombre, int kiNecesario, int nivelPerfeccion, int dano) throws DBException {
        this.nombre = nombre;
        this.kiNecesario = kiNecesario;
        setNivelPerfeccion(nivelPerfeccion);
        setDano(dano);
    }

    // Hacemos los get y set
    public String getNombre() {
        return nombre;
    }

    public int getNivelPerfeccion() {
        return nivelPerfeccion;
    }

    public void setNivelPerfeccion(int nivelPerfeccion) throws DBException {
        if (nivelPerfeccion < 1 || nivelPerfeccion > 3) {
            throw new DBException("El nivel de perfección no es válido");
        }
        this.nivelPerfeccion = nivelPerfeccion;
    }

    public int getDano() {
        return dano;
    }

    public void setDano(int dano) throws DBException {
        if (dano < 1) {
            throw new DBException("El daño no es válido");
        }
        this.dano = dano;
    }

    public int getKiNecesario() {
        return kiNecesario;
    }

    // Hacemos un equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ataque ataque = (Ataque) o;
        return nivelPerfeccion == ataque.nivelPerfeccion && dano == ataque.dano && Objects.equals(nombre, ataque.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, nivelPerfeccion, dano);
    }

    // Hacemos un toString
    @Override
    public String toString() {
        return String.format("Nombre: %s, Ki necesario: %d, Nivel perfección: %d, Daño: %d", this.nombre, this.kiNecesario,
                this.nivelPerfeccion, this.dano);
    }
}