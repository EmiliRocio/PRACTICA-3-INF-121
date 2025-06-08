import java.io.*;
import java.util.ArrayList;
import java.util.List;

class Cliente implements Serializable {
    private int id;
    private String nombre;
    private int telefono;

    public Cliente(int id, String nombre, int telefono) {
        this.id = id;
        this.nombre = nombre;
        this.telefono = telefono;
    }

    // Getters
    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public int getTelefono() { return telefono; }

    @Override
    public String toString() {
        return String.format("ID: %d, Nombre: %s, Teléfono: %d", id, nombre, telefono);
    }
}

class ArchivoCliente {
    private String nomA;
    private List<Cliente> clientes;

    public ArchivoCliente(String n) {
        this.nomA = n;
        this.clientes = new ArrayList<>();
        crearArchivo();
    }

    public void crearArchivo() {
        File archivo = new File(nomA);
        if (!archivo.exists()) {
            try {
                archivo.createNewFile();
            } catch (IOException e) {
                System.err.println("Error al crear el archivo: " + e.getMessage());
            }
        }
    }

    public void guardaCliente(Cliente c) {
        clientes.add(c);
        guardarEnArchivo();
    }

    private void guardarEnArchivo() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nomA))) {
            oos.writeObject(clientes);
        } catch (IOException e) {
            System.err.println("Error al guardar clientes: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    private void cargarClientes() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nomA))) {
            clientes = (List<Cliente>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error al cargar clientes: " + e.getMessage());
        }
    }

    public Cliente buscarCliente(int c) {
        cargarClientes();
        for (Cliente cliente : clientes) {
            if (cliente.getId() == c) {
                return cliente;
            }
        }
        return null;
    }

    public Cliente buscarCelularCliente(int c) {
        cargarClientes();
        for (Cliente cliente : clientes) {
            if (cliente.getTelefono() == c) {
                return cliente;
            }
        }
        return null;
    }
}

public class Main {
    public static void main(String[] args) {
        ArchivoCliente archivo = new ArchivoCliente("clientes.dat");
        
        // Guardar clientes
        archivo.guardaCliente(new Cliente(1, "Ana Martínez", 5551234));
        archivo.guardaCliente(new Cliente(2, "Luis Rodríguez", 5555678));
        archivo.guardaCliente(new Cliente(3, "Marta Sánchez", 5559012));
        
        // Buscar cliente por ID
        Cliente encontrado = archivo.buscarCliente(2);
        System.out.println("Cliente encontrado por ID: " + encontrado);
        
        // Buscar cliente por teléfono
        Cliente porCelular = archivo.buscarCelularCliente(5559012);
        System.out.println("Cliente encontrado por teléfono: " + porCelular);
    }
}