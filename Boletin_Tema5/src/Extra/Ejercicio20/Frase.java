package Extra.Ejercicio20;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Frase {

    // Creamos los atributos
    private String texto;
    private String pelicula;
    private String actor;
    private LocalDate fechaIncorporacion;
    private int valoracion;

    // Creamos el cosntructor
    public Frase(String texto, String pelicula, String actor, LocalDate fechaIncorporacion, int valoracion) throws FraseException {
        setTexto(texto);
        setPelicula(pelicula);
        setActor(actor);
        setFechaIncorporacion(fechaIncorporacion);
        setValoracion(valoracion);
    }

    // Hacemos los get y set
    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) throws FraseException {
        for (int i = 0; i < texto.length(); i++) {
            if (!Character.isLetter(texto.charAt(i)) && !Character.isSpaceChar(texto.charAt(i))) {
                throw new FraseException("El caracter " + texto.charAt(i) + " no está permitido");
            }
        }
        if (texto.isBlank()) {
            throw new FraseException("La frase no puede estar compuesta solo por espacios");
        }
        this.texto = texto;
    }

    public String getPelicula() {
        return pelicula;
    }

    public void setPelicula(String pelicula) throws FraseException {
        if (!Character.isUpperCase(pelicula.charAt(0))) {
            throw new FraseException("La película debe empezar en mayúsculas");
        }
        for (int i = 1; i < pelicula.length(); i++) {
            if (!Character.isLetter(pelicula.charAt(i)) && !Character.isSpaceChar(pelicula.charAt(i))) {
                throw new FraseException("El caracter " + texto.charAt(i) + " no está permitido");
            }
            if (pelicula.isBlank()) {
                throw new FraseException("La frase no puede estar compuesta solo por espacios");
            }
        }
        this.pelicula = pelicula;
    }

    public String getActor() {
        return actor;
    }

    public void setActor(String actor) throws FraseException {
        if (!Character.isUpperCase(actor.charAt(0))) {
            throw new FraseException("La película debe empezar en mayúsculas");
        }
        for (int i = 1; i < actor.length(); i++) {
            if (!Character.isLetter(actor.charAt(i)) && !Character.isSpaceChar(actor.charAt(i))) {
                throw new FraseException("El caracter " + texto.charAt(i) + " no está permitido");
            }
            if (actor.isBlank()) {
                throw new FraseException("La frase no puede estar compuesta solo por espacios");
            }
        }
        this.actor = actor;
    }

    public LocalDate getFechaIncorporacion() {
        return fechaIncorporacion;
    }

    public void setFechaIncorporacion(LocalDate fechaIncorporacion) throws FraseException {
        if (fechaIncorporacion.isAfter(LocalDate.now())) {
            throw new FraseException("La fecha no puede ser posterior a la actual");
        }
        this.fechaIncorporacion = fechaIncorporacion;
    }

    public int getValoracion() {
        return valoracion;
    }

    public void setValoracion(int valoracion) throws FraseException {
        if (valoracion < 0 || valoracion > 5) {
            throw new FraseException("La valoración debe estar entre 0 y 5");
        }
        this.valoracion = valoracion;
    }

    // Hacemos un equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Frase frase = (Frase) o;
        return Objects.equals(texto, frase.texto) && Objects.equals(pelicula, frase.pelicula);
    }

    @Override
    public int hashCode() {
        return Objects.hash(texto, pelicula);
    }

    // Hacemos un toString
    @Override
    public String toString() {
        return String.format("Texto: %s, Película: %s, Actor: %s, Fecha incorporación: %s, Valoración: %d",
                this.texto, this.pelicula, this.actor, this.fechaIncorporacion.format(DateTimeFormatter.
                        ofPattern("yyyy/MM/dd")), this.valoracion);
    }
}