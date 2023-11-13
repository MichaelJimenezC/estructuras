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
    private DoubleLinkedList<String[]> direcciones;
    private DoubleLinkedList<String> emails;
    private DoubleLinkedList<String> redes;
    private DoubleLinkedList<String> fotos;
    private DoubleLinkedList<String[]> fechas;
    private DoubleLinkedList<String[]> telefonos;
    private DoubleLinkedList<String[]> contactosRelacionados;

    public Contacto(String nombre, DoubleLinkedList<String[]> direcciones, DoubleLinkedList<String> emails, DoubleLinkedList<String> redes, DoubleLinkedList<String> fotos, DoubleLinkedList<String[]> fechas, DoubleLinkedList<String[]> telefonos) {
        this.nombre = nombre;
        this.direcciones = direcciones;
        this.emails = emails;
        this.redes = redes;
        this.fotos = fotos;
        this.fechas = fechas;
        this.telefonos = telefonos;
        
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public DoubleLinkedList<String[]> getDirecciones() {
        return direcciones;
    }

    public void setDirecciones(DoubleLinkedList<String[]> direcciones) {
        this.direcciones = direcciones;
    }

    
    public DoubleLinkedList<String[]> getFechas() {
        return fechas;
    }

    public void setFechas(DoubleLinkedList<String[]> fechas) {
        this.fechas = fechas;
    }

    public DoubleLinkedList<String[]> getTelefonos() {
        return telefonos;
    }

    public void setTelefonos(DoubleLinkedList<String[]> telefonos) {
        this.telefonos = telefonos;
    }

    public DoubleLinkedList<String[]> getContactosRelacionados() {
        return contactosRelacionados;
    }

    public void setContactosRelacionados(DoubleLinkedList<String[]> contactosRelacionados) {
        this.contactosRelacionados = contactosRelacionados;
    }
   
   
   
   
 
}
