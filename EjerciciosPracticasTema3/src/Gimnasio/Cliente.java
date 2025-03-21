package Gimnasio;

import java.util.Arrays;

public class Cliente {

    // Creamos los atributos
    private String nombre;
    private String dni;
    private int edad;
    private String nivel;
    private static final int MAX_ENTRENAMIENTO = 6;
    private Entrenamiento[] entrenamientos;

    // Creamos el constructor
    public Cliente(String nombre, String dni, int edad, String nivel) throws GimnasioException {
        this.nombre = nombre;
        setDni(dni);
        setEdad(edad);
        setNivel(nivel);
        this.entrenamientos = new Entrenamiento[MAX_ENTRENAMIENTO];
    }

    // Creamos los get y set
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDni() {
        return dni;
    }

    private void setDni(String dni) throws GimnasioException {
        // Si el dni no tiene 9 caracteres salta una excepción
        if (dni.length() != 9) {
            throw new GimnasioException("El DNI no puede tener menos de 8 caracteres");
        }
        // Si el dni no contiene una letra al final salta una excepción
        if (!Character.isLetter(dni.charAt(8))) {
            throw new GimnasioException("No se ha colocado la letra del DNI");
        }
        this.dni = dni;
    }

    private int getEdad() {
        return edad;
    }

    private void setEdad(int edad) throws GimnasioException {
        // Si el cliente es menor de 16 años no podrá ingresar al gym
        if (edad < 16) {
            throw new GimnasioException("No puedes ingresar al gym aún");
        }
        this.edad = edad;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) throws GimnasioException {

        if (!nivel.equalsIgnoreCase("Bajo") && !nivel.equalsIgnoreCase("Medio")
                && !nivel.equalsIgnoreCase("Alto")) {
            throw new GimnasioException("No has seleccionado un nivel existente");
        }
        this.nivel = nivel;
    }

    // Hacemos un toString
    @Override
    public String toString() {
        return "Cliente{" +
                "nombre='" + nombre + '\'' +
                ", dni='" + dni + '\'' +
                ", edad=" + edad +
                ", nivel='" + nivel + '\'' +
                ", entrenamientos=" + Arrays.toString(entrenamientos) +
                '}';
    }

    // Hacemos un método para crear un entrenamiento
    public void anadirEntrenamiento(Entrenamiento entrenamiento) throws GimnasioException {

        // Comprobamos con este método que llamamos que se hayan completados todos los entrenamientos
        if (!comprobarCompletados()) {
            throw new GimnasioException("No has completado todos los entrenamientos");
        }

        boolean esIgual = false;
        int hayEspacio = -1;

        for (int i = 0; i < entrenamientos.length && !esIgual; i++) {

            if (entrenamiento.equals(entrenamientos[i])) {
                esIgual = true;
            }

            if (entrenamientos[i] == null && hayEspacio == -1) {
                hayEspacio = i;
            }
        }

        if (!esIgual && hayEspacio != -1) {
            entrenamientos[hayEspacio] = entrenamiento;

        } else {
            throw new GimnasioException("No se puede crear el entrenamiento");
        }
    }

    // Hacemos un método para añadir una actividad al entrenamiento
    public void anadirActividad(Entrenamiento entrenamiento, Actividad actividad) throws GimnasioException {

        if (!actividad.getNivelIntensidad().equalsIgnoreCase(this.nivel) && !this.nivel.equalsIgnoreCase("Alto")) {
            if (actividad.getNivelIntensidad().equalsIgnoreCase("Medio") && this.nivel.equalsIgnoreCase("Bajo")) {
                throw new GimnasioException("No puedes añadir una actividad de mayor nivel que el tuyo");
            }

            if (actividad.getNivelIntensidad().equalsIgnoreCase("Alto") && (this.nivel.equalsIgnoreCase("Medio") ||
                    this.nivel.equalsIgnoreCase("Bajo"))) {
                throw new GimnasioException("No puedes añadir una actividad de mayor nivel que el tuyo");
            }
        }

        int entrenamientoPosicion = -1;
        boolean esIgual = false;
        int hayEspacio = -1;

        for (int i = 0; i < entrenamientos.length; i++) {

            if (entrenamiento.equals(entrenamientos[i])) {
                for (int j = 0; j < entrenamientos[i].getActividades().length && !esIgual; j++) {

                    if (actividad.equals(entrenamientos[i].getActividades()[j])) {
                        esIgual = true;
                    }

                    if (entrenamientos[i].getActividades()[j] == null && hayEspacio == -1) {
                        hayEspacio = j;
                        entrenamientoPosicion = i;
                    }
                }
            }
        }

        if (!esIgual && hayEspacio != -1) {
            entrenamientos[entrenamientoPosicion].getActividades()[hayEspacio] = actividad;

        } else {
            throw new GimnasioException("No se puede añadir la actividad " + actividad.getNombre());
        }
    }

