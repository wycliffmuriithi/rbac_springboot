package com.test.demo.config.oauth;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

/**
 * Add resource server config
 * this overrides any config done in the WebSecurityConfigurerAdapter
 * inject the Role Based Access Control *
 */
@Order(2)
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    private static final String RESOURCE_ID = "securitydemoservice";

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.resourceId(RESOURCE_ID).stateless(false);
    }


    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .anonymous().disable()
                .authorizeRequests()
                .antMatchers("/login").permitAll()
                .antMatchers("/app/index").authenticated()
                .antMatchers("/app/users").hasRole("ADMIN")
                .and()
                .formLogin().loginPage("/api/login")
                .failureForwardUrl("/api/login")
                .successForwardUrl("/app/index")
                .and().logout().permitAll()
                .and().exceptionHandling().accessDeniedPage("/api/login")
        ;
        //to allow access to h2-console
        http.headers().frameOptions().sameOrigin();
    }

}
