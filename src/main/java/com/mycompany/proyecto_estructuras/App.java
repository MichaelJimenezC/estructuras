package com.mycompany.proyecto_estructuras;

import Logica.AL;
import Logica.Archivos;
import Logica.Usuario;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

/**
 * JavaFX App
 */
public class App extends Application{

    public static Usuario usuario = null;
    private static Scene scene;
   public static ArrayList<Usuario> listaUsuarios= Archivos.deserializarListaUsuarios("usuarios.ser");

    @Override
    public void start(Stage stage) throws IOException {
//        Archivos.serializarListaUsuarios(listaUsuarios,"usuarios.ser");
        scene = new Scene(loadFXML("primary"), 433, 597);
        stage.setTitle("Loggin Page");
        stage.setScene(scene);
        stage.show();
        System.out.println(listaUsuarios);
        
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));

        return fxmlLoader.load();
    }

    public static ArrayList<Usuario> crearListaUsuarios(String archivo) {
        ArrayList<String> lineas = new ArrayList<>();
        ArrayList<Usuario> usuarios = new ArrayList<>();
        try ( BufferedReader bf = new BufferedReader(new FileReader(archivo, StandardCharsets.UTF_8))) {
            String linea;
            while ((linea = bf.readLine()) != null) {
                lineas.add(linea);
            }
        } catch (FileNotFoundException e) {
            System.out.println("No se encontró el archivo, estamos en el metodo de crear lista de usuarios");
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (int i = 1; i < lineas.size(); i++) {
            String[] ListaLineas = lineas.get(i).split(",");
            String prefijo = ListaLineas[0];
            String numero = ListaLineas[1];
            String contrasenia = ListaLineas[2];
            usuarios.add(new Usuario(prefijo, numero, contrasenia));

        }
        return usuarios;
    }
    public static void main(String[] args) {

        launch();

    }
}
