package com.example.mobilestudiolaptop003.placefinder.model;

public class ApiFourSquareResponse {
    private Meta meta;
    private ResponseF response;

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
}
