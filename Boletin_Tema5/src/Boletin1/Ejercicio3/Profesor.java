package Boletin1.Ejercicio3;

public class Profesor extends Persona {

    // Creamos el constructor
    public Profesor(String nombre, String dni, int edad) {
        super(nombre, dni, edad);
    }

    // Implementamos el método de la clase padre
    @Override
    public void enviarMensaje(Persona persona, String cuerpo) {
        Mensaje mensaje = new Mensaje(this, cuerpo);
        persona.getMensajes().add(mensaje);
    }
}