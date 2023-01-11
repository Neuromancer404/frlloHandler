package com.example.frllohandler;

import com.example.frllohandler.JsonHandler.Downloader;
import com.example.frllohandler.JsonHandler.JSONParser;
import com.example.frllohandler.JsonHandler.ReaderResult;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
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
    private Tab egissoTab;
    @FXML
    private Tab frlloCase1Tab;
    @FXML
    private Tab frlloCase2Tab;
    @FXML
    private Tab frlloCase3Tab;

    @FXML
    void startDownload(MouseEvent event) {
        alert.setText("");
        String fileName = "file";
        if(egissoTab.isSelected()){
            alert.setText("Идет загрузка...");
            download("EGISSO", 0);
        }else if(frlloCase1Tab.isSelected()){
            download("FRLLO1", 1);
        }else if(frlloCase2Tab.isSelected()){
            download("FRLLO2", 2);
        }else if(frlloCase3Tab.isSelected()){
            download("FRLLO3", 3);
        }
    }
    private Downloader downloader;
    private void download(String fileName, int num) {
        LocalDate startDate = startPeriod.getValue();
        LocalDate endDate = endPeriod.getValue();

        if(num != 0){
            fileName = fileName+"_"+startDate+"-"+endDate;
            if(startDate == null && endDate == null){
                alert.setText("Введите дату начала и окончания");
            }
        }

        else{
            JSONParser jsnPrsr = new JSONParser();
            ReaderResult result =  jsnPrsr.parse("config.json");

            downloader = new Downloader();
            try {
                switch (num){
                    case 1:
                        downloader.startDownload(startDate, endDate, result.getfirstStageURL(), fileName);
                        break;
                    case 2:
                        downloader.startDownload(startDate, endDate, result.getSecondStageURL(), fileName);
                        break;
                    case 3:
                        downloader.startDownload(startDate, endDate, result.getThirhStageURL(), fileName);
                        break;
                    case 0:
                        downloader.startDownload(result.getEgissoUrl(), fileName);
                        break;
                }
            } catch (IOException e) {
                alert.setText(e.toString());
                System.out.println(e);
            }
        }
    }
    @FXML
    void killDownload(MouseEvent event) {
        downloader = null;
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