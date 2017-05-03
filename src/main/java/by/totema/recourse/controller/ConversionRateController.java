package by.totema.recourse.controller;

import by.totema.recourse.entity.model.ConversionRate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

@RequestMapping("api/conversion-rates")
public interface ConversionRateController extends CrudController<ConversionRate, Integer> {
}
