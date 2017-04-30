package by.totema.recourse.util;

import by.totema.recourse.controller.exception.BadRequestException;
import by.totema.recourse.controller.exception.ControllerException;
import by.totema.recourse.service.exception.ServiceConstraintViolationException;
import by.totema.recourse.service.exception.ServiceException;
import by.totema.recourse.util.WrapperFunctions.WrapperFunction;
import by.totema.recourse.util.WrapperFunctions.WrapperVoidFunction;
import org.slf4j.Logger;

public class ServiceCallWrapper {

    public static <R> R wrapServiceCall(Logger logger, WrapperFunction<R> function) {
        try {
            return function.call();
        } catch (ServiceConstraintViolationException e) {
            logger.trace("Fail to call service because of constraint violation ", e);
            throw new BadRequestException(e);
        } catch (ServiceException e) {
            logger.warn("Fail to call service\n", e);
            throw new ControllerException(e);
        }
    }

    public static void wrapServiceCall(Logger logger, WrapperVoidFunction function) {
        wrapServiceCall(logger, () -> {
            function.call();
            return null;
        });
    }

}
