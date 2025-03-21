package ExamenInstrumentos;

import java.util.Objects;

public abstract class Instrumento implements Afinable, Tocable {

    // Creamos los atributos
    private String nombre;
    private Material material;
    private Familia familia;
    private boolean afinado;

    // Creamos el constructor
    public Instrumento(String nombre, Material material, Familia familia, boolean afinado) {
        this.nombre = nombre;
        this.material = material;
        this.familia = familia;
        this.afinado = afinado;
    }

    // Hacemos los get y set
    public String getNombre() {
        return nombre;
    }

    private void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public Familia getFamilia() {
        return familia;
    }

    public void setFamilia(Familia familia) {
        this.familia = familia;
    }

    public boolean isAfinado() {
        return afinado;
    }

    public void setAfinado(boolean afinado) {
        this.afinado = afinado;
    }

    // Hacemos un equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Instrumento that = (Instrumento) o;
        return Objects.equals(nombre, that.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(nombre);
    }

    // Hacemos un toString
    @Override
    public String toString() {
        return String.format("Nombre: %s, ExamenInstrumentos.Material: %s, ExamenInstrumentos.Familia: %s, Afinado: %b", this.nombre, this.material, this.familia, this.afinado);
    }
}