package com.mycompany.proyecto_estructuras;

import Direcciones.Direccion;
import Fechas.Fecha;
import Logica.DoubleLinkedList;
import Logica.Persona;
import Logica.Telefono;
import Prefijos.PrefijoPais;
import Social_Media.RedesSociales;
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
    private ToggleGroup generos;
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
    private ComboBox<RedesSociales> comboBoxRedes;
    @FXML
    private ComboBox<Direccion> comboDirections;
    @FXML
    private ComboBox<Fecha> comboFechas;

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
    private void handleComboBoxPersona(Event event) {
        PrefijoPais.configurarComboBoxConPrefijos(comboPrefijos2);
    }

    @FXML
    private void handleComboBoxSocialMedia(Event event) {
        RedesSociales.configurarComboBoxConRedes(comboBoxRedes);
    }

    @FXML
    private void handleComboBoxDirections(Event event) {
        Direccion.configurarComboBoxConDirecciones(comboDirections);
    }

    @FXML
    private void handleComboBoxDates(Event event) {
        Fecha.configurarComboBoxConFechas(comboFechas);
    }

    @FXML
    private void handleButtonAction(ActionEvent event) {
        Button sourceButton = (Button) event.getSource();

        VBox vbox = (VBox) ((HBox) sourceButton.getParent().getParent()).getChildren().get(0);
        agregarEliminar(sourceButton, vbox);
        if (vbox == cajaTelefonos ) {
            agregarTextField(vbox,"telefonos");
        } else if (vbox == cajaEmails) {
            agregarTextFieldEnHBox(vbox);
        } else if (vbox == cajaDirecciones) {
            agregarTextField(vbox,"direcciones");
        } else if (vbox == cajaFechas) {
            agregarTextFieldYDatePickerEnHBox(vbox);
        } else if (vbox == cajaRedes){
            agregarTextField(vbox,"redes");
        }

    }

    private void agregarTextField(VBox parentVBox, String tipo) {
        HBox hBox = new HBox();
        hBox.setSpacing(20);
        ComboBox<PrefijoPais> comboBox1 = new ComboBox<PrefijoPais>();
        ComboBox<RedesSociales> comboBox2 = new ComboBox<RedesSociales>();
        ComboBox<Direccion> comboBox3 = new ComboBox<Direccion>();

        ComboBox<Fecha> comboBox4 = new ComboBox<Fecha>();
        comboBox1.setMinWidth(150);
        comboBox2.setMinWidth(150);
        comboBox3.setMinWidth(150);
        comboBox4.setMinWidth(150);

        if (tipo.equalsIgnoreCase("telefonos")) {

            PrefijoPais.configurarComboBoxConPrefijos(comboBox1);
            hBox.getChildren().add(comboBox1);

        } else if (tipo.equalsIgnoreCase("redes")) {
            RedesSociales.configurarComboBoxConRedes(comboBox2);
            hBox.getChildren().add(comboBox2);
            
        } else if (tipo.equalsIgnoreCase("direcciones")) {
            Direccion.configurarComboBoxConDirecciones(comboBox3);
            hBox.getChildren().add(comboBox3);

        } 

        TextField textField = new TextField();
        hBox.getChildren().add(textField);
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
        ComboBox<Fecha> cb = new ComboBox();
        Fecha.configurarComboBoxConFechas(cb);
        DatePicker datePicker = new DatePicker();
        hBox.getChildren().addAll(cb, datePicker);
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
            String genero = "";
            if (generos.getSelectedToggle() != null) {
                RadioButton select = (RadioButton) generos.getSelectedToggle();

                genero = select.getText();
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
            DoubleLinkedList<Telefono> lltelefonos = new DoubleLinkedList();
            //añadir telefonos
            String[] telefonosArray = telefonos.split("\\|");
            String[] emailsArray = emails.split("\\|");
            String[] redesSocialesArray = redesSociales.split("\\|");
            String[] direccionesArray = direcciones.split("\\|");
            String[] fechasRelevantesArray = fechasRelevantes.split("\\,");
            for (String algo : telefonosArray) {
                if (!algo.isEmpty()) {
                    String[] telefonoArray = algo.split("[()]");
                    Telefono telefono = new Telefono(telefonoArray[0], telefonoArray[1], telefonoArray[2]);
                    lltelefonos.add(telefono);
                }
            }
            for (String algo : emailsArray) {
                llemails.add(algo);
            }
            for (String algo : redesSocialesArray) {
                llredes.add(algo);
            }
            for (String algo : direccionesArray) {
                String[] direccion = algo.split(" ");
                lldirecciones.add(direccion);
            }
            for (String algo : fechasRelevantesArray) {
                String[] fecha = algo.split("\\|");
                llfechas.add(fecha);
            }

//            Persona(String apellido , String genero, String fechaNacimiento
//            , String ocupacion, String Nacionalidad
//            , String nombre, DoubleLinkedList<String[]> direcciones
//            , DoubleLinkedList<String> emails, DoubleLinkedList<String> redes
//            , DoubleLinkedList<String> fotos, DoubleLinkedList<String[]> fechas
//            , DoubleLinkedList<String[]> telefonos
//            ) 
            Persona contacto = new Persona(apellidos, genero, fechasRelevantes, ocupacion, Nacionalidad, nombres, lldirecciones, llemails, llredes, llfotos, llfechas, lltelefonos);
            System.out.println("Contacto: " + contacto);
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
                        ComboBox<?> comboBox = (ComboBox<?>) child;
                        Object valor = comboBox.getValue();
                        valores.append(valor).append(" ");
                    } else if (child instanceof TextField) {
                        TextField textField = (TextField) child;
                        valores.append(textField.getText()).append("|");
                    } else if (child instanceof DatePicker) {
                        DatePicker datePicker = (DatePicker) child;
                        valores.append(datePicker.getValue()).append(",");
                    }
                }
            } else if (node instanceof TextField) {
                TextField textField = (TextField) node;
                valores.append(textField.getText()).append("|");
            }
        }
        return valores.toString().trim();
    }

    private void agregarEliminar(Button botonOrigen, VBox padre) {

        // Obtener el nodo padre del botón
        VBox nodoPadre = (VBox) botonOrigen.getParent();
        nodoPadre.setSpacing(5);
        if (nodoPadre.getChildren().size() < 2) {
            ImageView imgv = new ImageView();
            imgv.setFitWidth(15); // ajusta el ancho de la imagen
            imgv.setFitHeight(15); // ajusta la altura de la imagen
            Image img = new Image("file:src/main/resources/Imagenes/close.png");
            imgv.setImage(img);
            // Agregar un nuevo botón al nodo padre
            Button nuevoBoton = new Button("", imgv);
            nuevoBoton.setStyle("-fx-background-color: white;");
            nuevoBoton.setOnAction(e -> {
                if (padre.getChildren().size() >= 1) {
                    padre.getChildren().remove(padre.getChildren().size() - 1);
                    if (padre.getChildren().size() == 1) {
                        nodoPadre.getChildren().remove(nodoPadre.getChildren().size() - 1);
                    }
                }
            });

            nodoPadre.getChildren().add(nuevoBoton);
        }
    }
}
