from typing import TypeVar, Generic

T = TypeVar('T')

class Caja(Generic[T]):
    def __init__(self):
        self._contenido = None
    
    def guardar(self, objeto: T) -> None:
        self._contenido = objeto
    
    def obtener(self) -> T:
        return self._contenido

# Crear dos instancias de Caja con diferentes tipos
caja_entero = Caja[int]()
caja_string = Caja[str]()

# Almacenar datos
caja_entero.guardar(42)
caja_string.guardar("Hola Mundo")

# Mostrar contenido
print(f"Caja de enteros: {caja_entero.obtener()}")
print(f"Caja de strings: {caja_string.obtener()}")