/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import modelo.Modelo;
import vista.Login;

/**
 *
 * @author elmen
 */
public class Controlador implements ActionListener {

    private Login view;
    private Modelo model;

    public Controlador(Login view, Modelo model) {
        this.view = view;
        this.model = model;
        this.view.btnMultiplicar.addActionListener(this);
    }

    public void iniciar() {
        view.setTitle("Cinema");
        view.setLocationRelativeTo(null);
    }

    public void actionPerformed(ActionEvent e) {
        model.setNumeroUno(Integer.parseInt(view.txtNumeroUno.getText()));
        model.setNumeroDos(Integer.parseInt(view.txtNumeroDos.getText()));
        model.multiplicar();
        view.txtResultado.setText(String.valueOf(model.getResultado()));

    }

}