/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Prefijos;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Clase bandera, creada con la finalidad de llenar el comboBox con la bandera
 * del país con su respectivo codigo y el numero de usuario para el respectivo
 * ingreso a la aplicación
 *
 * @author marle
 */
public class PrefijoPais {

    private Image bandera;
    private String prefijo;
    private String nombrePais;
    private ImageView graphic;

    public PrefijoPais(Image bandera, String prefijo, String nombrePais) {
        this.bandera = bandera;
        this.prefijo = prefijo;
        this.nombrePais = nombrePais;
    }

    public String getPrefijo() {
        return prefijo;
    }

    public void setPrefijo(String prefijo) {
        this.prefijo = prefijo;
    }

    public String getNombrePais() {
        return nombrePais;
    }

    public void setNombrePais(String nombrePais) {
        this.nombrePais = nombrePais;
    }

    public Image getBandera() {
        return bandera;
    }

    public void setBandera(Image bandera) {
        this.bandera = bandera;
    }

    public void setGraphic(ImageView imagen) {
        this.graphic = imagen;
    }

    @Override
    public String toString() {
        return nombrePais + " (" + prefijo + ")";
    }

}
