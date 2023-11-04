package com.mycompany.proyecto_estructuras;

import Logica.Usuario;
import Prefijos.PrefijoPais;
import static com.mycompany.proyecto_estructuras.App.listaUsuarios;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class PrimaryController {

    @FXML
    private TextField telefono;
    @FXML
    private PasswordField password;
    @FXML
    private ComboBox<PrefijoPais> comboPrefijos;

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }
    private boolean comboBoxLoaded = false;

    @FXML
    private void LoginPage(ActionEvent event) throws IOException {

        PrefijoPais selection = comboPrefijos.getValue();
        String numero = telefono.getText();
        String prefijo = selection != null ? selection.getPrefijo() : "";
        String contrasenia = password.getText();
        App.usuario = verificarUsuario(prefijo, numero, contrasenia);
        if (App.usuario != null) {
            App.setRoot("secondary.fxml");
        } else {
            showAlert("Error de login", "No puedes continuar", "Por favor, llenar todos los campos");
            telefono.clear();
            password.clear();
            comboPrefijos.getSelectionModel().clearSelection();
        }
    }

    public static Usuario verificarUsuario(String prefijo, String number, String contra) {
        for (Usuario usuario : listaUsuarios) {
            if (usuario.getPrefijo().equals(prefijo) && usuario.getTelefono().equals(number) && usuario.getContraseña().equals(contra)) {

                System.out.println(usuario);
                System.out.println("Usuario verificado");
                return usuario;
            }

        }
        System.out.println("No se ha encontrado el archivo");
        return null;
    }

    private boolean isInputValid() {
        String MessageError = "";
        if (telefono.getText() == null || telefono.getText().isEmpty()) {
            MessageError = "Telefono no valido";
        }
        if (password.getText() == null || password.getText().isEmpty()) {
            MessageError = "Constraeña no valida";

        }
        if (comboPrefijos.getValue() == null) {
            MessageError = "Seleccione el prefijo del país";

        }
        return MessageError.isEmpty();
    }

    private void showAlert(String title, String header, String content) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();

    }

    @FXML
    private void handleComboBoxShowing(Event event) {
        if (!comboBoxLoaded) {
            List<PrefijoPais> prefijos = obtenerPrefijosPais();

            comboPrefijos.getItems().setAll(prefijos);
            comboBoxLoaded = true;

        }
    }

    private List<PrefijoPais> obtenerPrefijosPais() {
        List<PrefijoPais> lista = new ArrayList<>();

        lista.add(new PrefijoPais("+593", "Ecuador"));
        lista.add(new PrefijoPais("+44", "Reino Unido"));
        lista.add(new PrefijoPais("+33", "Francia"));
        lista.add(new PrefijoPais("+49", "Alemania"));
        lista.add(new PrefijoPais("+81", "Japón"));
        lista.add(new PrefijoPais("+91", "India"));
        return lista;
    }

    @FXML
    public void initialize() {

    }

}
