package Extra.Ejercicio7;

import java.util.*;

public class TareaApp {
    static Queue<Tarea> tareasPendientes = new PriorityQueue<>();
    static List<Tarea> tareasBloqueadas = new LinkedList<>();
    static List<Tarea> tareasCompletadas = new ArrayList<>();

    public static void main(String[] args) {
        try {
            // Creamos tareas
            Tarea tarea = new Tarea("Tarea Chelu", "Buscar a Chelu", Prioridad.MEDIA, null);
            Tarea tarea1 = new Tarea("Tarea Atisbedo", "Buscar a Atisbedo", Prioridad.BAJA, null);
            Tarea tarea2 = new Tarea("Tarea Respicio Godefrío", "Buscar a Respicio Godefrío", Prioridad.MEDIA, null);
            Tarea tarea3 = new Tarea("Tarea Saragarcon", "Buscar a Saragarcon", Prioridad.ALTA, null);
            // Creamos tareas bloqueadas
            Tarea tarea4 = new Tarea("Tarea Carles Xavier", "Buscar a Carles Xavier", Prioridad.ALTA, tarea);
            Tarea tarea5 = new Tarea("Tarea Bermudín", "Buscar a Bermudo", Prioridad.MEDIA, tarea3);
            // Añadimos las tareas
            registrarTarea(tarea);
            registrarTarea(tarea1);
            registrarTarea(tarea2);
            registrarTarea(tarea3);
            registrarTarea(tarea4);
            registrarTarea(tarea5);
            // Completamos algunas tareas
            completarTarea();
            completarTarea();
            completarTarea();
            // Llamamos a los métodos para que nos muestre las tareas
            System.out.println();
            mostrarTareasPendientes();
            System.out.println();
            mostrarTareasCompletadas();

        } catch (TareaException e) {
            System.out.println(e.getMessage());
        }
    }

    // Hacemos un método para añadir tareas
    public static void registrarTarea(Tarea tarea) throws TareaException {
        if (tarea.isSolucionada()) {
            throw new TareaException(tarea.getNombre() + " ya está solucionada");
        }
        if (tarea.getTarea() == null) {
            if (tareasPendientes.contains(tarea)) {
                throw new TareaException("La tarea ya está añadida");
            }
            tareasPendientes.add(tarea);
        } else {
            if (tareasBloqueadas.contains(tarea)) {
                throw new TareaException("La tarea ya está añadida");
            }
            for (Tarea tareaRecorrida : tareasBloqueadas) {
                if (tarea.getTarea().equals(tareaRecorrida.getTarea())) {
                    throw new TareaException("No puedes añadir una tarea con una tarea que ya bloquea a otra");
                }
            }
            tareasBloqueadas.add(tarea);
        }
    }

    // Hacemos un método para completar una tarea
    public static void completarTarea() throws TareaException {
        if (tareasPendientes.isEmpty()) {
            throw new TareaException("No hay tareas disponibles");
        }
        Tarea tarea = tareasPendientes.poll();
        tarea.setSolucionada(true);
        System.out.println(tarea.getNombre() + " completada");
        desbloquearTarea(tarea);
        tareasCompletadas.add(tarea);
    }

    /* Hacemos un método para comprobar si existe una tarea bloqueada por culpa de la tarea que acabamos de completar
     y si es así la desbloqueamos */
    public static void desbloquearTarea(Tarea tarea) throws TareaException {
        if (tareasBloqueadas.isEmpty()) {
            return;
        }
        for (int i = 0; i < tareasBloqueadas.size(); i++) {
            if (tarea.equals(tareasBloqueadas.get(i).getTarea())) {
                tareasBloqueadas.get(i).setTarea(null);
                System.out.println(tareasBloqueadas.get(i).getNombre() + " desbloqueada");
                registrarTarea(tareasBloqueadas.get(i));
                tareasBloqueadas.remove(i);
                break;
            }
        }
    }

    // Hacemos un método para mostrar las tareas pendientes por el orden de prioridad
    public static void mostrarTareasPendientes() {
        tareasPendientes.forEach(System.out::println);
    }

    // Hacemos un método para mostrar las tareas completadas ordenado alfabéticamente
    public static void mostrarTareasCompletadas() {
        tareasCompletadas.stream().sorted(Comparator.comparing(Tarea::getNombre)).forEach(System.out::println);
    }
}