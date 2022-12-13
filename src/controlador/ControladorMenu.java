/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.awt.Desktop;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.net.URI;
import java.net.URISyntaxException;
import javax.swing.JOptionPane;
import vista.AboutGUI;
import vista.Comida;
import vista.Login;
import vista.EmpleadoGUI;
import vista.PeliculasGUI;
import vista.SnacksGUI;
import vista.Peliculas;
import vista.MenuGUI;

/**
 *
 * @author elmen
 */
public class ControladorMenu implements ActionListener {

    MenuGUI view = new MenuGUI();
    EmpleadoGUI empView = new EmpleadoGUI();
    SnacksGUI snkView = new SnacksGUI();
    PeliculasGUI peliView = new PeliculasGUI();
    Login logView = new Login();
    AboutGUI abtView = new AboutGUI();
    Comida comView = new Comida();
//    LoginModel model = new LoginModel();

    public ControladorMenu(MenuGUI view) {
        this.view = view;

        this.view.btnEmpleado.addActionListener(this);
        this.view.btnCerrar.addActionListener(this);
        this.view.btnBoleteria.addActionListener(this);
        this.view.btnComida.addActionListener(this);
        this.view.btnPelicula.addActionListener(this);
        this.view.btnSnacks.addActionListener(this);
        this.view.btnAbt.addActionListener(this);
        this.abtView.btnAyuda.addActionListener(this);

//        view.setVisible(false);
//        EmpleadoGUI empView = new EmpleadoGUI();
//        ControladorEmpleado empCont00rol = new ControladorEmpleado(empView);
//        empView.setVisible(true);
//        empView.setLocationRelativeTo(null);
    }

    public void iniciar() {
        view.setTitle("Cinema");
        view.setLocationRelativeTo(null);
    }

    public void goToURL(String enlaceAAceder) {
        Desktop enlace = Desktop.getDesktop();
        try {
            enlace.browse(new URI(enlaceAAceder));
        } catch (IOException | URISyntaxException e) {
            e.getMessage();
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == view.btnEmpleado) {
            ControladorEmpleado empControl = new ControladorEmpleado(empView);
            empView.setVisible(true);
            empView.setLocationRelativeTo(null);
        }
        if (e.getSource() == view.btnSnacks) {
            ControladorSnacks snkControl = new ControladorSnacks(snkView);
            snkView.setVisible(true);
            snkView.setLocationRelativeTo(null);
        }
        if (e.getSource() == view.btnPelicula) {
            ControladorPeliculas peliControl = new ControladorPeliculas(peliView);
            peliView.setLocationRelativeTo(null);
            peliView.setVisible(true);
        }
        if (e.getSource() == view.btnBoleteria) {
            Peliculas bolView = new Peliculas();
            bolView.setVisible(true);
        }
        if (e.getSource() == view.btnComida) {
            abtView.setVisible(true);
            comView.setVisible(true);
        }
        if (e.getSource() == view.btnCerrar) {
            view.setVisible(false);
            logView.setVisible(true);
        }
        if (e.getSource() == view.btnAbt) {
            abtView.setVisible(true);
        }
        if (e.getSource() == abtView.btnAyuda) {
            goToURL("https://drive.google.com/file/d/1z51mYNYk7lfKLx-Dvun0FeVR5nUWHSGx/view?usp=sharing");
        }
//        model.setNumeroUno(Integer.parseInt(view.txtNumeroUno.getText()));
//        model.setNumeroDos(Integer.parseInt(view.txtNumeroDos.getText()));
//        model.multiplicar();
//        view.txtResultado.setText(String.valueOf(model.getResultado()));

    }

}
