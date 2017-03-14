package edu.bsuir.totema.model;

public class ModelException extends Exception {
    public ModelException() {
        super();
    }

    public ModelException(String cause) {
        super(cause);
    }

    public ModelException(Throwable t) {
        super(t);
    }

    public ModelException(String cause, Throwable t) {
        super(cause, t);
    }
}
