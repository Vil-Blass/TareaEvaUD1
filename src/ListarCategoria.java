import java.io.IOException;
import java.io.RandomAccessFile;

public class ListarCategoria {

    private static final String FICHERO = "inventario.dat";

    private static final int BYTES_INT = 4;
    private static final int BYTES_DOUBLE = 8;
    private static final int BYTES_CHAR = 2;

    private static final int LONGITUD_CODIGO = 8;
    private static final int LONGITUD_NOMBRE = 20;
    private static final int LONGITUD_CATEGORIA = 12;

    private static final int TAMANIO_REGISTRO =
            BYTES_INT
            + LONGITUD_CODIGO * BYTES_CHAR
            + LONGITUD_NOMBRE * BYTES_CHAR
            + LONGITUD_CATEGORIA * BYTES_CHAR
            + BYTES_INT
            + BYTES_DOUBLE;

    public static void main(String[] args) {

        if (args.length != 1) {

            System.out.println(
                    "Uso: java ListarCategoria <categoria>"
            );
            return;
        }

        String categoriaBuscada = args[0].trim();

        if (categoriaBuscada.isEmpty()) {

            System.out.println(
                    "La categoría no puede estar vacía."
            );
            return;
        }

        int encontrados = 0;

        try (RandomAccessFile fichero =
                     new RandomAccessFile(FICHERO, "r")) {

            long numeroRegistros =
                    fichero.length() / TAMANIO_REGISTRO;

            for (int i = 0; i < numeroRegistros; i++) {

                fichero.seek(
                        (long) i * TAMANIO_REGISTRO
                );

                int id = fichero.readInt();

                String codigo =
                        leerCadenaFija(
                                fichero,
                                LONGITUD_CODIGO
                        ).trim();

                String nombre =
                        leerCadenaFija(
                                fichero,
                                LONGITUD_NOMBRE
                        ).trim();

                String categoria =
                        leerCadenaFija(
                                fichero,
                                LONGITUD_CATEGORIA
                        ).trim();

                int stock = fichero.readInt();

                double precio = fichero.readDouble();

                if (categoria.equalsIgnoreCase(categoriaBuscada)) {

                    System.out.println(
                            "-----------------------------------"
                    );

                    System.out.println("ID: " + id);
                    System.out.println("Código: " + codigo);
                    System.out.println("Nombre: " + nombre);
                    System.out.println(
                            "Categoría: " + categoria
                    );
                    System.out.println("Stock: " + stock);
                    System.out.println(
                            "Precio: " + precio
                    );

                    encontrados++;
                }
            }

            System.out.println(
                    "-----------------------------------"
            );

            System.out.println(
                    "Total de equipos encontrados: "
                            + encontrados
            );

            if (encontrados == 0) {

                System.out.println(
                        "No se encontraron equipos de la categoría: "
                                + categoriaBuscada
                );
            }

        } catch (IOException e) {

            System.err.println(
                    "Error al leer el inventario: "
                            + e.getMessage()
            );
        }
    }

    private static String leerCadenaFija(
            RandomAccessFile fichero,
            int longitud) throws IOException {

        StringBuilder texto = new StringBuilder();

        for (int i = 0; i < longitud; i++) {
            texto.append(fichero.readChar());
        }

        return texto.toString();
    }
}