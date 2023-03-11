package com.example.spring_basic.taco.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    @Bean
    protected SecurityFilterChain configure(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(
                        (auth) -> auth.antMatchers("/design", "orders").hasRole("USER")
                                .antMatchers("/", "/**").permitAll()
                )
                .httpBasic()
                .and()
                .build();
    }

    @Bean
    public UserDetailsService userDetailsService(@Autowired DataSource dataSource) {
//        UserDetails user1 = User.withUsername("user1").password("{noop}password1").roles("USER").build();
//        UserDetails user2 = User.withUsername("user2").password("{noop}password2").roles("USER").build();
        return new JdbcUserDetailsManager(dataSource);
    }
}
