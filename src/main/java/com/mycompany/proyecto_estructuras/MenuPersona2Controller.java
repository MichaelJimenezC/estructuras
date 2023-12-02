/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.proyecto_estructuras;

import Logica.Contacto;
import Logica.Direccion;
import Logica.Empresa;
import Logica.Fecha;
import Logica.LinkedListPropia;
import Logica.Persona;
import Logica.RedSocial;
import Logica.Telefono;
import Prefijos.PrefijoPais;
import static com.mycompany.proyecto_estructuras.ContactosPageController.contactoSelecionado;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class para la clase Empresa
 *
 * @author marle
 */
public class MenuPersona2Controller implements Initializable {

    /**
     * Initializes the controller class.
     *
     */
    @FXML
    public void handleBotonRegresar(ActionEvent event) {
        try {
            App.setRoot("ContactosPage");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    public void exportarVCard(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        // Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("vCard files (*.vcf)", "*.vcf");
        fileChooser.getExtensionFilters().add(extFilter);
        // Show save dialog
        // Obtener el Stage
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        File file = fileChooser.showSaveDialog(stage);

        if (file != null) {
            saveContactAsVCard(file);
        }
    }

    private void saveContactAsVCard(File file) {
        try ( PrintWriter writer = new PrintWriter(file)) {
            LinkedListPropia<Contacto> contactos = App.usuario.getContactos();

            for (Contacto contacto : contactos) {
                if (contacto instanceof Empresa) { // Verificar que el contacto es una instancia de Empresa
                    Empresa e1 = (Empresa) contacto;

                    writer.println("BEGIN:VCARD");
                    writer.println("VERSION:3.0");
                    writer.println("FN:" + e1.getNombre());

                    for (String email : e1.getEmails()) {
                        writer.println("EMAIL;TYPE=INTERNET:" + email);
                    }

                    for (Direccion direccion : e1.getDirecciones()) {
                        // Ajustar formato de la dirección según tus atributos
                        writer.println("ADR;TYPE=" + direccion.getTipo() + ":;;" + direccion.getUbicacion() + ";;;;");
                    }

                    for (Telefono telefono : e1.getTelefonos()) {
                        // Formatear el número de teléfono con el prefijo del país y el número
                        String numeroCompleto = "+" + telefono.getPais() + telefono.getPrefijo() + telefono.getNumero();
                        writer.println("TEL:" + numeroCompleto);
                    }

                    for (Contacto relacionado : e1.getContactosRelacionados()) {
                        writer.println("RELATED;TYPE=contact:" + relacionado.getNombre());
                    }

                    writer.println("END:VCARD");
                    writer.println(); // Línea vacía entre contactos
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace(); // Manejar la excepción mostrando el stack trace
            // Considera mostrar un mensaje de error al usuario o registrar este error en un archivo de log
        }

    }
@FXML
private VBox vboxTelefonosEmpresa;
@FXML 
private VBox vboxEmailsEmpresa;
@FXML 
private VBox vboxRedesSocialesEmpresa;
@FXML 
private VBox vboxDireccionesEmpresa;
@FXML 
private VBox vboxFechasRelevantesEmpresa;
@FXML 
private Label labelNombreEmpresa;
@FXML 
private TextField txtNombresEmpresa;
@FXML
private ImageView ImagenEmpresa;
@FXML 
private Button buttonEmpresa;
    private void mostrarContactos(boolean valor) {
        if (valor) {
            buttonEmpresa.setText("Guardar");
            //aquí debo actualizar el objeto 
            //recorrer la lista del usuario actual y si coincide se obtiene, me voy a la lista de contacto
            //obtengo la lista recorre la lista de contacto y si coincide con el contacto que quiero modificar, lo igualo al contacto 

        } else {
            buttonEmpresa.setText("Editar");
        }

        System.out.println(contactoSelecionado);
        if (contactoSelecionado instanceof Persona) {
            Empresa e1 = (Empresa) contactoSelecionado;
            if (!e1.getFotos().isEmpty()) {

                ImagenEmpresa.setImage(new Image(e1.getFotos().get(0)));
            }
            labelNombreEmpresa.setText(e1.getRazonSocial());
            txtNombresEmpresa.setText(e1.getNombre());
            txtNombresEmpresa.setEditable(valor);
         
           

            for (Telefono telefono : e1.getTelefonos()) {

                ComboBox<PrefijoPais> comboBoxTelefono = new ComboBox<>();
                comboBoxTelefono.setEditable(valor);
                PrefijoPais.configurarComboBoxConPrefijos(comboBoxTelefono);

                // Encuentra y establece el país correspondiente al prefijo del teléfono
                PrefijoPais prefijoPaisSeleccionado = encontrarPrefijoPaisPorPrefijo(telefono.getPrefijo());
                comboBoxTelefono.setValue(prefijoPaisSeleccionado);
                comboBoxTelefono.setMouseTransparent(true);
                comboBoxTelefono.setFocusTraversable(valor);

                TextField txtNumero = new TextField(telefono.getNumero());
                txtNumero.setPrefHeight(23.0);
                txtNumero.setPrefWidth(153.0);
                txtNumero.setStyle("-fx-border-color: #7F65FF; -fx-border-radius: 6;");

                txtNumero.setEditable(valor);

                HBox hboxTelefono = new HBox(5);
                hboxTelefono.getChildren().addAll(comboBoxTelefono, txtNumero);
                vboxTelefonosEmpresa.getChildren().add(hboxTelefono);

            }
            for (String email : e1.getEmails()) {
                TextField txtEmails = new TextField(email);
                txtEmails.setEditable(valor);
                txtEmails.setPrefHeight(27.0);
                txtEmails.setPrefWidth(322.0);
                txtEmails.setStyle("-fx-border-color: #7F65FF; -fx-border-radius: 6;");
                vboxEmailsEmpresa.setPadding(new Insets(5, 40, 15, 0));

                vboxEmailsEmpresa.getChildren().add(txtEmails);

            }
            for (RedSocial red2 : e1.getRedes()) {
                TextField txtRed = new TextField(red2.getRed() + " " + red2.getUsuario());
                txtRed.setPrefHeight(27.0);
                txtRed.setPrefWidth(322.0);
                txtRed.setStyle("-fx-border-color: #7F65FF; -fx-border-radius: 6;");
                txtRed.setEditable(valor);
                vboxRedesSocialesEmpresa.setPadding(new Insets(5, 40, 15, 0));

                vboxRedesSocialesEmpresa.getChildren().add(txtRed);
            }
            for (Direccion direccion : e1.getDirecciones()) {
                TextField txtDirecion1 = new TextField(direccion.getUbicacion());
                txtDirecion1.setPrefHeight(27.0);
                txtDirecion1.setPrefWidth(322.0);
                txtDirecion1.setStyle("-fx-border-color: #7F65FF; -fx-border-radius: 6;");

                txtDirecion1.setEditable(valor);
                vboxDireccionesEmpresa.setPadding(new Insets(5, 40, 15, 0));
                vboxDireccionesEmpresa.getChildren().add(txtDirecion1);

            }
            for (Fecha fecha : e1.getFechas()) {
                TextField txtfecha = new TextField(fecha.getFecha().toString());
                txtfecha.setPrefWidth(322.0);
                txtfecha.setPrefHeight(27.0);
                txtfecha.setStyle("-fx-border-color: #7F65FF; -fx-border-radius: 6;");
                txtfecha.setEditable(valor);
                vboxFechasRelevantesEmpresa.setPadding(new Insets(5, 40, 15, 0));
                vboxFechasRelevantesEmpresa.getChildren().add(txtfecha);
            }
            

        }
    }
        private PrefijoPais encontrarPrefijoPaisPorPrefijo(String prefijo) {
        // Busca en la lista el prefijo del país que coincide y lo retorna
        for (PrefijoPais prefijoPais : PrefijoPais.obtenerPrefijosPais()) {
            if (prefijoPais.getPrefijo().equals(prefijo)) {
                return prefijoPais;
            }
        }
        return null; // O manejar de otra manera si no se encuentra el prefijo del país
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        mostrarContactos(false);
    }

}
