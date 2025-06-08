from typing import TypeVar, Generic, List, Optional

T = TypeVar('T')

class Catalogo(Generic[T]):
    def __init__(self):
        self._elementos: List[T] = []
    
    def agregar(self, elemento: T) -> None:
        self._elementos.append(elemento)
    
    def buscar(self, criterio: str) -> Optional[T]:
        for elemento in self._elementos:
            if criterio.lower() in str(elemento).lower():
                return elemento
        return None

    def __str__(self) -> str:
        return "\n".join(str(elem) for elem in self._elementos)

# Clases para probar el catálogo
class Libro:
    def __init__(self, titulo: str, autor: str):
        self.titulo = titulo
        self.autor = autor
    
    def __str__(self):
        return f"Libro: {self.titulo} - {self.autor}"

class Producto:
    def __init__(self, nombre: str, precio: float):
        self.nombre = nombre
        self.precio = precio
    
    def __str__(self):
        return f"Producto: {self.nombre} - ${self.precio:.2f}"

# Prueba con libros
catalogo_libros = Catalogo[Libro]()
catalogo_libros.agregar(Libro("Cien años de soledad", "Gabriel García Márquez"))
catalogo_libros.agregar(Libro("1984", "George Orwell"))

print("Catálogo de Libros:")
print(catalogo_libros)

# Prueba con productos
catalogo_productos = Catalogo[Producto]()
catalogo_productos.agregar(Producto("Laptop", 1200.99))
catalogo_productos.agregar(Producto("Teléfono", 599.50))

print("\nCatálogo de Productos:")
print(catalogo_productos)

# Búsqueda en catálogos
print("\nBúsqueda:")
print("Libro encontrado:", catalogo_libros.buscar("soledad"))
print("Producto encontrado:", catalogo_productos.buscar("teléfono"))