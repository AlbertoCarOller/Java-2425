package Boletin1.Ejercicio2;

public class EquipoApp {
    public static void main(String[] args) {
        Alumno alumno1 = new Alumno("Atisbedo", "4587655W");
        Alumno alumno2 = new Alumno("Respicio Godefrio", "981632R");
        Alumno alumno3 = new Alumno("Antonio", "3245465Q");
        Equipo<Alumno> equipo1 = new Equipo<>("Bermudines");
        Equipo<Alumno> equipo2 = new Equipo<>("Atisbedito");
        Alumno alumno4 = new Alumno("Alfredo", "897489P");
        Alumno alumno5 = new Alumno("Cales Xavier", "4587645W");
        try {
            // Añadimos los alumnos al equipo
            equipo1.addAlumno(alumno1);
            equipo1.addAlumno(alumno2);
            equipo1.addAlumno(alumno3);
            // Mostramos el equipo
            System.out.println(equipo1.mostrarEquipo());

            // Añadimos los alumnos al segundo equipo
            equipo2.addAlumno(alumno4);
            equipo2.addAlumno(alumno5);
            equipo2.addAlumno(alumno1);
            // Mostramos el equipo
            System.out.println(equipo2.mostrarEquipo());

            // Hacemos la unión
            Equipo unionEquipo = equipo1.unirEquipos(equipo2);
            System.out.println(unionEquipo);

            // Hacemos la intersección
            Equipo interseccionEquipo = equipo1.inserccionEquipo(equipo2);
            System.out.println(interseccionEquipo);

        } catch (EquipoException e) {
            System.out.println(e.getMessage());
        }
    }
}