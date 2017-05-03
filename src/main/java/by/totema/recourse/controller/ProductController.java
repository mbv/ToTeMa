package by.totema.recourse.controller;

import by.totema.recourse.entity.model.Product;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

@RequestMapping("api/products")
public interface ProductController extends CrudController<Product, Integer> {
}
