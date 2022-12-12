/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
import controlador.Controlador; 
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

        Login view = new Login(); 
        Controlador ctrl = new Controlador(view);
        ctrl.iniciar();
        view.setVisible(true);
    }

}
