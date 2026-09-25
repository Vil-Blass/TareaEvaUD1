public class Equipo {

    private int id;
    private String codigo;
    private String nombre;
    private String categoria;
    private int stock;
    private double precio;

    public Equipo(int id, String codigo, String nombre,
                  String categoria, int stock, double precio) {

        this.id = id;
        this.codigo = codigo;
        this.nombre = nombre;
        this.categoria = categoria;
        this.stock = stock;
        this.precio = precio;
    }

    public int getId() {
        return id;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public int getStock() {
        return stock;
    }

    public double getPrecio() {
        return precio;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {

        return "ID: " + id +
                ", Código: " + codigo +
                ", Nombre: " + nombre +
                ", Categoría: " + categoria +
                ", Stock: " + stock +
                ", Precio: " + precio;
    }
}
