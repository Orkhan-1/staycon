package com.staycon.config.security;

import com.staycon.service.impl.DefaultPrincipalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DefaultPrincipalService principalService;
    @Autowired
    private PasswordEncoder passwordEncoder;

   /* @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("Hello")
                .password("Hello")
                .roles("USER");
    }*/

    @Bean
    public PasswordEncoder getEncoder () {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/", "/about", "/register", "/verify", "/confirmRegistration", "/invalidUser", "/expiredToken").permitAll()
                .antMatchers("/js/*", "/css/*", "/img/*").permitAll()
                .antMatchers("/status", "/displayStatus", "/deletestatus", "/editstatus", "/upload-profile-photo", "/profilePhoto").hasRole("ADMIN")
                .antMatchers("/profile", "/editprofile").authenticated()
                .anyRequest().denyAll()
                .and().formLogin().loginPage("/login").defaultSuccessUrl("/").permitAll()
                .and().logout().permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(principalService).passwordEncoder(passwordEncoder);
    }
}