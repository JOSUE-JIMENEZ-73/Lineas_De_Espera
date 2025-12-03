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
        int opcion=0;
        do {
            System.out.println("Seleccione el modelo de línea de espera que desea calcular:");
            System.out.println("1. Modelo M/M/1");
            System.out.println("2. Modelo M/M/C");
            System.out.println("3. Modelo M/M/1/K");
            System.out.println("4. Salir");
            opcion = sc.nextInt();

            switch (opcion) {
                case 1:{

                }
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción inválida. Por favor, seleccione una opción válida.");
            }
        } while (opcion!=4);
    }

    //Metodos para los calculos
    //Modelo M/M/1
    public static void modeloMM1() {
        
    }
    //Modelo M/M/C
    public static void modeloMMC() {
        
    }
    //Modelo M/M/1K
    public static void modeloMM1K() {
        
    }
    

}
