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
    public static ComboBox<PrefijoPais> comboPrefijos;

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }
    private boolean comboBoxLoaded = false;

    /**
     * Manejador de eventos que se encargará de hacer las respectivas
     * validaciones de las credenciales de los usuarios en que de caso no el
     * usuario no exista se mostrará un mensaje de error
     *
     * @param event evento
     * @throws IOException lanzamos una excepcion
     */
    @FXML
    private void LoginPage(ActionEvent event) {
        if (!isInputValid()) {
            return;
        }
        PrefijoPais seleccion = comboPrefijos.getValue();
        String PrefijoSeleccionado = seleccion.getPrefijo();
        String numero = telefono.getText().trim();
        String contrasenia = password.getText().trim();
        App.usuario = verificarUsuario(PrefijoSeleccionado, numero, contrasenia);
        if (App.usuario != null) { try {
            // Verificación de las credenciales del usuario
            App.setRoot("secondary"); // Cambiar a la siguiente pantalla
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } else {
            // Informar al usuario que las credenciales son incorrectas
            showAlert("Error de login", "Acceso Denegado", "Prefijo, número de teléfono o contraseña incorrectos.");
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
        return null;
    }

    private boolean isInputValid() {
        StringBuilder errorMessage = new StringBuilder();

        if (comboPrefijos.getValue() == null) {
            errorMessage.append("Seleccione el prefijo del país.\n");
        }
        if (telefono.getText() == null || telefono.getText().isEmpty()) {
            errorMessage.append("Número de teléfono no válido.\n");
        }
        if (password.getText() == null || password.getText().isEmpty()) {
            errorMessage.append("Contraseña no válida.\n");
        }

        if (errorMessage.length() > 0) {
            // Mostrar todos los errores acumulados si hay alguno.
            showAlert("Error de Validación", "Por favor corrija los siguientes campos", errorMessage.toString());
             telefono.clear();
            password.clear();
            comboPrefijos.getSelectionModel().clearSelection();
            return false; // Indicar que la entrada no es válida.
        }
        return true; // Indicar que la entrada es válida.
    }

    /**
     * Método que creara un showAlert personalizado y consta de 3 parametros que
     * serán editados por el usuario
     *
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
