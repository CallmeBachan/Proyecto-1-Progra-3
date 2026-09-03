// Proyecto #1 - EIF206 Programación 3 (2026-II)
// Componente reutilizable de UI: un gráfico de barras simple dibujado con
// Graphics2D, para no depender de librerías externas como JFreeChart.
// Autor: bachi

package Reservas.ui;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class PanelGraficoBarras extends JPanel {

    private String titulo;
    private Color colorBarra;

    private ArrayList<String> etiquetas;
    private ArrayList<Integer> valores;

    public PanelGraficoBarras(String titulo, Color colorBarra) {

        this.titulo = titulo;
        this.colorBarra = colorBarra;
        this.etiquetas = new ArrayList<>();
        this.valores = new ArrayList<>();

        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(350, 220));
    }

    // Reemplaza los datos que se están graficando y vuelve a pintar el panel
    public void setDatos(ArrayList<String> etiquetas, ArrayList<Integer> valores) {

        this.etiquetas = etiquetas;
        this.valores = valores;

        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int ancho = getWidth();
        int alto = getHeight();

        int margenIzquierdo = 45;
        int margenInferior = 40;
        int margenSuperior = 30;
        int margenDerecho = 20;

        g2.setColor(new Color(60, 60, 60));
        g2.setFont(new Font("Arial", Font.BOLD, 13));
        g2.drawString(titulo, margenIzquierdo, 18);

        if (valores.isEmpty()) {

            g2.setFont(new Font("Arial", Font.PLAIN, 12));
            g2.drawString("Sin datos para mostrar", margenIzquierdo, alto / 2);
            return;
        }

        int valorMaximo = 1;

        for (int i = 0; i < valores.size(); i++) {

            if (valores.get(i) > valorMaximo) {
                valorMaximo = valores.get(i);
            }
        }

        int areaAlto = alto - margenSuperior - margenInferior;
        int areaAncho = ancho - margenIzquierdo - margenDerecho;

        // Eje vertical
        g2.setColor(new Color(190, 190, 190));
        g2.drawLine(margenIzquierdo, margenSuperior, margenIzquierdo, alto - margenInferior);

        // Eje horizontal
        g2.drawLine(margenIzquierdo, alto - margenInferior, ancho - margenDerecho, alto - margenInferior);

        int cantidadBarras = valores.size();
        int anchoPorBarra = areaAncho / cantidadBarras;
        int anchoBarra = anchoPorBarra / 2;

        for (int i = 0; i < cantidadBarras; i++) {

            int valor = valores.get(i);

            int alturaBarra = (int) ((valor / (double) valorMaximo) * (areaAlto - 20));

            int x = margenIzquierdo + (i * anchoPorBarra) + (anchoPorBarra - anchoBarra) / 2;
            int y = alto - margenInferior - alturaBarra;

            g2.setColor(colorBarra);
            g2.fillRect(x, y, anchoBarra, alturaBarra);

            g2.setColor(new Color(60, 60, 60));
            g2.setFont(new Font("Arial", Font.PLAIN, 11));
            g2.drawString(String.valueOf(valor), x + (anchoBarra / 2) - 4, y - 5);

            String etiqueta = etiquetas.get(i);
            g2.drawString(etiqueta, x - 5, alto - margenInferior + 15);
        }
    }
}
