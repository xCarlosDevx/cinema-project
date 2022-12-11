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

/**
 *
 * @author elmen
 */
public class ControladorEmpleado implements ActionListener {

    EmpleadoModel emp = new EmpleadoModel();
    EmpleadoDAO empDAO = new EmpleadoDAO();
    Empleado empView = new Empleado();
    DefaultTableModel model = new DefaultTableModel();

    public Controlador(empView e) {
        this.empView = e;
        this.empView.btnGuardar.addActionListener(this);
        this.empView.btnEditar.addActionListener(this);
        this.empView.btnEliminar.addActionListener(this);
        this.empView.btnCancelar.addActionListener(this);
        this.empView.btnBuscar.addActionListener(this);
        this.empView.txtBuscar.addActionListener(this);

        this.empView.btnGuardar.setEnabled(true);
        this.empView.btnEditar.setEnabled(false);
        this.empView.btnCancelar.setEnabled(true);
        this.empView.btnEliminar.setEnabled(false);
        this.empView.btnBuscar.setEnabled(true);

        //Limpiar formulario y Listar contactos
        limpiarCampos(e);
        traer(empView.tblEmpleado);
    }


    public final void traer(JTable tabla) {
        model = (DefaultTableModel) tabla.getModel();
        List<EmpleadoModel> lista = (List<EmpleadoModel>) empDAO.traerDatos();
        Object[] object = new Object[5];
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

    public void insertarEmpleado() {
        boolean r;
        String nombre = empView.txtNombre.getText();
        String apellido = empView.txtApellido.gettext();
        String nombreUsuario = empView.txtNombreUsuario.getText();
        String contraseña = empView.txtContraseña.getText();
        String cargo = empView.txtCargo.getText();
        String rol = empView.txtRol.getText();

        emp.setNombre(nombre);
        emp.setApellido(apellido);
        emp.setNombreUsuario(nombreUsuario);
        emp.setContraseña(contraseña);
        emp.setCargo(cargo);
        emp.setRol(rol);

        if (validarCampos(empView) > 0) {
            r = empDAO.insertarDatos(emp);
            if (r == true) {
                JOptionPane.showMessageDialog(empView, "Se ha agregado el empleado!", JOptionPane.INFORMATION_MESSAGE);

                traer(empView.tblEmpleado);
            } else {
                JOptionPane.showMessageDialog(empView, "Error no se pudo agregar el empleado", JOptionPane.ERROR_MESSAGE);

                traer(empView.tblEmpleado);
            }
        }
    }

    public void editarEmpleado() {

        int id = Integer.parseInt(empView.txtID.getText());
        String nombre = (String) empView.tblEmpleado.getValueAt(fila, 1);
        String apellido = (String) empView.tblEmpleado.getValueAt(fila, 2);
        String nombreUsuario = (String) empView.tblEmpleado.getValueAt(fila, 3);
        String contraseña = (String) empView.tblEmpleado.getValueAt(fila, 4);
        String cargo = (String) empView.tblEmpleado.getValueAt(fila, 5);
        String rol = (String) empView.tblEmpleado.getValueAt(fila, 6);

        empView.txtNombre.setText(nombre);
        empView.txtApellido.setText(apellido);
        empView.txtNombreUsuario.setText(nombreUsuario);
        empView.txtContraseña.setText(contraseña);
        empView.txtCargo.setText(cargo);
        empView.txtRol.setText(rol);

        empView.txtNombre.requestFocus();

    }

    public void eliminar() {

        if (JOptionPane.showConfirmDialog(empView, "Seguro que quiere eliminar este registro?", JOptionPane.YES_NO_OPTION) == 0) {
            int id = Integer.parseInt(empView.txtID.getText());
            empDAO.eliminar(id);
            JOptionPane.showMessageDialog(empView, "Se ha eliminado el registro", JOptionPane.INFORMATION_MESSAGE);

            traer(empView.tblEmpleado);
        }
    }

    public final void vaciarCampos(EmpleadoModel emp) {
        emp.txtNombre.setText("");
        emp.txtApellido.setText("");
        emp.txtNombreUsuario.setText("");
        emp.txtContraseña.setText("");
        emp.txtCargo.setText("");
        emp.txtRol.setText("");
        emp.txtNombre.requestFocus();
        
    }

    public int revisarCampos(EmpleadoModel emp) {

        int validacion = 1;

        if (e.txtNombre.getText().equals("")) {
            JOptionPane.showMessageDialog(empView, "El campo de nombre no puede estar vacio.", JOptionPane.ERROR_MESSAGE);
            e.txtNombre.requestFocus();
            validacion = 0;
        } else if (e.txtApellido.getText().equals("")) {
            JOptionPane.showMessageDialog(empView, "El campo de apellido no puede estar vacio.", JOptionPane.ERROR_MESSAGE);
            e.txtApellido.requestFocus();
            validacion = 0;
        } else if (e.txtNombreUsuario.getText().equals("")) {
            JOptionPane.showMessageDialog(empView, "El campo de nombre de usuario no puede estar vacio.", JOptionPane.ERROR_MESSAGE);
            e.txtNombreUsuario.requestFocus();
            validacion = 0;
        } else if (e.txtContraseña.getText().equals("")) {
            JOptionPane.showMessageDialog(empView, "El campo de contraseña no puede estar vacio.", JOptionPane.ERROR_MESSAGE);
            e.txtContraseña.requestFocus();
            validacion = 0;
        } else if (e.txtCargo.getText().equals("")) {
            JOptionPane.showMessageDialog(empView, "El campo de cargo no puede estar vacio.", JOptionPane.ERROR_MESSAGE);
            e.txtCargo.requestFocus();
            validacion = 0;
        } else if (e.txtRol.getText().equals("")) {
            JOptionPane.showMessageDialog(empView, "El campo de rol no puede estar vacio.", JOptionPane.ERROR_MESSAGE);
            e.txtRol.requestFocus();
            validacion = 0;
        }
//        } else {
//            validacion = 1;
//        }

        return validacion;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
             if (e.getSource() == empView.btnGuardar) {
            insertarEmpleado();
            traer(empView.tblContactos);
        }
        if (e.getSource() == empView.btnEditar) {
            editarEmpleado();
            this.empView.btnEditar.setEnabled(false);
        }
        if (e.getSource() == empView.btnEliminar) {
            eliminarEmpleado();
            this.empView.btnGuardar.setEnabled(true);
            this.empView.btnEditar.setEnabled(false);
            this.empView.btnCancelar.setEnabled(false);
            this.empView.btnEliminar.setEnabled(false);
            vaciarCampos(empView);
            traer(empView.tblContactos);
        }
        if (e.getSource() == empView.btnCancelar) {
            this.empView.btnGuardar.setEnabled(true);
            this.empView.btnEditar.setEnabled(false);
            this.empView.btnCancelar.setEnabled(false);
            this.empView.btnEliminar.setEnabled(false);
            this.empView.lblAviso.setVisible(false);
            empView.txtBuscar.setText("");
            vaciarCampos(empView);
            traer(empView.tblContactos);
        }
        if (e.getSource() == empView.btnBuscar) {
            if (!this.empView.txtBuscar.getText().equals("")) {
                this.empView.btnGuardar.setEnabled(true);
                this.empView.btnEditar.setEnabled(false);
                this.empView.btnCancelar.setEnabled(true);
                this.empView.btnEliminar.setEnabled(false);
                vaciarCampos(empView);
                listarContacto(empView.tblContactos);
            } else {
                this.empView.btnCancelar.setEnabled(true);
                JOptionPane.showMessageDialog(empView, "El campo de busqueda esta vacio.");
            }
        }
    }
    }
}
