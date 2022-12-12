/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author elmen
 */
public class PeliculasDAO {
    ConexionSqlite conect = new ConexionSqlite();
    Connection cn;
    PreparedStatement ps;
    ResultSet rs;

    public List traerDatos() {
        String sql = "select * from peliculas";

        List<PeliculasModel> datos = new ArrayList<>();
        try {

            cn = conect.doConexion();
            ps = cn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                PeliculasModel p = new PeliculasModel();
                p.setCategoria(rs.getString(1));
                p.setSala(rs.getInt(2));
                p.setNombre(rs.getString(3));
                p.setRangoE(rs.getInt(4));
                p.setFecha(rs.getString(5));
                p.setTipo(rs.getString(6));
                p.setPrecio(rs.getDouble(7));
                datos.add(p);

            }
        } catch (SQLException ex) {
            System.out.println("Error al traer los datos " + ex);
        }
        return datos;
    }

    public List traerDatosId(int id) {
        String sql = "select * from peliculas where id=" + id;

        List<PeliculasModel> datos = new ArrayList<>();
        try {

            cn = conect.doConexion();
            ps = cn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                PeliculasModel p = new PeliculasModel();
                p.setCategoria(rs.getString(1));
                p.setSala(rs.getInt(2));
                p.setNombre(rs.getString(3));
                p.setRangoE(rs.getInt(4));
                p.setFecha(rs.getString(5));
                p.setTipo(rs.getString(6));
                p.setPrecio(rs.getDouble(7));
                datos.add(p);

            }
        } catch (SQLException ex) {
            System.out.println("Error al traer los datos " + ex);
        }
        return datos;
    }

    public int insertarDatos(PeliculasModel p) {
        String sql = "INSERT INTO peliculas (categoria,numSala,nombre,eEdad,fechaEmision,tipo,precio) VALUES (?,?,?,?,?,?,?)";

        try {

            cn = conect.doConexion();
            ps = cn.prepareStatement(sql);
            ps.setString(1, p.getCategoria());
            ps.setInt(2, p.getSala());
            ps.setString(3, p.getNombre());
            ps.setInt(4, p.getRangoE());
            ps.setString(5, p.getFecha());
            ps.setString(6, p.getTipo());
            ps.setDouble(6, p.getPrecio());
            ps.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("Error al insertar los datos " + ex);
        }
        return 1;
    }

    public int editarDatos(PeliculasModel p, int id) {

        String sql = "UPDATE peliculas set categoria=?,numSala=?,nombre=?,eEdad=?,fechaEmision=?,tipo=?,precio=? where id="
                + id;

        try {
            cn = conect.doConexion();
            ps = cn.prepareStatement(sql);
            ps.setString(1, p.getCategoria());
            ps.setInt(2, p.getSala());
            ps.setString(3, p.getNombre());
            ps.setInt(4, p.getRangoE());
            ps.setString(5, p.getFecha());
            ps.setString(6, p.getTipo());
            ps.setDouble(6, p.getPrecio());
            ps.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("Error al intentar editar los datos" + ex);
        }
        return 1;
    }

    public boolean eliminar(int id) {

        String sql = "delete from peliculas where id=" + id;

        try {
            cn = conect.doConexion();
            ps = cn.prepareStatement(sql);
            ps.executeUpdate();
        } catch (SQLException p) {
            System.out.println("Error al tratar de borrar datos: " + p);
        }
        return true;
    }
}
