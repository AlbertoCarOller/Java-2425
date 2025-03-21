package Extra.Ejercicio5;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AnimalApp {
    static Set<Biologo> biologos = new HashSet<>();

    public static void main(String[] args) {
        try {
            // Creamos biólogos
            Biologo biologo = new Biologo("Doctor Chelu");
            Biologo biologo1 = new Biologo("Doctor Atisbedo");
            // Creamos animales
            Animal animal = new Animal("Chelu´s lion", LocalDate.of(2020, 10, 8), "Lepe");
            Animal animal1 = new Animal("Bellingham", LocalDate.of(2025, 2, 12), "Illuniom");
            Animal animal2 = new Animal("Bellingham", LocalDate.of(2019, 12, 26), "Lepe");
            Animal animal3 = new Animal("El Chico Malote", LocalDate.of(2022, 9, 4), "Lepe");
            Animal animal4 = new Animal("Papito lindo", LocalDate.of(2023, 7, 14),
                    "Isla Cristina");
            // Añadimos los animales a los biólogos
            biologo.registrarAnimal(animal);
            biologo.registrarAnimal(animal1);
            biologo.registrarAnimal(animal2);
            biologo1.registrarAnimal(animal3);
            biologo1.registrarAnimal(animal4);
            // Añadimos los biólogos al conjunto de biólogos
            biologos.add(biologo);
            biologos.add(biologo1);
            System.out.println(animalesDeZona("Lepe"));
            System.out.println(zonasEspecie("Bellingham"));


        } catch (AnimalException e) {
            System.out.println(e.getMessage());
        }
    }

    // Creamos un método que nos va a devolver una lista de animales de una determinada zona ordenados alfabéticamente
    public static List<Animal> animalesDeZona(String zona) {
        return biologos.stream().flatMap(b -> b.getAnimales().stream())
                .filter(a -> zona.equalsIgnoreCase(a.getZonaReserva()))
                .sorted(Comparator.comparingInt(a -> Character.toUpperCase(a.getNombreEspecie().charAt(0)))).toList();
    }

    // Creamos un método que nos va a devolver una lista de todas las zonas donde ha sido vista una especie
    public static List<String> zonasEspecie(String nombreEspecie) {
        return biologos.stream().flatMap(a -> a.getAnimales().stream()).filter(a -> nombreEspecie.equalsIgnoreCase(a.getNombreEspecie())).
                map(Animal::getZonaReserva).toList();
    }
}