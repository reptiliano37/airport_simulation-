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
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Logger;
import javax.swing.JTextField;

/**
 *
 * @author migue
 */
public class Cinta extends UnicastRemoteObject implements InterfaceServidor {

    private ArrayList<String> cinta;
    private int max;
    private Lock cerrojo = new ReentrantLock();
    private Condition vacio = cerrojo.newCondition();
    private Condition lleno = cerrojo.newCondition();
    private final JTextField cintaMaletas;
    private Log log;

    public Cinta(int max, JTextField cintaMaletas, Log log) throws RemoteException {
        this.max = max;
        cinta = new ArrayList<String>(max);
        this.cintaMaletas = cintaMaletas;
        this.log = log;
    }

    public void depositarMaleta(String maleta, Log log, int id) throws InterruptedException {
        cerrojo.lock();
        try {
            while (cinta.size() == max) {
                lleno.await();
            }
            cinta.add(maleta);
            imprimirCinta();
            log.escribirArchivo("Pasajero " + id + " deposita " + maleta + " en la cinta. \n");
            vacio.signal();

        } catch (Exception e) {
            System.out.println(e);
        } finally {
            cerrojo.unlock();
        }
    }

    public void imprimirCinta() throws InterruptedException {

        String contenidoCinta = "";
        for (int i = 0; i < cinta.size(); i++) {
            contenidoCinta = contenidoCinta + cinta.get(i) + " ";
            cintaMaletas.setText(contenidoCinta);
        }
    }

    public String extraerMaleta(Log log, int id) throws InterruptedException {
        cerrojo.lock();
        String maleta = null;
        try {
            while (cinta.isEmpty()) {
                vacio.await();
            }
            maleta = cinta.get(0);
            cinta.remove(0);
            imprimirCinta();
            log.escribirArchivo("Empleado " + id + " llevando " + maleta + " a la bodega." + " \n");
            lleno.signal();

        } catch (Exception e) {
        } finally {
            cerrojo.unlock();
        }
        return maleta;
    }

    @Override
    public String mostrarEstadosRemote() throws RemoteException {
        String contenidoCinta = "";
        for (int i = 0; i < cinta.size(); i++) {
            contenidoCinta = contenidoCinta + cinta.get(i) + " ";
        }
        return contenidoCinta;
    }
}
