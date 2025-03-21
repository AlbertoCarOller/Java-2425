package Boletin2.Producto;

import utils.MiEntradaSalida;

import java.util.Scanner;

public class ProductoApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Creamos el objeto
        Producto gafasDeSol = new Producto();

        boolean error;

        // Preguntamos el precio sin IVA, verificamos que introduce un precio válido
        do {
            error = true;
            try {
                System.out.println("Introduce el precio del producto sin IVA");
                double precioSinIva = sc.nextDouble();
                sc.nextLine();
                gafasDeSol.setPrecio(precioSinIva);
                error = false;

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } while (error);

        // Calculamos el precio con IVA
        gafasDeSol.calcularIva();

        // Preguntamos por la descripción del producto
        String descripcionProducto = MiEntradaSalida.solicitarCadena("Describa el producto");
        gafasDeSol.setDescripcion(descripcionProducto);

        // Llamamos al método 'datosProducto'
        datosProducto(gafasDeSol);
    }

    // Mostramos toda la información del producto
    public static void datosProducto(Producto gf) {

        System.out.println("El código del producto es " + gf.getCodigo() + " su precio con iva es "
                + gf.getPrecio() + " su descripción es: " + gf.getDescripcion());
    }
}
