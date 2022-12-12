/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
import controlador.Controlador;
import modelo.Modelo;
import vista.Login;
import modelo.QuerysBd;

/**
 *
 * @author elmen
 */
public class CinemaProject {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Login view = new Login();
        QuerysBd qr = new QuerysBd();
        Controlador ctrl = new Controlador(view, qr);
        ctrl.iniciar();
        view.setVisible(true);
    }

}
