package by.totema.recourse.controller.exception;

import by.totema.recourse.entity.dto.ErrorMessage;
import org.springframework.http.HttpStatus;

public class BadRequestException extends RequestException {
    public BadRequestException() {
        super(HttpStatus.BAD_REQUEST, new ErrorMessage("Error", "Bad request"));
    }
}
