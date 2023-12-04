/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


/**
 * Clase contacto relacionado para llenar un comboBox 
 * @author 59397
 */
public class ContactoRelacionado {

    public Image tipoRelacionImagen;
    public String tipoRelacion;

    public ContactoRelacionado(Image tipoRelacionImagen, String tipoRelacion) {
        this.tipoRelacionImagen = tipoRelacionImagen;
        this.tipoRelacion = tipoRelacion;
    }

    public Image getTipoRelacionImagen() {
        return tipoRelacionImagen;
    }

    public void setTipoRelacionImagen(Image tipoRelacionImagen) {
        this.tipoRelacionImagen = tipoRelacionImagen;
    }

    public String getTipoRelacion() {
        return tipoRelacion;
    }

    public void setTipoRelacion(String tipoRelacion) {
        this.tipoRelacion = tipoRelacion;
    }

    @Override
    public String toString() {
        return "tipoRelacionImagen=" + tipoRelacionImagen + ", tipoRelacion=" + tipoRelacion + '}';
    }

    public static List<ContactoRelacionado> obtenerListaContactoRelacionado() {
        List<ContactoRelacionado> listaRelaciones = new ArrayList<>();

        listaRelaciones.add(new ContactoRelacionado(new Image(ContactoRelacionado.class.getResourceAsStream("/Imagenes/friendship.png")), "Amigo"));
        listaRelaciones.add(new ContactoRelacionado(new Image(ContactoRelacionado.class.getResourceAsStream("/Imagenes/couple.png")), "Pareja"));
        listaRelaciones.add(new ContactoRelacionado(new Image(ContactoRelacionado.class.getResourceAsStream("/Imagenes/home.png")), "Familiar"));
        listaRelaciones.add(new ContactoRelacionado(new Image(ContactoRelacionado.class.getResourceAsStream("/Imagenes/manager.png")), "Manager"));
        listaRelaciones.add(new ContactoRelacionado(new Image(ContactoRelacionado.class.getResourceAsStream("/Imagenes/businessman.png")), "Vendedor"));

        return listaRelaciones;
    }

    public static void llenarComboBoxContactoRelacionado(ComboBox<ContactoRelacionado> comboBox) {
        // Asegurarse de que el ComboBox no se haya cargado previamente
        if (comboBox.getItems().isEmpty()) {
            // Cargar datos en el ComboBox
            comboBox.getItems().addAll(ContactoRelacionado.obtenerListaContactoRelacionado());

            // Configurar cómo se muestra cada item con una celda personalizada
            comboBox.setCellFactory(lv -> new ListCell<ContactoRelacionado>() {
                private final ImageView imageView = new ImageView();

                @Override
                protected void updateItem(ContactoRelacionado item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null) {
                        setText(null);
                        setGraphic(null);
                    } else {
                        imageView.setImage(item.getTipoRelacionImagen());
                        imageView.setFitWidth(20);
                        imageView.setFitHeight(15);
                        setText(item.getTipoRelacion());
                        setGraphic(imageView);
                    }
                }
            });

            // Configurar cómo se muestra el item seleccionado en el botón del ComboBox
            comboBox.setButtonCell(new ListCell<ContactoRelacionado>() {
                private final ImageView imageView = new ImageView();

                @Override
                protected void updateItem(ContactoRelacionado item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null) {
                        setText(null);
                        setGraphic(null);
                    } else {
                        imageView.setImage(item.getTipoRelacionImagen());
                        imageView.setFitWidth(20);
                        imageView.setFitHeight(15);
                        setText(item.getTipoRelacion());
                        setGraphic(imageView);
                    }
                }
            });

        }
    }

}
