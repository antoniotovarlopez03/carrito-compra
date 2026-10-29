package com.antoniotovar.carrito;

public record Producto(String nombre, double precio, String categoria) {
    public Producto {
        if (nombre == null || categoria == null) {
            throw new IllegalArgumentException("El nombre y la categoria son obligatorios");
        }
        if (precio < 0) {
            throw new IllegalArgumentException("El precio no puede ser negativo");
        }
    }

    public boolean esDeCategoria(String otraCategoria) {
        return categoria.equalsIgnoreCase(otraCategoria);
    }

    public boolean seLlama(String otroNombre) {
        return nombre.equalsIgnoreCase(otroNombre);
    }
}
