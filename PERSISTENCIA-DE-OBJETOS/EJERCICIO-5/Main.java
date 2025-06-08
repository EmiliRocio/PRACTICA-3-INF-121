import java.io.*;
import java.util.ArrayList;
import java.util.List;

class Medicamento implements Serializable {
    private String nombre;
    private int codMedicamento;
    private String tipo;
    private double precio;

    public Medicamento() {}

    public Medicamento(String nombre, int codMedicamento, String tipo, double precio) {
        this.nombre = nombre;
        this.codMedicamento = codMedicamento;
        this.tipo = tipo;
        this.precio = precio;
    }

    public void leer() {
        // Implementar lógica de lectura desde consola si es necesario
    }

    public void mostrar() {
        System.out.println("Medicamento: " + nombre + 
                         ", Código: " + codMedicamento + 
                         ", Tipo: " + tipo + 
                         ", Precio: " + precio);
    }

    public String getTipo() {
        return tipo;
    }

    public double getPrecio() {
        return precio;
    }

    public String getNombre() {
        return nombre;
    }
}

class ArchFarmacia {
    private String na;
    private List<Farmacia> farmacias;

    public ArchFarmacia(String na) {
        this.na = na;
        this.farmacias = new ArrayList<>();
        crearArchivo();
    }

    public void crearArchivo() {
        File archivo = new File(na);
        if (!archivo.exists()) {
            try {
                archivo.createNewFile();
            } catch (IOException e) {
                System.err.println("Error al crear el archivo: " + e.getMessage());
            }
        }
    }

    public void adicionar(Farmacia f) {
        farmacias.add(f);
        guardarEnArchivo();
    }

    private void guardarEnArchivo() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(na))) {
            oos.writeObject(farmacias);
        } catch (IOException e) {
            System.err.println("Error al guardar farmacias: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public void listar() {
        cargarFarmacias();
        for (Farmacia f : farmacias) {
            f.mostrar();
        }
    }

    public void mostrarMedicamentosResfrios() {
        cargarFarmacias();
        for (Farmacia f : farmacias) {
            for (int i = 0; i < f.getNroMedicamentos(); i++) {
                Medicamento m = f.getMedicamento(i);
                if (m != null && m.getTipo().equalsIgnoreCase("resfriado")) {
                    m.mostrar();
                }
            }
        }
    }

    public double precioMedicamentoTos() {
        cargarFarmacias();
        double total = 0;
        for (Farmacia f : farmacias) {
            for (int i = 0; i < f.getNroMedicamentos(); i++) {
                Medicamento m = f.getMedicamento(i);
                if (m != null && m.getTipo().equalsIgnoreCase("tos")) {
                    total += m.getPrecio();
                }
            }
        }
        return total;
    }

    public void mostrarMedicamentosMenorTos() {
        cargarFarmacias();
        for (Farmacia f : farmacias) {
            for (int i = 0; i < f.getNroMedicamentos(); i++) {
                Medicamento m = f.getMedicamento(i);
                if (m != null && m.getTipo().equalsIgnoreCase("tos") && m.getPrecio() < 10.0) {
                    m.mostrar();
                }
            }
        }
    }

    @SuppressWarnings("unchecked")
    private void cargarFarmacias() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(na))) {
            farmacias = (List<Farmacia>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error al cargar farmacias: " + e.getMessage());
        }
    }

    // Métodos para los requerimientos específicos
    public void mostrarMedicamentosTosSucursal(int x) {
        cargarFarmacias();
        for (Farmacia f : farmacias) {
            if (f.getSucursal() == x) {
                for (int i = 0; i < f.getNroMedicamentos(); i++) {
                    Medicamento m = f.getMedicamento(i);
                    if (m != null && m.getTipo().equalsIgnoreCase("tos")) {
                        m.mostrar();
                    }
                }
            }
        }
    }

    public void mostrarSucursalesConGolpex() {
        cargarFarmacias();
        for (Farmacia f : farmacias) {
            for (int i = 0; i < f.getNroMedicamentos(); i++) {
                Medicamento m = f.getMedicamento(i);
                if (m != null && m.getNombre().equalsIgnoreCase("Golpex")) {
                    System.out.println("Sucursal: " + f.getSucursal() + 
                                      ", Dirección: " + f.getDireccion());
                    break;
                }
            }
        }
    }
}

class Farmacia implements Serializable {
    private String nombreFarmacia;
    private int sucursal;
    private String direccion;
    private int nroMedicamentos;
    private Medicamento[] m = new Medicamento[100];

    public Farmacia() {}

    public Farmacia(String nombreFarmacia, int sucursal, String direccion) {
        this.nombreFarmacia = nombreFarmacia;
        this.sucursal = sucursal;
        this.direccion = direccion;
        this.nroMedicamentos = 0;
    }

    public void leer() {
        // Implementar lógica de lectura desde consola si es necesario
    }

    public void mostrar() {
        System.out.println("Farmacia: " + nombreFarmacia + 
                         ", Sucursal: " + sucursal + 
                         ", Dirección: " + direccion);
    }

    public String getDireccion() {
        return direccion;
    }

    public int getSucursal() {
        return sucursal;
    }

    public int getNroMedicamentos() {
        return nroMedicamentos;
    }

    public Medicamento getMedicamento(int index) {
        if (index >= 0 && index < nroMedicamentos) {
            return m[index];
        }
        return null;
    }

    public void agregarMedicamento(Medicamento medicamento) {
        if (nroMedicamentos < 100) {
            m[nroMedicamentos++] = medicamento;
        }
    }

    public void mostrarMedicamentos(String tipo) {
        for (int i = 0; i < nroMedicamentos; i++) {
            if (m[i] != null && m[i].getTipo().equalsIgnoreCase(tipo)) {
                m[i].mostrar();
            }
        }
    }

    public boolean buscaMedicamento(String nombreMed) {
        for (int i = 0; i < nroMedicamentos; i++) {
            if (m[i] != null && m[i].getNombre().equalsIgnoreCase(nombreMed)) {
                return true;
            }
        }
        return false;
    }
}

public class Main {
    public static void main(String[] args) {
        ArchFarmacia archivo = new ArchFarmacia("farmacias.dat");
        
        // Crear y agregar farmacias con medicamentos
        Farmacia f1 = new Farmacia("Farmacia Central", 1, "Av. Principal 123");
        f1.agregarMedicamento(new Medicamento("Golpex", 101, "tos", 15.50));
        f1.agregarMedicamento(new Medicamento("Jarabe", 102, "tos", 8.75));
        
        Farmacia f2 = new Farmacia("Farmacia Norte", 2, "Calle Norte 456");
        f2.agregarMedicamento(new Medicamento("Golpex", 101, "tos", 16.00));
        f2.agregarMedicamento(new Medicamento("Antigripal", 103, "resfriado", 12.30));
        
        archivo.adicionar(f1);
        archivo.adicionar(f2);
        
        // a) Listar farmacias
        System.out.println("Listado de farmacias:");
        archivo.listar();
        
        // b) Mostrar medicamentos para la tos de sucursal X
        System.out.println("\nMedicamentos para la tos en sucursal 1:");
        archivo.mostrarMedicamentosTosSucursal(1);
        
        // c) Mostrar sucursales con Golpex
        System.out.println("\nSucursales que tienen Golpex:");
        archivo.mostrarSucursalesConGolpex();
    }
}