/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author elmen
 */
public class LoginDAO {

    ConexionSqlite conect = new ConexionSqlite();
    Connection cn;
    PreparedStatement ps;
    ResultSet rs;

    public boolean iniciarSesion(String nombreUsuario, String contraseña) {
        cn = conect.doConexion();

        String sql = "select * from empleados where nombreUsuario = ? and contraseña = ?";

        try {
            ps = cn.prepareStatement(sql);

            ps.setString(1, nombreUsuario);
            ps.setString(2, contraseña);

            rs = ps.executeQuery();

            while (rs.next()) {
                cn.close();
                return true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(LoginDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return false;
    }
}
