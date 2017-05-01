package by.totema.recourse.controller;

import by.totema.recourse.entity.model.ProductType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

@RequestMapping("api/roductTypes")
public interface ProductTypeController extends CrudController<ProductType, Integer> {
}
