/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.proyecto_estructuras;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

/**
 * FXML Controller class
 *
 * @author marle
 */
public class CreateContactPageController {

    @FXML
    private Button cargarImagen;
    @FXML
    private ImageView imagenPerfil;

    @FXML
    private void handleCargarImagen() {
        FileChooser fileChooser = new FileChooser();
        // Configurar los filtros de extensión del archivo (opcional)
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Imágenes", "*.png", "*.jpg", "*.gif")
        );

        // Mostrar el diálogo de archivos
        File file = fileChooser.showOpenDialog(cargarImagen.getScene().getWindow());

        if (file != null) {
            // Mostrar la imagen seleccionada
            Image image = new Image(file.toURI().toString());
            imagenPerfil.setImage(image);
        }
    }

}
