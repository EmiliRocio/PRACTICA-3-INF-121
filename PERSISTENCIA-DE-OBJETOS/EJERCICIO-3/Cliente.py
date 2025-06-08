import pickle
import os

class Cliente:
    def __init__(self, id: int, nombre: str, telefono: int):
        self.id = id
        self.nombre = nombre
        self.telefono = telefono
    
    def __str__(self):
        return f"ID: {self.id}, Nombre: {self.nombre}, Teléfono: {self.telefono}"

class ArchivoCliente:
    def __init__(self, n: str):
        self.nomA = n
        self.clientes = []
        self.crearArchivo()
    
    def crearArchivo(self):
        if not os.path.exists(self.nomA):
            with open(self.nomA, 'wb') as f:
                pickle.dump([], f)
    
    def guardaCliente(self, c: Cliente):
        self.cargarClientes()
        self.clientes.append(c)
        self.guardarEnArchivo()
    
    def guardarEnArchivo(self):
        with open(self.nomA, 'wb') as f:
            pickle.dump(self.clientes, f)
    
    def cargarClientes(self):
        try:
            with open(self.nomA, 'rb') as f:
                self.clientes = pickle.load(f)
        except EOFError:
            self.clientes = []
    
    def buscarCliente(self, c: int) -> Cliente:
        self.cargarClientes()
        for cliente in self.clientes:
            if cliente.id == c:
                return cliente
        return None
    
    def buscarCelularCliente(self, c: int) -> Cliente:
        self.cargarClientes()
        for cliente in self.clientes:
            if cliente.telefono == c:
                return cliente
        return None

# Pruebas
if __name__ == "__main__":
    archivo = ArchivoCliente("clientes.pkl")
    
    # Guardar clientes
    archivo.guardaCliente(Cliente(1, "Ana Martínez", 5551234))
    archivo.guardaCliente(Cliente(2, "Luis Rodríguez", 5555678))
    archivo.guardaCliente(Cliente(3, "Marta Sánchez", 5559012))
    
    # Buscar cliente por ID
    encontrado = archivo.buscarCliente(2)
    print("Cliente encontrado por ID:", encontrado)
    
    # Buscar cliente por teléfono
    por_celular = archivo.buscarCelularCliente(5559012)
    print("Cliente encontrado por teléfono:", por_celular)