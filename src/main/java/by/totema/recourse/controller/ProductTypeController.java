package by.totema.recourse.controller;

import by.totema.recourse.entity.model.ProductType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

@RequestMapping("api/product-types")
public interface ProductTypeController extends CrudController<ProductType, Integer> {
}
