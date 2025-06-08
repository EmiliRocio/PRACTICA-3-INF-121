import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Prueba con libros
        Catalogo<Libro> catalogoLibros = new Catalogo<>();
        catalogoLibros.agregar(new Libro("Cien años de soledad", "Gabriel García Márquez"));
        catalogoLibros.agregar(new Libro("1984", "George Orwell"));
        
        System.out.println("Catálogo de Libros:");
        System.out.println(catalogoLibros);
        
        // Prueba con productos
        Catalogo<Producto> catalogoProductos = new Catalogo<>();
        catalogoProductos.agregar(new Producto("Laptop", 1200.99));
        catalogoProductos.agregar(new Producto("Teléfono", 599.50));
        
        System.out.println("\nCatálogo de Productos:");
        System.out.println(catalogoProductos);
        
        // Búsqueda en catálogos
        System.out.println("\nBúsqueda:");
        System.out.println("Libro encontrado: " + catalogoLibros.buscar("soledad"));
        System.out.println("Producto encontrado: " + catalogoProductos.buscar("teléfono"));
    }
}

class Catalogo<T> {
    private List<T> elementos;
    
    public Catalogo() {
        elementos = new ArrayList<>();
    }
    
    public void agregar(T elemento) {
        elementos.add(elemento);
    }
    
    public T buscar(String criterio) {
        for (T elemento : elementos) {
            if (elemento.toString().toLowerCase().contains(criterio.toLowerCase())) {
                return elemento;
            }
        }
        return null;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (T elemento : elementos) {
            sb.append(elemento.toString()).append("\n");
        }
        return sb.toString();
    }
}

class Libro {
    private String titulo;
    private String autor;
    
    public Libro(String titulo, String autor) {
        this.titulo = titulo;
        this.autor = autor;
    }
    
    @Override
    public String toString() {
        return "Libro: " + titulo + " - " + autor;
    }
}

class Producto {
    private String nombre;
    private double precio;
    
    public Producto(String nombre, double precio) {
        this.nombre = nombre;
        this.precio = precio;
    }
    
    @Override
    public String toString() {
        return String.format("Producto: %s - $%.2f", nombre, precio);
    }
}