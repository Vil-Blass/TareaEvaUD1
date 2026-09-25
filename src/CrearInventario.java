import java.io.IOException;
import java.io.RandomAccessFile;

public class CrearInventario {

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

        Equipo[] equipos = DatosInventario.obtenerEquipos();

        try (RandomAccessFile fichero =
                     new RandomAccessFile(FICHERO, "rw")) {

            fichero.setLength(0);

            for (Equipo equipo : equipos) {
                escribirEquipo(fichero, equipo);
            }

            System.out.println(
                    "Inventario creado correctamente."
            );

            System.out.println(
                    "Número de registros guardados: "
                            + equipos.length
            );

            System.out.println(
                    "Tamaño de cada registro: "
                            + TAMANIO_REGISTRO
                            + " bytes"
            );

        } catch (IOException e) {

            System.err.println(
                    "Error al crear el inventario: "
                            + e.getMessage()
            );
        }
    }

    private static void escribirEquipo(
            RandomAccessFile fichero,
            Equipo equipo) throws IOException {

        fichero.writeInt(equipo.getId());

        escribirCadenaFija(
                fichero,
                equipo.getCodigo(),
                LONGITUD_CODIGO
        );

        escribirCadenaFija(
                fichero,
                equipo.getNombre(),
                LONGITUD_NOMBRE
        );

        escribirCadenaFija(
                fichero,
                equipo.getCategoria(),
                LONGITUD_CATEGORIA
        );

        fichero.writeInt(equipo.getStock());

        fichero.writeDouble(equipo.getPrecio());
    }

    private static void escribirCadenaFija(
            RandomAccessFile fichero,
            String texto,
            int longitud) throws IOException {

        if (texto == null) {
            texto = "";
        }

        if (texto.length() > longitud) {
            texto = texto.substring(0, longitud);
        }

        for (int i = 0; i < longitud; i++) {

            if (i < texto.length()) {
                fichero.writeChar(texto.charAt(i));
            } else {
                fichero.writeChar(' ');
            }
        }
    }
}