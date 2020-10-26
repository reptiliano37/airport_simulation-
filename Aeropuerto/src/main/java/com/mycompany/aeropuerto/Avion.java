/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.aeropuerto;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Logger;
import javax.swing.JTextField;
import javax.swing.JTextArea;

/**
 *
 * @author migue
 */
public class Avion extends UnicastRemoteObject implements InterfaceServidor {

    private List<String> avion;
    private int max;
    private Lock cerrojo = new ReentrantLock();
    private Condition vacio = cerrojo.newCondition();
    private Condition lleno = cerrojo.newCondition();
    private final JTextArea bodegaAvion;
    
    public Avion(int max, JTextArea bodegaAvion) throws RemoteException{
        this.max = max;
        avion = new ArrayList<String>(max);
        this.bodegaAvion = bodegaAvion;

    }

    public void depositarMaletaBodega(String maleta) throws InterruptedException {

        cerrojo.lock();
        try {
            while (avion.size() == max) {
                lleno.await();
            }
            avion.add(maleta);
            imprimirBodega();
            vacio.signal();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            
            cerrojo.unlock();
        }
    }

    public void imprimirBodega() throws InterruptedException {

        String contenidoBodega = "";
        int contadorLinea = 0;
        for (int i = 0; i < avion.size(); i++) {
            contenidoBodega = contenidoBodega + avion.get(i) + " ";
            contadorLinea = contadorLinea + 1;
            //System.out.println(contenidoBodega);
            if (contadorLinea == 9) {
                contenidoBodega = contenidoBodega + " \n";
                contadorLinea = 0;
                
            }
        }
        bodegaAvion.setText(contenidoBodega);
    }

    @Override
    public String mostrarEstadosRemote() throws RemoteException {
        String contenidoBodega = "";
        int contadorLinea = 0;
        for (int i = 0; i < avion.size(); i++) {
            contenidoBodega = contenidoBodega + avion.get(i) + " ";
            contadorLinea = contadorLinea + 1;
            if (contadorLinea == 9) {
                contenidoBodega = contenidoBodega + avion.get(i) + " \n";
                contadorLinea = 0;
            }
        }
        return contenidoBodega;
    }
}
