package Extra.Ejercicio15;

import java.time.LocalDateTime;

public class EstacionMetereologicaApp {
    public static void main(String[] args) {
        try {
            // Creamos la estación metereológica
            EstacionMetereologica estacionMetereologica = new EstacionMetereologica("París", 5.6);
            // Creamos los sensores
            Sensor sensor = new Sensor(TSensor.HUMEDAD);
            Sensor sensor1 = new Sensor(TSensor.RADIACION_UV);
            // Creamos las mediciones
            Medicion medicion = new Medicion(7.8, LocalDateTime.of(2020, 11, 8, 12, 20));
            Medicion medicion1 = new Medicion(6.5, LocalDateTime.of(2023, 10, 5, 11, 10));
            Medicion medicion2 = new Medicion(5.5, LocalDateTime.of(2024, 5, 7, 16, 40));
            Medicion medicion3 = new Medicion(9.4, LocalDateTime.of(2025, 1, 27, 17, 30));
            Medicion medicion4 = new Medicion(19.9, LocalDateTime.of(2019, 8, 22, 19, 15));
            // Registramos los sensores en la estación
            estacionMetereologica.instalarSensor(sensor);
            estacionMetereologica.instalarSensor(sensor1);
            // Registramos las mediciones en los sensores
            estacionMetereologica.registrarMedicion(sensor, medicion);
            estacionMetereologica.registrarMedicion(sensor, medicion1);
            estacionMetereologica.registrarMedicion(sensor, medicion2);
            estacionMetereologica.registrarMedicion(sensor1, medicion3);
            estacionMetereologica.registrarMedicion(sensor1, medicion4);
            // Llamamos a los diferentes métodos que me va a mostrar lo especificado
            System.out.println(estacionMetereologica.informeSensor());
            System.out.println(estacionMetereologica.calcularMediaMediciones(sensor, 2));
            System.out.println(estacionMetereologica.mostrarMediciones(sensor1));

        } catch (EstacionException | RuntimeException e) {
            System.out.println(e.getMessage());

        }
    }
}