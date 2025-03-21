package Gimnasio;

import java.util.Objects;

public class Actividad {

    // Creamos los atributos
    private String nombre;
    private int duracionEstimada;
    private String nivelIntensidad;
    private boolean completada;

    // Creamos el constructor
    public Actividad(String nombre, int duracionEstimada, String nivelIntensidad) throws GimnasioException {
        this.nombre = nombre;
        setDuracionEstimada(duracionEstimada);
        setNivelIntensidad(nivelIntensidad);
        this.completada = false;
    }

    // Hacemos los get y set
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getDuracionEstimada() {
        return duracionEstimada;
    }

    public void setDuracionEstimada(int duracionEstimada) throws GimnasioException {
        if (duracionEstimada < 5 || duracionEstimada > 20) {
            throw new GimnasioException("La duracíon no puede ser menor a 5 ni mayor a 20 minutos");
        }
        this.duracionEstimada = duracionEstimada;
    }

    public String getNivelIntensidad() {
        return nivelIntensidad;
    }

    public void setNivelIntensidad(String nivelIntensidad) throws GimnasioException {

        if (!nivelIntensidad.equalsIgnoreCase("Bajo") && !nivelIntensidad.equalsIgnoreCase("Medio")
                && !nivelIntensidad.equalsIgnoreCase("Alto")) {
            throw new GimnasioException("El nivel de intensidad no corresponde");
        }
        this.nivelIntensidad = nivelIntensidad;
    }

    public void setCompletada(boolean completada) {
        this.completada = completada;
    }

    public boolean isCompletada() {
        return completada;
    }

    // Hacemos un toString
    @Override
    public String toString() {
        return "Actividad{" +
                "nombre='" + nombre + '\'' +
                ", duracionEstimada=" + duracionEstimada +
                ", nivelIntensidad='" + nivelIntensidad + '\'' +
                ", completada=" + completada +
                '}';
    }

    // Hacemos un equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Actividad actividad = (Actividad) o;
        return Objects.equals(nombre, actividad.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(nombre);
    }
}