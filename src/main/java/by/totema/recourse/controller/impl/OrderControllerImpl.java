package by.totema.recourse.controller.impl;

import by.totema.recourse.configuration.security.Auth;
import by.totema.recourse.configuration.security.EmployeeAuthDetails;
import by.totema.recourse.controller.OrderController;
import by.totema.recourse.controller.exception.AccessDeniedException;
import by.totema.recourse.controller.exception.BadRequestException;
import by.totema.recourse.controller.exception.ControllerException;
import by.totema.recourse.controller.exception.NotFoundException;
import by.totema.recourse.entity.dto.OrderDto;
import by.totema.recourse.entity.model.Order;
import by.totema.recourse.entity.model.ProductList;
import by.totema.recourse.service.OrderService;
import by.totema.recourse.service.ProductListService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;

import static by.totema.recourse.util.ServiceCallWrapper.wrapServiceCall;
import static org.slf4j.LoggerFactory.getLogger;


public class OrderControllerImpl extends AbstractCrudController<Order, Integer> implements OrderController {

    private static final Logger logger = getLogger(OrderControllerImpl.class);
    private OrderService orderService;
    private ProductListService productListService;

    @Autowired
    public OrderControllerImpl(OrderService orderService, ProductListService productListService) {
        super(orderService, logger);
        this.orderService = orderService;
        this.productListService = productListService;
    }

    protected boolean hasAuthorityToEdit(OrderDto dto, EmployeeAuthDetails authDetails) {
        return true;
    }

    protected boolean hasAuthorityToRead(OrderDto dto, EmployeeAuthDetails authDetails) {
        return true;
    }

    protected void checkAuthority(OrderDto dto, EmployeeAuthDetails authDetails, BiFunction<OrderDto, EmployeeAuthDetails, Boolean> authorityChecker) {
        if (!authDetails.isAdmin() && !authorityChecker.apply(dto, authDetails)) {
            throw new AccessDeniedException();
        }
    }

    @Override
    protected boolean hasAuthorityToEdit(Order entity, EmployeeAuthDetails authDetails) {
        return authDetails.isAdmin();
    }

    @Override
    public List<ProductList> getProductLists(@PathVariable("orderId") Integer orderId, Pageable pageable) throws ControllerException {
        return wrapServiceCall(logger, () -> {
            Optional<List<ProductList>> productLists = productListService.findByOrderId(orderId, pageable);
            return productLists.orElseThrow(NotFoundException::new);
        });
    }

    @Override
    public Order create(@Valid @RequestBody OrderDto dto, @Auth EmployeeAuthDetails authDetails) throws ControllerException {
        checkAuthority(dto, authDetails, this::hasAuthorityToEdit);
        return wrapServiceCall(logger, () -> {
            Optional<Order> callResult = orderService.add(dto);
            return callResult.orElseThrow(BadRequestException::new);
        });
    }

    @Override
    public Order update(@Valid @RequestBody OrderDto dto, @PathVariable("orderId") Integer orderId, @Auth EmployeeAuthDetails authDetails) throws ControllerException {
        checkAuthority(dto, authDetails, this::hasAuthorityToEdit);
        return wrapServiceCall(logger, () -> {
            Optional<Order> callResult = orderService.update(dto, orderId);
            return callResult.orElseThrow(NotFoundException::new);
        });
    }
}
