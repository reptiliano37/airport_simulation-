package com.mycompany.aeropuerto;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author migue
 */
public class PauseControl {

    Empleado e;
    private boolean pause;
    private boolean cerrado = false;
    private boolean cerradoEmp1 = false;
    private boolean cerradoEmp2 = false;
    private Lock cerrojo = new ReentrantLock();
    private Condition pausarTodo = cerrojo.newCondition();
    private Condition pausarEmpleado1 = cerrojo.newCondition();
    private Condition pausarEmpleado2 = cerrojo.newCondition();

    public void mirar() {
        try {
            cerrojo.lock();
            while (cerrado) {
                try {
                    pausarTodo.await();
                } catch (Exception e) {
                }
            }
        } finally {
            cerrojo.unlock();
        }
    }

    public void mirarEmp1() {
        try {
            cerrojo.lock();
            while (cerradoEmp1) {
                try {
                    pausarEmpleado1.await();
                } catch (Exception e) {
                }
            }
        } finally {
            cerrojo.unlock();
        }
    }

    public void mirarEmp2() {
        try {
            cerrojo.lock();
            while (cerradoEmp2) {
                try {
                    pausarEmpleado2.await();
                } catch (Exception e) {
                }
            }
        } finally {
            cerrojo.unlock();
        }
    }

    public void abrir() {
        try {
            cerrojo.lock();
            cerrado = false;
            pausarTodo.signalAll();
            pausarEmpleado1.signalAll();
            pausarEmpleado2.signalAll();

        } finally {
            cerrojo.unlock();
        }
    }

    public void abrirEmp1() {
        try {
            cerrojo.lock();
            cerradoEmp1 = false;
            pausarEmpleado1.signalAll();

        } finally {
            cerrojo.unlock();
        }
    }

    public void abrirEmp2() {
        try {
            cerrojo.lock();
            cerradoEmp2 = false;
            pausarEmpleado2.signalAll();

        } finally {
            cerrojo.unlock();
        }
    }

    public void cerrar() {
        try {
            cerrojo.lock();
            cerrado = true;
        } finally {
            cerrojo.unlock();
        }
    }

    public void cerrarEmp1() {
        try {
            cerrojo.lock();
            cerradoEmp1 = true;
        } finally {
            cerrojo.unlock();
        }
    }

    public void cerrarEmp2() {
        try {
            cerrojo.lock();
            cerradoEmp2 = true;
        } finally {
            cerrojo.unlock();
        }
    }
}
