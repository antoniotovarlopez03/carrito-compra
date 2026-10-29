package com.antoniotovar.carrito;

/**
 * Producto que se puede añadir al carrito.
 *
 * <p>Es un {@code record}: inmutable, con constructor, métodos de acceso,
 * {@code equals}, {@code hashCode} y {@code toString} generados por Java.</p>
 *
 * @param nombre    nombre del producto; no puede ser {@code null}
 * @param precio    precio unitario en euros; no puede ser negativo
 * @param categoria categoría del producto, por ejemplo «Informática»; no puede ser {@code null}
 * @author Antonio Tovar López
 * @version 2.0
 */
public record Producto(String nombre, double precio, String categoria) {
    /**
     * Comprueba que los datos del producto son válidos.
     *
     * @throws IllegalArgumentException si el nombre o la categoría son {@code null}
     *                                  o si el precio es negativo
     */
    public Producto {
        if (nombre == null || categoria == null) {
            throw new IllegalArgumentException("El nombre y la categoria son obligatorios");
        }
        if (precio < 0) {
            throw new IllegalArgumentException("El precio no puede ser negativo");
        }
    }

    /**
     * Indica si el producto pertenece a una categoría, sin distinguir mayúsculas.
     *
     * @param otraCategoria categoría con la que comparar
     * @return {@code true} si las categorías coinciden
     */
    public boolean esDeCategoria(String otraCategoria) {
        return categoria.equalsIgnoreCase(otraCategoria);
    }

    /**
     * Indica si el producto tiene un nombre, sin distinguir mayúsculas.
     *
     * @param otroNombre nombre con el que comparar
     * @return {@code true} si los nombres coinciden
     */
    public boolean seLlama(String otroNombre) {
        return nombre.equalsIgnoreCase(otroNombre);
    }
}
