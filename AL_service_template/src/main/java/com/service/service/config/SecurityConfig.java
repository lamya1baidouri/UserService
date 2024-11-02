package com.service.service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.context.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final ApplicationContext applicationContext;

    @Autowired
    public SecurityConfig(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        JwtAuthenticationFilter jwtAuthenticationFilter = applicationContext.getBean(JwtAuthenticationFilter.class);

        http
                .csrf(csrf -> csrf.disable())  // Désactiver CSRF car nous utilisons des JWT
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers("/api/users/authenticate").permitAll()  // Route publique pour l'authentification
                        .requestMatchers("/api/users/register/doctor").permitAll()  // Route publique pour l'inscription des médecins
                        .requestMatchers(HttpMethod.POST, "/api/users/*/reset-password").hasAnyRole("USER", "DOCTOR")  // Autoriser USER et DOCTOR à réinitialiser le mot de passe
                        .requestMatchers(HttpMethod.GET, "/api/users").authenticated()  // Autoriser tous les utilisateurs authentifiés à récupérer la liste des utilisateurs
                        .requestMatchers(HttpMethod.GET, "/api/users/{userId}").authenticated()  // Autoriser tous les utilisateurs authentifiés à récupérer un utilisateur par ID
                        .requestMatchers("/api/users/**").hasRole("ADMIN")  // Restreindre les autres actions utilisateurs à ADMIN
                        .anyRequest().authenticated()  // Toute autre route doit être authentifiée
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))  // Utilisation de JWT (sans session)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);  // Ajout du filtre JWT avant le filtre d'authentification par nom d'utilisateur/mot de passe

        return http.build();
    }


    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User.builder()
                .username("admin")
                .password(passwordEncoder().encode("password"))
                .roles("ADMIN")
                .build();

        UserDetails user2 = User.builder()
                .username("user")
                .password(passwordEncoder().encode("password"))
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(user, user2);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder
                .userDetailsService(userDetailsService())
                .passwordEncoder(passwordEncoder());

        return authenticationManagerBuilder.build();
    }
}
