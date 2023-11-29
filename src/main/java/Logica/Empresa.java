/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica;

public class Empresa extends Contacto{
    private String razonSocial;
    private String tipoEmpresa;

    public Empresa(String razonSocial, String tipoEmpresa, String nombre, DoubleLinkedList<Direccion> direcciones, DoubleLinkedList<String> emails, DoubleLinkedList<RedSocial> redes, DoubleLinkedList<String> fotos, DoubleLinkedList<Fecha> fechas, DoubleLinkedList<Telefono> telefonos) {
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

