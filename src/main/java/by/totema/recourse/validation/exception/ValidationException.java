package by.totema.recourse.validation.exception;

import by.totema.recourse.entity.dto.ErrorMessage;

import java.util.Collections;
import java.util.List;

public class ValidationException extends RuntimeException{
    private final List<ErrorMessage> messages;

    public ValidationException(ErrorMessage message) {
        this(Collections.singletonList(message));
    }

    public ValidationException(List<ErrorMessage> messages) {
        this.messages = messages;
    }

    public List<ErrorMessage> getErrorMessages() {
        return messages;
    }
}
