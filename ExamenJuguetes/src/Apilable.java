public interface Apilable {

    // Hacemos el método apilar, este va a lanzar excepciones si los datos no son correctos
    default void apilar(Juguete juguete) throws JugueteExcepcion {
        if (this.equals(juguete)) {
            throw new JugueteExcepcion("No puedes apilar una pieza con ella misma");
        }

        if (!this.getClass().equals(juguete.getClass())) {
            throw new JugueteExcepcion("No puedes apilzar un juguete que no es del mismo tipo");
        }
    }
}