package com.exercise.gestioneEventi.security;


import com.exercise.gestioneEventi.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity(debug = true)
@EnableMethodSecurity
public class SecurityChain {
    @Autowired
    private JwtTools jwtTools;
    @Autowired
    private JwtFilter jwtFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf(AbstractHttpConfigurer::disable);
        //httpSecurity.cors(AbstractHttpConfigurer::disable);

        httpSecurity.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        httpSecurity.authorizeHttpRequests(authorizeRequests ->
                authorizeRequests
                        .requestMatchers("/auth/**").permitAll()
                        .requestMatchers("/utenti/**").hasAnyAuthority(Role.AMMINISTRATORE.name())
                        .requestMatchers("/events/**").hasAnyAuthority(Role.AMMINISTRATORE.name())
                        .requestMatchers(HttpMethod.POST, "/events").hasAnyAuthority(Role.ORGANIZZATORE.name(), Role.AMMINISTRATORE.name())
                        .requestMatchers(HttpMethod.PUT, "/events/**").hasAnyAuthority(Role.ORGANIZZATORE.name(), Role.AMMINISTRATORE.name())
                        .requestMatchers(HttpMethod.DELETE, "/events/**").hasAnyAuthority(Role.ORGANIZZATORE.name(), Role.AMMINISTRATORE.name())
                        .requestMatchers(HttpMethod.POST, "/event/{id}/book").permitAll()
                        .anyRequest().denyAll()
        );


        return httpSecurity.build();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration cors = new CorsConfiguration();
        cors.addAllowedOrigin("http://www.example.com");
        cors.addAllowedMethod(HttpMethod.GET);
        UrlBasedCorsConfigurationSource configurationSource = new UrlBasedCorsConfigurationSource();
        configurationSource.registerCorsConfiguration("*/**", cors);
        return configurationSource;
    };

}




