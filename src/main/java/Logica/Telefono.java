/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica;

import java.io.Serializable;

/**
 *
 * @author Michael
 */
public class Telefono implements Serializable{
    private String pais;
    private String prefijo;
    private String numero;

    public Telefono(String pais, String prefijo, String numero) {
        this.pais = pais;
        this.prefijo = prefijo;
        this.numero = numero;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getPrefijo() {
        return prefijo;
    }

    public void setPrefijo(String prefijo) {
        this.prefijo = prefijo;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    @Override
    public String toString() {
        return super.toString()+" , "+"Telefono{" + "pais=" + pais + ", prefijo=" + prefijo + ", numero=" + numero + '}';
    }
    
     
}
