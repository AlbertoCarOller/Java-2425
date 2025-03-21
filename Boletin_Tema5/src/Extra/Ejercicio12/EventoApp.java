package Extra.Ejercicio12;

import java.util.*;
import java.util.stream.Collectors;

public class EventoApp {
    static List<Asistente> asistentes = new ArrayList<>();

    public static void main(String[] args) {
        try {
            // Creamos los ponentes
            Ponente ponente = new Ponente("Chelu", "España", Especialidad.CIBERSEGURIDAD);
            Ponente ponente1 = new Ponente("Yuki-Chan", "México", Especialidad.INTELIGENCIA_ARTIFICIAL);
            Ponente ponente2 = new Ponente("Carles Xavier", "Venezuela", Especialidad.DESARROLLO_WEB);
            Ponente ponente3 = new Ponente("Atisbedo", "Canadá", Especialidad.CIBERSEGURIDAD);
            // Creamos los eventos
            Evento evento = new Evento("Chelu´s event", 30);
            Evento evento1 = new Evento("Atisbedo event", 60);
            // Creamos asistentes
            Asistente asistente = new Asistente("Bermudín");
            // Registramos ponentes en eventos
            evento.registrarPonente(ponente);
            evento.registrarPonente(ponente1);
            evento1.registrarPonente(ponente2);
            evento1.registrarPonente(ponente3);
            // Registramos los eventos
            asistente.registrarseEvento(evento);
            asistente.registrarseEvento(evento1);
            // Registramos el asistente
            registrarAsistente(asistente);
            // Llamamos a los métodos de mostrar
            System.out.println(asistentesEnEvento(evento));
            System.out.println(eventosDeAsistente(asistente));
            System.out.println(mostrarPonentesDePais("Canadá"));
            System.out.println(eventosPopulares());
            System.out.println(mostrarPonenteEventoPorPais("España"));

        } catch (EventoException e) {
            System.out.println(e.getMessage());
        }
    }

    // Hacemos un método registrar asistentes en eventos
    public static void registrarAsistente(Asistente asistente) throws EventoException {
        if (asistentes.contains(asistente)) {
            throw new EventoException("El asistente ya está registrado");
        }
        asistentes.add(asistente);
    }

    // Hacemos un método para mostrar todos los asistentes que van a un evento
    public static List<Asistente> asistentesEnEvento(Evento evento) throws EventoException {
        List<Asistente> asistenteList = asistentes.stream().filter(a -> a.getEventos().contains(evento))
                .sorted(Comparator.comparing(Asistente::getNombre))
                .toList();
        if (asistenteList.isEmpty()) {
            throw new EventoException("Ningún asistente coincidente");
        }
        return asistenteList;
    }

    // Hacemos un método para mostrar todos los eventos en los que está inscrito un asistente
    public static List<Evento> eventosDeAsistente(Asistente asistente) throws EventoException {
        List<Evento> eventoList = asistentes.stream().filter(asistente::equals)
                .flatMap(a -> a.getEventos().stream())
                .sorted(Comparator.comparing(Evento::getMinutos)).toList();
        if (eventoList.isEmpty()) {
            throw new EventoException("No se han encontrado datos");
        }
        return eventoList;
    }

    // Hacemos un método para mostrar los ponentes de un país
    public static List<Ponente> mostrarPonentesDePais(String pais) throws EventoException {
        return Optional.of(asistentes.stream().flatMap(a -> a.getEventos().stream())
                        .flatMap(e -> e.getPonentes().stream())
                        .filter(p -> p.getPais().equalsIgnoreCase(pais)).toList())
                .filter(p -> !p.isEmpty())
                .orElseThrow(() -> new EventoException("No hay ponentes que sean de " + pais));
    }

    // Hacemos un método para mostrar los eventos más populares y en caso de empate que se ordenen alfabéticamente
    public static List<Evento> eventosPopulares() throws EventoException {
        return Optional.of(asistentes.stream().flatMap(a -> a.getEventos().stream())
                        .sorted((e1, e2) -> {
                            int i = Integer.compare(e1.getPonentes().size(), e2.getPonentes().size());
                            if (i == 0) {
                                return e1.getNombre().compareTo(e2.getNombre());
                            }
                            return i;
                        }).toList()).filter(e -> !e.isEmpty())
                .orElseThrow(() -> new EventoException("No hay eventos"));
    }

    // Hacemos un método para mostrar los ponentes de un país con su respectivo evento
    public static Map<Ponente, List<Evento>> mostrarPonenteEventoPorPais(String pais) {
        return asistentes.stream().flatMap(a -> a.getEventos().stream())
                .flatMap(e -> e.getPonentes().stream()
                        .filter(p -> p.getPais().equalsIgnoreCase(pais.trim()))
                        .map(p -> Map.entry(p, e)))
                .collect(Collectors.groupingBy(Map.Entry::getKey,
                        Collectors.mapping(Map.Entry::getValue, Collectors.toList())));
    }
}