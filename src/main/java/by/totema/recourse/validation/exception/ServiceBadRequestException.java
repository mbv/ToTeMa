package by.totema.recourse.validation.exception;

import by.totema.recourse.entity.dto.ErrorMessage;
import org.springframework.validation.BindingResult;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ServiceBadRequestException extends ValidationException {
    public ServiceBadRequestException(BindingResult bindingResult) {
        super(bindingResult
                .getFieldErrors()
                .stream()
                .map(ErrorMessage::fromFieldError)
                .collect(Collectors.toList()));
    }

    public ServiceBadRequestException(List<ErrorMessage> errorMessages) {
        super(errorMessages);
    }

    public ServiceBadRequestException(ErrorMessage... errorMessages) {
        super(Arrays.asList(errorMessages));
    }
}
