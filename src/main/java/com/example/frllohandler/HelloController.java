package com.example.frllohandler;

import com.example.frllohandler.FrlloTools.*;
import com.example.frllohandler.JsonHandler.*;
import com.example.frllohandler.JsonHandler.persons.egissoModifiedPerson;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static com.example.frllohandler.FrlloTools.FrlloCase1Handler.frlloCase1HandlerResult;

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
    private Button writeToDataBaseBtn;
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
            download("FRLLO", 1);
        }else if(frlloCase2Tab.isSelected()){
            alert.setText("Идет загрузка...");
            download("FRLLO1", 2);
        }else if(frlloCase3Tab.isSelected()){
            alert.setText("Идет загрузка...");
            download("FRLLO2", 3);
        }
    }
    private Downloader downloader = new Downloader();;
    private void download(String fileName, int num) {
        LocalDate startDate = startPeriod.getValue();
        LocalDate endDate = endPeriod.getValue();
        alert.setText("");
        if(num != 0){
            if(startDate == null || endDate == null){
                alert.setText("Введите дату");
                return;
            }
        }
        JSONParser jsnPrsr = new JSONParser();
        ReaderResult result =  jsnPrsr.parse("config.json");
        try {
            alert.setText("Идет загрузка");
            String finalFileName = fileName;
            CompletableFuture<FrlloCase1HandlerResult> future = CompletableFuture.supplyAsync(() -> {
                FrlloCase1Handler handler = null;
                handler = new FrlloCase1Handler(
                        startPeriod,
                        endPeriod,
                        result.getfirstStageURL(),
                        finalFileName);
                return frlloCase1HandlerResult;
            });
            FrlloCase1HandlerResult futRes = future.get();
            if(futRes.error == null){
                alert.setText("Загрузка завершена");
                ReadFileBtn.setDisable(false);
                //description11.setText("Файл: "+futRes.downloadedFile);
                inputFileName.setText(futRes.downloadedFile);

            }else {
                alert.setText("Downloader: "+futRes.error);
            }

        } catch (ExecutionException e) {
            alert.setText(e.toString());
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            alert.setText(e.toString());
            throw new RuntimeException(e);
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

    @FXML
    private TextField inputFileName;
    private FrlloCase1ParserResult frlloCase1ParserResult;
    @FXML
    void ReadDownloadFile(MouseEvent event) {
        alert.setText("");
        frlloCase1ParserResult = new FrlloCase1ParserResult();
        CompletableFuture<FrlloCase1ParserResult> future = CompletableFuture.supplyAsync(() -> {
            FrlloCase1Parser frlloCase1Parser = new FrlloCase1Parser(inputFileName.getText());
            return frlloCase1Parser.getFrlloCase1ParserResult();
        });
        try {
            FrlloCase1ParserResult res = future.get();
            if(res.error == null){
                description21.setText("Количество записей: "+res.getNodeCount());
                alert.setText("Записи прочитаны.");
            }else{
                alert.setText(res.error);
            }
        } catch (InterruptedException e) {
            alert.setText(e.getMessage());
        } catch (ExecutionException e) {
            alert.setText(e.getMessage());
        }
        //writeToDataBaseBtn.setDisable(false);
        dadataCheckBtn.setDisable(false);
    }

    @FXML
    void dadataCheckBtnClick(MouseEvent event) {
        DadataAdressParserResult result;
        CompletableFuture<DadataAdressParserResult> future = CompletableFuture.supplyAsync(() -> {
            DadataAdressParser dadataAdressParser = new DadataAdressParser(frlloCase1HandlerResult);
            return dadataAdressParser.result;
        });
        try {
            result = future.get();
            if(result.error == null){

            }else{
                alert.setText(result.error);
            }
        } catch (InterruptedException e) {
            alert.setText(e.getMessage());
        } catch (ExecutionException e) {
            alert.setText(e.getMessage());
        }
    }
    @FXML
    void initialize() {
        ReadFileBtn.setDisable(true);
        writeToDataBaseBtn.setDisable(true);
        dadataCheckBtn.setDisable(true);
    }
    @FXML
    private Button ReadFileBtn;
    @FXML
    private Button dadataCheckBtn;
    @FXML
    private Label description1;
    @FXML
    private Label description2;
    @FXML
    private Label description3;
    @FXML
    private Label description4;
    @FXML
    private Label description5;
    @FXML
    private Label description6;
    @FXML
    private Label description11;
    @FXML
    private Label description21;
    @FXML
    private Label description31;
    @FXML
    private Label description41;
    @FXML
    private Label description51;
    @FXML
    private Label description61;
    @FXML
    private Button deleteLastMonth;
    private List<egissoModifiedPerson> EgissoPersonList;
    private List<egissoModifiedPerson> lastMonthPersonList;
    @FXML
    void startParsing(MouseEvent event) throws FileNotFoundException {
        /*try {
            egissoParser parser = new egissoParser(fileTextbox.getText());
            description1.setText("Количество записей "+parser.getNodeCount());
            this.personList = parser.modPersonList;
        }catch (FileNotFoundException ex){
            alert.setText(ex.toString());
        }*/
    }
    @FXML
    private TextField lastMonthFile;
    @FXML
    void deleteLastMonth(MouseEvent event) throws IOException {
        /*alert.setText("");
        if(description1.getText().length() == 0){
            startParsing(event);
        }
        if(lastMonthFile.getText().length()>0){
            try{
                egissoParser parser = new egissoParser(lastMonthFile.getText());
                description2.setText("Количество записей прошлого месяца: "+ parser.getNodeCount());
                lastMonthPersonList = parser.modPersonList;
                if(lastMonthFile.getText().contains(".xml")){
                    personsHandler();
                }
                else if(lastMonthFile.getText().contains(".csv")){
                    personsHandler(parser.modedPersonList);
                }
                else{
                    lastMonthFile.setText("");
                }
            }
            catch (IOException ex){
                alert.setText(ex.toString());
            }
        }
        else{
            alert.setText("Укажите файл прошлого месяца");
        }*/
    }

   /* private void personsHandler(List<egissoModifiedPerson> modedPersonList) {
        ListComparison listComparison = new ListComparison(personList, modedPersonList);
        description3.setText("Количество совпавших записей: "+listComparison.matchesNum);
        description4.setText("Количество новых записей: "+listComparison.newPersonsCount);
    }*/

    /*private void personsHandler() {
        ListComparison listComparison = new ListComparison(personList, lastMonthPersonList);
        description3.setText("Количество совпавших записей: "+listComparison.matchesNum);
        description4.setText("Количество новых записей: "+listComparison.newPersonsCount);
    }*/
}