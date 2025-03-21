package Extra.Ejercicio9;

public class AgenciaApp {
    public static void main(String[] args) {
        try {
            // Creamos la agencia
            Agencia agencia = new Agencia("Chelu´s agencia");
            // Creamos los clientes
            Cliente cliente = new Cliente("Chelu");
            Cliente cliente1 = new Cliente("Atisbedo");
            Cliente cliente2 = new Cliente("Carles Xavier");
            // Creamos las rutas
            Ruta ruta = new Ruta("Ruta pishcolabis", "Italia");
            Ruta ruta1 = new Ruta("Ruta del bacalao", "Mediterraneo");
            Ruta ruta2 = new Ruta("Ruta atisbedísimo", "Italia");
            Ruta ruta3 = new Ruta("Ruta Xavier", "México");
            // Añadimos los clientes
            agencia.registrarCliente(cliente);
            agencia.registrarCliente(cliente1);
            agencia.registrarCliente(cliente2);
            // Añadimos a los clientes las rutas
            agencia.anadirRuta(cliente, ruta);
            agencia.anadirRuta(cliente, ruta1);
            agencia.anadirRuta(cliente1, ruta3);
            agencia.anadirRuta(cliente, ruta2);
            // Añadimos a las rutas las paradas
            agencia.anadirParada(cliente, ruta, "Lepe");
            agencia.anadirParada(cliente, ruta, "Illuniom");
            agencia.anadirParada(cliente, ruta1, "Villalba");
            agencia.anadirParada(cliente, ruta1, "Villablanca");
            agencia.anadirParada(cliente, ruta2, "Villanueva");
            agencia.anadirParada(cliente1, ruta3, "Lepe");
            // Llamamos a los métodos para mostrar
            agencia.mostrarRutas(cliente);
            System.out.println();
            System.out.println(agencia.clientesConParada("Lepe"));

        } catch (ClienteException e) {
            System.out.println(e.getMessage());
        }
    }
}