import java.io.IOException;
import java.io.RandomAccessFile;

public class ActualizarStock {

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

    private static final int POSICION_STOCK =
            BYTES_INT
            + LONGITUD_CODIGO * BYTES_CHAR
            + LONGITUD_NOMBRE * BYTES_CHAR
            + LONGITUD_CATEGORIA * BYTES_CHAR;

    public static void main(String[] args) {

        if (args.length != 2) {
            System.out.println(
                    "Uso: java ActualizarStock <id> <nuevoStock>"
            );
            return;
        }

        int id;
        int nuevoStock;

        try {

            id = Integer.parseInt(args[0]);
            nuevoStock = Integer.parseInt(args[1]);

        } catch (NumberFormatException e) {

            System.out.println(
                    "El id y el stock deben ser números enteros."
            );
            return;
        }

        if (id <= 0) {
            System.out.println(
                    "El id debe ser mayor que 0."
            );
            return;
        }

        if (nuevoStock < 0) {
            System.out.println(
                    "El stock no puede ser negativo."
            );
            return;
        }

        try (RandomAccessFile fichero =
                     new RandomAccessFile(FICHERO, "rw")) {

            long numeroRegistros =
                    fichero.length() / TAMANIO_REGISTRO;

            if (id > numeroRegistros) {

                System.out.println(
                        "No existe ningún registro con el id "
                                + id + "."
                );
                return;
            }

            long posicionRegistro =
                    (long) (id - 1) * TAMANIO_REGISTRO;

            fichero.seek(posicionRegistro);

            int idLeido = fichero.readInt();

            if (idLeido != id) {

                System.out.println(
                        "El registro no coincide con el id solicitado."
                );
                return;
            }

            // Saltamos el código
            leerCadenaFija(
                    fichero,
                    LONGITUD_CODIGO
            );

            // Leemos el nombre
            String nombre =
                    leerCadenaFija(
                            fichero,
                            LONGITUD_NOMBRE
                    ).trim();

            // Saltamos la categoría
            leerCadenaFija(
                    fichero,
                    LONGITUD_CATEGORIA
            );

            int stockAnterior = fichero.readInt();

            /*
             * Volvemos al principio del registro y vamos
             * directamente a la posición del stock.
             */
            fichero.seek(
                    posicionRegistro + POSICION_STOCK
            );

            fichero.writeInt(nuevoStock);

            int variacion = nuevoStock - stockAnterior;

            System.out.println("Equipo: " + nombre);
            System.out.println(
                    "Stock anterior: " + stockAnterior
            );
            System.out.println(
                    "Stock nuevo: " + nuevoStock
            );
            System.out.println(
                    "Variación: " + variacion
            );

        } catch (IOException e) {

            System.err.println(
                    "Error al actualizar el stock: "
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