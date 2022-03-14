package com.javierg.appcontactos;

import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import org.json.*;


import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.net.http.*;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    //animacion
    private boolean desplegado;
    private TranslateTransition animationMenu, animationBtnMenu;

    //TODOS LOS ELEMENTOS DE LA VISTA Y MAS VISTAS
    @FXML
    private VBox menu;//menu tiene que casar con el ID
    @FXML
    private Button btnMenu;//boton menu
    @FXML
    private Button btnAbrir;
    @FXML
    private Button btnVerDetalle;
    @FXML
    private Button btnVerTabla;
    @FXML
    private Button btnVerGraficos;
    @FXML
    private Button btnSalir;
    @FXML
    private Button btnInfo;
    @FXML
    private AnchorPane appDetalle, appVistaLista,appGraficos;
    @FXML
    private AppDetalleController appDetalleController;
    @FXML
    private AppVistaListaController appVistaListaController;
    @FXML
    private AppGraficosController appGraficosController;


    @FXML
    private ListView<Contacto> listaContactos;
    private ObservableList<Contacto> datosContactos;

    @FXML
    private ListView<Personaje> listaContactosAPI;
    private ObservableList<Personaje> datosContactosAPI;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //MENU
        menu.setTranslateX(-70); //lo mandamos fuera
        desplegado=false;
        //vistas
        appDetalle.setVisible(false);
        appDetalle.setTranslateX(600);
        appVistaLista.setVisible(false);
        appVistaLista.setTranslateX(600);
        appGraficos.setVisible(false);
        appGraficos.setTranslateX(600);
        //LISTA CONTACTOS

        datosContactos= FXCollections.observableArrayList();
        rellenarLista();
        listaContactos.setItems(datosContactos);
        listaContactos.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        listaContactos.getSelectionModel().selectedItemProperty().addListener((observable,oldValue,newValue)->{
            if(newValue !=null){
                appDetalleController.cargarContactoDetalle(newValue);
                //cargarVistaDetalle(); //PARA VER SI ME MANDA DIRECTO O SOLO AL SELECCIONAR DIGO SI QUIERO VER
            }
        });

        //LISTA API
        datosContactosAPI= FXCollections.observableArrayList();
        rellenarListaAPI();
        listaContactosAPI.setItems(datosContactosAPI);
        listaContactosAPI.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        /*
        listaContactosAPI.getSelectionModel().selectedItemProperty().addListener((observable,oldValue,newValue)->{
            if(newValue!=null){
                appDetalleController.cargarContacto(newValue);
                vistaDetalle();
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Informacion");
                alert.setContentText("Vas a abrir un personaje");
                alert.showAndWait();
            }
        });

         */

    }
    @FXML
    private void cargarVistaDetalle(){
        appDetalle.setVisible(true);
        appDetalle.setTranslateX(100);

    }
    @FXML
    private void cargarVistaGraficos(){
        appGraficos.setVisible(true);
        appGraficos.setTranslateX(100);
        appGraficosController.cargarDatosPieChart(datosContactos);

    }
    @FXML //TABLA
    private void cargarVistaLista(){
        appVistaLista.setVisible(true);
        appVistaLista.setTranslateX(100);
        appVistaListaController.cargarTabla(datosContactos);
    }
    @FXML
    private void volverPrincipal(){
        appDetalle.setVisible(false);
        appDetalle.setTranslateX(600);
        appVistaLista.setVisible(false);
        appVistaLista.setTranslateX(600);
        appGraficos.setVisible(false);
        appGraficos.setTranslateX(600);
    }

    private void rellenarListaAPI() {
        int numRandom = (int) Math.floor(Math.random()*42 +1);

        Runnable task =() -> {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://rickandmortyapi.com/api/character?page=" + numRandom))
                    .build();
            HttpResponse<String> response = null;
            try {
                response = client.send(request, HttpResponse.BodyHandlers.ofString());
                Platform.runLater(()-> listaContactosAPI.getItems().removeAll(datosContactosAPI));

                JSONObject responseObject = new JSONObject(response.body());
                JSONArray dataArray = new JSONArray(responseObject.getJSONArray("results"));
                for ( int i=0; i<dataArray.length();i++){
                    JSONObject row = dataArray.getJSONObject(i);
                    Platform.runLater(()-> datosContactosAPI.add(new Personaje(row.getInt("id"),
                            row.getString("image"),
                            row.getString("name"),
                            row.getString("status"),
                            row.getString("species"),
                            row.getString("gender"))));
                }
            } catch (IOException | InterruptedException e){
                e.printStackTrace();
            }
        };
        new Thread(task).start();
    }

    private void rellenarLista() {
        datosContactos = FXCollections.observableArrayList(
                new Contacto("Javier","619805372",21,"Masculino","Jefe"),
                new Contacto("Daniel","628192738",20,"Masculino","Jefe"),
                new Contacto("Alba","643827903",20,"Femenino","Jefe"),
                new Contacto("Jose Luis","629501235",40,"Masculino","Profesor"),
                new Contacto("Teresa","639576109",56,"Femenino","Profesor")
        );
    }

    //metodos a hacer
    @FXML
    private void desplegarMenu(){
        //creamos la animacion y la asociamos a un boton/layaout
        animationMenu = new TranslateTransition(Duration.millis(700), menu);
        //animationBtnMenu = new TranslateTransition(Duration.millis(500), btnMenu);

        if(!desplegado){
            //si esta desplegado , tomamos desde donde este y lo movemos a 0
            animationMenu.setFromX(-menu.getWidth());//se desplaza en x lo que mida
            animationMenu.setToX(0);
            //animationBtnMenu.setFromX(0);
            //animationBtnMenu.setToX(70);
            desplegado=true;
            btnMenu.setText("Cerrar Menu");
        }else{
            animationMenu.setFromX(0);
            animationMenu.setToX(-menu.getWidth());//se desplaza en x lo que mida
            //animationBtnMenu.setFromX(70);
            //animationBtnMenu.setToX(0);
            desplegado=false;
            btnMenu.setText("Abrir Menu");
        }
        animationMenu.play();
        //animationBtnMenu.play();
    }
    @FXML
    private void mostrarInfo(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Informacion del programa");
        alert.setTitle("Preferencias");
        alert.setContentText("Idioma: Inglés  \n" +
                "Version: 1.0\n" +
                "Creador: Javier González Muñoz\n");
        alert.showAndWait();
    }
    @FXML
    private void salir(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setContentText("Vas a salir de la aplicacion...");
        alert.showAndWait();
        System.exit(0);
    }
}