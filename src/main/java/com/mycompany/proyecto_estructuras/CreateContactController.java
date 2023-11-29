package com.mycompany.proyecto_estructuras;

import Direcciones.DireccionCb;
import Fechas.FechaCb;
import Logica.Archivos;
import Logica.Direccion;
import Logica.DoubleLinkedList;
import Logica.Fecha;
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
    private void handleBtnFotoClick(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Selecciona una imagen");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Imagenes", "*.png", "*.jpg", "*.jpeg"));

        File selectedFile = fileChooser.showOpenDialog(stage);
        if (selectedFile != null) {
            // Asumiendo que "Img" es una carpeta en el mismo directorio que el directorio de salida del proyecto.
            Path destPath = Paths.get("Img" + File.separator + selectedFile.getName());

            // Verificar si el archivo ya existe en el directorio
            if (Files.exists(destPath)) {
                // El archivo ya existe, puedes mostrar un mensaje o realizar alguna acción.
                System.out.println("La imagen ya existe en el directorio.");
            } else {
                // El archivo no existe, copiarlo y agregarlo a la lista
                Files.copy(selectedFile.toPath(), destPath, StandardCopyOption.REPLACE_EXISTING);

                // Reflejar la imagen en la UI
                String rutaImagen = destPath.toUri().toString();
                Image image = new Image(rutaImagen);
                ImgFotoPersona.setImage(image);
                fotos.add(rutaImagen);
                fotos.esCircular();
                System.out.println("se agregó");
                if (iterator.hasNext()) {
                    iterator.next();
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
            DoubleLinkedList<Direccion> lldirecciones = new DoubleLinkedList();
            DoubleLinkedList<String> llemails = new DoubleLinkedList();
            DoubleLinkedList<RedSocial> llredes = new DoubleLinkedList();
            DoubleLinkedList<String> llfotos = new DoubleLinkedList();
            DoubleLinkedList<Fecha> llfechas = new DoubleLinkedList();
            DoubleLinkedList<Telefono> lltelefonos = new DoubleLinkedList();
            llfotos.addAll(fotos);
            //añadir telefonos
            String[] telefonosArray = telefonos.split("\\|");
            String[] emailsArray = emails.split("\\|");
            String[] redesSocialesArray = redesSociales.split("\\|");
            String[] direccionesArray = direcciones.split("\\|");
            String[] fechasRelevantesArray = fechasRelevantes.split("\\,");
            DateTimeFormatter formateo = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate cumpleaños = null;
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
                String[] red = algo.split(" ");
                RedSocial rs = new RedSocial(red[0], red[1]);
                llredes.add(rs);
            }
            for (String algo : direccionesArray) {
                String[] direccion = algo.split(" ");
                Direccion d = new Direccion(direccion[0], direccion[1]);
                lldirecciones.add(d);
            }
            for (String algo : fechasRelevantesArray) {
                String[] fecha = algo.split(" ");
                if (fecha[0].equals("Cumpleaños")) {
                    LocalDate fechaComoLocalDate = LocalDate.parse(fecha[1], formateo);
                    cumpleaños = fechaComoLocalDate;
                }
                LocalDate localD = LocalDate.parse(fecha[1], formateo);
                Fecha f = new Fecha(fecha[0], localD);

                llfechas.add(f);

            }

//            Persona(String apellido , String genero, String fechaNacimiento
//            , String ocupacion, String Nacionalidad
//            , String nombre, DoubleLinkedList<String[]> direcciones
//            , DoubleLinkedList<String> emails, DoubleLinkedList<String> redes
//            , DoubleLinkedList<String> fotos, DoubleLinkedList<String[]> fechas
//            , DoubleLinkedList<String[]> telefonos
//            ) 
            Persona contacto = new Persona(apellidos, genero, cumpleaños, ocupacion, Nacionalidad, nombres, lldirecciones, llemails, llredes, llfotos, llfechas, lltelefonos);
            System.out.println("Contacto: " + contacto);
            App.usuario.getContactos().add(contacto);
            for (Usuario usuario : App.listaUsuarios) {
                if (usuario.equals(App.usuario)) {
                    usuario.getContactos().add(contacto);
                    break;
                }
            }
            Archivos.serializarListaUsuarios(App.listaUsuarios, "usuarios.ser");

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
            } 
        } else {
            System.out.println("bug 2");
        }
    }

    private void mostrarImagenActual(String rutaImagen) {
        Image image = new Image(rutaImagen);
        ImgFotoPersona.setImage(image);
    }

}
