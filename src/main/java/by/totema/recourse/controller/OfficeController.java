package by.totema.recourse.controller;

import by.totema.recourse.entity.model.Office;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

@RequestMapping("api/offices")
public interface OfficeController extends CrudController<Office, Integer> {
}
