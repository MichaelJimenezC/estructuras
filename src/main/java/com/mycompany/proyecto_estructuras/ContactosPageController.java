/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.proyecto_estructuras;

import Logica.Contacto;
import Logica.DoubleLinkedList;
import Logica.LinkedListPropia;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author marle
 */
public class ContactosPageController implements Initializable {

    @FXML
    VBox vboxVerContactos;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        LinkedListPropia<Contacto> contactos = App.usuario.getContactos();
        if (contactos.isEmpty()) {
            Label label = new Label("NO TIENES CONTACTOS");
            vboxVerContactos.getChildren().add(label);
        } else {
            System.out.println(contactos.size());
            for (Contacto contacto : contactos) {
                System.out.println(contacto);
                HBox principal = new HBox();
                ImageView imgv = new ImageView();
                Label nombre = new Label(contacto.getNombre());
                Button boton = new Button("Ver");
                boton.setOnAction(event -> {
                    try {
                        App.setRoot("Menu");
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                });
                principal.getChildren().addAll(imgv, nombre, boton);
                vboxVerContactos.getChildren().add(principal);

            }
        }
         
    }

    @FXML
    private void agregar(ActionEvent event) {
        try {
            App.setRoot("createContact");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
