package Boletin1.Vehiculo;

import utils.MiEntradaSalida;

public class VehiculoApp {
    static final int MAX_VEHICULOS = 200;
    static Vehiculo[] vehiculos = new Vehiculo[MAX_VEHICULOS];

    public static void main(String[] args) {

        int op;

        do {
            System.out.println("1. Dar de alta vehículo");
            System.out.println("2. Calcular el precio final");
            System.out.println("3. Salir");

            op = MiEntradaSalida.solicitarEnteroPositivo("Elija una opción");

            try {
                switch (op) {
                    case 1:
                        System.out.println("1. Dar de alta coche");
                        System.out.println("2. Dar de alta furgoneta");
                        System.out.println("3. Dar de alta microbus");

                        int op2 = MiEntradaSalida.solicitarEnteroPositivo("Elija una opción");

                        switch (op2) {
                            case 1:
                                String matriculaCoche = MiEntradaSalida.solicitarCadena("Introduce la matrícula");
                                int numDiasCoche = MiEntradaSalida.solicitarEnteroPositivo("Introduce los días alquilados");
                                /* Creamos un String el cuál va a recoger la gama que introduzca el usuario, posteriormente
                                 * creamos un enum llamado 'gamaCoche' al cuál le vamos a dar el valor del String ya que es
                                 * lo que hace 'valueof', transformarlo de String a enum 'Gama', lo pasaremos a este si obviamente
                                 * se llama igual que algún tipo de los enum 'Gama', para asegurarnos de que esté en mayúscula el String
                                 * le hacemos un 'toUpperCase()', ya que los enum son constantes, por lo que están en mayúscula*/
                                String gamaCocheSt = MiEntradaSalida.solicitarCadena("Introduce la gama, BAJA, MEDIA o ALTA");
                                Gama gamaCoche = Gama.valueOf(gamaCocheSt.toUpperCase());
                                String combustibleCocheSt = MiEntradaSalida.solicitarCadena("Introduce el tipo de combustible");
                                Combustible combustibleCoche = Combustible.valueOf(combustibleCocheSt.toUpperCase());
                                // Creamos el coche
                                Coche coche = new Coche(matriculaCoche, numDiasCoche, gamaCoche, combustibleCoche);
                                // Llamamos al método para dar de alta el coche
                                darDeAlta(coche);
                                break;

                            case 2:
                                String matriculaFurgoneta = MiEntradaSalida.solicitarCadena("Introduce la matrícula de la furgoneta");
                                int numDiasFurgoneta = MiEntradaSalida.solicitarEnteroPositivo("Introduce los días alquilados");
                                String gamaFurgonetaSt = MiEntradaSalida.solicitarCadena("Introduce la gama, BAJA, MEDIA o ALTA");
                                Gama gamaFurgoneta = Gama.valueOf(gamaFurgonetaSt.toUpperCase());
                                double masaMaximaAutorizada = MiEntradaSalida.solicitarDoublePositivo("Introduce la masa máxima autorizada");
                                // Creamos la furgoneta
                                Furgoneta furgoneta = new Furgoneta(matriculaFurgoneta, numDiasFurgoneta, gamaFurgoneta, masaMaximaAutorizada);
                                // Llamamos al método para dar de alta la furgoneta
                                darDeAlta(furgoneta);
                                break;

                            case 3:
                                String matriculaMicrobus = MiEntradaSalida.solicitarCadena("Inrtroduce la matrícula del microbus");
                                int numDiasMicrobus = MiEntradaSalida.solicitarEnteroPositivo("Introduce el número de días alquilados");
                                String gamaMicrobusSt = MiEntradaSalida.solicitarCadena("Introduce la gama, BAJA, MEDIA o ALTA");
                                Gama gamaMicrobus = Gama.valueOf(gamaMicrobusSt.toUpperCase());
                                int plazasMicrobus = MiEntradaSalida.solicitarEnteroPositivo("Introduce el número de plazas");
                                // Creamos el microbus
                                Microbus microbus = new Microbus(matriculaMicrobus, numDiasMicrobus, gamaMicrobus, plazasMicrobus);
                                // Llamamos al método para dar de alta el microbus
                                darDeAlta(microbus);
                                break;

                            default:
                                System.out.println("No has seleccionado ninguna opción");
                                break;
                        }
                        break;

                    case 2:
                        String matriculaABuscar = MiEntradaSalida.solicitarCadena("Introduce la matrícula del vehículo");

                        boolean encontrado = false;

                        for (int i = 0; i < vehiculos.length && !encontrado; i++) {
                            if (vehiculos[i] != null) {
                                if (matriculaABuscar.equals(vehiculos[i].matricula)) {
                                    double resultado = vehiculos[i].calcularTotal();
                                    System.out.println("El total es de " + resultado);
                                    encontrado = true;
                                }
                            }
                        }

                        if (!encontrado) {
                            System.out.printf("La matrícula %s no se ha encontrado en el concesonario", matriculaABuscar);
                        }
                        break;

                    case 3:
                        System.out.println("Hasta pronto");
                        break;

                    default:
                        System.out.println("No has seleccionado ninguna opción");
                        break;
                }
            } catch (VehiculoException e) {
                System.out.println(e.getMessage());

            } catch (IllegalArgumentException e) {
                System.out.println("El tipo de gama o combustible no es correcto");
            }

        } while (op != 3);
    }

    // Hacemos un método para dar de alta a un vehículo, lo almacenamos en un array
    public static void darDeAlta(Vehiculo vehiculo) throws VehiculoException {

        boolean esIgual = false;
        int hayEspacio = -1;

        for (int i = 0; i < vehiculos.length && !esIgual; i++) {

            if (vehiculo.equals(vehiculos[i])) {
                esIgual = true;
            }

            if (vehiculos[i] == null && hayEspacio == -1) {
                hayEspacio = i;
            }
        }

        if (!esIgual && hayEspacio != -1) {
            vehiculos[hayEspacio] = vehiculo;

        } else {
            throw new VehiculoException("No se puede dar de alta al vehículo");
        }
    }
}