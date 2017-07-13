package com.simple.admin.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.simple.admin.util.LoginUserUtil;
import com.simple.common.util.AjaxWebUtil;
import com.simple.model.GdHomeWorkItems;
import com.simple.model.GdHomeWorkWorkersItem;
import com.simple.model.PageResult;
import com.simple.service.GdService;
@Controller
@RequestMapping(value = "/gd")
public class GdController {
	
	private static final Logger log = LoggerFactory.getLogger(GdController.class);
	@Autowired
	GdService gdService;
	
	@RequestMapping(value = "homeworks",method=RequestMethod.GET)
	@ResponseBody
	public String homeworkItems(int page,int pageSize,HttpServletRequest request, HttpServletResponse response) {
		try {
			PageResult gs = gdService.queryHomeWorks(LoginUserUtil.getLeaseholderId(request), page, pageSize);
			return AjaxWebUtil.sendAjaxResponse(request, response, true,"查询成功", gs);
		}catch(Exception e) {
			log.error("gd kaike error.",e);
			return AjaxWebUtil.sendAjaxResponse(request, response, false,"查询失败:"+e.getLocalizedMessage(), e.getLocalizedMessage());
		}
	}	
	
	@RequestMapping(value = "homeworksInfo",method=RequestMethod.GET)
	@ResponseBody
	public String homeworksInfo(int homeworkId,HttpServletRequest request, HttpServletResponse response) {
		try {
			GdHomeWorkItems gs = gdService.queryHomeWorkItem(LoginUserUtil.getLeaseholderId(request), homeworkId);
			return AjaxWebUtil.sendAjaxResponse(request, response, true,"查询成功", gs);
		}catch(Exception e) {
			log.error("gd kaike error.",e);
			return AjaxWebUtil.sendAjaxResponse(request, response, false,"查询失败:"+e.getLocalizedMessage(), e.getLocalizedMessage());
		}
	}
	
	@RequestMapping(value = "updateHomeworkItems",method=RequestMethod.POST)
	@ResponseBody
	public String updateHomeworkItems(int homeworkId,String items,HttpServletRequest request, HttpServletResponse response) {
		try {
			GdHomeWorkItems gs = gdService.queryHomeWorkItem(LoginUserUtil.getLeaseholderId(request), homeworkId);
			gs.setItemNames(items);
			gdService.updateGdHomeWorkItems(gs);
			return AjaxWebUtil.sendAjaxResponse(request, response, true,"更新成功", gs);
		}catch(Exception e) {
			log.error("gd kaike error.",e);
			return AjaxWebUtil.sendAjaxResponse(request, response, false,"更新失败:"+e.getLocalizedMessage(), e.getLocalizedMessage());
		}
	}
	
	@RequestMapping(value = "homeworkWorkersItemList",method=RequestMethod.POST)
	@ResponseBody
	public String homeworkWorkersItemList(String cardNo,Integer homeworkId,String beginTime,String endTime, int page,
			int pageSize,HttpServletRequest request, HttpServletResponse response) {
		try {
			PageResult pr = gdService.queryHomeworkWorkersItem(LoginUserUtil.getLeaseholderId(request), cardNo, homeworkId, beginTime, endTime, page, pageSize);
			return AjaxWebUtil.sendAjaxResponse(request, response, true,"查询成功", pr);
		}catch(Exception e) {
			log.error("gd kaike error.",e);
			return AjaxWebUtil.sendAjaxResponse(request, response, false,"查询失败:"+e.getLocalizedMessage(), e.getLocalizedMessage());
		}
	}
	
	@RequestMapping(value = "homeworkWorkersItemInfo",method=RequestMethod.GET)
	@ResponseBody
	public String homeworkWorkersItemInfo(String cardNo,String gdSignId,HttpServletRequest request, HttpServletResponse response) {
		try {
			GdHomeWorkWorkersItem pr = gdService.queryHomeworkWorkersItem(gdSignId, cardNo);
			//TODO 查询分数等信息
			return AjaxWebUtil.sendAjaxResponse(request, response, true,"查询成功", pr);
		}catch(Exception e) {
			log.error("gd kaike error.",e);
			return AjaxWebUtil.sendAjaxResponse(request, response, false,"查询失败:"+e.getLocalizedMessage(), e.getLocalizedMessage());
		}
	}
	
	
	@RequestMapping(value = "updateHomeworkWorkersItem",method=RequestMethod.POST)
	@ResponseBody
	public String updateHomeworkWorkersItem(String gdSignId,String cardNo,String itemJson,String beginTime,String endTime, int page,
			int pageSize,HttpServletRequest request, HttpServletResponse response) {
		try {
			GdHomeWorkWorkersItem gi = gdService.queryHomeworkWorkersItem(gdSignId, cardNo);
			gi.setCreateTime(new Date());
			//TODO 查询答题的时间
			gi.setHomeworkTime(homeworkTime);
			gi.setItemJson(itemJson);
			gdService.updateGdHomeWorkWorkersItem(gi);
			return AjaxWebUtil.sendAjaxResponse(request, response, true,"更新成功", null);
		}catch(Exception e) {
			log.error("gd kaike error.",e);
			return AjaxWebUtil.sendAjaxResponse(request, response, false,"查询失败:"+e.getLocalizedMessage(), e.getLocalizedMessage());
		}
	}
	
	@RequestMapping(value = "homeworkWorkersItemReport",method=RequestMethod.POST)
	@ResponseBody
	public String homeworkWorkersItemReport(String cardNo,Integer homeworkId,String beginTime,String endTime, int page,
			int pageSize,HttpServletRequest request, HttpServletResponse response) {
		try {
			PageResult pr = gdService.queryHomeworkWorkersItem(LoginUserUtil.getLeaseholderId(request), cardNo, homeworkId, beginTime, endTime, page, pageSize);
			//TODO 查询其他信息
			return AjaxWebUtil.sendAjaxResponse(request, response, true,"查询成功", pr);
		}catch(Exception e) {
			log.error("gd kaike error.",e);
			return AjaxWebUtil.sendAjaxResponse(request, response, false,"查询失败:"+e.getLocalizedMessage(), e.getLocalizedMessage());
		}
	}
	
	@RequestMapping(value = "homeworkWorkersItemReportExport",method=RequestMethod.POST)
	@ResponseBody
	public String homeworkWorkersItemReportExport(String cardNo,Integer homeworkId,String beginTime,String endTime, int page,
			int pageSize,HttpServletRequest request, HttpServletResponse response) {
		try {
			PageResult pr = gdService.queryHomeworkWorkersItem(LoginUserUtil.getLeaseholderId(request), cardNo, homeworkId, beginTime, endTime, page, pageSize);
			//TODO 查询其他信息
			return AjaxWebUtil.sendAjaxResponse(request, response, true,"查询成功", pr);
		}catch(Exception e) {
			log.error("gd kaike error.",e);
			return AjaxWebUtil.sendAjaxResponse(request, response, false,"查询失败:"+e.getLocalizedMessage(), e.getLocalizedMessage());
		}
	}
	
}
