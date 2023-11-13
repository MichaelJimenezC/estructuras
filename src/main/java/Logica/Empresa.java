/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica;

public class Empresa extends Contacto{
    private String razonSocial;
    private String numeroRegistro;
    private String tipoEmpresa;

    public Empresa(String razonSocial, String numeroRegistro, String tipoEmpresa, String nombre, DoubleLinkedList<String[]> direcciones, DoubleLinkedList<String> emails, DoubleLinkedList<String> redes, DoubleLinkedList<String> fotos, DoubleLinkedList<String[]> fechas, DoubleLinkedList<String[]> telefonos) {
        super(nombre, direcciones, emails, redes, fotos, fechas, telefonos);
        this.razonSocial = razonSocial;
        this.numeroRegistro = numeroRegistro;
        this.tipoEmpresa = tipoEmpresa;
    }

   
   

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getNumeroRegistro() {
        return numeroRegistro;
    }

    public void setNumeroRegistro(String numeroRegistro) {
        this.numeroRegistro = numeroRegistro;
    }

    public String getTipoEmpresa() {
        return tipoEmpresa;
    }

    public void setTipoEmpresa(String tipoEmpresa) {
        this.tipoEmpresa = tipoEmpresa;
    }

   
   
   
   
}

