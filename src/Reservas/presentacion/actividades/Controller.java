// Proyecto #1 - EIF206 Programación 3 (2026-II)
// Autor: bachi

package reservas.presentacion.actividades;

import reservas.logica.Actividad;

import javax.swing.*;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;

public class Controller {

    private View view;
    private Model model;

    public Controller(View view, Model model) {

        this.view = view;
        this.model = model;

        view.setController(this);

        // Se carga la semana de la fecha de referencia inicial al abrir la pantalla
        cargarSemana(model.getFechaReferencia());
    }

    public void cargarSemana(LocalDate fechaReferencia) {

        model.setFechaReferencia(fechaReferencia);

        LocalDate lunes = fechaReferencia.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));

        LocalDate[] diasSemana = new LocalDate[7];

        for (int i = 0; i < 7; i++) {
            diasSemana[i] = lunes.plusDays(i);
        }

        ArrayList<Actividad> actividadesSemana = new ArrayList<>();

        for (int i = 0; i < model.getActividades().size(); i++) {

            Actividad a = model.getActividades().get(i);

            if (!a.getFecha().isBefore(lunes) && !a.getFecha().isAfter(lunes.plusDays(6))) {
                actividadesSemana.add(a);
            }
        }

        view.mostrarSemana(diasSemana, actividadesSemana);
    }

    // El generado real del PDF no está implementado en esta demo; solo se simula la acción
    public void generarReporte() {

        JOptionPane.showMessageDialog(
                view,
                "Reporte de actividades generado (simulado para esta demo).",
                "Generar reporte",
                JOptionPane.INFORMATION_MESSAGE
        );
    }
}
