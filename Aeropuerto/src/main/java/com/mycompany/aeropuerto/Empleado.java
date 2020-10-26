/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.aeropuerto;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextField;

/**
 *
 * @author migue
 */
public class Empleado extends Thread {

    private int id;
    private Cinta cinta;
    private Avion avion;
    private Log log;
    private PauseControl pauseControl;
    private final JTextField campoEmpleado1;
    private final JTextField campoEmpleado2;
    

    public Empleado(int id, PauseControl pauseControl, Cinta cinta, Avion avion, JTextField campoEmpleado1,
            JTextField campoEmpleado2, Log log) {
        this.cinta = cinta;
        this.avion = avion;
        this.pauseControl = pauseControl;
        this.id = id;
        this.log = log;
        this.campoEmpleado1 = campoEmpleado1;
        this.campoEmpleado2 = campoEmpleado2;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void run() {

        String maleta;
        String maletaCinta;
        Thread thisThread;
        thisThread = Thread.currentThread();

        while (true) { //No terminan nunca
            try {
                pauseControl.mirar();

                if (id == 1) {

                    //Empleado 1
                    pauseControl.mirarEmp1();
                    double tiempo = (double) (Math.random() * (700 - 400) + 400);
                    maleta = cinta.extraerMaleta(log,id);
                    maletaCinta = "Empleado " + getId() + " llevando " + maleta;
                    campoEmpleado1.setText(maletaCinta);

                    Empleado.sleep((long) tiempo);
                    avion.depositarMaletaBodega(maleta);
                    Empleado.sleep((long) tiempo); // Espera entre 0.4 y 0.7 seg.

                } else if (id == 2) {

                    //Empleado 2
                    
                    pauseControl.mirarEmp2();
                    double tiempo = (double) (Math.random() * (700 - 400) + 400);
                    maleta = cinta.extraerMaleta(log,id);
                    maletaCinta = "Empleado " + getId() + " llevando " + maleta;
                    campoEmpleado2.setText(maletaCinta);

                    Empleado.sleep((long) tiempo);
                    avion.depositarMaletaBodega(maleta);
                    Empleado.sleep((long) tiempo); // Espera entre 0.4 y 0.7 seg.
                }

            } catch (Exception e) {
                System.out.println(e);
            }
        }

    }
}
