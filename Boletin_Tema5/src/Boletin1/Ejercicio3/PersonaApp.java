package Boletin1.Ejercicio3;

public class PersonaApp {
    public static void main(String[] args) {

        Alumno alumno2 = new Alumno("Respicio Godefrio", "444548S", 36);
        Alumno alumno1 = new Alumno("Atisbedo", "963458Q", 21);
        Profesor profesor1 = new Profesor("Carles Xavier", "7802349L", 23);

        try {
            profesor1.enviarMensaje(alumno2, "Mensaje 1");
            alumno1.enviarMensaje(alumno2, "Mensaje 2");
            System.out.println(alumno2.getMensajes());
            alumno1.enviarMensaje(alumno2, "Mensaje 3");
            System.out.println(alumno2.buscarCadena("Mensaje"));
            System.out.println(alumno2.mostrarBuzonOrdenado());
            alumno2.eliminarMensaje(0);
            System.out.println(alumno2.getMensajes());

        } catch (PersonaExcepcion e) {
            System.out.println(e.getMessage());
        }
    }
}