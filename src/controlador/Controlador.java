/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import vista.EmpleadoGUI;
import vista.Login;
import vista.Menu;

/**
 *
 * @author elmen
 */
public class Controlador implements ActionListener {
    
    Login view = new Login();
    Menu menView = new Menu();
    
    public Controlador(Login view) {
        this.view = view;
        this.view.btnIniciar.addActionListener(this);
    }
    
    public void iniciar() {
        view.setTitle("Cinema");
        view.setLocationRelativeTo(null);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == view.btnIniciar) {
            ControladorMenu menControl = new ControladorMenu(menView);
            menView.setVisible(true);
            menView.setLocationRelativeTo(null);
            this.view.setVisible(false);
        }
    }
}
