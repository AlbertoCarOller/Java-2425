package Fichero;

public class FicheroTextoPlano extends FicheroTexto implements Representable {

    // Creamos el constructor
    public FicheroTextoPlano(String nombre, String[] parrafos) {
        super(nombre, parrafos);
    }

    // Implementamos el método para imprimir sus atributos
    @Override
    public void mostrar() {
        for (int i = 0; i < this.getParrafos().length; i++) {
            System.out.println(this.getParrafos()[i]);
        }
    }
}