package com.antoniotovar.carrito;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {

    private final List<Producto> productos = new ArrayList<>();

    public void agregarProducto(String nombre, double precio, String categoria) {
        if (nombre != null && categoria != null) {
            productos.add(new Producto(nombre, precio, categoria));
        }
    }

    public void eliminarProductoPorNombre(String nombre) {
        if (nombre == null) {
            return;
        }
        for (int i = 0; i < productos.size(); i++) {
            if (nombre.equalsIgnoreCase(productos.get(i).nombre())) {
                productos.remove(i);
                i--;
            }
        }
    }

    public double calcularTotal() {
        double total = 0;
        for (Producto p : productos) {
            total = total + p.precio();
        }
        return total;
    }

    public double calcularTotalPorCategoria(String categoriaBuscada) {
        if (categoriaBuscada == null) {
            return 0;
        }
        double total = 0;
        for (Producto p : productos) {
            if (p.categoria().equalsIgnoreCase(categoriaBuscada)) {
                total = total + p.precio();
            }
        }
        return total;
    }

    public int buscarIndiceProductoMasCaro() {
        if (productos.isEmpty()) {
            return -1;
        }
        int indiceMasCaro = 0;
        for (int i = 1; i < productos.size(); i++) {
            if (productos.get(i).precio() > productos.get(indiceMasCaro).precio()) {
                indiceMasCaro = i;
            }
        }
        return indiceMasCaro;
    }

    public Producto getProducto(int indice) {
        return productos.get(indice);
    }

    public void mostrarTicket() {
        System.out.println("===== CARRITO DE LA COMPRA =====");
        for (int i = 0; i < productos.size(); i++) {
            Producto p = productos.get(i);
            System.out.println((i + 1) + ". " + p.nombre() + " - " + p.precio() + " €" + " (" + p.categoria() + ")");
        }
        System.out.println("--------------------------------");
        System.out.println("TOTAL: " + calcularTotal() + " €");
        System.out.println("================================");
    }

    public static void main(String[] args) {
        ShoppingCart carrito = new ShoppingCart();
        carrito.agregarProducto("Teclado", 25.99, "Informática");
        carrito.agregarProducto("Monitor", 199.99, "Informática");
        carrito.agregarProducto("Libro Java", 35.50, "Libros");
        carrito.agregarProducto("Ratón", 15.00, "Informática");
        carrito.mostrarTicket();
        System.out.println("Total en libros: " + carrito.calcularTotalPorCategoria("Libros") + " €");
        int indiceMasCaro = carrito.buscarIndiceProductoMasCaro();
        if (indiceMasCaro != -1) {
            Producto caro = carrito.getProducto(indiceMasCaro);
            System.out.println("Producto más caro: " + caro.nombre() + " (" + caro.precio() + " €)");
        }
        System.out.println();
        System.out.println("Eliminando 'Ratón' del carrito...");
        carrito.eliminarProductoPorNombre("Ratón");
        carrito.mostrarTicket();
    }
}
