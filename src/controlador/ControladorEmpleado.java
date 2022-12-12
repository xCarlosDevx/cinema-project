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
import modelo.EmpleadoModel;
import modelo.EmpleadoDAO;
import vista.EmpleadoGUI;

/**
 *
 * @author elmen
 */
public class ControladorEmpleado implements ActionListener {

    EmpleadoModel emp = new EmpleadoModel();
    EmpleadoDAO empDAO = new EmpleadoDAO();
    EmpleadoGUI empView = new EmpleadoGUI();
    DefaultTableModel model = new DefaultTableModel();

    public ControladorEmpleado(EmpleadoGUI empView) {
        this.empView = empView;
        this.empView.btnGuardar.addActionListener(this);
        this.empView.btnEditar.addActionListener(this);
        this.empView.btnEliminar.addActionListener(this);

        vaciarCampos(empView);
        traer(empView.tblEmpleado);
    }

    public final void traer(JTable tabla) {
        vaciarTabla();
        model = (DefaultTableModel) tabla.getModel();
        List<EmpleadoModel> lista = (List<EmpleadoModel>) empDAO.traerDatos();
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
        empView.tblEmpleado.setModel(model);
    }

    public final void traerId() {
        int id = Integer.parseInt(empView.txtId.getText());
        vaciarTabla();
        List<EmpleadoModel> lista = (List<EmpleadoModel>) empDAO.traerDatosId(id);
        for (int i = 0; i < lista.size(); i++) {
            empView.txtNombre.setText(lista.get(i).getNombre());
            empView.txtApellido.setText(lista.get(i).getApellido());
            empView.txtNombreUsuario.setText(lista.get(i).getNombreUsuario());
            empView.txtContra.setText(lista.get(i).getContraseña());
            empView.txtCargo.setText(lista.get(i).getCargo());
            empView.txtRol.setText(lista.get(i).getRol());
        }
        traer(empView.tblEmpleado);
    }

    public void insertarEmpleado() {
        int r;
        String nombre = empView.txtNombre.getText();
        String apellido = empView.txtApellido.getText();
        String nombreUsuario = empView.txtNombreUsuario.getText();
        String contraseña = empView.txtContra.getText();
        String cargo = empView.txtCargo.getText();
        String rol = empView.txtRol.getText();

        emp.setNombre(nombre);
        emp.setApellido(apellido);
        emp.setNombreUsuario(nombreUsuario);
        emp.setContraseña(contraseña);
        emp.setCargo(cargo);
        emp.setRol(rol);

        if (revisarCampos(empView) > 0) {
            r = empDAO.insertarDatos(emp);
            if (r == 1) {
                JOptionPane.showMessageDialog(empView, "Se ha agregado el empleado!", "Continuar", JOptionPane.INFORMATION_MESSAGE);
                vaciarTabla();
                vaciarCampos(empView);
                traer(empView.tblEmpleado);
            } else {
                JOptionPane.showMessageDialog(empView, "Error no se pudo agregar el empleado", "Error", JOptionPane.ERROR_MESSAGE);
                vaciarTabla();
                vaciarCampos(empView);
                traer(empView.tblEmpleado);
            }
        }
    }

    public void editarEmpleado() {
        int r;
        String nombre = empView.txtNombre.getText();
        String apellido = empView.txtApellido.getText();
        String nombreUsuario = empView.txtNombreUsuario.getText();
        String contraseña = empView.txtContra.getText();
        String cargo = empView.txtCargo.getText();
        String rol = empView.txtRol.getText();

        emp.setNombre(nombre);
        emp.setApellido(apellido);
        emp.setNombreUsuario(nombreUsuario);
        emp.setContraseña(contraseña);
        emp.setCargo(cargo);
        emp.setRol(rol);

        if (revisarCampos(empView) > 0) {
            if (JOptionPane.showConfirmDialog(empView, "Seguro que quiere eliminar este registro?", "Continuar", JOptionPane.YES_NO_OPTION) == 0) {
                int id = Integer.parseInt(empView.txtId.getText());
                r = empDAO.editarDatos(emp, id);
                if (r == 1) {
                    JOptionPane.showMessageDialog(empView, "Se ha editado el empleado con el id " + id, "Continuar", JOptionPane.INFORMATION_MESSAGE);
                    vaciarTabla();
                    vaciarCampos(empView);
                    traer(empView.tblEmpleado);
                } else {
                    JOptionPane.showMessageDialog(empView, "Error no se pudo editar el empleado con el id " + id, "Error", JOptionPane.ERROR_MESSAGE);
                    vaciarTabla();
                    vaciarCampos(empView);
                    traer(empView.tblEmpleado);
                }
            }
        }

    }

