/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica;

/**
 *
 * @author marle
 *//**
 *
 * @author Michael
 */
public class Persona extends Contacto{
    private String apellido;
    private String genero;
    private String fechaNacimiento;
    private String ocupacion;
    private String Nacionalidad;

    public Persona(String apellido , String genero, String fechaNacimiento, String ocupacion, String Nacionalidad, String nombre, DoubleLinkedList<String[]> direcciones, DoubleLinkedList<String> emails, DoubleLinkedList<String> redes, DoubleLinkedList<String> fotos, DoubleLinkedList<String[]> fechas, DoubleLinkedList<String[]> telefonos) {
        super(nombre, direcciones, emails, redes, fotos, fechas, telefonos);
        this.apellido = apellido;
        this.genero = genero;
        this.fechaNacimiento = fechaNacimiento;
        this.ocupacion = ocupacion;
        this.Nacionalidad = Nacionalidad;
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

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getOcupacion() {
        return ocupacion;
    }

    public void setOcupacion(String ocupacion) {
        this.ocupacion = ocupacion;
    }

    public String getNacionalidad() {
        return Nacionalidad;
    }

    public void setNacionalidad(String Nacionalidad) {
        this.Nacionalidad = Nacionalidad;
    }
    
}
