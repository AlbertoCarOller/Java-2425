package Ejercicio13;

import java.util.Arrays;
import java.util.Objects;

public class Academia {

    // Creamos los atributos
    private String nombre;
    private Detective[] detectives;
    private static final int MAX_DETECTIVE = 8;

    // Creamos el constructor
    public Academia(String nombre) {
        this.nombre = nombre;
        this.detectives = new Detective[MAX_DETECTIVE];
    }

    // Hacemos los get y set
    public String getNombre() {
        return nombre;
    }

    private void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Detective[] getDetectives() {
        return detectives;
    }

    private void setDetectives(Detective[] detectives) {
        this.detectives = detectives;
    }

    // Hacemos un equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Academia academia = (Academia) o;
        return Objects.equals(nombre, academia.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(nombre);
    }

    // Hacemos un toString
    @Override
    public String toString() {
        return String.format("Nombre academia: %s, Detectives de la academia: %s", this.nombre, Arrays.toString(this.detectives));
    }

    // Hacemos un método para añadir un detective a la academia
    public void anadirDetective(Detective detective) throws DetectiveException {
        boolean esIgual = false;
        int hayEspacio = -1;
        for (int i = 0; i < detectives.length && !esIgual; i++) {
            if (detective.equals(detectives[i])) {
                esIgual = true;
            }
            if (detectives[i] == null && hayEspacio == -1) {
                hayEspacio = i;
            }
        }
        if (!esIgual && hayEspacio != -1) {
            detectives[hayEspacio] = detective;

        } else {
            throw new DetectiveException("No se ha podido añadir el detective");
        }
    }

    // Hacemos un método para pasar a experto los detectives ya capacitados
    public void pasarAExperto() {
        for (int i = 0; i < detectives.length; i++) {
            if (!(detectives[i] instanceof DetectiveExperto &&
                    ((detectives[i].getFechaIngreso().plusYears(detectives[i].getAnosTrascurridos()))
                            .isAfter(detectives[i].getFechaIngreso().plusYears(5)) ||
                            (detectives[i].getFechaIngreso().plusYears(detectives[i].getAnosTrascurridos()))
                                    .equals((detectives[i].getFechaIngreso().plusYears(5)))))) {
                DetectiveExperto detectiveExperto = new DetectiveExperto(detectives[i].getNombre(), detectives[i].getFechaIngreso());
                detectives[i] = detectiveExperto;
            }
        }
    }

    // Hacemos un método para asignar un caso a un detective
    public void asignarCaso(Caso caso, String nombreDetective) throws DetectiveException {
        boolean encontrado = false;
        for (int i = 0; i < detectives.length && !encontrado; i++) {
            if (detectives[i] != null) {
                if (nombreDetective.equalsIgnoreCase(detectives[i].getNombre())) {
                    encontrado = true;
                    boolean esIgual = false;
                    int hayEspacio = -1;
                    for (int j = 0; j < detectives[i].getCasosAsignados().length && !esIgual; j++) {
                        if (caso.equals(detectives[i].getCasosAsignados()[j])) {
                            esIgual = true;
                        }
                        if (detectives[i].getCasosAsignados()[j] == null && hayEspacio == -1) {
                            hayEspacio = j;
                        }
                    }
                    if (!esIgual && hayEspacio != -1) {
                        detectives[i].getCasosAsignados()[hayEspacio] = caso;
                        System.out.println("El caso " + caso.getNombre() + " ha sido asignado al detective " + nombreDetective);

                    } else {
                        throw new DetectiveException("No se ha podido añadir el caso");
                    }
                }
            }
        }
        if (!encontrado) {
            throw new DetectiveException("No se ha encontrado el detective al que asignarle el caso");
        }
    }

    // Hacemos un método para eliminar un caso
    public void eliminarCaso(String nombreDetective, Caso caso) throws DetectiveException {
        boolean encontrado = false;
        for (int i = 0; i < detectives.length && !encontrado; i++) {
            if (detectives[i] != null) {
                if (nombreDetective.equalsIgnoreCase(detectives[i].getNombre())) {
                    encontrado = true;
                    boolean encontradoCaso = false;
                    for (int j = 0; j < detectives[i].getCasosAsignados().length; j++) {
                        if (caso.equals(detectives[i].getCasosAsignados()[j])) {
                            encontradoCaso = true;
                            detectives[i].getCasosAsignados()[j] = null;
                        }
                    }
                    if (!encontradoCaso) {
                        throw new DetectiveException("No se ha encontrado el caso");
                    }
                }
            }
        }
        if (!encontrado) {
            throw new DetectiveException("No se ha encontrado el detective");
        }
    }

    // Hacemos un método para buscar un detective de la academia
    public Detective buscarDetective(String nombre) {
        for (int i = 0; i < detectives.length; i++) {
            if (detectives[i] == null) {
                continue;
            }
            if (nombre.equalsIgnoreCase(detectives[i].getNombre())) {
                return detectives[i];
            }
        }
        return null;
    }
}