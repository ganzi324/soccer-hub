package com.ganzi.soccerhub.auth;

import com.ganzi.soccerhub.auth.application.port.in.AddRefreshTokenUseCase;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.Customizer.withDefaults;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration {

    private final AuthenticationConfiguration authenticationConfiguration;

    private final CustomOAuth2UserService customOauth2UserService;
    private final AuthenticationEntryPoint authenticationEntryPoint;
    private final JwtAuthProvider jwtAuthProvider;
    private final AddRefreshTokenUseCase addRefreshTokenUseCase;

    public SecurityConfiguration(
            AuthenticationConfiguration authenticationConfiguration,
            CustomOAuth2UserService customOauth2UserService,
            @Qualifier("delegatedAuthenticationEntryPoint") AuthenticationEntryPoint authenticationEntryPoint,
            JwtAuthProvider jwtAuthProvider,
            AddRefreshTokenUseCase addRefreshTokenUseCase
    ) {
        this.authenticationConfiguration = authenticationConfiguration;
        this.customOauth2UserService = customOauth2UserService;
        this.authenticationEntryPoint = authenticationEntryPoint;
        this.jwtAuthProvider = jwtAuthProvider;
        this.addRefreshTokenUseCase = addRefreshTokenUseCase;
    }

    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(PasswordEncoder passwordEncoder, UserDetailsService userDetailsService) {
        return new CustomAuthenticationProvider(userDetailsService, passwordEncoder);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .headers(headersConfigurer ->
                        headersConfigurer
                                .frameOptions(
                                        HeadersConfigurer.FrameOptionsConfig::disable
                                )
                )
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers("/h2-console/**", "/v1/auth/**").permitAll()
                        .anyRequest().authenticated()
                )
                .addFilterBefore(new JwtAuthenticationFilter(jwtAuthProvider), UsernamePasswordAuthenticationFilter.class)
                .addFilterAt(new LoginAuthenticationFilter(authenticationManager(), jwtAuthProvider, addRefreshTokenUseCase), UsernamePasswordAuthenticationFilter.class)
                .logout((logout) -> logout
                        .logoutSuccessUrl("/")
                )
                .oauth2Login(oauth2 -> oauth2
                        .userInfoEndpoint(userInfo -> userInfo.userService(customOauth2UserService))
                )
                .httpBasic(withDefaults())
                .exceptionHandling(configure -> configure.authenticationEntryPoint(authenticationEntryPoint));
        return http.build();
    }
}
