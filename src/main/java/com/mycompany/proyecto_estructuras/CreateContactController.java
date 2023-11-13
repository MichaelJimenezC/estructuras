package com.mycompany.proyecto_estructuras;

import Prefijos.PrefijoPais;
import static com.mycompany.proyecto_estructuras.PrimaryController.comboPrefijos;
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
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.Event;
import javafx.scene.Node;
import javafx.scene.image.Image;

public class CreateContactController implements Initializable {

    @FXML
    private ComboBox cbTipo;
    @FXML
    private ComboBox<PrefijoPais> comboPrefijos2=PrimaryController.comboPrefijos;
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
    private Button buttonGuardar;
    private boolean comboBoxLoaded=false;

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
  @FXML
    private void handleComboBoxShowing(Event event) {
        if (!comboBoxLoaded) {
            List<PrefijoPais> prefijos = obtenerPrefijosPais();

            comboPrefijos.getItems().setAll(prefijos);
            comboBoxLoaded = true;

            for (PrefijoPais prefijoPais : comboPrefijos.getItems()) {
                System.out.println(prefijoPais.getNombrePais() + " - " + prefijoPais.getPrefijo() + " - " + prefijoPais.getBandera());
            }
        }
    }
    private List<PrefijoPais> obtenerPrefijosPais() {
        List<PrefijoPais> lista = new ArrayList<>();

        lista.add(new PrefijoPais(new Image(getClass().getResourceAsStream("/Imagenes/ecuador.png")), "+593", "Ecuador"));
        lista.add(new PrefijoPais(new Image(getClass().getResourceAsStream("/Imagenes/reino-unido.png")), "+44", "Reino Unido"));
        lista.add(new PrefijoPais(new Image(getClass().getResourceAsStream("/Imagenes/francia.png")), "+33", "Francia"));
        lista.add(new PrefijoPais(new Image(getClass().getResourceAsStream("/Imagenes/alemania.png")), "+49", "Alemania"));
        lista.add(new PrefijoPais(new Image(getClass().getResourceAsStream("/Imagenes/peru.png")), "+51", "Peru"));
        lista.add(new PrefijoPais(new Image(getClass().getResourceAsStream("/Imagenes/argentina.png")), "+54", "Argentina"));
        lista.add(new PrefijoPais(new Image(getClass().getResourceAsStream("/Imagenes/colombia.png")), "+57", "Colombia"));
        lista.add(new PrefijoPais(new Image(getClass().getResourceAsStream("/Imagenes/india.png")), "+91", "India"));
        lista.add(new PrefijoPais(new Image(getClass().getResourceAsStream("/Imagenes/corea-del-sur.png")), "+82", "Corea"));
        lista.add(new PrefijoPais(new Image(getClass().getResourceAsStream("/Imagenes/estados-unidos.png")), "+1", "Estados Unidos"));
        lista.add(new PrefijoPais(new Image(getClass().getResourceAsStream("/Imagenes/chile.png")), "+56", "Chile"));

        return lista;
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
    private void guardarContacto(ActionEvent event) {
        // Recuperar los valores de los atributos
        String tipo = (String) cbTipo.getValue();
        String nombres = txtNombres.getText();
        String apellidos = txtApellidos.getText();

        // Recuperar valores dinámicos de los componentes
        String telefonos = obtenerValores(cajaTelefonos);
        String emails = obtenerValores(cajaEmails);
        String redesSociales = obtenerValores(cajaRedes);
        String direcciones = obtenerValores(cajaDirecciones);
        String fechasRelevantes = obtenerValores(cajaFechas);

        // Imprimir o realizar acciones con los valores recuperados
        System.out.println("Tipo: " + tipo);
        System.out.println("Nombres: " + nombres);
        System.out.println("Apellidos: " + apellidos);
        System.out.println("Telefonos: " + telefonos);
        System.out.println("Emails: " + emails);
        System.out.println("Redes Sociales: " + redesSociales);
        System.out.println("Direcciones: " + direcciones);
        System.out.println("Fechas Relevantes: " + fechasRelevantes);

        // Aquí puedes realizar acciones adicionales con los valores recuperados
        // Por ejemplo, cerrar la ventana actual, cambiar a otra vista, etc.
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
