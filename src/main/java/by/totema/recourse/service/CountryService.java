package by.totema.recourse.service;

import by.totema.recourse.entity.dto.CountryOrderReportDto;
import by.totema.recourse.entity.model.Country;

import java.util.List;
import java.util.Optional;

public interface CountryService extends CrudService<Country, Integer> {
    Optional<List<CountryOrderReportDto>> getOrderReport();
}
