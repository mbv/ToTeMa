package by.totema.recourse.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Error in request")
public class BadRequestException extends ControllerException {
    public BadRequestException() {
    }

    public BadRequestException(String cause) {
        super(cause);
    }

    public BadRequestException(Throwable t) {
        super(t);
    }

    public BadRequestException(String cause, Throwable t) {
        super(cause, t);
    }
}
