// Proyecto #1 - EIF206 Programación 3 (2026-II)
// Ventana principal de demostración: solo las pestañas de Actividades y Estadísticas
// están implementadas; las demás quedarían pendientes para el resto del proyecto.
// Autor: bachi

package Reservas.presentacion;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

public class VentanaPrincipal extends JFrame {

    public VentanaPrincipal() {

        configurarVentana();

        JLabel lblUsuario = new JLabel("  Sesión: admin (Administrador)");
        lblUsuario.setFont(new Font("Arial", Font.PLAIN, 12));
        lblUsuario.setBorder(BorderFactory.createEmptyBorder(6, 0, 6, 0));

        JTabbedPane tabs = new JTabbedPane();

        reservas.presentacion.actividades.View vistaActividades = new reservas.presentacion.actividades.View();
        reservas.presentacion.actividades.Model modeloActividades = new reservas.presentacion.actividades.Model();
        new reservas.presentacion.actividades.Controller(vistaActividades, modeloActividades);

        reservas.presentacion.estadisticas.View vistaEstadisticas = new reservas.presentacion.estadisticas.View();
        reservas.presentacion.estadisticas.Model modeloEstadisticas = new reservas.presentacion.estadisticas.Model();
        reservas.presentacion.estadisticas.Controller controladorEstadisticas =
                new reservas.presentacion.estadisticas.Controller(vistaEstadisticas, modeloEstadisticas);

        tabs.addTab("Agenda de Actividades", vistaActividades);
        tabs.addTab("Estadísticas", vistaEstadisticas);

        setLayout(new BorderLayout());
        add(lblUsuario, BorderLayout.NORTH);
        add(tabs, BorderLayout.CENTER);

        controladorEstadisticas.cargarRecursos(LocalDate.of(2026, 8, 3), LocalDate.of(2026, 8, 13));
        controladorEstadisticas.cargarActividades(LocalDate.of(2026, 8, 4), LocalDate.of(2026, 8, 15));
    }

    private void configurarVentana() {

        setTitle("Sistema de Reservas - Demo (bachi)");
        setSize(1150, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
}
