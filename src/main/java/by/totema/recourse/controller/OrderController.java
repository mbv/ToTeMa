package by.totema.recourse.controller;

import by.totema.recourse.entity.model.Order;
import by.totema.recourse.entity.model.ProductList;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController

@RequestMapping("api/orders")
public interface OrderController extends CrudController<Order, Integer> {
    @GetMapping("{orderId}/product-lists")
    List<ProductList> getProductLists(
            @PathVariable("orderId") Integer orderId, Pageable pageable);
}
