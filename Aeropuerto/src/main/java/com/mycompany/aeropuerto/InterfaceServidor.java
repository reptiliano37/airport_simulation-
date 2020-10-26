/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.aeropuerto;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author migue
 */
public interface InterfaceServidor extends Remote{
    //String imprimirCintaRemote(String cinta) throws RemoteException;
    //String imprimirBodegaRemote(String Bodega) throws RemoteException;
    String mostrarEstadosRemote() throws RemoteException;
}
