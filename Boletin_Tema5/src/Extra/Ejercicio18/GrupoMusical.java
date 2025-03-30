package Extra.Ejercicio18;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GrupoMusical {

    // Creamos los atributos
    private String nombre;
    private List<AlumnoMusical> alumnos;

    // Creamos el constructor
    public GrupoMusical(String nombre) {
        this.nombre = nombre;
        this.alumnos = new ArrayList<>();
    }

    // Hacemos los get y set
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) throws EstudianteMusicalException {
        if (!Character.isUpperCase(nombre.charAt(0))) {
            throw new EstudianteMusicalException("La primera letra debe estar en mayúsculas");
        }
        for (int i = 1; i < nombre.length(); i++) {
            if (!Character.isLowerCase(nombre.charAt(i))) {
                throw new EstudianteMusicalException("El resto de caracteres deben estar en minúscula y ser letras");
            }
        }
        this.nombre = nombre;
    }

    public List<AlumnoMusical> getAlumnos() {
        return alumnos;
    }

    // Hacemos un equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GrupoMusical that = (GrupoMusical) o;
        return Objects.equals(nombre, that.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(nombre);
    }

    // Hacemos un toString
    @Override
    public String toString() {
        return String.format("Nombre: %s, Alumnos: %s", this.nombre, this.alumnos);
    }
}