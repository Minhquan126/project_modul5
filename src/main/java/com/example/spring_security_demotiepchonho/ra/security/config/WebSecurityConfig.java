package com.example.spring_security_demotiepchonho.ra.security.config;

import com.example.spring_security_demotiepchonho.ra.security.jwt.JwtAuthenticationFilter;
import com.example.spring_security_demotiepchonho.ra.security.userPrincipal.CustomUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private CustomUserDetailService customUserDetailService;
    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter(){
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
        auth.userDetailsService(customUserDetailService) // Cung cap customUserDetailService cho spring security
                .passwordEncoder(passwordEncoder()); // cung cấp password encoder
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors() // Ngăn chặn request từ một domain khác
                .and().csrf().disable()
                .authorizeRequests()
                .antMatchers("/api/v4/auth/**").permitAll()
                .antMatchers("/api/v4/users/update/**").permitAll()
                .antMatchers("/api/v4/users/password").permitAll()
                .antMatchers("/api/v4/users/block/**").hasAnyAuthority("ADMIN")
                .antMatchers("/api/v4/users").hasAnyAuthority("ADMIN")
                .antMatchers("/api/v4/products").permitAll()
                .antMatchers("/api/v4/products/create").hasAnyAuthority("ADMIN","PM")
                .antMatchers("/api/v4/products/delete/**").hasAnyAuthority("ADMIN","PM")
                .antMatchers("/api/v4/products/update/**").hasAnyAuthority("ADMIN","PM")
                .antMatchers("/api/v4/products/byName/**").permitAll()
                .antMatchers("/api/v4/products/byCatalog/**").permitAll()
                .antMatchers("/api/v4/orderDetails").hasAnyAuthority("ADMIN","PM")
                .antMatchers("/api/v4/orderDetails/**").permitAll()
                .antMatchers("/api/v4/orderDetails/create").permitAll()
                .antMatchers("/api/v4/orders").permitAll()
                .antMatchers("/api/v4/orders/checkOrder/**").hasAnyAuthority("ADMIN","PM")
                .antMatchers("/api/v4/orders/total").hasAnyAuthority("ADMIN","PM")
                .antMatchers("/api/v4/orders/totalByMonth").hasAnyAuthority("ADMIN","PM")
                .antMatchers("/api/v4/orders/totalThisMonth").hasAnyAuthority("ADMIN","PM")
                .antMatchers("/api/v4/orders/numberOfOrders").hasAnyAuthority("ADMIN","PM")
                .antMatchers("/api/v4/orders/numberOfOrderFalse").hasAnyAuthority("ADMIN","PM")
                .antMatchers("/api/v4/catalogs").permitAll()
                .antMatchers("/api/v4/catalogs/create").hasAnyAuthority("ADMIN","PM")
                .antMatchers("/api/v4/catalogs/update/**").hasAnyAuthority("ADMIN","PM")
                .antMatchers("/api/v4/catalogs/delete/**").hasAnyAuthority("ADMIN","PM")
                .antMatchers("/api/v4/cartItems/**").permitAll()
//                .antMatchers("/api/v4/orderDetails/**").permitAll()// Cho phép tất cả mọi người truy cập vào địa chỉ này
//                .antMatchers("/api/v4/admin/**").hasAuthority("ADMIN")
//                .antMatchers("/api/v4/pm/**").hasAnyAuthority("ADMIN","PM")
//                .antMatchers("/api/v4/user/**").hasAnyAuthority("USER","ADMIN","PM")
                .anyRequest().authenticated(); // Tất cả các request khác đều cần phải xác thực mới được truy cập

        // Thêm một lớp Filter kiểm tra jwt
        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}
