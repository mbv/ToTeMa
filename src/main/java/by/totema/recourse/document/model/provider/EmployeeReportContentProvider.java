package by.totema.recourse.document.model.provider;


import by.totema.recourse.entity.dto.EmployeeOrderReportDto;
import org.springframework.data.util.Pair;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class EmployeeReportContentProvider implements ContentProvider<Object, EmployeeOrderReportDto> {
    @Override
    public String createTitle(Object object) {
        return "";
    }

    @Override
    public String createFilename(Object object) {
        return "employeesReport";
    }

    @Override
    public List<Pair<String, String>> createSubtitles(Object mainEntity) {
        return Collections.emptyList();
    }

    @Override
    public List<String> getHeaders() {
        return Arrays.asList("Employee", "Quantity", "Total Price", "Total Cost", "Total Gross Margin", "Currency");
    }

    @Override
    public String createTableTitle(Object object, Collection<EmployeeOrderReportDto> courses) {
        return "Orders";
    }

    @Override
    public List<List<String>> createRows(Collection<EmployeeOrderReportDto> employeeOrderReportDtos) {
        return employeeOrderReportDtos.stream()
                .map(employeeOrderReportDto -> Arrays.asList(employeeOrderReportDto.getEmployee(), employeeOrderReportDto.getQuantity().toString(), employeeOrderReportDto.getTotalPrice().toString(), employeeOrderReportDto.getTotalCost().toString(), employeeOrderReportDto.getTotalGrossMargin().toString(), employeeOrderReportDto.getCurrency()))
                .collect(Collectors.toList());
    }
}