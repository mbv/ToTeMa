package by.totema.recourse.service;

import by.totema.recourse.entity.dto.OrderReportDto;
import by.totema.recourse.entity.model.Office;
import by.totema.recourse.service.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public interface OfficeService extends CrudService<Office, Integer> {
    Optional<List<OrderReportDto>> getOrderReport() throws ServiceException;
}
