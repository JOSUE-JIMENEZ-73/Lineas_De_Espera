
/*
Variables:
λ =promdedio de llegadas
μ =promedio de servicio
C = numero de servidores
a = intensidad de trafico 
ρ = factor de utilizacion del sistema
*/
import java.util.Scanner;

public class LineasDeEspera {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcion = 0;
        do {
            // Menú de opciones
            System.out.println("\n===============MNEU===============");
            System.out.println("Seleccione el modelo de línea de\nespera que desea calcular:");
            System.out.println("1. Modelo M/M/1");
            System.out.println("2. Modelo M/M/C");
            System.out.println("3. Modelo M/M/1/K");
            System.out.println("4. Salir");
            System.out.print("Ingrese su opción: ");
            opcion = sc.nextInt();

            switch (opcion) {
                case 1: {
                    System.out.println("\n===============Modelo M/M/1===============");
                    System.out.println("Ingrese el promedio de llegadas: ");
                    int promedioLlegadas = sc.nextInt();
                    System.out.println("Ingrese el promedio de servicio: ");
                    int promdedioServicio = sc.nextInt();
                    modeloMM1(promedioLlegadas, promdedioServicio);
                    break;
                }
                case 2: {
                    System.out.println("\n===============Modelo M/M/C===============");
                    System.out.println("Ingrese el promedio de llegadas: ");
                    int promedioLlegadas = sc.nextInt();
                    System.out.println("Ingrese el promedio de servicio: ");
                    int promdedioServicio = sc.nextInt();
                    System.out.println("Ingrese el número de servidores: ");
                    int c = sc.nextInt();
                    modeloMMC(promedioLlegadas, promdedioServicio, c);
                    break;
                }
                case 3: {
                    modeloMM1K();
                    break;
                }
                case 4: {
                    System.out.println("Saliendo del programa...");
                    break;
                }
                default:
                    System.out.println("Opción inválida. Por favor, seleccione una opción válida.");
            }
        } while (opcion != 4);
        sc.close();
    }

    // Metodos para los calculos
    // Modelo M/M/1
    // λ = promedio de llegadas
    // μ = promedio de servicio
    public static void modeloMM1(int lambda, int mu) {
        if (lambda < mu) {

            double p = (double) lambda / mu; // factor de utilizacion del sistema
            p = (int) (p * 100.0) / 100.0;

            double Lq = (Math.pow(lambda, 2)) / (mu * (mu - lambda));// numero promedio de clientes en la cola
            Lq = (int) (Lq * 100.0) / 100.0;

            double Wq = (double) Lq / lambda; // tiempo promedio de espera en la cola
            Wq = (int) (Wq * 100.0) / 100.0;

            double W = (double) Wq + ((double) 1 / mu); // tiempo total en el sistema
            W = (int) (W * 100.0) / 100.0;

            double L = (double) lambda * W;// numero promedio de clientes en el sistema
            L = (int) (L * 100.0) / 100.0;

            System.out.println("\nEl sistema es estable.");
            System.out.println("Factor de utilizacion del sistema: " + p);
            System.out.println("Número promedio de clientes en la cola: " + Lq);
            System.out.println("Tiempo promedio de espera en la cola: " + Wq + " horas o " + (Wq * 60) + " minutos.");
            System.out.println("Tiempo total en el sistema: " + W + " horas o " + (W * 60) + " minutos.");
            System.out.println("Número promedio de clientes en el sistema: " + L);
        } else {
            System.out.println(
                    "\nEl sistema no es estable, ya que el promedio de\nllegadas debe ser menor que el promedio de servicio.");
        }

    }

    // Modelo M/M/C
    // λ = promedio de llegadas
    // μ = promedio de servicio
    // C = numero de servidores
    public static void modeloMMC(int lambda, int mu, int c) {

        double a = (double) lambda / mu;// intencidad de trafico a
        a = (int) (a * 100.0) / 100.0;

        double P = (double) a / c; // factor de utilizacion del sistema P
        P = (int) (P * 100.0) / 100.0;

        // Calculo de P_o
        // Sumatoria 
        double sumatoria = 0.0;
        for (int i = 0; i < c; i++) {
            sumatoria += (double) (Math.pow(a, i)) / factoria(i);
        }
        sumatoria = (int) (sumatoria * 100.0) / 100.0;

        double P_espera = (double) ((Math.pow(a, c)) / (factoria(c) * (1 - P)));// *P_o;
        P_espera = (int) (P_espera * 100.0) / 100.0;

        double Lq = (double) (P_espera * P) / (1 - P);// numero promedio de clientes en la cola Lq
        Lq = (int) (Lq * 100.0) / 100.0;

        double Wq = (double) Lq / lambda; // tiempo promedio esperado antes de ser atendico Wq
        Wq = (int) (Wq * 100.0) / 100.0;

        double W = (double) Wq + ((double) 1 / mu); // tiempo total en el sistema W
        W = (int) (W * 100.0) / 100.0;

        System.out.println("\nIntencidad del sistema: " + a);
        System.out.println("Factor de utilizacion del sistema: " + P);
        // System.out.println("Probabilidad de que no haya clientes en el sistema: " +
        // P_o);
        System.out.println("Probabilidad de que un cliente espere en la cola: " + P_espera);
        System.out.println("Número promedio de clientes en la cola: " + Lq);
        System.out.println("Tiempo promedio de espera en la cola: " + Wq + " horas o " + (Wq * 60) + " minutos.");
        System.out.println("Tiempo total en el sistema: " + W + " horas o " + (W * 60) + " minutos.");

    }

    // Modelo M/M/1K
    public static void modeloMM1K() {

    }

    public static int factoria(int n) {
        if (n == 0 || n == 1) {
            return 1;
        } else {
            return n * factoria(n - 1);
        }
    }

}
