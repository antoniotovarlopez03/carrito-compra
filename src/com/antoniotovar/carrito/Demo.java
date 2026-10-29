package com.antoniotovar.carrito;

/**
 * Programa de ejemplo que usa el carrito, separado de la propia clase {@link ShoppingCart}.
 *
 * @author Antonio Tovar López
 * @version 2.0
 */
public final class Demo {
    private Demo() {
    }

    /**
     * Crea un carrito con cuatro productos, muestra el ticket y los totales,
     * elimina un producto y vuelve a mostrar el ticket.
     *
     * @param args no se usan
     */
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
