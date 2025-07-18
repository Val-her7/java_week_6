package dev.val.COGIP_API.configuration;

import dev.val.COGIP_API.filter.JwtFilter;
import dev.val.COGIP_API.service.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final CustomUserDetailsService userDetailsService;
    private final JwtFilter jwtFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth ->
                        auth.requestMatchers("/api/v1/login", "/api/v1/register").permitAll()
                                .requestMatchers(HttpMethod.GET, "/api/v1/companies/**").hasAnyRole("USER", "ADMIN")
                                .requestMatchers(HttpMethod.POST, "/api/v1/companies/**").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.PUT, "/api/v1/companies/**").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.DELETE, "/api/v1/companies/**").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.GET, "/api/v1/contacts/**").hasAnyRole("USER", "ADMIN")
                                .requestMatchers(HttpMethod.POST, "/api/v1/contacts/**").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.PUT, "/api/v1/contacts/**").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.DELETE, "/api/v1/contacts/**").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.POST, "/api/v1/invoices/**").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.GET, "/api/v1/invoices/**").hasAnyRole("USER", "ADMIN")
                                .requestMatchers(HttpMethod.PUT, "/api/v1/invoices/**").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.DELETE, "/api/v1/invoices/**").hasRole("ADMIN")

                                .anyRequest().authenticated())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .build();

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http, PasswordEncoder passwordEncoder) throws Exception{
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
        return authenticationManagerBuilder.build();
    }
}
