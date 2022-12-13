/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import modelo.LoginDAO;
import vista.Login;
import vista.MenuGUI;

/**
 *
 * @author elmen
 */
public class ControladorLogin implements ActionListener {

    LoginDAO logDAO = new LoginDAO();
    Login logView = new Login();
    MenuGUI menView = new MenuGUI();

    public ControladorLogin(Login logView) {
        this.logView = logView;
        this.logView.btnIniciar.addActionListener(this);
        vaciarCampos(logView);
    }

    private void vaciarCampos(Login logView) {
        logView.txtNombreUsuario.setText("");
        logView.txtContra.setText("");
        logView.txtNombreUsuario.requestFocus();
    }

    private boolean revisarCampos(Login logView) {
        if (logView.txtNombreUsuario.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(logView, "El campo de nombre de usuario no puede estar vacio", "Error", JOptionPane.ERROR_MESSAGE);
            logView.txtNombreUsuario.requestFocus();
            return false;
        }
        if (logView.txtContra.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(logView, "El campo de contraseña no puede estar vacio", "Error", JOptionPane.ERROR_MESSAGE);
            logView.txtContra.requestFocus();
            return false;
        }
        return true;
    }

    public boolean iniciarSesion(String nombreUsuario, String contraseña, Login logView) {
        if (revisarCampos(logView)) {
            try {
                if (logDAO.iniciarSesion(nombreUsuario, contraseña)) {
                    JOptionPane.showMessageDialog(logView, "Ha iniciado sesion", "Continuar", JOptionPane.INFORMATION_MESSAGE);
                    logView.setVisible(false);
                    MenuGUI menView = new MenuGUI();
                    ControladorMenu menContol = new ControladorMenu(menView);
                    menView.setVisible(true);
                    menView.setLocationRelativeTo(null);
                    return true;
                } else {
                    JOptionPane.showMessageDialog(logView, "Hubo un error, El nombre de usuario o la contraseña deben ser incorrectas", "Error!", JOptionPane.ERROR_MESSAGE);
                    vaciarCampos(logView);
                    return false;
                }
            } catch (HeadlessException ex) {
                JOptionPane.showMessageDialog(logView, "Error al iniciar sesion " + ex, "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        return false;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == logView.btnIniciar) {
            String nombreUsuario = logView.txtNombreUsuario.getText();
            String contraseña = logView.txtContra.getText();
            iniciarSesion(nombreUsuario, contraseña, logView);
        }
    }
}
