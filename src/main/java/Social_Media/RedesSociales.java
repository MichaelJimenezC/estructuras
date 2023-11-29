/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Social_Media;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author marle
 */
public class RedesSociales {

    private Image logoRedSocial;
    private String nombreRedSocial;

    public RedesSociales(Image logoRedSocial, String nombreRedSocial) {
        this.logoRedSocial = logoRedSocial;
        this.nombreRedSocial = nombreRedSocial;
    }

    public Image getLogoRedSocial() {
        return logoRedSocial;
    }

    public void setLogoRedSocial(Image logoRedSocial) {
        this.logoRedSocial = logoRedSocial;
    }

    public String getNombreRedSocial() {
        return nombreRedSocial;
    }

    public void setNombreRedSocial(String nombreRedSocial) {
        this.nombreRedSocial = nombreRedSocial;
    }

    public static List<RedesSociales> obtenerRedSocial() {
        List<RedesSociales> listaRedes = new ArrayList<>();
        listaRedes.add(new RedesSociales(new Image(RedesSociales.class.getResourceAsStream("/Imagenes/facebook.png")), "Facebook"));
        listaRedes.add(new RedesSociales(new Image(RedesSociales.class.getResourceAsStream("/Imagenes/instagram.png")), "Instagram"));
        listaRedes.add(new RedesSociales(new Image(RedesSociales.class.getResourceAsStream("/Imagenes/letra-x.png")), " Twitter"));
        listaRedes.add(new RedesSociales(new Image(RedesSociales.class.getResourceAsStream("/Imagenes/tik-tok.png")), "Tik tok"));
        return listaRedes;
    }
public static void configurarComboBoxConRedes(ComboBox<RedesSociales> comboBox) {
        // Asegurarse de que el ComboBox no se haya cargado previamente
        if (comboBox.getItems().isEmpty()) {
            // Cargar datos en el ComboBox
            comboBox.getItems().addAll(RedesSociales.obtenerRedSocial());

            // Configurar cómo se muestra cada item con una celda personalizada
            comboBox.setCellFactory(lv -> new ListCell<RedesSociales>() {
                private final ImageView imageView = new ImageView();

                @Override
                protected void updateItem(RedesSociales item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null) {
                        setText(null);
                        setGraphic(null);
                    } else {
                        imageView.setImage(item.getLogoRedSocial());
                        imageView.setFitWidth(16);
                        imageView.setFitHeight(16);
                        setText(item.getNombreRedSocial());
                        setGraphic(imageView);
                    }
                }
            });

            // Configurar cómo se muestra el item seleccionado en el botón del ComboBox
            comboBox.setButtonCell(new ListCell<RedesSociales>() {
                private final ImageView imageView = new ImageView();

                @Override
                protected void updateItem(RedesSociales item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null) {
                        setText(null);
                        setGraphic(null);
                    } else {
                        imageView.setImage(item.getLogoRedSocial());
                        imageView.setFitWidth(16);
                        imageView.setFitHeight(16);
                        setText(item.getNombreRedSocial());
                        setGraphic(imageView);
                    }
                }
            });
        }
    }
     @Override
    public String toString() {
        return nombreRedSocial;
    }

    
}
