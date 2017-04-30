package by.totema.recourse.util;

import by.totema.recourse.entity.model.Employee;
import by.totema.recourse.repository.EmployeeRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public class Util {

    private static final PageRequest ALL_ITERMS_PAGE_REQUEST = new PageRequest(0, Integer.MAX_VALUE);

    public static boolean ifExistsWithRole(EmployeeRepository repository, Integer id, Employee.Role role) {
        Employee user = repository.findOne(id);
        return (user != null) && (user.getRole() == role);
    }

    public static Pageable allItemsPage() {
        return ALL_ITERMS_PAGE_REQUEST;
    }

}
