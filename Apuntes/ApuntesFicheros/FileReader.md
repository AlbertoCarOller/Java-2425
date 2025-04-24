- Sirve para leer archivos de texto (char por char).
- No es eficiente para grandes volúmenes de datos, se suele envolver con BufferedReader.
- No tiene buffer interno.
- Creando un array de caracteres puede tener un búfer.
- No crea el fichero automáticamente, en caso de que no exista, lanzará exception.

___Ejemplos:___

_Ejercicio 1:_
```java
/**  
 * En este método probamos el funcionamiento de FileReader con un * buffer propio creado * @throws RespaldoBasicoException */
 public static void fileReaderP() throws RespaldoBasicoException {  
    try {  
        Path leerlo = Path.of("Boletin_Tema6/src/Ayuda/EjerciciosDeApoyo/Ejercicio2A/FicheroEJ2A.txt");  
        // Creamos un buffer de 5 caracteres de capacidad  
        char[] buffer = new char[5];  
        try (FileReader fr = new FileReader(leerlo.toFile())) {  
            int limite;  
            /* el .read() sobrecargado acepta un buffer, que es un array de char, este método sobrecargado devuelve  
             * un entero que representa el número de caracteres que ha leído, -1 cuando ya no hay más caracteres
             * por leer */
                while ((limite = fr.read(buffer)) != -1) {
                /* Imprimimos los caracteres dentro del buffer, hasta el límite, es decir hasta antes que  
                 * devuelva -1, va de la posición 0 hasta el límite */
                 System.out.println(new String(buffer, 0, limite));  
                /* Si mostráramos directamente el buffer, si imprime los 5 caracteres por obligación, imprimirá  
				* el residuo que haya dentro del buffer, ya que los caracteres no alcanzarían para mostrar los 5 */                //System.out.println(buffer);  
            }  
            int caracter;  
            while ((caracter = fr.read()) != -1) {  
                System.out.println(caracter + "-" + (char) caracter);  
            }  
        }  
  
    } catch (InvalidPathException | IOException e) {  
        throw new RespaldoBasicoException(e.getMessage());  
    }  
}
```

_Ejercicio 2:_
```java
/**  
 * Este método muestra por pantalla la letra leída y el número ASCII que corresponde a este, * lee letra por letra * * @throws RespaldoBasicoException */public static void fileReaderPV2() throws RespaldoBasicoException {  
    try {  
        Path leerlo = Path.of("Boletin_Tema6/src/Ayuda/EjerciciosDeApoyo/Ejercicio2A/FicheroEJ2A.txt");  
        try (FileReader fr = new FileReader(leerlo.toFile())) {  
            // Guardamos el entero, que a su vez al hacer un casting de char nos devolverá la letra correspondiente  
            int caracter;  
            while ((caracter = fr.read()) != -1) {  
                /* Mostramos el número de char y después mostramos el char que corresponde, el número es ASCII,  
                 * por eso corresponde a un char */
                 System.out.println(caracter + "-" + (char) caracter);  
            }  
        }  
  
    } catch (InvalidPathException | IOException e) {  
        throw new RespaldoBasicoException(e.getMessage());  
    }  
}
```