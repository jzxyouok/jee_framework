package com.loy.e.core.web;

import java.util.List;
import java.util.Locale;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.loy.e.core.annotation.ControllerLogExeTime;
import com.loy.e.core.query.MapQueryParam;
import com.loy.e.core.util.UserUtils;
import com.loy.e.sys.domain.CommonQueryParam;
import com.loy.e.sys.domain.entity.DictionaryEntity;
import com.loy.e.sys.repository.DictionaryRepository;
import com.loy.e.sys.repository.PerformanceRepository;
/**
 * 
 * 
 * @author Loy Fu qq群 540553957
 * @since 1.7
 * @version 1.0.0
 *
 */
@RestController
@RequestMapping(value="/",method={RequestMethod.POST,RequestMethod.GET})

public class IndexController { 
	@Autowired
	PerformanceRepository performanceRepository;
	@Autowired
	DictionaryRepository dictionaryRepository;
	@ControllerLogExeTime(description="登入")
	@RequestMapping(value="/login") 
	public void login(String username,
			 String password) { 
		 AuthenticationToken token = new UsernamePasswordToken(username, password);
		 Subject currentUser = SecurityUtils.getSubject();
		 currentUser.login(token);
	}
	
	
	@ControllerLogExeTime(description="登出")
	@RequestMapping(value="/logout") 
	public void logout() { 
		 Subject currentUser = SecurityUtils.getSubject();
		 currentUser.logout();
	}
	
	
	@RequestMapping(value="/lang") 
	public void lang(String lang) { 
		SimpleUser simpleUser = UserUtils.getSimipleUser();
		String[] temp = lang.split("_");
		Locale locale = new Locale(temp[0], temp[1]);
		simpleUser.setLocale(locale);
	}
	
	@RequestMapping(value="/select") 
	public Page select(CommonQueryParam param,Pageable pageable) { 
		return performanceRepository.findPage("sys.common.findSelectPage",new MapQueryParam(param), pageable);
	}
	
	@RequestMapping(value="/dict") 
	public List<DictionaryEntity> dict(String group) { 
		List<DictionaryEntity> list = dictionaryRepository.findByGroup(group);
		return list;
	}
}
