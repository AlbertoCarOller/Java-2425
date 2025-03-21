package Extra.Ejercicio11;

import java.util.Objects;

public class Participante {

    // Creamos los atributos
    private String nombre;
    private String email;
    private int numTelefono;

    // Creamos el constructor
    public Participante(String nombre, String email, int numTelefono) throws ParticipanteException {
        this.nombre = nombre;
        setEmail(email);
        setNumTelefono(numTelefono);
    }

    // Hacemos los get y set
    public String getNombre() {
        return nombre;
    }

    private void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    private void setEmail(String email) throws ParticipanteException {
        if (!email.endsWith("@gmail.com") && !email.endsWith("@outlook.com") && !email.endsWith("@hotmail.com")) {
            throw new ParticipanteException("El dominio del email no coincide con ninguno válido");
        }
        String[] partes = email.split("@");
        for (int i = 0; i < partes[0].length(); i++) {
            if (Character.isUpperCase(partes[0].charAt(i)) || !Character.isLetter(partes[0].charAt(i))) {
                throw new ParticipanteException("Antes del dominio solo se aceptan letras (minúsculas)");
            }
        }
        this.email = email;
    }

    public int getNumTelefono() {
        return numTelefono;
    }

    private void setNumTelefono(int numTelefono) throws ParticipanteException {
        String numString = String.valueOf(numTelefono);
        if (numString.length() != 9) {
            throw new ParticipanteException("Un número de teléfono no debe ser distinto de 9 dígitos");
        }
        this.numTelefono = numTelefono;
    }

    // Hacemos un equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Participante that = (Participante) o;
        return Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(email);
    }

    // Hacemos un toString
    @Override
    public String toString() {
        return String.format("Nombre: %s, Email: %s, Teléfono: %d", this.nombre, this.email, this.numTelefono);
    }
}