/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.proyecto_estructuras;

import Direcciones.DireccionCb;
import Fechas.FechaCb;
import Logica.Archivos;
import Logica.Contacto;
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
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import static com.mycompany.proyecto_estructuras.ContactosPageController.contactoSelecionado;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ListIterator;
import java.util.Optional;
import javafx.event.Event;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
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
public class EditarContactoPersonaController implements Initializable {

    @FXML
    private TextField txtApellidosNuevos;
    @FXML
    private TextField txtNombresNuevos;
    @FXML
    private TextField txtNacionalidad;
    @FXML
    private TextField txtOcupación;
    @FXML
    private TextField txtTelefono;
    @FXML
    private ComboBox<PrefijoPais> comboPrefijos2;
    @FXML
    private TextField txtEmails;
    @FXML
    private ComboBox<RedesSociales> comboBoxRedes;
    @FXML
    private ComboBox<DireccionCb> comboDirecciones;
    @FXML
    private ComboBox<FechaCb> comboFechas;
    @FXML
    private DatePicker dateFecha;
    @FXML
    private TextField txtRedes;
    @FXML
    private TextField txtDirecciones;
    @FXML
    private ImageView ImgFotoPersona;
    private DoubleLinkedList<String> fotos = contactoSelecionado.getFotos();
    private ListIterator<String> iterator = fotos.listIterator();
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
    private Button buttonGuardar;
    @FXML
    private Button btnFoto;
    @FXML
    private RadioButton rMasculino;
    @FXML
    private RadioButton rFemenino;
    @FXML
    private ComboBox<Contacto> comboRelacionado;
    @FXML
    private ComboBox<Contacto> comboYaRelacionado;

    @Override

    public void initialize(URL url, ResourceBundle rb) {
        ImgFotoPersona.setImage(new Image("file:" + fotos.get(0)));
        iterator.next();
        if (!App.usuario.getContactos().isEmpty()) {
            comboRelacionado.getItems().addAll(App.usuario.getContactos());

        }
        handleComboBoxPersona();
        handleComboBoxSocialMedia();
        handleComboBoxDirections();
        handleComboBoxDates();
        llenarDatos();
    }

