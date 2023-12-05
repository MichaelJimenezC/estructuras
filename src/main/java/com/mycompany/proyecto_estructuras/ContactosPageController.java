/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.proyecto_estructuras;

import Logica.Contacto;
import Logica.DoubleLinkedList;
import Logica.Empresa;
import Logica.LinkedListPropia;
import Logica.Persona;
import Logica.Telefono;
import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author marle
 */
public class ContactosPageController implements Initializable {

    @FXML
    VBox vboxVerContactos;
    @FXML
    ComboBox<String> cbxOrdenar;
    @FXML
    TextField txtFiltro;
    @FXML
    ImageView imgvFiltro;
    @FXML
    VBox cajaAsociados;

    public static Contacto contactoSelecionado = null;
    private LinkedListPropia<Contacto> contactos = App.usuario.getContactos();
    private LinkedListPropia<Contacto> contactosOrdenados; // Lista ordenada, inicialmente null
    private String ordenSeleccionado = "";

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cbxOrdenar.getItems().addAll("Tipo de Contacto", "Pais de Residencia", "Empresa"); // Agrega tus opciones de ordenamiento
        cbxOrdenar.setValue("A-Z"); // Valor por defecto
        cbxOrdenar.setOnAction(event -> {
            ordenSeleccionado = cbxOrdenar.getValue();
            ordenarContactos(contactos, ordenSeleccionado);
        });
        imgvFiltro.setOnMouseClicked(event -> {
            String criterioFiltro = txtFiltro.getText();
            LinkedListPropia<Contacto> contactosFiltrados = filtrarContactos(contactosOrdenados != null ? contactosOrdenados : contactos, criterioFiltro, ordenSeleccionado);
            mostrarContactos(contactosFiltrados);
        });

