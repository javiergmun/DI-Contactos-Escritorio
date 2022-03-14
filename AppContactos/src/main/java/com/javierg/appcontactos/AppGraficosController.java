package com.javierg.appcontactos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.AnchorPane;

public class AppGraficosController {
    @FXML
    private AnchorPane appGraficos;
    @FXML
    private PieChart pieChart;
    @FXML
    private LineChart<String,Double> lineChart;
    @FXML
    private CategoryAxis ejeX;
    @FXML
    private NumberAxis ejeY;

    //grafico circular
    public void cargarDatosPieChart(ObservableList<Contacto> datosContactos){
        int contadorGeneroFemenino=0;
        for(Contacto c1: datosContactos){
            if(c1.getGenero().equals("Femenino")){
                contadorGeneroFemenino++;
            }
        }
        int contadorGeneroMasculino=0;
        for(Contacto c1: datosContactos){
            if(c1.getGenero().equals("Masculino")){
                contadorGeneroMasculino++;
            }
        }
        int contadorRestante = datosContactos.size() - contadorGeneroFemenino - contadorGeneroMasculino;

        ObservableList<PieChart.Data> datosGraficaCircular= FXCollections.observableArrayList(
                new PieChart.Data("Femenino",contadorGeneroFemenino),
                new PieChart.Data("Masculino",contadorGeneroMasculino),
                new PieChart.Data("Otros",contadorRestante));
        pieChart.setData(datosGraficaCircular);
        //pieChart.setClockwise(false);
        //pieChart.setAnimated(true);
    }
    public void cargarDatosLineChart(){

    }
}