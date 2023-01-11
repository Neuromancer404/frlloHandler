package com.example.frllohandler.JsonHandler;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Downloader{
    private String startPeriodDate;
    private String endPeriodDate;

    public void startDownload(LocalDate startDate, LocalDate endDate, String url, String fileName) throws IOException {
        System.out.println(url);
        startPeriodDate = dateToString(startDate);
        endPeriodDate = dateToString(endDate);

        URL web = new URL(url+"/"+fileName+".xml?begin_date="+startPeriodDate+"&end_date="+endPeriodDate);

        ReadableByteChannel rbc = Channels.newChannel(web.openStream());
        FileOutputStream fos = new FileOutputStream(fileName+".xml");
        fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
    }

    private String dateToString(LocalDate Date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String date = Date.format(formatter);
        return date;
    }

    public void startDownload(String url, String fileName) throws IOException {
        Thrd thrd = new Thrd(url+"/"+fileName+".xml?begin_date="+startPeriodDate+"&end_date="+endPeriodDate, fileName);
        thrd.start();
    }
}

class Thrd extends Thread{
    private String url;
    private String fileName;
    Thrd(String url, String fileName){
        this.url = url;
        this.fileName = fileName;
    }

    @Override
    public void run() {
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
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
