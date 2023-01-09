package com.example.frllohandler;

import com.example.frllohandler.JsonHandler.Downloader;
import com.example.frllohandler.JsonHandler.JSONParser;
import com.example.frllohandler.JsonHandler.ReaderResult;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class HelloController {
    @FXML
    private Label alert;
    @FXML
    private ResourceBundle resources;
    @FXML
    private DatePicker endPeriod;

    @FXML
    private DatePicker startPeriod;
    @FXML
    private URL location;
    @FXML
    void startDownload(MouseEvent event) {
        alert.setText("");
        LocalDate startDate = startPeriod.getValue();
        LocalDate endDate = endPeriod.getValue();

        if(startDate == null && endDate == null){
            alert.setText("Введите дату начала и окончания");
        }
        else{
            JSONParser jsnPrsr = new JSONParser();
            ReaderResult result =  jsnPrsr.parse("config.json");

            Downloader downloader = new Downloader();
            try {
                downloader.startDownload(startDate, endDate, result.getfirstStageURL(), "FRLLO");
            } catch (IOException e) {
                alert.setText(e.toString());
                System.out.println(e);
            }
        }
    }
    @FXML
    void openSettingsBtnClick(MouseEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("configWindow.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            stage.setTitle("Настройки");
            stage.setScene(scene);
            stage.show();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public HelloController() {
    }

    @FXML
    void initialize() {

    }


}