// Proyecto #1 - EIF206 Programación 3 (2026-II)
// Sistema de Reserva de Recursos - Demo de Actividades y Estadísticas
// Autor: bachi

package Reservas.logica;

import java.time.LocalDate;

public class Actividad {

    private String nombre;
    private String funcionario;
    private LocalDate fecha;
    private int horaInicio;
    private int horaFin;

    public Actividad(String nombre, String funcionario, LocalDate fecha, int horaInicio, int horaFin) {
        this.nombre = nombre;
        this.funcionario = funcionario;
        this.fecha = fecha;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
    }

    public String getNombre() {
        return nombre;
    }

    public String getFuncionario() {
        return funcionario;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public int getHoraInicio() {
        return horaInicio;
    }

    public int getHoraFin() {
        return horaFin;
    }
}
