/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente;

import javax.swing.JTextField;
import com.mycompany.aeropuerto.InterfaceServidor;
import java.rmi.Naming;
import javax.swing.JTextArea;
/**
 *
 * @author migue
 */
public class Cliente extends Thread{
    private JTextField jTextCinta;
    private JTextArea jTextBodega;
    private InterfaceServidor cinta;
    private InterfaceServidor bodega;
    
    
    public Cliente(JTextField jTextCinta, JTextArea jTextBodega){
        this.jTextCinta = jTextCinta;
        this.jTextBodega = jTextBodega;
    }
    
    public void run(){
        try {
            this.cinta = (InterfaceServidor) Naming.lookup("//127.0.0.1/cinta");
            this.bodega = (InterfaceServidor) Naming.lookup("//127.0.0.1/bodega");
        } catch (Exception e) {
        }
        while(true){
            try {
                jTextCinta.setText(cinta.mostrarEstadosRemote());
                jTextBodega.setText(bodega.mostrarEstadosRemote());
            } catch (Exception e) {
            }
        }
    }
    
    
}
