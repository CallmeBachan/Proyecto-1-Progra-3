// Proyecto #1 - EIF206 Programación 3 (2026-II)
// Autor: bachi

package Reservas;

import Reservas.presentacion.VentanaPrincipal;

import javax.swing.*;

public class Aplicacion {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            VentanaPrincipal ventana = new VentanaPrincipal();

            ventana.setVisible(true);
        });
    }
}
