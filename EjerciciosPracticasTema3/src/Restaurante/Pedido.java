package Restaurante;

import java.util.Arrays;
import java.util.Objects;

public class Pedido {

    // Atributos
    private String cliente;
    private Plato[] platos;
    private String estado;

    // Útilis
    double totalPrecio;

    // Hacemos el constructor
    public Pedido(String cliente, String estado) throws PlatoExcepcion {
        this.cliente = cliente;
        this.setEstado(estado);
        platos = new Plato[3];
        this.totalPrecio = 0;
    }

    // Hacemos un método para agregar un plato a la lista
    public void anadirPlato(Plato plato, Restaurante restaurante) throws PlatoExcepcion {

        boolean estaEnMenu = false;

        for (int i = 0; i < restaurante.getMenu().length; i++) {

            if (restaurante.getMenu()[i] != null) {
                if (restaurante.getMenu()[i].equals(plato)) {
                    estaEnMenu = true;
                }
            }
        }

        if (!estaEnMenu) {
            throw new PlatoExcepcion("El plato no está en el menú");
        }

        int hayEspacio = -1;

        for (int i = 0; i < platos.length; i++) {

            // Si hay espacio en la lista de platos, se puede añadir, se podrá añadir el mismo plato
            if (platos[i] == null) {
                platos[i] = plato;
                hayEspacio = i;
                break;
            }
        }
        if (hayEspacio == -1) {
            throw new PlatoExcepcion("No se puede añadir el plato, la lista está completa");
        }
    }

    // Hacemos un método para calcular el total del pedido
    public void calcularTotal() {

        for (int i = 0; i < platos.length; i++) {

            if (platos[i] == null) {
                continue;
            }
            totalPrecio += platos[i].getPrecio();
        }
        System.out.println("El precio del pedido es de " + totalPrecio);
    }

    // Hacemos un toString para mostrar toda la información de los pedidos
    @Override
    public String toString() {
        return "Restaurante.Pedido{" +
                "cliente='" + cliente + '\'' +
                ", platos=" + Arrays.toString(platos) +
                ", estado='" + estado + '\'' + '}';
    }

    // Hacemos los get
    public String getCliente() {
        return cliente;
    }

    public Plato[] getPlatos() {
        return platos;
    }

    public String getEstado() {
        return estado;
    }

    public double getTotalPrecio() {
        return totalPrecio;
    }

    // Hacemos un equals para comprobar que dos pedidos sean los mismos
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pedido pedido = (Pedido) o;
        return Objects.equals(cliente, pedido.cliente);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(cliente);
    }

    // Hacemos un set del estado del pedido
    public void setEstado(String estado) throws PlatoExcepcion {

        if (!estado.equals("en proceso") && !estado.equals("entregado")) {
            throw new PlatoExcepcion("Este estado no existe");
        }
        this.estado = estado;
    }
}