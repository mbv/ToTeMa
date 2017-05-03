package by.totema.recourse.controller;

import by.totema.recourse.entity.model.Order;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

@RequestMapping("api/orders")
public interface OrderController extends CrudController<Order, Integer> {
}
