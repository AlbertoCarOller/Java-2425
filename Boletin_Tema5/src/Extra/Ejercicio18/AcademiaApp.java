package Extra.Ejercicio18;

import java.time.LocalDate;

public class AcademiaApp {
    public static void main(String[] args) {
        try {
            // Creamos la academia
            Academia academia = new Academia("Academy");
            // Creamos los alumnos
            AlumnoMusical alumnoMusical = new AlumnoMusical("Chelu", "Guitarra");
            AlumnoMusical alumnoMusical1 = new AlumnoMusical("Antonio", "Tambor");
            AlumnoMusical alumnoMusical2 = new AlumnoMusical("Victor", "Trompeta");
            // Creamos las audicones
            Audicion audicion = new Audicion(LocalDate.of(2022, 7, 12));
            Audicion audicion1 = new Audicion(LocalDate.of(2024, 11, 26));
            Audicion audicion2 = new Audicion(LocalDate.of(2025, 1, 8));
            Audicion audicion3 = new Audicion(LocalDate.of(2021, 5, 19));
            // Creamos grupos
            GrupoMusical grupoMusical = new GrupoMusical("Grupox");
            GrupoMusical grupoMusical1 = new GrupoMusical("Grupo");
            // Registramos los alumnos
            academia.registrarAlumno(alumnoMusical);
            academia.registrarAlumno(alumnoMusical1);
            academia.registrarAlumno(alumnoMusical2);
            // Registramos los grupos
            academia.registrarGrupo(grupoMusical);
            academia.registrarGrupo(grupoMusical1);
            // Asignamos la las audiciones a la academia
            academia.realizarAudicion(alumnoMusical, audicion);
            academia.realizarAudicion(alumnoMusical, audicion1);
            academia.realizarAudicion(alumnoMusical1, audicion2);
            academia.realizarAudicion(alumnoMusical2, audicion3);
            // Seleccionamos los mejores alumnos para un grupo
            if (academia.seleccionarMejoresAlumnosV2(grupoMusical, 2) == -1) {
                System.out.println("Se han cogido todos los alumnos");

            } else {
                System.out.println("No se han cogido todos los alumnos");
            }
            // Mostramos una lista de audiciones de un alumno
            System.out.println();
            System.out.println(academia.listaAudicionesOrdenadas(alumnoMusical1));
            // Mostramos una lista de alumnos de un grupo de forma ordenada
            System.out.println();
            System.out.println(academia.listaAlumnosOrdenados(grupoMusical));
            // Seleccionamos los mejores alumnos para un grupo
            System.out.println();
            if (academia.seleccionarMejoresAlumnosV2(grupoMusical1, 1) == -1) {
                System.out.println("Se han cogido todos los alumnos");

            } else {
                System.out.println("No se han cogido todos los alumnos");
            }
            // Fusionamos dos grupos
            System.out.println();
            System.out.println(academia.fusionarGrupos(grupoMusical, grupoMusical1));
            // Mostramos un mapa de los grupos y el número de alumnos
            System.out.println();
            System.out.println(academia.mapaDeGrupos());

        } catch (EstudianteMusicalException e) {
            System.out.println(e.getMessage());
        }
    }
}