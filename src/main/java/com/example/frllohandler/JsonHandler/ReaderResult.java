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

    private String secondStageURL;
    public String getSecondStageURL(){return secondStageURL;}
    public void setSecondStageURL(String url){secondStageURL = url;}

    private String thirhStageURL;
    public String getThirhStageURL(){return thirhStageURL;}
    public void setThirhStageURL(String url){thirhStageURL = url;}

    private String egissoUrl;
    public String getEgissoUrl(){return egissoUrl;}
    public void setEgissoUrl(String url){egissoUrl = url;}
}
