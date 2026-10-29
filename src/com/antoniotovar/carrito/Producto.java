package com.antoniotovar.carrito;

public record Producto(String nombre, double precio, String categoria) {

    public Producto {
        if (nombre == null || categoria == null) {
            throw new IllegalArgumentException("El nombre y la categoria son obligatorios");
        }
    }
}
