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
import modelo.PeliculasModel;
import modelo.PeliculasDAO;
import vista.PeliculasGUI;

/**
 *
 * @author elmen
 */
public class ControladorPeliculas implements ActionListener {

    PeliculasModel peli = new PeliculasModel();
    PeliculasDAO peliDAO = new PeliculasDAO();
    PeliculasGUI peliView = new PeliculasGUI();
    DefaultTableModel model = new DefaultTableModel();

    public ControladorPeliculas(PeliculasGUI peliView) {
        this.peliView = peliView;
        this.peliView.btnGuardar.addActionListener(this);
        this.peliView.btnEditar.addActionListener(this);
        this.peliView.btnEliminar.addActionListener(this);

        vaciarCampos(peliView);
        traer(peliView.tblPeliculas);
    }

    public final void traer(JTable tabla) {
        vaciarTabla();
        model = (DefaultTableModel) tabla.getModel();
        List<PeliculasModel> lista = (List<PeliculasModel>) peliDAO.traerDatos();
        Object[] object = new Object[7];
        for (int i = 0; i < lista.size(); i++) {
            object[0] = lista.get(i).getId();
            object[1] = lista.get(i).getNombre();
            object[2] = lista.get(i).getApellido();
            object[3] = lista.get(i).getNombreUsuario();
            object[4] = lista.get(i).getContraseña();
            object[5] = lista.get(i).getCargo();
            object[6] = lista.get(i).getRol();

            model.addRow(object);
        }
        peliView.tblPeliculas.setModel(model);
    }

    public final void traerId() {
        int id = Integer.parseInt(peliView.txtId.getText());
        vaciarTabla();
        List<PeliculasModel> lista = (List<PeliculasModel>) peliDAO.traerDatosId(id);
        for (int i = 0; i < lista.size(); i++) {
            peliView.txtNombre.setText(lista.get(i).getNombre());
            peliView.txtApellido.setText(lista.get(i).getApellido());
            peliView.txtNombreUsuario.setText(lista.get(i).getNombreUsuario());
            peliView.txtContra.setText(lista.get(i).getContraseña());
            peliView.txtCargo.setText(lista.get(i).getCargo());
            peliView.txtRol.setText(lista.get(i).getRol());
        }
        traer(peliView.tblPeliculas);
    }

    public void insertarPeliculas() {
        int r;
        String nombre = peliView.txtNombre.getText();
        String apellido = peliView.txtApellido.getText();
        String nombreUsuario = peliView.txtNombreUsuario.getText();
        String contraseña = peliView.txtContra.getText();
        String cargo = peliView.txtCargo.getText();
        String rol = peliView.txtRol.getText();

        peli.setNombre(nombre);
        peli.setApellido(apellido);
        peli.setNombreUsuario(nombreUsuario);
        peli.setContraseña(contraseña);
        peli.setCargo(cargo);
        peli.setRol(rol);

        if (revisarCampos(peliView) > 0) {
            r = peliDAO.insertarDatos(peli);
            if (r == 1) {
                JOptionPane.showMessageDialog(peliView, "Se ha agregado la pelicula!", "Continuar", JOptionPane.INFORMATION_MESSAGE);
                vaciarTabla();
                vaciarCampos(peliView);
                traer(peliView.tblPeliculas);
            } else {
                JOptionPane.showMessageDialog(peliView, "Error no se pudo agregar la pelicula", "Error", JOptionPane.ERROR_MESSAGE);
                vaciarTabla();
                vaciarCampos(peliView);
                traer(peliView.tblPeliculas);
            }
        }
    }

