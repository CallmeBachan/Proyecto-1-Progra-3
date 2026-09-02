// Proyecto #1 - EIF206 Programación 3 (2026-II)
// Pantalla: Agenda de Actividades (Administrador y Funcionario)
// Autor: bachi

package reservas.presentacion.actividades;

import reservas.logica.Actividad;

import java.time.LocalDate;
import java.util.ArrayList;

public class Model {

    private ArrayList<Actividad> actividades;
    private LocalDate fechaReferencia;

    public Model() {

        actividades = new ArrayList<>();
        fechaReferencia = LocalDate.of(2026, 8, 5);

        cargarDatosDeEjemplo();
    }

    // Datos quemados solo para que la pantalla tenga algo que mostrar
    private void cargarDatosDeEjemplo() {

        actividades.add(new Actividad("Charla técnica de seguridad", "María Pérez", LocalDate.of(2026, 8, 5), 8, 10));
        actividades.add(new Actividad("Sesión de Junta Directiva", "Juan Pérez", LocalDate.of(2026, 8, 4), 9, 11));
        actividades.add(new Actividad("Reunión con clientes", "Carlos Rojas", LocalDate.of(2026, 8, 3), 8, 9));
        actividades.add(new Actividad("Capacitación de onboarding", "Ana Solís", LocalDate.of(2026, 8, 6), 14, 16));
        actividades.add(new Actividad("Revisión de avance", "María Pérez", LocalDate.of(2026, 8, 12), 8, 9));
        actividades.add(new Actividad("Entrevista de selección", "Ana Solís", LocalDate.of(2026, 8, 13), 10, 11));
    }

    public ArrayList<Actividad> getActividades() {
        return actividades;
    }

    public LocalDate getFechaReferencia() {
        return fechaReferencia;
    }

    public void setFechaReferencia(LocalDate fechaReferencia) {
        this.fechaReferencia = fechaReferencia;
    }
}