    public void eliminarEmpleado() {

        if (JOptionPane.showConfirmDialog(empView, "Seguro que quiere eliminar este registro?", "Continuar", JOptionPane.YES_NO_OPTION) == 0) {
            int id = Integer.parseInt(empView.txtId.getText());
            empDAO.eliminar(id);
            vaciarTabla();
            vaciarCampos(empView);
            traer(empView.tblEmpleado);
        }
    }

    public void vaciarTabla() {
        for (int i = 0; i < empView.tblEmpleado.getRowCount(); i++) {
            model.removeRow(i);
            i = i - 1;
        }
    }

    public final void vaciarCampos(EmpleadoGUI empView) {
        empView.txtId.setText("");
        empView.txtNombre.setText("");
        empView.txtApellido.setText("");
        empView.txtNombreUsuario.setText("");
        empView.txtContra.setText("");
        empView.txtCargo.setText("");
        empView.txtRol.setText("");
        empView.txtNombre.requestFocus();

    }

    public int revisarCampos(EmpleadoGUI e) {

        int status = 1;

        if (e.txtNombre.getText().equals("")) {
            JOptionPane.showMessageDialog(empView, "El campo de nombre no puede estar vacio.", "Error", JOptionPane.ERROR_MESSAGE);
            e.txtNombre.requestFocus();
            status = 0;
        } else if (e.txtApellido.getText().equals("")) {
            JOptionPane.showMessageDialog(empView, "El campo de apellido no puede estar vacio.", "Error", JOptionPane.ERROR_MESSAGE);
            e.txtApellido.requestFocus();
            status = 0;
        } else if (e.txtNombreUsuario.getText().equals("")) {
            JOptionPane.showMessageDialog(empView, "El campo de nombre de usuario no puede estar vacio.", "Error", JOptionPane.ERROR_MESSAGE);
            e.txtNombreUsuario.requestFocus();
            status = 0;
        } else if (e.txtContra.getText().equals("")) {
            JOptionPane.showMessageDialog(empView, "El campo de contraseña no puede estar vacio.", "Error", JOptionPane.ERROR_MESSAGE);
            e.txtContra.requestFocus();
            status = 0;
        } else if (e.txtCargo.getText().equals("")) {
            JOptionPane.showMessageDialog(empView, "El campo de cargo no puede estar vacio.", "Error", JOptionPane.ERROR_MESSAGE);
            e.txtCargo.requestFocus();
            status = 0;
        } else if (e.txtRol.getText().equals("")) {
            JOptionPane.showMessageDialog(empView, "El campo de rol no puede estar vacio.", "Error", JOptionPane.ERROR_MESSAGE);
            e.txtRol.requestFocus();
            status = 0;
        }
        return status;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == empView.btnGuardar) {
            if (empView.txtId.getText().equals("")) {
                insertarEmpleado();
                traer(empView.tblEmpleado);
            } else {
                editarEmpleado();
                traer(empView.tblEmpleado);
            }

        }
        if (e.getSource() == empView.btnEditar) {
            traerId();
        }
        if (e.getSource() == empView.btnEliminar) {
            eliminarEmpleado();
            vaciarCampos(empView);
            traer(empView.tblEmpleado);
        }

    }
}
