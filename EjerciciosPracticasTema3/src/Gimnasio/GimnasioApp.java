package Gimnasio;

public class GimnasioApp {
    public static void main(String[] args) {

        try {
            // Creamos un cliente para probar
            Cliente c1 = new Cliente("Atisbedo", "69146083W", 25, "medio");
            // Creamos dos actividades para el cliente
            Entrenamiento e1 = new Entrenamiento("Quita papadas", 3);
            Entrenamiento e2 = new Entrenamiento("Abdominales", 3);
            // Creamos dos actividades para el primer entrenamiento
            Actividad ac1 = new Actividad("Machaca cuellos", 15, "bajo");
            Actividad ac2 = new Actividad("Gargantudo", 10, "medio");
            // Añadimos el primer entrenamiento
            c1.anadirEntrenamiento(e1);
            // Le añadimos al primer entrenamiento del cliente dos actividades
            c1.anadirActividad(e1, ac1);
            c1.anadirActividad(e1, ac2);
            // Completamos el primer entrenamiento
            c1.completarEntrenamiento(e1);
            // Añadimos otro entrenamiento
            c1.anadirEntrenamiento(e2);
            // Creamos dos actividades más para el segundo entrenamiento
            Actividad ac3 = new Actividad("Levantar furgonetas a dos manos", 15, "medio");
            Actividad ac4 = new Actividad("Levantar pesas con los dientes", 10, "bajo");
            // Añadimos las actividades al segundo entrenamiento del cliente
            c1.anadirActividad(e2, ac3);
            c1.anadirActividad(e2, ac4);
            // Imprimimos los datos del cliente
            System.out.println(c1);
            // Comprobamos el tiempo invertido al primer entrenamiento
            int tiempo = c1.tiempoDedicadoEntrenamiento(e1);
            System.out.println("El tiempo que has dedicado al primer entrenamiento es de " + tiempo + " minutos");
            // Comprobamos la actividad a la que más tiempo le hemos dedicado del primer entrenamiento
            c1.actividadConMasTiempo(e1);

        } catch (GimnasioException e) {
            System.out.println(e.getMessage());
        }
    }
}