// Proyecto #1 - EIF206 Programación 3 (2026-II)
// Autor: bachi

package reservas.presentacion.actividades;

import reservas.logica.Actividad;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Locale;

public class View extends JPanel {

    private Controller controller;

    private JTextField txtFechaReferencia;

    private JTable tablaActividades;
    private DefaultTableModel modeloTabla;

    private static final int HORA_INICIO_JORNADA = 7;
    private static final int HORA_FIN_JORNADA = 17;

    public View() {

        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        add(crearPanelSemana(), BorderLayout.NORTH);
        add(crearPanelTabla(), BorderLayout.CENTER);
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    // =========================
    // PANEL SUPERIOR: SEMANA
    // =========================
    private JPanel crearPanelSemana() {

        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        panel.setBorder(BorderFactory.createTitledBorder("Semana de trabajo"));

        panel.add(new JLabel("Fecha de referencia (aaaa-mm-dd):"));

        txtFechaReferencia = new JTextField("2026-08-05", 10);
        panel.add(txtFechaReferencia);

        JButton btnCargar = new JButton("Cargar semana");
        JButton btnReporte = new JButton("Generar reporte");

        panel.add(btnCargar);
        panel.add(btnReporte);

        btnCargar.addActionListener(e -> cargarSemanaDesdeTexto());

        btnReporte.addActionListener(e -> controller.generarReporte());

        return panel;
    }

    // Lee y valida el texto de la fecha antes de pedirle al controller que cargue la semana
    private void cargarSemanaDesdeTexto() {

        String texto = txtFechaReferencia.getText().trim();

        LocalDate fecha;

        try {
            fecha = LocalDate.parse(texto);
        } catch (DateTimeParseException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "La fecha debe tener el formato aaaa-mm-dd, por ejemplo 2026-08-05.",
                    "Fecha inválida",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        controller.cargarSemana(fecha);
    }

    // =========================
    // PANEL CENTRAL: TABLA
    // =========================
    private JPanel crearPanelTabla() {

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Agenda semanal de actividades"));

        modeloTabla = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int fila, int columna) {
                return false;
            }
        };

        tablaActividades = new JTable(modeloTabla);
        tablaActividades.setRowHeight(42);
        tablaActividades.setDefaultRenderer(Object.class, crearRendererCeldas());

        JScrollPane scroll = new JScrollPane(tablaActividades);
        panel.add(scroll, BorderLayout.CENTER);

        return panel;
    }

    // Colorea la columna de horas y resalta las celdas que sí tienen una actividad
    private DefaultTableCellRenderer crearRendererCeldas() {

        return new DefaultTableCellRenderer() {

            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                             boolean hasFocus, int row, int column) {

                Component celda = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                if (column == 0) {
                    celda.setBackground(new Color(225, 225, 225));
                } else if (value != null && !value.toString().isEmpty()) {
                    celda.setBackground(new Color(210, 235, 255));
                } else {
                    celda.setBackground(Color.WHITE);
                }

                return celda;
            }
        };
    }

    // =========================
    // ACTUALIZAR LA AGENDA
    // =========================
    public void mostrarSemana(LocalDate[] diasSemana, ArrayList<Actividad> actividadesSemana) {

        DateTimeFormatter formatoColumna = DateTimeFormatter.ofPattern("dd/MM");

        String[] columnas = new String[diasSemana.length + 1];
        columnas[0] = "Hora";

        for (int i = 0; i < diasSemana.length; i++) {

            String nombreDia = diasSemana[i].getDayOfWeek().getDisplayName(TextStyle.SHORT, new Locale("es", "ES"));

            columnas[i + 1] = nombreDia + " " + diasSemana[i].format(formatoColumna);
        }

        modeloTabla.setColumnIdentifiers(columnas);
        modeloTabla.setRowCount(0);

        for (int hora = HORA_INICIO_JORNADA; hora <= HORA_FIN_JORNADA; hora++) {

            Object[] fila = new Object[diasSemana.length + 1];
            fila[0] = String.format("%02d:00", hora);

            for (int i = 0; i < diasSemana.length; i++) {
                fila[i + 1] = obtenerTextoCelda(diasSemana[i], hora, actividadesSemana);
            }

            modeloTabla.addRow(fila);
        }
    }

    private String obtenerTextoCelda(LocalDate dia, int hora, ArrayList<Actividad> actividadesSemana) {

        StringBuilder texto = new StringBuilder();

        for (int i = 0; i < actividadesSemana.size(); i++) {

            Actividad a = actividadesSemana.get(i);

            if (a.getFecha().equals(dia) && hora >= a.getHoraInicio() && hora < a.getHoraFin()) {

                if (texto.length() > 0) {
                    texto.append(" / ");
                }

                texto.append(a.getNombre()).append(" (").append(a.getFuncionario()).append(")");
            }
        }

        return texto.toString();
    }
}
