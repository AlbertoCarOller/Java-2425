import utils.MiEntradaSalida;

import java.time.Year;

public class JugueteApp {
    // Creamos un tamaño máximo de juguetes, en este caso va a ser 10
    static int MAX_JUGUETES = 10;
    // Creamos un array de juguetes donde almacenaremos todos los juguetes
    static Juguete[] juguetes = new Juguete[MAX_JUGUETES];

    public static void main(String[] args) {

        int indice = -1;
        do {
            try {
                indice = MiEntradaSalida.seleccionarOpcion("Seleccione una opción",
                        new String[]{"Crear figura de madera", "Crear instrumento musical", "Crear un vehículo de" +
                                " plástico", "Crear una pieza de lego", "Apilar juguete", "Salir"});

                switch (indice) {
                    case 1:
                        String nombreFiguraMadera = MiEntradaSalida.solicitarCadena("Introduce el nombre de la figura");
                        String marcaFiguraMadera = MiEntradaSalida.solicitarCadena("Introduce la marca de la figura");
                        String origenMadera = MiEntradaSalida.solicitarCadena("Introduce el origen de la madera");
                        int anoTalaMadera = MiEntradaSalida.solicitarEnteroPositivo("Introduce el año de la tala");
                        String colorMadera = MiEntradaSalida.solicitarCadena("Introduce el color de la madera");
                        int numLadosMadera = MiEntradaSalida.solicitarEnteroPositivo("Introduce el número de lados de la figura");
                        // Creamos la figura de madera
                        FiguraMadera figuraMadera = new FiguraMadera(nombreFiguraMadera, marcaFiguraMadera, origenMadera,
                                Year.of(anoTalaMadera), colorMadera, numLadosMadera);
                        // Introducimos la figura de madera en el array
                        anadirJuguete(figuraMadera);
                        break;

                    case 2:
                        String nombreInstrumento = MiEntradaSalida.solicitarCadena("Introduce el nombre del instrumento");
                        String marcaInstrumento = MiEntradaSalida.solicitarCadena("Introduce la marca del instrumento");
                        String origenInstrumento = MiEntradaSalida.solicitarCadena("Introduce el origen del isntrumento");
                        int anoTalaInstrumento = MiEntradaSalida.solicitarEnteroPositivo("Introduce el año de la tala");
                        int edadMinima = MiEntradaSalida.solicitarEnteroPositivo("Introduce la edad mínima");
                        // Creamos el instrumento
                        InstrumentoMusicalMadera instrumentoMusicalMadera = new InstrumentoMusicalMadera(nombreInstrumento,
                                marcaInstrumento, origenInstrumento, Year.of(anoTalaInstrumento), edadMinima);
                        // Guardamos el instrumento
                        anadirJuguete(instrumentoMusicalMadera);
                        break;

                    case 3:
                        String nombreVehiculo = MiEntradaSalida.solicitarCadena("Introduce el nombre del vehículo");
                        String marcaVehiculo = MiEntradaSalida.solicitarCadena("Introduce la narca del vehículo");
                        int numRuedas = MiEntradaSalida.solicitarEnteroPositivo("Introduce el número de ruedas");
                        // Creamos el vehículo
                        VehiculoPlastico vehiculoPlastico = new VehiculoPlastico(nombreVehiculo, marcaVehiculo, numRuedas);
                        anadirJuguete(vehiculoPlastico);
                        break;

                    case 4:
                        String nombreLego = MiEntradaSalida.solicitarCadena("Introduce el nombre de la pieza de Lego");
                        int longitud = MiEntradaSalida.solicitarEnteroPositivo("Introduce la longitud de lapieza");
                        String colorPiezaLego = MiEntradaSalida.solicitarCadena("Introduce el color de la pieza de lego");
                        // Creamos la pieza de lego
                        PiezaLego piezaLego = new PiezaLego(nombreLego, longitud, colorPiezaLego);
                        // Añadimos la pieza de lego al array
                        anadirJuguete(piezaLego);
                        break;

                    case 5:
                        int tamanoMenu = mostrarMenu();
                        tamanoMenu = tamanoMenu - 1;
                        int opApilar = MiEntradaSalida.solicitarEnteroPositivo("Elija una opción");

                        if (opApilar > tamanoMenu) {
                            System.out.println("Opción inválida");

                        } else if (!(juguetes[opApilar] instanceof Apilable)) {
                            System.out.println("Opción inválida");

                        } else {
                            int tamanoSubMenu = mostrarMenu();
                            tamanoSubMenu = tamanoSubMenu - 1;
                            int opOtroApilar = MiEntradaSalida.solicitarEnteroPositivo("Elija una opción para apilarla");

                            if (opOtroApilar > tamanoSubMenu) {
                                System.out.println("Opción inválida");

                            } else {
                                ((Apilable) juguetes[opApilar]).apilar(juguetes[opOtroApilar]);
                            }
                        }
                        break;

                    case 6:
                        System.out.println("Hasta pronto");
                        break;

                    default:
                        System.out.println("No has seleccionado ninguna opción");
                        break;
                }

            } catch (JugueteExcepcion e) {
                System.out.println(e.getMessage());
            }
        } while (indice != 6);
    }

    // Hacemos un método para añadir el juguete al array
    public static void anadirJuguete(Juguete juguete) throws JugueteExcepcion {

        boolean esIgual = false;
        int hayEspacio = -1;

        for (int i = 0; i < juguetes.length && !esIgual; i++) {
            if (juguete.equals(juguetes[i])) {
                esIgual = true;
            }

            if (juguetes[i] == null && hayEspacio == -1) {
                hayEspacio = i;
            }
        }

        if (!esIgual && hayEspacio != -1) {
            juguetes[hayEspacio] = juguete;

        } else {
            throw new JugueteExcepcion("No se puede añadir el juguete");
        }
    }

    // Hacemos un método para mostrar el menú de los juguetes que hay
    public static int mostrarMenu() throws JugueteExcepcion {

        int numJuguetes = 0;

        for (int i = 0; i < juguetes.length; i++) {

            if (juguetes[i] != null) {
                System.out.println(numJuguetes + ". " + juguetes[i].getNombre());
                numJuguetes++;
            }
        }

        if (numJuguetes == 0) {
            throw new JugueteExcepcion("No hay juguetes");
        }
        return numJuguetes;
    }
}