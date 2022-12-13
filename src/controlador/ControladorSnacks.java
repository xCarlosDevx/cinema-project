/*
 * Click nbfs://nbhost/SystemFileSystem/Tsnklates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Tsnklates/Classes/Class.java to edit this tsnklate
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.SnacksModel;
import modelo.SnacksDAO;
import vista.SnacksGUI;

/**
 *
 * @author elmen
 */
public class ControladorSnacks implements ActionListener {

    SnacksModel snk = new SnacksModel();
    SnacksDAO snkDAO = new SnacksDAO();
    SnacksGUI snkView = new SnacksGUI();
    DefaultTableModel model = new DefaultTableModel();

    public ControladorSnacks(SnacksGUI snkView) {
        this.snkView = snkView;
        this.snkView.btnGuardar.addActionListener(this);
        this.snkView.btnEditar.addActionListener(this);
        this.snkView.btnEliminar.addActionListener(this);

        vaciarCampos(snkView);
        traer(snkView.tblSnacks);
    }

    public final void traer(JTable tabla) {
        vaciarTabla();
        model = (DefaultTableModel) tabla.getModel();
        List<SnacksModel> lista = (List<SnacksModel>) snkDAO.traerDatos();
        Object[] object = new Object[7];
        for (int i = 0; i < lista.size(); i++) {
            object[0] = lista.get(i).getId();
            object[1] = lista.get(i).getNombre();
            object[2] = lista.get(i).getPrecio();
            object[3] = lista.get(i).getTipo();

            model.addRow(object);
        }
        snkView.tblSnacks.setModel(model);
    }

    public final void traerId() {
        int id = Integer.parseInt(snkView.txtId.getText());
        vaciarTabla();
        List<SnacksModel> lista = (List<SnacksModel>) snkDAO.traerDatosId(id);
        for (int i = 0; i < lista.size(); i++) {
            snkView.txtNombre.setText(lista.get(i).getNombre());
            snkView.txtPrecio.setText(String.valueOf(lista.get(i).getPrecio()));
            snkView.txtTipo.setText(lista.get(i).getTipo());
        }
        traer(snkView.tblSnacks);
    }

    public void insertarSnacks() {
        int r;
        String nombre = snkView.txtNombre.getText();
        double precio = Integer.parseInt(snkView.txtPrecio.getText());
        String tipo = snkView.txtTipo.getText();

        snk.setNombre(nombre);
        snk.setPrecio(precio);
        snk.setTipo(tipo);

        if (revisarCampos(snkView) > 0) {
            r = snkDAO.insertarDatos(snk);
            if (r == 1) {
                JOptionPane.showMessageDialog(snkView, "Se ha agregado el snack", "Continuar", JOptionPane.INFORMATION_MESSAGE);
                vaciarTabla();
                vaciarCampos(snkView);
                traer(snkView.tblSnacks);
            } else {
                JOptionPane.showMessageDialog(snkView, "Error no se pudo agregar el snack", "Error", JOptionPane.ERROR_MESSAGE);
                vaciarTabla();
                vaciarCampos(snkView);
                traer(snkView.tblSnacks);
            }
        }
    }

    public void editarSnacks() {
        int r;
        String nombre = snkView.txtNombre.getText();
        double precio = Integer.parseInt(snkView.txtPrecio.getText());
        String tipo = snkView.txtTipo.getText();

        snk.setNombre(nombre);
        snk.setPrecio(precio);
        snk.setTipo(tipo);
        if (revisarCampos(snkView) > 0) {
            if (JOptionPane.showConfirmDialog(snkView, "Seguro que quiere editar este registro", "Continuar", JOptionPane.YES_NO_OPTION) == 0) {
                int id = Integer.parseInt(snkView.txtId.getText());
                r = snkDAO.editarDatos(snk, id);
                if (r == 1) {
                    JOptionPane.showMessageDialog(snkView, "Se ha editado el snack con el id " + id, "Continuar", JOptionPane.INFORMATION_MESSAGE);
                    vaciarTabla();
                    vaciarCampos(snkView);
                    traer(snkView.tblSnacks);
                } else {
                    JOptionPane.showMessageDialog(snkView, "Error no se pudo editar el snack con el id " + id, "Error", JOptionPane.ERROR_MESSAGE);
                    vaciarTabla();
                    vaciarCampos(snkView);
                    traer(snkView.tblSnacks);
                }
            }
        }

    }

    public void eliminarSnacks() {

        if (JOptionPane.showConfirmDialog(snkView, "Seguro que quiere eliminar este registro?", "Continuar", JOptionPane.YES_NO_OPTION) == 0) {
            int id = Integer.parseInt(snkView.txtId.getText());
            snkDAO.eliminar(id);
            vaciarTabla();
            vaciarCampos(snkView);
            traer(snkView.tblSnacks);
        }
    }

    public void vaciarTabla() {
        for (int i = 0; i < snkView.tblSnacks.getRowCount(); i++) {
            model.removeRow(i);
            i = i - 1;
        }
    }

    public final void vaciarCampos(SnacksGUI snkView) {
        snkView.txtId.setText("");
        snkView.txtNombre.setText("");
        snkView.txtPrecio.setText("");
        snkView.txtTipo.setText("");

    }

    public int revisarCampos(SnacksGUI s) {

        int status = 1;

        if (s.txtNombre.getText().equals("")) {
            JOptionPane.showMessageDialog(snkView, "El campo de nombre no puede estar vacio.", "Error", JOptionPane.ERROR_MESSAGE);
            s.txtNombre.requestFocus();
            status = 0;
        } else if (s.txtPrecio.getText().equals("")) {
            JOptionPane.showMessageDialog(snkView, "El campo de precio no puede estar vacio.", "Error", JOptionPane.ERROR_MESSAGE);
            s.txtPrecio.requestFocus();
            status = 0;
        } else if (s.txtTipo.getText().equals("")) {
            JOptionPane.showMessageDialog(snkView, "El campo de tipo de comida no puede estar vacio.", "Error", JOptionPane.ERROR_MESSAGE);
            s.txtTipo.requestFocus();
            status = 0;

            return status;
        }
        return 0;

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == snkView.btnGuardar) {
            if (snkView.txtId.getText().equals("")) {
                insertarSnacks();
                traer(snkView.tblSnacks);
            } else {
                editarSnacks();
                traer(snkView.tblSnacks);
            }

        }
        if (e.getSource() == snkView.btnEditar) {
            traerId();
        }
        if (e.getSource() == snkView.btnEliminar) {
            eliminarSnacks();
            vaciarCampos(snkView);
            traer(snkView.tblSnacks);
        }

    }
}
