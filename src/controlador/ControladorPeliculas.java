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
        Object[] object = new Object[8];
        for (int i = 0; i < lista.size(); i++) {
            object[0] = lista.get(i).getId();
            object[1] = lista.get(i).getCategoria();
            object[2] = lista.get(i).getSala();
            object[3] = lista.get(i).getNombre();
            object[4] = lista.get(i).getRangoE();
            object[5] = lista.get(i).getFecha();
            object[6] = lista.get(i).getTipo();
            object[7] = lista.get(i).getPrecio();

            model.addRow(object);
        }
        peliView.tblPeliculas.setModel(model);
    }

    public final void traerId() {
        int id = Integer.parseInt(peliView.txtId.getText());
        vaciarTabla();
        List<PeliculasModel> lista = (List<PeliculasModel>) peliDAO.traerDatosId(id);
        for (int i = 0; i < lista.size(); i++) {
            peliView.txtCategoria.setText(lista.get(i).getCategoria());
            peliView.txtSala.setText(String.valueOf(lista.get(i).getSala()));
            peliView.txtNombre.setText(lista.get(i).getNombre());
            peliView.txtRangoE.setText(String.valueOf(lista.get(i).getRangoE()));
            peliView.txtFecha.setText(lista.get(i).getFecha());
            peliView.txtTipo.setText(lista.get(i).getTipo());
            peliView.txtTipo.setText(String.valueOf(lista.get(i).getPrecio()));

        }
        traer(peliView.tblPeliculas);
    }

    public void insertarPeliculas() {
        int r;
        String categoria = peliView.txtCategoria.getText();
        int sala =Integer.parseInt(peliView.txtSala.getText());
        String nombre = peliView.txtNombre.getText();
        int rango =Integer.parseInt(peliView.txtRangoE.getText());
        String fecha = peliView.txtFecha.getText();
        String tipo = peliView.txtTipo.getText();
        double precio = Integer.parseInt(peliView.txtPrecio.getText());

        peli.setCategoria(categoria);
        peli.setSala(sala);
        peli.setNombre(nombre);
        peli.setRangoE(rango);
        peli.setFecha(fecha);
        peli.setTipo(tipo);
        peli.setPrecio(precio);

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
        String categoria = peliView.txtCategoria.getText();
        int sala =Integer.parseInt(peliView.txtSala.getText());
        String nombre = peliView.txtNombre.getText();
        int rango =Integer.parseInt(peliView.txtRangoE.getText());
        String fecha = peliView.txtFecha.getText();
        String tipo = peliView.txtTipo.getText();
        double precio = Integer.parseInt(peliView.txtPrecio.getText());

        peli.setCategoria(categoria);
        peli.setSala(sala);
        peli.setNombre(nombre);
        peli.setRangoE(rango);
        peli.setFecha(fecha);
        peli.setTipo(tipo);
        peli.setPrecio(precio);


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
//        for (int i = 0; i < peliView.tblPeliculas.getRowCount(); i++) {
//            model.removeRow(i);
//            i = i - 1;
//        }
    }

    public final void vaciarCampos(PeliculasGUI peliView) {
        peliView.txtId.setText("");
                    peliView.txtCategoria.setText("");
            peliView.txtSala.setText("");
            peliView.txtNombre.setText("");
            peliView.txtRangoE.setText("");
            peliView.txtFecha.setText("");
            peliView.txtTipo.setText("");
            peliView.txtPrecio.setText("");
        peliView.txtCategoria.requestFocus();

    }

    public int revisarCampos(PeliculasGUI e) {

        int status = 1;

        if (e.txtCategoria.getText().equals("")) {
            JOptionPane.showMessageDialog(peliView, "El campo de categoria no puede estar vacio.", "Error", JOptionPane.ERROR_MESSAGE);
            e.txtCategoria.requestFocus();
            status = 0;
        } else if (e.txtSala.getText().equals("")) {
            JOptionPane.showMessageDialog(peliView, "El campo de sala no puede estar vacio.", "Error", JOptionPane.ERROR_MESSAGE);
            e.txtSala.requestFocus();
            status = 0;
        } else if (e.txtNombre.getText().equals("")) {
            JOptionPane.showMessageDialog(peliView, "El campo de nombre no puede estar vacio.", "Error", JOptionPane.ERROR_MESSAGE);
            e.txtNombre.requestFocus();
            status = 0;
        }else if (e.txtRangoE.getText().equals("")) {
            JOptionPane.showMessageDialog(peliView, "El campo de rango no puede estar vacio.", "Error", JOptionPane.ERROR_MESSAGE);
            e.txtRangoE.requestFocus();
            status = 0;
        }else if (e.txtFecha.getText().equals("")) {
            JOptionPane.showMessageDialog(peliView, "El campo de fecha no puede estar vacio.", "Error", JOptionPane.ERROR_MESSAGE);
            e.txtFecha.requestFocus();
            status = 0;
        }else if (e.txtTipo.getText().equals("")) {
            JOptionPane.showMessageDialog(peliView, "El campo de tipo no puede estar vacio.", "Error", JOptionPane.ERROR_MESSAGE);
            e.txtTipo.requestFocus();
            status = 0;
        }else if (e.txtPrecio.getText().equals("")) {
            JOptionPane.showMessageDialog(peliView, "El campo de precio no puede estar vacio.", "Error", JOptionPane.ERROR_MESSAGE);
            e.txtPrecio.requestFocus();
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
