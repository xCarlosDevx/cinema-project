/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.Modelo;
import modelo.QuerysBd;
import modelo.EmpleadoModel;
import modelo.EmpleadoDAO;
import vista.Login;

/**
 *
 * @author elmen
 */
public class Controlador implements ActionListener {

    Login view = new Login();
    Modelo model = new Modelo();
    QuerysBd qr = new QuerysBd();
    EmpleadoDAO empDao = new EmpleadoDAO();
    EmpleadoModel emp = new EmpleadoModel();
    
    public Controlador(Login view, QuerysBd qr) {
        this.view = view;
        this.qr = qr;
        qr.doConexion();
//        this.view.btnMultiplicar.addActionListener(this);
    }

    public void iniciar() {
        view.setTitle("Cinema");
        view.setLocationRelativeTo(null);
    }

    public void actionPerformed(ActionEvent e) {
//        model.setNumeroUno(Integer.parseInt(view.txtNumeroUno.getText()));
//        model.setNumeroDos(Integer.parseInt(view.txtNumeroDos.getText()));
//        model.multiplicar();
//        view.txtResultado.setText(String.valueOf(model.getResultado()));

    }

}
