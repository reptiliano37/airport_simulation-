/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.aeropuerto;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author migue
 */
public class Log {
    
    BufferedWriter bw;
    private Lock cerrojo = new ReentrantLock();


//    FileOutputStream fos = new FileOutputStream(file, append);
//    OutputStreamWriter osw = new OutputStreamWriter(fos, charset);
  

    public Log() {
        
    }

    public void crearArchivo() throws IOException {
        
        File file = new File("C:\\Users\\migue\\Documents\\NetBeansProjects\\Aeropuerto\\src\\main\\java\\com\\mycompany\\aeropuerto\\evolucionAeropuerto.txt");
        bw = new BufferedWriter(new FileWriter(file));
        bw.write("--------------------- EVOLUCION AEROPUERTO ---------------------  \n\n");
        bw.flush();
    }

    public void escribirArchivo(String mensaje) {
       cerrojo.lock();
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime today = LocalDateTime.now();
            bw.write(formatter.format(today) + " INFO " + mensaje);
            bw.flush();
        } catch (Exception e) {
            System.out.println(e);
        }finally{
            cerrojo.unlock();
        }
    }

    public void cerrarArchivo() {
        try {
            bw.close();
        } catch (Exception e) {
        }
    }
}
