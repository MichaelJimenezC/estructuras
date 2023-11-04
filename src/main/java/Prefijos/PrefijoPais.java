/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Prefijos;

import javafx.scene.image.Image;


/**
 *
 * @author marle
 */
public class PrefijoPais {
    private String prefijo;
    private String nombrePais;


    public PrefijoPais(String prefijo, String nombrePais) {
      
        this.prefijo = prefijo;
        this.nombrePais = nombrePais;
    }

    public String getPrefijo() {
        return prefijo;
    }

    public void setPrefijo(String prefijo) {
        this.prefijo = prefijo;
    }


    public String getNombrePais() {
        return nombrePais;
    }

    public void setNombrePais(String nombrePais) {
        this.nombrePais = nombrePais;
    }


    @Override
    public String toString() {
        return nombrePais + " (" + prefijo + ")";
    }

}
