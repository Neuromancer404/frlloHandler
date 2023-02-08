package com.example.frllohandler.JsonHandler;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class Downloader{
    private String startPeriodDate;
    private String endPeriodDate;

    public String startDownload(LocalDate startDate, LocalDate endDate, String url, String fileName) throws IOException, ExecutionException, InterruptedException {
        startPeriodDate = dateToString(startDate);
        endPeriodDate = dateToString(endDate);

        URL web = null;
        try {
            web = new URL(url+"/"+fileName+".xml?begin_date="+startPeriodDate+"&end_date="+endPeriodDate);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }

        ReadableByteChannel rbc = null;
        try {
            rbc = Channels.newChannel(web.openStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(fileName+"_"+startDate+"-"+endDate+".xml");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
            fos.close();
            rbc.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return fileName+"_"+startDate+"-"+endDate+".xml";
    }

    private String dateToString(LocalDate Date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String date = Date.format(formatter);
        return date;
    }

    public String startDownload(String url, String fileName) throws IOException, ExecutionException, InterruptedException {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            URL web = null;
            try {
                web = new URL(url);
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            }

            ReadableByteChannel rbc = null;
            try {
                rbc = Channels.newChannel(web.openStream());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            FileOutputStream fos = null;
            try {
                fos = new FileOutputStream(fileName+".xml");
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            try {
                fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
                fos.close();
                rbc.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return fileName+".xml";
        });
        String result = future.get();
        return result;
    }
}
