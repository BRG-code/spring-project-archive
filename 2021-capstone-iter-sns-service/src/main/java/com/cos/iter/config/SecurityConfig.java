package com.cos.iter.config;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import com.cos.iter.config.oauth.PrincipalOAuth2UserService;
import com.cos.iter.util.Script;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
@Log4j2
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private PrincipalOAuth2UserService principalOAuth2UserService;
	
	@Bean
	public BCryptPasswordEncoder encode() {
		return new BCryptPasswordEncoder();
	}

	@Value("${server.servlet.context-path}")
	private String prefix;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		log.info("prefix: " + prefix);
		http.csrf().disable();
		http.authorizeRequests()
		.antMatchers("/", "/user/**", "/follow/**", "/image/**").authenticated()
		.antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
//		.anyRequest().permitAll()
		.and()
		.formLogin()
		.loginPage("/auth/loginForm")
		.loginProcessingUrl("/auth/login")
		.defaultSuccessUrl(prefix + "/")
		.failureHandler(new AuthenticationFailureHandler() {		
			@Override 
			public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
					AuthenticationException exception) throws IOException, ServletException {
				response.setContentType("text/html; charset=utf-8"); 
				PrintWriter out = response.getWriter();
				out.print(Script.back("???????????? ?????? ??????????????? ?????? ??? ????????????."));
				return;
			}
		})
		.and()
		.logout()
		.logoutUrl("/auth/logout")
		.logoutSuccessUrl("/")
		.and()
		.oauth2Login()  // oauth ?????? ????????? ??? ?????????
		.userInfoEndpoint() //  oauth ????????? ?????? ?????? ????????? ????????? ?????????????????? ????????? ??????
		.userService(principalOAuth2UserService); // ????????? ???????????? ????????????. (????????? ??? ????????? ?????? ???)
	}
}







