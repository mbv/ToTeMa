package by.totema.recourse.controller.impl;

import by.totema.recourse.configuration.security.EmployeeAuthDetails;
import by.totema.recourse.controller.OrderController;
import by.totema.recourse.controller.exception.NotFoundException;
import by.totema.recourse.entity.model.Order;
import by.totema.recourse.entity.model.ProductList;
import by.totema.recourse.service.OrderService;
import by.totema.recourse.service.ProductListService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

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

    @Override
    protected boolean hasAuthorityToEdit(Order entity, EmployeeAuthDetails authDetails) {
        return authDetails.isAdmin();
    }

    @Override
    public List<ProductList> getProductLists(Integer orderId, Pageable pageable) {
        return wrapServiceCall(logger, () -> {
            Optional<List<ProductList>> productLists = productListService.findByOrderId(orderId, pageable);
            return productLists.orElseThrow(NotFoundException::new);
        });
    }
}
