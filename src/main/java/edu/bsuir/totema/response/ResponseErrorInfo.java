package edu.bsuir.totema.response;

import com.google.gson.annotations.Expose;

public class ResponseErrorInfo {

    public ResponseErrorInfo() {

    }
    public ResponseErrorInfo(String error) {
        this.error = error;
    }
    @Expose
    private String error;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
