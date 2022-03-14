package com.javierg.appcontactos;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class AppDetalleController {
    //controlador vista anidada
    @FXML
    private VBox vboxDatos;
    @FXML
    private AnchorPane appDetalle;
    @FXML
    private Label nombreContacto, edadContacto, generoContacto, numeroContacto;
    @FXML
    private TextField rolContacto;

    public void cargarContactoDetalle(Contacto contacto){
        nombreContacto.setText(contacto.getNombre());
        edadContacto.setText(String.valueOf(contacto.getEdad()));
        generoContacto.setText(contacto.getGenero());
        numeroContacto.setText(contacto.getNumero());
        rolContacto.setText(contacto.getRol());
    }
}