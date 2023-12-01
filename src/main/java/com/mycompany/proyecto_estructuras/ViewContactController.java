/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.proyecto_estructuras;

import Direcciones.DireccionCb;
import Fechas.FechaCb;
import Logica.Contacto;
import Logica.Direccion;
import Logica.DoubleLinkedList;
import Logica.Fecha;
import Logica.LinkedListPropia;
import Logica.Persona;
import Logica.RedSocial;
import Logica.Telefono;
import Prefijos.PrefijoPais;
import Social_Media.RedesSociales;
import static com.mycompany.proyecto_estructuras.ContactosPageController.contactoSelecionado;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author marle
 */
public class ViewContactController implements Initializable {

    @FXML
    private Label labelNombreApellido;
    @FXML
    private ImageView ImagenContacto;
    @FXML
    private TextField txtApellidos;
    @FXML
    private TextField txtNombres;
    @FXML
    private TextField txtTelefono;
    @FXML
    private ComboBox<PrefijoPais> comboPrefijos2;
    @FXML
    private TextField txtEmail;
    @FXML
    private ComboBox<RedesSociales> comboBoxRedes;
    @FXML
    private TextField txtRedes;
    @FXML
    private TextField txtNacionalidad;
    @FXML
    private TextField txtOcupación;
    @FXML
    private TextField txtDireccion;
    @FXML
    private ComboBox<FechaCb> comboFechas;
    @FXML
    private DatePicker calendario;
    @FXML
    private ComboBox<DireccionCb> comboDirecciones;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        mostrarContactos();
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
            for (Contacto contacto : contactos) { // Suponiendo que tienes una lista de contactos
                writer.println("BEGIN:VCARD");
                writer.println("VERSION:3.0");
                writer.println("FN:" + contacto.getNombre());
                for (Direccion direccion : contacto.getDirecciones()) {
                    writer.println("ADR;TYPE=home:" + direccion);
                }
                for (String email : contacto.getEmails()) {
                    writer.println("EMAIL;TYPE=INTERNET: " + email);
                }
//          for (RedesSociales redes: contacto.getRedes()){
//          writer.println("X-SOCIALPROFILE;TYPE= "+);
//          }
                for (String foto : contacto.getFotos()) {
                    writer.println("PHOTO;TYPE=JPEG;VALUE=URI:" + foto);
                }
                for (Fecha fecha : contacto.getFechas()) {
                    writer.println("BDAY:" + fecha);
                }
                // Teléfonos
                for (Telefono telefono : contacto.getTelefonos()) {
                    writer.println("TEL;TYPE=" + telefono.getPrefijo() + ":" + telefono.getNumero());
                }
                for (Contacto relacionado : contacto.getContactosRelacionados()) {
                    writer.println("RELATED;TYPE=contact:" + relacionado.getNombre());
                }
                writer.println("END:VCARD");
                writer.println(); // Línea vacía entre contactos
            }
        } catch (FileNotFoundException e) {
            // Manejar la excepción
        }
    }
    @FXML
    private VBox vboxTelefonosDinamico;
    @FXML
    private VBox vboxEmailsDinamico;
    @FXML
    private VBox vboxRedesDinamico;
    @FXML
    private VBox vboxDireccionesDinamico;
    @FXML
    private VBox vboxFechasDinamico;

    @FXML
    public void regresarBoton(ActionEvent event) {
        try {
            App.setRoot("ContactosPage");
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    private void mostrarContactos() {
        System.out.println(contactoSelecionado);
        if (contactoSelecionado instanceof Persona) {
            Persona p1 = (Persona) contactoSelecionado;
            labelNombreApellido.setText(p1.getNombre() + " " + p1.getApellido());
            txtNombres.setText(p1.getNombre());
            txtNombres.setEditable(false);
            txtApellidos.setText(p1.getApellido());
            txtApellidos.setEditable(false);

            for (Telefono telefono : p1.getTelefonos()) {

                ComboBox<PrefijoPais> comboBoxTelefono = new ComboBox<>();
                comboBoxTelefono.setEditable(false);
                PrefijoPais.configurarComboBoxConPrefijos(comboBoxTelefono);

                // Encuentra y establece el país correspondiente al prefijo del teléfono
                PrefijoPais prefijoPaisSeleccionado = encontrarPrefijoPaisPorPrefijo(telefono.getPrefijo());
                comboBoxTelefono.setValue(prefijoPaisSeleccionado);
                comboBoxTelefono.setMouseTransparent(true);
                comboBoxTelefono.setFocusTraversable(false);

                TextField txtNumero = new TextField(telefono.getNumero());
                txtNumero.setPrefHeight(23.0);
                txtNumero.setPrefWidth(153.0);
                txtNumero.setStyle("-fx-border-color: #7F65FF; -fx-border-radius: 6;");

                txtNumero.setEditable(false);

                HBox hboxTelefono = new HBox(5);
                hboxTelefono.getChildren().addAll(comboBoxTelefono, txtNumero);
                vboxTelefonosDinamico.getChildren().add(hboxTelefono);

            }
            for (String email : p1.getEmails()) {
                TextField txtEmails = new TextField(email);
                txtEmails.setEditable(false);
                vboxEmailsDinamico.getChildren().add(txtEmails);

            }
            for (RedSocial red2 : p1.getRedes()) {
                HBox hboxRedes = new HBox();
                ComboBox<RedesSociales> comboRedes= new ComboBox<>();
                comboRedes.setEditable(false);
                RedesSociales.configurarComboBoxConRedes(comboRedes);
                RedesSociales redseleccionada= encontrarRedSocial(red2.getRed());
                comboRedes.setValue(redseleccionada);
                TextField txtRed = new TextField(red2.getRed() + " " + red2.getUsuario());
                txtRed.setPrefHeight(23.0);
                txtRed.setPrefWidth(153.0);
                txtRed.setStyle("-fx-border-color: #7F65FF; -fx-border-radius: 6;");
                txtRed.setEditable(false);
                hboxRedes.getChildren().addAll(comboRedes,txtRed);
                vboxRedesDinamico.getChildren().add(txtRed);
            }
            for (Direccion direccion : p1.getDirecciones()) {
                HBox hbooxDirecciones = new HBox();


                TextField txtDirecion1 = new TextField(direccion.getUbicacion());
                txtDirecion1.setPrefHeight(23.0);
                txtDirecion1.setPrefWidth(153.0);
                txtDirecion1.setStyle("-fx-border-color: #7F65FF; -fx-border-radius: 6;");

                txtDirecion1.setEditable(false);
                hbooxDirecciones.getChildren().addAll(txtDirecion1);
                vboxDireccionesDinamico.getChildren().add(hbooxDirecciones);

            }
            txtNacionalidad.setText(p1.getNacionalidad());
            txtNacionalidad.setEditable(false);
            txtOcupación.setText(p1.getOcupacion());
            txtOcupación.setEditable(false);

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

    private DireccionCb encontrarDireccion(String direccion) {
        for (DireccionCb direccionCombo : DireccionCb.obtenerDireccion()) {
            if (direccionCombo.getTipoDireccion().equals(direccion)) {
                return direccionCombo;
            }
        }
        return null;
    }
        private RedesSociales encontrarRedSocial(String redSocial) {
        for (RedesSociales redesCombo : RedesSociales.obtenerRedSocial()) {
            if (redesCombo.getNombreRedSocial().equals(redSocial)) {
                return redesCombo;
            }
        }
        return null;
    }
}
