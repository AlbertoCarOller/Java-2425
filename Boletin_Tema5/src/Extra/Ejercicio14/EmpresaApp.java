package Extra.Ejercicio14;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class EmpresaApp {
    static List<Ruta> rutas = new ArrayList<>();

    public static void main(String[] args) {
        try {
            // Creamos paquetes
            Paquete paquete = new Paquete("Chelu", "Carles", 3, 10);
            Paquete paquete1 = new Paquete("Atisbedo", "Respecio", 2, 22);
            Paquete paquete2 = new Paquete("Saragarcon", "Xavier", 2, 25);
            Paquete paquete3 = new Paquete("Lolitogoku", "Natalia", 3, 8);
            Paquete paquete4 = new Paquete("Bermudín", "Peccary", 1, 1);
            // Creamos las rutas
            Ruta ruta = new Ruta("Ruta mediterránea");
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
            // Llamamos al método para ver el próximo paquete en salir de una ruta
            System.out.println("La próxima ruta en salir es la ruta con id "
                    + proximoPaqueteAEntregar(ruta).getNumeroSeguimiento());
            // Llamamos al método para avisar si hay paquetes en la ruta que lleven más de 1 día
            if (avisarDePaquetes(ruta1)) {
                System.out.println("En la ruta " + ruta1.getNombre() + " hay paquetes con más de 1 día de espera");

            } else {
                System.out.println("En la ruta " + ruta1.getNombre() + " no tiene paquetes con más de 1 día de espera");
            }
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
    public static String listarPaquetesRuta(Ruta ruta) throws PaqueteException {
        return Optional.of(rutas.stream().filter(ruta::equals)
                        .flatMap(r -> r.getPaquetes().stream())
                        .map(Paquete::toString)
                        .collect(Collectors.joining("\n")))
                .filter(s -> !s.isEmpty())
                .orElseThrow(() -> new PaqueteException("No se han encontrado resultados"));
    }

    // Hacemos un método para ver el próximo paquete que se entregará de una ruta sin eliminarlo
    public static Paquete proximoPaqueteAEntregar(Ruta ruta) throws PaqueteException {
        if (rutas.stream().noneMatch(ruta::equals)) {
            throw new PaqueteException("La ruta no está registrada");
        }
        int indiceRuta = rutas.indexOf(ruta);
        Paquete paquete = rutas.get(indiceRuta).getPaquetes().peek();
        if (paquete == null) {
            throw new PaqueteException("No hay paquetes");
        }
        return paquete;
    }

    /* Hacemos un método que avise si hay paquetes en una ruta específica que llevan más de 1
     día esperando para ser entregados */
    public static boolean avisarDePaquetes(Ruta ruta) {
        return rutas.stream().filter(ruta::equals)
                .flatMap(r -> r.getPaquetes().stream())
                .anyMatch(p -> p.getTiempoEspera() > 24);
    }

    // Hacemos un método para combinar los paquetes de dos rutas
    public static List<Paquete> combinarRutas(Ruta ruta, Ruta otraRuta) {
        return rutas.stream().filter(r -> r.equals(ruta) || r.equals(otraRuta))
                .flatMap(r -> r.getPaquetes().stream()).sorted()
                .toList();
    }
}