package com.mycompany.proyecto_estructuras;

import Direcciones.DireccionCb;
import Fechas.FechaCb;
import Logica.Archivos;
import Logica.Direccion;
import Logica.DoubleLinkedList;
import Logica.Fecha;
import Logica.LinkedListPropia;
import Logica.Persona;
import Logica.RedSocial;
import Logica.Telefono;
import Logica.Usuario;
import Prefijos.PrefijoPais;
import Social_Media.RedesSociales;
import static com.mycompany.proyecto_estructuras.App.listaUsuarios;
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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ListIterator;
import java.util.ResourceBundle;
import javafx.event.Event;
import javafx.scene.Node;
import javafx.scene.control.Alert;
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
    private ComboBox<DireccionCb> comboDirecciones;
    @FXML
    private ComboBox<FechaCb> comboFechas;
    private DoubleLinkedList<String> fotos = new DoubleLinkedList<>();
    private ListIterator<String> iterator = fotos.listIterator();
    @FXML
    private TextField txtTelefono;

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
        DireccionCb.configurarComboBoxConDirecciones(comboDirecciones);
    }

    @FXML
    private void handleComboBoxDates(Event event) {
        FechaCb.configurarComboBoxConFechas(comboFechas);
    }

    @FXML
    private void handleButtonAction(ActionEvent event) {
        Button sourceButton = (Button) event.getSource();

        VBox vbox = (VBox) ((HBox) sourceButton.getParent().getParent()).getChildren().get(0);
        agregarEliminar(sourceButton, vbox);
        if (vbox == cajaTelefonos) {
            agregarTextField(vbox, "telefonos");
        } else if (vbox == cajaEmails) {
            agregarTextFieldEnHBox(vbox);
        } else if (vbox == cajaDirecciones) {
            agregarTextField(vbox, "direcciones");
        } else if (vbox == cajaFechas) {
            agregarTextFieldYDatePickerEnHBox(vbox);
        } else if (vbox == cajaRedes) {
            agregarTextField(vbox, "redes");
        }

    }

    private void agregarTextField(VBox parentVBox, String tipo) {
        HBox hBox = new HBox();
        hBox.setSpacing(20);
        ComboBox<PrefijoPais> comboBox1 = new ComboBox<PrefijoPais>();
        ComboBox<RedesSociales> comboBox2 = new ComboBox<RedesSociales>();
        ComboBox<DireccionCb> comboBox3 = new ComboBox<DireccionCb>();

        ComboBox<FechaCb> comboBox4 = new ComboBox<FechaCb>();
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
            DireccionCb.configurarComboBoxConDirecciones(comboBox3);
            hBox.getChildren().add(comboBox3);

        }

        TextField textField = new TextField();
        hBox.getChildren().add(textField);
        parentVBox.getChildren().add(hBox);
    }

    @FXML
