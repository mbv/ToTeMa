package by.totema.recourse.configuration.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import javax.sql.DataSource;

@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

    private final DataSource dataSource;

    @Autowired
    public ResourceServerConfiguration(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public TokenStore tokenStore() {
        return new JdbcTokenStore(dataSource);
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources
                .tokenStore(tokenStore());
        resources.stateless(false);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()

                .antMatchers(HttpMethod.GET, "/api/countries/**").permitAll()
                .antMatchers(HttpMethod.PUT, "/api/countries/**").permitAll()
                .antMatchers(HttpMethod.GET, "/api/employees/**").permitAll()
                .antMatchers(HttpMethod.PUT, "/api/employees/**").permitAll()
                .antMatchers(HttpMethod.GET, "/api/offices/**").permitAll()
                .antMatchers(HttpMethod.PUT, "/api/offices/**").permitAll()
                .antMatchers(HttpMethod.GET, "/api/product-types/**").permitAll()
                .antMatchers(HttpMethod.PUT, "/api/product-types/**").permitAll()

                .antMatchers("/api/employees/logout", "/api/employees/password/change").authenticated()
                .antMatchers( "/api/countries/**").permitAll()
                .antMatchers( "/api/employees/**").permitAll()
                .antMatchers( "/api/offices/**").permitAll()
                .antMatchers( "/api/product-types/**").permitAll()



                .antMatchers("/api/**").authenticated()
                .anyRequest().permitAll();
    }
}
