package com.wlf.controller;

import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.wlf.dto.User;
import com.wlf.dto.UserQueryCondition;

@RestController
@RequestMapping("/user")
public class UserController {

	@GetMapping
	@JsonView(User.UserSimpleView.class) 
	public List<User> query(UserQueryCondition userCondition, 
							@PageableDefault(page=1, size=6, sort="username") Pageable page){
		System.out.println(ReflectionToStringBuilder.toString(userCondition, ToStringStyle.MULTI_LINE_STYLE));
		System.out.println(page.getPageNumber());
		System.out.println(page.getPageSize());
		System.out.println(page.getSort());
		return Arrays.asList(new User(),new User(),new User());
	}
	
	@GetMapping("/{userid:\\d+}")
	@JsonView(User.UserDetailView.class)
	public User getInfo(@PathVariable("userid") String userid) {
		User user = new User();
		user.setUserid("1");
		user.setUsername("admin");
		return user;
	}
	
	@PostMapping
	@JsonView(User.UserSimpleView.class)
	public User create(@Valid @RequestBody() User user, BindingResult errors) {
		
		if(errors.hasErrors()) {
			errors.getAllErrors()
				    .stream()
				    .forEach(error->{
				    	System.out.println(error.getDefaultMessage());
				    });
		}
		
		user.setUserid("1");
		user.setUsername("admin");
		user.setPassword("123456");
		
		return user;
		
	}
	
}
