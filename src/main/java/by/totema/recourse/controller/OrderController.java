package by.totema.recourse.controller;

import by.totema.recourse.configuration.security.Auth;
import by.totema.recourse.configuration.security.EmployeeAuthDetails;
import by.totema.recourse.controller.exception.ControllerException;
import by.totema.recourse.entity.dto.OrderDto;
import by.totema.recourse.entity.model.Order;
import by.totema.recourse.entity.model.ProductList;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

@RequestMapping("api/orders")
public interface OrderController extends CrudController<Order, Integer> {
    @GetMapping("{orderId}/product-lists")
    List<ProductList> getProductLists(@PathVariable("orderId") Integer orderId, Pageable pageable) throws ControllerException;

    @PostMapping("dto")
    Order create(@RequestBody OrderDto dto, @Auth EmployeeAuthDetails authDetails) throws ControllerException;

    @PutMapping("{orderId}/dto")
    Order update(@RequestBody OrderDto dto, @PathVariable("orderId") Integer orderId, @Auth EmployeeAuthDetails authDetails) throws ControllerException;
}
