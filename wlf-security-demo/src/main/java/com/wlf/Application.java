package com.wlf;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xyt.util.MD5Util;

@RestController
@SpringBootApplication
public class Application {

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new MD5Encryption();
	}
	
	public static void main(String[] args) {
		new SpringApplicationBuilder(Application.class).web(true).run(args);
	}
	
	@GetMapping("/hello")
	public String hello() {
		return "hello spring security";
	}
	
	class MD5Encryption implements PasswordEncoder {

		@Override
		public String encode(CharSequence rawPassword) {
			return MD5Util.MD5Encode(rawPassword.toString());
		}

		@Override
		public boolean matches(CharSequence rawPassword, String encodedPassword) {
			
			return MD5Util.MD5Encode(rawPassword.toString())
					.equals(encodedPassword);
		}
		
	}
	
}
