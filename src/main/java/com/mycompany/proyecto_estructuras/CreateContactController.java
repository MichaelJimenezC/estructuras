package com.mycompany.proyecto_estructuras;

import Logica.Archivos;
import Logica.DoubleLinkedList;
import Logica.Persona;
import Logica.Usuario;
import Prefijos.PrefijoPais;
import java.io.File;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.Event;
import javafx.scene.Node;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class CreateContactController implements Initializable {

    @FXML
    private ComboBox cbTipo;
    @FXML
    private ComboBox<PrefijoPais> comboPrefijos2;
    @FXML
    private TextField txtNombres;
    @FXML
    private TextField txtApellidos;

    @FXML
    private VBox cajaTelefonos;
    @FXML
    private VBox cajaEmails;
    @FXML
    private VBox cajaRedes;
    @FXML
    private VBox cajaDirecciones;
    @FXML
    private VBox cajaFechas;
    @FXML
    private ComboBox<String> cbxGenero;
    @FXML
    private TextField txtNacionalidad;
    @FXML
    private TextField txtOcupación;
    @FXML
    private Button buttonGuardar;
    @FXML
    private Button btnFoto;
    @FXML
    private ImageView ImgFotoPersona;

    private boolean comboBoxLoaded = false;

    @FXML
    private void cambiarTipo(ActionEvent event) {
        String opcionSeleccionada = (String) cbTipo.getValue();

        if (opcionSeleccionada != null) {
            if (opcionSeleccionada.equals("Empresa")) {
                try {
                    App.setRoot("CreateContactPage2");
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
    @FXML
    private ToggleGroup generos;

    @FXML
    private void handleComboBoxPersona(Event event) {
        PrimaryController.configurarComboBoxConPrefijos(comboPrefijos2);
    }

    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("boton");
        Button sourceButton = (Button) event.getSource();
        System.out.println(sourceButton.getParent().getParent());
        VBox vbox = (VBox) ((HBox) sourceButton.getParent().getParent()).getChildren().get(0);

        if (vbox == cajaTelefonos) {
            agregarTextField(vbox);
        } else if (vbox == cajaEmails || vbox == cajaRedes) {
            agregarTextFieldEnHBox(vbox);
        } else if (vbox == cajaDirecciones) {
            agregarComboBoxYTextFieldEnHBox(vbox);
        } else if (vbox == cajaFechas) {
            agregarTextFieldYDatePickerEnHBox(vbox);
        } else {
            System.out.println("wtf");
        }

    }

    private void agregarTextField(VBox parentVBox) {
        HBox hBox = new HBox();
        hBox.setSpacing(10);
        ComboBox<String> comboBox = new ComboBox<>();
        comboBox.setPrefWidth(150);
        TextField textField = new TextField();
        hBox.getChildren().addAll(comboBox, textField);
        parentVBox.getChildren().add(hBox);
    }

    @FXML
    private void handleBtnFotoClick(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow(); // Obtiene la ventana actual

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Selecciona una imagen");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Imagenes", "*.png", "*.jpg", "*.jpeg"));

        File selectedFile = fileChooser.showOpenDialog(stage);
        if (selectedFile != null) {
            // Asumiendo que "Img" es una carpeta en el mismo directorio que el directorio de salida del proyecto.
            Path destPath = Paths.get("Img" + File.separator + selectedFile.getName());
            Files.copy(selectedFile.toPath(), destPath, StandardCopyOption.REPLACE_EXISTING);
            // Si deseas reflejar la imagen en la UI:
            Image image = new Image(destPath.toUri().toString());
            ImgFotoPersona.setImage(image); // Asegúrate de tener un ImageView en tu FXML para mostrar la imagen.
        }
    }

    private void agregarTextFieldEnHBox(VBox parentVBox) {
        TextField textField = new TextField();
        parentVBox.getChildren().add(textField);
    }

    private void agregarComboBoxYTextFieldEnHBox(VBox parentVBox) {
        HBox hBox = new HBox();
        hBox.setSpacing(10);
        ComboBox<String> comboBox = new ComboBox<>();
        comboBox.setPrefWidth(150);
        TextField textField = new TextField();
        hBox.getChildren().addAll(comboBox, textField);
        parentVBox.getChildren().add(hBox);
    }

    private void agregarTextFieldYDatePickerEnHBox(VBox parentVBox) {
        HBox hBox = new HBox();
        hBox.setSpacing(10);
        TextField textField = new TextField();
        DatePicker datePicker = new DatePicker();
        hBox.getChildren().addAll(textField, datePicker);
        parentVBox.getChildren().add(hBox);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cbTipo.getItems().addAll("Persona", "Empresa");
        cbTipo.setValue("Persona");
    }

    @FXML
    private void handleRadioButtonAction() {
        if (generos.getSelectedToggle() != null) {
            RadioButton selectedRadioButton = (RadioButton) generos.getSelectedToggle();
            System.out.println("Selected Radio Button: " + selectedRadioButton.getText());
        }
    }

    @FXML
    private void guardarContacto(ActionEvent event) {
        String telefonos = obtenerValores(cajaTelefonos);
        if (!txtNombres.getText().isEmpty() && !telefonos.isEmpty()) {
            String nombres = txtNombres.getText();
            String apellidos = txtApellidos.getText();
            String genero="";
            if (cbxGenero != null) {
                genero = (String) cbxGenero.getValue();
            }
            String ocupacion = txtOcupación.getText();
            String Nacionalidad = txtNacionalidad.getText();
            // Recuperar valores dinámicos de los componentes
            String emails = obtenerValores(cajaEmails);
            String redesSociales = obtenerValores(cajaRedes);
            String direcciones = obtenerValores(cajaDirecciones);
            String fechasRelevantes = obtenerValores(cajaFechas);
            System.out.println("telefonos: " + telefonos);
            System.out.println("emails: " + emails);
            System.out.println("redesSociales: " + redesSociales);
            System.out.println("direcciones: " + direcciones);
            System.out.println("fechasRelevantes: " + fechasRelevantes);
            DoubleLinkedList<String[]> lldirecciones = new DoubleLinkedList();
            DoubleLinkedList<String> llemails = new DoubleLinkedList();
            DoubleLinkedList<String> llredes = new DoubleLinkedList();
            DoubleLinkedList<String> llfotos = new DoubleLinkedList();
            DoubleLinkedList<String[]> llfechas = new DoubleLinkedList();
            DoubleLinkedList<String[]> lltelefonos = new DoubleLinkedList();
//            Persona(String apellido , String genero, String fechaNacimiento
//            , String ocupacion, String Nacionalidad
//            , String nombre, DoubleLinkedList<String[]> direcciones
//            , DoubleLinkedList<String> emails, DoubleLinkedList<String> redes
//            , DoubleLinkedList<String> fotos, DoubleLinkedList<String[]> fechas
//            , DoubleLinkedList<String[]> telefonos
//            ) 
//            Persona contacto = new Persona(apellidos, genero, fechasRelevantes, ocupacion, Nacionalidad, nombres, lldirecciones, llemails, llredes, llfotos, llfechas, lltelefonos);
        }
    }

// Método auxiliar para obtener valores de VBox dinámicos
    private String obtenerValores(VBox parentVBox) {
        StringBuilder valores = new StringBuilder();
        for (Node node : parentVBox.getChildren()) {
            if (node instanceof HBox) {
                HBox hBox = (HBox) node;
                for (Node child : hBox.getChildren()) {
                    if (child instanceof ComboBox) {
                        ComboBox<String> comboBox = (ComboBox<String>) child;
                        valores.append(comboBox.getValue()).append(" ");
                    } else if (child instanceof TextField) {
                        TextField textField = (TextField) child;
                        valores.append(textField.getText()).append(" ");
                    } else if (child instanceof DatePicker) {
                        DatePicker datePicker = (DatePicker) child;
                        valores.append(datePicker.getValue()).append(" ");
                    }
                }
            } else if (node instanceof TextField) {
                TextField textField = (TextField) node;
                valores.append(textField.getText()).append(" ");
            }
        }
        return valores.toString().trim();
    }

}
