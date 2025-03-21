package Boletin1.Ejercicio7;

import java.util.*;

public class Receta implements Comparable<Receta> {

    // Creamos los atributos
    private String nombre;
    private int tiempoPreparacion;
    private Set<Ingrediente> ingredientes;
    private List<String> pasos;

    // Creamos el constructor
    public Receta(String nombre, int tiempoPreparacion) {
        this.nombre = nombre;
        this.tiempoPreparacion = tiempoPreparacion;
        this.ingredientes = new HashSet<>();
        this.pasos = new LinkedList<>();
    }

    // Hacemos los get y set
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getTiempoPreparacion() {
        return tiempoPreparacion;
    }

    public void setTiempoPreparacion(int tiempoPreparacion) {
        this.tiempoPreparacion = tiempoPreparacion;
    }

    public Set<Ingrediente> getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(Set<Ingrediente> ingredientes) {
        this.ingredientes = ingredientes;
    }

    public List<String> getPasos() {
        return pasos;
    }

    public void setPasos(List<String> pasos) {
        this.pasos = pasos;
    }

    // Hacemos un método para saber si una receta ya tiene un ingrediente
    public boolean necesitaIngrediente(String nombreIngrediente) throws IngredienteException {
        if (ingredientes.isEmpty()) {
            throw new IngredienteException("No hay ingredientes todavía");
        }
        for (Ingrediente ingrediente : ingredientes) {
            if (ingrediente.getNombre().equalsIgnoreCase(nombreIngrediente)) {
                return true;
            }
        }
        return false;
    }

    // Hacemos un método para añadir un ingrediente a una receta
    public void anadirIngrediente(Ingrediente ingrediente) throws IngredienteException {
        if (ingredientes.contains(ingrediente)) {
            Iterator<Ingrediente> it = ingredientes.iterator();
            while (it.hasNext()) {
                Ingrediente i = it.next();
                if (ingrediente.equals(i)) {
                    i.setCantidad(i.getCantidad() + 1);
                    break;
                }
            }

        } else {
            ingredientes.add(ingrediente);
        }
    }

    // Hacemos un método para borrar un ingrediente y los pasos que nombren al ingrediente
    public void borrarIngrediente(Ingrediente ingrediente) throws IngredienteException {
        if (ingredientes.contains(ingrediente)) {
            ingredientes.remove(ingrediente);

            if (pasos.contains(ingrediente.getNombre())) {
                Iterator<String> it2 = pasos.iterator();
                while (it2.hasNext()) {
                    if (ingrediente.getNombre().equalsIgnoreCase(it2.next())) {
                        it2.remove();
                    }
                }
            }
        } else {
            throw new IngredienteException("No se ha encontrado el ingrediente");
        }
    }

    // Hacemos un método para añadir un paso detrás de otro
    public void anadirPasoDetrasDe(String pasoNuevo, String pasoExistente) throws IngredienteException {
        if (pasos.contains(pasoExistente)) {
            int indicePasoExistente = pasos.indexOf(pasoExistente);
            pasos.add(indicePasoExistente + 1, pasoNuevo);

        } else {
            throw new IngredienteException("No se ha encontrado el paso existente");
        }
    }

    // Hacemos un toString
    @Override
    public String toString() {
        return String.format("Nombre: %s, tiempo preparación: %d", this.nombre, this.tiempoPreparacion);
    }

    // Implementamos el 'compareTo' ascendente
    @Override
    public int compareTo(Receta o) {
        return this.tiempoPreparacion - o.tiempoPreparacion;
    }
}