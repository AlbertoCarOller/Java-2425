package Extra.Ejercicio18;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class AlumnoMusical {

    // Creamos los atributos
    private String nombre;
    private String instrumento;
    private List<Audicion> audiciones;

    // Creamos el constructor
    public AlumnoMusical(String nombre, String instrumento) throws EstudianteMusicalException {
        setNombre(nombre);
        setInstrumento(instrumento);
        this.audiciones = new ArrayList<>();
    }

    // Hacemos los get y set
    public String getNombre() {
        return nombre;
    }

    private void setNombre(String nombre) throws EstudianteMusicalException {
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

    public String getInstrumento() {
        return instrumento;
    }

    public void setInstrumento(String instrumento) throws EstudianteMusicalException {
        if (!Character.isUpperCase(instrumento.charAt(0))) {
            throw new EstudianteMusicalException("La primera letra debe estar en mayúsculas");
        }
        for (int i = 1; i < instrumento.length(); i++) {
            if (!Character.isLowerCase(instrumento.charAt(i))) {
                throw new EstudianteMusicalException("El resto de caracteres deben estar en minúscula y ser letras");
            }
            this.instrumento = instrumento;
        }
    }

    public List<Audicion> getAudiciones() {
        return audiciones;
    }

    public void setAudiciones(List<Audicion> audiciones) {
        this.audiciones = audiciones;
    }

    // Hacemos un equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AlumnoMusical that = (AlumnoMusical) o;
        return Objects.equals(nombre, that.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(nombre);
    }

    // Hacemos un toString
    @Override
    public String toString() {
        return String.format("Nombre: %s, Instrumento: %s, Audiciones: %s", this.nombre, this.instrumento,
                this.audiciones);
    }

    // Hacemos un método para obtener el último puntuaje del alumno (método auxiliar)
    public double obtenerUltimoPuntuaje() {
        return audiciones.stream().sorted(Comparator.comparing(Audicion::getFecha).reversed())
                .map(Audicion::getPuntuaje).findFirst().orElse(0.0);
    }
}