package by.totema.recourse.controller.impl;

import by.totema.recourse.configuration.security.Auth;
import by.totema.recourse.configuration.security.EmployeeAuthDetails;
import by.totema.recourse.controller.CrudController;
import by.totema.recourse.controller.exception.AccessDeniedException;
import by.totema.recourse.controller.exception.BadRequestException;
import by.totema.recourse.controller.exception.NotFoundException;
import by.totema.recourse.entity.model.BaseEntity;
import by.totema.recourse.service.CrudService;
import org.slf4j.Logger;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.Optional;
import java.util.function.BiFunction;

import static by.totema.recourse.util.ServiceCallWrapper.wrapServiceCall;

public abstract class AbstractCrudController<E extends BaseEntity<ID>, ID> implements CrudController<E,ID> {

    private final Logger logger;
    private final CrudService<E, ID> crudService;

    AbstractCrudController(CrudService<E, ID> crudService, Logger logger) {
        this.logger = logger;
        this.crudService = crudService;
    }

    protected abstract boolean hasAuthorityToEdit(E entity, EmployeeAuthDetails authDetails);

    protected boolean hasAuthorityToRead(E entity, EmployeeAuthDetails authDetails) {
        return true;
    }

    protected void checkAuthority(E entity, EmployeeAuthDetails authDetails, BiFunction<E, EmployeeAuthDetails, Boolean> authorityChecker) {
        if (!authDetails.isAdmin() && !authorityChecker.apply(entity, authDetails)) {
            throw new AccessDeniedException();
        }
    }

    @Override
    public E getById(@PathVariable("id") ID id, @Auth EmployeeAuthDetails employeeAuthDetails) {
        return wrapServiceCall(logger, () -> {
            Optional<E> callResult = crudService.findById(id);
            if (callResult.isPresent()){
                checkAuthority(callResult.get(), employeeAuthDetails, this::hasAuthorityToRead);
                return callResult.get();
            } else {
                throw new NotFoundException();
            }
        });
    }

    public Iterable<E> getAll(@Auth EmployeeAuthDetails authDetails) {
        return wrapServiceCall(logger, crudService::findAll);
    }

    @Override
    public <S extends E> S create(@Valid @RequestBody S entity, @Auth EmployeeAuthDetails authDetails) {
        checkAuthority(entity, authDetails, this::hasAuthorityToEdit);
        return wrapServiceCall(logger, () -> {
            Optional<S> callResult = crudService.add(entity);
            return callResult.orElseThrow(BadRequestException::new);
        });
    }

    @Override
    public E update(@Valid @RequestBody E entity, @PathVariable("id") ID id, @Auth EmployeeAuthDetails authDetails) {
        checkAuthority(entity, authDetails, this::hasAuthorityToEdit);
        return wrapServiceCall(logger, () -> {
            Optional<E> callResult = crudService.update(entity, id);
            return callResult.orElseThrow(NotFoundException::new);
        });
    }

    @Override
    public void delete(@PathVariable("id") ID id, @Auth EmployeeAuthDetails authDetails) {
        checkAuthority(getById(id, authDetails), authDetails, this::hasAuthorityToEdit);
        wrapServiceCall(logger, () -> crudService.delete(id).orElseThrow(NotFoundException::new));
    }
}
