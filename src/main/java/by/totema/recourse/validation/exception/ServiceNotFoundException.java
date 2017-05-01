package by.totema.recourse.validation.exception;

import by.totema.recourse.entity.dto.ErrorMessage;
import org.springframework.http.HttpStatus;

import java.util.List;

public class ServiceNotFoundException extends ServiceRequestException {

    public ServiceNotFoundException(List<ErrorMessage> messages) {
        super(HttpStatus.NOT_FOUND, messages);
    }

    public ServiceNotFoundException(ErrorMessage... message) {
        super(HttpStatus.NOT_FOUND, message);
    }

}
