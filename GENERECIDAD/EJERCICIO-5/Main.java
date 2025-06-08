import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Pila de enteros
        Pila<Integer> pilaEnteros = new Pila<>();
        pilaEnteros.apilar(10);
        pilaEnteros.apilar(20);
        pilaEnteros.apilar(30);
        
        System.out.println("Pila de enteros: " + pilaEnteros);
        System.out.println("Desapilado: " + pilaEnteros.desapilar());
        System.out.println("Pila después de desapilar: " + pilaEnteros);
        
        // Pila de cadenas
        Pila<String> pilaCadenas = new Pila<>();
        pilaCadenas.apilar("Hola");
        pilaCadenas.apilar("Mundo");
        pilaCadenas.apilar("Java");
        
        System.out.println("\nPila de cadenas: " + pilaCadenas);
        System.out.println("Desapilado: " + pilaCadenas.desapilar());
        System.out.println("Pila después de desapilar: " + pilaCadenas);
        
        // Pila de booleanos
        Pila<Boolean> pilaBooleanos = new Pila<>();
        pilaBooleanos.apilar(true);
        pilaBooleanos.apilar(false);
        pilaBooleanos.apilar(true);
        
        System.out.println("\nPila de booleanos: " + pilaBooleanos);
        System.out.println("Desapilado: " + pilaBooleanos.desapilar());
        System.out.println("Pila después de desapilar: " + pilaBooleanos);
    }
}

class Pila<T> {
    private List<T> elementos;
    
    public Pila() {
        elementos = new ArrayList<>();
    }
    
    public void apilar(T elemento) {
        elementos.add(elemento);
    }
    
    public T desapilar() {
        if (estaVacia()) {
            throw new IllegalStateException("La pila está vacía");
        }
        return elementos.remove(elementos.size() - 1);
    }
    
    public boolean estaVacia() {
        return elementos.isEmpty();
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Pila[");
        for (int i = elementos.size() - 1; i >= 0; i--) {
            sb.append(elementos.get(i));
            if (i > 0) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }
}