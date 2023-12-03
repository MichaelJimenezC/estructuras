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
import Logica.Empresa;
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
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class de la clase Persona
 *
 * @author marle
 */
public class ViewContactController implements Initializable {

    @FXML
    private Label labelNombreApellido;

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
        mostrarContactos(false);
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
    private Button button;

    @FXML
    public void regresarBoton(ActionEvent event) {
        try {
            App.setRoot("ContactosPage");
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }
    @FXML
    private ImageView ImagenContacto;

    @FXML
    public void handleEditarBoton(ActionEvent event) {
        try {
            App.setRoot("EditarContactoPersona");
            mostrarContactos(true);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
//haccer un set on action para el boton para que se cambie a la funcionalidad de guardar o editar 

    private void mostrarContactos(boolean valor) {
        
        System.out.println(contactoSelecionado);
        if (contactoSelecionado instanceof Persona) {
            Persona p1 = (Persona) contactoSelecionado;
            if (!p1.getFotos().isEmpty()) {
                ImagenContacto.setImage(new Image("file:" + p1.getFotos().get(0)));
                ImagenContacto.setFitWidth(80); // nuevo ancho
                ImagenContacto.setFitHeight(80); // nueva altura
                ImagenContacto.setPreserveRatio(false); // preserva la relación de aspecto
            }
            labelNombreApellido.setText(p1.getNombre() + " " + p1.getApellido());
            txtNombres.setText(p1.getNombre());
            txtNombres.setEditable(valor);
            txtApellidos.setText(p1.getApellido());
            txtApellidos.setEditable(valor);

            for (Telefono telefono : p1.getTelefonos()) {

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
                vboxTelefonosDinamico.getChildren().add(hboxTelefono);

            }
            for (String email : p1.getEmails()) {
                TextField txtEmails = new TextField(email);
                txtEmails.setEditable(valor);
                txtEmails.setPrefHeight(27.0);
                txtEmails.setPrefWidth(322.0);
                txtEmails.setStyle("-fx-border-color: #7F65FF; -fx-border-radius: 6;");
                vboxEmailsDinamico.setPadding(new Insets(5, 40, 15, 0));

                vboxEmailsDinamico.getChildren().add(txtEmails);

            }
            for (RedSocial red2 : p1.getRedes()) {
                TextField txtRed = new TextField(red2.getRed() + " " + red2.getUsuario());
                txtRed.setPrefHeight(27.0);
                txtRed.setPrefWidth(322.0);
                txtRed.setStyle("-fx-border-color: #7F65FF; -fx-border-radius: 6;");
                txtRed.setEditable(valor);
                vboxRedesDinamico.setPadding(new Insets(5, 40, 15, 0));

                vboxRedesDinamico.getChildren().add(txtRed);
            }
            for (Direccion direccion : p1.getDirecciones()) {
                TextField txtDirecion1 = new TextField(direccion.getUbicacion());
                txtDirecion1.setPrefHeight(27.0);
                txtDirecion1.setPrefWidth(322.0);
                txtDirecion1.setStyle("-fx-border-color: #7F65FF; -fx-border-radius: 6;");

                txtDirecion1.setEditable(valor);
                vboxDireccionesDinamico.setPadding(new Insets(5, 40, 15, 0));
                vboxDireccionesDinamico.getChildren().add(txtDirecion1);

            }
            for (Fecha fecha : p1.getFechas()) {
                TextField txtfecha = new TextField(fecha.getFecha().toString());
                txtfecha.setPrefWidth(322.0);
                txtfecha.setPrefHeight(27.0);
                txtfecha.setStyle("-fx-border-color: #7F65FF; -fx-border-radius: 6;");
                txtfecha.setEditable(valor);
                vboxFechasDinamico.setPadding(new Insets(5, 40, 15, 0));
                vboxFechasDinamico.getChildren().add(txtfecha);
            }
            txtNacionalidad.setText(p1.getNacionalidad());
            txtNacionalidad.setEditable(valor);
            txtOcupación.setText(p1.getOcupacion());
            txtOcupación.setEditable(valor);

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
}
