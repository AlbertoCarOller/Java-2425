package Ejercicio11;

public class EmpleadoApp {
    public static void main(String[] args) {
        try {
            Gerente gerente1 = new Gerente("Atis", 2300.50);
            gerente1.calcularBonificacion();
            System.out.println(gerente1.getNombre() + " tiene un salario de " + gerente1.getSalario() + " euros con la bonificación");
            gerente1.mensajeMotivacional();

            Vendedor vendedor1 = new Vendedor("Chelu", 1005.79, 2000);
            vendedor1.calcularBonificacion();
            System.out.println(gerente1.getNombre() + " tiene un salario de " + vendedor1.getSalario() + " con la bonificación");

        } catch (EmpleadoException e) {
            System.out.println(e.getMessage());
        }
    }
}