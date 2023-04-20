package com.cos.photogramstart.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity	// 해당 파일(클래스)로 시큐리티를 활성화
@Configuration	// IoC 등록해서 메모리에 띄어야함   
public class SecurityConfig {

	@Bean
	BCryptPasswordEncoder encode() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	SecurityFilterChain configure(HttpSecurity http) throws Exception {
		// super 삭제 -> 기존 시큐리티가 가지고 있는 기능이 다 비활성화 됨.
		http.csrf().disable();
		http.authorizeRequests()
			.antMatchers("/","/user/**","/image/**","/subscribe/**","/comment/**","/api/**").authenticated()		// 해당 주소들은 인증이 안되어있으면 로그인페이지로
			.anyRequest().permitAll()		// 나머지 주소들은 접근가능 
			.and()
			.formLogin()
			.loginPage("/auth/signin")	//GET
			.loginProcessingUrl("/auth/signin")	//POST	-> 스프링 시큐리티가 로그인 프로세스 진행
			.defaultSuccessUrl("/");
		return http.build();
	}
}
