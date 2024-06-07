package com.app.config;

import com.app.services.UserDetailServiceImp;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity // Requerido para usar la anotación @PreAuthorized en el controlador
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(csrf -> csrf.disable()) // Esta protección no es necesaria en el ámbito de API. Si fuera MVC enotnces sí por el uso de formularios
                .httpBasic(Customizer.withDefaults()) // Significa que la autenticación pedirá usuario y contraseña
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Creamos una sesión sin estado. la sesión dependerá de la expiración de un token
//                .authorizeHttpRequests(http -> {
//                    http.requestMatchers(HttpMethod.GET, "/auth/hello").permitAll(); // Permite todo el acceso a este endpoint sin necesidad de estar logueado
//                    http.requestMatchers(HttpMethod.GET, "/auth/hello-secured").hasAnyAuthority( "CREATE"); // Necesita autorización de CREATE para poder usar este recurso
//                    http.anyRequest().denyAll(); // Deniega el acceso a cualquier otro endpoint que no sean los dos definidos antes
//                    // http.anyRequest().authenticated(); // Permite el acceso a cualquier endpoint no especificado, siempre que se esté autenticado
//                })
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(UserDetailServiceImp userDetailServiceImp) {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(userDetailServiceImp);
        return provider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    public static void main(String[] args) {
//        System.out.println(new BCryptPasswordEncoder().encode("1234"));
//    }



}
