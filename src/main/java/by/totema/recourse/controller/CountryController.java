package by.totema.recourse.controller;

import by.totema.recourse.entity.model.Country;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

@RequestMapping("api/countries")
public interface CountryController extends CrudController<Country, Integer> {
}
