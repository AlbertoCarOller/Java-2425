package Ejercicio14;

public class ExperimentoConfidencial extends Experimento {

    // Creamos el constructor
    public ExperimentoConfidencial(String nombre, Cientifico cientifico) throws CientificoException {
        super(nombre, cientifico);
        setCientifico(cientifico);
    }

    /* Hacemos una modificación del set padre de científico para comrprobar que tenga autorización para formar
     parte del experimento */
    @Override
    public void setCientifico(Cientifico cientifico) throws CientificoException {
        if (!cientifico.isAutorizacion()) {
            throw new CientificoException("El ciéntifico " + cientifico.getNombre() + " no tiene autorización para este experimento");
        }
        super.setCientifico(cientifico);
    }
}