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
public class Contacto implements Serializable{
    private String nombre;
    private LinkedListPropia<Direccion> direcciones;
    private LinkedListPropia<String> emails;
    private LinkedListPropia<RedSocial> redes;
    private DoubleLinkedList<String> fotos;
    private LinkedListPropia<Fecha> fechas;
    private LinkedListPropia<Telefono> telefonos;
    private LinkedListPropia<Contacto> contactosRelacionados;

    public Contacto(String nombre, LinkedListPropia<Direccion> direcciones, LinkedListPropia<String> emails, LinkedListPropia<RedSocial> redes, DoubleLinkedList<String> fotos, LinkedListPropia<Fecha> fechas, LinkedListPropia<Telefono> telefonos) {
        this.nombre = nombre;
        this.direcciones = direcciones;
        this.emails = emails;
        this.redes = redes;
        this.fotos = fotos;
        this.fechas = fechas;
        this.telefonos = telefonos;
        this.contactosRelacionados = new LinkedListPropia<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LinkedListPropia<Direccion> getDirecciones() {
        return direcciones;
    }

    public void setDirecciones(LinkedListPropia<Direccion> direcciones) {
        this.direcciones = direcciones;
    }

    public LinkedListPropia<String> getEmails() {
        return emails;
    }

    public void setEmails(LinkedListPropia<String> emails) {
        this.emails = emails;
    }

    public LinkedListPropia<RedSocial> getRedes() {
        return redes;
    }

    public void setRedes(LinkedListPropia<RedSocial> redes) {
        this.redes = redes;
    }

    public DoubleLinkedList<String> getFotos() {
        return fotos;
    }

    public void setFotos(DoubleLinkedList<String> fotos) {
        this.fotos = fotos;
    }

    public LinkedListPropia<Fecha> getFechas() {
        return fechas;
    }

    public void setFechas(LinkedListPropia<Fecha> fechas) {
        this.fechas = fechas;
    }

    public LinkedListPropia<Telefono> getTelefonos() {
        return telefonos;
    }

    public void setTelefonos(LinkedListPropia<Telefono> telefonos) {
        this.telefonos = telefonos;
    }

    public LinkedListPropia<Contacto> getContactosRelacionados() {
        return contactosRelacionados;
    }

    public void setContactosRelacionados(LinkedListPropia<Contacto> contactosRelacionados) {
        this.contactosRelacionados = contactosRelacionados;
    }

    @Override
    public String toString() {
        return "Contacto{" + "nombre=" + nombre + ", direcciones=" + direcciones + ", emails=" + emails + ", redes=" + redes + ", fotos=" + fotos + ", fechas=" + fechas + ", telefonos=" + telefonos + ", contactosRelacionados=" + contactosRelacionados + '}';
    }

    
   
   
   
 
}
