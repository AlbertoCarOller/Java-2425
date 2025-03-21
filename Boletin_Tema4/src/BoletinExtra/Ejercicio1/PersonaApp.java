package BoletinExtra.Ejercicio1;

public class PersonaApp {
    public static void main(String[] args) {

        // Creamos una persona
        Saludador persona = new Persona();
        // Creamos el robot
        Saludador robot = new Robot();

        // Llamamos a los métodos
        persona.saludar();
        robot.saludar();
    }
}