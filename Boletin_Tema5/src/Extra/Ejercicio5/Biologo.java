package Extra.Ejercicio5;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Biologo {

    // Creamos los atributos
    private String nombre;
    private List<Animal> animales;

    // Creamos el constructor
    public Biologo(String nombre) {
        this.nombre = nombre;
        this.animales = new ArrayList<>();
    }

    // Creamos los get y set
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Animal> getAnimales() {
        return animales;
    }

    private void setAnimales(List<Animal> animales) {
        this.animales = animales;
    }

    // Hacemos un equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Biologo biologo = (Biologo) o;
        return Objects.equals(nombre, biologo.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(nombre);
    }

    // Hacemos un toString
    @Override
    public String toString() {
        return String.format("Nombre: %s, Animales: %s", this.nombre, this.animales);
    }

    // Hacemos un método para registrar un animal
    public void registrarAnimal(Animal animal) throws AnimalException {
        for (Animal animalRecorrido : animales) {
            if (animal.getZonaReserva().equalsIgnoreCase(animalRecorrido.getZonaReserva()) && animal.equals(animalRecorrido)) {
                throw new AnimalException("Ya has registrado el mismo animal en la misma zona antes");
            }
        }
        animales.add(animal);
    }
}