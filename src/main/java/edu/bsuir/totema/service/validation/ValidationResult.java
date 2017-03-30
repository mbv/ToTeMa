package edu.bsuir.totema.service.validation;

import com.google.gson.annotations.Expose;

import java.util.HashMap;

public class ValidationResult {
    @Expose
    private String error;
    @Expose
    private HashMap<String, String> errors;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public HashMap<String, String> getErrors() {
        return errors;
    }

    public void setErrors(HashMap<String, String> errors) {
        this.errors = errors;
    }
}
