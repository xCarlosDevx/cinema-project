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
public class SnacksDAO {

    ConexionSqlite conect = new ConexionSqlite();
    Connection cn;
    PreparedStatement ps;
    ResultSet rs;

    public List traerDatos() {
        String sql = "select * from snackAdicional";

        List<SnacksModel> datos = new ArrayList<>();
        try {

            cn = conect.doConexion();
            ps = cn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                SnacksModel s = new SnacksModel();
                s.setId(rs.getInt(1));
                s.setNombre(rs.getString(2));
                s.setPrecio(rs.getDouble(3));
                s.setTipo(rs.getString(4));

                datos.add(s);

            }
        } catch (SQLException ex) {
            System.out.println("Error al traer los datos " + ex);
        }
        return datos;
    }

    public List traerDatosId(int id) {
        String sql = "select * from snackAdicional where id=" + id;

        List<SnacksModel> datos = new ArrayList<>();
        try {

            cn = conect.doConexion();
            ps = cn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                SnacksModel s = new SnacksModel();
                s.setId(rs.getInt(1));
                s.setNombre(rs.getString(2));
                s.setPrecio(rs.getDouble(3));
                s.setTipo(rs.getString(4));

                datos.add(s);

            }
        } catch (SQLException ex) {
            System.out.println("Error al traer los datos " + ex);
        }
        return datos;
    }

    public int insertarDatos(SnacksModel s) {
        String sql = "INSERT INTO snackAdicional (nombre,precio,tipo) VALUES (?,?,?)";

        try {

            cn = conect.doConexion();
            ps = cn.prepareStatement(sql);
            ps.setString(1, s.getNombre());
            ps.setDouble(2, s.getPrecio());
            ps.setString(3, s.getTipo());

            ps.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("Error al insertar los datos " + ex);
        }
        return 1;
    }

    public int editarDatos(SnacksModel s, int id) {

        String sql = "UPDATE snackAdicional set nombre=?,precio=?,tipo=? where id=" + id;

        try {
            cn = conect.doConexion();
            ps = cn.prepareStatement(sql);
            ps.setString(1, s.getNombre());
            ps.setDouble(2, s.getPrecio());
            ps.setString(3, s.getTipo());

            ps.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("Error al intentar editar los datos" + ex);
        }
        return 1;
    }

    public boolean eliminar(int id) {

        String sql = "delete from snackAdicional where id=" + id;

        try {
            cn = conect.doConexion();
            ps = cn.prepareStatement(sql);
            ps.executeUpdate();
        } catch (SQLException s) {
            System.out.println("Error al tratar de borrar datos: " + s);
        }
        return true;
    }

    public List traerDatosCombo() {
        String sql = "select * from snackCombo";

        List<SnacksModel> datos = new ArrayList<>();
        try {

            cn = conect.doConexion();
            ps = cn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                SnacksModel s = new SnacksModel();
                s.setId(rs.getInt(1));
                s.setNombre(rs.getString(2));
                s.setPrecio(rs.getDouble(3));
                s.setTipo(rs.getString(4));

                datos.add(s);

            }
        } catch (SQLException ex) {
            System.out.println("Error al traer los datos " + ex);
        }
        return datos;
    }

    public List traerDatosIdCombo(int id) {
        String sql = "select * from snackCombo where id=" + id;

        List<SnacksModel> datos = new ArrayList<>();
        try {

            cn = conect.doConexion();
            ps = cn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                SnacksModel s = new SnacksModel();
                s.setId(rs.getInt(1));
                s.setNombre(rs.getString(2));
                s.setPrecio(rs.getDouble(3));
                s.setTipo(rs.getString(4));

                datos.add(s);

            }
        } catch (SQLException ex) {
            System.out.println("Error al traer los datos " + ex);
        }
        return datos;
    }

    public int insertarDatosCombo(SnacksModel s) {
        String sql = "INSERT INTO snackCombo (nombre,precio,tipo) VALUES (?,?,?)";

        try {

            cn = conect.doConexion();
            ps = cn.prepareStatement(sql);
            ps.setString(1, s.getNombre());
            ps.setDouble(2, s.getPrecio());
            ps.setString(3, s.getTipo());

            ps.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("Error al insertar los datos " + ex);
        }
        return 1;
    }

    public int editarDatosCombo(SnacksModel s, int id) {

        String sql = "UPDATE snackCombo set nombre=?,precio=?,tipo=? where id=" + id;

        try {
            cn = conect.doConexion();
            ps = cn.prepareStatement(sql);
            ps.setString(1, s.getNombre());
            ps.setDouble(2, s.getPrecio());
            ps.setString(3, s.getTipo());

            ps.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("Error al intentar editar los datos" + ex);
        }
        return 1;
    }

    public boolean eliminarCombo(int id) {

        String sql = "delete from snackCombo where id=" + id;

        try {
            cn = conect.doConexion();
            ps = cn.prepareStatement(sql);
            ps.executeUpdate();
        } catch (SQLException s) {
            System.out.println("Error al tratar de borrar datos: " + s);
        }
        return true;
    }
}
