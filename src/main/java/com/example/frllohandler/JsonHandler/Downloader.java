package com.example.frllohandler.JsonHandler;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Downloader {
    private String startPeriodDate;
    private String endPeriodDate;

    public void startDownload(LocalDate startDate, LocalDate endDate, String url, String fileName) throws IOException {
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
}
