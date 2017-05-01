package by.totema.recourse.service.exception;

import by.totema.recourse.entity.dto.ErrorMessage;
import by.totema.recourse.validation.exception.ServiceRequestException;
import org.springframework.http.HttpStatus;

import java.util.List;

public class ServiceConstraintViolationException extends ServiceRequestException {

    public ServiceConstraintViolationException(ErrorMessage... message) {
        super(HttpStatus.BAD_REQUEST, message);
    }

    public ServiceConstraintViolationException(List<ErrorMessage> messages) {
        super(HttpStatus.BAD_REQUEST, messages);
    }
}
