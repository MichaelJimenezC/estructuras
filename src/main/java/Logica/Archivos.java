/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 *
 * @author Michael
 */
public class Archivos {

    public static void serializarListaUsuarios(ArrayList<Usuario> listaUsuarios, String nombreArchivo) {
        try ( ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(nombreArchivo))) {
            outputStream.writeObject(listaUsuarios);
            System.out.println("Lista de usuarios serializada correctamente.");
        } catch (IOException e) {
            System.out.println("Serilizando clase archivos");
            e.printStackTrace();
            // Maneja la excepción según tus necesidades.
        }
    }
    public static ArrayList<Usuario> deserializarListaUsuarios(String nombreArchivo) {
        ArrayList<Usuario> listaUsuarios = null;
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(nombreArchivo))) {
            listaUsuarios = (ArrayList<Usuario>) inputStream.readObject();
            System.out.println("Lista de usuarios deserializada correctamente.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("No serializo");
            e.printStackTrace();
            // Maneja la excepción según tus necesidades.
        }
        return listaUsuarios;
    }
}
