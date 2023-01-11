package com.example.frllohandler;

import com.example.frllohandler.JsonHandler.JSONParser;
import com.example.frllohandler.JsonHandler.ReaderResult;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class ConfigWindowController {
    @FXML
    private TextField DownloadEGISSO;

    @FXML
    private TextField DowmloadAppointmentAdress;

    @FXML
    private TextField DownloadReleaseAdress;

    @FXML
    private TextField downloadBenefitAdress;

    @FXML
    private Label alertLabel;
    Map<String, String> config;
    @FXML
    void SaveConfigBtnClick(MouseEvent event) {
        if(checkFields()){
            alertLabel.setText("");
            config = getConfig();
            JSONParser jsnParser = new JSONParser();
            jsnParser.writeConfig(config);
        }
        else{
            alertLabel.setText("Заполните все поля");
        }
    }

    private boolean checkFields() {
        if(
                DowmloadAppointmentAdress.getText().length() > 0 && DownloadReleaseAdress.getText().length() > 0
                && downloadBenefitAdress.getText().length() > 0 && DownloadEGISSO.getText().length() > 0
        ){
            return true;
        }else{
            return false;
        }
    }

    private Map<String, String> getConfig() {//Получить значения из полей
        Map<String, String> conf = new HashMap<>();
        conf.put("DowmloadAppointmentAdress", DowmloadAppointmentAdress.getText());
        conf.put("DownloadReleaseAdress", DownloadReleaseAdress.getText());
        conf.put("downloadBenefitAdress", downloadBenefitAdress.getText());
        conf.put("downloadEGISSO", DownloadEGISSO.getText());
        return conf;
    }

    @FXML
    void initialize() {
        setConfig();
    }

    private void setConfig() {
        JSONParser jsnPrsr = new JSONParser();
        ReaderResult result =  jsnPrsr.parse("config.json");
        if(result.getParserStatus() == true){
            Map<String, String> config = jsnPrsr.getConfig();
            DowmloadAppointmentAdress.setText(config.get("DowmloadAppointmentAdress"));
            DownloadReleaseAdress.setText(config.get("DownloadReleaseAdress"));
            downloadBenefitAdress.setText(config.get("downloadBenefitAdress"));
            DownloadEGISSO.setText(config.get("downloadEGISSO"));
        }
    }
}
