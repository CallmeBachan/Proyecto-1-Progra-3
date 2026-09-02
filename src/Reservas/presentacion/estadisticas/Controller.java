// Proyecto #1 - EIF206 Programación 3 (2026-II)
// Autor: bachi

package reservas.presentacion.estadisticas;

import reservas.logica.EstadisticaActividadSemana;
import reservas.logica.EstadisticaRecurso;

import javax.swing.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class Controller {

    private View view;
    private Model model;

    public Controller(View view, Model model) {

        this.view = view;
        this.model = model;

        view.setController(this);
    }

    public void cargarRecursos(LocalDate desde, LocalDate hasta) {

        if (!fechasValidas(desde, hasta)) {
            return;
        }

        ArrayList<EstadisticaRecurso> lista = model.consultarRecursos(desde, hasta);

        view.mostrarRecursos(lista);
    }

    public void cargarActividades(LocalDate desde, LocalDate hasta) {

        if (!fechasValidas(desde, hasta)) {
            return;
        }

        ArrayList<EstadisticaActividadSemana> lista = model.consultarActividades(desde, hasta);

        view.mostrarActividades(lista);
    }

    private boolean fechasValidas(LocalDate desde, LocalDate hasta) {

        if (desde == null || hasta == null) {

            JOptionPane.showMessageDialog(
                    view,
                    "Seleccione ambas fechas antes de consultar.",
                    "Estadísticas",
                    JOptionPane.WARNING_MESSAGE
            );

            return false;
        }

        if (hasta.isBefore(desde)) {

            JOptionPane.showMessageDialog(
                    view,
                    "La fecha 'hasta' no puede ser anterior a la fecha 'desde'.",
                    "Estadísticas",
                    JOptionPane.WARNING_MESSAGE
            );

            return false;
        }

        return true;
    }
}
