import pickle
import os

class Empleado:
    def __init__(self, nombre: str, edad: int, salario: float):
        self.nombre = nombre
        self.edad = edad
        self.salario = salario
    
    def __str__(self):
        return f"Nombre: {self.nombre}, Edad: {self.edad}, Salario: {self.salario:.2f}"

class ArchivoEmpleado:
    def __init__(self, n: str):
        self.nomA = n
        self.empleados = []
        self.crearArchivo()
    
    def crearArchivo(self):
        if not os.path.exists(self.nomA):
            with open(self.nomA, 'wb') as f:
                pickle.dump([], f)
    
    def guardarEmpleado(self, e: Empleado):
        self.cargarEmpleados()
        self.empleados.append(e)
        self.guardarEnArchivo()
    
    def guardarEnArchivo(self):
        with open(self.nomA, 'wb') as f:
            pickle.dump(self.empleados, f)
    
    def cargarEmpleados(self):
        try:
            with open(self.nomA, 'rb') as f:
                self.empleados = pickle.load(f)
        except EOFError:
            self.empleados = []
    
    def buscaEmpleado(self, n: str) -> Empleado:
        self.cargarEmpleados()
        for e in self.empleados:
            if e.nombre.lower() == n.lower():
                return e
        return None
    
    def mayorSalario(self, sueldo: float) -> Empleado:
        self.cargarEmpleados()
        for e in self.empleados:
            if e.salario > sueldo:
                return e
        return None

# Pruebas
if __name__ == "__main__":
    archivo = ArchivoEmpleado("empleados.pkl")
    
    # Guardar empleados
    archivo.guardarEmpleado(Empleado("Juan Pérez", 35, 2500.50))
    archivo.guardarEmpleado(Empleado("María García", 28, 3200.75))
    archivo.guardarEmpleado(Empleado("Carlos López", 42, 1800.25))
    
    # Buscar empleado
    encontrado = archivo.buscaEmpleado("María García")
    print("Empleado encontrado:", encontrado)
    
    # Mayor salario
    mayor_salario = archivo.mayorSalario(3000.0)
    print("Empleado con salario mayor a 3000:", mayor_salario)