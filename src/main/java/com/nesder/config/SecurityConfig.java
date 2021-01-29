package com.nesder.config;	

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.nesder.handler.JWTAccessDeniedHandler;
import com.nesder.handler.JWTAuthenticationEntryPoint;
import com.nesder.handler.JWTBasicAuthenticationFilter;
import com.nesder.handler.JWTUsernameAuthenticationFilter;	

@EnableWebSecurity	
@EnableGlobalMethodSecurity(prePostEnabled = true)	
public class SecurityConfig extends WebSecurityConfigurerAdapter {	

    @Autowired	
    @Qualifier("accountService")	
    private UserDetailsService userDetailsService;
    
    @Bean	
    public BCryptPasswordEncoder bCryptPasswordEncoder() {	
        return new BCryptPasswordEncoder();	
    }	

    @Override	
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {	
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());	
    }	

    @Override	
    protected void configure(HttpSecurity http) throws Exception {	
        http.csrf().disable()
                .authorizeRequests()	
                // 需要验证了的用户才能访问	
//                .antMatchers("/nesder/**").authenticated()	
//                .antMatchers(HttpMethod.DELETE, "/tasks/**").hasRole("ADMIN")	
                // 其他都放行了
                .antMatchers(HttpMethod.OPTIONS)
//                .anyRequest()
                .permitAll();
//                .and()	
//                .addFilter(new JWTUsernameAuthenticationFilter(authenticationManager()))	
//                .addFilter(new JWTBasicAuthenticationFilter(authenticationManager()))	
                // 不需要session	
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)	
//                .and()	
//                .exceptionHandling().authenticationEntryPoint(new JWTAuthenticationEntryPoint())	
//                .accessDeniedHandler(new JWTAccessDeniedHandler());      //添加无权限时的处理	
    }	

    @Bean	
    CorsConfigurationSource corsConfigurationSource() {	
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();	
        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());	
        return source;	
    }	
}