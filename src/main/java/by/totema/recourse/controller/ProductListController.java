package by.totema.recourse.controller;

import by.totema.recourse.entity.model.ProductList;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

@RequestMapping("api/product-lists")
public interface ProductListController extends CrudController<ProductList, Integer> {
}
