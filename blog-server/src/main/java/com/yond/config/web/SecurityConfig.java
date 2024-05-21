package com.yond.config.web;

import com.yond.config.login.JwtFilter;
import com.yond.config.login.JwtLoginFilter;
import com.yond.config.login.MyAuthenticationEntryPoint;
import com.yond.service.LoginLogService;
import com.yond.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.Customizer.withDefaults;

/**
 * @Description: Spring Security配置类
 * @Author: Naccl
 * @Date: 2020-07-19
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    private final UserServiceImpl userService;
    private final LoginLogService loginLogService;
    private final MyAuthenticationEntryPoint myAuthenticationEntryPoint;
    private final AuthenticationConfiguration authenticationConfiguration;

    public SecurityConfig(UserServiceImpl userService, LoginLogService loginLogService, MyAuthenticationEntryPoint myAuthenticationEntryPoint, AuthenticationConfiguration authenticationConfiguration) {
        this.userService = userService;
        this.loginLogService = loginLogService;
        this.myAuthenticationEntryPoint = myAuthenticationEntryPoint;
        this.authenticationConfiguration = authenticationConfiguration;
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(withDefaults())
                .sessionManagement(sessionManagement ->
                        sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers("/admin/webTitleSuffix").permitAll()
                                .requestMatchers(HttpMethod.GET, "/admin/**").hasAnyRole("admin", "visitor")
                                .requestMatchers("/admin/**").hasRole("admin")
                                .anyRequest().permitAll())
                .addFilterBefore(new JwtLoginFilter("/admin/login", authenticationManager(authenticationConfiguration), loginLogService), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(new JwtFilter(), UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling(exceptionHandling ->
                        exceptionHandling.authenticationEntryPoint(myAuthenticationEntryPoint));

        return http.build();
    }
}