    public void editarPeliculas() {
        int r;
        String nombre = peliView.txtNombre.getText();
        String apellido = peliView.txtApellido.getText();
        String nombreUsuario = peliView.txtNombreUsuario.getText();
        String contraseña = peliView.txtContra.getText();
        String cargo = peliView.txtCargo.getText();
        String rol = peliView.txtRol.getText();

        peli.setNombre(nombre);
        peli.setApellido(apellido);
        peli.setNombreUsuario(nombreUsuario);
        peli.setContraseña(contraseña);
        peli.setCargo(cargo);
        peli.setRol(rol);

        if (revisarCampos(peliView) > 0) {
            if (JOptionPane.showConfirmDialog(peliView, "Seguro que quiere eliminar este registro?", "Continuar", JOptionPane.YES_NO_OPTION) == 0) {
                int id = Integer.parseInt(peliView.txtId.getText());
                r = peliDAO.editarDatos(peli, id);
                if (r == 1) {
                    JOptionPane.showMessageDialog(peliView, "Se ha editado la pelicula con el id " + id, "Continuar", JOptionPane.INFORMATION_MESSAGE);
                    vaciarTabla();
                    vaciarCampos(peliView);
                    traer(peliView.tblPeliculas);
                } else {
                    JOptionPane.showMessageDialog(peliView, "Error no se pudo editar la pelicula con el id " + id, "Error", JOptionPane.ERROR_MESSAGE);
                    vaciarTabla();
                    vaciarCampos(peliView);
                    traer(peliView.tblPeliculas);
                }
            }
        }

    }

    public void eliminarPeliculas() {

        if (JOptionPane.showConfirmDialog(peliView, "Seguro que quiere eliminar este registro?", "Continuar", JOptionPane.YES_NO_OPTION) == 0) {
            int id = Integer.parseInt(peliView.txtId.getText());
            peliDAO.eliminar(id);
            vaciarTabla();
            vaciarCampos(peliView);
            traer(peliView.tblPeliculas);
        }
    }

    public void vaciarTabla() {
        for (int i = 0; i < peliView.tblPeliculas.getRowCount(); i++) {
            model.removeRow(i);
            i = i - 1;
        }
    }

    public final void vaciarCampos(PeliculasGUI peliView) {
        peliView.txtId.setText("");
        peliView.txtNombre.setText("");
        peliView.txtApellido.setText("");
        peliView.txtNombreUsuario.setText("");
        peliView.txtContra.setText("");
        peliView.txtCargo.setText("");
        peliView.txtRol.setText("");
        peliView.txtNombre.requestFocus();

    }

    public int revisarCampos(PeliculasGUI e) {

        int status = 1;

        if (e.txtNombre.getText().equals("")) {
            JOptionPane.showMessageDialog(peliView, "El campo de nombre no puede estar vacio.", "Error", JOptionPane.ERROR_MESSAGE);
            e.txtNombre.requestFocus();
            status = 0;
        } else if (e.txtApellido.getText().equals("")) {
            JOptionPane.showMessageDialog(peliView, "El campo de apellido no puede estar vacio.", "Error", JOptionPane.ERROR_MESSAGE);
            e.txtApellido.requestFocus();
            status = 0;
        } else if (e.txtNombreUsuario.getText().equals("")) {
            JOptionPane.showMessageDialog(peliView, "El campo de nombre de usuario no puede estar vacio.", "Error", JOptionPane.ERROR_MESSAGE);
            e.txtNombreUsuario.requestFocus();
            status = 0;
        } else if (e.txtContra.getText().equals("")) {
            JOptionPane.showMessageDialog(peliView, "El campo de contraseña no puede estar vacio.", "Error", JOptionPane.ERROR_MESSAGE);
            e.txtContra.requestFocus();
            status = 0;
        } else if (e.txtCargo.getText().equals("")) {
            JOptionPane.showMessageDialog(peliView, "El campo de cargo no puede estar vacio.", "Error", JOptionPane.ERROR_MESSAGE);
            e.txtCargo.requestFocus();
            status = 0;
        } else if (e.txtRol.getText().equals("")) {
            JOptionPane.showMessageDialog(peliView, "El campo de rol no puede estar vacio.", "Error", JOptionPane.ERROR_MESSAGE);
            e.txtRol.requestFocus();
            status = 0;
        }
        return status;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == peliView.btnGuardar) {
            if (peliView.txtId.getText().equals("")) {
                insertarPeliculas();
                traer(peliView.tblPeliculas);
            } else {
                editarPeliculas();
                traer(peliView.tblPeliculas);
            }

        }
        if (e.getSource() == peliView.btnEditar) {
            traerId();
        }
        if (e.getSource() == peliView.btnEliminar) {
            eliminarPeliculas();
            vaciarCampos(peliView);
            traer(peliView.tblPeliculas);
        }

    }
}
