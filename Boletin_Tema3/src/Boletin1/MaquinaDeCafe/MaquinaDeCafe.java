package Boletin1.MaquinaDeCafe;

public class MaquinaDeCafe {

    // Atributos
    private int depositoCafe;
    private int depositoLeche;
    private int depositoVasos;
    private double monedero;

    // Precios
    private static final double PRECIO_CAFE = 1;
    private static final double PRECIO_LECHE = 0.8;
    private static final double PRECIO_CAFE_LECHE = 1.5;

    // Cantidad máxima depósitos
    private static final int MAX_DEPOSITO_CAFE = 50;
    private static final int MAX_DEPOSITO_LECHE = 50;
    private static final int MAX_DEPOSITO_VASOS = 80;

    // Creamos el constructor dándole valores iniciales a los atributos
    public MaquinaDeCafe() {

        this.monedero = 0;
        rellenarDepositos();
    }

    // Rellenamos los depósitos al máximo
    public void rellenarDepositos() {

        this.depositoCafe = MAX_DEPOSITO_CAFE;
        this.depositoLeche = MAX_DEPOSITO_LECHE;
        this.depositoVasos = MAX_DEPOSITO_VASOS;
    }

    // Se resta 1 dosis para el depósito de café en caso de que haya
    public void pedirCafeSolo(double dineroAMeter) throws MaquinaDeCafeException {

        if (dineroAMeter < PRECIO_CAFE) {

            throw new MaquinaDeCafeException("El saldo es insuficiente");
        }

        if (this.depositoVasos < 1) {

            throw new MaquinaDeCafeException("No quedan vasos, lo siento");
        }

        if (this.depositoCafe < 1) {

            throw new MaquinaDeCafeException("No quedan dosis de café");
        }
        retirarDinero(dineroAMeter, PRECIO_CAFE);
        System.out.println("Café solo servido");
        this.depositoCafe--;
        this.depositoVasos--;

    }

    // Se resta 1 dosis del depósito de leche en caso de que haya
    public void pedirLeche(double dineroAMeter) throws MaquinaDeCafeException {

        if (dineroAMeter < PRECIO_LECHE) {

            throw new MaquinaDeCafeException("El saldo es insufiente");
        }

        if (this.depositoVasos < 1) {

            throw new MaquinaDeCafeException("No quedan vasos, lo siento");
        }

        if (this.depositoLeche < 1) {

            throw new MaquinaDeCafeException("No quedan dosis de leche");
        }

        retirarDinero(dineroAMeter, PRECIO_LECHE);
        System.out.println("Leche servida");
        this.depositoLeche--;
        this.depositoVasos--;
    }

    // Se resta 1 dosis de los depósitos de café y leche en caso de que haya
    public void pedirCafeConLeche(double dineroAMeter) throws MaquinaDeCafeException {

        if (dineroAMeter < PRECIO_CAFE_LECHE) {

            throw new MaquinaDeCafeException("El saldo es insuficiente");
        }

        if (this.depositoVasos < 1) {

            throw new MaquinaDeCafeException("No quedan vasos, lo siento");
        }

        if (this.depositoCafe < 1 || this.depositoLeche < 1) {

            throw new MaquinaDeCafeException("No quedan dosis de café o leche, lo siento");
        }

        retirarDinero(dineroAMeter, PRECIO_CAFE_LECHE);
        System.out.println("Café con leche servida");
        this.depositoCafe--;
        this.depositoLeche--;
        this.depositoVasos--;
    }

    // Calculamos el cambio que nos devolverá la máquina
    public void retirarDinero(double dineroIntroducido, double precio) throws MaquinaDeCafeException {

        double cambio;

        cambio = dineroIntroducido - precio;

        if (this.monedero < cambio) {

            throw new MaquinaDeCafeException("La máquina no dispone del cambio necesario, lo siento");
        }

        this.monedero -= cambio;
        this.monedero += dineroIntroducido;

        System.out.println("Recoja su cambio de " + cambio);
    }

    // Vaciamos el monedero
    public void vaciarMonedero() {

        this.monedero = 0;
    }

    // Hacemos los get para acceder al valor de los atributos fuera de la clase
    public int getDepositoCafe() {
        return depositoCafe;
    }

    public int getDepositoLeche() {
        return depositoLeche;
    }

    public int getDepositoVasos() {
        return depositoVasos;
    }

    public double getMonedero() {
        return monedero;
    }
}