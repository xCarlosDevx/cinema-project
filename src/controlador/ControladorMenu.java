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
import vista.PeliculasGUI;
import vista.CafeteriaGUI;
import vista.Comida;
import vista.Peliculas;
import vista.Menu;

/**
 *
 * @author elmen
 */
public class ControladorMenu implements ActionListener {

    Menu view = new Menu();
    EmpleadoGUI empView = new EmpleadoGUI();

    PeliculasGUI peliView = new PeliculasGUI();
//    Modelo model = new Modelo();

    public ControladorMenu(Menu view) {
        this.view = view;
        this.view.btnEmpleado.addActionListener(this);
        this.view.btnCerrar.addActionListener(this);
        this.view.btnBoleteria.addActionListener(this);
        this.view.btnComida.addActionListener(this);
        this.view.btnPelicula.addActionListener(this);
        this.view.btnSnacks.addActionListener(this);

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

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == view.btnEmpleado) {
            ControladorEmpleado empControl = new ControladorEmpleado(empView);
            empView.setVisible(true);
            empView.setLocationRelativeTo(null);
        }
        if (e.getSource() == view.btnSnacks) {
            CafeteriaGUI cafView = new CafeteriaGUI();
            cafView.setVisible(true);
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
        if (e.getSource() == view.btnCerrar) {
            view.setVisible(false);
        }
//        model.setNumeroUno(Integer.parseInt(view.txtNumeroUno.getText()));
//        model.setNumeroDos(Integer.parseInt(view.txtNumeroDos.getText()));
//        model.multiplicar();
//        view.txtResultado.setText(String.valueOf(model.getResultado()));

    }

}
