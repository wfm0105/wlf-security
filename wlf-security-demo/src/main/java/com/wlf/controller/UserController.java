package com.wlf.controller;

import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.wlf.dto.User;
import com.wlf.dto.UserQueryCondition;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/user")
public class UserController {

	private Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@GetMapping
	@JsonView(User.UserSimpleView.class) 
	@ApiOperation(value="用户查询服务")
	public List<User> query(@Valid UserQueryCondition userCondition, 
							@PageableDefault(page=1, size=6, sort="username") Pageable page){
		logger.info(ReflectionToStringBuilder.toString(userCondition, ToStringStyle.MULTI_LINE_STYLE));
		logger.info(""+page.getPageNumber());
		logger.info(""+page.getPageSize());
		logger.info(""+page.getSort());
		return Arrays.asList(new User(),new User(),new User());
	}
	
	@GetMapping("/{userid:\\d+}")
	@JsonView(User.UserDetailView.class)
	@ApiOperation(value="根据id查询用户服务")
	public User getInfo(@ApiParam(value="用户id") @PathVariable("userid") String userid) {
		User user = new User();
		user.setUserid("1");
		user.setUsername("admin");

		return user;
	}
	
	@PostMapping
	@JsonView(User.UserSimpleView.class)
	@ApiOperation(value="新增用户服务")
	public User create(@Valid @RequestBody() User user) {
		user.setUserid("1");
		user.setUsername("admin");
		user.setPassword("123456");
		
		return user;
	}
	
	@PutMapping("/{userid:\\d+}")
	@ApiOperation(value="修改用户服务")
	public User update(@Valid @RequestBody() User user) {
		
		user.setUserid("1");
		user.setUsername("admin");
		user.setPassword("666666");
		
		return user;
		
	}
	
	@DeleteMapping("/{userid:\\d+}")
	@ApiOperation(value="删除用户服务")
	public void delete(@ApiParam(value="用户id") @PathVariable("userid") String userid) {
		logger.info("UserController.delete");
	}
	
}
