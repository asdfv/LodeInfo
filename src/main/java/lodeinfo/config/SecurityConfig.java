package lodeinfo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("user").password("userlode321").roles("USER");
        auth.inMemoryAuthentication().withUser("admin").password("vasili").roles("ADMIN", "USER", "DIRECTOR");
        auth.inMemoryAuthentication().withUser("director").password("director321").roles("DIRECTOR");
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/assets/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.httpBasic()
                .and()
                .authorizeRequests()
                .anyRequest().permitAll().and()
                .logout()
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .clearAuthentication(true)
                .logoutSuccessUrl("/#/login").permitAll().and()
                .csrf().disable();

//                http.httpBasic()
//                .and()
//                .authorizeRequests()
//                .antMatchers(
//                        "/pages/newsEdit.html",
//                        "/pages/newsAdd.html",
//                        "/news/save/**",
//                        "/news/delete/**",
//                        "/pages/vip.html",
//                        "/vip/**",
//
//                        "/pages/asteriskMonth.html",
//                        "/pages/asteriskDay.html",
//                        "/pages/asteriskCDR.html",
//                        "/asterisk/**",
//
//                        "/pages/vip.html",
//                        "/vip/**",
//
//                        "/pages/sms.html",
//                        "/sms"
//                        ).hasAnyRole("DIRECTOR", "ADMIN", "USER")
//                .anyRequest().permitAll().and()
//                .logout()
//                    .invalidateHttpSession(true)
//                    .deleteCookies("JSESSIONID")
//                    .clearAuthentication(true)
//                    .logoutSuccessUrl("/#/login").permitAll().and()
//                .csrf().disable();
    }
}
