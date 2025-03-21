package BoletinExtra.Ejercicio4;

public class VehiculoApp {
    public static void main(String[] args) {

        // Creamos los vehículos
        Movible coche = new Coche("Atisbedo");
        Movible autobus = new Autobus("Respucio Godefrío");
        Movible bicicleta = new Bicicleta("Rebustiana");

        /* El array tiene que ser movible ya que coche, autobús, bicicleta cumplen con el contrato de movible, pero
         * los vehículos no cumplen con este contrato */
        Movible[] vehiculos = {coche, autobus, bicicleta};

        // Llamamos a los métodos
        probarMobilidad(bicicleta);
        probarCargaYDescargaPasajeros(vehiculos);
    }

    // Hacemos un método para probar la movilidad
    public static void probarMobilidad(Movible movible) {
        movible.acelerar();
        movible.frenar();
    }

    // Hacemos un método para probar la carga y descarga de los pasajeros
    public static void probarCargaYDescargaPasajeros(Movible[] vehiculos) {

        for (Movible vehiculo : vehiculos) {
            if (vehiculo instanceof Cargable c) {
                c.cargar();
                c.descargar();
            }
        }
    }
}