package Ejercicio12;

public class IngenerioEnergia extends Ingeniero implements Mejorador {

    // Creamos el constructor
    public IngenerioEnergia(String nombre, int edad) throws ModuloException {
        super(nombre, edad);
    }

    @Override
    public void mejorar(Modulo modulo, Estacion estacion) throws ModuloException {
        if (!estaEnEstacion(estacion)) {
            throw new ModuloException("No se puede mejorar ya que el ingeniero no se encuentra en la estación");
        }
        boolean encontrado = false;
        for (Modulo moduloRecorrido : estacion.getModulos()) {
            if (modulo.equals(moduloRecorrido)) {
                encontrado = true;
                if (!(moduloRecorrido instanceof ModuloInvestigacion)) {
                    throw new ModuloException("No puedes mejorar un módulo que no sea el de investigación");
                }
                if (moduloRecorrido.getSalud() != Modulo.SALUD_MAXIMA) {
                    if ((moduloRecorrido.getSalud() + 50) > Modulo.SALUD_MAXIMA) {
                        moduloRecorrido.setSalud(Modulo.SALUD_MAXIMA);

                    } else {
                        moduloRecorrido.setSalud(moduloRecorrido.getSalud() + 50);
                    }
                }
                if (moduloRecorrido.getEnvejecimiento() != 0) {
                    if ((moduloRecorrido.getEnvejecimiento() - 20) < 0) {
                        moduloRecorrido.setEnvejecimiento(0);

                    } else {
                        moduloRecorrido.setEnvejecimiento(moduloRecorrido.getEnvejecimiento() - 20);
                    }
                }
            }
        }
        if (!encontrado) {
            throw new ModuloException("No se ha encontrado el módulo");
        }
    }
}