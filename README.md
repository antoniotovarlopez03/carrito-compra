# Carrito de la compra

Refactorización de la clase `ShoppingCart` para la tarea 4 de Entornos de Desarrollo (1.º DAW / DAM, SAFA).

## Qué cambia respecto al código inicial

- Una clase `Producto` (record) en lugar de tres listas paralelas.
- Cálculos con Streams: `calcularTotal`, `calcularTotalPorCategoria`, `buscarProductoMasCaro`.
- `eliminarProductoPorNombre` con `removeIf`.
- El ticket se genera como texto (`generarTicket`) y el `main` pasa a la clase `Demo`.
- Todas las clases y métodos documentados con JavaDoc.

## Compilar y ejecutar

```
javac -encoding UTF-8 -d out src/com/antoniotovar/carrito/*.java
java -cp out com.antoniotovar.carrito.Demo
```

## Documentación

La documentación HTML generada con JavaDoc está en la carpeta [`docs`](docs/index.html).

Autor: Antonio Tovar López
