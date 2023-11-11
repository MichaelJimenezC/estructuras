package com.mycompany.proyecto_estructuras;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class NewContactWindow extends Application {

    private VBox contentBox; // Referencia a VBox para campos dinámicos

    @Override
    public void start(Stage stage) {
        stage.setTitle("Nuevo Contacto");

        ScrollPane scrollPane = new ScrollPane();
        contentBox = new VBox(10);
        contentBox.setPadding(new Insets(10));
        scrollPane.setContent(contentBox);
        scrollPane.setFitToWidth(true);

        ComboBox<String> contactTypeComboBox = new ComboBox<>();
        contactTypeComboBox.setItems(FXCollections.observableArrayList("Persona", "Empresa"));
        contactTypeComboBox.setPromptText("Seleccione tipo de contacto");

        // Crear GridPane para campos comunes
        GridPane commonFieldsPanel = new GridPane();
        commonFieldsPanel.setVgap(8);
        commonFieldsPanel.setHgap(10);

        // Agregar campos comunes aquí...
        addCommonFields(commonFieldsPanel);

        GridPane personaFieldsPanel = createDynamicFieldsPanel("Persona");
        GridPane empresaFieldsPanel = createDynamicFieldsPanel("Empresa");

        contactTypeComboBox.valueProperty().addListener((obs, oldVal, newVal) -> {
            contentBox.getChildren().removeAll(personaFieldsPanel, empresaFieldsPanel); // Remover ambos para resetear la vista.
            if ("Persona".equals(newVal)) {
                contentBox.getChildren().add(personaFieldsPanel);
            } else if ("Empresa".equals(newVal)) {
                contentBox.getChildren().add(empresaFieldsPanel);
            }
        });

        // Añadir el ComboBox y el panel de campos comunes al VBox principal
        contentBox.getChildren().addAll(contactTypeComboBox, commonFieldsPanel);

        Scene scene = new Scene(scrollPane, 350, 600);
        stage.setScene(scene);
        stage.show();
    }

    private void addCommonFields(GridPane grid) {
        // Añade aquí los campos comunes para todas las entradas de contacto.
        // La fila base para campos comunes empezará en 0 y aumentará con cada conjunto de campos agregados.
        int rowIndex = 0;

        // Nombre
        addFieldWithButtons(grid, "Nombre", rowIndex++);
        // Direcciones
        addFieldWithButtons(grid, "Direcciones", rowIndex++);
        // Emails
        addFieldWithButtons(grid, "Emails", rowIndex++);
        // Telefonos
        addFieldWithButtons(grid, "Telefonos", rowIndex++);
        // Redes sociales
        addFieldWithButtons(grid, "Redes sociales", rowIndex++);
        // Fotos
        addFieldWithButtons(grid, "Fotos", rowIndex++);
        // Fechas
        addFieldWithButtons(grid, "Fechas", rowIndex++);
        // Contactos relacionados
        addFieldWithButtons(grid, "Contactos Relacionados", rowIndex);
    }

    private void addFieldWithButtons(GridPane gridPane, String fieldLabel, int rowIndex) {
        Label label = new Label(fieldLabel + ":");
        VBox vboxFields = new VBox(5); // VBox para contener todos los TextField relacionados
        vboxFields.getChildren().add(new TextField()); // Añadir el primer TextField
        
        VBox vboxButtons = new VBox(5); // VBox para contener los botones de agregar y eliminar
        Button addButton = new Button("Agregar");
        addButton.setOnAction(e -> vboxFields.getChildren().add(new TextField()));

        Button removeButton = new Button("Eliminar");
        removeButton.setOnAction(e -> {
            if (vboxFields.getChildren().size() > 1) {
                vboxFields.getChildren().remove(vboxFields.getChildren().size() - 1);
            }
        });
        
        vboxButtons.getChildren().addAll(addButton, removeButton);

        HBox hbox = new HBox(10, label, vboxFields, vboxButtons);
        gridPane.add(hbox, 0, rowIndex, 3, 1);
    }

    private GridPane createDynamicFieldsPanel(String type) {
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.setVgap(8);
        gridPane.setHgap(10);
        int rowIndex = 0; // Iniciar contador de filas para campos específicos.

        if ("Persona".equals(type)) {
            // Agregar campos específicos para Persona aquí...
            addFieldWithButtons(gridPane, "Apellido", rowIndex++);
            addFieldWithButtons(gridPane, "Cedula", rowIndex++);
            addFieldWithButtons(gridPane, "Género", rowIndex++);
            addFieldWithButtons(gridPane, "Fecha de Nacimiento", rowIndex++);
            addFieldWithButtons(gridPane, "Ocupación", rowIndex++);
            addFieldWithButtons(gridPane, "Nacionalidad", rowIndex);
        } else if ("Empresa".equals(type)) {
            // Agregar campos específicos para Empresa aquí...
            addFieldWithButtons(gridPane, "Razón Social", rowIndex++);
            addFieldWithButtons(gridPane, "Número Registro", rowIndex++);
            addFieldWithButtons(gridPane, "Tipo Empresa", rowIndex);
        }

        return gridPane;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
