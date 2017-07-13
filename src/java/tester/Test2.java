/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tester;

import catastro.logica.servicios.ServiciosParroquia;
import catastro.logica.servicios.ServiciosSector;

/**
 *
 * @author Geovanny
 */
public class Test2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            System.out.println("test zonas: "+
                    ServiciosSector.obtenerSectoresDadoZona(1).size());
        } catch (Exception e) {
            System.out.println("error: "+e.getMessage());
        }
    }
    
}
