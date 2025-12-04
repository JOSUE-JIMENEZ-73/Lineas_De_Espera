
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
            System.out.println("\n===============MENU===============");
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
                    System.out.println("Ingrese el promedio de llegadas: ");// lambda
                    int promedioLlegadas = sc.nextInt();
                    System.out.println("Ingrese el promedio de servicio: ");// mu
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
                    System.out.println("\n===============Modelo M/M/1/K===============");
                    System.out.println("Ingrese el promedio de llegadas: ");
                    int promedioLlegadas = sc.nextInt();
                    System.out.println("Ingrese el promedio de servicio: ");
                    int promdedioServicio = sc.nextInt();
                    System.out.println("Ingrese la capacidad máxima del sistema (K): ");
                    int K = sc.nextInt();
                    modeloMM1K(promedioLlegadas, promdedioServicio, K);
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

            double W_minutos = W * 60;
            W_minutos = (int) (W_minutos * 100.0) / 100.0;

            System.out.println("\nEl sistema es estable.");
            System.out.println("Factor de utilizacion del sistema: " + p + "(" + (p * 100) + "%)");
            System.out.println("Número promedio de clientes en la cola: " + Lq);
            System.out.println("Tiempo promedio de espera en la cola: " + Wq + " horas o " + (Wq * 60) + " minutos.");
            System.out.println("Tiempo total en el sistema: " + W + " horas o " + W_minutos + " minutos.");
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
        System.out.println(a);

        double P = (double) a / c; // factor de utilizacion del sistema P
        P = (int) (P * 100.0) / 100.0;
        System.out.println(P);

        // Calculo de P_o
        // Sumatoria
        double sumatoria = 0.0;
        for (int i = 0; i < c; i++) {
            sumatoria += (double) (Math.pow(a, i)) / factoria(i);
        }
        sumatoria = (int) (sumatoria * 1000.0) / 1000.0;

        double suma = (double) Math.pow(a, c) / factoria(c);// segunda parte de la formula
        suma = (double) suma * (1 / (1 - P));// se multiplica por la tercera parte de la formula
        suma = (int) (suma * 1000.0) / 1000.0;

        double P_o = (double) 1 / (sumatoria + suma);// probabilidad de que no haya clientes en el sistema P_o
        P_o = (int) (P_o * 1000.0) / 1000.0;
        System.out.println(P_o);

        // probabilidad de que un cliente espere en la cola P_espera
        double P_espera = (double) ((Math.pow(a, c)) / (factoria(c) * (1 - P))) * P_o;
        P_espera = (int) (P_espera * 1000.0) / 1000.0;

        double Lq = (double) (P_espera * P) / (1 - P);// numero promedio de clientes en la cola Lq
        Lq = (int) (Lq * 100.0) / 100.0;

        double Wq = (double) Lq / lambda; // tiempo promedio esperado antes de ser atendico Wq
        Wq = (int) (Wq * 1000.0) / 1000.0;

        double W = (double) Wq + ((double) 1 / mu); // tiempo total en el sistema W
        W = (int) (W * 1000.0) / 1000.0;

        // minutos y porcentajes
        double P_porcentaje = P * 100;
        P_porcentaje = (int) (P_porcentaje * 100.0) / 100.0;

        double P_o_porcentaje = P_o * 100;
        P_o_porcentaje = (int) (P_o_porcentaje * 100.0) / 100.0;

        double P_espera_porcentaje = P_espera * 100;
        P_espera_porcentaje = (int) (P_espera_porcentaje * 100.0) / 100.0;

        double Wq_minutos = Wq * 60;
        Wq_minutos = (int) (Wq_minutos * 1000.0) / 1000.0;

        double W_minutos = W * 60;
        W_minutos = (int) (W_minutos * 1000.0) / 1000.0;

        System.out.println("\nIntencidad del sistema: " + a);
        System.out.println("Factor de utilizacion del sistema: " + P + "(" + P_porcentaje + "%)");
        System.out.println("Probabilidad de que el sistema este vacio: " + P_o + "(" + P_o_porcentaje + "%)");
        System.out.println(
                "Probabilidad de que un cliente espere en la cola: " + P_espera + "(" + P_espera_porcentaje + "%)");
        System.out.println("Número promedio de clientes en la cola: " + Lq);
        System.out.println("Tiempo promedio de espera en la cola: " + Wq + " horas o " + Wq_minutos + " minutos.");
        System.out.println("Tiempo total en el sistema: " + W + " horas o " + (W_minutos) + " minutos.");

    }

    // Modelo M/M/1K
    // λ = promedio de llegadas
    // μ = promedio de servicio
    // k= capacidad maxima del sistema
    public static void modeloMM1K(int lambda, int mu, int K) {

        double p = (double) lambda / mu; // factor de utilizacion del sistema p
        p = (int) (p * 100.0) / 100.0;

        double P_o = (double) (1 - p) / (1 - Math.pow(p, K + 1)); // probabilidad de que este vacio el sistema P_o
        P_o = (int) (P_o * 1000.0) / 1000.0;

        double P_k = (double) (Math.pow(p, K)) * (P_o); // probabilidad de que el sistema este lleno P_k
        P_k = (int) (P_k * 1000.0) / 1000.0;

        double lambda_e = lambda * (1 - P_k); // tasa efectiva de llegadas lambda_e
        lambda_e = (int) (lambda_e * 100.0) / 100.0;

        double L = (double) (p * (1 - (K + 1) * Math.pow(p, K) + (K * Math.pow(p, K + 1))))
                / ((1 - p) * (1 - Math.pow(p, K + 1))); // numero promedio de clientes en el sistema L
        L = (int) (L * 100.0) / 100.0;

        double W = (double) L / lambda_e; // tiempo promedio en el sistema W
        W = (int) (W * 1000.0) / 1000.0;

        // minutos y porcentajes
        double P_porcentaje = p * 100;
        P_porcentaje = (int) (P_porcentaje * 100.0) / 100.0;

        double P_o_porcentaje = P_o * 100;
        P_o_porcentaje = (int) (P_o_porcentaje * 100.0) / 100.0;

        double P_k_porcentaje = P_k * 100;
        P_k_porcentaje = (int) (P_k_porcentaje * 100.0) / 100.0;

        double W_minutos = W * 60;
        W_minutos = (int) (W_minutos * 1000.0) / 1000.0;

        System.out.println("\nFactor de utilizacion del sistema: " + p + "(" + P_porcentaje + "%)");
        System.out.println("Probabilidad de que el sistema este vacio: " + P_o + "(" + P_o_porcentaje + "%)");
        System.out.println("Probabilidad de que el sistema este lleno: " + P_k + "(" + P_k_porcentaje + "%)");
        System.out.println("Tasa efectiva de llegadas: " + lambda_e);
        System.out.println("Número promedio de clientes en el sistema: " + L);
        System.out.println("Tiempo promedio en el sistema: " + W + " horas o " + W_minutos + " minutos.");
    }

    // Metodo para calcular la factorial
    public static int factoria(int n) {
        if (n == 0 || n == 1) {
            return 1;
        } else {
            return n * factoria(n - 1);
        }
    }

}
