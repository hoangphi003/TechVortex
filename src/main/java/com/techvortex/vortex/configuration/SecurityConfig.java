package com.techvortex.vortex.configuration;

import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.techvortex.vortex.entity.Account;
import com.techvortex.vortex.service.LoginService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private LoginService loginService;

    @Autowired
    BCryptPasswordEncoder pe;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(username -> {
            try {
                Account user = loginService.findById(username);
                String password = pe.encode(user.getPassword());
                String[] roles = user.getAuthorities().stream().map(er -> er.getRole().getRoleId())
                        .collect(Collectors.toList()).toArray(new String[0]);
                return User.withUsername(username).password(password).roles(roles).build();
            } catch (NoSuchElementException e) {
                System.out.println("User not found");
                throw new UsernameNotFoundException(username + "Not found");
            }
        });
    };

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.cors().disable();
        http.authorizeRequests().antMatchers("/admin/role").hasRole("Admin").antMatchers("/admin/account")
                .hasRole("admin").antMatchers("/admin/authority").hasRole("Admin").antMatchers("/admin/**")
                .authenticated().antMatchers("/purchase")
                .authenticated().antMatchers("/checkout")
                .authenticated().antMatchers("/changepassword")
                .authenticated().antMatchers("/profile")
                .authenticated().antMatchers("/cart")
                .authenticated().antMatchers("/favorite")
                .authenticated().antMatchers("/admin/**")
                .hasAnyRole("Staff", "Admin").anyRequest().permitAll();

        http.formLogin().loginPage("/login").loginProcessingUrl("/UserLogin")
                .defaultSuccessUrl("/login/success", false).failureUrl("/login/fail");

        http.exceptionHandling().accessDeniedPage("/login");

        http.logout().logoutUrl("/user/logout").logoutSuccessUrl("/index");

        // OAuth2 - Đăng nhập từ mạng xã hội
        http.oauth2Login()
                .loginPage("/login")
                .defaultSuccessUrl("/loginsocial/auth", false)
                .failureUrl("/login/fail")
                .authorizationEndpoint().baseUri("/oauth2/authorization");
    };

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**");
    };

}
