package Ejercicio4;

import java.time.Year;

public class Obra {

    // Creamos los atributos
    private String titulo;
    private String autor;
    private Year anoCreacion;
    private static int codigoValor;
    private int codigoInventario;
    private double valorEuros;
    private static final int MAX_RESTAURACIONES = 5;
    private Restauracion[] restauraciones;

    // Creamos el constructor
    public Obra(String titulo, String autor, Year anoCreacion, double valorEuros) throws ObraException {
        this.titulo = titulo;
        this.autor = autor;
        setAnoCreacion(anoCreacion);
        this.codigoInventario = codigoValor++;
        setValorEuros(valorEuros);
        this.restauraciones = new Restauracion[MAX_RESTAURACIONES];
    }

    // Hacemos los get y set
    public String getTitulo() {
        return titulo;
    }

    private void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    private void setAutor(String autor) {
        this.autor = autor;
    }

    public Year getAnoCreacion() {
        return anoCreacion;
    }

    private void setAnoCreacion(Year anoCreacion) throws ObraException {
        if (anoCreacion.isAfter(Year.now())) {
            throw new ObraException("El año de creación no puede ser mayor que el actual");
        }
        this.anoCreacion = anoCreacion;
    }

    public int getCodigoInventario() {
        return codigoInventario;
    }

    private void setCodigoInventario(int codigoInventario) {
        this.codigoInventario = codigoInventario;
    }

    public double getValorEuros() {
        return valorEuros;
    }

    private void setValorEuros(double valorEuros) throws ObraException {
        if (valorEuros < 0) {
            throw new ObraException("No puede costar menos de 0 euros");
        }
        this.valorEuros = valorEuros;
    }

    public static int getCodigoValor() {
        return codigoValor;
    }

    private static void setCodigoValor(int codigoValor) {
        Obra.codigoValor = codigoValor;
    }

    public Restauracion[] getRestauraciones() {
        return restauraciones;
    }

    private void setRestauraciones(Restauracion[] restauraciones) {
        this.restauraciones = restauraciones;
    }

    // Hacemos un método para añadir una restauración a la obra
    public void anadirRestauracion(String motivo) throws ObraException {

        Restauracion restauracion = new Restauracion(motivo);
        boolean hayEspacio = false;

        for (int i = 0; i < restauraciones.length; i++) {

            if (restauraciones[i] == null) {
                restauraciones[i] = restauracion;
                hayEspacio = true;
                break;
            }
        }
        if (!hayEspacio) {
            throw new ObraException("No se puede añadir la restauración");
        }
    }
}