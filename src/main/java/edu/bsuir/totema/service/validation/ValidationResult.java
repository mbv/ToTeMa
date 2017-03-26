package edu.bsuir.totema.service.validation;

import com.google.gson.annotations.Expose;

import java.util.HashMap;

public class ValidationResult {
    @Expose
    private String status;
    @Expose
    private HashMap<String, String> errors;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public HashMap<String, String> getErrors() {
        return errors;
    }

    public void setErrors(HashMap<String, String> errors) {
        this.errors = errors;
    }
}
