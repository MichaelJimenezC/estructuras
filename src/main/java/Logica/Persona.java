/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica;

import java.io.Serializable;
import java.time.LocalDate;

/**
 *
 * @author marle
 *//**
 *
 * @author Michael
 */
public class Persona extends Contacto implements Serializable{
    private String apellido;
    private String genero;
    private LocalDate fechaNacimiento;
    private String ocupacion;

    public Persona(String apellido, String genero, LocalDate fechaNacimiento, String ocupacion, String nombre, LinkedListPropia<Direccion> direcciones, LinkedListPropia<String> emails, LinkedListPropia<RedSocial> redes, DoubleLinkedList<String> fotos, LinkedListPropia<Fecha> fechas, LinkedListPropia<Telefono> telefonos, String Nacionalidad) {
        super(nombre, direcciones, emails, redes, fotos, fechas, telefonos, Nacionalidad);
        this.apellido = apellido;
        this.genero = genero;
        this.fechaNacimiento = fechaNacimiento;
        this.ocupacion = ocupacion;
    }
    


   

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getOcupacion() {
        return ocupacion;
    }

    public void setOcupacion(String ocupacion) {
        this.ocupacion = ocupacion;
    }

  
    

    

 
    @Override
    public String toString() {
        return super.toString()+" , "+"Persona{" + "apellido=" + apellido + ", genero=" + genero + ", fechaNacimiento=" + fechaNacimiento + ", ocupacion=" + ocupacion +'}';
    }

    
    
}