    // Hacemos un método para completar un entrenamiento
    public void completarEntrenamiento(Entrenamiento entrenamiento) throws GimnasioException {

        boolean entrenamientoEncontrado = false;

        for (int i = 0; i < entrenamientos.length; i++) {

            if (entrenamiento.equals(entrenamientos[i])) {
                entrenamientoEncontrado = true;

                for (int j = 0; j < entrenamientos[i].getActividades().length; j++) {

                    if (entrenamientos[i].getActividades()[j] != null) {
                        entrenamientos[i].getActividades()[j].setCompletada(true);
                    }
                }
            }
        }

        if (!entrenamientoEncontrado) {
            throw new GimnasioException("No se ha encontrado el entrenamiento");
        }
    }

    // Hacemos un método para comprobar que se han completado todos los entrenamientos para poder añadir otro
    public boolean comprobarCompletados() {

        for (int i = 0; i < entrenamientos.length; i++) {

            if (entrenamientos[i] == null) {
                continue;
            }

            for (int j = 0; j < entrenamientos[i].getActividades().length; j++) {

                if (entrenamientos[i].getActividades()[j] == null) {
                    continue;
                }

                if (!entrenamientos[i].getActividades()[j].isCompletada()) {
                    return false;
                }
            }
        }
        return true;
    }

    // Creamos un método para mostrar el tiempo total dedicado a un entrenamiento
    public int tiempoDedicadoEntrenamiento(Entrenamiento entrenamiento) throws GimnasioException {

        int totalTiempoEntrenamiento = 0;

        boolean encontrado = false;

        for (int i = 0; i < entrenamientos.length; i++) {

            if (entrenamiento.equals(entrenamientos[i])) {
                encontrado = true;

                for (int j = 0; j < entrenamientos[i].getActividades().length; j++) {

                    if (entrenamientos[i].getActividades()[j] == null) {
                        continue;
                    }
                    totalTiempoEntrenamiento += entrenamientos[i].getActividades()[j].getDuracionEstimada();
                }
            }
        }

        if (!encontrado) {
            throw new GimnasioException("No se ha encontrado el entrenamiento");
        }
        return totalTiempoEntrenamiento;
    }

    // Hacemos un método para comprobar cuál es la actividad a la que le has dedicado más tiempo de un entrenamiento
    public void actividadConMasTiempo(Entrenamiento entrenamiento) throws GimnasioException {

        boolean encontrado = false;
        int mayor = 0;
        int indiceEntrenamiento = 0;
        int indiceActividad = 0;
        int contadorActividades = 0;

        for (int i = 0; i < entrenamientos.length && !encontrado; i++) {

            if (entrenamiento.equals(entrenamientos[i])) {
                encontrado = true;
                indiceEntrenamiento = i;

                for (int j = 0; j < entrenamientos[i].getActividades().length; j++) {

                    if (entrenamientos[i].getActividades()[j] == null) {
                        continue;
                    }

                    contadorActividades++;

                    if (mayor < entrenamientos[i].getActividades()[j].getDuracionEstimada()) {
                        mayor = entrenamientos[i].getActividades()[j].getDuracionEstimada();
                        indiceActividad = j;
                    }
                }
            }

            if (contadorActividades == 0) {
                throw new GimnasioException("No se ha encontrado el entrenamiento o no hay actividades en él");
            }

            System.out.println("La actividad que más se ha practicado es " + entrenamientos[indiceEntrenamiento]
                    .getActividades()[indiceActividad].getNombre());
        }
    }
}