package com.simple.admin.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.simple.model.RuZhuSchool;
import com.simple.service.RuZhuSchoolService;
@Controller
@RequestMapping(value = "/ruzhuSchool")
public class RuZhuController {
	
	private static final Logger log = LoggerFactory.getLogger(RuZhuController.class);
	@Autowired
	RuZhuSchoolService ruzhuSchoolService;
	
	@RequestMapping(value = "detail",method=RequestMethod.GET)
	public String detail(HttpServletRequest request, HttpServletResponse response) {
		PrintWriter out = null;
		try {
			out = response.getWriter();
			response.setContentType("text/html;charset=UTF-8");
			request.setCharacterEncoding("UTF-8");
			RuZhuSchool rsz = ruzhuSchoolService.query();
			if (null == rsz || StringUtils.isEmpty(rsz.getContent())) {
				out.println("");
			}else {
				out.print(rsz.getContent());
			}
			return null;
		}catch(Exception e) {
			log.error("登录失败",e);
			e.printStackTrace();
			return null;
		} finally{
			if(null != out){
				out.flush();
				out.close();
			}
		}
	}
}
