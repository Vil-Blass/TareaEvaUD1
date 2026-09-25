import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        if (args.length > 0) {
            ejecutarComando(args);
            return;
        }

        mostrarMenu();

        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.print("\nSelecciona una opción (0-6): ");
                String opcion = scanner.nextLine().trim();

                switch (opcion) {
                    case "1":
                        CrearInventario.main(new String[0]);
                        break;
                    case "2":
                        System.out.print("Introduce la categoría: ");
                        String categoria = scanner.nextLine().trim();
                        if (categoria.isEmpty()) {
                            System.out.println("La categoría no puede estar vacía.");
                        } else {
                            ListarCategoria.main(new String[]{categoria});
                        }
                        break;
                    case "3":
                        System.out.print("Introduce el id del equipo: ");
                        String id = scanner.nextLine().trim();
                        System.out.print("Introduce el nuevo stock: ");
                        String stock = scanner.nextLine().trim();
                        ActualizarStock.main(new String[]{id, stock});
                        break;
                    case "4":
                        System.out.print("Introduce la ruta del archivo ZIP: ");
                        String rutaZip = scanner.nextLine().trim();
                        if (rutaZip.isEmpty()) {
                            System.out.println("La ruta no puede estar vacía.");
                        } else {
                            ComprobarZip.main(new String[]{rutaZip});
                        }
                        break;
                    case "5":
                        TransformarFichero.main(new String[0]);
                        break;
                    case "6":
                        FiltrarErrores.main(new String[0]);
                        break;
                    case "0":
                        System.out.println("Saliendo del programa.");
                        return;
                    default:
                        System.out.println("Opción no válida. Prueba otra vez.");
                        break;
                }
            }
        }
    }

    private static void mostrarMenu() {
        System.out.println("========================================");
        System.out.println("     Proyecto de Acceso a Datos");
        System.out.println("========================================");
        System.out.println("1. Crear inventario");
        System.out.println("2. Listar por categoría");
        System.out.println("3. Actualizar stock");
        System.out.println("4. Comprobar fichero ZIP");
        System.out.println("5. Transformar fichero");
        System.out.println("6. Filtrar errores");
        System.out.println("0. Salir");
    }

    private static void ejecutarComando(String[] args) {
        String comando = args[0].toLowerCase();

        switch (comando) {
            case "1":
            case "crear":
                CrearInventario.main(new String[0]);
                break;
            case "2":
            case "categoria":
                if (args.length < 2) {
                    System.out.println("Uso: java App 2 <categoria>");
                    return;
                }
                ListarCategoria.main(new String[]{args[1]});
                break;
            case "3":
            case "stock":
                if (args.length < 3) {
                    System.out.println("Uso: java App 3 <id> <nuevoStock>");
                    return;
                }
                ActualizarStock.main(new String[]{args[1], args[2]});
                break;
            case "4":
            case "zip":
                if (args.length < 2) {
                    System.out.println("Uso: java App 4 <ruta_fichero>");
                    return;
                }
                ComprobarZip.main(new String[]{args[1]});
                break;
            case "5":
            case "transformar":
                TransformarFichero.main(new String[0]);
                break;
            case "6":
            case "errores":
                FiltrarErrores.main(new String[0]);
                break;
            default:
                System.out.println("Comando no reconocido.");
                mostrarMenu();
                break;
        }
    }
}
