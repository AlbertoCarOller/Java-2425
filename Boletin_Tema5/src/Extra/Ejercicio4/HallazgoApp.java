package Extra.Ejercicio4;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HallazgoApp {
    static Map<Expedicion, List<Hallazgo>> expediciones = new HashMap<>();

    public static void main(String[] args) {
        try {
            // Creamos las expediciones
            Expedicion expedicion = new Expedicion("Chelu´s expedition", "Illuniom",
                    LocalDate.of(2020, 4, 12));
            Expedicion expedicion1 = new Expedicion("Atisbedo´s expedition", "California",
                    LocalDate.of(2021, 6, 21));
            Expedicion expedicion2 = new Expedicion("Respicio´s expedition", "Madrid",
                    LocalDate.of(2019, 5, 9));
            // Creamos los hallazgos
            Hallazgo hallazgo = new Hallazgo("Hallazgo Chelu", "Chelu fue encontrado",
                    LocalDate.of(2024, 10, 26));
            Hallazgo hallazgo1 = new Hallazgo("Hallazgo Atisbedo", "Atisbedo encontrado",
                    LocalDate.of(2022, 12, 9));
            Hallazgo hallazgo2 = new Hallazgo("Hallazgo Respicio", "Respicio encontrado",
                    LocalDate.of(2024, 11, 5));
            Hallazgo hallazgo3 = new Hallazgo("Hallazgo Bermudin", "Bermudo encontrado",
                    LocalDate.of(2018, 8, 8));
            Hallazgo hallazgo4 = new Hallazgo("Hallazgo Walter Alejandro", "Walter encontrado",
                    LocalDate.of(2020, 7, 1));
            Hallazgo hallazgo5 = new Hallazgo("Hallazgo Saragarcon", "Sara encontrada",
                    LocalDate.of(2025, 3, 11));
            // Creamos las observaciones
            Observacion observacion = new Observacion(LocalDate.of(2017, 4, 12), "Chelu");
            Observacion observacion1 = new Observacion(LocalDate.of(2017, 3, 11), "Atisbedo");
            Observacion observacion2 = new Observacion(LocalDate.of(2017, 2, 10), "Walter Alejandro");
            Observacion observacion3 = new Observacion(LocalDate.of(2017, 1, 9), "Chelu");
            Observacion observacion4 = new Observacion(LocalDate.of(2016, 9, 12), "Saragarcon");
            Observacion observacion5 = new Observacion(LocalDate.of(2016, 8, 11), "Saragarcon");
            // Registramos las expediciones
            registrarExpedicion(expedicion);
            registrarExpedicion(expedicion1);
            registrarExpedicion(expedicion2);
            // Registramos los hallazgos en las expediciones
            registrarHallazgo("Chelu´s expedition", hallazgo);
            registrarHallazgo("Chelu´s expedition", hallazgo1);
            registrarHallazgo("Atisbedo´s expedition", hallazgo2);
            registrarHallazgo("Atisbedo´s expedition", hallazgo3);
            registrarHallazgo("Respicio´s expedition", hallazgo4);
            registrarHallazgo("Respicio´s expedition", hallazgo5);
            // Añadimos arqueólogos a los hallazgos
            hallazgo.registrarArqueologo("Chelu");
            hallazgo.registrarArqueologo("Atisbedo");
            hallazgo1.registrarArqueologo("Walter Alejandro");
            hallazgo2.registrarArqueologo("Saragarcon");
            // Añadimos las observaciones
            hallazgo.registrarObservacion("Chelu", observacion);
            hallazgo.registrarObservacion("Atisbedo", observacion1);
            hallazgo1.registrarObservacion("Walter Alejandro", observacion2);
            hallazgo.registrarObservacion("Chelu", observacion3);
            hallazgo2.registrarObservacion("Saragarcon", observacion4);
            hallazgo2.registrarObservacion("Saragarcon", observacion5);

            System.out.println(hallazgosOrdenados("Chelu´s expedition"));
            System.out.println(buscarHallazgo("Hallazgo Atisbedo"));


        } catch (HallazgoException e) {
            System.out.println(e.getMessage());
        }
    }

    // Hacemos un método para registrar una expedición
    public static void registrarExpedicion(Expedicion expedicion) throws HallazgoException {
        boolean esIgual = false;
        for (Expedicion expedicionRecorrida : expediciones.keySet()) {
            if (expedicion.equals(expedicionRecorrida)) {
                esIgual = true;
            }
        }
        if (esIgual) {
            throw new HallazgoException("La expedición ya ha sido registrada");
        }
        expediciones.put(expedicion, new ArrayList<>());
    }

    // Hacemos un método para registrar un hallazgo
    public static void registrarHallazgo(String nombreExpedicion, Hallazgo hallazgo) throws HallazgoException {
        if (expediciones.isEmpty()) {
            throw new HallazgoException("No hay expediciones todavía");
        }
        boolean esIgual = false;
        boolean encontrado = false;
        for (Expedicion expedicion : expediciones.keySet()) {
            if (nombreExpedicion.equalsIgnoreCase(expedicion.getNombre())) {
                encontrado = true;
                for (Hallazgo hallazgoRecorrido : expediciones.get(expedicion)) {
                    if (hallazgo.equals(hallazgoRecorrido)) {
                        esIgual = true;
                    }
                }
                if (esIgual) {
                    throw new HallazgoException("El hallazgo ya existía en esa expedición");
                }
                expediciones.get(expedicion).add(hallazgo);
                break;
            }
        }
        if (!encontrado) {
            throw new HallazgoException("La expedición no se ha encontrado en los registros");
        }
    }

    // Hacemos un método que va a devolver una lista de todos los hallazgos ordenados de una expedición específica
    public static List<Hallazgo> hallazgosOrdenados(String nombreExpedicion) throws HallazgoException {
        if (expediciones.isEmpty()) {
            throw new HallazgoException("No hay expediciones todavía");
        }
        List<Hallazgo> hallazgosOrdenados = new ArrayList<>();
        boolean encontrado = false;
        for (Expedicion expedicion : expediciones.keySet()) {
            if (nombreExpedicion.equalsIgnoreCase(expedicion.getNombre())) {
                encontrado = true;
                if (expediciones.get(expedicion).isEmpty()) {
                    throw new HallazgoException("No hay hallazgos en la expedición aún");
                }
                hallazgosOrdenados.addAll(expediciones.get(expedicion));
                break;
            }
        }
        if (!encontrado) {
            throw new HallazgoException("No se ha encontrado el nombre de la expedición");
        }
        hallazgosOrdenados.sort(null);
        return hallazgosOrdenados;
    }

    /* Hacemos un método que va a devolver un string con las expediciones donde se encuentra un hallazgo
     y su información básica */
    public static String buscarHallazgo(String nombreHallazgo) throws HallazgoException {
        if (expediciones.isEmpty()) {
            throw new HallazgoException("No hay expediciones todavía");
        }
        boolean encontrado = false;
        StringBuilder informacionHallazgo = new StringBuilder();
        Hallazgo hallazgoEncontrado = null;
        informacionHallazgo.append("Expediciones:");
        for (Expedicion expedicion : expediciones.keySet()) {
            for (Hallazgo hallazgo : expediciones.get(expedicion)) {
                if (nombreHallazgo.equalsIgnoreCase(hallazgo.getNombre())) {
                    encontrado = true;
                    hallazgoEncontrado = hallazgo;
                    informacionHallazgo.append("\n").append(expedicion.getNombre());
                    break;
                }
            }
        }
        if (!encontrado) {
            throw new HallazgoException("No se ha encontrado el hallazgo en ninguna expedición");
        }
        informacionHallazgo.append("\nDescripción: ").append(hallazgoEncontrado.getDescripcion()).append("\nFecha origen: ")
                .append(hallazgoEncontrado.getFechaEstimadaOrigen());
        return informacionHallazgo.toString();
    }
}