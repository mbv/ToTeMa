package by.totema.recourse.controller.exception;

import by.totema.recourse.entity.dto.ErrorMessage;
import org.springframework.http.HttpStatus;

import java.util.List;

public class BadRequestException extends RequestException {
    public BadRequestException() {
        super(HttpStatus.BAD_REQUEST, new ErrorMessage("Error", "Bad request"));
    }

    public BadRequestException(List<ErrorMessage> errors) {
                super(HttpStatus.BAD_REQUEST, errors);
           }
}
