/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.proyecto_estructuras;

import Direcciones.DireccionCb;
import Fechas.FechaCb;
import Logica.Direccion;
import Logica.Fecha;
import Logica.Persona;
import Logica.RedSocial;
import Logica.Telefono;
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
import java.util.List;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

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
    private ComboBox<Fecha> comboFechas;
    @FXML
    private DatePicker dateFecha;
    @FXML
    private TextField txtRedes;
    @FXML
    private TextField txtDirecciones;

    @Override

    public void initialize(URL url, ResourceBundle rb) {
    }

    public void CambiarPestañaVista(ActionEvent event) {
        try {
            App.setRoot("MenuPersona");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void EliminarContacto(ActionEvent event) {
        if (contactoSelecionado instanceof Persona) {

        }
    }
    @FXML
    private VBox vboxTelefonos;
    @FXML
    private VBox vboxEmails;
    @FXML
    private VBox vboxRedesSociales;
    @FXML
    private VBox vboxDirecciones;
    @FXML
    private VBox vboxFechasRelevantes;

    public void llenarDatos() {

        if (contactoSelecionado instanceof Persona) {
            Persona personallenar = (Persona) contactoSelecionado;
            txtNombresNuevos.setText(personallenar.getNombre()); //obligatorio
            if (!personallenar.getApellido().isEmpty()) {
                txtApellidosNuevos.setText(personallenar.getApellido());
            }
            txtTelefono.setText(personallenar.getTelefonos().get(0).getNumero()); //obligatorio

            if (!personallenar.getEmails().isEmpty()) {
                txtEmails.setText(personallenar.getEmails().get(0));
                for (Telefono telefono : personallenar.getTelefonos()) {
                    int index = personallenar.getTelefonos().indexOf(telefono);
                    if (index >= 1) {
                        HBox hboxNuevosTelefonos = new HBox();
                        ComboBox<PrefijoPais> comboxNuevosTelefonos = new ComboBox<>();
                        PrefijoPais.configurarComboBoxConPrefijos(comboxNuevosTelefonos);
                        // Encuentra el PrefijoPais que coincide con el prefijo del teléfono
                        PrefijoPais prefijoSeleccionado = buscarPrefijoPaisPorPrefijo(telefono.getPrefijo(), comboxNuevosTelefonos.getItems());
                        comboxNuevosTelefonos.setValue(prefijoSeleccionado);
                        TextField txttelefonos= new TextField();
                        txttelefonos.setText(telefono.getNumero());
                        // Agrega el ComboBox y otros elementos necesarios al HBox
                        hboxNuevosTelefonos.getChildren().addAll(comboxNuevosTelefonos,txttelefonos);
                        vboxEmails.getChildren().addAll(hboxNuevosTelefonos);
                    }
                }

            }
            if (!personallenar.getRedes().isEmpty()) {
                txtRedes.setText(personallenar.getRedes().get(0).getUsuario());
                for (RedSocial redesocial : personallenar.getRedes()) {
                    int index = personallenar.getRedes().indexOf(redesocial);
                    if (index >= 1) {
                        HBox hboxNuevasRedes = new HBox();
                        ComboBox<RedesSociales> comboxNuevasRedes = new ComboBox<>();
                        RedesSociales.configurarComboBoxConRedes(comboxNuevasRedes);
                        RedesSociales redSocialSeleccionada = buscarRedSocialPorNombre(redesocial.getRed(), comboxNuevasRedes.getItems());
                        comboxNuevasRedes.setValue(redSocialSeleccionada);
                        TextField txtredes= new TextField();
                        txtredes.setText(redesocial.getUsuario());
                        // Agrega el ComboBox y otros elementos necesarios al HBox
                        hboxNuevasRedes.getChildren().addAll(comboxNuevasRedes,txtredes);
                        // ... agregar otros elementos al HBox según sea necesario ...

                        hboxNuevasRedes.getChildren().addAll();
                        vboxRedesSociales.getChildren().addAll(hboxNuevasRedes);
                    }
                }
            }
            if (personallenar.getDirecciones().get(0).getUbicacion() != null) {
                txtDirecciones.setText(personallenar.getDirecciones().get(0).getUbicacion());
                for (Direccion direccion : personallenar.getDirecciones()) {
                    int index = personallenar.getDirecciones().indexOf(direccion);
                    if (index >= 1) {
                        HBox hboxNuevadDirecciones = new HBox();
                        ComboBox<DireccionCb> comboxNuevasDirecciones = new ComboBox<>();
                        DireccionCb.configurarComboBoxConDirecciones(comboxNuevasDirecciones);
                        DireccionCb direccionSeleccionada = buscarDireccionPorTipo(direccion.getTipo(), comboxNuevasDirecciones.getItems());
                        comboxNuevasDirecciones.setValue(direccionSeleccionada);
                        TextField txtdirecciones= new TextField();
                        txtdirecciones.setText(direccion.getUbicacion());
                        // Agrega el ComboBox y otros elementos necesarios al HBox
                        hboxNuevadDirecciones.getChildren().addAll(comboxNuevasDirecciones,txtdirecciones);
                        // ... agregar otros elementos al HBox según sea necesario ...

                        // Agrega el HBox a tu VBox u otro contenedor
                        hboxNuevadDirecciones.getChildren().addAll(comboxNuevasDirecciones);
                        vboxDirecciones.getChildren().addAll(hboxNuevadDirecciones);
                    }
                }

            }
            if (!personallenar.getFechas().isEmpty()) {
                dateFecha.setValue(personallenar.getFechas().get(0).getFecha());
                for (Fecha fecha : personallenar.getFechas()) {
                    int index = personallenar.getFechas().indexOf(fecha);
                    if (index >= 1) {
                        HBox hboxNuevadRedes = new HBox();
                        ComboBox<FechaCb> comboxNuevasFechas = new ComboBox<>();
                        FechaCb.configurarComboBoxConFechas(comboxNuevasFechas);
                        FechaCb fechaSeleccionada = buscarFechaPorFestividad(fecha.getFestividad(), comboxNuevasFechas.getItems());
                        comboxNuevasFechas.setValue(fechaSeleccionada);
                        DatePicker txtnuevasredes= new DatePicker();
                        txtnuevasredes.setValue(fecha.getFecha());
                        hboxNuevadRedes.getChildren().addAll(comboxNuevasFechas, txtnuevasredes);
                        vboxFechasRelevantes.getChildren().addAll();
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
            if (prefijoPais.getPrefijo().equals(prefijo)) {
                return prefijoPais;
            }
        }
        return null; // O manejar de otra manera si no se encuentra el prefijo
    }

    private DireccionCb buscarDireccionPorTipo(String tipoDireccion, List<DireccionCb> listaDirecciones) {
        for (DireccionCb direccionCb : listaDirecciones) {
            if (direccionCb.getTipoDireccion().equals(tipoDireccion)) {
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
            if (fechaCb.getFestividad().equals(festividad)) {
                return fechaCb;
            }
        }
        return null; // O manejar de otra manera si no se encuentra la festividad
    }
}