    @FXML
    public void EliminarContacto(ActionEvent event) {
        // Encuentra el contacto seleccionado
        for (Contacto c : App.usuario.getContactos()) {
            if (c.equals(contactoSelecionado)) {
                // Crea un alerta para confirmación
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmación");
                alert.setHeaderText(null);
                alert.setContentText("¿Está seguro de eliminar el contacto?");

                // Muestra el alerta y espera la respuesta del usuario
                Optional<ButtonType> result = alert.showAndWait();
                // Si el usuario confirma la eliminación
                if (result.isPresent() && result.get() == ButtonType.OK) {
                    // Elimina el contacto y rompe el bucle
                    App.usuario.getContactos().remove(contactoSelecionado);

                    // Guarda la lista de usuarios actualizada
                    Archivos.serializarListaUsuarios(App.listaUsuarios, "usuarios.ser");

                    // Cambia la vista
                    try {
                        App.setRoot("ContactosPage");
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    break;
                }
            }
        }
        // Si se presiona "Cancelar", el código no hará nada y el usuario permanecerá en la misma escena.
    }

    @FXML
    public void handleBotonRegresar(ActionEvent event) {
        // Crea un alerta para confirmación
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmación de regreso");
        alert.setHeaderText(null);
        alert.setContentText("¿Está seguro de que desea regresar? Los cambios no guardados se perderán.");

        // Muestra el alerta y espera la respuesta del usuario
        Optional<ButtonType> result = alert.showAndWait();
        // Si el usuario confirma que quiere regresar
        if (result.isPresent() && result.get() == ButtonType.OK) {
            // Cambia a la pantalla "MenuPersona"
            try {
                App.setRoot("MenuPersona");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        // Si el usuario elige "Cancelar", permanecerá en la pantalla actual
    }

    public void llenarDatos() {

        if (contactoSelecionado instanceof Persona) {
            Persona personallenar = (Persona) contactoSelecionado;
            if (!personallenar.getContactosRelacionados().isEmpty()) {
                comboRelacionado.getItems().addAll(personallenar.getContactosRelacionados());

            }
            txtNombresNuevos.setText(personallenar.getNombre()); //obligatorio
            if (!personallenar.getGenero().isEmpty()) {
                if (personallenar.getGenero().equals("Femenino")) {
                    rFemenino.setSelected(true);
                } else {
                    rMasculino.setSelected(true);
                }
            }
            if (!personallenar.getApellido().isEmpty()) {
                txtApellidosNuevos.setText(personallenar.getApellido());
            }
            txtTelefono.setText(personallenar.getTelefonos().get(0).getNumero()); //obligatorio
            System.out.println(comboPrefijos2.getItems().size());
            PrefijoPais prefijo = buscarPrefijoPaisPorPrefijo(personallenar.getTelefonos().get(0).getPrefijo(), comboPrefijos2.getItems());

            comboPrefijos2.setValue(prefijo);
            for (Telefono telefono : personallenar.getTelefonos()) {
                int index = personallenar.getTelefonos().indexOf(telefono);
                if (index >= 1) {
                    HBox hboxNuevosTelefonos = new HBox();
                    ComboBox<PrefijoPais> comboxNuevosTelefonos = new ComboBox<>();
                    PrefijoPais.configurarComboBoxConPrefijos(comboxNuevosTelefonos);
                    // Encuentra el PrefijoPais que coincide con el prefijo del teléfono
                    PrefijoPais prefijoSeleccionado = buscarPrefijoPaisPorPrefijo(telefono.getPrefijo(), comboxNuevosTelefonos.getItems());
                    comboxNuevosTelefonos.setValue(prefijoSeleccionado);
                    TextField txttelefonos = new TextField();
                    txttelefonos.setText(telefono.getNumero());
                    // Agrega el ComboBox y otros elementos necesarios al HBox
                    hboxNuevosTelefonos.getChildren().addAll(comboxNuevosTelefonos, txttelefonos);
                    cajaTelefonos.getChildren().add(hboxNuevosTelefonos);
                }
            }
            if (!personallenar.getEmails().isEmpty()) {
                txtEmails.setText(personallenar.getEmails().get(0));

                for (String email : personallenar.getEmails()) {
                    int index = personallenar.getEmails().indexOf(email);
                    if (index >= 1) {
                        HBox hboxEmails = new HBox();

                        TextField txtEmails = new TextField();
                        txtEmails.setText(email);
                        txtEmails.setPrefWidth(305);
                        // Agrega el ComboBox y otros elementos necesarios al HBox
                        hboxEmails.getChildren().add(txtEmails);
                        cajaEmails.getChildren().add(hboxEmails);
                    }

                }

            }
            if (!personallenar.getRedes().isEmpty()) {
                txtRedes.setText(personallenar.getRedes().get(0).getUsuario());
                RedesSociales red = buscarRedSocialPorNombre(personallenar.getRedes().get(0).getRed(), comboBoxRedes.getItems());
                comboBoxRedes.setValue(red);
                for (RedSocial redesocial : personallenar.getRedes()) {
                    int index = personallenar.getRedes().indexOf(redesocial);
                    if (index >= 1) {
                        HBox hboxNuevasRedes = new HBox();
                        ComboBox<RedesSociales> comboxNuevasRedes = new ComboBox<>();
                        RedesSociales.configurarComboBoxConRedes(comboxNuevasRedes);
                        RedesSociales redSocialSeleccionada = buscarRedSocialPorNombre(redesocial.getRed(), comboxNuevasRedes.getItems());
                        comboxNuevasRedes.setValue(redSocialSeleccionada);
                        TextField txtredes = new TextField();
                        txtredes.setText(redesocial.getUsuario());
                        // Agrega el ComboBox y otros elementos necesarios al HBox
                        hboxNuevasRedes.getChildren().addAll(comboxNuevasRedes, txtredes);
                        // ... agregar otros elementos al HBox según sea necesario ...

                        hboxNuevasRedes.getChildren().addAll();
                        cajaRedes.getChildren().addAll(hboxNuevasRedes);
                    }
                }
            }
            if (!personallenar.getDirecciones().isEmpty()) {
                txtDirecciones.setText(personallenar.getDirecciones().get(0).getUbicacion());
                DireccionCb d = buscarDireccionPorTipo(personallenar.getDirecciones().get(0).getTipo(), comboDirecciones.getItems());
                comboDirecciones.setValue(d);
                for (Direccion direccion : personallenar.getDirecciones()) {
                    int index = personallenar.getDirecciones().indexOf(direccion);
                    if (index >= 1) {
                        HBox hboxNuevadDirecciones = new HBox();
                        ComboBox<DireccionCb> comboxNuevasDirecciones = new ComboBox<>();
                        DireccionCb.configurarComboBoxConDirecciones(comboxNuevasDirecciones);
                        DireccionCb direccionSeleccionada = buscarDireccionPorTipo(direccion.getTipo(), comboxNuevasDirecciones.getItems());
                        comboxNuevasDirecciones.setValue(direccionSeleccionada);
                        TextField txtdirecciones = new TextField();
                        txtdirecciones.setText(direccion.getUbicacion());
                        // Agrega el ComboBox y otros elementos necesarios al HBox
                        hboxNuevadDirecciones.getChildren().addAll(comboxNuevasDirecciones, txtdirecciones);
                        // ... agregar otros elementos al HBox según sea necesario ...

                        // Agrega el HBox a tu VBox u otro contenedor
                        hboxNuevadDirecciones.getChildren().addAll(comboxNuevasDirecciones);
                        cajaDirecciones.getChildren().addAll(hboxNuevadDirecciones);
                    }
                }

            }
            if (!personallenar.getFechas().isEmpty()) {
                dateFecha.setValue(personallenar.getFechas().get(0).getFecha());
                FechaCb f = buscarFechaPorFestividad(personallenar.getFechas().get(0).getFestividad(), comboFechas.getItems());
                comboFechas.setValue(f);
                for (Fecha fecha : personallenar.getFechas()) {
                    int index = personallenar.getFechas().indexOf(fecha);
                    if (index >= 1) {
                        HBox hboxNuevadRedes = new HBox();
                        ComboBox<FechaCb> comboxNuevasFechas = new ComboBox<>();
                        FechaCb.configurarComboBoxConFechas(comboxNuevasFechas);
                        FechaCb fechaSeleccionada = buscarFechaPorFestividad(fecha.getFestividad(), comboxNuevasFechas.getItems());
                        comboxNuevasFechas.setValue(fechaSeleccionada);
                        DatePicker txtnuevasredes = new DatePicker();
                        txtnuevasredes.setValue(fecha.getFecha());
                        hboxNuevadRedes.getChildren().addAll(comboxNuevasFechas, txtnuevasredes);
                        cajaFechas.getChildren().addAll();
                    }
                }
            }
            if (!personallenar.getNacionalidad().isEmpty()) {
                txtNacionalidad.setText(personallenar.getNacionalidad());

            }
            if (!personallenar.getOcupacion().isEmpty()) {
                txtOcupación.setText(personallenar.getOcupacion());

            }

        }

    }

    private PrefijoPais buscarPrefijoPaisPorPrefijo(String prefijo, List<PrefijoPais> listaPrefijos) {
        for (PrefijoPais prefijoPais : listaPrefijos) {
            System.out.println("Prefijo del telefono del usuario:" + prefijo);
            System.out.println("Prefijo de la lista;" + prefijoPais);
            if (prefijoPais.getPrefijo().equals(prefijo)) {
                return prefijoPais;
            }
        }
        return null; // O manejar de otra manera si no se encuentra el prefijo
    }

    private DireccionCb buscarDireccionPorTipo(String tipoDireccion, List<DireccionCb> listaDirecciones) {
        for (DireccionCb direccionCb : listaDirecciones) {
            System.out.println("Direccion del user:" + tipoDireccion);
            System.out.println("Direccion del combo: " + direccionCb);
            if (direccionCb.getTipoDireccion().trim().equals(tipoDireccion)) {
                return direccionCb;
            }
        }
        return null; // O manejar de otra manera si no se encuentra el tipo de dirección
    }

    public void GuardarContactoActualizado(ActionEvent event) {
        if (contactoSelecionado instanceof Persona) {
            Persona personaSeleccionada = (Persona) contactoSelecionado;
            String nuevoNombre = txtNombresNuevos.getText();
            String ApellidoNuevo = txtApellidosNuevos.getText();
            String nacionalidadNueva = txtNacionalidad.getText();
            String ocupacionNueva = txtOcupación.getText();

//            Persona contacto = new Persona(nuevoNombre, ApellidoNuevo, cumpleaños, ocupacion, nombres, lldirecciones, llemails, llredes, fotos, llfechas, lltelefonos, nacionalidad);
        }
    }

    private RedesSociales buscarRedSocialPorNombre(String nombre, List<RedesSociales> listaRedes) {
        for (RedesSociales redSocial : listaRedes) {
            if (redSocial.getNombreRedSocial().equals(nombre)) {
                return redSocial;
            }
        }
        return null; // O manejar de otra manera si no se encuentra la red social
    }

    private FechaCb buscarFechaPorFestividad(String festividad, List<FechaCb> listaFechas) {

        for (FechaCb fechaCb : listaFechas) {
            System.out.println("Festividad del user:" + festividad);
            System.out.println("Festividad del combo: " + fechaCb);
            if (fechaCb.getFestividad().trim().equals(festividad)) {
                return fechaCb;
            }
        }
        return null; // O manejar de otra manera si no se encuentra la festividad
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
        Image image = new Image("file:" + rutaImagen);
        ImgFotoPersona.setImage(image);
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
    private void handleComboBoxPersona() {
        PrefijoPais.configurarComboBoxConPrefijos(comboPrefijos2);
    }

    @FXML
    private void handleComboBoxSocialMedia() {
        RedesSociales.configurarComboBoxConRedes(comboBoxRedes);
    }

    @FXML
    private void handleComboBoxDirections() {
        DireccionCb.configurarComboBoxConDirecciones(comboDirecciones);
    }

    @FXML
    private void handleComboBoxDates() {
        FechaCb.configurarComboBoxConFechas(comboFechas);
    }

    @FXML
    private void guardarContacto(ActionEvent event) {

        if (!txtNombresNuevos.getText().isEmpty() && comboPrefijos2.getValue() != null && !txtTelefono.getText().isEmpty() && fotos.size() >= 2) {
            String nombres = txtNombresNuevos.getText();
            String apellidos = txtApellidosNuevos.getText();
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
                    String[] direccion = algo.split(" ");
                    String direccionGuardar = algo.replace(direccion[0], "");
                    System.out.println(direccionGuardar);
                    if (direccion.length > 1) {
                        Direccion d = new Direccion(direccion[0], direccionGuardar);
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
            Persona contacto = new Persona(apellidos, genero, cumpleaños, ocupacion, nombres, lldirecciones, llemails, llredes, fotos, llfechas, lltelefonos, nacionalidad);
            System.out.println("Contacto: " + contacto);
            LinkedListPropia<Contacto> relacionados = new LinkedListPropia();
            if (!comboYaRelacionado.getItems().isEmpty()) {
                relacionados.addAll(comboYaRelacionado.getItems());

            }
            contacto.setContactosRelacionados(relacionados);
            for (Usuario usuario : App.listaUsuarios) {
                System.out.println(usuario);
                if (usuario.equals(App.usuario)) {
                    List<Contacto> contactos = usuario.getContactos();
                    int index = contactos.indexOf(contactoSelecionado);

                    if (index != -1) {
                        contactos.set(index, contacto);
                    }
                    break;
                }
            }
            Archivos.serializarListaUsuarios(App.listaUsuarios, "usuarios.ser");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Confirmación");
            alert.setHeaderText(null);
            alert.setContentText("!Se ha modificado con éxito el contacto!");
            alert.showAndWait();
            try {
                App.setRoot("MenuPersona");
            } catch (IOException ex) {
                System.out.println("Estamos dentro del cambio de escena");
            }
            System.out.println("si llegue");

        }
    }

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

    @FXML
    public void EliminarContactoRelacionado(ActionEvent event) {

        if (comboYaRelacionado.getValue() != null) {
            Contacto c = comboYaRelacionado.getValue();
            comboYaRelacionado.getItems().remove(c);

        }

    }

    @FXML
    public void agregarRelacionado(ActionEvent event) {

        if (comboRelacionado.getValue() != null) {
            Contacto c = comboRelacionado.getValue();
            if (!comboYaRelacionado.getItems().contains(c)) {
                comboYaRelacionado.getItems().add(c);

            }

        }

    }

}
