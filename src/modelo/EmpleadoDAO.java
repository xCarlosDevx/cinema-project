/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author elmen
 */
public class EmpleadoDAO {

    ConexionSqlite conect = new ConexionSqlite();
    Connection cn;
    PreparedStatement ps;
    ResultSet rs;

    public List traerDatos() {
        String sql = "select * from empleados";

        List<EmpleadoModel> datos = new ArrayList<>();
        try {

            cn = conect.doConexion();
            ps = cn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                EmpleadoModel e = new EmpleadoModel();
                e.setId(rs.getInt(1));
                e.setNombre(rs.getString(2));
                e.setApellido(rs.getString(3));
                e.setNombreUsuario(rs.getString(4));
                e.setContraseña(rs.getString(5));
                e.setCargo(rs.getString(6));
                e.setRol(rs.getString(7));
                datos.add(e);

            } 
        } catch (SQLException ex) {
            System.out.println("Error al traer los datos " + ex);
        }
        return datos;
    }
        public List traerDatosId(int id) {
        String sql = "select * from empleados where id="+id;

        List<EmpleadoModel> datos = new ArrayList<>();
        try {

            cn = conect.doConexion();
            ps = cn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                EmpleadoModel e = new EmpleadoModel();
                e.setId(rs.getInt(1));
                e.setNombre(rs.getString(2));
                e.setApellido(rs.getString(3));
                e.setNombreUsuario(rs.getString(4));
                e.setContraseña(rs.getString(5));
                e.setCargo(rs.getString(6));
                e.setRol(rs.getString(7));
                datos.add(e);

            }
        } catch (SQLException ex) {
            System.out.println("Error al traer los datos " + ex);
        }
        return datos;
    }

    public int insertarDatos(EmpleadoModel e) {
        String sql = "INSERT INTO empleados (nombre,apellido,nombreUsuario,contraseña,cargo,rol) VALUES (?,?,?,?,?,?)";

        try {

            cn = conect.doConexion();
            ps = cn.prepareStatement(sql);
            ps.setString(1, e.getNombre());
            ps.setString(2, e.getApellido());
            ps.setString(3, e.getNombreUsuario());
            ps.setString(4, e.getContraseña());
            ps.setString(5, e.getCargo());
            ps.setString(6, e.getRol());
            ps.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("Error al insertar los datos " + ex);
        }
        return 1;
    }

    public int editarDatos(EmpleadoModel e,int id) {

        String sql = "UPDATE empleados set nombre=?,apellido=?,nombreUsuario=?,contraseña=?,cargo=?,rol=? where id="+id;

        try {
            cn = conect.doConexion();
            ps = cn.prepareStatement(sql);
            ps.setString(1, e.getNombre());
            ps.setString(2, e.getApellido());
            ps.setString(3, e.getNombreUsuario());
            ps.setString(4, e.getContraseña());
            ps.setString(5, e.getCargo());
            ps.setString(6, e.getRol());
            ps.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("Error al intentar editar los datos" + ex);
        }
        return 1;
    }

    public boolean eliminar(int id) {

        String sql = "delete from empleados where id=" + id;

        try {
            cn = conect.doConexion();
            ps = cn.prepareStatement(sql);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al tratar de borrar datos: " + e);
        }
        return true;
    }
}
