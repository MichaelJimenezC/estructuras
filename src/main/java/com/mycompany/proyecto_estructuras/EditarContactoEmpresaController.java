/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.proyecto_estructuras;

import Logica.Persona;
import static com.mycompany.proyecto_estructuras.ContactosPageController.contactoSelecionado;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author marle
 */
public class EditarContactoEmpresaController implements Initializable {

    /**
     * Initializes the controller class.
     *
     * @param url
     */
    @FXML
    private TextField txtApellidosNuevos;
    @FXML
    private TextField txtNombresNuevos;
    @FXML
    private TextField txtNacionalidad;
    @FXML
    private TextField txtOcupación;

    @Override

    public void initialize(URL url, ResourceBundle rb) {
    }

    public void CambiarPestañaVista(ActionEvent event) {
        try {
            App.setRoot("MenuPersona");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void EliminarContacto(ActionEvent event) {

    }

    public void GuardarContactoActualizado(ActionEvent event) {
//        if (contactoSelecionado instanceof Persona) {
//            Persona personaSeleccionada = (Persona) contactoSelecionado;
//            String nuevoNombre = txtNombresNuevos.getText();
//            String ApellidoNuevo = txtApellidosNuevos.getText();
//            String nacionalidadNueva = txtNacionalidad.getText();
//            String ocupacionNueva = txtOcupación.getText();
//
//            Persona contacto = new Persona(nuevoNombre, ApellidoNuevo, cumpleaños, ocupacion, nombres, lldirecciones, llemails, llredes, fotos, llfechas, lltelefonos, nacionalidad);

        }

    }

