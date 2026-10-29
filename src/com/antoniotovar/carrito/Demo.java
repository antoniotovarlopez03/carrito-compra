package com.antoniotovar.carrito;

public final class Demo {
    private Demo() {
    }

    public static void main(String[] args) {
        ShoppingCart carrito = new ShoppingCart();
        carrito.agregarProducto("Teclado", 25.99, "Informática");
        carrito.agregarProducto("Monitor", 199.99, "Informática");
        carrito.agregarProducto("Libro Java", 35.50, "Libros");
        carrito.agregarProducto("Ratón", 15.00, "Informática");

        carrito.mostrarTicket();
        System.out.println("Total en libros: " + ShoppingCart.formatearPrecio(carrito.calcularTotalPorCategoria("Libros")) + " €");
        carrito.buscarProductoMasCaro().ifPresent(p ->
                System.out.println("Producto más caro: " + p.nombre() + " (" + ShoppingCart.formatearPrecio(p.precio()) + " €)"));

        System.out.println();
        System.out.println("Eliminando 'Ratón' del carrito...");
        carrito.eliminarProductoPorNombre("Ratón");
        carrito.mostrarTicket();
    }
}
