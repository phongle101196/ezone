package com.ezone.config;

import com.ezone.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

@Component
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
    private IUserService userService;


    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter();
    }

    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        // Get AuthenticationManager bean
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        // Password encoder, để Spring Security sử dụng mã hóa mật khẩu người dùng
        return new BCryptPasswordEncoder();
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors()
                .and()
                .exceptionHandling()
                .and()

                // add specific auth
                //permitAll: Cho phép call api mà không cần xác thực gì
                //authenticated: Cần xác thực mới call được api, không phân biệt quyền
                //hasAuthority("tên_quyền"): Sau khi xác thực, CHỈ người có quyền "tên_quyền" mới call được api
                //hasAnyAuthority("tên_quyền_1","tên_quyền_2"...): tương tự bên trên nhưng truyền được nhiều quyền trong tham số

                .authorizeRequests()

                .antMatchers(HttpMethod.GET).permitAll()

                .antMatchers("/api/v1/login").permitAll()
                .antMatchers(HttpMethod.POST,"/api/v1/users/**").permitAll()
                .antMatchers("/api/v1/users/**").authenticated()
                .antMatchers(HttpMethod.DELETE,"/api/v1/users/**").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.POST,"/api/v1/orderVouchers/**").authenticated()
                .antMatchers(HttpMethod.POST,"/api/v1/orderDetails/**").authenticated()
                .antMatchers("/api/v1/orders/**").authenticated()
                .antMatchers("/api/v1/bills/**").authenticated()
                .antMatchers("/api/v1/customers/**").authenticated()
                .antMatchers(HttpMethod.DELETE,"/api/v1/customers/**").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.DELETE,"/api/v1/bills/**").hasAuthority("ADMIN")


                .anyRequest().hasAuthority("ADMIN")

                .and()
                .httpBasic()
                .and()
                .csrf().disable();

        // Thêm một lớp Filter kiểm tra jwt
        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}
