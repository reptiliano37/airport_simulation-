/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.aeropuerto;

import static java.lang.Thread.sleep;
import java.util.ArrayList;
import java.util.logging.Logger;
import com.mycompany.aeropuerto.Cinta;
import java.util.List;
import java.util.logging.Level;

/**
 *
 * @author migue
 */
public class Pasajero extends Thread {

    private int id;
    private Cinta cinta;
    private Log log;
    private String maleta_1;
    private String maleta_2;
    private static final Logger LOGGER = Logger.getLogger(Pasajero.class
            .getClass().getName());

    public Pasajero(int id, Cinta cinta,Log log) {

        super.setName(String.valueOf(id));
        this.cinta = cinta;
        this.id = id;
        this.log = log;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void run() {

        try {
            //MALETA 1
            
                String maleta1_str = "IDPAS_" + getId() + "-M1";
                cinta.depositarMaleta(maleta1_str,log ,id);
                int tiempo = 500 + (int) (1000 * Math.random());
           
                Pasajero.sleep(tiempo); // Espera entre 0.5 y 1 seg.
                
                //MALETA 2
        
                String maleta2_str = "IDPAS_" + getId() + "-M2";
                cinta.depositarMaleta(maleta2_str,log,id);

        } catch (InterruptedException e) {
            System.out.println(e);
        }
    }
}
