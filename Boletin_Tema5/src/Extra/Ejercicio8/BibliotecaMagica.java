package Extra.Ejercicio8;

import java.util.*;

public class BibliotecaMagica {

    // Creamos los atributos
    private String nombre;
    private Stack<Grimorio> pilaGrimorios;
    private Stack<Grimorio> listaNegra;

    // Creamos el constructor
    public BibliotecaMagica(String nombre) {
        this.nombre = nombre;
        this.pilaGrimorios = new Stack<>();
        this.listaNegra = new Stack<>();
    }

    // Hacemos los get y set
    public String getNombre() {
        return nombre;
    }

    private void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Stack<Grimorio> getPilaGrimorios() {
        return pilaGrimorios;
    }

    private void setPilaGrimorios(Stack<Grimorio> pilaGrimorios) {
        this.pilaGrimorios = pilaGrimorios;
    }

    public Stack<Grimorio> getListaNegra() {
        return listaNegra;
    }

    private void setListaNegra(Stack<Grimorio> listaNegra) {
        this.listaNegra = listaNegra;
    }

    // Hacemos un equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BibliotecaMagica that = (BibliotecaMagica) o;
        return Objects.equals(nombre, that.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(nombre);
    }

    // Hacemos un toString
    @Override
    public String toString() {
        return String.format("Nombre: %s, Pila de grimorios: %s, Lista negra: %s", this.nombre, this.pilaGrimorios, this.listaNegra);
    }

    // Hacemos un método para registrar grimorios
    public void registrarGrimorio(Grimorio grimorio) throws GrimorioException {
        if (listaNegra.contains(grimorio) || pilaGrimorios.contains(grimorio)) {
            throw new GrimorioException("El grimorio ya está registrado");
        }
        if (grimorio.isPeligroso()) {
            listaNegra.add(grimorio);

        } else {
            pilaGrimorios.add(grimorio);
        }
    }

    // Hacemos un método para consultar un grimorio
    public void consultarGrimorio() throws GrimorioException {
        if (pilaGrimorios.isEmpty()) {
            throw new GrimorioException("No hay grimorios a los que consultar");
        }
        System.out.println("\nConsultando pila de grimorios...");
        Random probabilidadCambiante = new Random();
        if (probabilidadCambiante.nextBoolean()) {
            Grimorio grimorioConsultado = pilaGrimorios.pop();
            grimorioConsultado.setPeligroso(true);
            registrarGrimorio(grimorioConsultado);
            System.out.println(grimorioConsultado.getNombre() + " ha sido consultado y es peligroso");

        } else {
            System.out.println(pilaGrimorios.peek().getNombre() + " ha sido consultado y no es peligroso");
        }
    }

    // Hacemos un método para consultar un grimorio de la lista negra
    public void consultarListaNegra() throws GrimorioException {
        if (listaNegra.isEmpty()) {
            throw new GrimorioException("La lista negra de grimorios está vacía");
        }
        System.out.println("\nConsultando lista negra...");
        Random probabilidadCambiante = new Random();
        if (probabilidadCambiante.nextBoolean()) {
            Grimorio grimorioConsultado = listaNegra.pop();
            grimorioConsultado.setPeligroso(false);
            registrarGrimorio(grimorioConsultado);
            System.out.println(grimorioConsultado.getNombre() + " ha sido consultado y no es peligroso");

        } else {
            System.out.println(listaNegra.peek().getNombre() + " ha sido consultado y sigue siendo peligroso");
        }
    }

    // Hacemos un método para mostrar la pila de grimorios que empiece por una palabra
    public void mostrarPila(char letra) {
        pilaGrimorios.stream().filter(g -> g.getNombre().charAt(0) == Character.toUpperCase(letra))
                .forEach(System.out::println);
    }

    // Hacemos un método para mostrar la lista negra ordenada alfabéticamente
    public void mostrarListaNegra() {
        listaNegra.stream().sorted(Comparator.comparing(Grimorio::getNombre)).forEach(System.out::println);
    }

    // Hacemos un método que va a devolver el primer grimorio que empiece por "S" de la lista que quieras
    public Grimorio grimorioPorS(Stack<Grimorio> lista) throws GrimorioException {
        return lista.stream().filter(g -> g.getNombre().charAt(0) == 'S').findFirst()
                .orElseThrow(() -> new GrimorioException("No se ha encontrado ninguno"));
    }
}