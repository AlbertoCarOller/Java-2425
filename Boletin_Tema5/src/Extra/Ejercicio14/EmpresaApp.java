package Extra.Ejercicio14;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EmpresaApp {
    static List<Ruta> rutas = new ArrayList<>();

    public static void main(String[] args) {
        try {
            // Creamos paquetes
            Paquete paquete = new Paquete("Chelu", "Carles",
                    3, LocalDateTime.of(2020, 5, 13, 12, 30));
            Paquete paquete1 = new Paquete("Atisbedo", "Respecio", 2,
                    LocalDateTime.of(2021, 3, 15, 13, 45));
            Paquete paquete2 = new Paquete("Saragarcon", "Xavier", 2,
                    LocalDateTime.of(2023, 9, 19, 20, 10));
            Paquete paquete3 = new Paquete("Lolitogoku", "Natalia", 3,
                    LocalDateTime.of(2022, 11, 25, 19, 20));
            Paquete paquete4 = new Paquete("Bermudín", "Peccary", 1,
                    LocalDateTime.of(2024, 10, 22, 13, 55));
            // Creamos las rutas
            Ruta ruta = new Ruta("Ruta mediterranea");
            Ruta ruta1 = new Ruta("Ruta del bacalao");
            // Añadimos los paquetes a las rutas
            ruta.registrarPaquete(paquete);
            ruta.registrarPaquete(paquete1);
            ruta1.registrarPaquete(paquete2);
            ruta1.registrarPaquete(paquete3);
            ruta1.registrarPaquete(paquete4);
            // Registramos las rutas
            registrarRuta(ruta);
            registrarRuta(ruta1);
            // Llamamos al método para mostrar los paquetes de una ruta
            System.out.println(listarPaquetesRuta(ruta));
            System.out.println();
            // Llamamos al método para ver el próximo paquete en salir de una ruta
            System.out.println("El próximo paquete en salir es el paquete con id "
                    + proximoPaqueteAEntregar(ruta).getNumeroSeguimiento());
            System.out.println();
            // Llamamos al método para avisar si hay paquetes en la ruta que lleven más de 1 día
            System.out.println(avisarDePaquetes());
            System.out.println();
            // Llamamos al método para combinar rutas
            System.out.println(combinarRutas(ruta, ruta1));

        } catch (PaqueteException e) {
            System.out.println(e.getMessage());
        }
    }

    // Hacemos un método para añadir una ruta
    public static void registrarRuta(Ruta ruta) throws PaqueteException {
        if (rutas.stream().anyMatch(ruta::equals)) {
            throw new PaqueteException("La ruta ya está registrada");
        }
        rutas.add(ruta);
    }

    // Hacemos un método para listar todos los paquetes de una ruta específica
    public static List<Paquete> listarPaquetesRuta(Ruta ruta) throws PaqueteException {
        /*return Optional.of(rutas.stream().filter(ruta::equals)
                        .flatMap(r -> r.getPaquetes().stream())
                        .map(Paquete::toString)
                        .collect(Collectors.joining("\n")))
                .filter(s -> !s.isEmpty())
                .orElseThrow(() -> new PaqueteException("No se han encontrado resultados"));*/
        if (!rutas.contains(ruta)) {
            throw new PaqueteException("No existe la ruta");
        }
        return ruta.getPaquetes().stream().toList();
    }

    // Hacemos un método para ver el próximo paquete que se entregará de una ruta sin eliminarlo
    public static Paquete proximoPaqueteAEntregar(Ruta ruta) throws PaqueteException {
        if (rutas.stream().noneMatch(ruta::equals)) {
            throw new PaqueteException("La ruta no está registrada");
        }
        Paquete paquete = ruta.getPaquetes().peek();
        if (paquete == null) {
            throw new PaqueteException("No hay paquetes");
        }
        return paquete;
    }

    /* Hacemos un método que avise si hay paquetes en una ruta específica que llevan más de 1
     día esperando para ser entregados */
    public static List<Ruta> avisarDePaquetes() throws PaqueteException {
        return Optional.of(rutas.stream().filter(r -> r.getPaquetes().stream()
                        .anyMatch(p -> Duration.between(p.getFechaRecepcion(), LocalDateTime.now()).toHours() > 24))
                .toList()).orElseThrow(() -> new PaqueteException("No hay resultados"));
    }

    // Hacemos un método para combinar los paquetes de dos rutas
    public static List<Paquete> combinarRutas(Ruta ruta, Ruta otraRuta) throws PaqueteException {
        if (!rutas.contains(ruta) || !rutas.contains(otraRuta)) {
            throw new PaqueteException("Las rutas no están");
        }
        return rutas.stream().filter(r -> r.equals(ruta) || r.equals(otraRuta))
                .flatMap(r -> r.getPaquetes().stream()).sorted()
                .toList();
    }
}