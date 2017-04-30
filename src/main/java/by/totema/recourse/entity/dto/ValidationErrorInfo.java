package by.totema.recourse.entity.dto;

import java.util.List;

public class ValidationErrorInfo {
    private String error;

    private int status;

    private List<ErrorMessage> errors;

    public ValidationErrorInfo(String error, int status, List<ErrorMessage> errors) {
        this.error = error;
        this.status = status;
        this.errors = errors;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<ErrorMessage> getErrors() {
        return errors;
    }

    public void setErrors(List<ErrorMessage> errors) {
        this.errors = errors;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

}
