package by.totema.recourse.entity.support;

import by.totema.recourse.entity.model.Employee;

import java.beans.PropertyEditorSupport;

public class UserRoleEnumConverter extends PropertyEditorSupport {

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        setValue(Employee.Role.valueOf(text.toUpperCase()));
    }
}
