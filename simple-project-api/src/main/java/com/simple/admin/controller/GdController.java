package com.simple.admin.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.simple.common.util.AjaxWebUtil;
import com.simple.common.util.PrimaryKeyUtil;
import com.simple.model.GdHomeWorkWorkersItem;
import com.simple.model.GdSign;
import com.simple.model.GdSignWorkers;
import com.simple.model.PageResult;
import com.simple.service.GdService;
@Controller
@RequestMapping(value = "/gd")
public class GdController {
	
	private static final Logger log = LoggerFactory.getLogger(GdController.class);
	@Autowired
	GdService gdService;
	
	@RequestMapping(value = "kaike",method=RequestMethod.POST)
	@ResponseBody
	public String kaike(String tanentId,int homeworkId,HttpServletRequest request, HttpServletResponse response) {
		try {
			GdSign gs = gdService.queryByTHD(tanentId, homeworkId, new Date());
			if (null == gs) {
				gs = new GdSign();
				gs.setCreateDate(new Date());
				gs.setCreateTime(new Date());
				gs.setId(PrimaryKeyUtil.getUUID());
				gs.setHomeworkId(homeworkId);
				gs.setTanentId(tanentId);
				gs.setStatus(1);
				gdService.addGdSign(gs);
			}
			return AjaxWebUtil.sendAjaxResponse(request, response, true,"开课成功", gs);
		}catch(Exception e) {
			log.error("gd kaike error.",e);
			return AjaxWebUtil.sendAjaxResponse(request, response, false,"开课失败:"+e.getLocalizedMessage(), e.getLocalizedMessage());
		}
	}
	
	@RequestMapping(value = "sign",method=RequestMethod.POST)
	@ResponseBody
	public String sign(String gsid,String cardNo,String cardImage,String name,int homeworkId,String tanentId,HttpServletRequest request, HttpServletResponse response) {
		try {
			if (StringUtils.isEmpty(cardNo)) {
				return AjaxWebUtil.sendAjaxResponse(request, response, false,"身份证号不能为空", "身份证号不能为空");
			}
			
			GdSignWorkers gsw =gdService.queryBySC(gsid, cardNo);
			if (null == gsw) {
				gsw = new GdSignWorkers();
				gsw.setId(PrimaryKeyUtil.getUUID());
				gsw.setGsid(gsid);
				gsw.setCardNo(cardNo);
				gsw.setCardImage(cardImage);
				gsw.setName(name);
				gsw.setCreateTime(new Date());
				gdService.addGdSignWorkers(gsw);
				
				GdHomeWorkWorkersItem gi = new GdHomeWorkWorkersItem();
				gi.setCardNo(cardNo);
				gi.setGdSignId(gsid);
				gi.setHomeworkId(homeworkId);
				gi.setName(name);
				gi.setTanentId(tanentId);
				gi.setSignTime(gsw.getCreateTime());
				gdService.addGdHomeWorkWorkersItem(gi);
			}
			return AjaxWebUtil.sendAjaxResponse(request, response, true,"签到成功", gsw);
		}catch(Exception e) {
			log.error("gd sign error.",e);
			return AjaxWebUtil.sendAjaxResponse(request, response, false,"签到失败:"+e.getLocalizedMessage(), e.getLocalizedMessage());
		}
	}
	
	@RequestMapping(value = "workers",method=RequestMethod.GET)
	@ResponseBody
	public String workers(String gsid,Integer page,Integer pageSize,HttpServletRequest request, HttpServletResponse response) {
		try {
			PageResult pr =gdService.queryWorkers(gsid, page, pageSize);
			return AjaxWebUtil.sendAjaxResponse(request, response, true,"查询成功", pr);
		}catch(Exception e) {
			log.error("gd sign error.",e);
			return AjaxWebUtil.sendAjaxResponse(request, response, false,"查询失败:"+e.getLocalizedMessage(), e.getLocalizedMessage());
		}
	}
	
	@RequestMapping(value = "kts",method=RequestMethod.GET)
	@ResponseBody
	public String kts(String tanentId,Integer page,Integer pageSize,HttpServletRequest request, HttpServletResponse response) {
		try {
			PageResult pr =gdService.queryHomeWorks(tanentId, page, pageSize);
			return AjaxWebUtil.sendAjaxResponse(request, response, true,"查询成功", pr);
		}catch(Exception e) {
			log.error("gd sign error.",e);
			return AjaxWebUtil.sendAjaxResponse(request, response, false,"查询失败:"+e.getLocalizedMessage(), e.getLocalizedMessage());
		}
	}
}
