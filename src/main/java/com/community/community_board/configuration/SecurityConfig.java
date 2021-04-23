package com.community.community_board.configuration;

import com.community.community_board.service.MemberService;
import lombok.AllArgsConstructor;
import org.apache.catalina.filters.CorsFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.servlet.support.csrf.CsrfRequestDataValueProcessor;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.multipart.support.MultipartFilter;
import org.springframework.web.servlet.support.RequestDataValueProcessor;

import javax.servlet.ServletContext;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter  {

    private MemberService memberService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Override
    public void configure(WebSecurity web) throws Exception
    {
        // static 디렉터리의 하위 파일 목록은 인증 무시 ( = 항상통과 )
        web.ignoring().antMatchers("/css/**", "/js/**", "/img/**", "/lib/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                // 페이지 권한 설정, antMachers는 인증을 했을때 롤 부여 및 접근권한을 설정해주는 것.


                .antMatchers("/").permitAll()
                .antMatchers("/user/login.do").permitAll()
                .antMatchers("/user/signup.do").permitAll()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/user/myinfo.do").hasRole("MEMBER")
                .anyRequest().authenticated()


                .and() // 로그인 설정
                .formLogin()
                .loginPage("/user/login.do")
                .defaultSuccessUrl("/user/login/result.do")



                .and() // 로그아웃 설정
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/user/logout.do"))
                .logoutSuccessUrl("/user/logout/result.do")
                .invalidateHttpSession(true)


                .and()
                // 403 예외처리 핸들링
                .exceptionHandling().accessDeniedPage("/user/denied.do");





        //http
                //.cors().disable()
               // .csrf().disable()
               // .formLogin().disable()
               // .headers().frameOptions().disable();
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(memberService).passwordEncoder(passwordEncoder());
    }

}