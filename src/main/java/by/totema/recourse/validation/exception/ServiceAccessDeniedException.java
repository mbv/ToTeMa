package by.totema.recourse.validation.exception;

import by.totema.recourse.entity.dto.ErrorMessage;

public class ServiceAccessDeniedException extends ValidationException {
    public ServiceAccessDeniedException(ErrorMessage message) {
        super(message);
    }
}
