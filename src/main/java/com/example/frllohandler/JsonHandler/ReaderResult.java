package com.example.frllohandler.JsonHandler;

public class ReaderResult {
    private boolean ParserStatus;

    public boolean getParserStatus() {
        return ParserStatus;
    }

    public void setParserStatus(boolean parserStatus) {
        ParserStatus = parserStatus;
    }

    private String firstStageURL;
    public String getfirstStageURL(){return firstStageURL;}
    public void setFirstStageURL(String url){firstStageURL = url;}
}
