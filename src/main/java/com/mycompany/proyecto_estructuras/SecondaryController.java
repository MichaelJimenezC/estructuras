package com.mycompany.proyecto_estructuras;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class SecondaryController {

    @FXML
    private void switchToPrimary() throws IOException {
       
    }
    @FXML
    private void MenuLogin(ActionEvent event) throws IOException{
     App.setRoot("Menu");
    }
    
}