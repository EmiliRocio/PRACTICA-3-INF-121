from typing import TypeVar, Generic, List

T = TypeVar('T')

class Pila(Generic[T]):
    def __init__(self):
        self._elementos: List[T] = []
    
    def apilar(self, elemento: T) -> None:
        """Añade un elemento a la parte superior de la pila"""
        self._elementos.append(elemento)
    
    def desapilar(self) -> T:
        """Elimina y devuelve el elemento de la parte superior de la pila"""
        if self.esta_vacia():
            raise IndexError("La pila está vacía")
        return self._elementos.pop()
    
    def esta_vacia(self) -> bool:
        """Verifica si la pila está vacía"""
        return len(self._elementos) == 0
    
    def __str__(self) -> str:
        """Representación de la pila como cadena de texto"""
        return "Pila(" + ", ".join(str(elem) for elem in reversed(self._elementos)) + ")"

# Prueba con diferentes tipos de datos
if __name__ == "__main__":
    # Pila de enteros
    pila_enteros = Pila[int]()
    pila_enteros.apilar(10)
    pila_enteros.apilar(20)
    pila_enteros.apilar(30)
    
    print("Pila de enteros:", pila_enteros)
    print("Desapilado:", pila_enteros.desapilar())
    print("Pila después de desapilar:", pila_enteros)
    
    # Pila de cadenas
    pila_cadenas = Pila[str]()
    pila_cadenas.apilar("Hola")
    pila_cadenas.apilar("Mundo")
    pila_cadenas.apilar("Python")
    
    print("\nPila de cadenas:", pila_cadenas)
    print("Desapilado:", pila_cadenas.desapilar())
    print("Pila después de desapilar:", pila_cadenas)
    
    # Pila de booleanos
    pila_booleanos = Pila[bool]()
    pila_booleanos.apilar(True)
    pila_booleanos.apilar(False)
    pila_booleanos.apilar(True)
    
    print("\nPila de booleanos:", pila_booleanos)
    print("Desapilado:", pila_booleanos.desapilar())
    print("Pila después de desapilar:", pila_booleanos)