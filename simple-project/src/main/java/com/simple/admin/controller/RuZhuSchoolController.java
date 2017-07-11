package com.simple.admin.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.simple.common.config.EnvPropertiesConfiger;
import com.simple.common.util.AjaxWebUtil;
import com.simple.model.RuZhuSchool;
import com.simple.service.RuZhuSchoolService;
@Controller
@RequestMapping(value = "/ruzhuschool")
public class RuZhuSchoolController {
	
	private static final Logger log = LoggerFactory.getLogger(RuZhuSchoolController.class);

	@Autowired
	RuZhuSchoolService ruzhuSchoolService;
	
	@RequestMapping(value = "doUpdate",method=RequestMethod.POST)
	@ResponseBody
	public String doUpdate(RuZhuSchool ruzhuSchool,HttpServletRequest request, HttpServletResponse response) {
		ruzhuSchoolService.addOrUpdate(ruzhuSchool);
		request.setAttribute("projectpath", StringUtils.trimToEmpty(EnvPropertiesConfiger.getValue("projectpath")));
		return "ok";
	}
	
	@RequestMapping(value = "detail",method=RequestMethod.GET)
	public String detail(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("projectpath", StringUtils.trimToEmpty(EnvPropertiesConfiger.getValue("projectpath")));
		RuZhuSchool rs = ruzhuSchoolService.query();
		if (null == rs) {
			request.setAttribute("content", null);
		}else {
			request.setAttribute("content", rs.getContent());
		}
		return "ruzhu";
	}
}
