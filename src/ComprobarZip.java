import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class ComprobarZip {

    public static void main(String[] args) {

        if (args.length != 1) {
            System.out.println(
                    "Uso: java ComprobarZip <ruta_fichero>"
            );
            return;
        }

        String ruta = args[0];

        try (InputStream entrada = new FileInputStream(ruta)) {

            byte[] cabecera = new byte[4];

            int bytesLeidos = entrada.read(cabecera);

            if (bytesLeidos < 4) {
                System.out.println(
                        "El fichero contiene menos de cuatro bytes."
                );
                return;
            }

            boolean esZip =
                    Byte.toUnsignedInt(cabecera[0]) == 80 &&
                    Byte.toUnsignedInt(cabecera[1]) == 75 &&
                    Byte.toUnsignedInt(cabecera[2]) == 3 &&
                    Byte.toUnsignedInt(cabecera[3]) == 4;

            if (esZip) {
                System.out.println(
                        "La cabecera es compatible con un fichero ZIP."
                );
            } else {
                System.out.println(
                        "La cabecera no corresponde con la firma esperada."
                );
            }

        } catch (IOException e) {
            System.err.println(
                    "Error al leer el fichero: " + e.getMessage()
            );
        }
    }
}
