package by.totema.recourse.configuration.security;

import by.totema.recourse.entity.model.Employee;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;

public class EmployeeAuthDetails extends Employee implements UserDetails, Serializable {

    public EmployeeAuthDetails(Employee employee) {
        super(employee);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return AuthorityUtils.createAuthorityList(
                getRole().name()
        );
    }

    public boolean isAdmin() {
        return getRole() == Role.ADMIN;
    }

    @Override
    public String getPassword() {
        return getPasswordHash();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return getRole() != Role.DISABLED;
    }
}
