import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FiltrarErrores {

    public static void main(String[] args) {

        String ficheroEntrada = "accesos.log";
        String ficheroSalida = "errores.log";

        int totalErrores = 0;

        try (BufferedReader lector =
                     new BufferedReader(new FileReader(ficheroEntrada));
             BufferedWriter escritor =
                     new BufferedWriter(new FileWriter(ficheroSalida))) {

            String linea;

            while ((linea = lector.readLine()) != null) {

                String[] campos = linea.split(";");

                if (campos.length >= 3) {

                    String resultado = campos[2].trim();

                    if (resultado.equalsIgnoreCase("ERROR")) {

                        escritor.write(linea);
                        escritor.newLine();

                        totalErrores++;
                    }
                }
            }

            escritor.write("Total de errores: " + totalErrores);
            escritor.newLine();

            System.out.println("Fichero generado correctamente.");
            System.out.println(
                    "Total de errores: " + totalErrores
            );

        } catch (IOException e) {
            System.err.println(
                    "Error de entrada/salida: " + e.getMessage()
            );
        }
    }
}