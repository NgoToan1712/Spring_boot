package com.company.opentalk.configuration;

import com.company.opentalk.security.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableWebSecurity
@EnableTransactionManagement
@ComponentScan()//chỉ định bean
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    //Mã hóa password người dùng(băm)
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }


    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
                .antMatchers("/employee-paging/**").authenticated()//done
                .antMatchers("/employee-topic/**").authenticated()//done
                .antMatchers(HttpMethod.GET, "/topic/{id}").permitAll() //done
                .antMatchers(HttpMethod.GET, "/topic").permitAll()
                .antMatchers("/topic/**").authenticated()//done
                .antMatchers("/user/**").authenticated() //done
                .antMatchers("/account/password").authenticated()//done
                .antMatchers("/account/update-info").authenticated()//done
                .antMatchers("/account/**").access("hasAnyRole('ROLE_ADMIN')") //done
                .antMatchers(HttpMethod.GET, "/company-branch").authenticated() //done
                .antMatchers(HttpMethod.GET, "/company-branch/{id}").authenticated()//done
                .antMatchers("/company-branch/**").access("hasAnyRole('ROLE_ADMIN')")//done
                .antMatchers("/employee/**").authenticated() //done
                .antMatchers("/login").permitAll().and();  //done
        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);


    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/css/**", "/js/**", "/img/**");
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
