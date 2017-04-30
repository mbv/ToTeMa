package by.totema.recourse.validation.exception;

import by.totema.recourse.entity.dto.ErrorMessage;

import java.util.List;

public class ServiceNotFoundException extends ValidationException {
    public ServiceNotFoundException(List<ErrorMessage> messages) {
        super(messages);
    }
}
