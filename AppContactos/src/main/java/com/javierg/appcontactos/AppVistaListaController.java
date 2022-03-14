package com.javierg.appcontactos;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

public class AppVistaListaController {
    @FXML
    private AnchorPane appVistaLista;
    @FXML
    private TableView tabla1;
    @FXML
    private TableColumn<Contacto,String>columnaNombre,columnaTlf;

    public void cargarTabla(ObservableList<Contacto> datosContactos){
        tabla1.setEditable(true);
        columnaNombre.setCellValueFactory(new PropertyValueFactory<>("Nombre"));
        columnaTlf.setCellValueFactory(new PropertyValueFactory<>("Numero"));
        //modificar campo de tabla
        columnaTlf.setCellFactory(TextFieldTableCell.forTableColumn());
        columnaTlf.setOnEditCommit(data ->{
            if(data != null){
                data.getRowValue().setNumero(data.getNewValue());
            }
        });
        tabla1.setItems(datosContactos);
    }

}