package com.dimitar.microservices.eventmanagement.eventmanagementapi.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {


    /**
     * Using the authentication manager builder, configure the users, passwords and roles.
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        final PasswordEncoder passwordEncoder = getPasswordEncoder();

       auth.inMemoryAuthentication()
               .passwordEncoder(passwordEncoder)
               .withUser("dimitar").password(passwordEncoder.encode("dimitar")).roles("USER").and()
               .withUser("admin").password(passwordEncoder.encode("admin")).roles("ADMIN");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
             .httpBasic()
             .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/events").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/events/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.PATCH, "/events/**").hasRole("ADMIN")
             .and()
                .csrf().disable();
    }

    PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
