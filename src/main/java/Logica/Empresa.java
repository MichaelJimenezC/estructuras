/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica;

import java.io.Serializable;

public class Empresa extends Contacto implements Serializable{
    private String razonSocial;
    private String tipoEmpresa;

    public Empresa(String razonSocial, String tipoEmpresa, String nombre, LinkedListPropia<Direccion> direcciones, LinkedListPropia<String> emails, LinkedListPropia<RedSocial> redes, DoubleLinkedList<String> fotos, LinkedListPropia<Fecha> fechas, LinkedListPropia<Telefono> telefonos) {
        super(nombre, direcciones, emails, redes, fotos, fechas, telefonos);
        this.razonSocial = razonSocial;
        this.tipoEmpresa = tipoEmpresa;
    }

  

   
    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getTipoEmpresa() {
        return tipoEmpresa;
    }

    public void setTipoEmpresa(String tipoEmpresa) {
        this.tipoEmpresa = tipoEmpresa;
    }

   

   
   
   
   
}

