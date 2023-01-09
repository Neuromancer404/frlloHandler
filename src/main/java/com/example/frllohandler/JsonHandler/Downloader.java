package com.example.frllohandler.JsonHandler;

import java.io.FileOutputStream;
import java.io.IOException;
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

        URL web = new URL(url+"/"+"fileName"+".xml?begin_date="+startPeriodDate+"&end_date="+endPeriodDate);
        ReadableByteChannel readableByteChannel = Channels.newChannel(web.openStream());
        FileOutputStream fileOutputStream = new FileOutputStream(fileName+".xml");
        FileChannel fileChannel = fileOutputStream.getChannel();
        fileOutputStream.getChannel().transferFrom(readableByteChannel, 0, Long.MAX_VALUE);

    }

    private String dateToString(LocalDate Date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String date = Date.format(formatter);
        return date;
    }
}
