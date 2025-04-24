- Permite leer líneas completas de texto, no carácter a carácter.
- Tiene el método .readLine() que es muy útil (lee línea por línea).
- Necesita otro Reader como base. No sabe por sí mismo abrir archivos, necesita un  lector que ya esté leyendo de algún sitio, como un FileReader, InputStreamReader, etc.
- Depende directamente del FileReader, por lo que también lanzará exception en caso de  que no exista el fichero.

___Ejemplos:___

_Ejercicio 1:_
```java
/**  
 * Este método va a leer la primera línea del fichero especificado, pero antes de leer * va a colocar un mark, cuando se haya leído la primera línea, se hace un reset y vuelve * al principio, se imprimen por pantalla la primera línea dos veces * * @throws RespaldoBasicoException */public static void bufferedReaderMarkReset() throws RespaldoBasicoException {  
    try {  
        Path leerlo = Path.of("Boletin_Tema6/src/Ayuda/EjerciciosDeApoyo/Ejercicio2A/FicheroEJ1A.txt");  
        FileReader fr = new FileReader(leerlo.toFile());  
        // Para utilizar el mark y el reset, el File debe estar envuelto en un Buffered  
        try (BufferedReader br = new BufferedReader(fr)) {  
            String linea;  
            /* Hacemos un mark, este funciona a modo de marca para cuando haga un reset volver hasta donde  
             * coloqué el mark, el mark va a ser físicamente literal, por ejemplo, si leo 3 líneas, hago un             * mark y después leo otras 3 líneas y hago un reset va a volver a la línea donde llamé al mark.             * El entero pasado por parámetros es decir el 'readAheadLimit', es el número máximo de caracteres             * que puedes leer después del mark, si lees más de lo especificado, el mark se perderá */            
            br.mark(100);  
            if ((linea = br.readLine()) != null) {  
                System.out.println("Primera línea: " + linea);  
            }  
            // El reset vuelve al mark, si no se ha perdido el mark  
            br.reset();  
            System.out.println("Reset: " + br.readLine());  
        }  
  
    } catch (InvalidPathException | IOException e) {  
        throw new RespaldoBasicoException(e.getMessage());  
    }  
}
```

_Ejemplo 2:_
```java
/**  
 * Este método lee por teclado e imprime mientras no haya una línea vacía, * es decir que no haga un intro * * @throws RespaldoBasicoException */
 public static void bufferedReaderImputStreamReader() throws RespaldoBasicoException {  
    /* El ImputStreamReader está leyendo lo pasado por teclado, además lee por teclado una serie de caracteres  
     * gracias a que está envuelto en un BufferedReader, si esto no fuera así, leería char por char*/    
     try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {  
        String linea;  
        while (!(linea = br.readLine()).isEmpty()) {  
            System.out.println(linea);  
        }  
  
    } catch (IOException e) {  
        throw new RespaldoBasicoException(e.getMessage());  
    }  
}
```