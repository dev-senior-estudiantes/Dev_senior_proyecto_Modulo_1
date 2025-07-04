import java.util.Scanner;

public class App {
    private static String estudiante;
    private static double n1, n2, n3;

    public static void main(String[] args) {
        var input = new Scanner(System.in);
        int opc;
        do {
            mostrarMenu(input);
            opc = leerEntero(input, "Ingrese una opcion: " );

            switch (opc) {
                case 1:
                    registrarEstudiante(input);
                    break;
                case 2:
                    mostrarNotas();
                    break;
                case 3:
                    validarPromedio();
                    break;
                case 4:
                    borarDatos();
                    break;
                case 0:
                    opc = 0;
                    break;
                default:
                    System.out.println("\nERORR: Seleccione una opcion valida");
                    break;
                }
        } while (opc != 0);
        
        input.close();
    }

    private static void mostrarMenu(Scanner input) {
        System.out.print("""
                \n********** SISTEMA DE REGISTRO DE ESTUDIANTES **********

                =========================================================

                [1] Registrar datos de un estudiante
                [2] Mostrar datos del estudiante actual
                [3] Calcular promedio de notas del estudiante actual
                [4] Borrar datos de estudiante actual
                [0] Salir
                
                =========================================================

                """);
    }


    private static int leerEntero(Scanner input, String mensaje) {
        var entrada = 9;
        System.out.print(mensaje);

        while (true) {
            if (input.hasNextDouble()){
                entrada = input.nextInt();
                input.nextLine();
            }else{
                System.out.print("ERROR: ingrese un numero de opcion valida");
                input.next();
            }    
        return entrada;
        }
    }

    private static Double leerNotas(Scanner input, String mensaje) {
        double entrada = -1;
        System.out.print(mensaje);

        while (true) {
            if (input.hasNextDouble()){
                entrada = input.nextDouble();

                if (entrada >= 0 && entrada <= 5){
                    return entrada;
                }else{
                    System.out.println("\nERROR: la nota debe estar entre 0 y 5");
                    System.out.print("Ingrese una nota valida: ");
                }

            }else{
                System.out.print("ERROR: ingrese un numero valido: ");
                input.next();
            }
        }
        
    }

    private static String leerNombre(Scanner input, String mensaje) {
        System.out.print(mensaje);
        var entrada =  input.nextLine();
        return entrada;
    }

    private static void registrarEstudiante(Scanner input) {
        if (estudiante == null){
            System.out.println(":::: REGISTRO DE ESTUDIANTE ::::");
            var estudiante_temp = leerNombre(input, "Ingrese el nombre del estudiante: ");
            var n1_temp = leerNotas(input, "Ingrese la nota 1 del estudiante: ");
            var n2_temp = leerNotas(input, "Ingrese la nota 2 del estudiante: ");
            var n3_temp = leerNotas(input, "Ingrese la nota 3 del estudiante: ");
            var save = mostrarResumen(input, estudiante_temp, n1_temp, n2_temp, n3_temp);
            if (save == true){
                estudiante = estudiante_temp;
                n1 = n1_temp;
                n2 = n2_temp;
                n3 = n3_temp;
            }
        }else {
            System.out.println("""
                \nQuiere sobre escribir los datos del estudiante registrados?
                [1] SI
                [2] NO
                """);
            var opc = leerEntero(input, "Ingrese el numero de su respuesta: ");
            if (opc == 1) {
                System.out.println(":::: REGISTRO DE ESTUDIANTE ::::");
                var estudiante_temp = leerNombre(input, "Ingrese el nombre del estudiante: ");
                var n1_temp = leerNotas(input, "Ingrese la nota 1 del estudiante: ");
                var n2_temp = leerNotas(input, "Ingrese la nota 2 del estudiante: ");
                var n3_temp = leerNotas(input, "Ingrese la nota 3 del estudiante: ");
                var save =mostrarResumen(input, estudiante_temp, n1_temp, n2_temp, n3_temp);
                if (save == true){
                    estudiante = estudiante_temp;
                    n1 = n1_temp;
                    n2 = n2_temp;
                    n3 = n3_temp;
                }
            }else if (opc == 2) {
                System.out.println("Se mantienen los datos");
            }else {
                System.out.printf("ERROR: %d no es una respuesta correcta", opc);
            }
        }


    }

    private static void validarPromedio() {
        if (estudiante == null){
            System.out.println("Actualmente no hay estudiante");
        }else{
            var promedio = (n1 + n2 + n3) / 3;
            if (promedio < 3){
                System.out.printf("%nEl estudiante tiene un promedio de %.1f por lo tanto reprobo\n", promedio);
            }else{
                System.out.printf("%nEl estudiante tiene un promedio de %.1f por lo tanto aprobo\n", promedio);
            }
        }
    }

    private static boolean mostrarResumen(Scanner input, String nombre, double n1, double n2, double n3) {
        System.out.println("\nResumen de datos ingresados: ");
        System.out.printf("El nombre de estudiante ingresado es: %s %nLa nota 1 ingresada es: %.1f %nLa nota 2 ingresada es: %.1f %nLa nota 3 ingresada es: %.1f %n", nombre, n1, n2, n3);

        do {
            System.out.println("""
            \nQuiere confirmar el guardado de datos?
            [1] SI
            [2] NO
            """);
            var opc = leerEntero(input, "Ingrese una opcion: ");
            switch (opc) {
                case 1:
                    return true;
                case 2:
                    return false;
                default:
                    System.out.println("ERROR: Ingrese una opcion valida: ");
                    continue;  
            }
        } while (true);

    }


    private static void mostrarNotas() {
       if (estudiante == null){
        System.out.println("Actualmente no hay estudiante");
       }else{
        System.out.println("/n::: ESTUDIANTE ACTUAL :::");
        System.out.printf("\nNombre = %s %nNota 1 = %.1f %nNota 2 = %.1f %nNota 3 = %.1f %n", estudiante, n1, n2, n3);
       }
    }


    private static void borarDatos() {
        System.out.println("::: BORRANDO DATOS ::::");
        estudiante = null;
        n1 = 0;
        n2 = 0;
        n3 = 0;
        System.out.println("::: SE HAN BORRADO LOS DATOS :::");
    }

}
