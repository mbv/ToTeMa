package by.totema.recourse.document.model.provider;


import by.totema.recourse.entity.dto.OrderReportDto;
import org.springframework.data.util.Pair;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class YearReportContentProvider implements ContentProvider<Object, OrderReportDto> {
    @Override
    public String createTitle(Object object) {
        return "";
    }

    @Override
    public String createFilename(Object object) {
        return "yearReport";
    }

    @Override
    public List<Pair<String, String>> createSubtitles(Object mainEntity) {
        return Collections.emptyList();
    }

    @Override
    public List<String> getHeaders() {
        return Arrays.asList("Year", "Quantity", "Total Price", "Total Cost", "Total Gross Margin", "Currency");
    }

    @Override
    public String createTableTitle(Object object, Collection<OrderReportDto> courses) {
        return "Orders";
    }

    @Override
    public List<List<String>> createRows(Collection<OrderReportDto> orderReportDtos) {
        return orderReportDtos.stream()
                .map(dto -> Arrays.asList(dto.getYear().toString(), dto.getQuantity().toString(), dto.getTotalPrice().toString(), dto.getTotalCost().toString(), dto.getTotalGrossMargin().toString(), dto.getCurrency()))
                .collect(Collectors.toList());
    }
}