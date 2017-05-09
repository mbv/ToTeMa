package by.totema.recourse.document.model.provider;


import by.totema.recourse.entity.dto.CountryOrderReportDto;
import org.springframework.data.util.Pair;

import java.util.*;
import java.util.stream.Collectors;

public class CountryReportContentProvider implements ContentProvider<Object, CountryOrderReportDto> {
    @Override
    public String createTitle(Object object) {
        return "";
    }

    @Override
    public String createFilename(Object object) {
        return "countriesReport";
    }

    @Override
    public List<Pair<String, String>> createSubtitles(Object mainEntity) {
        return Collections.emptyList();
    }

    @Override
    public List<String> getHeaders() {
        return Arrays.asList("Country", "Quantity", "Total Price", "Total Cost", "Total Gross Margin", "Currency");
    }

    @Override
    public String createTableTitle(Object object, Collection<CountryOrderReportDto> courses) {
        return "Orders";
    }

    @Override
    public List<List<String>> createRows(Collection<CountryOrderReportDto> countryOrderReportDtos) {
        return countryOrderReportDtos.stream()
                .map(countryOrderReportDto -> Arrays.asList(countryOrderReportDto.getCountry(), countryOrderReportDto.getQuantity().toString(), countryOrderReportDto.getTotalPrice().toString(), countryOrderReportDto.getTotalCost().toString(), countryOrderReportDto.getTotalGrossMargin().toString(), countryOrderReportDto.getCurrency()))
                .collect(Collectors.toList());
    }
}