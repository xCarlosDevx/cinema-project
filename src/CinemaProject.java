/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
import controlador.ControladorLogin;
import vista.Login;

/**
 *
 * @author elmen
 */
public class CinemaProject {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Login logView = new Login();
        ControladorLogin logControl = new ControladorLogin(logView);
        logView.setVisible(true);
        logView.setLocationRelativeTo(null);
    }

}
