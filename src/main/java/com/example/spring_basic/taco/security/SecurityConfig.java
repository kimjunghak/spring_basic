package com.example.spring_basic.taco.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final UserDetailsService userDetailsService;

    @Bean
    protected PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    protected SecurityFilterChain configure(HttpSecurity http) throws Exception {
        return http
                .authorizeRequests(
                        auth -> auth.antMatchers("/design", "orders").access("""
                                        hasRole('ROLE_USER')""")
                                .antMatchers("/", "/**").permitAll()
                )
                .userDetailsService(userDetailsService)
                .httpBasic()
                .and()
                .formLogin(
                        form -> form.loginPage("/login")
                                .loginProcessingUrl("/authenticate")
                                .usernameParameter("name")
                                .passwordParameter("pwd")
                                .defaultSuccessUrl("/design")
                )
                .logout(
                        logout -> logout.logoutSuccessUrl("/")
                )
                .csrf().and()
                .headers(
                        header -> header.frameOptions().disable()
                )
                .build();
    }
//    @Bean
//    protected UserDetailsService userDetailsService(@Autowired DataSource dataSource) {
////        UserDetails user1 = User.withUsername("user1").password("{noop}password1").roles("USER").build();
////        UserDetails user2 = User.withUsername("user2").password("{noop}password2").roles("USER").build();
//        JdbcUserDetailsManager manager = new JdbcUserDetailsManager(dataSource);
//        manager.setUsersByUsernameQuery("""
//                select username, password, enabled from users where username = ?""");
//        manager.setAuthoritiesByUsernameQuery("""
//                select username, authority from authorities where username = ?""");
//        return manager;
//    }
}
