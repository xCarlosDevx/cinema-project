/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.sql.*;

/**
 *
 * @author elmen
 */
public class QuerysBd extends ConexionSqlite {

    PreparedStatement ps;
    ConexionSqlite startConexion = new ConexionSqlite();
    Connection cone;

    public boolean probarConexion() {
        cone = startConexion.doConexion();
        return true;
    }
;
//    public boolean registrar(Producto pro) {
//        PreparedStatement ps = null;
//        Connection coon = doConexion();
//
//        String sql = "INSERT INTO producto (codigo,nombre,precio,cantidad) values (?,?,?,?)";
//
//        try {
//            ps = con.prepareStatement(sql);
//            ps.setString(1, getCodigo());
//            ps.setString(2, getNombre());
//            ps.setDouble(3, getPrecio());
//            ps.setInt(4, getCantidad());
//
//            ps.execute();
//            return true;
//        } catch (SQLException ex) {
//            System.out.println("Hubo un error " + ex);
//            return false;
//        } finally {
//            try {
//                con.close();
//            } catch (SQLExeption ex) {
//                System.out.println("Hubo un error " + ex);
//            }
//        }
//    }
}