        mostrarContactos(contactos);
    }

    @FXML
    private void agregar(ActionEvent event) {
        try {
            App.setRoot("createContact");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void mostrarContactos(LinkedListPropia<Contacto> contactos) {
        vboxVerContactos.getChildren().clear();
        if (contactos.isEmpty()) {

            Label label = new Label("NO TIENES CONTACTOS");
            vboxVerContactos.getChildren().add(label);
        } else {
            System.out.println(contactos.size());
            for (Contacto contacto : contactos) {
                if (contacto instanceof Persona) {
                    Persona p1 = (Persona) contacto;
                    ImageView imgv = new ImageView();
                    if (!p1.getFotos().isEmpty()) {
                        imgv.setImage(new Image("file:" + p1.getFotos().get(0)));
                        imgv.setFitWidth(80); // Establecer el ancho de la imagen
                        imgv.setFitHeight(80); // Establecer la altura de la imagen
                        imgv.setPreserveRatio(true);
                        imgv.setSmooth(true);
                        imgv.setCache(true);

                    }
                    System.out.println(contacto);
                    HBox principal = new HBox(40);
                    principal.setAlignment(Pos.CENTER);
                    VBox contactoInformacion = new VBox(5);
                    contactoInformacion.setAlignment(Pos.CENTER);

                    Label nombre = null;
                    Label numero = null;

                    for (Telefono telefono : p1.getTelefonos()) {
                        nombre = new Label(p1.getNombre() + " " + p1.getApellido());
                        numero = new Label(telefono.getPrefijo() + " " + telefono.getNumero());
                    }

                    nombre.setStyle("-fx-text-fill: #6735a4; -fx-font-weight: bold;");
                    numero.setStyle("-fx-text-fill: #7F65FF");
                    Button boton = new Button("Ver");
                    boton.setStyle("-fx-background-color: #FFFF;");
                    boton.setStyle("-fx-font-weight: bold; -fx-text-fill: #6735a4;"); // Fuente en negrita y color morado
                    boton.setOnAction(event -> {
                        contactoSelecionado = contacto;
                        try {
                            App.setRoot("MenuPersona");
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    });
                    principal.setStyle("-fx-border-color: #D3D3D3");
                    principal.setPadding(new Insets(5, 5, 5, 5));
                    contactoInformacion.getChildren().addAll(nombre, numero);
//                    ToggleButton botonFavorito= new ToggleButton("✰");
//                    botonFavorito.setUserData(contacto);
//                    botonFavorito.setSelected(contacto.isFavorito());
//                    botonFavorito.setOnAction(this::handleFavoritoAction);
                    principal.getChildren().addAll(imgv, contactoInformacion, boton);
                    vboxVerContactos.setMargin(principal, new Insets(10, 10, 10, 10));

                    vboxVerContactos.getChildren().add(principal);

                } else if (contacto instanceof Empresa) {
                    Empresa p1 = (Empresa) contacto;
                    ImageView imgv = new ImageView();
                    if (!p1.getFotos().isEmpty()) {
                        imgv.setImage(new Image("file:" + p1.getFotos().get(0)));
                        imgv.setFitWidth(80); // Establecer el ancho de la imagen
                        imgv.setFitHeight(80); // Establecer la altura de la imagen
                        imgv.setPreserveRatio(true);
                        imgv.setSmooth(true);
                        imgv.setCache(true);

                    }
                    System.out.println(contacto);
                    HBox principal = new HBox(40);
                    principal.setAlignment(Pos.CENTER);
                    VBox contactoInformacion = new VBox(5);
                    contactoInformacion.setAlignment(Pos.CENTER);

                    Label nombre = null;
                    Label numero = null;

                    for (Telefono telefono : p1.getTelefonos()) {
                        nombre = new Label(p1.getNombre() + " " + p1.getRazonSocial());
                        numero = new Label(telefono.getPrefijo() + " " + telefono.getNumero());
                    }

                    nombre.setStyle("-fx-text-fill: #6735a4; -fx-font-weight: bold;");
                    numero.setStyle("-fx-text-fill: #7F65FF");
                    Button boton = new Button("Ver");
                    boton.setStyle("-fx-background-color: #FFFF;");
                    boton.setStyle("-fx-font-weight: bold; -fx-text-fill: #6735a4;"); // Fuente en negrita y color morado
                    boton.setOnAction(event -> {
                        contactoSelecionado = contacto;
                        try {
                            App.setRoot("MenuEmpresa");
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    });
                    principal.setStyle("-fx-border-color: #D3D3D3");
                    principal.setPadding(new Insets(5, 5, 5, 5));
                    contactoInformacion.getChildren().addAll(nombre, numero);
//                    ToggleButton botonFavorito= new ToggleButton("✰");
//                    botonFavorito.setUserData(contacto);
//                    botonFavorito.setSelected(contacto.isFavorito());
//                    botonFavorito.setOnAction(this::handleFavoritoAction);
                    principal.getChildren().addAll(imgv, contactoInformacion, boton);
                    vboxVerContactos.setMargin(principal, new Insets(10, 10, 10, 10));

                    vboxVerContactos.getChildren().add(principal);
                    //para la empresa
                }
            }
        }
    }

//    actualizarListaContactos(contacto);
//    guardarCambios(); //llamda a metodo que tiene guarda las modificaciones
//    private void actualizarListaContactos(Contacto contactoActualizado) {
//    for (Contacto contacto : ) {
//        if (contacto instanceof Persona) {
//            Persona persona = (Persona) contacto;
//            for (Telefono telefono: persona.getTelefonos()){
//                telefono.equals(contacttoActualizado.getNumero());
//                persona.setFavorito(contactoActualizado.getEsFavorito());
//            }
//        } else if (contacto instanceof Empresa) {
//            Empresa empresa = (Empresa) contacto;
//            for (Telefono telefono: empresa.getTelefonos()){
//            if (telefono.equals(contactoActualizado.getTelefono())) {
//                empresa.setEsFavorito(contactoActualizado.getEsFavorito());
//                break;
//            }
//            }
//        }
    private void ordenarContactos(LinkedListPropia<Contacto> contactos, String opcionOrdenar) {
        switch (opcionOrdenar) {
            case "Tipo de Contacto":
                // Ordenar por tipo de contacto (supongamos que hay un método getTipoContacto)
                contactosOrdenados = ordenarPorTipoContacto(contactos);
                break;
            case "País de Residencia":
                // Ordenar por país de residencia (supongamos que hay un método getPaisResidencia)
                contactosOrdenados = ordenarPorPaisResidencia(contactos);
                break;
            case "Empresa":
                // Ordenar por empresa (supongamos que hay un método getEmpresa)
                contactosOrdenados = ordenarPorEmpresa(contactos);
                break;
            default:
                // Manejar opción desconocida, si es necesario
                break;
        }
    }

    private LinkedListPropia<Contacto> ordenarPorTipoContacto(LinkedListPropia<Contacto> contactos) {
        // Encontrar todos los contactos de tipo Empresa
        LinkedListPropia<Contacto> contactosEmpresa = contactos.findAll((contacto1, contacto2) -> {
            if (contacto1 instanceof Empresa) {
                return 0;
            }
            return 1;
        }, null);

        // Ordenar los contactos de tipo Empresa por razon social
        Comparator<Contacto> comparatorRazonSocial = Comparator.comparing(contacto -> ((Empresa) contacto).getRazonSocial());
        contactosEmpresa.sort(comparatorRazonSocial);

        // Encontrar todos los contactos de tipo Persona
        LinkedListPropia<Contacto> contactosPersona = contactos.findAll((contacto1, contacto2) -> {
            if (contacto1 instanceof Persona) {
                return 0;
            }
            return 1;
        }, null);

        // Ordenar los contactos de tipo Persona por nombre (o cualquier otro criterio deseado)
        Comparator<Contacto> comparatorNombre = Comparator.comparing(contacto -> ((Persona) contacto).getNombre());
        contactosPersona.sort(comparatorNombre);

        // Crear la lista final ordenada
        LinkedListPropia<Contacto> contactosOrdenados = new LinkedListPropia<>();
        contactosOrdenados.addAll(contactosEmpresa);
        contactosOrdenados.addAll(contactosPersona);

        return contactosOrdenados;
    }

    private LinkedListPropia<Contacto> ordenarPorPaisResidencia(LinkedListPropia<Contacto> contactos) {
        Collections.sort(contactos, new Comparator<Contacto>() {
            @Override
            public int compare(Contacto contacto1, Contacto contacto2) {
                // Aquí asumimos que la nacionalidad es un String
                // Puedes ajustar según el tipo de datos real de la nacionalidad
                String nacionalidad1 = contacto1.getNacionalidad();
                String nacionalidad2 = contacto2.getNacionalidad();

                // Usa compareTo para la comparación alfabética
                return nacionalidad1.compareTo(nacionalidad2);
            }
        });
        return contactos;
    }

    private LinkedListPropia<Contacto> ordenarPorEmpresa(LinkedListPropia<Contacto> contactos) {
        // Encontrar todos los contactos de tipo Empresa
        LinkedListPropia<Contacto> contactosEmpresa = contactos.findAll((contacto1, contacto2) -> {
            if (contacto1 instanceof Empresa) {
                return 0;
            }
            return 1;
        }, null);

        // Ordenar los contactos de tipo Empresa por razon social
        Comparator<Contacto> comparatorRazonSocial = Comparator.comparing(contacto -> ((Empresa) contacto).getRazonSocial());
        contactosEmpresa.sort(comparatorRazonSocial);

        // Encontrar todos los contactos que no son empresas
        LinkedListPropia<Contacto> contactosNoEmpresa = contactos.findAll((contacto1, contacto2) -> {
            if (contacto1 instanceof Empresa) {
                return 1;
            }
            return 0;
        }, null);

        // Crear la lista final ordenada
        LinkedListPropia<Contacto> contactosOrdenados = new LinkedListPropia<>();
        contactosOrdenados.addAll(contactosEmpresa);
        contactosOrdenados.addAll(contactosNoEmpresa);

        return contactosOrdenados;
    }

    private LinkedListPropia<Contacto> filtrarContactos(LinkedListPropia<Contacto> contactos, String criterioFiltro, String ordenSeleccionado) {

        if (!criterioFiltro.isEmpty()) {
            LinkedListPropia<Contacto> contactosFiltrados = new LinkedListPropia<>();
            for (Contacto contacto : contactos) {
                switch (ordenSeleccionado) {
                    case "Tipo de Contacto":
                        filtrarTipoContacto(contacto, criterioFiltro, contactosFiltrados);
                        break;
                    case "Pais de Residencia":
                        filtrarPorPais(contacto, criterioFiltro, contactosFiltrados);
                        break;
                    case "Empresa":
                        filtrarEmpresa(contacto, criterioFiltro, contactosFiltrados);
                        break;
                    default:
                        contactosFiltrados = this.contactos;

                        break;
                    // Agrega más casos según sea necesario para otros criterios de ordenamiento
                }
            }

            return contactosFiltrados;
        } else {
            return this.contactos;
        }
    }

    private void filtrarTipoContacto(Contacto contacto, String criterioFiltro, LinkedListPropia<Contacto> contactosFiltrados) {
        // Lógica para filtrar por tipo de contacto (Persona o Empresa)
        // Puedes ajustar esto según la estructura de tu código

        if ("Persona".equalsIgnoreCase(criterioFiltro) && contacto instanceof Persona) {
            contactosFiltrados.add(contacto);
        } else if ("Empresa".equalsIgnoreCase(criterioFiltro) && contacto instanceof Empresa) {
            contactosFiltrados.add(contacto);
        }
    }

    private void filtrarPorPais(Contacto contacto, String criterioFiltro, LinkedListPropia<Contacto> contactosFiltrados) {
        // Asumo que el atributo nacionalidad es un String en la clase Contacto
        String nacionalidad = contacto.getNacionalidad();

        // Verificar si la nacionalidad coincide con el criterio de filtro
        if (nacionalidad != null && nacionalidad.toLowerCase().contains(criterioFiltro.toLowerCase())) {
            contactosFiltrados.add(contacto);
        }
    }

    private void filtrarEmpresa(Contacto contacto, String criterioFiltro, LinkedListPropia<Contacto> contactosFiltrados) {
        if (contacto instanceof Empresa) {
            Empresa empresa = (Empresa) contacto;
            // Filtrar por razón social
            if (empresa.getRazonSocial().toLowerCase().contains(criterioFiltro.toLowerCase())) {
                contactosFiltrados.add(empresa);
            }
        }
    }

}