private void handleBtnFotoClick(ActionEvent event) {
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

    FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Selecciona una imagen");
    fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Imagenes", "*.png", "*.jpg", "*.jpeg"));

    File selectedFile = fileChooser.showOpenDialog(stage);
    if (selectedFile != null) {
        // Definir la ruta de destino en la carpeta "Img" dentro del directorio del proyecto
        Path destPath = Paths.get("Img", selectedFile.getName());

        // Verificar si el archivo ya existe en el directorio
        if (Files.exists(destPath)) {
            System.out.println("La imagen ya existe en el directorio.");
        } else {
            try {
                // Copiar el archivo seleccionado a la carpeta "Img"
                Files.copy(selectedFile.toPath(), destPath, StandardCopyOption.REPLACE_EXISTING);

                // Obtener la ruta relativa para cargar la imagen en el ImageView
                String rutaImagen = destPath.toString().replace("\\", "/");
                System.out.println(rutaImagen);
                Image image = new Image("file:" + rutaImagen);
                ImgFotoPersona.setImage(image);
                System.out.println(rutaImagen);
                // Agregar la ruta relativa a la lista de fotos si es necesario
                fotos.add(rutaImagen);

                System.out.println("La imagen se agregó correctamente.");

                if (iterator.hasNext()) {
                    iterator.next();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
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
        ComboBox<FechaCb> cb = new ComboBox();
        FechaCb.configurarComboBoxConFechas(cb);
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

        if (!txtNombres.getText().isEmpty() && comboPrefijos2.getValue() != null && !txtTelefono.getText().isEmpty()&&fotos.size()>=2) {
            String nombres = txtNombres.getText();
            String apellidos = txtApellidos.getText();
            String genero = "";
            if (generos.getSelectedToggle() != null) {
                RadioButton select = (RadioButton) generos.getSelectedToggle();

                genero = select.getText();
            }
            String ocupacion = txtOcupación.getText();
            String telefonos = obtenerValores(cajaTelefonos);
            String nacionalidad = txtNacionalidad.getText();
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
            LinkedListPropia<Direccion> lldirecciones = new LinkedListPropia();
            LinkedListPropia<String> llemails = new LinkedListPropia();
            LinkedListPropia<RedSocial> llredes = new LinkedListPropia();
            LinkedListPropia<Fecha> llfechas = new LinkedListPropia();
            LinkedListPropia<Telefono> lltelefonos = new LinkedListPropia();
            //añadir telefonos
            String[] telefonosArray = telefonos.split("\\|");
            String[] emailsArray = emails.split("\\|");
            System.out.println("emails: " + emailsArray.length);
            String[] redesSocialesArray = redesSociales.split("\\|");
            System.out.println("redes: " + redesSocialesArray.length);

            String[] direccionesArray = direcciones.split("\\|");
            System.out.println("direcciones: " + direccionesArray.length);

            String[] fechasRelevantesArray = fechasRelevantes.split("\\,");
            System.out.println("fechas: " + fechasRelevantesArray.length);

            DateTimeFormatter formateo = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate cumpleaños = null;
            for (String algo : telefonosArray) {
                if (!algo.isEmpty()) {
                    String[] telefonoArray = algo.split("[()]");
                    Telefono telefono = new Telefono(telefonoArray[0], telefonoArray[1], telefonoArray[2]);
                    lltelefonos.add(telefono);
                }
            }
            if (emailsArray.length > 0) {
                for (String algo : emailsArray) {
                    llemails.add(algo);
                }
            }
            if (!redesSocialesArray[0].trim().equals("null")) {
                for (String algo : redesSocialesArray) {
                    String[] red = algo.split(" ");
                    if (red.length > 1) {
                        RedSocial rs = new RedSocial(red[0], red[1]);
                        llredes.add(rs);
                    }
                }
            }
            if (!direccionesArray[0].trim().equals("null")) {
                for (String algo : direccionesArray) {
                    System.out.println("Direccion bug: "+algo);
                    String[] direccion = algo.split(" ");
                    String direccionGuardar=algo.replace(direccion[0],"");
                    System.out.println(direccionGuardar);
                    if (direccion.length > 1) {
                        
                        Direccion d = new Direccion(direccion[0],direccionGuardar);
                        lldirecciones.add(d);
                    }
                }
            }
            System.out.println(fechasRelevantesArray[0].trim());
            if (!fechasRelevantesArray[0].trim().equals("null null")) {
                for (String algo : fechasRelevantesArray) {
                    String[] fecha = algo.split(" ");
                    if (!fecha[0].equals("null") && !fecha[1].equals("null")) {
                        if (fecha[0].equals("Cumpleaños")) {
                            LocalDate fechaComoLocalDate = LocalDate.parse(fecha[1], formateo);
                            cumpleaños = fechaComoLocalDate;
                        }
                        LocalDate localD = LocalDate.parse(fecha[1], formateo);
                        Fecha f = new Fecha(fecha[0], localD);

                        llfechas.add(f);
                    }

                }
            }

//            Persona(String apellido , String genero, String fechaNacimiento
//            , String ocupacion, String Nacionalidad
//            , String nombre, DoubleLinkedList<String[]> direcciones
//            , DoubleLinkedList<String> emails, DoubleLinkedList<String> redes
//            , DoubleLinkedList<String> fotos, DoubleLinkedList<String[]> fechas
//            , DoubleLinkedList<String[]> telefonos
//            ) 

            Persona contacto = new Persona(apellidos, genero, cumpleaños, ocupacion, nombres, lldirecciones, llemails, llredes, fotos, llfechas, lltelefonos,nacionalidad);
            System.out.println("Contacto: " + contacto);
            
            for (Usuario usuario : App.listaUsuarios) {
                System.out.println(usuario);
                if (usuario.equals(App.usuario)) {
                    usuario.getContactos().add(contacto);
                    break;
                }
            }
            Archivos.serializarListaUsuarios(App.listaUsuarios, "usuarios.ser");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Confirmación");
            alert.setHeaderText(null);
            alert.setContentText("!Se ha creado con éxito el contacto!");
            alert.showAndWait();
            try {
                App.setRoot("ContactosPage");
            } catch (IOException ex) {
                System.out.println("Estamos dentro del cambio de escena");
            }
            System.out.println("si llegue");

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

    @FXML
    private void handleBtnSiguienteClick(ActionEvent event) {
        if (!fotos.isEmpty()) {
            if (iterator.hasNext()) {
                System.out.println("pa delante");
                String siguienteFoto = iterator.next();
                mostrarImagenActual(siguienteFoto);
            } else {
                // Volvemos al principio de la lista
                iterator = fotos.listIterator();
                if (iterator.hasNext()) {
                    String siguienteFoto = iterator.next();
                    mostrarImagenActual(siguienteFoto);
                }
            }
        } else {
            System.out.println("bug");
        }
    }

    @FXML
    private void handleBtnAnteriorClick(ActionEvent event) {
        if (!fotos.isEmpty()) {
            System.out.println("para atras");
            if (iterator.hasPrevious()) {
                String anteriorFoto = iterator.previous();
                mostrarImagenActual(anteriorFoto);
            } else {
                // Vamos al final de la lista
                while (iterator.hasNext()) {
                    iterator.next();
                }
                if (iterator.hasPrevious()) {
                    String anteriorFoto = iterator.previous();
                    mostrarImagenActual(anteriorFoto);
                }
            }
        } else {
            System.out.println("bug 2");
        }
    }

    private void mostrarImagenActual(String rutaImagen) {
        Image image = new Image("file:" +rutaImagen);
        ImgFotoPersona.setImage(image);
    }

}
