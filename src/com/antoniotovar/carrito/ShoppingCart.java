package com.antoniotovar.carrito;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class ShoppingCart {
    private static final String SEPARADOR = "================================";

    private final List<Producto> productos = new ArrayList<>();

    public void agregarProducto(String nombre, double precio, String categoria) {
        productos.add(new Producto(nombre, precio, categoria));
    }

    public int eliminarProductoPorNombre(String nombre) {
        int antes = productos.size();
        if (nombre != null) {
            productos.removeIf(p -> p.seLlama(nombre));
        }
        return antes - productos.size();
    }

    public double calcularTotal() {
        return sumar(productos);
    }

    public double calcularTotalPorCategoria(String categoria) {
        if (categoria == null) {
            return 0;
        }
        return sumar(productos.stream().filter(p -> p.esDeCategoria(categoria)).toList());
    }

    public Optional<Producto> buscarProductoMasCaro() {
        return productos.stream().max(Comparator.comparingDouble(Producto::precio));
    }

    public List<Producto> getProductos() {
        return Collections.unmodifiableList(productos);
    }

    public String generarTicket() {
        StringBuilder ticket = new StringBuilder("===== CARRITO DE LA COMPRA =====\n");
        for (int i = 0; i < productos.size(); i++) {
            ticket.append(formatearLinea(i + 1, productos.get(i))).append('\n');
        }
        ticket.append("--------------------------------\n");
        ticket.append("TOTAL: ").append(formatearPrecio(calcularTotal())).append(" €\n");
        ticket.append(SEPARADOR);
        return ticket.toString();
    }

    public void mostrarTicket() {
        System.out.println(generarTicket());
    }

    static String formatearPrecio(double precio) {
        return String.valueOf(precio);
    }

    private static String formatearLinea(int numero, Producto p) {
        return numero + ". " + p.nombre() + " - " + formatearPrecio(p.precio()) + " € (" + p.categoria() + ")";
    }

    private static double sumar(List<Producto> lista) {
        return lista.stream().mapToDouble(Producto::precio).sum();
    }
}
