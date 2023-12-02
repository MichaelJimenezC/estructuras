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
public class Contacto implements Serializable, Comparable<Contacto> {

    private String nombre;
    private LinkedListPropia<Direccion> direcciones;
    private LinkedListPropia<String> emails;
    private LinkedListPropia<RedSocial> redes;
    private DoubleLinkedList<String> fotos;
    private LinkedListPropia<Fecha> fechas;
    private LinkedListPropia<Telefono> telefonos;
    private LinkedListPropia<Contacto> contactosRelacionados;
    private String Nacionalidad;

    public Contacto(String nombre, LinkedListPropia<Direccion> direcciones, LinkedListPropia<String> emails, LinkedListPropia<RedSocial> redes, DoubleLinkedList<String> fotos, LinkedListPropia<Fecha> fechas, LinkedListPropia<Telefono> telefonos, String Nacionalidad) {
        this.nombre = nombre;
        this.direcciones = direcciones;
        this.emails = emails;
        this.redes = redes;
        this.fotos = fotos;
        this.fechas = fechas;
        this.telefonos = telefonos;
        this.Nacionalidad = Nacionalidad;
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

    public String getNacionalidad() {
        return Nacionalidad;
    }

    public void setNacionalidad(String Nacionalidad) {
        this.Nacionalidad = Nacionalidad;
    }

   

    @Override
    public int compareTo(Contacto otroContacto) {
        return this.nombre.compareTo(otroContacto.nombre);

    }

}
