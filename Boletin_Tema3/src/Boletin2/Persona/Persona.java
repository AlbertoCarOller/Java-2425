package Boletin2.Persona;

import java.time.LocalDate;

public class Persona {

    // Atributos
    private String nombre;
    private LocalDate fechaNacimiento;
    private int dni;
    private char sexo;
    private float altura;
    private float peso;

    // Generamos el constructor vacío
    public Persona() {
    }

    // Método que devuelve la letra del dni
    public char letraDni() {

        int numLetra = this.dni % 23;
        char letraDni = 0;
        char[] letrasDni = {'T', 'R', 'W', 'A', 'G', 'M', 'Y', 'F', 'P', 'D', 'X', 'B',
                'N', 'J', 'Z', 'S', 'Q', 'V', 'H', 'L', 'C', 'K', 'E'};

        for (int i = 0; i < letrasDni.length; i++) {

            if (i == numLetra) {
                letraDni = letrasDni[i];
            }
        }
        return letraDni;
    }

    // Calculamos el índice de masa corporal
    public float calcularImc() {

        float imc = (float) (this.peso / Math.pow(this.altura, 2));
        return imc;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) throws PersonaException {

        if (LocalDate.now().isBefore(fechaNacimiento)) {

            throw new PersonaException("La fecha no puede ser posterior a la fecha actual");
        }

        this.fechaNacimiento = fechaNacimiento;
    }

    // Se introduce el dni y se comprueba que tenga 8 números
    public void setDni(int dni) throws PersonaException {

        if (dni < 10000000 || dni > 99999999) {

            throw new PersonaException("La cantidad de números debe ser 8");
        }

        this.dni = dni;
    }

    // Se introduce el sexo y comprobamos que sea una opción válida
    public void setSexo(char sexo) throws PersonaException {

        if (sexo != 'M' && sexo != 'F') {

            throw new PersonaException("No ha elegido una opción válida");
        }

        this.sexo = sexo;
    }

    public void setAltura(float altura) throws PersonaException {

        if (altura < 0) {

            throw new PersonaException("La altura no puede ser negativa");
        }
        this.altura = altura;
    }

    public void setPeso(float peso) throws PersonaException {

        if (peso < 0) {

            throw new PersonaException("El peso no puede ser negativo");
        }
        this.peso = peso;
    }

    public String getNombre() {
        return nombre;
    }
}