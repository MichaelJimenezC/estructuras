/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Direcciones;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author marle
 */
public class Direccion {

    private Image imagenDireccion;
    private String tipoDireccion;

    public Direccion(Image imagenDireccion, String tipoDireccion) {
        this.imagenDireccion = imagenDireccion;
        this.tipoDireccion = tipoDireccion;
    }

    public Image getImagenDireccion() {
        return imagenDireccion;
    }

    public void setImagenDireccion(Image imagenDireccion) {
        this.imagenDireccion = imagenDireccion;
    }

    public String getTipoDireccion() {
        return tipoDireccion;
    }

    public void setTipoDireccion(String tipoDireccion) {
        this.tipoDireccion = tipoDireccion;
    }

    public static List<Direccion> obtenerDireccion() {
        List<Direccion> listaDirecciones = new ArrayList<>();
        listaDirecciones.add(new Direccion(new Image(Direccion.class.getResourceAsStream("/Imagenes/casa.png")), " Casa"));
        listaDirecciones.add(new Direccion(new Image(Direccion.class.getResourceAsStream("/Imagenes/maleta.png")), " Trabajo"));
        listaDirecciones.add(new Direccion(new Image(Direccion.class.getResourceAsStream("/Imagenes/colegio.png")), " Universidad"));
        listaDirecciones.add(new Direccion(new Image(Direccion.class.getResourceAsStream("/Imagenes/colegio (1).png")), " Colegio"));
        return listaDirecciones;
    }
      public static void configurarComboBoxConDirecciones(ComboBox<Direccion> comboBox) {
        // Asegurarse de que el ComboBox no se haya cargado previamente
        if (comboBox.getItems().isEmpty()) {
            // Cargar datos en el ComboBox
            comboBox.getItems().addAll(Direccion.obtenerDireccion());

            // Configurar cómo se muestra cada item con una celda personalizada
            comboBox.setCellFactory(lv -> new ListCell<Direccion>() {
                private final ImageView imageView = new ImageView();

                @Override
                protected void updateItem(Direccion item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null) {
                        setText(null);
                        setGraphic(null);
                    } else {
                        imageView.setImage(item.getImagenDireccion());
                        imageView.setFitWidth(16);
                        imageView.setFitHeight(16);
                        setText(item.getTipoDireccion());
                        setGraphic(imageView);
                    }
                }
            });

            // Configurar cómo se muestra el item seleccionado en el botón del ComboBox
            comboBox.setButtonCell(new ListCell<Direccion>() {
                private final ImageView imageView = new ImageView();

                @Override
                protected void updateItem(Direccion item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null) {
                        setText(null);
                        setGraphic(null);
                    } else {
                        imageView.setImage(item.getImagenDireccion());
                        imageView.setFitWidth(16);
                        imageView.setFitHeight(16);
                        setText(item.getTipoDireccion());
                        setGraphic(imageView);
                    }
                }
            });
        }
    }

}
