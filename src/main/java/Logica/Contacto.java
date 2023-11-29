/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica;


   /**
 *
 * @author Michael
 */
public class Contacto {
    private String nombre;
    private DoubleLinkedList<Direccion> direcciones;
    private DoubleLinkedList<String> emails;
    private DoubleLinkedList<RedSocial> redes;
    private DoubleLinkedList<String> fotos;
    private DoubleLinkedList<Fecha> fechas;
    private DoubleLinkedList<Telefono> telefonos;
    private DoubleLinkedList<Contacto> contactosRelacionados;

    public Contacto(String nombre, DoubleLinkedList<Direccion> direcciones, DoubleLinkedList<String> emails, DoubleLinkedList<RedSocial> redes, DoubleLinkedList<String> fotos, DoubleLinkedList<Fecha> fechas, DoubleLinkedList<Telefono> telefonos) {
        this.nombre = nombre;
        this.direcciones = direcciones;
        this.emails = emails;
        this.redes = redes;
        this.fotos = fotos;
        this.fechas = fechas;
        this.telefonos = telefonos;
        contactosRelacionados=new DoubleLinkedList<>();
        
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public DoubleLinkedList<Direccion> getDirecciones() {
        return direcciones;
    }

    public void setDirecciones(DoubleLinkedList<Direccion> direcciones) {
        this.direcciones = direcciones;
    }

    public DoubleLinkedList<String> getEmails() {
        return emails;
    }

    public void setEmails(DoubleLinkedList<String> emails) {
        this.emails = emails;
    }

    public DoubleLinkedList<RedSocial> getRedes() {
        return redes;
    }

    public void setRedes(DoubleLinkedList<RedSocial> redes) {
        this.redes = redes;
    }

    public DoubleLinkedList<String> getFotos() {
        return fotos;
    }

    public void setFotos(DoubleLinkedList<String> fotos) {
        this.fotos = fotos;
    }

    public DoubleLinkedList<Fecha> getFechas() {
        return fechas;
    }

    public void setFechas(DoubleLinkedList<Fecha> fechas) {
        this.fechas = fechas;
    }

    public DoubleLinkedList<Telefono> getTelefonos() {
        return telefonos;
    }

    public void setTelefonos(DoubleLinkedList<Telefono> telefonos) {
        this.telefonos = telefonos;
    }

    public DoubleLinkedList<Contacto> getContactosRelacionados() {
        return contactosRelacionados;
    }

    public void setContactosRelacionados(DoubleLinkedList<Contacto> contactosRelacionados) {
        this.contactosRelacionados = contactosRelacionados;
    }

    @Override
    public String toString() {
        return "Contacto{" + "nombre=" + nombre + ", direcciones=" + direcciones + ", emails=" + emails + ", redes=" + redes + ", fotos=" + fotos + ", fechas=" + fechas + ", telefonos=" + telefonos + ", contactosRelacionados=" + contactosRelacionados + '}';
    }
    
   
   
   
   
 
}
