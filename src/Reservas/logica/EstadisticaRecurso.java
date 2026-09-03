// Proyecto #1 - EIF206 Programación 3 (2026-II)
// Autor: bachi

package Reservas.logica;

public class EstadisticaRecurso {

    private String categoria;
    private int cantidad;

    public EstadisticaRecurso(String categoria, int cantidad) {
        this.categoria = categoria;
        this.cantidad = cantidad;
    }

    public String getCategoria() {
        return categoria;
    }

    public int getCantidad() {
        return cantidad;
    }
}
