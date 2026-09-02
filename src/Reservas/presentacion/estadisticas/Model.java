// Proyecto #1 - EIF206 Programación 3 (2026-II)
// Pantalla: Estadísticas (Administrador y Funcionario)
// Autor: bachi

package reservas.presentacion.estadisticas;

import reservas.logica.EstadisticaActividadSemana;
import reservas.logica.EstadisticaRecurso;

import java.time.LocalDate;
import java.util.ArrayList;

public class Model {

    // En la demo se devuelven datos quemados; en el proyecto real esto consultaría
    // las reservas guardadas y filtraría por el rango de fechas recibido.
    public ArrayList<EstadisticaRecurso> consultarRecursos(LocalDate desde, LocalDate hasta) {

        ArrayList<EstadisticaRecurso> lista = new ArrayList<>();

        lista.add(new EstadisticaRecurso("Proyector", 3));
        lista.add(new EstadisticaRecurso("Laptop MacBook", 2));
        lista.add(new EstadisticaRecurso("Sala de reuniones", 4));

        return lista;
    }

    public ArrayList<EstadisticaActividadSemana> consultarActividades(LocalDate desde, LocalDate hasta) {

        ArrayList<EstadisticaActividadSemana> lista = new ArrayList<>();

        lista.add(new EstadisticaActividadSemana("Semana del 03/08", 3));
        lista.add(new EstadisticaActividadSemana("Semana del 10/08", 2));

        return lista;
    }
}
