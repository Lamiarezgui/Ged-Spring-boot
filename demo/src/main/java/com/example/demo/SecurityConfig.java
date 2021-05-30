package com.example.demo;

import com.example.demo.Services.UsersService;
import com.example.demo.jwt.AuthEntryPointJwt;
import com.example.demo.jwt.AuthTokenFilter;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;


@Configuration
@EnableWebSecurity
@AllArgsConstructor
@EnableGlobalMethodSecurity(
        securedEnabled = true,
        jsr250Enabled = true,
        prePostEnabled = true)

public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final UsersService usersService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private UserDetailsService myUserDetailsService;

    @Autowired
    private AuthEntryPointJwt unauthorizedHandler;

    @Bean
    public AuthTokenFilter authenticationJwtTokenFilter() {
        return new AuthTokenFilter();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserDetailsService);
    }


    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests().
                antMatchers("/authenticate").permitAll()
                .antMatchers("/username").authenticated()
                .antMatchers("/files").authenticated()
                .antMatchers("/files/*").authenticated()
                .antMatchers("/ajoutUser").authenticated()
                .antMatchers("/user/*").authenticated()
                .antMatchers("/users").authenticated()
                .antMatchers("/users/*").authenticated()
                .antMatchers("/archive").authenticated()
                .antMatchers("/archive/*").permitAll()
                .antMatchers("/groupe/*").authenticated()
                .antMatchers("/groupe/user/*").authenticated()
                .antMatchers("/files/versions/*").authenticated()
                .antMatchers("/files/ListVersions/*").authenticated()
                .antMatchers("/user/modifpass/*").authenticated()
                .antMatchers("/forgot-password/*").permitAll()
                .antMatchers("/reset-password/*").permitAll()
                .antMatchers("/start-process").permitAll()
                .and()
                .rememberMe();


        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
    }


    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "DELETE", "PUT","OPTIONS","HEAD","PATCH","MESSAGE"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
/*

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider =
                new DaoAuthenticationProvider();
        provider.setPasswordEncoder(bCryptPasswordEncoder);
        provider.setUserDetailsService(usersService);
        return provider;
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {

       http.httpBasic()
                .and()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(
                SessionCreationPolicy.STATELESS)
                .and()
                .exceptionHandling()
                .and()
                .authorizeRequests()
                .antMatchers("/*").permitAll()
                .antMatchers("/files").hasAnyRole("USER","ADMIN")
                .antMatchers("/files/*").permitAll()
                .antMatchers("/ajoutUser").authenticated()
                .antMatchers("/User/*").authenticated()
                .antMatchers("/users/*").authenticated()
                .antMatchers("/groupe/*").authenticated()
                .antMatchers("/groupe/user/*").authenticated()
                .antMatchers("/files/versions/*").permitAll()
                .antMatchers("/files/ListVersions/*").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin().usernameParameter("email").defaultSuccessUrl("/files").permitAll()
                .failureHandler(new SimpleUrlAuthenticationFailureHandler())
        .and()
        .logout().permitAll();
    }
*/

}
