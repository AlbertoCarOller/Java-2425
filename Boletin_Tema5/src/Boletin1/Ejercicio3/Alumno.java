package Boletin1.Ejercicio3;

public class Alumno extends Persona {

    // Creamos el constructor
    public Alumno(String nombre, String dni, int edad) {
        super(nombre, dni, edad);
    }

    // Implementamos el métodod de la clase persona
    @Override
    public void enviarMensaje(Persona persona, String cuerpo) throws PersonaExcepcion {
        // Si la persona a la que se lo vas a enviar es un alumno y eres menor salta una excepción
        if (persona instanceof Alumno) {
            if (this.getEdad() < 18) {
                throw new PersonaExcepcion("Eres menor, no puedes enviar el mensaje a otro alumno");
            }
        }
        // Creamos un mensaje
        Mensaje mensaje = new Mensaje(this, cuerpo);
        persona.getMensajes().add(mensaje);
    }
}