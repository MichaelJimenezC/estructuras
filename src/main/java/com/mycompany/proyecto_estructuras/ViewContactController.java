/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.proyecto_estructuras;

import Logica.Contacto;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author marle
 */
public class ViewContactController implements Initializable {
  @FXML
  private TextField txtApellidos;
  @FXML
  private  TextField txtNombres;
  @FXML
  private TextField txtNumero;
  @FXML
  private TextField txtEmails;
  @FXML
  private TextField txtRedes;
  @FXML
  private TextField txtDirecciones;
  @FXML
  private TextField txtFechas;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
  
    private static void cargarDatosPersona(Contacto contacto,ImageView perfil, Label txtNombre,TextField txtTelefono, 
                                           TextField txtEmail,TextField txtredes, TextField txtDirecciones, TextField txtfechas) {
        // Lógica para cargar datos de la persona
        perfil.setImage(contacto.getFotos());
        txtNombre.setLabelFor(contacto.getNombre());
        txtEmail.setText(contacto.getEmails());
        txtTelefono.setText(contacto.getTelefonos());
        txtredes.setText(contacto.getRedes());
        txtDirecciones.setText(contacto.getDirecciones());
        txtfechas.setText(contacto.getFechas());
        // Configurar otros campos específicos de la persona...
    }
    
}
