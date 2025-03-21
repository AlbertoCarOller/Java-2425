package Ejercicio16;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Laboratorio {

    // Creamos las características
    private String nombre;
    private List<Equipo> equipos;

    // Creamos el constructor
    public Laboratorio(String nombre) {
        this.nombre = nombre;
        this.equipos = new ArrayList<>();
    }

    // Hacemos los get y set
    public String getNombre() {
        return nombre;
    }

    private void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Equipo> getEquipos() {
        return equipos;
    }

    private void setEquipos(List<Equipo> equipos) {
        this.equipos = equipos;
    }

    // Hacemos un equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Laboratorio that = (Laboratorio) o;
        return Objects.equals(nombre, that.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(nombre);
    }

    // Hacemos un toString
    @Override
    public String toString() {
        return String.format("Nombre: %s, Equipos: %s", this.nombre, this.equipos);
    }

    // Hacemos un método para registrar un equipo en el laboratorio
    public void registrarEquipo(Equipo equipo) throws EquipoException {
        if (equipo instanceof EquipoAutorizable equipoAutorizable) {
            if (!equipoAutorizable.isAutorizacion()) {
                throw new EquipoException("No puedes añadir el equipo con id " + equipoAutorizable.getId() + " porque no tiene autorización");
            }
        }
        if (equipo instanceof EquipoAutorizableMantenible equipoAutorizableMantenible) {
            if (!equipoAutorizableMantenible.isAutorizacion()) {
                throw new EquipoException("No puedes añadir el equipo con id " + equipoAutorizableMantenible.getId() +
                        " porque no tiene autorización");
            }
        }
        boolean esIgual = false;
        for (Equipo equipoRecorrido : equipos) {
            if (equipo.equals(equipoRecorrido)) {
                esIgual = true;
            }
        }
        if (esIgual) {
            throw new EquipoException("El equipo ya está añadido");
        }
        equipos.add(equipo);
    }
}