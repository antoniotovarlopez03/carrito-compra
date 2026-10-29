package com.antoniotovar.carrito;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

/**
 * Carrito de la compra: guarda productos y calcula totales.
 *
 * <p>Sustituye a la versión original, que usaba tres listas paralelas
 * (nombres, precios y categorías), por una única lista de {@link Producto}.
 * Los cálculos se hacen con Streams.</p>
 *
 * @author Antonio Tovar López
 * @version 2.0
 * @see Producto
 */
public class ShoppingCart {
    private static final String SEPARADOR = "================================";

    private final List<Producto> productos = new ArrayList<>();

    /**
     * Añade un producto al carrito.
     *
     * @param nombre    nombre del producto
     * @param precio    precio en euros, cero o positivo
     * @param categoria categoría del producto
     * @throws IllegalArgumentException si algún dato no es válido
     */
    public void agregarProducto(String nombre, double precio, String categoria) {
        productos.add(new Producto(nombre, precio, categoria));
    }

    /**
     * Elimina del carrito todos los productos con ese nombre, sin distinguir mayúsculas.
     *
     * @param nombre nombre de los productos que se quieren quitar; si es {@code null} no hace nada
     * @return número de productos eliminados
     */
    public int eliminarProductoPorNombre(String nombre) {
        int antes = productos.size();
        if (nombre != null) {
            productos.removeIf(p -> p.seLlama(nombre));
        }
        return antes - productos.size();
    }

    /**
     * Calcula el precio total de todos los productos del carrito.
     *
     * @return suma de los precios en euros; {@code 0} si el carrito está vacío
     */
    public double calcularTotal() {
        return sumar(productos);
    }

    /**
     * Calcula el precio total de los productos de una categoría.
     *
     * @param categoria categoría buscada, sin distinguir mayúsculas
     * @return suma de los precios de esa categoría; {@code 0} si no hay ninguno o la categoría es {@code null}
     */
    public double calcularTotalPorCategoria(String categoria) {
        if (categoria == null) {
            return 0;
        }
        return sumar(productos.stream().filter(p -> p.esDeCategoria(categoria)).toList());
    }

    /**
     * Busca el producto más caro del carrito.
     *
     * @return el producto más caro, o {@link Optional#empty()} si el carrito está vacío
     */
    public Optional<Producto> buscarProductoMasCaro() {
        return productos.stream().max(Comparator.comparingDouble(Producto::precio));
    }

    /**
     * Devuelve los productos del carrito en el orden en que se añadieron.
     *
     * @return lista de solo lectura con los productos
     */
    public List<Producto> getProductos() {
        return Collections.unmodifiableList(productos);
    }

    /**
     * Genera el texto del ticket del carrito, con una línea por producto y el total.
     *
     * @return ticket listo para mostrar
     */
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

    /**
     * Muestra el ticket por la salida estándar.
     *
     * @see #generarTicket()
     */
    public void mostrarTicket() {
        System.out.println(generarTicket());
    }

    /**
     * Da formato a un precio para el ticket.
     *
     * @param precio precio en euros
     * @return precio como texto
     */
    static String formatearPrecio(double precio) {
        return String.format("%.2f", precio);
    }

    private static String formatearLinea(int numero, Producto p) {
        return numero + ". " + p.nombre() + " - " + formatearPrecio(p.precio()) + " € (" + p.categoria() + ")";
    }

    private static double sumar(List<Producto> lista) {
        return lista.stream().mapToDouble(Producto::precio).sum();
    }
}
