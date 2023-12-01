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
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
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
                if (contacto instanceof Persona){
                Persona p1 = (Persona) contacto;
                 ImageView imgv = new ImageView ();
                if (!p1.getFotos().isEmpty()){
                imgv.setImage(new Image(p1.getFotos().get(0)));
                imgv.setFitWidth(80); // Establecer el ancho de la imagen
                imgv.setFitHeight(80); // Establecer la altura de la imagen
                imgv.setPreserveRatio(true);
                imgv.setSmooth(true);
                imgv.setCache(true);

                
                }
                System.out.println(contacto);
                HBox principal = new HBox(40);
                principal.setAlignment(Pos.CENTER);
                VBox contactoInformacion = new VBox(5);
                contactoInformacion.setAlignment(Pos.CENTER);
              
                Label nombre = null;
                Label numero = null;
                
                for (Telefono telefono : p1.getTelefonos()) {
                    nombre = new Label(p1.getNombre() + " " + p1.getApellido());
                    numero = new Label(telefono.getPrefijo() + " " + telefono.getNumero());
                }

                nombre.setStyle("-fx-text-fill: #6735a4; -fx-font-weight: bold;");
                numero.setStyle("-fx-text-fill: #7F65FF");
                Button boton = new Button("Ver");
                boton.setStyle("-fx-background-color: #FFFF;");
                boton.setStyle("-fx-font-weight: bold; -fx-text-fill: #6735a4;"); // Fuente en negrita y color morado
                boton.setOnAction(event -> {
                    contactoSelecionado = contacto;
                    try {
                        App.setRoot("MenuPersona");
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                });
                principal.setStyle("-fx-border-color: #D3D3D3");
                 principal.setPadding(new Insets(5, 5, 5, 5));
                contactoInformacion.getChildren().addAll(nombre, numero);
                principal.getChildren().addAll(imgv, contactoInformacion, boton);
                vboxVerContactos.setMargin(principal, new Insets(10, 10, 10, 10));

                vboxVerContactos.getChildren().add(principal);

            }else{
                //para la empresa
                }}
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
