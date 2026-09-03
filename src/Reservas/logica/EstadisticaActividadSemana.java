// Proyecto #1 - EIF206 Programación 3 (2026-II)
// Autor: bachi

package Reservas.logica;

public class EstadisticaActividadSemana {

    private String semana;
    private int cantidad;

    public EstadisticaActividadSemana(String semana, int cantidad) {
        this.semana = semana;
        this.cantidad = cantidad;
    }

    public String getSemana() {
        return semana;
    }

    public int getCantidad() {
        return cantidad;
    }
}
