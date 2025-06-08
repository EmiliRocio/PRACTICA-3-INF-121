import java.io.*;
import java.util.ArrayList;
import java.util.List;

class Empleado implements Serializable {
    private String nombre;
    private int edad;
    private float salario;

    public Empleado(String nombre, int edad, float salario) {
        this.nombre = nombre;
        this.edad = edad;
        this.salario = salario;
    }

    // Getters
    public String getNombre() { return nombre; }
    public int getEdad() { return edad; }
    public float getSalario() { return salario; }

    @Override
    public String toString() {
        return String.format("Nombre: %s, Edad: %d, Salario: %.2f", nombre, edad, salario);
    }
}

class ArchivoEmpleado {
    private String nomA;
    private List<Empleado> empleados;

    public ArchivoEmpleado(String n) {
        this.nomA = n;
        this.empleados = new ArrayList<>();
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

    public void guardarEmpleado(Empleado e) {
        empleados.add(e);
        guardarEnArchivo();
    }

    private void guardarEnArchivo() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nomA))) {
            oos.writeObject(empleados);
        } catch (IOException e) {
            System.err.println("Error al guardar empleados: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    private void cargarEmpleados() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nomA))) {
            empleados = (List<Empleado>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error al cargar empleados: " + e.getMessage());
        }
    }

    public Empleado buscaEmpleado(String n) {
        cargarEmpleados();
        for (Empleado e : empleados) {
            if (e.getNombre().equalsIgnoreCase(n)) {
                return e;
            }
        }
        return null;
    }

    public Empleado mayorSalario(float sueldo) {
        cargarEmpleados();
        for (Empleado e : empleados) {
            if (e.getSalario() > sueldo) {
                return e;
            }
        }
        return null;
    }
}

public class Main {
    public static void main(String[] args) {
        ArchivoEmpleado archivo = new ArchivoEmpleado("empleados.dat");
        
        // Guardar empleados
        archivo.guardarEmpleado(new Empleado("Juan Pérez", 35, 2500.50f));
        archivo.guardarEmpleado(new Empleado("María García", 28, 3200.75f));
        archivo.guardarEmpleado(new Empleado("Carlos López", 42, 1800.25f));
        
        // Buscar empleado
        Empleado encontrado = archivo.buscaEmpleado("María García");
        System.out.println("Empleado encontrado: " + encontrado);
        
        // Mayor salario
        Empleado mayorSalario = archivo.mayorSalario(3000.0f);
        System.out.println("Empleado con salario mayor a 3000: " + mayorSalario);
    }
}