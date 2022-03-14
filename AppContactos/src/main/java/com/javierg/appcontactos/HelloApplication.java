package com.javierg.appcontactos;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        //internacionalizar
        Locale spanish = new Locale("es","ES");
        Locale english = new Locale("en","UK");
        ResourceBundle idioma = ResourceBundle.getBundle("i18n/strings",english);

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"),idioma);
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("Practica Examen");
        stage.setScene(scene);
        stage.setMinHeight(400);
        stage.setMinWidth(600);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}