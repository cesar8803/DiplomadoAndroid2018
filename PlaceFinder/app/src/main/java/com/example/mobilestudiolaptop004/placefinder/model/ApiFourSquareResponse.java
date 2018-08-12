package com.example.mobilestudiolaptop004.placefinder.model;

public class ApiFourSquareResponse {
    private Meta meta;

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public ResponseF getResponse() {
        return response;
    }

    public void setResponse(ResponseF response) {
        this.response = response;
    }

    private ResponseF response;
}
