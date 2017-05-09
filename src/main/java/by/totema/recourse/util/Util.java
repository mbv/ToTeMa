package by.totema.recourse.util;

import by.totema.recourse.entity.model.Employee;
import by.totema.recourse.repository.EmployeeRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public class Util {

    private static final PageRequest ALL_ITERMS_PAGE_REQUEST = new PageRequest(0, Integer.MAX_VALUE);

    public static boolean ifExistsWithRole(EmployeeRepository repository, Integer id, Employee.Role role) {
        Employee employee = repository.findOne(id);
        return (employee != null) && (employee.getRole() == role);
    }

    public static Pageable allItemsPage() {
        return ALL_ITERMS_PAGE_REQUEST;
    }

    public static <T> T ifNullDefault(T toTest, T defaultValue) {
        return toTest == null ? defaultValue : toTest;
    }

    public static String sanitizeFilename(String filename) {
        return filename.replaceAll("[^a-zA-Z0-9.-]", "_");
    }
}
