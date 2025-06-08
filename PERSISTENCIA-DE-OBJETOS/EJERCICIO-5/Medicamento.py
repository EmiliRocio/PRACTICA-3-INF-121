import pickle
import os

class Medicamento:
    def __init__(self, nombre="", codMedicamento=0, tipo="", precio=0.0):
        self.nombre = nombre
        self.codMedicamento = codMedicamento
        self.tipo = tipo
        self.precio = precio
    
    def leer(self):
        # Implementar lógica de lectura desde consola si es necesario
        pass
    
    def mostrar(self):
        print(f"Medicamento: {self.nombre}, Código: {self.codMedicamento}, "
              f"Tipo: {self.tipo}, Precio: {self.precio:.2f}")
    
    def getTipo(self):
        return self.tipo
    
    def getPrecio(self):
        return self.precio
    
    def getNombre(self):
        return self.nombre

class ArchFarmacia:
    def __init__(self, na):
        self.na = na
        self.farmacias = []
        self.crearArchivo()
    
    def crearArchivo(self):
        if not os.path.exists(self.na):
            with open(self.na, 'wb') as f:
                pickle.dump([], f)
    
    def adicionar(self, f):
        self.cargarFarmacias()
        self.farmacias.append(f)
        self.guardarEnArchivo()
    
    def guardarEnArchivo(self):
        with open(self.na, 'wb') as f:
            pickle.dump(self.farmacias, f)
    
    def cargarFarmacias(self):
        try:
            with open(self.na, 'rb') as f:
                self.farmacias = pickle.load(f)
        except EOFError:
            self.farmacias = []
    
    def listar(self):
        self.cargarFarmacias()
        for f in self.farmacias:
            f.mostrar()
    
    def mostrarMedicamentosResfrios(self):
        self.cargarFarmacias()
        for f in self.farmacias:
            for m in f.m:
                if m and m.getTipo().lower() == "resfriado":
                    m.mostrar()
    
    def precioMedicamentoTos(self):
        self.cargarFarmacias()
        total = 0.0
        for f in self.farmacias:
            for m in f.m:
                if m and m.getTipo().lower() == "tos":
                    total += m.getPrecio()
        return total
    
    def mostrarMedicamentosMenorTos(self):
        self.cargarFarmacias()
        for f in self.farmacias:
            for m in f.m:
                if m and m.getTipo().lower() == "tos" and m.getPrecio() < 10.0:
                    m.mostrar()
    
    # Métodos para los requerimientos específicos
    def mostrarMedicamentosTosSucursal(self, x):
        self.cargarFarmacias()
        for f in self.farmacias:
            if f.getSucursal() == x:
                for m in f.m:
                    if m and m.getTipo().lower() == "tos":
                        m.mostrar()
    
    def mostrarSucursalesConGolpex(self):
        self.cargarFarmacias()
        for f in self.farmacias:
            for m in f.m:
                if m and m.getNombre().lower() == "golpex":
                    print(f"Sucursal: {f.getSucursal()}, Dirección: {f.getDireccion()}")
                    break

class Farmacia:
    def __init__(self, nombreFarmacia="", sucursal=0, direccion=""):
        self.nombreFarmacia = nombreFarmacia
        self.sucursal = sucursal
        self.direccion = direccion
        self.nroMedicamentos = 0
        self.m = [None] * 100
    
    def leer(self):
        # Implementar lógica de lectura desde consola si es necesario
        pass
    
    def mostrar(self):
        print(f"Farmacia: {self.nombreFarmacia}, Sucursal: {self.sucursal}, "
              f"Dirección: {self.direccion}")
    
    def getDireccion(self):
        return self.direccion
    
    def getSucursal(self):
        return self.sucursal
    
    def getNroMedicamentos(self):
        return self.nroMedicamentos
    
    def agregarMedicamento(self, medicamento):
        if self.nroMedicamentos < 100:
            self.m[self.nroMedicamentos] = medicamento
            self.nroMedicamentos += 1
    
    def mostrarMedicamentos(self, tipo):
        for med in self.m:
            if med and med.getTipo().lower() == tipo.lower():
                med.mostrar()
    
    def buscaMedicamento(self, nombreMed):
        for med in self.m:
            if med and med.getNombre().lower() == nombreMed.lower():
                return True
        return False

# Pruebas
if __name__ == "__main__":
    archivo = ArchFarmacia("farmacias.pkl")
    
    # Crear y agregar farmacias con medicamentos
    f1 = Farmacia("Farmacia Central", 1, "Av. Principal 123")
    f1.agregarMedicamento(Medicamento("Golpex", 101, "tos", 15.50))
    f1.agregarMedicamento(Medicamento("Jarabe", 102, "tos", 8.75))
    
    f2 = Farmacia("Farmacia Norte", 2, "Calle Norte 456")
    f2.agregarMedicamento(Medicamento("Golpex", 101, "tos", 16.00))
    f2.agregarMedicamento(Medicamento("Antigripal", 103, "resfriado", 12.30))
    
    archivo.adicionar(f1)
    archivo.adicionar(f2)
    
    # a) Listar farmacias
    print("Listado de farmacias:")
    archivo.listar()
    
    # b) Mostrar medicamentos para la tos de sucursal X
    print("\nMedicamentos para la tos en sucursal 1:")
    archivo.mostrarMedicamentosTosSucursal(1)
    
    # c) Mostrar sucursales con Golpex
    print("\nSucursales que tienen Golpex:")
    archivo.mostrarSucursalesConGolpex()