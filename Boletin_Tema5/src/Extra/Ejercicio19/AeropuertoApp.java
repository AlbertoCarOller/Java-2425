package Extra.Ejercicio19;

public class AeropuertoApp {
    public static void main(String[] args) {
        try {
            // Creamos el aeropuerto
            Aeropuerto aeropuerto = new Aeropuerto("Chelu´s airport");
            // Creamos los vuelos
            Vuelo vuelo = new Vuelo();
            Vuelo vuelo1 = new Vuelo();
            // Creamos los pasajeros
            Pasajero pasajero = new Pasajero("Chelu");
            Pasajero pasajero1 = new Pasajero("Atisbedo");
            Pasajero pasajero2 = new Pasajero("Vallejo");
            Pasajero pasajero3 = new Pasajero("Sensei");
            Pasajero pasajero4 = new Pasajero("Respicio");
            Pasajero pasajero5 = new Pasajero("Victor");
            // Registramos los vuelos en el aeropuerto
            aeropuerto.registrarVuelo(vuelo);
            aeropuerto.registrarVuelo(vuelo1);
            // Registramos a los pasajeros en los vuelos
            vuelo.registrarPasajero(pasajero);
            vuelo.registrarPasajero(pasajero1);
            vuelo.registrarPasajero(pasajero2);
            vuelo1.registrarPasajero(pasajero3);
            vuelo1.registrarPasajero(pasajero4);
            vuelo1.registrarPasajero(pasajero5);
            vuelo1.registrarPasajero(pasajero);
            // Llamamos a los diferentes métodos para su comprobación
            System.out.println(aeropuerto.encontrarPasajerosEnVuelos(vuelo, vuelo1));
            System.out.println(aeropuerto.fusionarVuelos(vuelo, vuelo1));
            System.out.println(vuelo.informacionPasajero(pasajero2));
            System.out.println(vuelo.listaEsperaVueloOrdenado());

        } catch (PasajeroExcepcion e) {
            System.out.println(e.getMessage());
        }
    }
}