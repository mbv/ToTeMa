package edu.bsuir.totema.dao.pool.exception;

import edu.bsuir.totema.dao.pool.ConnectionPool;

/**
 * Exception which represents that {@link ConnectionPool} cannot work or even been created.
 */
public class FatalPoolException extends RuntimeException {

    public FatalPoolException() {
        super();
    }

    public FatalPoolException(String cause) {
        super(cause);
    }

    public FatalPoolException(Throwable t) {
        super(t);
    }

    public FatalPoolException(String cause, Throwable t) {
        super(cause, t);
    }
}