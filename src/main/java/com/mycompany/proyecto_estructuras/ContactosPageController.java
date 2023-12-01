/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.proyecto_estructuras;

import Logica.Contacto;
import Logica.DoubleLinkedList;
import Logica.LinkedListPropia;
import Logica.Persona;
import Logica.Telefono;
import java.io.IOException;
import java.net.URL;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
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
    public static Contacto contactoSelecionado = null;

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
                Persona p1 = (Persona) contacto;
                System.out.println(contacto);
                HBox principal = new HBox(40);
                principal.setAlignment(Pos.CENTER);
                VBox contactoInformacion = new VBox(5);
                contactoInformacion.setAlignment(Pos.CENTER);
                ImageView imgv = new ImageView();
                imgv.setFitWidth(40); // Establecer el ancho de la imagen
                imgv.setFitHeight(40); // Establecer la altura de la imagen
                imgv.setPreserveRatio(true);
                imgv.setSmooth(true);
                imgv.setCache(true);

                Label nombre = null;
                Label numero = null;
                for (Telefono telefono : p1.getTelefonos()) {
                    nombre = new Label(p1.getNombre() + " " + p1.getApellido());
                    numero = new Label(telefono.getPrefijo() + " " + telefono.getNumero());
                }

                nombre.setStyle("-fx-text-fill: #6735a4; -fx-font-weight: bold;");
                numero.setStyle("-fx-text-fill: #7F65FF");
                Button boton = new Button("Ver");
                boton.setOnAction(event -> {
                    contactoSelecionado = contacto;
                    try {
                        App.setRoot("MenuPersona");
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                });
                contactoInformacion.getChildren().addAll(nombre, numero);
                principal.getChildren().addAll(imgv, contactoInformacion, boton);
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
