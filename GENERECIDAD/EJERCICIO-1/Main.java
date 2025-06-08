public class Main {
    public static void main(String[] args) {
        // Crear dos instancias de Caja con diferentes tipos
        Caja<Integer> cajaEntero = new Caja<>();
        Caja<String> cajaString = new Caja<>();
        
        // Almacenar datos
        cajaEntero.guardar(42);
        cajaString.guardar("Hola Mundo");
        
        // Mostrar contenido
        System.out.println("Caja de enteros: " + cajaEntero.obtener());
        System.out.println("Caja de strings: " + cajaString.obtener());
    }
}

class Caja<T> {
    private T contenido;
    
    public void guardar(T objeto) {
        this.contenido = objeto;
    }
    
    public T obtener() {
        return contenido;
    }
}