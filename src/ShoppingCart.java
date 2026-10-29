import java.util.ArrayList;

public class ShoppingCart {

    // Listas paralelas para guardar los datos de los productos
    // (esto se podría mejorar creando una clase Producto, pero así es más simple)
    private ArrayList<String> nombres = new ArrayList<>();
    private ArrayList<Double> precios = new ArrayList<>();
    private ArrayList<String> categorias = new ArrayList<>();

    /**
     * Añade un producto al carrito.
     */
    public void agregarProducto(String nombre, double precio, String categoria) {
        if (nombre != null && categoria != null) {
            nombres.add(nombre);
            precios.add(precio);
            categorias.add(categoria);
        }
    }

    /**
     * Elimina del carrito todos los productos que tengan ese nombre.
     */
    public void eliminarProductoPorNombre(String nombre) {
        if (nombre == null) {
            return;
        }

        for (int i = 0; i < nombres.size(); i++) {
            String nombreActual = nombres.get(i);
            if (nombre.equalsIgnoreCase(nombreActual)) {
                // Eliminamos en las tres listas el elemento de la misma posición
                nombres.remove(i);
                precios.remove(i);
                categorias.remove(i);
                i--; // para no saltarnos ningún elemento
            }
        }
    }

    /**
     * Calcula el precio total de todos los productos del carrito.
     */
    public double calcularTotal() {
        double total = 0;

        for (int i = 0; i < precios.size(); i++) {
            Double precio = precios.get(i);
            if (precio != null) {
                total = total + precio;
            }
        }

        return total;
    }

    /**
     * Calcula el precio total de los productos de una categoría concreta.
     */
    public double calcularTotalPorCategoria(String categoriaBuscada) {
        double total = 0;

        if (categoriaBuscada == null) {
            return 0;
        }

        for (int i = 0; i < categorias.size(); i++) {
            String categoriaActual = categorias.get(i);
            Double precioActual = precios.get(i);

            if (categoriaActual != null &&
                categoriaActual.equalsIgnoreCase(categoriaBuscada)) {

                total = total + precioActual;
            }
        }

        return total;
    }

    /**
     * Devuelve el índice del producto más caro del carrito.
     * Si el carrito está vacío, devuelve -1.
     */
    public int buscarIndiceProductoMasCaro() {
        if (precios.isEmpty()) {
            return -1;
        }

        int indiceMasCaro = 0;
        double precioMasAlto = precios.get(0);

        for (int i = 1; i < precios.size(); i++) {
            Double precioActual = precios.get(i);
            if (precioActual > precioMasAlto) {
                precioMasAlto = precioActual;
                indiceMasCaro = i;
            }
        }

        return indiceMasCaro;
    }

    /**
     * Muestra por pantalla el ticket del carrito.
     */
    public void mostrarTicket() {
        System.out.println("===== CARRITO DE LA COMPRA =====");

        for (int i = 0; i < nombres.size(); i++) {
            String nombre = nombres.get(i);
            Double precio = precios.get(i);
            String categoria = categorias.get(i);

            System.out.println((i + 1) + ". " + nombre
                    + " - " + precio + " €"
                    + " (" + categoria + ")");
        }

        System.out.println("--------------------------------");
        System.out.println("TOTAL: " + calcularTotal() + " €");
        System.out.println("================================");
    }

    /**
     * Método main de prueba.
     */
    public static void main(String[] args) {

        ShoppingCart carrito = new ShoppingCart();

        // Añadimos algunos productos de ejemplo
        carrito.agregarProducto("Teclado", 25.99, "Informática");
        carrito.agregarProducto("Monitor", 199.99, "Informática");
        carrito.agregarProducto("Libro Java", 35.50, "Libros");
        carrito.agregarProducto("Ratón", 15.00, "Informática");

        // Mostramos el ticket completo
        carrito.mostrarTicket();

        // Mostramos el total solo de una categoría
        double totalLibros = carrito.calcularTotalPorCategoria("Libros");
        System.out.println("Total en libros: " + totalLibros + " €");

        // Buscamos el producto más caro
        int indiceMasCaro = carrito.buscarIndiceProductoMasCaro();
        if (indiceMasCaro != -1) {
            System.out.println("Producto más caro: "
                    + carrito.nombres.get(indiceMasCaro)
                    + " (" + carrito.precios.get(indiceMasCaro) + " €)");
        }

        // Ejemplo de eliminación
        System.out.println();
        System.out.println("Eliminando 'Ratón' del carrito...");
        carrito.eliminarProductoPorNombre("Ratón");

        // Volvemos a mostrar el ticket
        carrito.mostrarTicket();
    }
}
