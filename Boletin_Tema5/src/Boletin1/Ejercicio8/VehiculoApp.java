package Boletin1.Ejercicio8;

import utils.MiEntradaSalida;

import java.util.*;

public class VehiculoApp {
    // Creamos un mapa con los vehículos
    static Map<String, Vehiculo> vehiculos = new HashMap<>();

    public static void main(String[] args) {

        int op;

        do {
            System.out.println("1. Dar de alta vehículo");
            System.out.println("2. Calcular el precio final");
            System.out.println("3. Ordenar vehículos por matrícula");
            System.out.println("4. Ordenar furgonetas por PMA");
            System.out.println("5. Ordenar vehículos por gama");
            System.out.println("6. Salir");

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
                        Vehiculo vehiculoACalcular = buscarVehiculo(matriculaABuscar);

                        if (vehiculoACalcular == null) {
                            System.out.println("No se ha enocntrado el vehículo");

                        } else {
                            System.out.println("El precio total del vehículo " + vehiculoACalcular.getMatricula() +
                                    " es de " + vehiculoACalcular.calcularTotal());
                        }
                        break;

                    case 3:
                        System.out.println(ordenarPorMatricula());
                        break;

                    case 4:
                        System.out.println(ordenarFurgonetas());
                        break;

                    case 5:
                        System.out.println(ordenarPorGama());
                        break;

                    case 6:
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

        } while (op != 6);
    }

    // Hacemos un método para dar de alta a un vehículo, lo almacenamos en un array
    public static void darDeAlta(Vehiculo vehiculo) throws VehiculoException {
        if (vehiculos.containsValue(vehiculo)) {
            throw new VehiculoException("El vehículo ya se encuentra en la lista");
        }
        vehiculos.put(vehiculo.getMatricula(), vehiculo);
    }

    // Hacemos un método que devuelva un vehículo
    public static Vehiculo buscarVehiculo(String matricula) {
        if (vehiculos.containsKey(matricula)) {
            return vehiculos.get(matricula);
        }
        return null;
    }

    // Hacemos un método para devolver los vehículos ordenados por la matrícula
    public static List<Vehiculo> ordenarPorMatricula() throws VehiculoException {
        if (vehiculos.isEmpty()) {
            throw new VehiculoException("No hay vehículos");
        }
        SortedMap<String, Vehiculo> vehiculosOrdenadoMatricula = new TreeMap<>(vehiculos);
        return new ArrayList<>(vehiculosOrdenadoMatricula.values());
    }

    // Hacemos un método para ordenar furgonetas por el PMA
    public static List<Furgoneta> ordenarFurgonetas() throws VehiculoException {
        List<Furgoneta> furgonetasOrdenadas = new ArrayList<>();
        for (Vehiculo vehiculo : vehiculos.values()) {
            if (vehiculo instanceof Furgoneta furgoneta) {
                furgonetasOrdenadas.add(furgoneta);
            }
        }
        if (furgonetasOrdenadas.isEmpty()) {
            throw new VehiculoException("No hay furgonetas");
        }
        furgonetasOrdenadas.sort(new Furgoneta(" ", 2, Gama.MEDIA, 200));
        return furgonetasOrdenadas;
    }

    // Hacemos un método para ordenar los vehículos por gama
    public static List<Vehiculo> ordenarPorGama() throws VehiculoException {
        if (vehiculos.isEmpty()) {
            throw new VehiculoException("No se han encontrado vehículos");
        }
        List<Vehiculo> vehiculosOrdenadosGama = new ArrayList<>(vehiculos.values());
        vehiculosOrdenadosGama.sort(null);
        return vehiculosOrdenadosGama;
    }
}