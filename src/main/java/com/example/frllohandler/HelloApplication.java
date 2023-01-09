package com.example.frllohandler;

import com.example.frllohandler.JsonHandler.JSONParser;
import com.example.frllohandler.JsonHandler.ReaderResult;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Map;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("startWindow.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        stage.setTitle("Обработчик ФРЛЛО");
        stage.setScene(scene);
        stage.show();

        readConfig();
    }

    private void readConfig() {
        JSONParser jsnPrsr = new JSONParser();
        ReaderResult result =  jsnPrsr.parse("config.json");
        if(result.getParserStatus() == false){
            openConfig("Не удалось прочитать конфигурацию.");
        }
    }

    private void openConfig(String title) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("configWindow.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            stage.setTitle(title);
            stage.setScene(scene);
            stage.show();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        launch();
    }
}