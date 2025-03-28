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
            Estudiante estudiante2 = new Estudiante("Carles Xavier");
            Estudiante estudiante3 = new Estudiante("Vallejo");
            Estudiante estudiante4 = new Estudiante("Víctor");
            Estudiante estudiante5 = new Estudiante("Antonio");
            Estudiante estudiante6 = new Estudiante("Ventisca");

        } catch (EstudianteException e) {
            System.out.println(e.getMessage());
        }
    }
}