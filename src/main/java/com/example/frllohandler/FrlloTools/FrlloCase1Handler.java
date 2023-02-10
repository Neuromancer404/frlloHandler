package com.example.frllohandler.FrlloTools;

import com.example.frllohandler.JsonHandler.Downloader;
import javafx.scene.control.DatePicker;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class FrlloCase1Handler {
    public FrlloCase1Handler(DatePicker startPeriod, DatePicker endPeriod, String url, String fileName) {
        Downloader downloader = new Downloader();
        try {
            String dnldFile = downloader.startDownload(startPeriod.getValue(), endPeriod.getValue(), url, fileName);
            frlloCase1HandlerResult = new FrlloCase1HandlerResult();
            frlloCase1HandlerResult.downloadedFile = dnldFile;
        } catch (IOException e) {
            gfy(e.getMessage());
        } catch (ExecutionException e) {
            gfy(e.getMessage());
        } catch (InterruptedException e) {
            gfy(e.getMessage());
        }
    }
    public static FrlloCase1HandlerResult frlloCase1HandlerResult;
    private void gfy(String error) {
        frlloCase1HandlerResult = new FrlloCase1HandlerResult();
        frlloCase1HandlerResult.error = error;
    }
}

