import java.util.Objects;

public abstract class Juguete {

    // Creamos los atributos
    private String nombre;
    private String marca;

    // Hacemos el constructor
    public Juguete(String nombre, String marca) {
        this.nombre = nombre;
        this.marca = marca;
    }

    // Creamos los get y set
    public String getNombre() {
        return nombre;
    }

    private void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMarca() {
        return marca;
    }

    private void setMarca(String marca) {
        this.marca = marca;
    }

    // Hacemos el equals, serán iguales si tienen el mismo nombre
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Juguete juguete = (Juguete) o;
        return Objects.equals(nombre, juguete.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(nombre);
    }
}