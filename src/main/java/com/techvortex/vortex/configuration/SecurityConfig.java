package com.techvortex.vortex.configuration;

import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties.Authentication;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.cglib.core.Local;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

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

    // Login from Google account
    // public void LoginFormOAuth2(OAuth2AuthenticationToken oauth2) {
    //     String fullname = oauth2.getPrincipal().getAttribute("name");
    //     // String email = oauth2.getPrincipal().getAttribute("email");
    //     // System.currentTimeMillis() mã hóa mật khẩu theo giờ hệ thống
    //     String password = Long.toHexString(System.currentTimeMillis());

    //     UserDetails user = User.withUsername(fullname)
    //             .password(pe.encode(password))
    //             .roles("User").build();
    //     UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(user, null,
    //             user.getAuthorities());
    //     SecurityContextHolder.getContext().setAuthentication(auth);
    // }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.cors().disable();
        http.authorizeRequests().antMatchers("/admin/role").hasRole("admin").antMatchers("/admin/account")
                .hasRole("admin").antMatchers("/admin/authority").hasRole("admin").antMatchers("/admin/**")
                .authenticated().antMatchers("/purchase")
                .authenticated().antMatchers("/checkout")
                .authenticated().antMatchers("/changepassword")
                .authenticated().antMatchers("/profile")
                .authenticated().antMatchers("/favorite")
                .authenticated().antMatchers("/admin/**")
                .hasAnyRole("staff", "admin").anyRequest().permitAll();

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
