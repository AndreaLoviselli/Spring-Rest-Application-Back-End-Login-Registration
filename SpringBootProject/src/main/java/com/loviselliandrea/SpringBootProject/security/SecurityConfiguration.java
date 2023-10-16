package com.loviselliandrea.SpringBootProject.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Bean
    public UserDetailsManager users(DataSource dataSource){
        /*codice commentato: compilato una volta, poi non necessario.
        UserDetails user = User
                .withUsername("user")
                .password(passwordEncoder.encode("user"))
                .roles("USER")
                .build();
        UserDetails admin = User
                .withUsername("admin")
                .password(passwordEncoder.encode("admin"))
                .roles("ADMIN")
                .build();                       */
        JdbcUserDetailsManager judm = new JdbcUserDetailsManager(dataSource);
        //judm.createUser(user);
        //judm.createUser(admin);
        return judm;
    }
    @Bean
    PasswordEncoder passwordEncoder(){
        return this.passwordEncoder;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) //da rimuovere in prod. (permette di usare PostMan per il testing)
                .authorizeHttpRequests(
                        auth -> auth.requestMatchers(HttpMethod.GET, "/users/**")
                                .hasAnyRole("USER","ADMIN")
                                .requestMatchers(HttpMethod.POST, "/users/**")
                                .hasAnyRole("USER","ADMIN")
                                .requestMatchers(HttpMethod.PUT, "/users/**")
                                .hasRole("ADMIN")
                                .requestMatchers(HttpMethod.DELETE, "/users/**")
                                .hasRole("ADMIN")
                                .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }

}
