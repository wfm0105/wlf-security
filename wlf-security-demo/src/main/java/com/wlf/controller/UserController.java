package com.wlf.controller;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wlf.dto.User;
import com.wlf.dto.UserQueryCondition;

@RestController
public class UserController {

	@GetMapping("/user")
	public List<User> query(UserQueryCondition userCondition, 
							@PageableDefault(page=1, size=6, sort="username") Pageable page){
		System.out.println(ReflectionToStringBuilder.toString(userCondition, ToStringStyle.MULTI_LINE_STYLE));
		System.out.println(page.getPageNumber());
		System.out.println(page.getPageSize());
		System.out.println(page.getSort());
		return Arrays.asList(new User(),new User(),new User());
	}
	
}
