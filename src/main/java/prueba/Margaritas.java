/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */
package prueba;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author jorge
 */
public class Margaritas {

    public static void main(String[] args) {

        // Declaro e inicializo variables que necesito
        int margaritas = 0, petalos = 0, numeroDado = 0;
        boolean miroDado;
        String empiezoCon = "", amorJulieta = "", respuesta = "";

        // Hago el bucle de si quiere continuar ejecutando el programa
        do {
            
            // Le pregunto a Romeo cuántas margaritas ha recogido
            margaritas = pedirMargaritas();
            int recuentoMargarita = 1;
            
            // Hago un bucle para pedir los datos de cada margarita y llevar el recuento
            do {
                // Le pregunto a Romeo cuántos pétalos tiene esa margarita
                petalos = pedirPetalosMargarita();

                // Romeo tira el dado para decidir por donde empieza
                numeroDado = tirarDado();
                
                // Romeo mira el dado para ver si ha salido par o impar
                miroDado = comoEmpiezo(numeroDado);
                
                // Romeo se acuerda de con que empezaba si era par o impar y lo determina
                empiezoCon = empiezoCon(miroDado);

                // Romeo empieza a quitar pétalos para ver si Julieta le quiere
                if (empiezoCon.equals("me quiere")) {
                    amorJulieta = (petalos % 2 == 0) ? "no le quiere" : "le quiere";
                } else {
                    amorJulieta = (petalos % 2 == 0) ? "le quiere" : "no le quiere";
                }

                // Muestro los resultados
                String texto = """
                       La margarita tiene %d pétalos, como sacó %d en el dado empezó a quitar pétalos con
                        "%s" y llegó a la conclusión de que Julieta "%s". """.formatted(petalos, numeroDado,
                        empiezoCon, amorJulieta);
                System.out.println(texto);
                System.out.println("-----------------------------------------------------------------");
                
                // Sumo 1 al recuento para ver cuántas margaritas llevo
                recuentoMargarita++;
            } while (recuentoMargarita <= margaritas);

            // Pregunto si quiere seguir
             respuesta = preguntoSiSeguir();
        } while (respuesta.equalsIgnoreCase("si")); // Si decide seguir sigue el bucle

    }
    private static Random aleatorio = new Random();
    private static Scanner teclado = new Scanner(System.in);

    private static int pedirMargaritas() {
        int margaritas = 0;
        final int MINIMO_MARGARITAS = 1, MAXIMO_MARGARITAS = 10;
        do {
            try {
                System.out.println("¿Cuántas margaritas has recogido Romeo? ");
                margaritas = teclado.nextInt();
                System.out.println("-----------------------------------------------------------------");
                if (margaritas < MINIMO_MARGARITAS) {
                    System.out.println("Tienes que coger alguna margarita Romeo");
                    System.out.println("-----------------------------------------------------------------");
                }
            } catch (InputMismatchException ime) {
                System.out.println("-----------------------------------------------------------------");
                System.out.println("Eso no es un número romeo.");
                System.out.println("-----------------------------------------------------------------");
            }
            teclado.nextLine(); // Limpio el buffer
            // Tiene que recoger entre 1 y 10 margaritas
        } while (margaritas < MINIMO_MARGARITAS || margaritas > MAXIMO_MARGARITAS);
        return margaritas;
    }

    private static int pedirPetalosMargarita() {
        int petalos = 0;
        final int MINIMO_PETALOS = 1;
        do {
            try {
                System.out.println("¿Cuántos pétalos tiene esta margarita Romeo? ");
                petalos = teclado.nextInt();
                System.out.println("-----------------------------------------------------------------");
                if (petalos < MINIMO_PETALOS) {
                    System.out.println("Las margaritas tienen más de 0 pétalos Romeo");
                    System.out.println("-----------------------------------------------------------------");
                }
            } catch (InputMismatchException ime) {
                System.out.println("-----------------------------------------------------------------");
                System.out.println("Eso no es un número romeo.");
                System.out.println("-----------------------------------------------------------------");
            }
            teclado.nextLine(); // Limpio el buffer
        } while (petalos < MINIMO_PETALOS); // Mínimo tiene que tener un pétalo
        return petalos;
    }

    private static int tirarDado() {
        int numeroDado = 0;
        do {
            numeroDado = aleatorio.nextInt(1, 7); // El dado tiene 6 caras;
            if (numeroDado == 3) { // Informo de que ha salido 3
                System.out.println("Romeo ha sacado un 3, se lamenta de su mala suerte y vuelve a tirar el dado.");
                System.out.println("-----------------------------------------------------------------");
            }
        } while (numeroDado == 3); // Repito la tirada si es 3
        return numeroDado;
    }

    private static boolean comoEmpiezo(int numeroDado) {
        return (numeroDado % 2 == 0); // Si es par devuelve true, si es impar devuelve false
    }

    private static String empiezoCon(boolean miroDado) {
        String empiezoCon = "";
        if (miroDado == true) { // Si es par le quiere
            empiezoCon = "me quiere";
        } else { // Si es impar no le quiere
            empiezoCon = "no me quiere";
        }
        return empiezoCon;
    }

    private static String preguntoSiSeguir() {
        String respuesta = "";
        do {
            System.out.println("¿Quieres seguir?");
            respuesta = teclado.nextLine();
            System.out.println("-----------------------------------------------------------------");
            
            // Si no es ni "si" ni "no" le digo que no es válido
            if (!(respuesta.equalsIgnoreCase("si") || respuesta.equalsIgnoreCase("no"))) {
                System.out.println("Por favor introduce una respuesta válida");
            }
            
            // Si no es "si" o "no" se repite
        } while (!(respuesta.equalsIgnoreCase("si") || respuesta.equalsIgnoreCase("no")));
        return respuesta;
    }
}
