package com.cos.photogramstart.web;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.handler.ex.CustomValidationException;
import com.cos.photogramstart.service.AuthService;
import com.cos.photogramstart.web.dto.auth.SignupDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
 
@Slf4j
@RequiredArgsConstructor
@Controller	// 1. IoC 2. 파일(view)을 리턴하는 컨트롤러 
public class AuthController {
	
	private final AuthService authService;

	@GetMapping("/auth/signin")
	public String signinForm() {
		return  "auth/signin";
	}
	
	@GetMapping("/auth/signup")
	public String signupForm() {
		return "auth/signup";
	}
	
	@PostMapping("/auth/signup")
	public String signup(@Valid  SignupDto signupDto, BindingResult bindingResult) {	// form방식은 key = value (x-www-form-urlencoded)
		
		if(bindingResult.hasErrors()) {
			Map<String, String> errorMap = new HashMap<>();
			
			for(FieldError error:bindingResult.getFieldErrors()) {
				errorMap.put(error.getField(), error.getDefaultMessage());
				System.out.println(">>>>>>>>>>>>>>>>>>>>>>>"+ error.getDefaultMessage());
			}
			throw new CustomValidationException("유효성 검사 실패함", errorMap);
		} else {
			log.info(signupDto.toString());
			// User < - SignupDto
			User user = signupDto.toEntity();
			User userEntity = authService.회원가입(user);
			System.out.println(userEntity);
			log.info(user.toString());
			return "auth/signin";
		}			
	}
}
