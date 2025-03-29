package Extra.Ejercicio17;

public class AcademiaApp {
    public static void main(String[] args) {
        try {
            // Creamos la academia
            Academia academia = new Academia("Chelu´s academy");
            // Creamos los cursos
            String curso = "Francés";
            String curso1 = "Inglés";
            // Creamos los estudiantes
            Estudiante estudiante = new Estudiante("Chelu");
            Estudiante estudiante1 = new Estudiante("Atisbedo");
            Estudiante estudiante2 = new Estudiante("Carles");
            Estudiante estudiante3 = new Estudiante("Vallejo");
            Estudiante estudiante4 = new Estudiante("Víctor");
            Estudiante estudiante5 = new Estudiante("Antonio");
            Estudiante estudiante6 = new Estudiante("Ventisca");
            // Registramos los cursos en la academia
            academia.registrarCurso(curso);
            academia.registrarCurso(curso1);
            // Añadimos estudiantes a los cursos
            academia.inscribirEstudiante(curso, estudiante);
            academia.inscribirEstudiante(curso, estudiante1);
            academia.inscribirEstudiante(curso, estudiante2);
            academia.inscribirEstudiante(curso, estudiante5);
            academia.inscribirEstudiante(curso1, estudiante3);
            academia.inscribirEstudiante(curso1, estudiante4);
            academia.inscribirEstudiante(curso1, estudiante5);
            academia.inscribirEstudiante(curso1, estudiante6);
            // Consultamos los cursos en los que está un estudiante
            System.out.println(academia.consultarCursosEstudiante(estudiante5));
            System.out.println();
            System.out.println(academia.mapaCursoNumEstudiantes());
            System.out.println();
            System.out.println(academia.estudiantesOrdenadosDeCurso(curso1));
            System.out.println();
            System.out.println(academia.fusionarCursosV1(curso, curso1));
            System.out.println();
            System.out.println(academia.fusionarCursosV2(curso, curso1));
            System.out.println();
            System.out.println(academia.estudiantesCoincidentesEnCursosV1(curso, curso1));
            System.out.println();
            System.out.println(academia.estudiantesCoincidentesEnCursosV2(curso, curso1));

        } catch (EstudianteException e) {
            System.out.println(e.getMessage());
        }
    }
}