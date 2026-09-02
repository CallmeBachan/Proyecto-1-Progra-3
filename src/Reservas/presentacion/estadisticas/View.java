// Proyecto #1 - EIF206 Programación 3 (2026-II)
// Autor: bachi

package reservas.presentacion.estadisticas;

import reservas.logica.EstadisticaActividadSemana;
import reservas.logica.EstadisticaRecurso;
import reservas.ui.PanelGraficoBarras;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class View extends JPanel {

    private Controller controller;

    private JTextField txtRecursosDesde;
    private JTextField txtRecursosHasta;
    private JTextField txtActividadesDesde;
    private JTextField txtActividadesHasta;

    private DefaultTableModel modeloRecursos;
    private DefaultTableModel modeloActividades;

    private PanelGraficoBarras graficoRecursos;
    private PanelGraficoBarras graficoActividades;

    public View() {

        setLayout(new GridLayout(1, 2, 15, 0));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        add(crearPanelRecursos());
        add(crearPanelActividades());
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    // =========================
    // PANEL IZQUIERDO: RECURSOS
    // =========================
    private JPanel crearPanelRecursos() {

        JPanel panel = new JPanel(new BorderLayout(8, 8));
        panel.setBorder(BorderFactory.createTitledBorder("Recursos reservados"));

        JPanel panelFechas = new JPanel(new FlowLayout(FlowLayout.LEFT, 6, 6));
        panelFechas.add(new JLabel("Desde:"));

        txtRecursosDesde = new JTextField("2026-08-03", 9);
        panelFechas.add(txtRecursosDesde);

        panelFechas.add(new JLabel("Hasta:"));

        txtRecursosHasta = new JTextField("2026-08-13", 9);
        panelFechas.add(txtRecursosHasta);

        JButton btnConsultar = new JButton("Consultar");
        panelFechas.add(btnConsultar);

        btnConsultar.addActionListener(e -> consultarRecursos());

        panel.add(panelFechas, BorderLayout.NORTH);

        JPanel panelCentro = new JPanel(new GridLayout(2, 1, 0, 8));

        modeloRecursos = new DefaultTableModel(new String[]{"Categoría", "Cantidad"}, 0) {
            @Override
            public boolean isCellEditable(int fila, int columna) {
                return false;
            }
        };

        JTable tablaRecursos = new JTable(modeloRecursos);
        panelCentro.add(new JScrollPane(tablaRecursos));

        graficoRecursos = new PanelGraficoBarras("Recursos más utilizados", new Color(46, 139, 87));
        panelCentro.add(graficoRecursos);

        panel.add(panelCentro, BorderLayout.CENTER);

        return panel;
    }

    // =========================
    // PANEL DERECHO: ACTIVIDADES
    // =========================
    private JPanel crearPanelActividades() {

        JPanel panel = new JPanel(new BorderLayout(8, 8));
        panel.setBorder(BorderFactory.createTitledBorder("Actividades programadas"));

        JPanel panelFechas = new JPanel(new FlowLayout(FlowLayout.LEFT, 6, 6));
        panelFechas.add(new JLabel("Desde:"));

        txtActividadesDesde = new JTextField("2026-08-04", 9);
        panelFechas.add(txtActividadesDesde);

        panelFechas.add(new JLabel("Hasta:"));

        txtActividadesHasta = new JTextField("2026-08-15", 9);
        panelFechas.add(txtActividadesHasta);

        JButton btnConsultar = new JButton("Consultar");
        panelFechas.add(btnConsultar);

        btnConsultar.addActionListener(e -> consultarActividades());

        panel.add(panelFechas, BorderLayout.NORTH);

        JPanel panelCentro = new JPanel(new GridLayout(2, 1, 0, 8));

        modeloActividades = new DefaultTableModel(new String[]{"Semana", "Cantidad"}, 0) {
            @Override
            public boolean isCellEditable(int fila, int columna) {
                return false;
            }
        };

        JTable tablaActividades = new JTable(modeloActividades);
        panelCentro.add(new JScrollPane(tablaActividades));

        graficoActividades = new PanelGraficoBarras("Actividades por semana", new Color(230, 126, 34));
        panelCentro.add(graficoActividades);

        panel.add(panelCentro, BorderLayout.CENTER);

        return panel;
    }

    // =========================
    // LEER Y VALIDAR LAS FECHAS ANTES DE CONSULTAR
    // =========================
    private void consultarRecursos() {

        LocalDate desde = parsearFecha(txtRecursosDesde.getText());
        LocalDate hasta = parsearFecha(txtRecursosHasta.getText());

        if (desde == null || hasta == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "Las fechas deben tener el formato aaaa-mm-dd, por ejemplo 2026-08-03.",
                    "Fecha inválida",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        controller.cargarRecursos(desde, hasta);
    }

    private void consultarActividades() {

        LocalDate desde = parsearFecha(txtActividadesDesde.getText());
        LocalDate hasta = parsearFecha(txtActividadesHasta.getText());

        if (desde == null || hasta == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "Las fechas deben tener el formato aaaa-mm-dd, por ejemplo 2026-08-04.",
                    "Fecha inválida",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        controller.cargarActividades(desde, hasta);
    }

    private LocalDate parsearFecha(String texto) {

        try {
            return LocalDate.parse(texto.trim());
        } catch (DateTimeParseException ex) {
            return null;
        }
    }

    // =========================
    // ACTUALIZAR TABLAS Y GRÁFICOS
    // =========================
    public void mostrarRecursos(ArrayList<EstadisticaRecurso> lista) {

        modeloRecursos.setRowCount(0);

        ArrayList<String> etiquetas = new ArrayList<>();
        ArrayList<Integer> valores = new ArrayList<>();

        for (int i = 0; i < lista.size(); i++) {

            EstadisticaRecurso r = lista.get(i);

            modeloRecursos.addRow(new Object[]{r.getCategoria(), r.getCantidad()});

            etiquetas.add(r.getCategoria());
            valores.add(r.getCantidad());
        }

        graficoRecursos.setDatos(etiquetas, valores);
    }

    public void mostrarActividades(ArrayList<EstadisticaActividadSemana> lista) {

        modeloActividades.setRowCount(0);

        ArrayList<String> etiquetas = new ArrayList<>();
        ArrayList<Integer> valores = new ArrayList<>();

        for (int i = 0; i < lista.size(); i++) {

            EstadisticaActividadSemana a = lista.get(i);

            modeloActividades.addRow(new Object[]{a.getSemana(), a.getCantidad()});

            etiquetas.add(a.getSemana());
            valores.add(a.getCantidad());
        }

        graficoActividades.setDatos(etiquetas, valores);
    }
}
