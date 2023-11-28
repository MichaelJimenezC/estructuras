/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Prefijos;

import com.mycompany.proyecto_estructuras.PrimaryController;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
/**
 * Clase bandera, creada con la finalidad de llenar el comboBox con la bandera
 * del país con su respectivo codigo y el numero de usuario para el respectivo
 * ingreso a la aplicación
 *
 * @author marle
 */
public class PrefijoPais {

    private Image bandera;
    private String prefijo;
    private String nombrePais;

    public PrefijoPais(Image bandera, String prefijo, String nombrePais) {
        this.bandera = bandera;
        this.prefijo = prefijo;
        this.nombrePais = nombrePais;
    }

    public String getPrefijo() {
        return prefijo;
    }

    public void setPrefijo(String prefijo) {
        this.prefijo = prefijo;
    }

    public String getNombrePais() {
        return nombrePais;
    }

    public void setNombrePais(String nombrePais) {
        this.nombrePais = nombrePais;
    }

    public Image getBandera() {
        return bandera;
    }

    public void setBandera(Image bandera) {
        this.bandera = bandera;
    }

    @Override
    public String toString() {
        return nombrePais + " (" + prefijo + ")";
    }
     /**
     * Método que se creo con la finalidad de llenar un comboBox fácil de
     * interpretar por el usuario en donde se logré visualizar por pantalla la
     * bandera del país, el nombre del país y por último el prefijo del país
     *
     * @return la lista de los paises que serán llenados en el comboBox
     * prefijoPaises
     */
    public static List<PrefijoPais> obtenerPrefijosPais() {
        List<PrefijoPais> lista = new ArrayList<>();

        lista.add(new PrefijoPais(new Image(PrefijoPais.class.getResourceAsStream("/Imagenes/ecuador.png")), "+593", "Ecuador"));
        lista.add(new PrefijoPais(new Image(PrefijoPais.class.getResourceAsStream("/Imagenes/reino-unido.png")), "+44", "Reino Unido"));
        lista.add(new PrefijoPais(new Image(PrefijoPais.class.getResourceAsStream("/Imagenes/francia.png")), "+33", "Francia"));
        lista.add(new PrefijoPais(new Image(PrefijoPais.class.getResourceAsStream("/Imagenes/alemania.png")), "+49", "Alemania"));
        lista.add(new PrefijoPais(new Image(PrefijoPais.class.getResourceAsStream("/Imagenes/peru.png")), "+51", "Peru"));
        lista.add(new PrefijoPais(new Image(PrefijoPais.class.getResourceAsStream("/Imagenes/argentina.png")), "+54", "Argentina"));
        lista.add(new PrefijoPais(new Image(PrefijoPais.class.getResourceAsStream("/Imagenes/colombia.png")), "+57", "Colombia"));
        lista.add(new PrefijoPais(new Image(PrefijoPais.class.getResourceAsStream("/Imagenes/india.png")), "+91", "India"));
        lista.add(new PrefijoPais(new Image(PrefijoPais.class.getResourceAsStream("/Imagenes/corea-del-sur.png")), "+82", "Corea"));
        lista.add(new PrefijoPais(new Image(PrefijoPais.class.getResourceAsStream("/Imagenes/estados-unidos.png")), "+1", "Estados Unidos"));
        lista.add(new PrefijoPais(new Image(PrefijoPais.class.getResourceAsStream("/Imagenes/chile.png")), "+56", "Chile"));

        return lista;
    }

    public static void configurarComboBoxConPrefijos(ComboBox<PrefijoPais> comboBox) {
        // Asegurarse de que el ComboBox no se haya cargado previamente
        if (comboBox.getItems().isEmpty()) {
            // Cargar datos en el ComboBox
            comboBox.getItems().addAll(PrefijoPais.obtenerPrefijosPais());

            // Configurar cómo se muestra cada item con una celda personalizada
            comboBox.setCellFactory(lv -> new ListCell<PrefijoPais>() {
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

            // Configurar cómo se muestra el item seleccionado en el botón del ComboBox
            comboBox.setButtonCell(new ListCell<PrefijoPais>() {
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

   
}
