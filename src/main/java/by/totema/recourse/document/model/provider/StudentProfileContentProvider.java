package by.totema.recourse.document.model.provider;



import by.totema.recourse.entity.model.Employee;
import by.totema.recourse.entity.model.Product;
import org.springframework.data.util.Pair;

import java.util.*;
import java.util.stream.Collectors;

public class StudentProfileContentProvider implements ContentProvider<Employee, Product> {
    @Override
    public String createTitle(Employee mainEntity) {
        return mainEntity.getName();
    }

    @Override
    public String createFilename(Employee user) {
        return String.format("%s_profile", user.getName());
    }

    @Override
    public List<Pair<String, String>> createSubtitles(Employee mainEntity) {
        return Collections.emptyList();
    }

    @Override
    public List<String> getHeaders() {
        return Arrays.asList("Title", "Max students");
    }

    @Override
    public String createTableTitle(Employee user, Collection<Product> courses) {
        return "Courses";
    }

    @Override
    public List<List<String>> createRows(Collection<Product> products) {
        return products.stream()
                .map(product -> Arrays.asList(product.getName(), product.getSize()))
                .collect(Collectors.toList());
    }
}