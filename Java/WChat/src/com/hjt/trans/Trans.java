package com.hjt.trans;

public class Trans {
    private String from;
    private String to;
    private TransResult transResult;

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public TransResult getTransResult() {
        return transResult;
    }

    public void setTransResult(TransResult transResult) {
        this.transResult = transResult;
    }
}
