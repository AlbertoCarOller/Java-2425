package Ejercicio8;

import utils.MiEntradaSalida;

public class FlotaApp {
    static NaveEspacial[] navesEspaciales = new NaveEspacial[4];

    public static void main(String[] args) {
        try {
            Tripulante tripulante1 = new Piloto("Atisbedo");
            Tripulante tripulante2 = new Mecanico("Vallejo");
            Tripulante tripulante3 = new Medico("Chelu");
            Tripulante tripulante4 = new Mecanico("Sensei");
            Tripulante tripulante5 = new Piloto("Respicio Godefrío");

            NaveEspacial naveEspacial1 = new NaveTransporte("AtisbedoX", 27,
                    Combustible.FUSION_NUCLEAR, 500, 100, true, false);

            Combustible combustible = Combustible.valueOf("Antimateria".toUpperCase());
            NaveEspacial naveEspacial2 = new NaveExploracion("StarChelu", 67, combustible,
                    69, true, false);

            naveEspacial1.anadirTripulante(tripulante1);
            naveEspacial1.anadirTripulante(tripulante2);
            naveEspacial1.anadirTripulante(tripulante3);
            naveEspacial2.anadirTripulante(tripulante4);
            naveEspacial2.anadirTripulante(tripulante5);

            anadirNave(naveEspacial1);
            anadirNave(naveEspacial2);

            llamarAMetodos();

            naveEspacial1.realizarViaje(new ViajeInterplanetario(Destino.PLANETA, 24, Mision.COMERCIAL, false));
            naveEspacial2.realizarViaje(new ViajeInterplanetario(Destino.ESTACION_ESPACIAL, 24, Mision.TURISTICA, true));

        } catch (NaveEspacialException e) {
            System.out.println(e.getMessage());

        } catch (IllegalArgumentException e) {
            System.out.println("Has introducido un tipo de combustible desconocido");
        }
    }

    // Hacemos un método para añadir naves
    public static void anadirNave(NaveEspacial naveEspacial) throws NaveEspacialException {
        boolean esIgual = false;
        int hayEspacio = -1;
        for (int i = 0; i < navesEspaciales.length && !esIgual; i++) {
            if (naveEspacial.equals(navesEspaciales[i])) {
                esIgual = true;
            }
            if (navesEspaciales[i] == null && hayEspacio == -1) {
                hayEspacio = i;
            }
        }
        if (!esIgual && hayEspacio != -1) {
            navesEspaciales[hayEspacio] = naveEspacial;

        } else {
            throw new NaveEspacialException("No se puede añadir la nave");
        }
    }

    // Hacemos un método para quitar una nave de la flota
    public static void eliminarNave(String nombreNave) throws NaveEspacialException {
        boolean encontrado = false;
        for (int i = 0; i < navesEspaciales.length; i++) {
            if (navesEspaciales[i] == null) {
                continue;
            }
            if (nombreNave.equalsIgnoreCase(navesEspaciales[i].getNombre())) {
                encontrado = true;
                navesEspaciales[i] = null;
            }
        }
        if (!encontrado) {
            throw new NaveEspacialException("No se ha encontrado la nave");
        }
    }

    // Hacemos un método para llamar a los diferentes métodos dependiendo del tipo de nave
    public static void llamarAMetodos() throws NaveEspacialException {
        for (int i = 0; i < navesEspaciales.length; i++) {
            if (navesEspaciales[i] instanceof NaveExploracion naveExploracion) {
                naveExploracion.pedirAuxilio(buscarNave("AtisbedoX"));
                String nombreSensor = MiEntradaSalida.solicitarCadena("Introduce el nombre del sensor");
                int precision = MiEntradaSalida.solicitarEnteroPositivo("Introduce la precisión");
                naveExploracion.anadirSensor(new Sensor(nombreSensor, precision));

            }
        }
    }

    // Hacemos un método para buscar una nave
    public static NaveEspacial buscarNave(String nombreNave) {
        for (int i = 0; i < navesEspaciales.length; i++) {
            if (navesEspaciales[i] == null) {
                continue;
            }
            if (nombreNave.equalsIgnoreCase(navesEspaciales[i].getNombre())) {
                return navesEspaciales[i];
            }
        }
        return null;
    }
}