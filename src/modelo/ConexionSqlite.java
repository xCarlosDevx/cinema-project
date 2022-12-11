/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author elmen
 */
public class ConexionSqlite {

//    private final String url = "jdbc:sqlite:C:\\cinema-project\\src\\modelo\\BaseDeDatosSQLITE\\cinema-db.db";
    Connection con = null;

    public Connection doConexion() {
        try {
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection("jdbc:sqlite:src\\modelo\\BaseDeDatosSQLITE\\cinema-bd.db");
            System.out.println("Se realizo la conexion");
        } catch (ClassNotFoundException ex) {
            System.out.println("Hubo un error en la conexion " + ex);
        } catch (SQLException ex) {
            Logger.getLogger(ConexionSqlite.class.getName()).log(Level.SEVERE, null, ex);
        }
        return con;
    }
}
