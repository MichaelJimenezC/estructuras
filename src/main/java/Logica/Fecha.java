/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

/**
 *
 * @author Michael
 */
public class Fecha implements Serializable{
    private String festividad;
    private LocalDate fecha;

    public Fecha(String festividad, LocalDate fecha) {
        this.festividad = festividad;
        this.fecha = fecha;
    }
    
}
