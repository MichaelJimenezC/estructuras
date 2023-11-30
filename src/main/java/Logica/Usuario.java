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
public class Usuario implements Serializable{
    private static final long serialVersionUID=1L;
    private String prefijo;
    private String telefono;
    private String contraseña;
    private LinkedListPropia<Contacto> contactos;

    public Usuario(String prefijo,String telefono, String contraseña) {
        this.prefijo=prefijo;
        this.telefono = telefono;
        this.contraseña = contraseña;
        this.contactos=new LinkedListPropia<>();
    } 

    public String getPrefijo() {
        return prefijo;
    }

    public void setPrefijo(String prefijo) {
        this.prefijo = prefijo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public LinkedListPropia<Contacto> getContactos() {
        return contactos;
    }

    public void setContactos(LinkedListPropia<Contacto> contactos) {
        this.contactos = contactos;
    }

    @Override
    public String toString() {
        return "Usuario{" + "prefijo=" + prefijo + ", telefono=" + telefono + ", contrase\u00f1a=" + contraseña +'}';
    }
   
}
