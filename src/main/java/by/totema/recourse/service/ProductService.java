package by.totema.recourse.service;

import by.totema.recourse.entity.dto.OrderReportDto;
import by.totema.recourse.entity.model.Product;
import by.totema.recourse.service.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public interface ProductService extends CrudService<Product, Integer> {
    Optional<List<OrderReportDto>> getOrderReport() throws ServiceException;
    Optional<List<OrderReportDto>> getDetailedOrderReport() throws ServiceException;
}
