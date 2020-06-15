package com.pbl.backend.config;

import com.pbl.backend.common.exception.JWTAccessDeniedHandler;
import com.pbl.backend.common.exception.JWTAuthenticationEntryPoint;
import com.pbl.backend.entity.Audience;
import com.pbl.backend.filter.JWTAuthenticationFilter;
import com.pbl.backend.filter.JWTAuthorizationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsUtils;

/**
 * @author: 杜东方
 * @date: 2020/5/23
 * @description: 负责注册有关权限控制和用户登录校验的类
 */
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    // 因为UserDetailsService的实现类实在太多啦，这里设置一下我们要注入的实现类
    @Qualifier("userDetailServiceImpl")
    UserDetailsService userService;

    @Autowired
    Audience audience;

    @Bean
    public Audience createAudience(){
        return audience;
    }

    // 加密密码的，安全第一嘛~
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    //注册登录认证方法
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(bCryptPasswordEncoder());
    }

    //配置登录及注销及权限配置
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors()
                .and().csrf().disable()
                .authorizeRequests()
                // 测试用资源，需要验证了的用户才能访问
                .requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/student/course/**").hasRole("STUDENT")
                .antMatchers("/teacher/course/**").hasRole("TEACHER")
                .antMatchers("/course/project/**").hasAnyRole("TEACHER", "STUDENT")
                .antMatchers("/user/**").hasAnyRole("STUDENT", "TEACHER", "ADMIN")
                //.antMatchers("/test/**").hasRole("TEACHER")
                // 其他都放行了
                .anyRequest().permitAll()
                .and()
                .addFilter(new JWTAuthenticationFilter(authenticationManager(), audience))
                .addFilter(new JWTAuthorizationFilter(authenticationManager(), audience))
                // 不需要session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .exceptionHandling().authenticationEntryPoint(new JWTAuthenticationEntryPoint())
                .accessDeniedHandler(new JWTAccessDeniedHandler());      //添加无权限时的处理

    }
}
