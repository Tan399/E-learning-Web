package com.example.ProjectElearning.Config;



import com.example.ProjectElearning.Filter.JwtAuthenticationFilter;
import com.example.ProjectElearning.Service.CustomUserDetailsService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

import java.util.Arrays;
import java.util.List;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Autowired
    CustomUserDetailsService userDetailsService;


    @Autowired
    JwtAuthenticationFilter jwtAuthenticationFilter;




    @Bean

    @Order(SecurityProperties.BASIC_AUTH_ORDER)

    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {

        http.cors(Customizer.withDefaults());

        http.csrf(AbstractHttpConfigurer::disable);

        http.httpBasic(Customizer.withDefaults());

        http.authorizeHttpRequests((requests) ->

                requests.requestMatchers(HttpMethod.OPTIONS, "**").permitAll()

                        .requestMatchers(
                                "/v3/api-docs/**",
                                "/swagger-ui/**",
                                "/swagger-ui.html",
                                "/swagger-resources/**",
                                "/webjars/**",
                                "/ws/**",
                                "/chatroom/public",
                                "/app/message"
                        ).permitAll()
                        .requestMatchers("/login", "/register/**", "/api/user-types", "/api/coursecategory").permitAll()

                        .requestMatchers("/secure/instructor/api/enrollments").hasAnyAuthority("USER", "INSTRUCTOR")

                        .requestMatchers(HttpMethod.POST, "/secure/instructor/api/coursefeedback").hasAnyAuthority("USER", "INSTRUCTOR")

                        .requestMatchers(HttpMethod.GET, "/secure/instructor/**").hasAnyAuthority("USER", "INSTRUCTOR")

                        .requestMatchers("/secure/instructor/**").hasAuthority("INSTRUCTOR")

                        .requestMatchers(HttpMethod.POST, "/api/payments").hasAnyAuthority("USER")

                        .requestMatchers(HttpMethod.GET, "/api/payments").hasAnyAuthority("INSTRUCTOR")

                        .requestMatchers("/secure/user/**").hasAuthority("USER")

                        .anyRequest().authenticated()
//                        .anyRequest().permitAll()

        );

        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        http.userDetailsService(userDetailsService);

        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();

    }



    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }






}
