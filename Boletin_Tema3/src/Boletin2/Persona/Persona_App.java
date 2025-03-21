package Boletin2.Persona;

import utils.MiEntradaSalida;

import java.time.LocalDate;
import java.util.Scanner;

public class Persona_App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Creamos el objeto persona
        Persona p = new Persona();

        // Pedimos el nombre
        String nombre = MiEntradaSalida.solicitarCadena("Introduzca su nombre");
        p.setNombre(nombre);

        boolean resultado0;

        do {

            resultado0 = true;
            try {
                // Pedimos la fecha y la transformamos a 'LocalDate'
                System.out.println("Introduce su fecha de nacimiento");
                String fechaTexto = sc.nextLine();
                String[] partes = fechaTexto.split("-");
                int dia = Integer.parseInt(partes[0]);
                int mes = Integer.parseInt(partes[1]);
                int ano = Integer.parseInt(partes[2]);
                LocalDate fecha = LocalDate.of(ano, mes, dia);
                p.setFechaNacimiento(fecha);
                resultado0 = false;

            } catch (PersonaException e) {
                System.out.println(e.getMessage());
            }
        } while (resultado0);



        boolean error;

        // Pedimos el dni
        do {
            error = true;

            try {
                int dni = MiEntradaSalida.solicitarEnteroPositivo("Introduce su dni");
                p.setDni(dni);
                error = false;

            } catch (PersonaException e) {
                System.out.println(e.getMessage());
            }
        } while (error);

        boolean error2;

        // Solicitamos el sexo del usuario
        do {
            error2 = true;

            try {
                char sexo = MiEntradaSalida.solicitarCaracter("Introduce su sexo");
                p.setSexo(sexo);
                error2 = false;

            } catch (PersonaException e) {
                System.out.println(e.getMessage());
            }
        } while (error2);

        boolean error3;

        // Solicitamos la altura al usuario
        do {
            error3 = true;

            try {
                System.out.println("Introduce su altura");
                float altura = sc.nextFloat();
                p.setAltura(altura);
                error3 = false;

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } while (error3);

        boolean error4;

        // Solicitamos al usuario su peso
        do {
            error4 = true;

            try {
                System.out.println("Introduce su peso");
                float peso = sc.nextFloat();
                p.setPeso(peso);
                error4 = false;

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } while (error4);

        // Mostramos el nombre, la letra del dni y el IMC del usuario
        System.out.println(p.getNombre() + " la letra de su dni es " + p.letraDni() +
                " y su índice de masa corporal es de " + p.calcularImc());
    }
}