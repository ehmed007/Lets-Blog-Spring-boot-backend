package com.example.BlogApplication.DTOs.Response;

public class ResponseAPI {
    private Object message;
    private String result;

    public ResponseAPI() {
        super();
    }

    public ResponseAPI(Object message, String result) {
        this.message = message;
        this.result = result;
    }

    public Object getMessage() {
        return message;
    }

    public void setMessage(Object message) {
        this.message = message;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
