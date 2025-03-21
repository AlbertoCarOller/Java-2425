package Boletin2.Ordenador;

public class PlacaBase {

    // Atributos
    private String marca;
    private String chipset;
    private String socket;

    // Generamos el constructor vacío
    public PlacaBase() {
    }

    /* Hacemos este método que acepta un objeto de tipo 'Microprocesador' y comparamos el 'socket' de esta clase
     con la de 'Microprocesador', para acceder al valor del socket del microprocesador hacemos un get en esa clase
      y así podemos comparar su valor mediante '.equals', esta comparación da un boolean */
    public void esCompatible(Microprocesador mc) throws PlacaBaseException {

        boolean compatible = this.socket.equals(mc.getSocket());

        // En caso de que 'compatible' sea 'false' laza la excepción
        if (!compatible) {

            throw new PlacaBaseException("La placa base y el microprocesador no son compatibles");
        }
    }

    // Imprimimos todos los atributos de la clase 'PlacaBase'
    @Override
    public String toString() {
        return "PlacaBase{" +
                "marca='" + marca + '\'' +
                ", chipset='" + chipset + '\'' +
                ", socket='" + socket + '\'' +
                '}';
    }

    // Hacemos los set para poder darle valores al solicitárselos al usuario
    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setChipset(String chipset) {
        this.chipset = chipset;
    }

    public void setSocket(String socket) {
        this.socket = socket;
    }
}