package Extra.Ejercicio9;

import java.util.*;

public class Agencia {

    // Creamos los atributos
    private String nombre;
    private Map<Cliente, List<Ruta>> clientes;

    // Creamos el constructor
    public Agencia(String nombre) {
        this.nombre = nombre;
        this.clientes = new HashMap<>();
    }

    // Hacemos los get y set
    public String getNombre() {
        return nombre;
    }

    private void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Map<Cliente, List<Ruta>> getClientes() {
        return clientes;
    }

    private void setClientes(Map<Cliente, List<Ruta>> clientes) {
        this.clientes = clientes;
    }

    // Hacemos un equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Agencia agencia = (Agencia) o;
        return Objects.equals(nombre, agencia.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(nombre);
    }

    // Hacemos un toString
    @Override
    public String toString() {
        return String.format("Nombre: %s, Clientes: %s", this.nombre, this.clientes);
    }

    // Hacemos un método para añadir una ruta a un cliente
    public void anadirRuta(Cliente cliente, Ruta ruta) throws ClienteException {
        if (clientes.keySet().stream().filter(cliente::equals)
                .noneMatch(c -> clientes.get(c).contains(ruta))) {
            throw new ClienteException("No se puede añadir la ruta");
        }
        clientes.get(cliente).add(ruta);
    }

    // Hacemos un método para eliminar una ruta a un cliente
    public void eliminarRuta(Cliente cliente, Ruta ruta) throws ClienteException {
        if (clientes.isEmpty()) {
            throw new ClienteException("No hay clientes");
        }
        if (clientes.containsKey(cliente)) {
            int indice = clientes.get(cliente).indexOf(ruta);
            if (indice != -1) {
                clientes.get(cliente).remove(indice);

            } else {
                throw new ClienteException("No se ha encontrado la ruta");
            }

        } else {
            throw new ClienteException("No se ha encontrado el cliente");
        }
    }

    // Hacemos un método para registrar un cliente
    public void registrarCliente(Cliente cliente) throws ClienteException {
        if (clientes.containsKey(cliente)) {
            throw new ClienteException("El cliente ya está registrado");
        }
        clientes.put(cliente, new ArrayList<>());
    }

    // Hacemos un método para buscar una ruta de un cliente y añadirle una parada
    public void anadirParada(Cliente cliente, Ruta ruta, String parada) throws ClienteException {
        Ruta rutaEncontrada = clientes.keySet().stream().filter(cliente::equals)
                .flatMap(c -> clientes.get(c).stream())
                .filter(ruta::equals).findFirst().orElseThrow(() -> new ClienteException("No se ha encontrado la ruta"));
        rutaEncontrada.registrarParada(parada);
    }

    // Hacemos un método para eliminar una parada
    public void eliminarParada(Cliente cliente, Ruta ruta, String parada) throws ClienteException {
        Ruta rutaEncontrada = clientes.keySet().stream().filter(cliente::equals).flatMap(c -> clientes.get(c).stream())
                .filter(ruta::equals).findFirst().orElseThrow(() -> new ClienteException("No se ha encontrado la ruta"));
        rutaEncontrada.eliminarParada(parada);
    }

    // Hacemos un método para mostrar todas las rutas de un cliente
    public void mostrarRutas(Cliente cliente) {
        clientes.keySet().stream()
                .filter(cliente::equals)
                .flatMap(c -> clientes.get(c).stream())
                .sorted(Comparator.comparing(Ruta::getNombre))
                .forEach(System.out::println);
    }

    /* Hacemos un método que va a devolver una lista de todos los clientes que contengan al menos una ruta
     con una parada concreta */
    public List<Cliente> clientesConParada(String parada) throws ClienteException {
        //clientes.values().stream().flatMap(Collection::stream).filter(r -> r.getParadas().contains(parada))
        List<Cliente> clientesList = clientes.keySet().stream().filter(c -> clientes.get(c).stream()
                .flatMap(r -> r.getParadas().stream()).anyMatch(parada::equals)).toList();
        if (clientesList.isEmpty()) {
            throw new ClienteException("No hay clentes que cumplan");
        }
        return clientesList;
    }

    // Hacemos un método para obtener rutas distintas con una misma parada
    public List<Ruta> rutasConParada(String parada) throws ClienteException {
        List<Ruta> rutasList = clientes.values().stream().flatMap(Collection::stream)
                .filter(r -> r.getParadas().stream().anyMatch(parada::equals))
                .distinct().toList();
        if (rutasList.isEmpty()) {
            throw new ClienteException("No hay rutas que cumplan");
        }
        return rutasList;
    }

    // Hacemos un método que va a devolver una lista de todas las paradas de un cliente
    public List<String> paradasDeCliente(Cliente cliente) throws ClienteException {
        List<String> paradasList = clientes.keySet().stream().filter(cliente::equals)
                .flatMap(c -> clientes.get(c).stream())
                .flatMap(r -> r.getParadas().stream())
                .distinct().sorted(String::compareTo).toList();
        if (paradasList.isEmpty()) {
            throw new ClienteException("No hay paradas que cumplan");
        }
        return paradasList;
    }
}