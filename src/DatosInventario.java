public class DatosInventario { // Clase que contiene los equipos

    private DatosInventario() { // Constructor privado para evitar instanciación
    }

    public static Equipo[] obtenerEquipos() { // Función que devuelve un array de equipos
        Equipo[] equipos = new Equipo[10]; // Creamos un array de equipos con 10 elementos
        equipos[0] = new Equipo(1, "12345678", "Equipo 1", "Categoría 1", 10, 19.99);
        equipos[1] = new Equipo(2, "23456789", "Equipo 2", "Categoría 2", 5, 24.99);
        equipos[2] = new Equipo(3, "34567890", "Equipo 3", "Categoría 3", 8, 14.99);
        equipos[3] = new Equipo(4, "45678901", "Equipo 4", "Categoría 4", 12, 29.99);
        equipos[4] = new Equipo(5, "56789012", "Equipo 5", "Categoría 5", 6, 19.99);
        equipos[5] = new Equipo(6, "67890123", "Equipo 6", "Categoría 6", 9, 24.99);
        equipos[6] = new Equipo(7, "78901234", "Equipo 7", "Categoría 7", 11, 14.99);
        equipos[7] = new Equipo(8, "89012345", "Equipo 8", "Categoría 8", 7, 29.99);
        equipos[8] = new Equipo(9, "90123456", "Equipo 9", "Categoría 9", 13, 19.99);
        equipos[9] = new Equipo(10, "01234567", "Equipo 10", "Categoría 10", 4, 24.99);
        return equipos; // Devuelve el array de equipos
    }
}