package com.example.hitomagicwater_maria.seguridad;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class DatabaseWebSecurity  {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new PasswordEncoder() {
            @Override
            public String encode(CharSequence charSequence) {
                return charSequence.toString();
            }

            @Override
            public boolean matches(CharSequence charSequence, String s) {
                return charSequence.toString().equals(s);
            }
        };
    }

    @Bean
    public UserDetailsManager users(DataSource dataSource) {
        JdbcUserDetailsManager users = new JdbcUserDetailsManager(dataSource);
        users.setUsersByUsernameQuery("SELECT pf_nik, password, activo FROM usuario WHERE pf_nik=?");
        users.setAuthoritiesByUsernameQuery("SELECT pf_nik, permiso FROM usuario WHERE pf_nik=?");
        return users;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable);

        http.authorizeHttpRequests(auth -> auth
                .requestMatchers("/css/**").permitAll()
                .requestMatchers("/imagenes/**").permitAll()
                .requestMatchers("/", "/login", "/signup").permitAll() // permitir el acceso no autenticado a "/"
                .requestMatchers("/tarea").hasAuthority("trabajador")
                .requestMatchers("/creartarea").hasAuthority("trabajador")
                .requestMatchers("/proyecto").hasAuthority("supervisor")
                .requestMatchers("/tarea").permitAll()
                .requestMatchers("/editarTarea").hasAuthority("trabajador")
                .requestMatchers("/eliminarTarea").hasAuthority("trabajador")
                .anyRequest().authenticated());

        http.formLogin(formLogin -> formLogin.loginPage("/login").permitAll());
        http.logout(logout -> logout.logoutUrl("/logout").logoutSuccessUrl("/").permitAll());
        http.exceptionHandling((exception)-> exception.accessDeniedPage("/denegado"));

        return http.build();
    }
}


