import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class TransformarFichero {

    public static void main(String[] args) {

        String ficheroEntrada = "entrada.txt";
        String ficheroSalida = "salida.txt";

        try (FileReader lector = new FileReader(ficheroEntrada);
             FileWriter escritor = new FileWriter(ficheroSalida)) {

            int caracter;

            while ((caracter = lector.read()) != -1) {

                char c = (char) caracter;

                if (Character.isLetter(c)) {
                    c = Character.toUpperCase(c);
                } else if (Character.isDigit(c)) {
                    c = '#';
                }

                escritor.write(c);
            }

            System.out.println("Fichero transformado correctamente.");

        } catch (IOException e) {
            System.err.println("Error de entrada/salida: " + e.getMessage());
        }
    }
}