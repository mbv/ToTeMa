package by.totema.recourse.controller;

import by.totema.recourse.configuration.security.Auth;
import by.totema.recourse.configuration.security.EmployeeAuthDetails;
import by.totema.recourse.controller.exception.ControllerException;
import by.totema.recourse.entity.model.BaseEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
public interface CrudController<E extends BaseEntity<ID>, ID> {

    @GetMapping("{id}")
    E getById(@PathVariable("id") ID id, @Auth EmployeeAuthDetails authDetails) throws ControllerException;

    @GetMapping
    Iterable<E> getAll(Pageable pageable, @Auth EmployeeAuthDetails authDetails) throws ControllerException;

    @PostMapping
    <S extends E> S create(@RequestBody S entity, @Auth EmployeeAuthDetails authDetails) throws ControllerException;

    @PutMapping("{id}")
    E update(@RequestBody E entity, @PathVariable("id") ID id, @Auth EmployeeAuthDetails authDetails) throws ControllerException;

    @DeleteMapping("{id}")
    void delete(@PathVariable("id") ID id, @Auth EmployeeAuthDetails authDetails) throws ControllerException;

}
