package Ejercicio12;

import java.util.Objects;

public abstract class Ingeniero implements Reparador {

    // Creamos los atributos
    private String nombre;
    private int edad;

    // Creamos el constructor
    public Ingeniero(String nombre, int edad) throws ModuloException {
        this.nombre = nombre;
        setEdad(edad);
    }

    // Hacemos los get y set
    public String getNombre() {
        return nombre;
    }

    private void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    private void setEdad(int edad) throws ModuloException {
        if (edad < 0 || edad > 70) {
            throw new ModuloException("Un ingeniero no puede tener menos de 0 ni más de 70 años");
        }
        this.edad = edad;
    }

    // Hacemos un equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ingeniero ingeniero = (Ingeniero) o;
        return Objects.equals(nombre, ingeniero.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(nombre);
    }

    // Hacemos un toString
    @Override
    public String toString() {
        return String.format("Nombre: %S, Edad: %d", this.nombre, this.edad);
    }

    // Implementamos el método reparar
    @Override
    public void reparar(Modulo modulo, Estacion estacion) throws ModuloException {
        if (!estaEnEstacion(estacion)) {
            throw new ModuloException("El ingeniero no puede reparar ya que no se encuentra en la estación");
        }
        boolean encontrado = false;
        for (int i = 0; i < estacion.getModulos().length; i++) {

            if (modulo.equals(estacion.getModulos()[i])) {
                encontrado = true;
                if (estacion.getModulos()[i].getSalud() > Modulo.SALUD_INICIAL) {
                    throw new ModuloException("No se puede reparar debido a que su salud por mejoras, es superior a la salud inicial");
                }
                if (estacion.getModulos()[i] instanceof ModuloInvestigacion && this instanceof IngenerioEnergia ||
                        estacion.getModulos()[i] instanceof ModuloManufactura && this instanceof IngenieroMaquinaria ||
                        estacion.getModulos()[i] instanceof ModuloCultivo && this instanceof IngenieroBiotecnologia) {
                    estacion.getModulos()[i].setSalud(Modulo.SALUD_INICIAL);
                    estacion.getModulos()[i].setEnvejecimiento(0);

                } else {
                    System.out.println("No puedes reparar módulos en los que no estás especializado");
                    if ((estacion.getModulos()[i].getSalud() - 10) < 0) {
                        System.out.println("El módulo " + estacion.getModulos()[i].getNombre() + " ha muerto");
                        estacion.getModulos()[i] = null;

                    } else {
                        estacion.getModulos()[i].setSalud(estacion.getModulos()[i].getSalud() - 10);
                        if ((estacion.getModulos()[i].getEnvejecimiento() + 10) > Modulo.ENVEJECIMIENTO_MAXIMO) {
                            System.out.println("El módulo " + estacion.getModulos()[i].getNombre() + " ha muerto");
                            estacion.getModulos()[i] = null;

                        } else {
                            estacion.getModulos()[i].setEnvejecimiento(estacion.getModulos()[i].getEnvejecimiento() + 10);
                        }
                    }
                }
                break;
            }
        }
        if (!encontrado) {
            throw new ModuloException("No se ha encontrado");
        }
    }

    // Hacemos un método para comprobar si el ingeniero está en la estación
    public boolean estaEnEstacion(Estacion estacion) {
        for (int i = 0; i < estacion.getIngenieros().length; i++) {
            if (this.equals(estacion.getIngenieros()[i])) {
                return true;
            }
        }
        return false;
    }
}