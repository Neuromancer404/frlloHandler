package com.example.frllohandler;

import com.example.frllohandler.JsonHandler.Downloader;
import com.example.frllohandler.JsonHandler.JSONParser;
import com.example.frllohandler.JsonHandler.ReaderResult;
import com.example.frllohandler.JsonHandler.xmlReader;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;

public class HelloController {
    @FXML
    private Label alert;
    @FXML
    private ResourceBundle resources;
    @FXML
    private DatePicker endPeriod;
    @FXML
    private Button downloadBtn1;
    @FXML
    private Button downloadBtn2;
    @FXML
    private Button downloadBtn3;
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
    private Button downloadBtn;

    @FXML
    private TextField fileTextbox;
    @FXML
    void startDownload(MouseEvent event) {
        alert.setText("");
        String fileName = "file";
        if(egissoTab.isSelected()){
            alert.setText("Идет загрузка...");
            download("EGISSO", 0);
        }else if(frlloCase1Tab.isSelected()){
            alert.setText("Идет загрузка...");
            download("FRLLO1", 1);
        }else if(frlloCase2Tab.isSelected()){
            alert.setText("Идет загрузка...");
            download("FRLLO2", 2);
        }else if(frlloCase3Tab.isSelected()){
            alert.setText("Идет загрузка...");
            download("FRLLO3", 3);
        }
    }
    private Downloader downloader = new Downloader();;
    private void download(String fileName, int num) {
        LocalDate startDate = startPeriod.getValue();
        LocalDate endDate = endPeriod.getValue();
        alert.setText("");
        if(num != 0){
            fileName = fileName+"_"+startDate+"-"+endDate;
        }

        else{
            JSONParser jsnPrsr = new JSONParser();
            ReaderResult result =  jsnPrsr.parse("config.json");

            try {
                String file=null;
                switch (num){
                    case 1:
                        if(startDate == null && endDate == null){
                            alert.setText("Введите дату начала и окончания");
                        }
                        else{
                            file = downloader.startDownload(startDate, endDate, result.getfirstStageURL(), fileName);
                            if(file.contains(".xml")){
                                alert.setText("Загрузка завершена");
                            }
                            blockButtons(true);
                        }

                        break;
                    case 2:
                        if(startDate == null && endDate == null){
                            alert.setText("Введите дату начала и окончания");
                        }
                        else {
                            file = downloader.startDownload(startDate, endDate, result.getSecondStageURL(), fileName);
                            if (file.contains(".xml")) {
                                alert.setText("Загрузка завершена");
                            }
                            blockButtons(true);
                        }
                        break;
                    case 3:
                        file = downloader.startDownload(startDate, endDate, result.getThirhStageURL(), fileName);
                        if(file.contains(".xml")){
                            alert.setText("Загрузка завершена");
                        }
                        break;
                    case 0:
                        file = downloader.startDownload(result.getEgissoUrl(), fileName);
                        if(file.contains(".xml")){
                            alert.setText("Загрузка завершена");
                        }
                        fileTextbox.setText(file);
                        break;
                }
                blockButtons(false);
            } catch (IOException e) {
                alert.setText(e.toString());
                System.out.println(e);
            } catch (ExecutionException e) {
                alert.setText(e.toString());
                throw new RuntimeException(e);
            } catch (InterruptedException e) {
                alert.setText(e.toString());
                throw new RuntimeException(e);
            }
        }
    }
    private void blockButtons(boolean sts){
        downloadBtn1.setDisable(sts);
        downloadBtn2.setDisable(sts);
        downloadBtn3.setDisable(sts);
        downloadBtn.setDisable(sts);
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

    @FXML
    void startParsing(MouseEvent event) {
        String file = fileTextbox.getText();
        xmlReader xmlRdr = new xmlReader();
        try {
            if(xmlRdr.Create(file, "fact")>0){
                List<Map<String, String>> data = xmlRdr.Read("fact");
                for(Map<String, String> item : data){
                    for(String key : item.keySet()){
                        System.out.println(key+" : "+item.get(key));
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}