package Extra.Ejercicio16;

import java.time.LocalDateTime;

public class ProyectoApp {
    public static void main(String[] args) {
        try {
            // Creamos el proyecto
            Proyecto proyecto = new Proyecto("Proyecto Chelu");
            // Creamos los participantes
            Participante participante = new Participante("Chelu");
            Participante participante1 = new Participante("Vallejo");
            Participante participante2 = new Participante("Antonio");
            Participante participante3 = new Participante("Víctor");
            // Creamos las tareas
            Tarea tarea = new Tarea("Barrer el suelo", "Barrer el suelo de la cocina",
                    LocalDateTime.of(2025, 2, 13, 12, 20));
            Tarea tarea1 = new Tarea("Crinpar Economato", "Crimpar los cables del Economato",
                    LocalDateTime.of(2025, 2, 7, 8, 0));
            Tarea tarea2 = new Tarea("Colocar focos", "Colocar focos en el techo",
                    LocalDateTime.of(2024, 11, 9, 11, 30));
            Tarea tarea3 = new Tarea("Echar lechada", "Echar lechada para tapar huecos",
                    LocalDateTime.of(2022, 7, 26, 9, 45));
            Tarea tarea4 = new Tarea("Comprobar termostatos", "Comprobar termostatos por habitación",
                    LocalDateTime.of(2019, 1, 19, 14, 5));
            // Registramos a los participantes en el proyecto
            proyecto.registrarParticipante(participante);
            proyecto.registrarParticipante(participante1);
            proyecto.registrarParticipante(participante2);
            proyecto.registrarParticipante(participante3);
            // Registramos las tareas en el proyecto
            proyecto.registrarTareas(tarea, tarea1, tarea2, tarea3, tarea4);
            // Añadimos tareas a los participantes
            System.out.println();
            proyecto.anadirTareaAParticipante(participante, tarea);
            proyecto.anadirTareaAParticipante(participante1, tarea2);
            proyecto.anadirTareaAParticipante(participante2, tarea1);
            // Completamos la tarea de un participante
            System.out.println();
            proyecto.completarTarea(participante);
            // Mostramos las tareas ordenadas
            System.out.println();
            System.out.println(proyecto.tareasOrdenadasPorEstado());
            // Mostramos a los participantes ordenados
            System.out.println();
            System.out.println(proyecto.participantesOrdenados());
            // Mostramos las tareas con más horas
            System.out.println();
            System.out.println(proyecto.tareasMasHoras(35000));
            // Hacemos un método para eliminar un participante
            System.out.println();
            proyecto.eliminarParticipante(participante1);

        } catch (ProyectoException e) {
            System.out.println(e.getMessage());
        }
    }
}