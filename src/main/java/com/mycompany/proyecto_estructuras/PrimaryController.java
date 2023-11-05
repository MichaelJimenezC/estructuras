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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.Node;
import javafx.scene.control.ListCell;

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
/**
 * 
 * @param prefijo parametro prefijo
 * @param number parametro numero
 * @param contra parametro contraseña
 * @return retorna el usuario verificado
 */
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

    /**
     * Método que creara un showAlert personalizado y consta de 3 parametros que serán editados por el usuario
     * @param title titulo de nuestra casilla emergente
     * @param header Encabezado de nuestra casilla emergente
     * @param content Contenido que se mostrá en la casilla emergente
     */
    private void showAlert(String title, String header, String content) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();

    }

    /**
     * Manejador de eventos del comboBox que al presionar sobre el comboBox me
     * aparezcan cargado los datos de los paìses para el inicio de sesión
     *
     * @param event se recine un tipo Event
     */
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

    /**
     * Método que se creo con la finalidad de llenar un comboBox fácil de
     * interpretar por el usuario en donde se logré visualizar por pantalla la
     * bandera del país, el nombre del país y por último el prefijo del país
     *
     * @return la lista de los paises que serán llenados en el comboBox
     * prefijoPaises
     */
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

    @FXML
    public void initialize() {

        comboPrefijos.setCellFactory(lv -> new ListCell<PrefijoPais>() {
            private final ImageView imageView = new ImageView();

            @Override
            protected void updateItem(PrefijoPais item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    imageView.setImage(item.getBandera());
                    imageView.setFitWidth(20);
                    imageView.setFitHeight(15);
                    setText(item.getNombrePais() + " " + item.getPrefijo());
                    setGraphic(imageView);
                }
            }
        });

        comboPrefijos.setButtonCell(new ListCell<PrefijoPais>() {
            private final ImageView imageView = new ImageView();

            @Override
            protected void updateItem(PrefijoPais item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    imageView.setImage(item.getBandera());
                    imageView.setFitWidth(20);
                    imageView.setFitHeight(15);
                    setText(item.getNombrePais() + " " + item.getPrefijo());
                    setGraphic(imageView);
                }
            }
        });
    }

}
