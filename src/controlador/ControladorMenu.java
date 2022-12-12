/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import modelo.Modelo;
import vista.Login;
import vista.EmpleadoGUI;
import vista.Menu;

/**
 *
 * @author elmen
 */
public class ControladorMenu implements ActionListener {

    Login view = new Login();
    Modelo model = new Modelo();

    public ControladorMenu(Login view) {
        this.view = view;
//        view.setVisible(false);
//        EmpleadoGUI empView = new EmpleadoGUI();
//        ControladorEmpleado empControl = new ControladorEmpleado(empView);
//        empView.setVisible(true);
//        empView.setLocationRelativeTo(null);
    }

    public void iniciar() {
        view.setTitle("Cinema");
        view.setLocationRelativeTo(null);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == view.btnMenu) {
            Menu menView = new Menu();
            menView.setVisible(true);
            view.setVisible(false);

        }
//        model.setNumeroUno(Integer.parseInt(view.txtNumeroUno.getText()));
//        model.setNumeroDos(Integer.parseInt(view.txtNumeroDos.getText()));
//        model.multiplicar();
//        view.txtResultado.setText(String.valueOf(model.getResultado()));

    }

}
